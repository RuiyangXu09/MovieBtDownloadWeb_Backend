package com.example.moviedownloadbtweb.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * 生成和解析jwt
 */
public class Jwt {
    /**
     * 设置JWT的key值和过期时间
     */
    private static String key = "movieDownload";
    private static Long expire = 604800000L;

    /**
     * 生成jwt令牌
     * @param claims jwt的第二部分payload中存储的内容，以键值对形式存储
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                //添加payload，也就是携带的数据内容
                .addClaims(claims)
                //算法和key
                .signWith(SignatureAlgorithm.HS256, key)
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                //生成jwt
                .compact();
        //返回给前端生成的jwt
        return jwt;
    }

    /**
     * 解析jwt
     * @param jwt jwt令牌
     * @return claims中携带的数据内容
     */
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                //设置密钥
                .setSigningKey(key)
                //要解析的jwt令牌
                .parseClaimsJws(jwt)
                //生成数据内容
                .getBody();
        return claims;
    }
}
