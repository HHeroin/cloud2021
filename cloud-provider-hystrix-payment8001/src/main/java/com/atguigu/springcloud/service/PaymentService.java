package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentOk() {
        return "线程池名称:" + Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String paymentTimeout() {
        int i = 1 / 0;
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池名称:" + Thread.currentThread().getName();
    }

    public String paymentTimeoutFallback() {
        return Thread.currentThread().getName() + "服务超时或异常";
    }


    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("程序异常");
        }
        return Thread.currentThread().getName() + IdUtil.randomUUID();
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id:" + id + "不能为负数";
    }

}
