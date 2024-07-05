package com.bob.ktssts;

import com.bob.ktssts.entity.*;
import com.bob.ktssts.mapper.TsApiuserMapper;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.mapper.TsTaskMapper;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskImpl;
import com.bob.ktssts.service.TsTaskService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
	@Autowired
	private TsTaskImpl tsTaskImpl;

	@Test
	void contextLoads() throws JsonProcessingException {

//		int res = tsTaskService.syncEventSolveTask();
//		LOGGER.info("res:{}",res);
//		KRpaFlowDataBean kRpaFlowDataBean = new KRpaFlowDataBean();
//		kRpaFlowDataBean.setId("1");
//		kRpaFlowDataBean.setIp("192.168.1.1");
//		kRpaFlowDataBean.setPort("5002");
//		kRpaFlowDataBean.setTmsId("1");
//		kRpaFlowDataBean.setSiteId("1");
//		kRpaFlowDataBean.setTmsName("1");
//		kRpaFlowDataBean.setUserName("1");
//		kRpaFlowDataBean.setPassword("1");
//		LOGGER.info("String:{}" ,kRpaFlowDataBean.toString());
//		LOGGER.info("Base64String:{}" , Base64Util.String2Base64(kRpaFlowDataBean.toString()));




//		int res = tsExecuterService.cleanKRpaAgent();
//		LOGGER.info("res:{}",res);
//		res = tsTaskService.deleteAutoRpaTask();
//		LOGGER.info("res:{}",res);
//		if(tsExecuterService.syncKRpaAgent())
//			LOGGER.info("同步完成");
//		res = tsTaskService.syncEventSolveTask();
//		LOGGER.info("res:{}",res);
//		res = tsTaskService.distributeKRpaTask(tsTaskMapper.selectAllTsTask());
//		LOGGER.info("res:{}",res);



//		TsTask tsTask = new TsTask();
//		tsTask.setId("1");
//		tsTask.setTaskName("test");
//		tsTask.setTaskDesc("test");
//		tsTask.setExecType("K-RPA");
//		tsTask.setExecParam("{\"flowType\":\"1\",\"flow\":\"1\",\"data\":\"1\"}");
//		tsTask.setExecTimeout(10);
//		tsTask.setExecRetry(1);
//		tsTask.setExecMonopoly("1");
//		tsTask.setExecAppoint("1");
//		tsTask.setScheduleType("1");
//		tsTask.setScheduleConf("1");
//		tsTask.setClone("1");
//		tsTask.setAddBy("1");
//		tsTask.setAddTime(new Date());
//		tsTask.setUpdateBy("1");
//		tsTask.setUpdateTime(new Date());
//		tsTask.setAvailable("1");
//		int res = tsTaskMapper.insert(tsTask);
//		LOGGER.info("res:{}",res);

//		List<TsTask> tsTaskList = tsTaskMapper.selectTsTaskByExecType("K-RPA");
//		int res = tsTaskService.distributeKRpaTask(tsTaskList);
//		LOGGER.info("res:{}",res);
	}
}
