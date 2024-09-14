package com.bob.ktssts.controller;

import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class ErrorHandlingController implements ErrorController {
//
//	private static final String PATH = "/error";
//
//	@RequestMapping(value = PATH)
//	public ResponseEntity<ResponseBean<Object>> handleError() {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBean<>("访问了错误了路径"));
//	}

}
