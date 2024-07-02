package com.bob.ktssts.service;

import org.springframework.stereotype.Service;

@Service
public interface TsExecuterService {

	public int cleanKRpaAgent();

	public boolean syncKRpaAgent();



}
