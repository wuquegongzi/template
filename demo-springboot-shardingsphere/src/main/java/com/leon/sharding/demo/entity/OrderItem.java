package com.leon.sharding.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单明细
 *
 * @author ml.c
 * @date 1:50 PM 8/5/21
 **/
@Table(name = "t_order_item")
public class OrderItem {

    /**
     * 订单明细流水ID
     */
    @Id
    @Column(name = "order_item_id")
    private Long orderItemId;

    /**
     * 订单流水ID
     */
    @Column(name = "order_id")
    private Long orderId;


    /**
     * 用户流水ID
     */
    @Column(name = "user_Id")
    private Long userId;

    /**
     * 卖家流水ID
     */
    @Column(name = "seller_id")
    private Long sellerId;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
