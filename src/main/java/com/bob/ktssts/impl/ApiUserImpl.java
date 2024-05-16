package com.bob.ktssts.impl;

import com.bob.ktssts.dao.ApiUserDao;
import com.bob.ktssts.domain.ApiUser;
import com.bob.ktssts.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUserImpl implements ApiUserService {

	@Autowired
	private ApiUserDao apiUserDao;

	@Override
	public String getToken(String username, String password) {
		ApiUser apiUser = apiUserDao.getUser("abc","123");
		if (apiUser == null) {
			return "";
		} else{
			return apiUser.getToken();
		}
	}

}
