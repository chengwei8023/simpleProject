package com.example.demo.service.impl;

import com.example.demo.pojo.Stu;
import com.example.demo.mapper.StuMapper;
import com.example.demo.service.StuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chengwei
 * @since 2021-02-19
 */
@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements StuService {

	@Autowired
	private StuMapper stuMapper;
	
	@Override
	public List<Stu> getAllStu() {
		return stuMapper.selectList(null);
	}

}
