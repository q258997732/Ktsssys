package com.bob.ktssts.entity;

import java.util.Objects;

public enum ExecStateEnum {
	RUN("-1", "正在运行"),
	STOP("0", "手动停止"),
	SUCCESS("1", "执行成功"),
	TIMEOUT("2", "执行超时");

	private final String code;
	private final String description;

	ExecStateEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ExecStateEnum fromCode(String code) {
		for (ExecStateEnum execStateEnum : ExecStateEnum.values()) {
			if (Objects.equals(execStateEnum.getCode(), code)) {
				return execStateEnum;
			}
		}
		return null;
	}

}
