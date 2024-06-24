package com.bob.ktssts.util;

import com.bob.ktssts.entity.RpaRequestBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RpaUtil {

	private static final Logger LOGGER = LogManager.getLogger(RpaUtil.class);

	public static String parseRpaParams(List<String> strings){
		if (strings==null || strings.isEmpty()){
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strings.size(); i++) {
			sb.append("'");
			sb.append(strings.get(i));
			sb.append("'");
			if(i!=strings.size()-1){
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * @param target VCLAgentIP:执行agentIP Value:执行结果 VCLName:调用组件 Description:描述 Level:级别
	 * @param rpaRequestBeanList
	 * @return
	 */
	public static String getRpaResponseValue(String target, List<RpaRequestBean> rpaRequestBeanList){
		String Result = null;
		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if(target.equals(rpaRequestBean.getName())){
				Result = rpaRequestBean.getValue().toString();
			}
		}
		return Result;
	}

	public static String getRpaResponseRes(List<RpaRequestBean> rpaRequestBeanList){
		if(rpaRequestBeanList==null || rpaRequestBeanList.isEmpty()) return null;
		String Result = getRpaResponseValue("Value",rpaRequestBeanList);
		String Level = getRpaResponseValue("Level",rpaRequestBeanList);
		String Description = getRpaResponseValue("Description",rpaRequestBeanList);
		String VCLAgentIP = getRpaResponseValue("VCLAgentIP",rpaRequestBeanList);
		String VCLName = getRpaResponseValue("VCLName",rpaRequestBeanList);
		return String.format("在机器: %s 调用 %s 组件结果: %s %s %s", VCLAgentIP, VCLName, Level, Result, Description);
	}

	public static boolean callFunStatus(List<RpaRequestBean> rpaRequestBeanList){
		if(rpaRequestBeanList==null || rpaRequestBeanList.isEmpty()) return false;
		String Level = getRpaResponseValue("Level",rpaRequestBeanList);
		return "正常".equals(Level);
	}
}
