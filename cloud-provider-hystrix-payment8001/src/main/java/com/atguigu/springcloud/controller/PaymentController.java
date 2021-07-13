package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String SERVER_PORT;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/hystrix/payment/ok")
    public String paymentOk() {
        return paymentService.paymentOk();
    }

    @GetMapping("/hystrix/payment/timeout")
    public String paymentTimeout() {
        return paymentService.paymentTimeout();
    }

    // 熔断，当时间窗口期内一定次数请求失败率达到一定百分比，则熔断服务，此时即使有正常请求也会走到降级方法，一定时间后进行重试，若能正常处理则关闭断路器
    @GetMapping("/hystrix/payment/circuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
