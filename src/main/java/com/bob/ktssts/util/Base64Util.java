package com.bob.ktssts.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

	public static String toKRpaBase64(String rpaData){
		return Base64.getEncoder().encodeToString(rpaData.getBytes(StandardCharsets.UTF_8));
	}

}
