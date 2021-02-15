package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;

public class RestResult {
	
	public static JSONObject resultOk(Object obj) {
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		json.put("data", obj);
		json.put("message", "success");
		return json;
	}
	
	public static JSONObject resultError(Object obj, String message) {
		JSONObject json = new JSONObject();
		json.put("status", "error");
		json.put("data", obj);
		json.put("message", message);
		return json;
	}

	public static JSONObject resultError(String message) {
		JSONObject json = new JSONObject();
		json.put("status", "error");
		json.put("data", null);
		json.put("message", message);
		return json;
	}
}
