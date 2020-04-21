package com.jr.couponkeeperapispringboot.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogObject {

    /**动作的日志类型，在LogContent里面定义好的*/
    private String action;
    private Long userId ;
    private Long timestamp ;
    /**用户端的ip地址*/
    private String remoteIp;
    /**具体的日志信息*/
    private Object logInfo = null;


}
