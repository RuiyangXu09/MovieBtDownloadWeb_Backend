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
     * 获取用户信息，根据传过来的token获取
     * @param token
     * @return
     */
    @GetMapping(value = "getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token){
        //解析请求头中的token，返回一个User类型的集合
        Map<String, Object> userMap = jwt.parseJwt(token);
        //强转为String类型，找到请求头中的字段名id中的值
        int userId = (int) userMap.get("id");
        //获取userId并作为参数传入service层中的方法getUserInfo，返回一个user对象，user对象中包含用户的必要信息
        User user = userService.getUserInfo(userId);

        return Result.success(user);
    }
}
