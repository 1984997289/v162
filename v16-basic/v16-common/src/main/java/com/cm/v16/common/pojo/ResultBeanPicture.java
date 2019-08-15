package com.cm.v16.common.pojo;

import java.io.Serializable;

/**
 * @author CaoMeng
 * @Date 2019-08-07
 */
public class ResultBeanPicture implements Serializable{

    private String errno;
    private String[] data;

    public String getErrno (){
        return errno;
    }

    public void setErrno (String errno){
        this.errno=errno;
    }

    public String[] getData (){
        return data;
    }

    public void setData (String[] data){
        this.data=data;
    }

    public ResultBeanPicture (){
    }

    public ResultBeanPicture (String errno, String[] data){
        this.errno=errno;
        this.data=data;
    }
}
