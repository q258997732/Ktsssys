package com.bob.ktssts.entity.response;

import lombok.Data;

@Data
public class ResponseBean<T> {
	private int code;
	private String message;
	private T data;
}
