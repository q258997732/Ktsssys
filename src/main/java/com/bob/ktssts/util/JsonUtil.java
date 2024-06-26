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

	public static String formatKAgent(String json){

		// 替换所有的Name=和Value=，并将它们放在双引号中
		String[] objects = json.split("}, \\{");

		// 构建标准的JSON字符串
		StringBuilder jsonBuilder = new StringBuilder("[");
		for (int i = 0; i < objects.length; i++) {
			if (i > 0) {
				jsonBuilder.append(",\n ");
			}
			// 替换每个键，将其放在双引号中
			String obj = objects[i].replaceAll("(\\w+)=", "\"$1\":");
			// 将整个键值对放在花括号中
			jsonBuilder.append("{").append(obj).append("}");
		}
		jsonBuilder.append("]");

		// 输出转换后的JSON字符串
		String standardJson = jsonBuilder.toString();
		System.out.println(standardJson);
		return standardJson;
	}
}
