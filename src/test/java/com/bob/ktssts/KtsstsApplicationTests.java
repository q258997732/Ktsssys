package com.bob.ktssts;

import com.bob.ktssts.entity.*;
import com.bob.ktssts.mapper.TsApiuserMapper;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.mapper.TsTaskMapper;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
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

	@Autowired
	TsExecuterService tsExecuterService;

	@Autowired
	TsTaskMapper tsTaskMapper;
	@Autowired
	private TsTaskService tsTaskService;

	@Test
	void contextLoads() throws JsonProcessingException {

		int res = tsTaskService.syncEventSolveTask();
		LOGGER.info("res:{}",res);

	}
}
