package com.sample.demo.properties.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@RefreshScope
@Component
public class ValueConfig {
    
	@Value("${rest.uuid}")
    private String uuid;
	
	@Value("${schedule.task.cron}")
	private String cron;
}
