package com.jr.couponkeeperapispringboot.common.constant;

/*
通用常量定义类
 */
public class Constants {

    /**商户优惠券投放的kafka topic*/
    public static final String TEMPLATE_TOPIC = "merchants-template";

    /**给通过验证的商户发放的token， 这是header的key*/
    public static final String TOKEN_STRING = "token";

    /**具体的token信息，这里是hearder中发送的value，与上面的TOKEN_STRING共同组成键值对*/
    public static final String TOKEN = "jr-couponkeeper-merchants";



    /**保存token的目录，如果优惠券带token，则以文件的形式去存储*/
    public static final String TOKEN_DIR = "tmp/token/";

    /**已经使用过的优惠券后缀是下划线*/
    public static final String USED_TOKEN_SUFFIX = "_";

    /**用户数的redis key*/
    public static final String USER_COUNT_REDIS_KEY = "jr-user-count";

    //内部类：参考users.hsh内容
    public class UserTable{

        /**User Hbase表名*/
        public static final String TABLE_NAME = "ck:user";

        /**b列族的信息:基本信息列族*/
        public static final String FAMILY_B = "b";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String SEX = "sex";

        /**额外信息列族*/
        public static final String FAMILY_O = "o";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
    }


    public class CouponTemplateTable {

        /**coupontemplate Hbase表名*/
        public static final String TABLE_NAME = "ck:coupontemplate";

        /**b列族:基本信息列族*/
        public static final String FAMILY_B = "b";
        public static final String MERCHANT_ID = "merchantId";
        public static final String TITLE = "title";
        public static final String SUMMARY = "summary";
        public static final String DESC = "desc";
        //TODO: 是String还是boolean?
        public static final String HAS_TOKEN = "has_token";
        public static final String BACKGROUND = "background";

        /**c：约束信息列族*/
        public static final String FAMILY_C = "c";
        public static final String LIMIT = "limit";
        public static final String START = "start";
        public static final String END = "end";
    }



    public class CouponTable{

        /**coupon Hbase表名*/
        public static final String TABLE_NAME = "ck:coupon";

        /**i列族:信息列族*/
        public static final String FAMILY_I = "i";
        /**优惠券拥有者的Id*/
        public static final String USER_Id = "user_id";
        /**优惠券对应的coupon template的id，外键*/
        public static final String COUPON_TEMPLATE_ID = "coupon_template_id";
        /**优惠券的识别码：如果没有则显示-1*/
        public static final String TOKEN = "token";
        /**领取日期*/
        public static final String ASSIGNED_DATE = "assigned-date";
        /**消费日期*/
        public static final String CONSUMED_DATE = "consumed-date";
    }


    public class FeedbackTable{

        /**coupon Hbase表名*/
        public static final String TABLE_NAME = "ck:feedback";

        /**i列族:信息列族*/
        public static final String FAMILY_I = "i";
        public static final String USER_Id = "user_id";
        public static final String TYPE ="type";
        /**评论的如果是coupon，需要知道该coupon的id;如果是针对app做出的评论，可以显示空或者-1*/
        public static final String COUPON_TEMPLATE_ID = "coupon_template_id";
        public static final String COMMENT = "comment";
    }

}
