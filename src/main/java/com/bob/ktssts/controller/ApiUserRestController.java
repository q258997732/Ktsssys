package com.bob.ktssts.controller;

import com.bob.ktssts.entity.resopnse.ErrorResponseBean;
import com.bob.ktssts.entity.resopnse.ResponseBean;
import com.bob.ktssts.entity.resopnse.SuccessResponseBean;
import com.bob.ktssts.mapper.ktss.TsExecuterMapper;
import com.bob.ktssts.schedule.RpaScheduleTask;
import com.bob.ktssts.service.ApiUserService;
import com.bob.ktssts.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
	TsExecuterMapper tsExecuterMapper;
	@Resource
	ApiUserService apiUserService;
	@Resource
	RpaScheduleTask rpaScheduleTask;

	@PostMapping("/login")
	public ResponseEntity<ResponseBean<Object>> login(@RequestBody JsonNode jsonNode) {
		if(!JsonUtil.userInfoNotNull(jsonNode)){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>("用户名或密码错误"));
		}
		String token = apiUserService.login(JsonUtil.getUserFromJson(jsonNode), JsonUtil.getPassFromJson(jsonNode));
		return ResponseEntity.ok(new SuccessResponseBean<>(token));
	}

	@RequestMapping("/test")
	public void test(HttpServletRequest request) {
//		rpaScheduleTask.startTsTaskExecuter("XCI系统_报警信息处理", 1000, 5000);
//		rpaScheduleTask.syncKAgentThreadTask(1000, 2000);
	}
}
