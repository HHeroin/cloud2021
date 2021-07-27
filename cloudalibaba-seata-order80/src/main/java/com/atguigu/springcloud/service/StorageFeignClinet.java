package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage-service")
public interface StorageFeignClinet {

    @GetMapping("/storage/deduct")
    String deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count);
}
