package com.bob.ktssts.controller;


import com.bob.ktssts.entity.ktss.KAgentThreadBean;
import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.util.ToptronExecuter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bob.ktssts.util.RpaExecuter;

import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
@ConditionalOnProperty(name = "toptron.enable", havingValue = "true")
public class ToptronController {

	@Resource
	private ToptronExecuter toptronExecuter;
	@Resource
	private RpaExecuter rpaExecuter;

	@CrossOrigin(origins = "*")
	@PostMapping("/execPower")
	public ResponseEntity<ResponseBean<Object>> execPower(@RequestBody JsonNode jsonNode) {
		boolean bo = toptronExecuter.ExecutePowerControl(jsonNode);
		if (bo) {
			return ResponseEntity.ok(new SuccessResponseBean<>( "execute power control success"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "execute power control fail"));
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/execControlMsg")
	public ResponseEntity<ResponseBean<Object>> execControlMsg(@RequestBody JsonNode jsonNode) {
		boolean bo = toptronExecuter.confirmTempToken(jsonNode);
		if (!bo) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "token error"));
		}

		bo = toptronExecuter.ExecuteControlMsg(jsonNode);
		if (bo) {
			return ResponseEntity.ok(new SuccessResponseBean<>( "execute Control Message success"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "execute Control Message fail"));
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/execRpa")
	public ResponseEntity<ResponseBean<Object>> execRpa(@RequestBody JsonNode jsonNode) {
		boolean bo = toptronExecuter.confirmTempToken(jsonNode);
		if (!bo) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "token error"));
		}

		bo = rpaExecuter.callRpaComponent(jsonNode);
		if (bo) {
			return ResponseEntity.ok(new SuccessResponseBean<>( "execute Rpa Component success"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "execute Rpa Component fail"));
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/getRpaAgentThread")
	public ResponseEntity<ResponseBean<Object>> getRpaAgentThread(@RequestBody JsonNode jsonNode) {
		boolean bo = toptronExecuter.confirmTempToken(jsonNode);
		if (!bo) {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "token error"));
		}

		Map<String, KAgentThreadBean> kAgentThreadBeanMap = rpaExecuter.getKRpaAgentThreadMap();
		if (kAgentThreadBeanMap != null) {
			return ResponseEntity.ok(new SuccessResponseBean<>("execute Rpa Component success"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "execute Rpa Component fail"));
		}
	}

}
