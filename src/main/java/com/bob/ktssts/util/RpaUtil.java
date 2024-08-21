package com.bob.ktssts.util;

import com.bob.ktssts.entity.*;
import com.bob.ktssts.entity.ktss.KAgentBean;
import com.bob.ktssts.entity.ktss.KAgentThreadBean;
import com.bob.ktssts.entity.ktss.KFlowBean;
import com.bob.ktssts.entity.ktss.KSxfAgentBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @return 返回内容中提取target的值
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
			case "GetAgentList" -> getKRpaAgentListRes(rpaRequestBeanList);
			case "GetSelfRole" -> getKRpaFlowListRes(rpaRequestBeanList);
			default -> "调用K-RPA接口后返回的错误信息未能解析:"+rpaRequestBeanList;
		};
	}

	public static String getKRpaCallComponentRes(List<RpaRequestBean> rpaRequestBeanList) {
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
		return String.format("K-RPA任务队列： 添加 %s 任务 %s , 分配机器人为: %s", flowName, "".equals(error) ? "成功" : "失败", robot);
	}

	public static String getSXFAgentFlowQueryRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return String.format("K-RPA当天任务获取: 执行%s", "".equals(error) ? "成功" : "失败");
	}

	public static String getKRpaAgentListRes(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return String.format("K-RPA获取Agent列表任务: 执行%s", "".equals(error) ? "成功" : "失败");
	}

	public static boolean callFunStatus(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return false;
		Object Level = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return "".equals(Level);
	}

	public static String getKRpaFlowListRes(List<RpaRequestBean> rpaRequestBeanList){
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Object error = getRpaResponseValue("{50043442-8A69-4A6B-A8B5-61F882EDE4F3}", rpaRequestBeanList);
		return String.format("K-RPA获取Flow列表任务: 执行%s", "".equals(error) ? "成功" : "失败");
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

	/**
	 * 写完发现这玩意儿用不上
	 * 将K-RPA API返回的内容转换为KAgentBean数组，只适用于K-RPA接口的getSXFAgentFlowQuery方法
	 *
	 * @param rpaRequestBeanList 接口返回的内容
	 * @return KAgent数组
	 */
	public static List<KSxfAgentBean> result2KSxfAgent(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;

		Map<Integer, String> KAgentInvMap = new HashMap<>();
		List<KSxfAgentBean> kSxfAgentBeanList = new ArrayList<>();

		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if ("k_agent_object".equals(rpaRequestBean.getName())) {
				List<List<Map<String, String>>> KAgentList = (List<List<Map<String, String>>>) rpaRequestBean.getValue();
				for (int i = 0; i < KAgentList.size(); i++) {
					if (i == 0) {
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							Map<String, String> KAgentMap = KAgentList.get(i).get(j);
							KAgentMap.get("Name");
							String name = KAgentMap.get("Name");
							if (!"".equals(name) && !name.isEmpty()) {
								KAgentInvMap.put(j, name);
							}
						}
					} else {
						Map<String, Object> beanParamMap = new HashMap<>();
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							beanParamMap.put(KAgentInvMap.get(j), KAgentList.get(i).get(j).get("Value"));
						}
						KSxfAgentBean kSxfAgentBean = new KSxfAgentBean(beanParamMap);
						kSxfAgentBeanList.add(kSxfAgentBean);
					}
				}
				break;
			}
		}

		return kSxfAgentBeanList;
	}

	public static Map<String,KAgentThreadBean> result2KAgentThreadList(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;
		Map<Integer, String> KAgentInvMap = new HashMap<>();
		Map<String,KAgentThreadBean> kAgentThreadBeanMap = new HashMap<>();

		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if ("k_agent".equals(rpaRequestBean.getName())) {
				List<List<Map<String, String>>> KAgentThreadList = (List<List<Map<String, String>>>) rpaRequestBean.getValue();
				for (int i = 0; i < KAgentThreadList.size(); i++) {
					if (i == 0) {
						for (int j = 0; j < KAgentThreadList.get(i).size(); j++) {
							Map<String, String> KAgentMap = KAgentThreadList.get(i).get(j);
							KAgentMap.get("Name");
							String name = KAgentMap.get("Name");
							if (!"".equals(name) && !name.isEmpty()) {
								KAgentInvMap.put(j, name);
							}
						}
					} else {
						Map<String, Object> beanParamMap = new HashMap<>();
						for (int j = 0; j < KAgentThreadList.get(i).size(); j++) {
							beanParamMap.put(KAgentInvMap.get(j), KAgentThreadList.get(i).get(j).get("Value"));
						}
						KAgentThreadBean kAgentThreadBean = new KAgentThreadBean(beanParamMap);
						kAgentThreadBeanMap.put(kAgentThreadBean.getID(),kAgentThreadBean);
					}
				}
				break;
			}
		}
		return kAgentThreadBeanMap;
	}

	public static List<KAgentBean> result2KAgentList(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;

		Map<Integer, String> KAgentInvMap = new HashMap<>();
		List<KAgentBean> kAgentBeanList = new ArrayList<>();

		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if ("k_agent".equals(rpaRequestBean.getName())) {
				List<List<Map<String, String>>> KAgentList = (List<List<Map<String, String>>>) rpaRequestBean.getValue();
				for (int i = 0; i < KAgentList.size(); i++) {
					if (i == 0) {
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							Map<String, String> KAgentMap = KAgentList.get(i).get(j);
							KAgentMap.get("Name");
							String name = KAgentMap.get("Name");
							if (!"".equals(name) && !name.isEmpty()) {
								KAgentInvMap.put(j, name);
							}
						}
					} else {
						Map<String, Object> beanParamMap = new HashMap<>();
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							beanParamMap.put(KAgentInvMap.get(j), KAgentList.get(i).get(j).get("Value"));
						}
						KAgentBean kAgentBean = new KAgentBean(beanParamMap);
						kAgentBeanList.add(kAgentBean);
					}
				}
				break;
			}
		}

		LOGGER.debug("result2KAgentList size : {}", kAgentBeanList.size());

		return kAgentBeanList;
	}

	public static List<KFlowBean> result2KFlowtList(List<RpaRequestBean> rpaRequestBeanList) {
		if (rpaRequestBeanList == null || rpaRequestBeanList.isEmpty()) return null;

		Map<Integer, String> KFlowInvMap = new HashMap<>();
		List<KFlowBean> kFlowBeans = new ArrayList<>();

		for (RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
			if ("k_flow_info".equals(rpaRequestBean.getName())) {
				List<List<Map<String, String>>> KAgentList = (List<List<Map<String, String>>>) rpaRequestBean.getValue();
				for (int i = 0; i < KAgentList.size(); i++) {
					if (i == 0) {
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							Map<String, String> KAgentMap = KAgentList.get(i).get(j);
							KAgentMap.get("Name");
							String name = KAgentMap.get("Name");
							if (!"".equals(name) && !name.isEmpty()) {
								KFlowInvMap.put(j, name);
							}
						}
					} else {
						Map<String, Object> beanParamMap = new HashMap<>();
						for (int j = 0; j < KAgentList.get(i).size(); j++) {
							beanParamMap.put(KFlowInvMap.get(j), KAgentList.get(i).get(j).get("Value"));
						}
						KFlowBean kFlowBean = new KFlowBean(beanParamMap);
						kFlowBeans.add(kFlowBean);
					}
				}
				break;
			}
		}

		LOGGER.debug("result2KFlowList size : {}", kFlowBeans.size());

		return kFlowBeans;
	}

	public static boolean kRpaIsAboveMaxThread(String id, Map<String, KAgentThreadBean> kAgentThreadBeanMap, int limit){
		LOGGER.debug("id:{} map:{}",id,kAgentThreadBeanMap);
		if(kAgentThreadBeanMap == null || kAgentThreadBeanMap.isEmpty() || limit <= 0 || id == null || id.isEmpty()) return false;
		if(kAgentThreadBeanMap.get(id) == null) return false;
		int agentExecThread = Integer.parseInt(kAgentThreadBeanMap.get(id).getExecCount());
		int agentWaitThread = Integer.parseInt(kAgentThreadBeanMap.get(id).getWaitCount());
		return agentExecThread + agentWaitThread < limit;
	}

}

