package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixPaymentFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "paymentDefaultFallback")
public class OrderController {

    @Autowired
    private HystrixPaymentFeignClient hystrixPaymentFeignClient;

    @HystrixCommand(fallbackMethod = "paymentOkFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/order/hystrix/payment/ok")
    public String paymentOk() {
        return hystrixPaymentFeignClient.paymentOk();
    }

    public String paymentOkFallback() {
        return "服务超时或不可用";
    }


    @HystrixCommand(fallbackMethod = "paymentTimeoutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping("/order/hystrix/payment/timeout")
    public String paymentTimeout() {
        return hystrixPaymentFeignClient.paymentTimeout();
    }

    public String paymentTimeoutFallback() {
        return "服务超时或不可用";
    }

    @HystrixCommand
    @GetMapping("/order/hystrix/payment/default")
    public String paymentDefault() {
        int i = 1 / 0;
        return "ok----default";
    }

    //为feignClient添加fallback实现类

    @GetMapping("/feign/order/hystrix/payment/ok")
    public String paymentFeignOk() {
        return hystrixPaymentFeignClient.paymentOk();
    }

    @GetMapping("/feign/order/hystrix/payment/timeout")
    public String paymentFeignTimeout() {
        return hystrixPaymentFeignClient.paymentTimeout();
    }

    // global fallback
    public String paymentDefaultFallback() {
        return "Global fallback";
    }


}
