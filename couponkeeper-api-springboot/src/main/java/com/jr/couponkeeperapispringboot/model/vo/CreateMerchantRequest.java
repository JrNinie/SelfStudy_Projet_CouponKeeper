package com.jr.couponkeeperapispringboot.model.vo;


import com.jr.couponkeeperapispringboot.common.constant.ErrorCode;
import com.jr.couponkeeperapispringboot.dao.MerchantDao;
import com.jr.couponkeeperapispringboot.model.entity.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>商户注册时，发送请求时用到的对象</h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantRequest {

    private String name;
    private String logoUrl;
    private String businessLicenseUrl;
    private String phone;
    private String address;

    public ErrorCode validate(MerchantDao merchantDao){
        if(this.name == null){
            return ErrorCode.EMPTY_NAME;
        }
        if(merchantDao.findByName(this.name) != null){
            return ErrorCode.DUPLICATE_NAME;
        }
        if(this.logoUrl == null){
            return ErrorCode.EMPTY_LOGO;
        }
        if(this.businessLicenseUrl == null){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }
        if(this.phone == null){
            return ErrorCode.ERROR_PHONE;
        }
        if(this.address == null){
            return ErrorCode.EMPTY_ADDRESS;
        }
        return ErrorCode.SUCCESS;
    }



//        /**
//         *因为在dao模块引用了model模块，所以在model模块不能有dao的依赖，否则就构成了循环依赖
//         * 因此把原本需要merchantDao的方法改成了不需要merchantDao的方法
//         * 所以就不能判断是否有重复商户名，需要再判断
//         */
//        public ErrorCode validateExceptDuplicateName(){
//        if(name == null){
//            return ErrorCode.EMPTY_NAME;
//        }
//        if(logoUrl == null){
//            return ErrorCode.EMPTY_LOGO;
//        }
//        if(businessLicenseUrl == null){
//            return ErrorCode.EMPTY_BUSINESS_LICENSE;
//        }
//        if(phone == null){
//            return ErrorCode.ERROR_PHONE;
//        }
//        if(address == null){
//            return ErrorCode.EMPTY_ADDRESS;
//        }
//        return ErrorCode.SUCCESS;
//    }

    /**
     * 把请求者转换成注册商户（但是还没有验证其营业执照）
     * 也就是转换成可以保存在数据库里面的对象
     * @return
     */
    public Merchant toMerchant(){
        Merchant merchant = new Merchant();
        //this.name = name
        merchant.setName(this.name);
        merchant.setAddress(address);
        merchant.setBusinessLicenseUrl(businessLicenseUrl);
        merchant.setLogoUrl(logoUrl);
        merchant.setPhone(phone);

        return  merchant;
    }
}
