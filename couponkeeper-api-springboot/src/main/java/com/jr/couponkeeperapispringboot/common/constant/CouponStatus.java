package com.jr.couponkeeperapispringboot.common.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <h1>优惠券的状态</h1>
 */
//@Data
@NoArgsConstructor
@AllArgsConstructor
public enum CouponStatus {

    UNUSED(1,"coupon not used"),
    USED(2,"coupon already used"),
    ALL(3,"all coupons of a user");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
