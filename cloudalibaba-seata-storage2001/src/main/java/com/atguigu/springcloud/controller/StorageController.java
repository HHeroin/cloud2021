package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/storage/deduct")
    public String deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count){
        storageService.deduct(commodityCode,count);
        return "库存更新成功";
    }
}
