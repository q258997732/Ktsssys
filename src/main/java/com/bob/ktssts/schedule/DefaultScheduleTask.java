package com.bob.ktssts.schedule;

import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DefaultScheduleTask {

	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;

	static Logger LOGGER = LogManager.getLogger(DefaultScheduleTask.class);

	@Value("${schedule.sync.KAgent}")
	private long syncKAgent;
	@Value("${rpa.fixedDelay}")
	private long kAgentDelay;
	@Value("${rpa.queue.limit}")
	private int queueLimit;


	/* 执行任务 */
	@Scheduled(fixedDelayString = "${schedule.sync.KAgent}")
	public void syncKAgentStatus(){
//		LOGGER.info("开始执行K-Agent同步任务");
//		if(tsExecuterService.syncKRpaAgent())
//			LOGGER.info("K-Agent同步任务执行成功");
//		else
//			LOGGER.info("K-Agent同步任务执行失败");
	}

}
