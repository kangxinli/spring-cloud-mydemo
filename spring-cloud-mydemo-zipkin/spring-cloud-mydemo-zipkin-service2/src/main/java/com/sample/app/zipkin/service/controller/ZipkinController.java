package com.sample.app.zipkin.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ZipkinController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@GetMapping(value = "/zipkin2")
	public String zipkinService1() {
		log.info("Inside zipkinService 2..");

		String response = (String) restTemplate.exchange("http://localhost:8083/zipkin3", 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {}
		).getBody();
		return "[zs2] hi," + response;
	}

}
