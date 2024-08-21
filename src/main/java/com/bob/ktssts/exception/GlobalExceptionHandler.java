package com.bob.ktssts.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bob.ktssts.entity.resopnse.ErrorResponseBean;
import com.bob.ktssts.entity.resopnse.ResponseBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ResponseBean<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		LOGGER.error("请求主体内容缺失:{}",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBean<>(400,"请求主体内容缺失",ex.getMessage()));
	}

	// 捕捉token过期异常并返回
	@ExceptionHandler(TokenExpiredException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ResponseBean<Object>> handleTokenExpiredException(TokenExpiredException ex) {
		LOGGER.error("token过期:{}",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBean<>(400,"token过期",ex.getMessage()));
	}

	@ExceptionHandler(JWTDecodeException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ResponseBean<Void>> handleJWTDecodeException(JWTDecodeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBean<>(ex.getMessage()));
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ResponseBean<Void>> handleUnauthorizedException(UnauthorizedException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBean<>(ex.getMessage()));
	}

}

