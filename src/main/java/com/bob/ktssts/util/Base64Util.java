package com.bob.ktssts.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

	public static String String2Base64(String rpaData){
		return Base64.getEncoder().encodeToString(rpaData.getBytes(StandardCharsets.UTF_8));
	}

	public static String Base642String(String rpaData){
		return new String(Base64.getDecoder().decode(rpaData),StandardCharsets.UTF_8);
	}



}
