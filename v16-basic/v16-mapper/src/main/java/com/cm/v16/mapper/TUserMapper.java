package com.cm.v16.mapper;

import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.entity.TUser;

public interface TUserMapper extends IBaseDao<TUser>{
    int deleteByPrimaryKey (Integer id);

    int insert (TUser record);

    int insertSelective (TUser record);

    TUser selectByPrimaryKey (Integer id);

    int updateByPrimaryKeySelective (TUser record);

    int updateByPrimaryKey (TUser record);

    TUser selectByName (String username);
}