package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    // private final String PAYMENT_SERVICE_URL = "http://localhost:8001";
    private final String PAYMENT_SERVICE_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult createPayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/discovery")
    public Object discoveryClient() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:{}",service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info("instance:host:{},port:{},uri:{}",instance.getHost(),instance.getPort(),instance.getUri());
            }
        }
        return services;
    }
}
