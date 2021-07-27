package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/account/debit")
    public String debit(@RequestParam("userId") String userId, @RequestParam("money") long money) {
        accountService.debit(userId,money);
        return "账号扣款成功";
    }
}
