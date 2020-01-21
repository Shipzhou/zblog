package com.shipzhou.zblog.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.shipzhou.zblog.entity.pojo.account.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    private static String jwtSecret;

    private static long TOKEN_EXP;

    @Value("${jwt.secret}")
    public void setJwtSecret(String secret) {
        JwtUtil.jwtSecret = secret;
    }

    @Value("${jwt.expireTime}")
    public void setTokenExp(long expireTime) {
        TOKEN_EXP = expireTime;
    }

    public static String genToken(Account account) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + TOKEN_EXP);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("accountJson", JSONObject.toJSONString(account))//userId
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(jwtSecret)); //SECRET加密
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = null;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
        jwt = verifier.verify(token);
        return jwt.getClaims();
    }
}
