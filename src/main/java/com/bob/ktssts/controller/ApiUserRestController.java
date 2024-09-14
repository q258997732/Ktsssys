package com.bob.ktssts.controller;

import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.service.ApiUserService;
import com.bob.ktssts.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiUserRestController {

	private static final Logger LOGGER = LogManager.getLogger(ApiUserRestController.class);

	@Resource
	ApiUserService apiUserService;

	@PostMapping("/login")
	public ResponseEntity<ResponseBean<Object>> login(@RequestBody JsonNode jsonNode) {
		if(!JsonUtil.userInfoNotNull(jsonNode)){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("用户名或密码错误"));
		}
		String token = apiUserService.login(JsonUtil.getUserFromJson(jsonNode), JsonUtil.getPassFromJson(jsonNode));
		if(token == null||token.isEmpty())
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("用户名或密码错误"));
		return ResponseEntity.ok(new SuccessResponseBean<>(token));
	}

}
