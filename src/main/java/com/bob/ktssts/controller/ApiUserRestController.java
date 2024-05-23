package com.bob.ktssts.controller;

import com.bob.ktssts.service.ApiUserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiUserRestController {

	private static final Logger LOGGER = LogManager.getLogger(ApiUserRestController.class);

	@Autowired
	ApiUserService apiUserService;

	@GetMapping("/testGet")
	@RequiresRoles(logical = Logical.OR,value = {"api_user","admin"})
	public String testGet() {
		String token = apiUserService.getToken("test","test");
//		String token = apiUserService.getUserById("6884558f141d11ef8cc6525400485ee1");
		return "Hello World: " + token;
	}

	@PostMapping("/testPost")
	public String testPost(@RequestBody JsonNode jsonNode ){
		return jsonNode.get("username").textValue();
	}

	@PostMapping("/getToken")
	public String getToken( String user , String password){
		return apiUserService.getToken(user,password);
	}

//	@PostMapping("/login")
//	public ResponseBean login(@RequestBody("username") String username, @RequestParam("password") String password) {
//		return null;
//	}
}
