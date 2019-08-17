package com.cm.v16usersservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cm.v16.api.IUserService;
import com.cm.v16.common.base.BaseServiceImpl;
import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TUser;
import com.cm.v16.mapper.*;
import com.cm.v16usersservice.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override         //无状态登录处理
    public ResultBean selectByName (TUser user){
        TUser currentUser=userMapper.selectByName(user.getUsername());
        if(user.getPassword().equals(currentUser.getPassword())){
            JwtUtils jwtUtils=new JwtUtils();
            jwtUtils.setTtl(30*60*1000);
            jwtUtils.setSecretKey("caomeng");
            String jwtToken=jwtUtils.createJwtToken(currentUser.getId().toString(), currentUser.getUsername());

            return new ResultBean("200",jwtToken);
        }
        //登录成功，返回令牌
        return new ResultBean("500","账号或密码错误!");
    }
    
    @Override
    public ResultBean checkIsLogin (String token){
        //解析令牌
        if(token!=null){
            JwtUtils jwtUtils=new JwtUtils();
            jwtUtils.setSecretKey("caomeng");
            try {
                Claims claims=jwtUtils.parseJwtToken(token);
                String username=claims.getSubject();
                jwtUtils.setTtl(30*60*1000);
                return new ResultBean("200",token);
            }catch (SignatureException e){
                //出现异常，则表示令牌有问题或者令牌已过期
                return new ResultBean("404","当前用户未登录！");
            }
        }

        return new ResultBean("404","未登录");
    }
    
    @Override
    public IBaseDao<TUser> getDao (){
        return userMapper;
    }

    /*//生成令牌
    private String getToken (TUser user){
        JwtBuilder builder = Jwts.builder()
                .setId(user.getId()+"").setSubject(user.getUsername())
                .setIssuedAt(new Date())
                //添加自定义属性
                .claim("role","admin")
                .claim("sex","women")
                .setExpiration(new Date(new Date().getTime()+600000))
                .signWith(SignatureAlgorithm.HS256,"caomeng");
        return builder.compact();
    }*/

    //有状态登录处理
    /**
     *
     public ResultBean selectByName (TUser user){
     TUser currentUser=userMapper.selectByName(user.getUsername());
     if(user.getPassword().equals(currentUser.getPassword())){
     //将信息存入Redis
     String uuid=UUID.randomUUID().toString();
     StringBuilder key=new StringBuilder("userToken").append(uuid);
     //将信息存入Redis
     redisTemplate.opsForValue().set(key.toString(),currentUser);
     //设置有效期 30分钟
     redisTemplate.expire(key.toString(),30, TimeUnit.MINUTES);
     return new ResultBean("200",uuid);
     }
     //登录成功，返回uuid(后期查看登录状态，根据uuid查看)
     return new ResultBean("500","账号或密码错误!");
     }
     */

    //有状态登录校验
    /**
     public ResultBean checkIsLogin (String uuid){
     StringBuilder key=new StringBuilder("userToken").append(uuid);
     TUser currentUser=(TUser) redisTemplate.opsForValue().get(key.toString());
     if(currentUser!=null){
     redisTemplate.expire(key.toString(),30,TimeUnit.MINUTES);
     return new ResultBean("200",uuid);
     }
     return new ResultBean("404","未登录");
     }
     */
    
}
