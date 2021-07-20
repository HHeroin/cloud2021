package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosOrderController {

    @Value("http://nacos-payment-provider")
    private String NACOS_PAYMENT_SERVICE;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/nacos/order/payment/{id}")
    public String getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(NACOS_PAYMENT_SERVICE + "/nacos/payment/" + id,String.class);
    }
}
