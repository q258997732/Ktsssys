package com.bob.ktssts.service;

import org.springframework.stereotype.Service;

@Service
public interface CzSealFixService {
	public boolean insertTrade(String tradeNo);
	public boolean insertOperateLog(String operate_user,String operate_detail);
}
