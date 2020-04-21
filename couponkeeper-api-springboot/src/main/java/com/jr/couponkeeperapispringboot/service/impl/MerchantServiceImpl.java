package com.jr.couponkeeperapispringboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.jr.couponkeeperapispringboot.common.constant.Constants;
import com.jr.couponkeeperapispringboot.common.constant.ErrorCode;
import com.jr.couponkeeperapispringboot.dao.MerchantDao;
import com.jr.couponkeeperapispringboot.model.entity.Merchant;
import com.jr.couponkeeperapispringboot.model.vo.CouponTemplate;
import com.jr.couponkeeperapispringboot.model.vo.CreateMerchantRequest;
import com.jr.couponkeeperapispringboot.model.vo.CreateMerchantResponse;
import com.jr.couponkeeperapispringboot.model.vo.Response;
import com.jr.couponkeeperapispringboot.service.IMerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class MerchantServiceImpl implements IMerchantService {

    private final MerchantDao merchantDao;
    /**kafka的客户端*/
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public MerchantServiceImpl(MerchantDao merchantDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantDao = merchantDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional //因为涉及到save所以一定要添加事务(成功就保存，失败就回滚）！！！
    public Response createMerchant(CreateMerchantRequest request) {
        Response response = new Response();
        CreateMerchantResponse merchantResponse = new CreateMerchantResponse();

        //校验商户注册信息是否符合要求，在createMerchantRequest里面有validate方法
        ErrorCode errorCode = request.validate(merchantDao);
        if(errorCode != ErrorCode.SUCCESS){
            merchantResponse.setId(-1);
            //设置给用户看的错误码和相关描述
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDescription());
        }else {
            //先保存该商户，然后取出其id值进行返回
            merchantResponse.setId(merchantDao.save(request.toMerchant()).getId());
        }
        //如果成功则返回生成的Id，失败则是-1
        response.setData(merchantResponse);
        return response;
    }


//    @Override
//    @Transactional //因为涉及到save所以一定要添加事务(成功就保存，失败就回滚）！！！
//    public Response createMerchant(CreateMerchantRequest request) {
//        Response response = new Response();
//        CreateMerchantResponse merchantResponse = new CreateMerchantResponse();
//
//        //TODO:是否还有其它更好的方法呢？
//        //校验商户注册信息是否符合要求，在createMerchantRequest里面有validateExceptDuplicateName方法
//        //但是该方法不能检查是否有商户重名（因为要使用到MerchantDao,但是用不能循环引用，所以只好把需要用到dao的判断放在这里了，而不是在model类里面）
//        ErrorCode errorCode = null;
//        String name = request.getName();
//        if(merchantDao.findByName(name) != null){
//            errorCode =  ErrorCode.DUPLICATE_NAME;
//        }else{
//            errorCode = request.validateExceptDuplicateName();
//        }
//
//        if(errorCode != ErrorCode.SUCCESS){
//            merchantResponse.setId(-1);
//            //设置给用户看的错误码和相关描述
//            response.setErrorCode(errorCode.getCode());
//            response.setErrorMsg(errorCode.getDescription());
//        }else {
//            //先保存该商户，然后取出其id值进行返回
//            merchantResponse.setId(merchantDao.save(request.toMerchant()).getId());
//        }
//        //如果成功则返回生成的Id，失败则是-1
//        response.setData(merchantResponse);
//        return response;
//    }

    /**如果*/
    @Override
    public Response buildMerchantInfoById(Integer id) {
        Response response = new Response();
        Optional<Merchant> merchant = merchantDao.findById(id);

        if(!merchant.isPresent()){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDescription());
        }
        response.setData(merchant.get());
        return response;
    }

    /**商户在平台上投放优惠券*/
    @Override
    public Response promoteCouponTemplate(CouponTemplate couponTemplate) {
        Response response = new Response();
        ErrorCode errorCode = couponTemplate.validate(merchantDao);

        //查看该商户是否存在，只有存在的情况下才能用kafka
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDescription());
        }else {
            //把couponTemplate转换成string方便投放，用Json序列化的方式
            String couponTemplateString  = JSON.toJSONString(couponTemplate);
            //通过kafka客户端进行投放
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC, //topic
                    Constants.TEMPLATE_TOPIC, //key
                    couponTemplateString //value
            );
            log.info("promoteCouponTemplate:{}", couponTemplateString);
        }
        return response;
    }


//    /**商户在平台上投放优惠券*/
//    @Override
//    public Response promoteCouponTemplate(CouponTemplate couponTemplate) {
//        Response response = new Response();
//        //ErrorCode errorCode = couponTemplate.validate(merchantDao);
//
//        Optional<Merchant> merchant = merchantDao.findById(couponTemplate.getMerchantId());
//        if(!merchant.isPresent()){
//            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
//            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDescription());
//        }else {
//            //把couponTemplate转换成string方便投放，用Json序列化的方式
//            String couponTemplateString  = JSON.toJSONString(couponTemplate);
//            //通过kafka客户端进行投放
//            kafkaTemplate.send(
//                    Constants.TEMPLATE_TOPIC, //topic
//                    Constants.TEMPLATE_TOPIC, //key
//                    couponTemplateString //value
//            );
//            log.info("promoteCouponTemplate:{}", couponTemplateString);
//        }
//
//        return response;
//    }


}
