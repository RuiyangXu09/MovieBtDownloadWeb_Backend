package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.UserService;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户controller
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    //注入user的service层
    @Autowired
    UserService userService;

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
}
