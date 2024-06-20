package com.bob.ktssts.service;

import com.bob.ktssts.entity.ResponseBean;
import com.bob.ktssts.entity.ApiUser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public interface ApiUserService {

	ApiUser getUserByUserPass(String username, String password);

	Boolean verifyTokenUser(String userId);
}
