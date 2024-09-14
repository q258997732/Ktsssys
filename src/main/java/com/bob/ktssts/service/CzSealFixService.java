package com.bob.ktssts.service;

import com.bob.ktssts.entity.cz.OpertedLog;
import com.bob.ktssts.entity.cz.SealfixOaMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CzSealFixService {
	 boolean insertTrade(String tradeNo);
	 boolean insertOperateLog(String operate_user,String operate_detail);

	boolean insertOperateLog(OpertedLog opertedLog);

	 List<SealfixOaMapping> getStatusByTradeNo(String tradeNo);
	// 根据ID获取
	 SealfixOaMapping getStatusById(String id);
}
