package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        Integer result = paymentService.createPayment(payment);
        if (result > 0) {
            return new CommonResult(200,"创建成功");
        } else {
            return new CommonResult(300,"创建失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new CommonResult(300,"根据id查询失败");
        } else {
            return new CommonResult(200,"查询成功",payment);
        }
    }

}
