package com.bob.ktssts.controller;


import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.schedule.RpaScheduleTask;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@ConditionalOnProperty(name = "ktss.enable", havingValue = "true")
public class TsTaskController {

	private static final Logger LOGGER = LogManager.getLogger(TsTaskController.class);

	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;
	@Resource
	RpaScheduleTask rpaScheduleTask;

	@PostConstruct
	public void init() {
		// 初始化数据库状态
		resetAutoRpaTask();
	}

	/**
	 * 初始化TS模块
	 *
	 * @return 执行结果ResponseBean
	 */
	@PostMapping("/resetAutoRpaTask")
	public ResponseEntity<ResponseBean<Object>> resetAutoRpaTask() {

		// 清除自动同步的KTSS执行器
		if (tsExecuterService.cleanKRpaAgent() >= 0) {
			LOGGER.info("清除自动生成的执行器成功");
		} else {
			LOGGER.warn("清除RPA任务失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("清除自动生成的执行器失败"));
		}

		// 清理自动生成的RPA任务
//		if (tsTaskService.deleteAutoRpaTask() >= 0) {
//			LOGGER.info("清理自动生成的RPA任务成功");
//		} else {
//			LOGGER.info("清理自动生成的RPA任务失败");
//			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("清理自动生成的RPA任务失败"));
//		}

		// 自动同步RPA Server Agent
		if (tsExecuterService.syncKRpaAgent()) {
			LOGGER.info("自动同步K-RPA Agent成功");
		} else {
			LOGGER.info("自动同步K-RPA Agent失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("自动同步任务失败"));
		}

		// 自动同步事件解决任务-XCI
		if (tsTaskService.syncXciEventSolveTask() >= 0) {
			LOGGER.info("自动同步事件解决任务-XCI成功");
		} else {
			LOGGER.info("自动同步事件解决任务-XCI失败");
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("自动同步事件解决任务-XCI失败"));
		}

		// 给任务分发执行器
		if (tsTaskService.distributeKRpaTask(tsTaskService.getAllTsTask()) >= 0) {
			LOGGER.info("任务分发执行器成功");
		} else {
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
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "启动XCI任务失败", e.getMessage()));
		}
		return ResponseEntity.ok().body(new SuccessResponseBean<>("启动XCI任务成功"));
	}

	@PostMapping("/stopXciTask")
	public ResponseEntity<ResponseBean<Object>> stopXciTask() {
		try {
			rpaScheduleTask.stopScheduledTask();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "停止XCI任务失败", Arrays.toString(e.getStackTrace())));
		}
		return ResponseEntity.ok().body(new SuccessResponseBean<>("停止XCI任务成功"));
	}

	@PostMapping("/updateAllXciUserStatus")
	public ResponseEntity<ResponseBean<Object>> updateTmsUserStatus() {
		try {
			int syncXciTaskCount = tsTaskService.syncXciEventSolveTask();
			int distributeXciTaskCount = tsTaskService.distributeKRpaTask(tsTaskService.getAllTsTask());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "更新所有XCI账号执行状态失败", Arrays.toString(e.getStackTrace())));
		}
		return ResponseEntity.ok().body(new SuccessResponseBean<>("更新所有XCI账号执行状态成功"));
	}

	@PostMapping("/updateTaskExecLog")
	public ResponseEntity<ResponseBean<Object>> updateTaskExecLog(@RequestBody JsonNode jsonNode) {
		try {
			String id = jsonNode.get("id").asText();
			String endTime = jsonNode.get("endTime").asText();
			String status = jsonNode.get("status").asText();
			String data = jsonNode.get("data").asText();
			String errorMessage = jsonNode.get("errorMessage").asText();
			if(!tsTaskService.updateTaskExecLog(id,endTime,status,data,errorMessage))
				return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "更新任务执行日志失败:"+id,null));
			else
				return ResponseEntity.ok(new SuccessResponseBean<>("更新任务执行日志成功:"+id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "更新任务执行日志失败", Arrays.toString(e.getStackTrace())));
		}

	}

}
