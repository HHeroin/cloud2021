package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixPaymentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private HystrixPaymentFeignClient hystrixPaymentFeignClient;

    @GetMapping("/order/hystrix/payment/ok")
    public String paymentOk() {
        return hystrixPaymentFeignClient.paymentOk();
    }

    @GetMapping("/order/hystrix/payment/timeout")
    public String paymentTimeout() {
        return hystrixPaymentFeignClient.paymentTimeout();
    }
}
