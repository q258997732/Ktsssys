package com.bob.ktssts.entity.resopnse;

import lombok.Data;

@Data
public class ResponseBean<T> {
	private int code;
	private String message;
	private T data;
}
