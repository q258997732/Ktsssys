package com.bob.ktssts.service;

import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.util.RpaExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TsExecuterImpl implements TsExecuterService {

	@Autowired
	private RpaExecuter rpaExecuter;

	@Autowired
	private TsExecuterMapper tsExecuterMapper;

	@Override
	public int cleanKRpaAgent() {
		return 0;
	}
}
