package com.hpugs.learning.web;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan
public class LearningWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningWebApplication.class, args);
	}

}
