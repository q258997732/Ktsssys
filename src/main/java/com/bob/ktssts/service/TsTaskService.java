package com.bob.ktssts.service;

import com.bob.ktssts.entity.TsTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TsTaskService {

	public int syncXciEventSolveTask();

	public int distributeKRpaTask(List<TsTask> tsTaskList);

	public int deleteAutoRpaTask();

	public List<TsTask> getAllTsTask();

	public String getKRpaTaskIdByFlowName(String flowName);

	void refreshKRpaAgentThreadList();
}
