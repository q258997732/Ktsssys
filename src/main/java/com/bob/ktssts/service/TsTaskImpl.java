package com.bob.ktssts.service;

import com.bob.ktssts.entity.TmsTaskBean;
import com.bob.ktssts.entity.TsExecuter;
import com.bob.ktssts.entity.TsTask;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.mapper.TsTaskMapper;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.TaskUtil;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TsTaskImpl implements TsTaskService {

	final static String ADD_BY = "ktssts";

	private static final Logger LOGGER = LogManager.getLogger(TsTaskImpl.class);

	@Resource
	TsTaskMapper tsTaskMapper;

	@Resource
	private TsExecuterService tsExecuterService;
	@Autowired
	private TsExecuterMapper tsExecuterMapper;

	@Override
	public int syncEventSolveTask() {
		List<TmsTaskBean> ktssTmsTask = tsTaskMapper.selectAllKtssTmsTask();
		List<TsTask> tsTaskList = tsTaskMapper.selectAllTsTask();
//		LOGGER.info("tsTaskList size:{}", tsTaskList.size());
		int effectCount = 0;


		for (TmsTaskBean tmsTaskBean : ktssTmsTask) {
			boolean isFind = false;
			for (TsTask tsTask : tsTaskList) {
				if (TaskUtil.compareTsTaskTmsTask(tsTask, tmsTaskBean) && !"1".equals(tsTask.getExecMonopoly())) {
//					tsTask.setUpdateTime(new Date());
//					tsTask.setTaskName("K-TSS 自动处理事件");
//					tsTask.setTaskDesc("K-TSS 自动处理事件:"+tmsTaskBean.getUsername());
//					tsTask.setScheduleType("CYCLE");
//					tsTask.setScheduleConf("10");
//					tsTask.setExecParam(tmsTaskBean.toKRpaString());
//					tsTask.setExecTimeout(1800);
//					tsTask.setExecRetry(0);
//					tsTask.setExecType("K-RPA");
//					tsTask.setExecMonopoly("0");
//					tsTask.setExecAppoint("");
//					int res = tsTaskMapper.updateByPrimaryKey(tsTask);
//					LOGGER.info("update tsTask:{}",res);
					isFind = true;
//					if (res > 0 )
//						effectCount ++;
					break;
				}
			}
			if (!isFind) {
				TsTask tsTask = new TsTask();
				tsTask.setId(UUID.randomUUID().toString().replace("-", ""));
				tsTask.setAddBy(ADD_BY);
				tsTask.setAddTime(new Date());
				tsTask.setUpdateTime(new Date());
				tsTask.setUpdateBy(ADD_BY);
				tsTask.setTaskName("K-TSS 自动处理事件");
				tsTask.setTaskDesc("K-TSS 自动处理事件:" + tmsTaskBean.getUsername());
				tsTask.setScheduleType("CYCLE");
				tsTask.setScheduleConf("10");
				tsTask.setExecParam(tmsTaskBean.toKRpaString());
				tsTask.setExecTimeout(1800);
				tsTask.setExecRetry(0);
				tsTask.setExecType("K-RPA");
				tsTask.setClone("1");
				tsTask.setExecMonopoly("0");
				tsTask.setExecAppoint("");
				tsTask.setAvailable("1");
				int res = tsTaskMapper.insert(tsTask);
//				LOGGER.info("insert tsTask:{}",res);
				if (res > 0)
					effectCount++;

			}
		}
		return effectCount;
	}

	/* 分发任务至执行器 */
	public int distributeKRpaTask(List<TsTask> tsTaskList) {

		int effectCount = 0;
		for (TsTask tsTask : tsTaskList) {
			if (TaskUtil.isDistributeRpaTask(tsTask)) {
				// 创建插入的Executer_task的基本map
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", UUID.randomUUID().toString().replace("-", ""));
				map.put("updateBy", "ktssts");
				map.put("updateTime", new Date());
				map.put("taskId", tsTask.getId());
				map.put("execId", "");
				map.put("priority", "9");
				map.put("available", "1");

				// 检查是否已经分配任务
				int count = tsTaskMapper.countExecTask(tsTask.getId());
				if (count <= 0 || (!"".equals(tsTask.getExecAppoint()) && tsTask.getExecAppoint() != null)) {
					// 判断是否有指定的IP,有指定IP则直接写入指定IP,无指定则获取一个空闲的执行器
					String appoint = tsTask.getExecAppoint();
					if (!"".equals(appoint) && appoint != null) {
						// 若Task指定了执行器IP，将Executer_Task表中的相关记录删除
						tsTaskMapper.clearExecTaskByTaskId(tsTask.getId());
						String[] appointList = appoint.split(",");
						if (appointList.length >= 1) {
							for (String ip : appointList) {
								// 根据类型获取Executer的ID
								LOGGER.info("给 {} 任务分配指定的执行器: {}", tsTask.getTaskDesc(),ip);
								String execId = tsExecuterMapper.getExecuterId("K-RPA", ip);
								if("".equals(execId) || execId == null) {
									LOGGER.warn("没有找到K-RPA的执行器：{}",ip);
									continue;
								}

								map.put("id", UUID.randomUUID().toString().replace("-", ""));
								map.put("execId", execId);
								if (tsTaskMapper.insertExecuterTask(map) > 0) {
									effectCount++;
								}
							}
						}
					} else {
						// 获取一个空闲的执行器
						List<TsExecuter> executerList = tsExecuterService.getFreeExecuter("K-RPA", 1);
						if (executerList.isEmpty()) {
							LOGGER.warn("没有了空闲K-RPA的执行器！");
							return effectCount;
						}
						LOGGER.info("给 {} 任务分配空闲的执行器: {}", tsTask.getTaskDesc(),executerList.get(0).getExec_addr());
						map.put("execId", executerList.get(0).getId());
						// 将tsTask写入ts_executer_task
						effectCount += tsTaskMapper.insertExecuterTask(map);
					}

				} else {
					LOGGER.info("{} 已经分配执行器，不自动同步", tsTask.getTaskDesc());
				}
			}
		}
		return effectCount;
	}


}
