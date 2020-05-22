package com.sample.demo.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sample.demo.business.feign.OrderFeignClient;
import com.sample.demo.business.feign.StorageFeignClient;

import io.seata.spring.annotation.GlobalTransactional;

/**
 * 业务
 *
 */
@Service
public class BusinessService {
    private static final String COMMODITY_CODE = "P001";
    private static final int ORDER_COUNT = 1;

    @Resource
    private OrderFeignClient orderFeignClient;

    @Resource
    private StorageFeignClient storageFeignClient;

    /**
     * 下订单
     */
    @GlobalTransactional
    public void placeOrder(String userId) {
        storageFeignClient.deduct(COMMODITY_CODE, ORDER_COUNT);

        orderFeignClient.create(userId, COMMODITY_CODE, ORDER_COUNT);
    }
}
