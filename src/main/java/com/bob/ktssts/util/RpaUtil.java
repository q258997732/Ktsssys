package com.bob.ktssts.util;

import com.bob.ktssts.entity.KAgentBean;
import com.bob.ktssts.entity.RpaRequestBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RpaUtil {

	private static final Logger LOGGER = LogManager.getLogger(RpaUtil.class);

	public static String parseRpaParams(List<String> strings) {
		if (strings == null || strings.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < strings.size(); i++) {
			sb.append("'");
			sb.append(strings.get(i));
			sb.append("'");
			if (i != strings.size() - 1) {
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param target             VCLAgentIP:执行agentIP Value:执行结果 VCLName:调用组件 Description:描述 Level:级别
	 * @param rpaRequestBeanList K-RPA API返回的内容
	 * @return	返回内容中提取target的值
	 */
	public static Object getRpaResponseValue(String target, List<RpaRequestBean> rpaRequestBeanList) {
		Object Result = null;
		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if (target.equals(rpaRequestBean.getName())) {
				Result = rpaRequestBean.getValue();
			}
		}
		return Result;
	}

	public static String getRpaResponseRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return "K-RPA 返回数据为空";
		String funName = getRpaResponseValue("{2881E26D-62CE-4937-B4BB-8998440417C4}", rpaRequestBeanList).toString();
		return switch (funName) {
			case "CallComponent" -> getKRpaCallComponentRes(rpaRequestBeanList);
			case "GetFlowIDByFullPath" -> getKRpaAddDataQueueRes(rpaRequestBeanList);
			case "GetSXFAgentFlowQuery" -> getSXFAgentFlowQueryRes(rpaRequestBeanList);
			default -> "未匹配的K-RPA接口返回信息";
		};
	}

	public static String getKRpaCallComponentRes(List<RpaRequestBean> rpaRequestBeanList){
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object Result = getRpaResponseValue("Value", rpaRequestBeanList);
		Object Level = getRpaResponseValue("Level", rpaRequestBeanList);
		Object Description = getRpaResponseValue("Description", rpaRequestBeanList);
		Object VCLAgentIP = getRpaResponseValue("VCLAgentIP", rpaRequestBeanList);
		Object VCLName = getRpaResponseValue("VCLName", rpaRequestBeanList);
		return String.format("K-RPA组件: 在机器: %s 调用 %s 结果: %s %s %s", VCLAgentIP, VCLName, Level, Result, Description);
	}

	public static String getKRpaAddDataQueueRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object flowName = getRpaResponseValue("FlowName", rpaRequestBeanList);
		Object robot = getRpaResponseValue("Robot", rpaRequestBeanList);
		Object error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return String.format("K-RPA任务队列： 添加 %s 任务 %s , 分配机器人为: %s",flowName,"".equals(error)?"成功":"失败",robot);
	}

	public static String getSXFAgentFlowQueryRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		Object bDate = getRpaResponseValue("BDate", rpaRequestBeanList);
		Object eDate = getRpaResponseValue("EDate", rpaRequestBeanList);
		return String.format("K-RPA任务执行: 获取 %s 至 %s 期间执行的任务 %s",bDate,eDate,"".equals(error)?"成功":"失败");
	}

	public static boolean callFunStatus(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return false;
		Object Level = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return "".equals(Level);
	}

	public static List<KAgentBean> getRpaResAgentList(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		String kAgentObject = getRpaResponseValue("k_agent_object",rpaRequestBeanList).toString();
//		LOGGER.info("kAgentObject: {}", kAgentObject);
		return result2KAgentRequestBean(kAgentObject);
	}

	public static List<RpaRequestBean> result2KRpaRequestBean(String json) {
//		LOGGER.warn("response json: {}",json);
		ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(new PascalCaseNamingStrategy());
		List<RpaRequestBean> rpaRequestBeanList;
		try {
			rpaRequestBeanList = objectMapper.readValue(json, new TypeReference<List<RpaRequestBean>>() {
			});
		} catch (JsonProcessingException e) {
			LOGGER.error("Parse Rpa response to json fail :{}", json);
			return null;
		}
		return rpaRequestBeanList;
	}

	public static List<KAgentBean> result2KAgentRequestBean(String json) {
//		json = json.substring(1, json.length() - 1);
		System.out.println("json: " + json);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(json);
			LOGGER.info("length: {}",jsonNode.size());
			LOGGER.info("node 1: {}",jsonNode.get(0).toString());
		} catch (JsonProcessingException e) {
			LOGGER.error("Parse Rpa Agent String to json fail .");
		}

		return null;
	}

}
