package com.sample.demo.properties.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sample.demo.properties.config.BizConfig;
import com.sample.demo.properties.config.ValueConfig;

@RestController
public class DemoController {
    @Autowired
    private ContextRefresher contextRefresher;

    @Autowired
    private BizConfig bizConfig;

    @Autowired
    private ValueConfig valueConfig;

    @GetMapping(path = "/show")
    public String show() {
        JSONObject res = new JSONObject();
        res.put("biz", JSONObject.toJSONString(bizConfig));
        res.put("uuid", valueConfig.getUuid());
        return res.toJSONString();
    }

    @GetMapping(path = "/refresh")
    public String refresh() {
        new Thread(() -> contextRefresher.refresh()).start();
        return show();
    }
    
    /**
     * 原则上不推荐写在这里
     * @param event
     */
//    @EventListener
//    public void envListener(EnvironmentChangeEvent event) {
//        System.out.println("conf change: " + event);
//    }
}
