package com.jr.couponkeeper.merchants.dao;

import com.jr.couponkeeper.merchants.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//不需要有daoImpl
public interface MerchantDao extends JpaRepository<Merchant,Integer>, JpaSpecificationExecutor<Merchant> {

    /*
 符合springdatajpa的dao层接口的规范：
 	-继承JpaRepository<T, ID>， T指操作的实体类类型，ID指实体类中主键属性的类型
 		封装了基本的CRUD操作
 	-继承JpaSpecificationExecutor<T>， T指操作的实体类类型
 		封装了复杂的查询（分页等）
 */

    /**
     * @param name 是唯一的
     * @return {@link Merchant}
     */
    //findBy 表示查询，然后再加上实体类属性名的名称custName，并且大写其首字母 => findByCustName根据属性名称进行查询
    public Merchant findByName(String name);

}
