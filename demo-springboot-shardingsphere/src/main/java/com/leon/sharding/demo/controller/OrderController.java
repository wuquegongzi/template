package com.leon.sharding.demo.controller;

import com.leon.sharding.demo.entity.Order;
import com.leon.sharding.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单的controller
 *
 * @author ml.c
 * @date 5:14 PM 8/4/21
 **/
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 功能描述： 实现新增订单
     * @param order 订单的实体
     */
    @PostMapping("saveOrder")
    public void saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
    }

    /**
     * 功能描述： 根据订单ID来获取订单数据
     * @param orderId 订单流水ID
     * @return 返回查询结果
     */
    @PostMapping("getOrderByOrderId")
    public Order getOrderByOrderId( Long orderId){
        return orderService.getOrderByOrderId(orderId);
    }

    /**
     * 功能描述： 根据用户ID来获取订单数据
     * @param userId 用户ID
     * @return 返回查询结果
     */
    @PostMapping("getMyOrder")
    public Order getMyOrder( Integer userId ){
        return orderService.getMyOrder(userId);
    }
}
