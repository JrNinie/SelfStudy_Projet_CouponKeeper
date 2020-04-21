package com.jr.couponkeeperapispringboot.mapper;

import com.jr.couponkeeperapispringboot.common.constant.Constants;
import com.jr.couponkeeperapispringboot.model.vo.CouponTemplate;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * <h1>Hbase CouponTemplate Row To CouponTemplate Object</h1>
 * 需要实现RowMapper<需要映射的对象>
 * ❤千万别选错RowMapper的import（来自Spring4All.spring.boot.starter.hbase.api的馈赠）
 */
public class CouponTemplatRowMapper implements RowMapper<CouponTemplate> {


    //Hbase里面存储无类型，全是字节数组byte[]
    private static byte[] FAMILY_B = Constants.CouponTemplateTable.FAMILY_B.getBytes();
    private static byte[] MERCHANT_ID = Constants.CouponTemplateTable.MERCHANT_ID.getBytes();
    private static byte[] TITLE = Constants.CouponTemplateTable.TITLE.getBytes();
    private static byte[] SUMMARY = Constants.CouponTemplateTable.SUMMARY.getBytes();
    private static byte[] DESC = Constants.CouponTemplateTable.DESC.getBytes();
    private static byte[] HAS_TOKEN = Constants.CouponTemplateTable.HAS_TOKEN.getBytes();
    private static byte[] BACKGROUND = Constants.CouponTemplateTable.BACKGROUND.getBytes();

    private static byte[] FAMILY_C = Constants.CouponTemplateTable.FAMILY_C.getBytes();
    private static byte[] LIMIT = Constants.CouponTemplateTable.LIMIT.getBytes();
    private static byte[] START = Constants.CouponTemplateTable.START.getBytes();
    private static byte[] END = Constants.CouponTemplateTable.END.getBytes();

    @Override
    public CouponTemplate mapRow(Result result, int i) throws Exception {

        CouponTemplate couponTemplate = new CouponTemplate();

        couponTemplate.setMerchantId(Bytes.toInt(result.getValue(FAMILY_B,MERCHANT_ID)));
        couponTemplate.setTitle(Bytes.toString(result.getValue(FAMILY_B,TITLE)));
        couponTemplate.setSummary(Bytes.toString(result.getValue(FAMILY_B,SUMMARY)));
        couponTemplate.setDesc(Bytes.toString(result.getValue(FAMILY_B,DESC)));
        couponTemplate.setHasToken(Bytes.toBoolean(result.getValue(FAMILY_B,HAS_TOKEN)));
        couponTemplate.setBackground(Bytes.toInt(result.getValue(FAMILY_B,BACKGROUND)));
        couponTemplate.setLimit(Bytes.toLong(result.getValue(FAMILY_C,LIMIT)));

        //关于日期的处理:先转String，再转成date
        String[] patterns = new String[]{"yyyy-MM-dd"};
        couponTemplate.setStart(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,START)),patterns));
        couponTemplate.setEnd(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,END)),patterns));



        return couponTemplate;
    }
}
