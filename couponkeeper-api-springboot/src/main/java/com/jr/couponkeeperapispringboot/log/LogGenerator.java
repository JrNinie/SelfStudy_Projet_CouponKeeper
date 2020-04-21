package com.jr.couponkeeperapispringboot.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/***
 * <h1>日志生成器</h1>
 * 因为要用到HttpServletRequest,可以放进controller里面去生成log
 */
@Slf4j
public class LogGenerator {

    /**
     *
     * @param request 可以获取用户的ip，cookie, 请求头等信息
     * @param userId 用户id
     * @param action 日志类型
     * @param logInfo 具体的日志信息，可以是null
     */
    public static void generateLog(HttpServletRequest request, Long userId, String action, Object logInfo){
        log.info(
                JSON.toJSONString(
                        //request.getRemoteAddress获取ip地址
                        new LogObject(action,userId,System.currentTimeMillis(),request.getRemoteAddr(),logInfo)
                )
        );
    }
}
