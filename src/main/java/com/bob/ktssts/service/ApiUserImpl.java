package com.bob.ktssts.service;

import com.bob.ktssts.entity.ApiUser;
import com.bob.ktssts.mapper.ApiUserMapper;
import com.bob.ktssts.util.SM4Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUserImpl implements ApiUserService {

	@Autowired
	private ApiUserMapper apiUserMapper;

	@Override
	public ApiUser getUserByUserPass(String username, String password) {
		//System.out.println("加密后的字符串为: " + SM4Util.encryptData_CBC(password));
		if(username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
//			System.out.println(SM4Util.encryptData_CBC(password));
			return apiUserMapper.getUser(username, SM4Util.encryptData_CBC(password));
		}else{
			return null;
		}
	}

	@Override
	public Boolean verifyTokenUser(String userId){
		ApiUser resApiUser = apiUserMapper.selectByPrimaryKey(userId);
		return resApiUser != null;
	}

}
