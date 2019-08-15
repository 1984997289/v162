package com.cm.v16.common.base;

import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-05
 */
public abstract class  BaseServiceImpl<T> implements IBaseService<T>{

    //未确定的实现方式采用抽象方法声明
    public abstract IBaseDao<T> getDao();

    public int deleteByPrimaryKey (Long id){
        return getDao().deleteByPrimaryKey(id);
    }

    public int insert (T record){
        return getDao().insert(record);
    }

    public int insertSelective (T record){
        return getDao().insertSelective(record);
    }

    public T selectByPrimaryKey (Long id){
        return getDao().selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective (T record){
        return getDao().updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs (T record){
        return getDao().updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey (T record){
        return getDao().updateByPrimaryKey(record);
    }

    public List<T> list (){
        return getDao().list();
    }
}
