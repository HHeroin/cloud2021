package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

public interface PaymentService {
     Integer createPayment(Payment payment);

     Payment getPaymentById(Long id);
}
