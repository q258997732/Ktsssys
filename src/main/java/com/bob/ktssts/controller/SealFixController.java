package com.bob.ktssts.controller;

import com.bob.ktssts.entity.resopnse.ErrorResponseBean;
import com.bob.ktssts.entity.resopnse.ResponseBean;
import com.bob.ktssts.entity.resopnse.SuccessResponseBean;
import com.bob.ktssts.service.CzSealFixService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ConditionalOnProperty(name = "cz.enable", havingValue = "true")
public class SealFixController {

	private static final Logger LOGGER = LogManager.getLogger(SealFixController.class);

	@Resource
	RpaExecuter rpaExecuter;
	@Resource
	CzSealFixService czSealFixService;

	@PostMapping("/addRpaDataProcess")
	public ResponseEntity<ResponseBean<Object>> addRpaDataProcess(@RequestBody JsonNode jsonNode) {
		String flowName;
		String agentIp;
		String params;
		String tradeNo;
		try {
			flowName = jsonNode.get("flowName").asText();
			agentIp = jsonNode.get("agentIp").asText();
			JsonNode paramNode = jsonNode.get("params");
			tradeNo = paramNode.get("tradeNo").asText();
			params = paramNode.toString();
//			LOGGER.info("params:{}", params);
//			LOGGER.info("tradeNo:{}", tradeNo);
		}catch(Exception e){
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "参数错误", e.getMessage()));
		}
		// String flowType, String flow, String data, String agentIp, int level
		if(rpaExecuter.addRpaDataProcess("name",flowName, Base64Util.String2Base64(params), agentIp, 4)) {

			if(czSealFixService.insertTrade(tradeNo))
				LOGGER.info("记录接口调用成功");
			else
				LOGGER.info("记录接口调用失败");
			return ResponseEntity.ok().body(new SuccessResponseBean<>("添加RPA数据处理成功:"+tradeNo));
		}else{
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "添加RPA数据处理失败", null));
		}

	}

}
