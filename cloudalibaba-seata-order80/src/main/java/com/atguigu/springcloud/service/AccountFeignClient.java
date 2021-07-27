package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-account-service")
public interface AccountFeignClient {

    @GetMapping("/account/debit")
    public String debit(@RequestParam("userId") String userId, @RequestParam("money") long money);

}
