package com.cm.v16.vo;

import com.cm.v16.entity.TProduct;

import java.io.Serializable;

/**
 * @author CaoMeng
 * @Date 2019-08-06
 */
public class ProductVO implements Serializable{

    private TProduct product;
    private String productDesc;


    public TProduct getProduct (){
        return product;
    }

    public void setProduct (TProduct product){
        this.product=product;
    }

    public String getProductDesc (){
        return productDesc;
    }

    public void setProductDesc(String productDesc){
        this.productDesc=productDesc;
    }
}
