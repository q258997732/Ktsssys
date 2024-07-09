package com.bob.ktssts.schedule;


import com.bob.ktssts.entity.KFlowBean;
import com.bob.ktssts.mapper.TsTaskMapper;
import com.bob.ktssts.service.TsTaskService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.TaskUtil;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class RpaScheduleTask {

	@Resource
	TsTaskService tsTaskService;
	@Resource
	RpaExecuter rpaExecuter;
	@Resource
	TsTaskMapper tsTaskMapper;

	final Logger LOGGER = LogManager.getLogger(DefaultScheduleTask.class);

	private final int corePoolSize = 10;
	private final int maxPoolSize = corePoolSize * 5;
	private final int maxDataQueueLimit = 300;
	private final Map<String,Integer> taskCount = new HashMap<String,Integer>();

	// 设置核心线程池数量
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(corePoolSize);

	// 启动定时调度默认任务
	public void startTsTaskExecuter(String flowName,long initialDelay, long delay) {
//		List<KFlowBean> kFlowBeanList = rpaExecuter.getKRpaFlowList();
		String flowID = tsTaskService.getKRpaTaskIdByFlowName(flowName);
		if (flowID == null) {
			LOGGER.error("没有找到K-RPA流程：{}", flowName);
		}
		Runnable task = new Runnable() {
			@Override
			public void run() {
				List<Map<String, Object>> tsExecuterTaskList = tsTaskMapper.selectKRpaExecutedTsTaskByFlowName(flowName);

				LOGGER.info("开始分发任务:{}",flowName);
				for (Map<String, Object> map : tsExecuterTaskList) {
					LOGGER.info(taskCount);
					String execAddr = (String) map.get("exec_addr");
					String taskName = (String) map.get("task_name");
					String execParam = (String) map.get("exec_param");
					int count = -1;
					if(taskCount.containsKey(execAddr)){
						count = taskCount.get(execAddr);
						LOGGER.info("1-execAddr:{} count:{}",execAddr,count);
						taskCount.put(execAddr, count+1);
					}else{
						LOGGER.info("2-execAddr:{} count:{}",execAddr,count);
						taskCount.put(execAddr, 1);
						count = 1;
						LOGGER.info(taskCount);
					}
					if(count <= maxDataQueueLimit && count != -1) {
						String base64string = Base64Util.String2Base64(TaskUtil.param2json(execParam));
						if (rpaExecuter.addRpaDataProcess("flowID", flowID, base64string, execAddr, 5)) {
							LOGGER.info("分发任务:{} {} 成功", execAddr,taskName);
						} else {
							LOGGER.error("分发任务:{} {} 失败", execAddr,taskName);
						}
					}else{
						LOGGER.info("分发任务:{} {} 超出最大限制",execAddr,taskName);
					}
				}
				LOGGER.info("完成分发任务:{}", flowName);

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
