package com.bob.ktssts.controller;

import com.bob.ktssts.service.TsExecuterService;
import com.bob.ktssts.service.TsTaskService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TsTaskController {

	private static final Logger LOGGER = LogManager.getLogger(TsTaskController.class);

	@Resource
	TsTaskService tsTaskService;
	@Resource
	TsExecuterService tsExecuterService;

	@PostMapping("/resetAutoRpaTask")
	public String resetAutoRpaTask() {
		return null;
	}

}
