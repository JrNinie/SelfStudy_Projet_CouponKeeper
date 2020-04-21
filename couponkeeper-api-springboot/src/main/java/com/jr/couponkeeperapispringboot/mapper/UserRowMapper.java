package com.jr.couponkeeperapispringboot.mapper;

import com.jr.couponkeeperapispringboot.common.constant.Constants;
import com.jr.couponkeeperapispringboot.model.vo.User;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;




/**
 * <h1>Hbase User Row To User Object</h1>
 * 需要实现RowMapper<需要映射的对象>
 * ❤千万别选错RowMapper的import（来自Spring4All.spring.boot.starter.hbase.api的馈赠）
 */
public class UserRowMapper implements RowMapper<User> {

    //Hbase里面存储无类型，全是字节数组byte[]
    private static byte[] FAMILY_B = Constants.UserTable.FAMILY_B.getBytes();
    private static byte[] NAME = Constants.UserTable.NAME.getBytes();
    private static byte[] AGE = Constants.UserTable.AGE.getBytes();
    private static byte[] SEX = Constants.UserTable.SEX.getBytes();

    private static byte[] FAMILY_O = Constants.UserTable.FAMILY_O.getBytes();
    private static byte[] PHONE = Constants.UserTable.PHONE.getBytes();
    private static byte[] ADDRESS = Constants.UserTable.ADDRESS.getBytes();



    @Override
    public User mapRow(Result result, int i) throws Exception {

        //得到列族b的各个属性列
        //❤ 别弄错Bytes来自org.apache.hadoop.hbase.util.Bytes
        User.BaseInfo baseInfo = new User.BaseInfo(
                Bytes.toString(result.getValue(FAMILY_B,NAME)),
                Bytes.toInt(result.getValue(FAMILY_B,AGE)),
                Bytes.toString(result.getValue(FAMILY_B,SEX))
                );

        //得到列族O的各个属性列
        User.OtherInfo otherInfo = new User.OtherInfo(
                Bytes.toString(result.getValue(FAMILY_O,PHONE)),
                Bytes.toString(result.getValue(FAMILY_O,ADDRESS))
                );

        //得到userId
        Long userId = Bytes.toLong(result.getRow());

        return new User(userId, baseInfo, otherInfo);
    }
}
