package com.atgui.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class FlowLimitService {

    @SentinelResource(value = "chainLimit")
    public String limitService() {
        return "chainLimit";
    }
}
