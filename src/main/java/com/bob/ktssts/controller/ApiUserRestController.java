package com.bob.ktssts.controller;

import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.service.ApiUserService;
import com.bob.ktssts.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ConditionalOnProperty(name = "usrmgt.enable", havingValue = "true")
@Slf4j
public class ApiUserRestController {

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
