package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.StuMapper;
import com.example.demo.pojo.Stu;

@Service
public class StuService {
	
	@Autowired
	private StuMapper stuMapper;

	public List<Stu> getAllStu(){
		return stuMapper.selectList(null);
	}
	
	public Stu addStu() {
		Stu stu = new Stu();
		stu.setBirthday(new Date());
		stu.setGender("Y");
		stu.setName("zhangsan");
		stuMapper.insert(stu);
		return stu;
	}
}
