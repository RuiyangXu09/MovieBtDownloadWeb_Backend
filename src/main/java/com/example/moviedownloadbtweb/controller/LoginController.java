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
     * 注入登录的service层
     */
    @Autowired
    private LoginService loginService;
    /**
     * 注入jwt
     */
    @Autowired
    private Jwt jwt;

    /**
     * 管理员登录，不推荐名称和密码的分开验证，有暴力破解的危险性
     * @param admin
     * @return
     */
    @PostMapping(value = "adminLogin")
    public Result adminLogin(@RequestBody Admin admin){
        //需要存储传递返回值，类型为Admin对象
        Admin ad = loginService.adminLogin(admin);
        //登录成功，生成jwt令牌
        if (ad != null){
            //创建一个map对象claims，用于存储登录信息
            Map<String, Object> claims = new HashMap<>();
            //添加需要存储的信息
            claims.put("id", ad.getId());
            claims.put("admin_name", ad.getAdminName());
            claims.put("admin_password", ad.getAdminPassword());
            //生成token，包含claims内容，也就是管理员登录的必要信息
            String token = jwt.generateJwt(claims);
            //将token值返回到前端
            return Result.success(token);
        }else {
            return Result.error("Admin name or Password is not correct.");
        }
    }

    @PostMapping(value = "userLogin")
    public Result userLogin(@RequestBody User user){
        //需要存储传递返回值，类型为User对象
        User usr = loginService.userLogin(user);
        if (usr != null){
            //创建一个map对象claims，用于存储登录信息
            Map<String, Object> claims = new HashMap<>();
            //添加需要存储的信息
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            claims.put("avatar_url", user.getAvatarUrl());
            //生成token，包含claims内容，也就是管理员登录的必要信息
            String token = jwt.generateJwt(claims);
            //将token值返回到前端
            return Result.success(token);
        }else {
            return Result.error("Username or Password is not correct");
        }
    }
}
