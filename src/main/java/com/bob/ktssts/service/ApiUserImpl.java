package com.bob.ktssts.service;

import com.bob.ktssts.entity.usrmgt.TsApiuser;
import com.bob.ktssts.mapper.usrmgt.TsApiuserMapper;
import com.bob.ktssts.util.TokenUtil;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(name = "usrmgt.enable", havingValue = "true")
public class ApiUserImpl implements ApiUserService{

	private final Logger LOGGER = LogManager.getLogger(ApiUserImpl.class);

	@Resource
	TsApiuserMapper tsApiuserMapper;

	@Override
	public String login(String user, String pass) {
		String token = null;
		TsApiuser tsApiuser = tsApiuserMapper.verifyUser(user, pass);
		if(tsApiuser == null || tsApiuser.getId() == null|| tsApiuser.getId().isEmpty())
			return token;
		try {
			token = TokenUtil.GenerateToken(tsApiuser.getId());
		}catch(Exception e ){
			LOGGER.error("生成token失败:{}", e.getMessage());
		}
		return token;
	}
}