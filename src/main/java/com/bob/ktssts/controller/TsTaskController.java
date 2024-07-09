package com.bob.ktssts.controller;

import com.bob.ktssts.entity.ResponseBean;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TsTaskController {

	private static final Logger LOGGER = LogManager.getLogger(TsTaskController.class);

	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;

	/**
	 * 初始化TS模块
	 * @return 执行结果ResponseBean
	 */
	@PostMapping("/resetAutoRpaTask")
	public ResponseBean resetAutoRpaTask() {

		// 清除RPA任务
		if (tsExecuterService.cleanKRpaAgent() >= 0) {
			LOGGER.info("清除RPA任务成功");
		} else {
			LOGGER.info("清除RPA任务失败");
			return new ResponseBean(ResponseBean.ECode.SERVER_ERROR.getCode(), "清除RPA任务失败", null);
		}

		// 清理自动生成的RPA任务
		if (tsTaskService.deleteAutoRpaTask() >= 0) {
			LOGGER.info("清理自动生成的RPA任务成功");
		} else {
			LOGGER.info("清理自动生成的RPA任务失败");
			return new ResponseBean(ResponseBean.ECode.SERVER_ERROR.getCode(), "清理自动生成的RPA任务失败", null);
		}

		// 自动同步RPA Server Agent
		if (tsExecuterService.syncKRpaAgent()) {
			LOGGER.info("自动同步K-RPA Agent成功");
		} else {
			LOGGER.info("自动同步任务失败");
			return new ResponseBean(ResponseBean.ECode.SERVER_ERROR.getCode(), "自动同步K-RPA Agent失败", null);
		}

		// 自动同步事件解决任务-XCI
		if (tsTaskService.syncEventSolveTask() >= 0) {
			LOGGER.info("自动同步事件解决任务-XCI成功");
		}else{
			LOGGER.info("自动同步事件解决任务-XCI失败");
			return new ResponseBean(ResponseBean.ECode.SERVER_ERROR.getCode(), "自动同步事件解决任务-XCI失败", null);
		}

		// 给任务分发执行器
		if(tsTaskService.distributeKRpaTask(tsTaskService.getAllTsTask()) >= 0){
			LOGGER.info("任务分发执行器成功");
		}else {
			LOGGER.info("任务分发执行器失败");
			return new ResponseBean(ResponseBean.ECode.SERVER_ERROR.getCode(), "任务分发执行器失败", null);
		}
		return new ResponseBean(ResponseBean.ECode.SUCCESS.getCode(), "初始化TS模块成功", null);

	}

}
