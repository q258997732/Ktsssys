package com.bob.ktssts.util;

import com.bob.ktssts.entity.RpaRequestBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RpaUtil {

	private static final Logger LOGGER = LogManager.getLogger(RpaUtil.class);

	public static String parseRpaParams(List<String> strings) {
		if (strings == null || strings.isEmpty()) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

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
	 * @param rpaRequestBeanList
	 * @return
	 */
	public static String getRpaResponseValue(String target, List<RpaRequestBean> rpaRequestBeanList) {
		String Result = null;
		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if (target.equals(rpaRequestBean.getName())) {
				Result = rpaRequestBean.getValue().toString();
			}
		}
		return Result;
	}

	public static String getRpaResponseRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return "K-RPA 返回数据为空";
		String funName = getRpaResponseValue("{2881E26D-62CE-4937-B4BB-8998440417C4}", rpaRequestBeanList);
		return switch (funName) {
			case "CallComponent" -> getKRpaCallComponentRes(rpaRequestBeanList);
			case "GetFlowIDByFullPath" -> getKRpaAddDataQueueRes(rpaRequestBeanList);
			default -> "未匹配的K-RPA接口返回信息";
		};
	}

	public static String getKRpaCallComponentRes(List<RpaRequestBean> rpaRequestBeanList){
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		String Result = getRpaResponseValue("Value", rpaRequestBeanList);
		String Level = getRpaResponseValue("Level", rpaRequestBeanList);
		String Description = getRpaResponseValue("Description", rpaRequestBeanList);
		String VCLAgentIP = getRpaResponseValue("VCLAgentIP", rpaRequestBeanList);
		String VCLName = getRpaResponseValue("VCLName", rpaRequestBeanList);
		return String.format("K-RPA组件: 在机器: %s 调用 %s 结果: %s %s %s", VCLAgentIP, VCLName, Level, Result, Description);
	}

	public static String getKRpaAddDataQueueRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		String flowName = getRpaResponseValue("FlowName", rpaRequestBeanList);
		String robot = getRpaResponseValue("Robot", rpaRequestBeanList);
		String error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return String.format("K-RPA任务队列： 添加 %s 任务 %s , 分配机器人为: %s",flowName,"".equals(error)?"成功":"失败",robot);
	}

	public static boolean callFunStatus(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return false;
		String Level = getRpaResponseValue("Level", rpaRequestBeanList);
		return "正常".equals(Level);
	}
}
