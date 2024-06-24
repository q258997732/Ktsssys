package com.bob.ktssts.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * K-RPA Server的接口首字母为大写
 */

public class PascalCaseNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {

	@Override
	public String translate(String key) {
		if (key == null || key.isEmpty()) {
			return key;
		}
		// 将第一个字母大写，其余字母小写
		return key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
	}

}
