package com.bob.ktssts;

import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.RpaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@SpringBootTest
class KtsstsApplicationTests {

	@Autowired
	public RpaExecuter rpaExecuter;

	@Test
	void contextLoads() {

		List<String> params = new ArrayList<>();
		params.add("111");
		params.add("222");
		String script = "测试API组件调用";
		String agentIp = "10.1.20.7,10.1.20.8,10.1.20.47";

		String originalString = "{\"rpa_task_ID\":\"61454376BEE947C48316737FCD7099DD\",\"端口\":\"6006\",\"密码\":\"RSTH1XCI96716\",\"服务器ip\":\"113.140.71.252\",\"tmsId\":\"45b730fe344940e68997559d6881f6dc\",\"用户名\":\"盛世腾辉3\",\"siteId\":\"46738887e2534e21a6100af6881cc8f6\"}";

		// 使用Base64编码字符串

		System.out.println("Original String: " + originalString);
		System.out.println("Encoded String: " + Base64Util.toKRpaBase64(originalString));


//		rpaExecuter.callRpaComponent(script, RpaUtil.parseRpaParams(params), agentIp);
		for(int i = 0;i<1;i++)
			rpaExecuter.addRpaProcess("name","TS任务调度器流程\\API测试流程",Base64Util.toKRpaBase64(originalString),agentIp,4);

	}
}
