package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Stu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengwei
 * @since 2021-02-19
 */
public interface StuService extends IService<Stu> {

	public List<Stu> getAllStu();
	
}
