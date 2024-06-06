package com.bob.ktssts.service;

import com.bob.ktssts.domain.ApiUser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public interface ApiUserService {

	Boolean getToken(JsonNode jsonNode);

	String getToken(String username, String password);

	String getUserById(String id);

	ApiUser getUserByUsername(String username);

	ApiUser getUserByUserPass(String username, String password);

	String getPermissionByUser(String username);

	String getRoleByUser(String username);



}
