package com.cm.v16sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.IUserService;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaoMeng
 * @Date 2019-08-15
 */
@Controller
@RequestMapping("user")
public class LoginController{

    @Reference
    private IUserService userService;

    /**
     * 登录验证
     * @param username  用户登录信息
     * @param password  用户登录信息
     * @return  返回uuid
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(String username, String password,
                            HttpServletResponse response){
        TUser user=new TUser(username,password);
        ResultBean<String> resultBean=userService.selectByName(user);
        //目标：登陆成功后将用户信息存入Redis，并设置有效期；并设置cookie
        if("200".equals(resultBean.getStatusCode())){
            String key="user_token";
            String uuid=resultBean.getData();
            //默认设置会话级cookie，不用设置有效期
            Cookie cookie=new Cookie(key,uuid);
            cookie.setPath("/");
            //防止XSS攻击，设置true ,表示无法从客户端通过脚本获取cookie信息
            cookie.setHttpOnly(true);
            //将cookie写入会话
            response.addCookie(cookie);
            return  new ResultBean("200",cookie.getValue());
        }
        return  new ResultBean("404","账号或密码错误!");
    }
    //第二种简化的从客户端获取UUID的方式，使用注解@CookieValue 代码如下
    @RequestMapping("checkIsLogin")
    public ResultBean checkIsLogin2(@CookieValue(name = "user_token",required = false) String uuid){
        if(uuid!=null){
            ResultBean resultBean=userService.checkIsLogin(uuid);
            if("200".equals(resultBean.getStatusCode())){
                return resultBean;
            }
        }
        return new ResultBean("404","未登录");
    }
    
    @RequestMapping("checkIsLogin")
    public ResultBean checkIsLogin(HttpServletRequest request){
        //验证登录状态,从客户端获取cookie，与redis中到uuid 比较
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("user_token".equals(cookie.getName())){
                    String uuid=cookie.getValue();
                    ResultBean resultBean=userService.checkIsLogin(uuid);
                    if("200".equals(resultBean.getStatusCode())){
                        return resultBean;
                    }
                }
            }
        }
        return new ResultBean("404","未登录");
    }
}
