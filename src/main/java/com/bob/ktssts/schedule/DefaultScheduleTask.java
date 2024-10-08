package com.bob.ktssts.schedule;

import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "ktss.enable", havingValue = "true")
public class DefaultScheduleTask {

	static Logger LOGGER = LogManager.getLogger(DefaultScheduleTask.class);
	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;
	@Value("${schedule.sync.KAgent}")
	private long syncKAgent;
	@Value("${rpa.fixedDelay}")
	private long kAgentDelay;
	@Value("${rpa.queue.limit}")
	private int queueLimit;
	@Value("${defaultScheduleEnable}")
	private boolean defaultScheduleEnable;


	/* 执行任务 */
	@Scheduled(fixedDelayString = "${schedule.sync.KAgent}")
	public void syncKAgentStatus() {
//		LOGGER.info("开始执行K-Agent同步任务");
//		if(tsExecuterService.syncKRpaAgent())
//			LOGGER.info("K-Agent同步任务执行成功");
//		else
//			LOGGER.info("K-Agent同步任务执行失败");
	}

	/* 定时同步Agent状态 */
	@Scheduled(fixedDelayString = "${schedule.sync.KagentStatus}")
	public void syncAgentStatus() {
//		LOGGER.info("开始执行Agent状态同步任务");
		if(defaultScheduleEnable) {
			if (tsExecuterService.syncKRpaAgent())
				LOGGER.info("Agent状态同步任务执行成功");
			else
				LOGGER.info("Agent状态同步任务执行失败");
		}
	}
}

