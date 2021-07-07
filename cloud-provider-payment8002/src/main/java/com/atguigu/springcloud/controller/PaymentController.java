package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String SERVER_PORT;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        log.info("create payment  1111 {}",payment );
        Integer result = paymentService.createPayment(payment);
        if (result > 0) {
            return new CommonResult(200,"创建成功"+SERVER_PORT);
        } else {
            return new CommonResult(300,"创建失败"+SERVER_PORT);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new CommonResult(300,"根据id查询失败"+SERVER_PORT);
        } else {
            return new CommonResult(200,"查询成功"+SERVER_PORT,payment);
        }
    }

}
