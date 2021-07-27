package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.entity.OrderEntity;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private StorageFeignClinet storageFeignClinet;

    @GlobalTransactional
    public void createOrder(OrderEntity orderEntity) {
        orderDao.create(orderEntity);

        storageFeignClinet.deduct(orderEntity.getCommodityCode(),orderEntity.getCount());

        long money = orderEntity.getCount() * 10;
        accountFeignClient.debit(orderEntity.getUserId(),money);


    }

}
