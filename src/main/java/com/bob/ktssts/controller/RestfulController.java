package com.bob.ktssts.controller;

import com.bob.ktssts.service.ApiUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulController {

	private static final Logger LOGGER = LogManager.getLogger(RestfulController.class);

	@Autowired
	ApiUserService apiUserService;


	@GetMapping("/test")
	public String test() {
		return "abc test";
	}

//	@PostMapping("/login")
//	public ResponseBean login(@RequestBody("username") String username, @RequestParam("password") String password) {
//		return null;
//	}
}
