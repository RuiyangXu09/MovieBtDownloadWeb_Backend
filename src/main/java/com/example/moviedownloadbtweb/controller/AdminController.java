package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.service.AdminService;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 管理员controller
 */
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    //注入adminService层
    @Autowired
    AdminService adminService;
    /**
     * 查询用户列表
     * @return users
     */
    @GetMapping(value = "listUser")
    public Result listUser(){
        //调用service对象中对应的方法来查询数据，使用list集合来接收
        List<User> users = adminService.listUser();

        return Result.success(users);
    }

    /**
     * 删除对应的用户，传入参数为Integer id，删除操作，不需要返回数据
     */
    @DeleteMapping(value = "deleteUser")
    public Result deleteUser(Integer id){
        adminService.deleteUser(id);
        return Result.success();
    }
}
