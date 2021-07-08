package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    private final String PAYMENT_SERVICE = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/zk/payment/create")
    public String createPayment() {
        return restTemplate.getForObject(PAYMENT_SERVICE + "/zk/payment/create",String.class);
    }
}
