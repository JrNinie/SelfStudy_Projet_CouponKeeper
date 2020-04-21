package com.jr.couponkeeperapispringboot.log;

public class LogConstants {

    public class ActionNname{
        /**用户查看优惠券信息*/
        public static final String USER_COUPON_INFO ="UserCouponInfo";
        /**用户查看已使用的优惠券信息*/
        public static final String USER_USED_COUPON_INFO = "UserUsedCouponInfo";

        /**用户使用优惠券*/
        public static final String USER_USE_COUPON ="UserUseCoupon";
        /**用户获得优惠券*/
        public static final String USER_GAIN_COUPON_TEMPLATE = "UserGainCouponTemplate";
        /**用户获取库存信息*/
        //TODO:是查看优惠券的是否还有吗？名字是否需要变动？
        public static final String USER_INVENTORY_INFO = "UserInventoryInfo";

        /**用户写评论*/
        public static final String USER_CREATE_FEEDBACK = "UserCreateFeedback";
        /**用户查看自己写过的评论*/
        public static final String USER_GET_FEEDBACK = "UserGetFeedback";
    }
}
