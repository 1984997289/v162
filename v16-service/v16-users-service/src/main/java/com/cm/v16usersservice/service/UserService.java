package com.cm.v16usersservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cm.v16.api.IUserService;
import com.cm.v16.common.base.BaseServiceImpl;
import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TUser;
import com.cm.v16.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author CaoMeng
 * @Date 2019-08-15
 */
@Service
public class UserService extends BaseServiceImpl<TUser> implements IUserService{

    @Autowired
    private TUserMapper userMapper;

    @Resource(name = "stringRsdisModuel")
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean selectByName (TUser user){
        TUser currentUser=userMapper.selectByName(user.getUsername());
        if(user.getPassword().equals(currentUser.getPassword())){
            //将信息存入Redis
            String uuid=UUID.randomUUID().toString();
            StringBuilder key=new StringBuilder("userToken").append(uuid);
            //将信息存入Redis
            redisTemplate.opsForValue().set(key,currentUser);
            //设置有效期 30分钟
            redisTemplate.expire(key,30, TimeUnit.MINUTES);
            return new ResultBean("200",uuid);
        }
        //登录成功，返回uuid(后期查看登录状态，根据uuid查看)
        return new ResultBean("500","账号或密码错误!");
    }

    @Override
    public ResultBean checkIsLogin (String uuid){
        StringBuilder key=new StringBuilder("userToken").append(uuid);
        TUser currentUser=(TUser) redisTemplate.opsForValue().get(key);
        if(currentUser!=null){
            redisTemplate.expire(key,30,TimeUnit.MINUTES);
            return new ResultBean("200",uuid);
        }
        return new ResultBean("404","未登录");
    }

    @Override
    public IBaseDao<TUser> getDao (){
        return userMapper;
    }
}
