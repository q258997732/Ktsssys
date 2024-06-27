package com.bob.ktssts.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.regex.Pattern;

public class JsonUtil {

	private static final Pattern KEY_PATTERN = Pattern.compile("(\\w+)=");
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
