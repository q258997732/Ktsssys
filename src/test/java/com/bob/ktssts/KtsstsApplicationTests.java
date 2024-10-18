package com.bob.ktssts;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KtsstsApplicationTests {

	private static final Logger LOGGER = LogManager.getLogger(KtsstsApplicationTests.class);

	@Test
	void contextLoads() throws JsonProcessingException {

//		String res = tsTaskService.getKRpaTaskIdByFlowName("XCI系统_报警信息处理");
//		LOGGER.info("res:{}",res);

//		List<SealfixOaMapping> res = czSealFixService.getStatusByTradeNo("RPA-202408231001");
//		LOGGER.info("res:{}",res.size());
//		for (SealfixOaMapping sealfixOaMapping : res) {
//			LOGGER.info(sealfixOaMapping.toString());
//		}



//		List<Map<String,Object>> res = tsTaskMapper.selectKRpaExecutedTsTask();
//		LOGGER.info("res:{}",res.size());
//		rpaScheduleTask.startTsTaskExecuter("XCI系统_报警信息处理",1000,3000);
		// String id,String taskId,Date startTime,String status
//		TsTaskExecutionLog tsTaskExecutionLog = new TsTaskExecutionLog(UUID.randomUUID().toString().replace("-",""),"test",new Date(),"执行中");
//		tsTaskExecutionLogMapper.insert(tsTaskExecutionLog);

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
