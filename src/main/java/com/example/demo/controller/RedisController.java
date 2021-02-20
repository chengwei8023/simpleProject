package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.RestResult;

@RestController
@RequestMapping("/rest/1.0/redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@GetMapping("/{key}")
	public JSONObject getRedisByKey(@PathVariable(value = "key")String key) {
		Object object;
		try {
			object = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResult.resultError(e.getMessage());
		}
		
		return RestResult.resultOk(object);
	}
	
	@GetMapping("/{key}/{value}")
	public JSONObject setRedisByKey(@PathVariable(value = "key")String key, @PathVariable(value = "value")String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResult.resultError(e.getMessage());
		}
		
		return RestResult.resultOk("save success.");
	}
	
}
