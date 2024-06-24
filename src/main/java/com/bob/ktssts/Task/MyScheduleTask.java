package com.bob.ktssts.Task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyScheduleTask {



	static Logger LOGGER = LogManager.getLogger(MyScheduleTask.class);

	@Value("${test.delay}")
	private long delay;

//	@Scheduled(fixedDelayString  = "${test.delay}")
//	public void test(){
//		LOGGER.info("MyScheduleTask test");
//	}
}
