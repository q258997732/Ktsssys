package com.bob.ktssts.schedule;


import com.bob.ktssts.entity.ktss.ExecStateEnum;
import com.bob.ktssts.entity.ktss.KAgentThreadBean;
import com.bob.ktssts.entity.ktss.TsTaskExecutionLog;
import com.bob.ktssts.exception.RpaFlowNotFind;
import com.bob.ktssts.mapper.ktss.TsTaskMapper;
import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.RpaUtil;
import com.bob.ktssts.util.TaskUtil;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(name = "ktss.enable", havingValue = "true")
public class RpaScheduleTask {

	@Resource
	TsExecuterService tsExecuterService;
	@Resource
	TsTaskService tsTaskService;
	@Resource
	RpaExecuter rpaExecuter;
	@Resource
	TsTaskMapper tsTaskMapper;

	final Logger LOGGER = LogManager.getLogger(RpaScheduleTask.class);

	private final int corePoolSize = 50;
	private final int maxPoolSize = corePoolSize * 5;
	private final int maxDataQueueLimit = 1;

	// 设置核心线程池数量
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(corePoolSize);

	// 启动定时调度默认任务
	public void startTsTaskExecuter(String flowName,long initialDelay, long delay) throws Exception {
		String flowID = tsTaskService.getKRpaTaskIdByFlowName(flowName);
		if (flowID == null) {
			LOGGER.error("没有找到K-RPA流程：{}", flowName);
			throw new RpaFlowNotFind("没有找到K-RPA流程：" + flowName);
		}
		Runnable task = new Runnable() {
			@Override
			public void run() {
				scheduler.setMaximumPoolSize(maxPoolSize);
				// 首次运行初始化线程组
				rpaExecuter.refreshKRpaAgentThreadList();
				List<Map<String, Object>> tsExecuterTaskList = tsTaskMapper.selectKRpaExecutedTsTaskByFlowName(flowName);
				Map<String,KAgentThreadBean> kAgentThreadBeanList = RpaExecuter.getFkAgentThread();
				LOGGER.info("开始分发任务:{}",flowName);
				for (Map<String, Object> map : tsExecuterTaskList) {
					String execAddr = (String) map.get("exec_addr");
					String taskName = (String) map.get("task_name");
					String execParam = (String) map.get("exec_param");
					String id = (String) map.get("exec_id");
					String taskid = (String) map.get("task_id");
					LOGGER.debug("获取的ID{}",kAgentThreadBeanList.get(id));
					if(RpaUtil.kRpaIsAboveMaxThread(id,kAgentThreadBeanList,maxDataQueueLimit)) {
						String sUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
						String base64string = Base64Util.String2Base64(TaskUtil.xciParam2json(execParam,sUuid));
						if (rpaExecuter.addRpaDataProcess("flowID", flowID, base64string, execAddr, 5)) {
							if(!tsTaskService.insertTaskLog(sUuid,taskid,id)){
								LOGGER.warn("插入执行日志失败:{}",taskid);
							}
							LOGGER.info("分发任务:{} {} 成功", execAddr, taskName);
						} else {
							tsTaskService.replaceIntoTaskExecutionLog(new TsTaskExecutionLog(sUuid, taskid, ExecStateEnum.DIST_FAIL.getDescription(),"",id));
							LOGGER.error("分发任务:{} {} 失败", execAddr, taskName);
						}
					}else{
						LOGGER.debug("分发任务:{} {} 失败,线程数超过限制", execAddr, taskName);
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

	// 启动定时同步K-Agent线程状态
	public void syncKAgentThreadTask(long initialDelay, long delay) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				scheduler.setMaximumPoolSize(maxPoolSize);
				LOGGER.info("定时同步K-RPA执行器线程状态");
				rpaExecuter.refreshKRpaAgentThreadList();
				scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
			}
		};
		scheduler.schedule(task, initialDelay, TimeUnit.MILLISECONDS);
	}

	// 停止定时任务的方法
	public void stopScheduledTask() {
		if(!scheduler.isShutdown())
			scheduler.shutdown();
	}


}
