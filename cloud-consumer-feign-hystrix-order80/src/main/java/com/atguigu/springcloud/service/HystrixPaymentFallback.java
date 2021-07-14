package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixPaymentFallback implements HystrixPaymentFeignClient {
    @Override
    public String paymentOk() {
        return "paymentOk---feignClientFallback";
    }

    @Override
    public String paymentTimeout() {
        return "paymentTimeout---feignClientFallback";
    }
}
