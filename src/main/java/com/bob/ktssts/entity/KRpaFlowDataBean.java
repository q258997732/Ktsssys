package com.bob.ktssts.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class KRpaFlowDataBean {
	public KRpaFlowDataBean() {
	}

	public KRpaFlowDataBean(String port, String ip, String siteId, String tmsId, String userName, String password) {
		this.id= UUID.randomUUID().toString().replace("-","").toUpperCase();
		this.port = port;
		this.ip = ip;
		this.siteId = siteId;
		this.tmsId = tmsId;
		this.userName = userName;
		this.password = password;
	}

	public KRpaFlowDataBean(String password, String userName, String tmsId, String siteId, String port, String ip, String tmsName, String id) {
		this.password = password;
		this.userName = userName;
		this.tmsId = tmsId;
		this.siteId = siteId;
		this.port = port;
		this.ip = ip;
		this.tmsName = tmsName;
		this.id = id;
	}

	private String id;
	private String tmsName;
	private String ip;
	private String port;
	private String siteId;
	private String tmsId;
	private String userName;
	private String password;

	// 返回Json格式数据
	public String toString() {
		return "{\"id\":\"" + id + "\",\"tmsName\":\"" + tmsName + "\",\"ip\":\"" + ip + "\",\"port\":\"" + port
				+ "\",\"siteId\":\"" + siteId + "\",\"tmsId\":\"" + tmsId + "\",\"userName\":\"" + userName
				+ "\",\"password\":\"" + password + "\"}";
	}

}
