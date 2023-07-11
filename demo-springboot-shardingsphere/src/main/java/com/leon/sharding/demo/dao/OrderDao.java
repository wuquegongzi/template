package com.leon.sharding.demo.dao;

import com.leon.sharding.demo.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 订单的dao
 *
 * @author ml.c
 * @date 4:27 PM 8/4/21
 **/
@Repository
public interface OrderDao extends Mapper<Order> {
    /**
     * 功能描述： 根据用户ID来查订单数据
     *
     * @param userId 用户ID
     * @return 返回查询结果
     */
    Order queryMyOrder(@Param("userId") Integer userId);
}

