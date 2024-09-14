package com.bob.ktssts.service;

import com.bob.ktssts.entity.cz.OpertedLog;
import com.bob.ktssts.entity.cz.SealfixOaMapping;
import com.bob.ktssts.mapper.cz.OpertedLogMapper;
import com.bob.ktssts.mapper.cz.SealfixOaMappingMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "cz.enable", havingValue = "true")
public class CzSealFixImpl implements CzSealFixService {

	@Resource
	SealfixOaMappingMapper sealfixOaMappingMapper;
	@Resource
	OpertedLogMapper opertedLogMapper;

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

	@Override
	public boolean insertOperateLog(OpertedLog opertedLog) {
		if(opertedLog == null)
			return false;
		// 补充默认属性
		if(opertedLog.getId()==null||opertedLog.getId().isEmpty())
			opertedLog.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		if(opertedLog.getOperateTime()==null)
			opertedLog.setOperateTime(new Date());
		if(opertedLog.getOperateUser()==null||opertedLog.getOperateUser().isEmpty())
			opertedLog.setOperateUser("system");

		int res = opertedLogMapper.insert(opertedLog);
		return res > 0;
	}

	@Override
	public List<SealfixOaMapping> getStatusByTradeNo(String tradeNo) {
		return sealfixOaMappingMapper.selectByTradeNo(tradeNo);
	}

	@Override
	public SealfixOaMapping getStatusById(String id) {
		return sealfixOaMappingMapper.selectByPrimaryKey(id);
	}


}
