package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @GetMapping("/zk/payment/create")
    public String createPayment() {
        return "message from zk "+ UUID.randomUUID().toString();
    }
}
