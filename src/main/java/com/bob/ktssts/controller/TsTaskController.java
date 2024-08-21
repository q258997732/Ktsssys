package com.bob.ktssts.controller;


import com.bob.ktssts.entity.resopnse.ErrorResponseBean;
import com.bob.ktssts.entity.resopnse.ResponseBean;
import com.bob.ktssts.entity.resopnse.SuccessResponseBean;
import com.bob.ktssts.schedule.RpaScheduleTask;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class TsTaskController {

	private static final Logger LOGGER = LogManager.getLogger(TsTaskController.class);

	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;
	@Resource
	RpaScheduleTask rpaScheduleTask;
	@Resource
	RpaExecuter rpaExecuter;

	@PostConstruct
	public void init() {
		// 初始化数据库状态
//		resetAutoRpaTask();
	}

	/**
	 * 初始化TS模块
	 * @return 执行结果ResponseBean
	 */
	@PostMapping("/resetAutoRpaTask")
	public ResponseEntity<ResponseBean<Object>> resetAutoRpaTask() {

		// 清除RPA任务
		if (tsExecuterService.cleanKRpaAgent() >= 0) {
			LOGGER.info("清除RPA任务成功");
		} else {
			LOGGER.warn("清除RPA任务失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("清除RPA任务失败"));
		}

		// 清理自动生成的RPA任务
		if (tsTaskService.deleteAutoRpaTask() >= 0) {
			LOGGER.info("清理自动生成的RPA任务成功");
		} else {
			LOGGER.info("清理自动生成的RPA任务失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("清理自动生成的RPA任务失败"));
		}

		// 自动同步RPA Server Agent
		if (tsExecuterService.syncKRpaAgent()) {
			LOGGER.info("自动同步K-RPA Agent成功");
		} else {
			LOGGER.info("自动同步任务失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("自动同步任务失败"));
		}

		// 自动同步事件解决任务-XCI
		if (tsTaskService.syncXciEventSolveTask() >= 0) {
			LOGGER.info("自动同步事件解决任务-XCI成功");
		}else{
			LOGGER.info("自动同步事件解决任务-XCI失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("自动同步事件解决任务-XCI失败"));
		}

		// 给任务分发执行器
		if(tsTaskService.distributeKRpaTask(tsTaskService.getAllTsTask()) >= 0){
			LOGGER.info("任务分发执行器成功");
		}else {
			LOGGER.info("任务分发执行器失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("任务分发执行器失败"));
		}

		// 刷新任务清单
		LOGGER.info("刷新任务清单");
		tsTaskService.refreshKRpaAgentThreadList();

		return ResponseEntity.ok().body(new SuccessResponseBean<>("初始化TS模块成功"));

	}

	@PostMapping("/startXciTask")
	public ResponseEntity<ResponseBean<Object>> startXciTask() {
		try {
			rpaScheduleTask.startTsTaskExecuter("XCI系统_报警信息处理", 1000, 5000);
		}catch(Exception e){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "启动XCI任务失败",e.getMessage()));
		}
		return ResponseEntity.ok().body(new SuccessResponseBean<>("启动XCI任务成功"));
	}

	@PostMapping("/stopXciTask")
	public ResponseEntity<ResponseBean<Object>> stopXciTask() {
		try {
			rpaScheduleTask.stopScheduledTask();
		}catch(Exception e){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "停止XCI任务失败",Arrays.toString(e.getStackTrace())));
		}
		return ResponseEntity.ok().body(new SuccessResponseBean<>("停止XCI任务成功"));
	}

	@PostMapping("/addRpaDataProcess")
	public ResponseEntity<ResponseBean<Object>> addRpaDataProcess(@RequestBody JsonNode jsonNode) {
		String flowName;
		String agentIp;
		String params;
		try {
			flowName = jsonNode.get("flowName").asText();
			agentIp = jsonNode.get("agentIp").asText();
			JsonNode paramNode = jsonNode.get("params");
			params = paramNode.toString();
		}catch(Exception e){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "参数错误", e.getStackTrace()));
		}
		// String flowType, String flow, String data, String agentIp, int level
		if(rpaExecuter.addRpaDataProcess("name",flowName, Base64Util.String2Base64(params), agentIp, 4))
			return ResponseEntity.ok().body(new SuccessResponseBean<>("添加RPA数据处理成功"));
		return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "添加RPA数据处理失败", null));
	}

}
