package com.bob.ktssts.impl;

import com.bob.ktssts.dao.ApiUserDao;
import com.bob.ktssts.domain.ApiUser;
import com.bob.ktssts.encryption.sm4.SM4Utils;
import com.bob.ktssts.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUserImpl implements ApiUserService {

	SM4Utils sm4 = new SM4Utils();
	@Autowired
	private ApiUserDao apiUserDao;

	@Override
	public String getToken(String username, String password) {
		ApiUser apiUser = apiUserDao.getUser(username,sm4.encryptData_CBC(password));
		if(apiUser != null) {
			return apiUser.getToken();
		}else return "user not found";
	}

	@Override
	public String getUserById(String id) {
		ApiUser apiUser = apiUserDao.selectByPrimaryKey(id);
		return apiUser.getToken();
	}

}
