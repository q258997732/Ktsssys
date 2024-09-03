package com.bob.ktssts.service;

import com.bob.ktssts.entity.ktss.TsExecuter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TsExecuterService {

	public int cleanKRpaAgent();

	/**
	 *
	 * @return 执行结果
	 */
	public boolean syncKRpaAgent();

	public List<TsExecuter> getFreeExecuter(String execType, int num);


}
