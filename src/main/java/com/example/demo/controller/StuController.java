package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.pojo.Stu;
import com.example.demo.service.StuService;
import com.example.demo.utils.RestResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chengwei
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/rest/1.0/stu")
public class StuController {
	
	@Autowired
	private StuService stuService;
	
	@GetMapping("stus")
	public JSONObject getStus() {
		List<Stu> allStu = stuService.getAllStu();
		return RestResult.resultOk(allStu);
	}
	
	@GetMapping("{id}")
	public JSONObject getStu(@PathVariable(value = "id") Integer id) {
		Stu one = stuService.getOne(Wrappers.<Stu>lambdaQuery().eq(Stu::getId, id));
		return RestResult.resultOk(one);
	}

}
