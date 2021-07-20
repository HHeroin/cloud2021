package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosPaymentController {

    @Value("${server.port}")
    private String SERVER_PORT;

    @GetMapping("/nacos/payment/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return "payment info from nacos:" + id + ",port" + SERVER_PORT;
    }
}
