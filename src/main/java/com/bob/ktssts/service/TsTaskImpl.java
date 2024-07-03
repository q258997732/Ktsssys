package com.bob.ktssts.service;

import com.bob.ktssts.entity.TmsTaskBean;
import com.bob.ktssts.entity.TsTask;
import com.bob.ktssts.mapper.TsTaskMapper;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.TaskUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TsTaskImpl implements TsTaskService{

	private static final Logger LOGGER = LogManager.getLogger(TsTaskImpl.class);

	final static String ADD_BY = "ktssts";

	@Autowired
	TsTaskMapper tsTaskMapper;

	@Override
	public int syncEventSolveTask() {
		List<TmsTaskBean> ktssTmsTask = tsTaskMapper.selectAllKtssTmsTask();
		List<TsTask> tsTaskList = tsTaskMapper.selectAllTsTask();
//		LOGGER.info("tsTaskList size:{}", tsTaskList.size());
		int effectCount = 0;


		for (TmsTaskBean tmsTaskBean : ktssTmsTask) {
			boolean isFind = false;
			for (TsTask tsTask : tsTaskList) {
				if(TaskUtil.compareTsTaskTmsTask(tsTask, tmsTaskBean) && !"1".equals(tsTask.getExecMonopoly())){
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
			if(!isFind){
				TsTask tsTask = new TsTask();
				tsTask.setId(UUID.randomUUID().toString().replace("-",""));
				tsTask.setAddBy(ADD_BY);
				tsTask.setAddTime(new Date());
				tsTask.setUpdateTime(new Date());
				tsTask.setUpdateBy(ADD_BY);
				tsTask.setTaskName("K-TSS 自动处理事件");
				tsTask.setTaskDesc("K-TSS 自动处理事件:"+tmsTaskBean.getUsername());
				tsTask.setScheduleType("CYCLE");
				tsTask.setScheduleConf("10");
				tsTask.setExecParam(tmsTaskBean.toKRpaString());
				tsTask.setExecTimeout(1800);
				tsTask.setExecRetry(0);
				tsTask.setExecType("K-RPA");
				tsTask.setClone("1");
				tsTask.setExecMonopoly("0");
				tsTask.setExecAppoint("");
				int res = tsTaskMapper.insert(tsTask);
//				LOGGER.info("insert tsTask:{}",res);
				if(res >0)
					effectCount++;

			}
		}
		return effectCount;
	}

	/* 分发任务至执行器 */
	public int distributeKRpaTask(List<TsTask> tsTaskList) {

		int effectCount = 0;
		for (TsTask tsTask : tsTaskList) {
			if(TaskUtil.isDistributeRpaTask(tsTask)){
				int count = tsTaskMapper.countExecTask(tsTask.getId());
				if(count == 0){
					// 将tsTask写入ts_executer_task
					effectCount += tsTaskMapper.insert(tsTask);
				}
			}
		}
		return effectCount;
	}


}
