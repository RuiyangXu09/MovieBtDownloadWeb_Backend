package com.example.moviedownloadbtweb.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.UserService;
import com.example.moviedownloadbtweb.utils.AliyunOss;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import com.example.moviedownloadbtweb.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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
    private UserService userService;
    /**
     * 注入AliyunOss的bean
     */
    @Autowired
    private AliyunOss aliyunOss;
    @Autowired
    private Jwt jwt;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "registerUser")
    public Result registerUser(@RequestBody User user){
        //检查用户名和密码是否为空
        if (user.getUsername() != null && !user.getUsername().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()){
            //检查密码长度是否符合标准
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
        //TODO 用户名重复校验是否必要等待验证，没有完成根据id修改用户信息，考虑使用token传入id值作为参数而不是user对象，目前仅为demo
        //检查用户名和密码是否为空
        if (user.getUsername() != null && !user.getUsername().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()){
            //检查密码长度是否符合标准
            if (user.getPassword().length() >= 5 && user.getPassword().length() <= 10){
                //检查用户名是否由重复
                if (userService.checkDuplicateUsername(user)){
                    return Result.error("Username duplicated.");
                }else {
                    //完成更新
                    userService.updateUser(user);
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
     * 获取用户信息，根据传过来的token获取
     * @return
     */
    @GetMapping(value = "getUserInfo")
    public Result getUserInfo(){
        //获取token解析后的map类型数据，token已经在登录拦截器中获得
        Map<String, Object> userMap = ThreadLocalUtils.get();
        //将键值对中key为id的数据取出，强转为id类型数据，赋值给userId
        int userId = (int) userMap.get("id");
        //获取userId并作为参数传入service层中的方法getUserInfo，返回一个user对象，user对象中包含用户的必要信息
        User user = userService.getUserInfo(userId);

        return Result.success(user);
    }
}
