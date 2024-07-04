package com.bob.ktssts.service;

import com.bob.ktssts.entity.TsTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TsTaskService {

	public int syncEventSolveTask();

	public int distributeKRpaTask(List<TsTask> tsTaskList);
}
