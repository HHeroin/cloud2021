package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = HystrixPaymentFallback.class)
@Primary
public interface HystrixPaymentFeignClient {
    @GetMapping("/hystrix/payment/ok")
    String paymentOk();

    @GetMapping("/hystrix/payment/timeout")
    String paymentTimeout();
}
