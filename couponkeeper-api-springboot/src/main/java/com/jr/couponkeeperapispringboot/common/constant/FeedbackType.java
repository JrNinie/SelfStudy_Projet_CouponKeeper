package com.jr.couponkeeperapispringboot.common.constant;

/***
 * <h1>用户反馈类型的枚举</h1>
 */
//@Data //ENUM类上不能使用lambok
public enum FeedbackType {

    ABOUT_COUPON(1,"feeback about coupon"),
    ABOUT_APP(2,"feedback about app");

    private Integer code;
    private String desc;

    FeedbackType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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
