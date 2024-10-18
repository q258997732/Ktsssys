package com.bob.ktssts.util;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import java.io.DataOutputStream;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Slf4j
@Component
public class ToptronExecuter {

	@Value("${TOPTRON.HOST}")
	private String host;

	@Value("${TOPTRON.PORT}")
	private int port;

	@Value("${TOPTRON.TOKEN}")
	private String tempToken;

	public boolean confirmTempToken(JsonNode jsonNode){
		String token = jsonNode.get("token").asText();
		return "88xVcGG~z4CV!@%$@#FGSWADHAirtqoitj123".equals(token);
	}

	public boolean ExecutePowerControl(JsonNode jsonNode) {
		/* 读取Json内容 */
		boolean turnon ;
		String gatePosition;
		System.out.println("json: "+jsonNode.toString());
		try {
			turnon = jsonNode.get("turnon").asBoolean();
			gatePosition = jsonNode.get("gatePosition").asText();
			if (gatePosition == null) {
				return false;
			}
		} catch (NullPointerException nullPointerException) {
			log.error(nullPointerException.getMessage());
			return false;
		}

		/* 拼接socket发送串 */
		String status = turnon ? "1" : "0";
		String hexString = "fa000002000305fdff000" + gatePosition + "0" + status + "55";
		System.out.println("执行码: "+hexString);
		return sendToptronMsg(hexString);
	}

	public boolean ExecutePowerControl(int gatePosition, boolean turnon) {
		/* 拼接socket发送串 */
		String status = turnon ? "1" : "0";
		String hexString = "fa000002000305fdff000" + gatePosition + "0" + status + "55";
		return sendToptronMsg(hexString);
	}

	public boolean ExecuteControlMsg(JsonNode jsonNode) {
		String cMsg;
		System.out.println("json: "+jsonNode.toString());
		// 获取报文内容
		try {
			cMsg = jsonNode.get("controlMsg").asText();
			if (cMsg == null) {
				return false;
			}
		} catch (NullPointerException nullPointerException) {
			log.error(nullPointerException.getMessage());
			return false;
		}
		return sendToptronMsg(cMsg);
	}

	/* 发送至Toptron中控设备 */
	private boolean sendToptronMsg(String hexString) {
		byte[] hexBytes = hexStringToByteArray(hexString);

		try (Socket socket = new Socket(host, port); DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
			/* 发送Socket报文 */
			dataOutputStream.write(hexBytes);
			dataOutputStream.flush();
//		System.out.println("16进制内容已发送");
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

}
