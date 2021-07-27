package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private Long id;
    private String userId;
    private String commodityCode;
    private int count;
    private int money;

    public OrderEntity(String userId, String commodityCode, int count) {
        this.userId = userId;
        this.commodityCode = commodityCode;
        this.count = count;
    }
}
