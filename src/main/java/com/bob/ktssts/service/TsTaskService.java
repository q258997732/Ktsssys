package com.bob.ktssts.service;

import com.bob.ktssts.entity.ktss.TsTask;
import com.bob.ktssts.entity.ktss.TsTaskExecutionLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TsTaskService {

	public int syncXciEventSolveTask();

	public int distributeKRpaTask(List<TsTask> tsTaskList);

	public int deleteAutoRpaTask();

	public List<TsTask> getAllTsTask();

	public String getKRpaTaskIdByFlowName(String flowName);

	void refreshKRpaAgentThreadList();

	public boolean insertTaskLog(String taskId,String executerId);

	public boolean insertTaskLog(String id,String taskId,String executerId);

	// 写入任务执行情况
	public boolean replaceIntoTaskExecutionLog(TsTaskExecutionLog tsTaskExecutionLog);

	public boolean updateTaskExecLog(String id,String endTime, String status, String data, String errorMessage);

}
