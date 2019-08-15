package com.cm.v16.api;

import com.cm.v16.common.base.IBaseService;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TUser;

/**
 * @author CaoMeng
 * @Date 2019-08-15
 */
public interface IUserService extends IBaseService<TUser>{
    ResultBean selectByName (TUser user);

    ResultBean checkIsLogin (String uuid);
}
