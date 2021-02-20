package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class SpringbootTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTest1Application.class, args);
	}

}
