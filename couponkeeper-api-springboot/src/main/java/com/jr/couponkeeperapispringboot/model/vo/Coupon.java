package com.jr.couponkeeperapispringboot.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>用户领取的优惠券类</h1>
 * ❤注意区分：CouponTemplate指的是商户发放的优惠券，但是Coupon指用户领取到的优惠券哦
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    private Long userId;
    /**coupon在Hbase里面的row key*/
    private String rowKey;
    /**外键关联关系：Hbase里面不存在外键，只是类似于外键这样的概念罢了。也就是CouponTemplate的主键*/
    //TODO:为什么是String而不是Long
    private String couponTemplateId;
    /**优惠券的token码，如果没有，则是-1*/
    private String token;
    /**领取日期*/
    private Date assignedDate;
    /**消费日期*/
    private Date consumedDate;

}
