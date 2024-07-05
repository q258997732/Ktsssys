package com.bob.ktssts.schedule;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class RpaScheduleTask {

	final Logger LOGGER = LogManager.getLogger(DefaultScheduleTask.class);

	private final int corePoolSize = 10;
	private final int maxPoolSize = corePoolSize * 5;

	// 设置核心线程池数量
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(corePoolSize);

	// 假设这是你的定时任务方法
	public void performTask() {
		// 任务逻辑
		String a;
	}

	// 启动定时任务的方法
	public void startScheduledTask(long initialDelay, long delay) {
		scheduler.setMaximumPoolSize(maxPoolSize);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				LOGGER.info("plan A");
				// 重新调度自己，使用局部变量的延迟时间
				scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
			}
		};
		// 首次执行任务，使用初始延迟
		scheduler.schedule(task, initialDelay, TimeUnit.MILLISECONDS);
	}

	public void startScheduledTask2(long initialDelay, long delay) {
		scheduler.setMaximumPoolSize(maxPoolSize);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				LOGGER.info("plan B");
				// 重新调度自己，使用局部变量的延迟时间
				scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
			}
		};
		// 首次执行任务，使用初始延迟
		scheduler.schedule(task, initialDelay, TimeUnit.MILLISECONDS);
	}

	// 停止定时任务的方法
	public void stopScheduledTask() {
		scheduler.shutdownNow();
	}


}
