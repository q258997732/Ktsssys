package com.bob.ktssts;

import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.RpaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KtsstsApplicationTests {

	@Autowired
	public RpaExecuter rpaExecuter;

	@Test
	void contextLoads() {

		List<String> params = new ArrayList<>();
		params.add("test1");
		params.add("test2");
		String script = "测试API组件调用";
		String agentIp = "10.1.20.7";

		rpaExecuter.callRpaComponent(script, RpaUtil.parseRpaParams(params), agentIp);
	}

}
