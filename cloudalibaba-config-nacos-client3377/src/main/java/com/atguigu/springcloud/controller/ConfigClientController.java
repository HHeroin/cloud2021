package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Autowired
    private Environment env;

    @Value("${person.age}")
    private String age;

    @GetMapping("/nacos/cofig/getAge")
    public String getAge() {
        return "person.age:" + age;
    }

    @GetMapping("/nacos/config/{key}")
    public Object getConfigValue(@PathVariable("key") String key) {
        return env.getProperty(key);
    }
}
