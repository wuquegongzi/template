package com.leon.sharding.demo.service;

import com.leon.sharding.demo.dao.OrderDao;
import com.leon.sharding.demo.dao.OrderItemDao;
import com.leon.sharding.demo.entity.Order;
import com.leon.sharding.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单的service
 *
 * @author ml.c
 * @date 4:34 PM 8/4/21
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

    /**
     * 功能描述： 实现新增订单
     * @param order 订单的实体
     */
    public void saveOrder(Order order){
        orderDao.insertSelective(order);
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItemDao.insertSelective(orderItem);
        }
    }

    /**
     * 功能描述： 根据订单ID来获取订单数据
     * @param orderId 订单流水ID
     * @return 返回查询结果
     */
    public Order getOrderByOrderId( Long orderId){
        return orderDao.selectByPrimaryKey(orderId);
    }

    /**
     * 功能描述： 根据用户ID来获取订单数据
     * @param userId 用户ID
     * @return 返回查询结果
     */
    public Order getMyOrder( Integer userId){
        return orderDao.queryMyOrder(userId);
    }

}
