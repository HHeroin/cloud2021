package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    void deduct(@Param("commodityCode") String commodityCode, @Param("count") int count);
}
