package com.atgui.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atgui.springcloud.service.FlowLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FlowLimitController {

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "testB";
    }

    @GetMapping("/chain1")
    public String chainLimit1() {
        return flowLimitService.limitService();
    }

    @GetMapping("/chain2")
    public String chainLimit2() {
        return flowLimitService.limitService();
    }

    // 服务熔断降级测试
    @GetMapping("/testDegrade")
    @SentinelResource(value = "testDegrade",fallback = "testDegradeFallback",blockHandler = "testDegradeBlockHandler")
    public String testDegrade() {
        //int i = 10 / 0;
        return "---ok";
    }

    public Object testDegradeFallback() {
        return "---fallback";
    }

    public Object testDegradeBlockHandler(BlockException blockException) {
        return "---blockHandler";
    }

}
