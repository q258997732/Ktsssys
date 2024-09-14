package com.bob.ktssts.controller;

import com.bob.ktssts.entity.cz.OpertedLog;
import com.bob.ktssts.entity.cz.SealfixOaMapping;
import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.mapper.upload.FileDao;
import com.bob.ktssts.service.CzSealFixService;
import com.bob.ktssts.service.FileService;
import com.bob.ktssts.util.Base64Util;
import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ConditionalOnProperty(name = "cz.enable", havingValue = "true")
public class SealFixController {

	private static final Logger LOGGER = LogManager.getLogger(SealFixController.class);

	@Resource
	RpaExecuter rpaExecuter;
	@Resource
	CzSealFixService czSealFixService;

	@Value("${cz.demo.agentIp}")
	private String demoAgentIp;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private FileService fileService;

	@PostMapping("/addRpaDataProcess")
	public ResponseEntity<ResponseBean<Object>> addRpaDataProcess(@RequestBody JsonNode jsonNode) {
		String flowName = "承章POC\\\\承章POC_借章流程";
		String params = "\"params\":{\"tradeNo\":\"RPA-001\",\"具体事由\":\"中国证券金融自动化运维项目2024付款通知函需盖章，修改了维护日期\",\"上传文件路径\":\"D:\\\\RPA\\\\POC文件目录\\\\天津神州浩天公司合同.docx\",\"超时时间\":5000}";
		String tradeNo = "test-01";
		try {
			flowName = jsonNode.get("flowName").asText();
			JsonNode paramNode = jsonNode.get("params");
			tradeNo = paramNode.get("tradeNo").asText();
			params = paramNode.toString();
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(400, "参数错误", e.getMessage()));
		}
		// String flowType, String flow, String data, String agentIp, int level
//		LOGGER.info("demoAgentIp:{}", demoAgentIp);
//		return ResponseEntity.ok().body(new SuccessResponseBean<>("test"));
		if(rpaExecuter.addRpaDataProcess("name",flowName, Base64Util.String2Base64(params), demoAgentIp, 4)) {
			OpertedLog opertedLog = new OpertedLog();
			opertedLog.setOperateUser("system");
			opertedLog.setOperateDetail("调用接口添加RPA任务成功:"+tradeNo);
			if(czSealFixService.insertOperateLog(opertedLog))
				LOGGER.info("记录接口调用成功");
			else
				LOGGER.info("记录接口调用失败");
			return ResponseEntity.ok().body(new SuccessResponseBean<>("添加RPA数据处理成功:"+tradeNo));
		}else{
			return ResponseEntity.badRequest().body(new ErrorResponseBean<>(500, "添加RPA数据处理失败", null));
		}
	}

	@PostMapping("/getFlowStatus")
	public ResponseEntity<ResponseBean<Object>> getFlowStatus(@RequestParam String tradeNo) {
		List<SealfixOaMapping> res = czSealFixService.getStatusByTradeNo(tradeNo);
		if(res.isEmpty())
			return ResponseEntity.ok().body(new SuccessResponseBean<>("未查询到结果"));
		else
			return ResponseEntity.ok().body(new SuccessResponseBean<>(czSealFixService.getStatusByTradeNo(tradeNo)));
	}

	@PostMapping("/getFileOriginName")
	public ResponseEntity<ResponseBean<Object>> getFileOriginName(@RequestParam String name) {
		return ResponseEntity.ok().body(new SuccessResponseBean<>(fileService.getFileOriginName(name)));
	}
}
