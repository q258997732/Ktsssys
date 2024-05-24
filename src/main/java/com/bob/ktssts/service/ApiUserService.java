package com.bob.ktssts.service;

import com.bob.ktssts.domain.ApiUser;
import org.springframework.stereotype.Service;

@Service
public interface ApiUserService {

	String getToken(String username, String password);

	String getUserById(String id);

	ApiUser getUserByUsername(String username);

	ApiUser getUserByUserPass(String username, String password);

	String getPermissionByUser(String username);

	String getRoleByUser(String username);

}
