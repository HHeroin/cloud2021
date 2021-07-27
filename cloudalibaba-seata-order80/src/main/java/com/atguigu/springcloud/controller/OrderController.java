package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.OrderEntity;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public String createOrder(@RequestBody OrderEntity order) {
        orderService.createOrder(order);
        return "---创建订单成功";
    }
}