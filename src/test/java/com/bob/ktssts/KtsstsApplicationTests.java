package com.bob.ktssts;

import com.bob.ktssts.entity.KAgentBean;
import com.bob.ktssts.entity.TsApiuser;
import com.bob.ktssts.mapper.TsApiuserMapper;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KtsstsApplicationTests {

	private static final Logger LOGGER = LogManager.getLogger(KtsstsApplicationTests.class);

	@Autowired
	public RpaExecuter rpaExecuter;

	@Autowired
	private TsExecuterMapper tsExecuterMapper;

	@Autowired
	private TsApiuserMapper tsApiuserMapper;

	@Test
	void contextLoads() throws JsonProcessingException {
		List<KAgentBean> kAgentBeanList = rpaExecuter.getKRpaAgentList();
		LOGGER.info("KAgentBeanList size: {}",kAgentBeanList.size());







//		List<String> params = new ArrayList<>();
//
//		params.add("111");
//		params.add("222");
//		String script = "测试API组件调用";
//		String agentIp = "10.1.20.7,10.1.20.8,10.1.20.47";
//		String originalString = "{\"rpa_task_ID\":\"61454376BEE947C48316737FCD7099DD\",\"端口\":\"6006\",\"密码\":\"RSTH1XCI96716\",\"服务器ip\":\"113.140.71.252\",\"tmsId\":\"45b730fe344940e68997559d6881f6dc\",\"用户名\":\"盛世腾辉3\",\"siteId\":\"46738887e2534e21a6100af6881cc8f6\"}";




		// 使用Base64编码字符串
//		System.out.println("Original String: " + originalString);
//		System.out.println("Encoded String: " + Base64Util.toKRpaBase64(originalString));


//		rpaExecuter.callRpaComponent(script, RpaUtil.parseRpaParams(params), agentIp);
//		for(int i = 0;i<1;i++)
//			rpaExecuter.addRpaProcess("name","TS任务调度器流程\\API测试流程",Base64Util.toKRpaBase64(originalString),agentIp,4);

//		Map<Integer, String> KAgentInvMap = new HashMap<Integer, String>();
//		List<KAgentBean> kAgentBeanList = new ArrayList<>();
//
//		List<RpaRequestBean> rpaRequestBeanList = rpaExecuter.getSXFAgentFlowQuery();
//		System.out.println("length:" + rpaRequestBeanList.size());
//		for(RpaRequestBean rpaRequestBean : rpaRequestBeanList) {
//			if("k_agent_object".equals(rpaRequestBean.getName())) {
//				List<List<Map<String,String>>> KAgentList = (List<List<Map<String, String>>>) rpaRequestBean.getValue();
//				for(int i = 0 ;i<KAgentList.size();i++) {
//					if(i==0){
////						System.out.println("1");
//						for(int j = 0 ;j<KAgentList.get(i).size();j++) {
////							System.out.println("put j :" + j);
//							Map<String,String> KAgentMap = KAgentList.get(i).get(j);
//							KAgentMap.get("Name");
//							String name = KAgentMap.get("Name");
//							if(!"".equals(name) && !name.isEmpty()){
////								System.out.println("name:" + name);
//								KAgentInvMap.put( j,name);
//							}
//						}
//					}else{
//						Map<String,Object> beanParamMap = new HashMap<>();
//						String[] beanList = new String[KAgentInvMap.size()];
//						for(int j = 0 ;j<KAgentList.get(i).size();j++) {
//							beanParamMap.put(KAgentInvMap.get(j),KAgentList.get(i).get(j).get("Value"));
//						}
//						KAgentBean kAgentBean = new KAgentBean(beanParamMap);
//						kAgentBeanList.add(kAgentBean);
////						System.out.println(kAgentBean.toString());
//					}
//				}
//				break;
//			}
//		}
//		System.out.println("kAgentBeanList :" + kAgentBeanList.size());
//		ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(new PascalCaseNamingStrategy());
//		try {
//			rpaRequestBeanList = objectMapper.readValue(json, new TypeReference<List<RpaRequestBean>>() {
//			});
//		} catch (JsonProcessingException e) {
//			LOGGER.error("Parse Rpa response to json fail :{}", json);
//		}

//		RpaUtil.getRpaResAgentList(rpaRequestBeanList);

	}
}
