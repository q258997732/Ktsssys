package com.bob.ktssts.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {

	public static boolean userInfoNotNull(JsonNode jsonNode) {
		try {
			String user = jsonNode.get("user").textValue();
			String password = jsonNode.get("pass").textValue();
			if(user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
				return true;
			}
		}catch (Exception exception){
			return false;
		}
		return false;
	}

	public static String getUserFromJson(JsonNode jsonNode) {
		if(JsonUtil.userInfoNotNull(jsonNode)){
			return jsonNode.get("user").textValue();
		}else{
			return null;
		}
	}

	public static String getPassFromJson(JsonNode jsonNode) {
		if(JsonUtil.userInfoNotNull(jsonNode)){
			return jsonNode.get("pass").textValue();
		}else{
			return null;
		}
	}
}
