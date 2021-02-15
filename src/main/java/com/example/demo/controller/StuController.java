package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Stu;
import com.example.demo.service.StuService;
import com.example.demo.utils.RestResult;

@RestController
@RequestMapping("/rest/1.0/test")
public class StuController {

	@Autowired
	private StuService stuService;
	
	@RequestMapping("stus")
	public Object getStu() {
		List<Stu> allStu = stuService.getAllStu();
		return RestResult.resultOk(allStu);
	}
	
	@RequestMapping("add")
	public Object setStu() {
		Stu allStu = stuService.addStu();
		return RestResult.resultOk(allStu);
	}
	
}
