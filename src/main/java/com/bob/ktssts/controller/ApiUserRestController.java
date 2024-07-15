package com.bob.ktssts.controller;

import com.bob.ktssts.entity.ResponseBean;
import com.bob.ktssts.entity.TsApiuser;
import com.bob.ktssts.entity.TsExecuter;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.schedule.RpaScheduleTask;
import com.bob.ktssts.service.ApiUserService;
import com.bob.ktssts.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiUserRestController {

	private static final Logger LOGGER = LogManager.getLogger(ApiUserRestController.class);

	@Resource
	TsExecuterMapper tsExecuterMapper;
	@Resource
	ApiUserService apiUserService;
	@Resource
	RpaScheduleTask rpaScheduleTask;

	@PostMapping("/login")
	public ResponseBean login(@RequestBody JsonNode jsonNode) {
		if(!JsonUtil.userInfoNotNull(jsonNode)){
			return new ResponseBean(ResponseBean.ECode.CLIENT_ERROR.getCode(), "账号/密码为空", null);
		}
		String token = apiUserService.login(JsonUtil.getUserFromJson(jsonNode), JsonUtil.getPassFromJson(jsonNode));
		return new ResponseBean(ResponseBean.ECode.SUCCESS.getCode(), "SUCCESS", token);
	}

	@RequestMapping("/test")
	public void test(HttpServletRequest request) {
		rpaScheduleTask.startTsTaskExecuter("XCI系统_报警信息处理", 1000, 5000);
		rpaScheduleTask.syncKAgentThreadTask(1000, 2000);
	}
}
