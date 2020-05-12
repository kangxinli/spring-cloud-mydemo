package com.sample.demo.properties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "biz")
public class BizConfig {
    private String key;
    private Long refresh;
}
