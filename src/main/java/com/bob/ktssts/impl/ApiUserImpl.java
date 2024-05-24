package com.bob.ktssts.impl;

import com.bob.ktssts.mapper.ApiUserMapper;
import com.bob.ktssts.domain.ApiUser;
import com.bob.ktssts.util.SM4Util;
import com.bob.ktssts.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUserImpl implements ApiUserService {

	@Autowired
	private ApiUserMapper apiUserMapper;

	@Override
	public String getToken(String username, String password) {
		ApiUser apiUser = apiUserMapper.getUser(username, SM4Util.encryptData_CBC(password));
		if(apiUser != null) {
			return apiUser.getToken();
		}else return "user not found";
	}

	@Override
	public String getUserById(String id) {
		ApiUser apiUser = apiUserMapper.selectByPrimaryKey(id);
		return apiUser.getToken();
	}

	@Override
	public ApiUser getUserByUserPass(String username, String password) {
		//System.out.println("加密后的字符串为: " + SM4Util.encryptData_CBC(password));
		return apiUserMapper.getUser(username, SM4Util.encryptData_CBC(password));
	}

	@Override
	public String getPermissionByUser(String username) {
		return apiUserMapper.getPermissionByUserString(username);
	}

	@Override
	public String getRoleByUser(String username) {
		return apiUserMapper.getRoleByUserString(username);
	}

	@Override
	public ApiUser getUserByUsername(String username) {
		return apiUserMapper.getUserByUsername(username);
	}
}
