package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    private StorageDao storageDao;


    public void deduct(String commodityCode, int count) {
        storageDao.deduct(commodityCode, count);
    }
}
