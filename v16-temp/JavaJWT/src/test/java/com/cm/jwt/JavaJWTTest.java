package com.cm.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @author CaoMeng
 * @Date 2019-08-16
 */
public class JavaJWTTest{
    @Test
    public void jwtTokenCreateTest(){
        JwtBuilder builder = Jwts.builder()
                .setId("666").setSubject("行走在牛A的路上")
                .setIssuedAt(new Date())
                //添加自定义属性
                .claim("role","admin")
                .claim("sex","women")
                .setExpiration(new Date(new Date().getTime()+600000))
                .signWith(SignatureAlgorithm.HS256,"caomeng");

        String jwtToken = builder.compact();
        System.out.println(jwtToken);
    }

    @Test
    public void jwtTokenParseTest(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLooYzotbDlnKjniZtB55qE6Lev5LiKIiwiaWF0IjoxNTY1OTU0NzkzLCJyb2xlIjoiYWRtaW4iLCJzZXgiOiJ3b21lbiIsImV4cCI6MTU2NTk1NTM5M30.wQSrSWQa4iFrO8gN6WsbQjSh8Io_zy2Zm-FSu5ix4MU";
        Claims claims = Jwts.parser().setSigningKey("caomeng")
                .parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        //获取属性
        System.out.println(claims.get("role"));
        System.out.println(claims.get("sex"));


    }

}
