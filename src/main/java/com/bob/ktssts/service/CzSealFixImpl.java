package com.bob.ktssts.service;

import com.bob.ktssts.entity.cz.SealfixOaMapping;
import com.bob.ktssts.mapper.cz.SealfixOaMappingMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "cz.enable", havingValue = "true")
public class CzSealFixImpl implements CzSealFixService {

	@Resource
	SealfixOaMappingMapper sealfixOaMappingMapper;

	@Override
	public boolean insertTrade(String tradeNo) {
		SealfixOaMapping sealfixOaMapping = new SealfixOaMapping();
		sealfixOaMapping.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		sealfixOaMapping.setSealfixTradeno(tradeNo);
		sealfixOaMapping.setOaBillcode("");
		sealfixOaMapping.setSucess(0);
		sealfixOaMapping.setUpdateTime(new Date());
		int res = sealfixOaMappingMapper.insert(sealfixOaMapping);
		return res > 0;
	}

	@Override
	public boolean insertOperateLog(String operate_user, String operate_detail) {
		return false;
	}
}
