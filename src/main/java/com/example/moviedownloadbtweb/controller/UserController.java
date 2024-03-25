package com.example.moviedownloadbtweb.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.UserService;
import com.example.moviedownloadbtweb.utils.AliyunOss;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 用户controller
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    /**
     * 注入UserService的bean
     */
    @Autowired
    UserService userService;
    /**
     * 注入Jwt的bean
     */
    @Autowired
    Jwt jwt;
    /**
     * 注入AliyunOss的bean
     */
    @Autowired
    AliyunOss aliyunOss;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "registerUser")
    public Result registerUser(User user){
        //检查用户名和密码是否为空
        if (user.getUsername() != null && !user.getUsername().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()){
            if (user.getPassword().length() >= 5 && user.getPassword().length() <= 10){
                //检查username是否重复，重复返回一个info
                if (userService.checkDuplicateUsername(user)){
                    return Result.error("Username duplicated.");
                }else {
                    userService.registerUser(user);
                    //调用service完成用户的注册
                    return Result.success();
                }
            }else {
                return Result.error("Password must be between 5 and 10 characters.");
            }
        }else {
            return Result.error("Username or Password could not be empty.");
        }
    }

    /**
     * 用户修改信息
     * @param user
     * @return
     */
    @PutMapping(value = "updateUser")
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 用户上传头像，文件上传到oss中后，获取url，传递到前端表单中，前端表单一起将参数传入对应接口放入数据库中
     * @param avatarUrl
     * @return
     * @throws IOException
     * @throws ClientException
     */
    @PostMapping(value = "uploadAvatar")
    public Result uploadAvatar(@RequestBody MultipartFile avatarUrl) throws IOException, ClientException {
        String url = aliyunOss.uploadAvatar(avatarUrl);

        return Result.success(url);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping(value = "getUserById")
    public Result getUserById(Integer id){
        User user = userService.getUserById(id);
        //返回前端一个user对象，封装在result中
        return Result.success(user);
    }
}
