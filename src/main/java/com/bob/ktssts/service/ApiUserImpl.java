package com.bob.ktssts.service;

import com.bob.ktssts.entity.TsApiuser;
import com.bob.ktssts.mapper.TsApiuserMapper;
import com.bob.ktssts.util.TokenUtil;
import jakarta.annotation.Resource;
import org.apache.el.parser.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ApiUserImpl implements ApiUserService{

	private final Logger LOGGER = LogManager.getLogger(ApiUserImpl.class);

	@Resource
	TsApiuserMapper tsApiuserMapper;

	@Override
	public String login(String user, String pass) {
		String token = null;
		TsApiuser tsApiuser = tsApiuserMapper.verifyUser(user, pass);
		if(tsApiuser == null || tsApiuser.getId() == null)
			return token;
		try {
			token = TokenUtil.GenerateToken(tsApiuser.getId());
		}catch(Exception e ){
			LOGGER.error("生成token失败:{}", e.getMessage());
		}
		return token;
	}
}