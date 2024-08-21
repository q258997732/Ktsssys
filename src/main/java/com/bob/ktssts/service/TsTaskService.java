package com.bob.ktssts.service;

import com.bob.ktssts.entity.ktss.TsTask;
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

	public boolean insertTaskLog(String taskId);
}
