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
		rpaScheduleTask.startTsTaskExecuter("XCI系统_报警信息处理",1000,5000);
//		String exec_name = "测试执行器";
//		String exec_addr = "10.1.20.7,10.1.20.8,10.1.20.47";
//		String exec_type = "KTSSTS";
//		String exec_available = "1";
//		String exec_monopoly = "1";
//		Date exec_register = new Date();
//		String exec_version = "1.0.0";
//
//
//		TsExecuter tsExecuter = new TsExecuter(exec_name, exec_addr, exec_type, exec_available, exec_monopoly, exec_register, exec_version);
//		tsExecuter.setExec_online("1");
//
//		int num = tsExecuterMapper.insertSelective(tsExecuter);
//		LOGGER.info("插入执行器信息：{}", num);
//		return new ResponseBean(ResponseBean.ECode.SUCCESS.getCode(), "test success .", "test success .");
	}

//	@PostMapping("/getToken")
//	public ResponseBean getToken(@RequestBody JsonNode jsonNode){
//		String user = jsonNode.get("username").textValue();
//		String password = jsonNode.get("password").textValue();
//		String token = null;
//		ApiUser apiUser = apiUserService.getUserByUserPass(user, password);
//		if(apiUser != null){
//			return new ResponseBean(ECode.SUCCESS.getCode(),"SUCCESS",JWTUtil.createJWT(user, password));
//		}
//		return new ResponseBean(ECode.CLIENT_ERROR.getCode(),"User Or Password Error .",null);
//	}

//	@PostMapping("/login")
//	public ResponseBean login(@RequestBody("username") String username, @RequestParam("password") String password) {
//		return null;
//	}
}
