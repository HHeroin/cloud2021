package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface HystrixPaymentFeignClient {
    @GetMapping("/hystrix/payment/ok")
    String paymentOk();

    @GetMapping("/hystrix/payment/timeout")
    String paymentTimeout();
}
