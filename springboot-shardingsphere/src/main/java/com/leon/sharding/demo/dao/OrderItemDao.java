package com.leon.sharding.demo.dao;

import com.leon.sharding.demo.entity.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 订单明细的dao
 *
 * @author ml.c
 * @date 1:56 PM 8/5/21
 **/
@Repository
public interface OrderItemDao extends Mapper<OrderItem> {
}


