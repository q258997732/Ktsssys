package com.bob.ktssts.util;

import com.bob.ktssts.entity.RpaRequestBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RpaExecuter {

	private static final Logger LOGGER = LogManager.getLogger(RpaExecuter.class);

	private String url;

	@Value("${RPA.HOST}")
	private String host;

	@Value("${RPA.PORT}")
	private int port;

	@Value("${RPA.USER}")
	private String user;

	@Value("${RPA.PASS}")
	private String pass;

	@Value("${RPA.CALLFUN.TIMEOUT:5000}")
	private int timeout;

	@PostConstruct
	public void init() {
		LOGGER.info("Init RpaExecuter ...");
		url = String.format("http://%s:%s/CallFunc.aom", getHost(), getPort());
		LOGGER.info(String.format("RpaExecuter init info , Host: %s, Port: %s, User: %s, Pass: %s", getHost(), getPort(), getUser(), getPass()));
	}

	public boolean callRpaComponent(JsonNode jsonNode) {
		String script;
		String params;
		String agentIp;
		try {
			script = jsonNode.get("script").asText();
			params = jsonNode.get("params").asText();
			agentIp = jsonNode.get("agentIp").asText();
		} catch (NullPointerException nullPointerException) {
			LOGGER.error(nullPointerException.getMessage());
			return false;
		}
		return callRpaComponent(script, params, agentIp);
	}

	public boolean callRpaComponent(String script, String params, String agentIp) {

		/* 拼接请求主体 */
		List<RpaRequestBean> rpaRequestBeans = new ArrayList<RpaRequestBean>();
		// 调用的AgentIP,多个IP用逗号隔开
		rpaRequestBeans.add(new RpaRequestBean(agentIp, 4, "IP"));
		// 调用的组件名称
		rpaRequestBeans.add(new RpaRequestBean(script, 4, "VCLName"));
		// 参数
		rpaRequestBeans.add(new RpaRequestBean(params, 7, "Params"));
		rpaRequestBeans.add(new RpaRequestBean(getPass(), 4, "AppPass"));
		rpaRequestBeans.add(new RpaRequestBean(getUser(), 4, "AppName"));
		rpaRequestBeans.add(new RpaRequestBean("TSystemDM", 4, "{9F8E5ECB-5976-4315-B8F3-43B8502B694D}"));
		rpaRequestBeans.add(new RpaRequestBean("CallComponent", 4, "{2881E26D-62CE-4937-B4BB-8998440417C4}"));
		rpaRequestBeans.add(new RpaRequestBean(true, 1, "VCLSynchro"));
		rpaRequestBeans.add(new RpaRequestBean(false, 1, "VCLBlockInput"));
		rpaRequestBeans.add(new RpaRequestBean(5000, 0, "TimeOut"));
		rpaRequestBeans.add(new RpaRequestBean(0, 0, "AomScript"));

		// 调用K-RPA RESTFul接口
		return sendKRpaRequest(rpaRequestBeans);
	}

	public boolean addRpaProcess(String flowType, String flow, String data, String agentIp,int level) {
		/* 拼接请求主体 */
		List<RpaRequestBean> rpaRequestBeans = new ArrayList<RpaRequestBean>();
		// 执行任务的AgentIP
		rpaRequestBeans.add(new RpaRequestBean(agentIp, 4, "Robot"));
		// 流程数据转Base64
		rpaRequestBeans.add(new RpaRequestBean(data, 4, "Data"));
		// FlowName与FlowID二选一，两者存在以FlowID为主（FlowName填写流程完整路径，如:分组1\\流程1）
		rpaRequestBeans.add(new RpaRequestBean(flow, 4, "name".equals(flowType)?"FlowName":"FlowID"));
		// 是否空闲机器人
		rpaRequestBeans.add(new RpaRequestBean(true, 1, "IdleRobot"));
		// 任务优先级 0-4 数字越大优先级越高
		rpaRequestBeans.add(new RpaRequestBean(level, 0, "Level"));

		/* 以下参数不需要调整 */
		rpaRequestBeans.add(new RpaRequestBean("TRPADM", 4, "{9F8E5ECB-5976-4315-B8F3-43B8502B694D}"));
		// 是否第三方调用
		rpaRequestBeans.add(new RpaRequestBean(true, 1, "IsThird"));
		// 是否加入数据队列
		rpaRequestBeans.add(new RpaRequestBean(true, 1, "IsQueue"));
		// 任务名称
		rpaRequestBeans.add(new RpaRequestBean("KTSSTS任务调度", 4, "TaskName"));
		rpaRequestBeans.add(new RpaRequestBean(getUser(), 4, "AppName"));
		rpaRequestBeans.add(new RpaRequestBean(getPass(), 4, "AppPass"));
		rpaRequestBeans.add(new RpaRequestBean("AddDataQueue", 4, "{2881E26D-62CE-4937-B4BB-8998440417C4}"));
		return sendKRpaRequest(rpaRequestBeans);
	}

	/**
	 * @param rpaRequestBeans K-RPA的请求主体List
	 * @return 执行是否成功
	 */
	public boolean sendKRpaRequest(List<RpaRequestBean> rpaRequestBeans) {
		String json;
		try {
			ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(new PascalCaseNamingStrategy());
			json = objectMapper.writeValueAsString(rpaRequestBeans);
			LOGGER.info("Request json : {}", json);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return false;
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			String responseBody = EntityUtils.toString(response.getEntity());
			LOGGER.info("Response json : {}", responseBody);
			// 处理返回结果
			List<RpaRequestBean> rpaRequestBeanList = result2KRpaRequestBean(responseBody);
			if (RpaUtil.callFunStatus(rpaRequestBeanList)) {
				LOGGER.info(RpaUtil.getRpaResponseRes(rpaRequestBeanList));
				return true;
			} else {
				LOGGER.error(RpaUtil.getRpaResponseRes(rpaRequestBeanList));
				return false;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	private List<RpaRequestBean> result2KRpaRequestBean(String json) {
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

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getUrl() {
		return url;
	}
}
