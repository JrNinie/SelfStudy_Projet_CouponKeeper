package com.jr.couponkeeperapispringboot.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>user类</h1>
 * user的信息要存入Hbase（参看users.hsh和constants里面相关部分），有两个列族 b , o，用内部类解决
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**row key*/
    private Long id;
    /**用户基本信息，b列族*/
    private BaseInfo baseInfo;
    /**用户补充信息，o列族*/
    private OtherInfo otherInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseInfo{
        private String name;
        private Integer age;
        private String sex;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherInfo{
        private String phone;
        private String address;
    }


}
