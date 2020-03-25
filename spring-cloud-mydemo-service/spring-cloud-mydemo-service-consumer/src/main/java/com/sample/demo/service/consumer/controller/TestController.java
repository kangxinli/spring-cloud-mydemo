package com.sample.demo.service.consumer.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sample.demo.service.consumer.entity.User;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/consumer/test")
@RestController
@Slf4j
public class TestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/users/{id}")
	@SentinelResource(value = "findById", blockHandler = "exceptionHandler")
	public User findById(@PathVariable Long id) {
		// 这里用到了RestTemplate的占位符能力
		User user = this.restTemplate.getForObject("http://demo-service-provider/users/{id}", User.class, id);
		return user;
	}

	public User exceptionHandler(Long id, BlockException ex) {
		log.error("限流处理", ex);
		return new User(-1L, "默认用户", "默认用户", 1, new BigDecimal("1"));
	}

}
