package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.LoginService;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录功能的controller
 */
@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {
    /**
     * 注入LoginService的bean
     */
    @Autowired
    private LoginService loginService;
    /**
     * 注入jwt
     */
    @Autowired
    private Jwt jwt;

    /**
     * 管理员登录的controller，不推荐名称和密码的分开验证，有暴力破解的危险性
     * @param admin
     * @return
     */
    @PostMapping(value = "adminLogin")
    public Result adminLogin(@RequestBody Admin admin){
        //需要存储传递返回值，类型为Admin对象
        Admin ad = loginService.adminLogin(admin);
        //登录成功，生成jwt令牌
        if (ad != null){
            //创建一个map对象claims，用于存储登录信息，不包括私密信息
            Map<String, Object> claims = new HashMap<>();
            //添加需要存储的信息
            claims.put("id", ad.getId());
            claims.put("admin_name", ad.getAdminName());
            //生成token，包含claims内容，也就是管理员登录的必要信息
            String token = jwt.generateJwt(claims);
            //将token值返回到前端
            return Result.success(token);
        }else {
            return Result.error("Admin name or Password is not correct.");
        }
    }

    /**
     * 用户登录的controller
     * @param user
     * @return
     */
    @PostMapping(value = "userLogin")
    public Result userLogin(@RequestBody User user){
        //需要存储传递返回值，类型为User对象
        User userDatabase = loginService.userLogin(user);
        if (userDatabase != null){
            //创建一个map对象claims，用于存储登录信息，不包括私密信息
            Map<String, Object> claims = new HashMap<>();
            //添加需要存储的信息，需要传入的是数据库返回的userDatabase中存储的信息
            claims.put("id", userDatabase.getId());
            claims.put("username", userDatabase.getUsername());
            claims.put("avatar_url", userDatabase.getAvatarUrl());
            claims.put("email", userDatabase.getEmail());
            //生成token，包含claims内容，也就是管理员登录的必要信息
            String token = jwt.generateJwt(claims);
            //将token值返回到前端
            return Result.success(token);
        }else {
            return Result.error("Account name or Password is not correct");
        }
    }
}
