package com.cm.v16.common.pojo;

import java.io.Serializable;

/**
 * @author CaoMeng
 * @Date 2019-08-06
 */
public class ResultBean<T> implements Serializable{
    private String statusCode;
    private T data;
   

    public ResultBean (String statusCode, T data){
        this.statusCode=statusCode;
        this.data=data;
    }

    public ResultBean (){
    }

    public String getStatusCode (){
        return statusCode;
    }

    public void setStatusCode (String statusCode){
        this.statusCode=statusCode;
    }

    public T getData (){
        return data;
    }

    public void setData (T data){
        this.data=data;
    }
}
