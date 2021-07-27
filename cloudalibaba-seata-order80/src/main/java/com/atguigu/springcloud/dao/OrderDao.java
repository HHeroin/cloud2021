package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    /**
     * create order
     */
    void create(OrderEntity order);
}
