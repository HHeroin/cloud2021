package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void debit(String userId,long money) {
        accountDao.debit(userId,money);
    }
}
