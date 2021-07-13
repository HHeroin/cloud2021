package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeignOrderController {

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @PostMapping("/order/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        return paymentFeignClient.createPayment(payment);
    }

    @GetMapping("/order/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignClient.getPaymentById(id);
    }

    @GetMapping("/order/payment/feign/timeout")
    public String paymentTimeout() {
        return paymentFeignClient.paymentFeignTimeout();
    }
}
