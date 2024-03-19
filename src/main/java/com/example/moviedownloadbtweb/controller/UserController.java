package com.example.moviedownloadbtweb.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.UserService;
import com.example.moviedownloadbtweb.utils.AliyunOss;
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
    //注入user的service层
    @Autowired
    UserService userService;

    //注入阿里云oss
    @Autowired
    AliyunOss aliyunOss;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "registerUser")
    public Result registerUser(@RequestBody User user){
        //调用service完成用户的注册
        userService.registerUser(user);
        return Result.success();
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
}
