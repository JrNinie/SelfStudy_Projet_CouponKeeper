package com.jr.couponkeeperapispringboot.model.vo;

import com.jr.couponkeeperapispringboot.common.constant.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.EnumUtils;

/**
 * <h1>反馈对象</h1>
 * TODO：可以限制评论的长度
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    private Long userId;
    /**评论的类型：是针对app还是coupon*/
    private String type;
    /**
     * 外键关联关系：Hbase里面不存在外键，只是类似于外键这样的概念罢了。也就是CouponTemplate的主键
     * 如果是app的反馈则没有couponTemplateId
     * */
    //TODO:为什么是String而不是Long
    private String couponTemplateId;
    private String comment;

    public boolean validate(){
        //检查feedback的type是否是我们定义过的类型
        boolean isRightFeedbackType = EnumUtils.isValidEnumIgnoreCase(FeedbackType.class, type);
        //如果是定义过的类型，且内容不为空，则通过验证；反之，不能通过
        if (isRightFeedbackType && comment != null){
            return true;
        }else {
            return false;
        }

    }

}
