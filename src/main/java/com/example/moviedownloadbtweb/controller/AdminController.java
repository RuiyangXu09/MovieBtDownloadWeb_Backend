package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.service.AdminService;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员controller
 */
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    /**
     * 注入adminService层
     */
    @Autowired
    AdminService adminService;
    /**
     * 注入jwt令牌
     */
    @Autowired
    Jwt jwt;
    /**
     * 删除对应的用户，传入参数为Integer id，删除操作，不需要返回数据
     */
    @DeleteMapping(value = "deleteUser")
    public Result deleteUser(Integer id){
        adminService.deleteUser(id);
        return Result.success();
    }

    /**
     * 分页查询用户信息，接收两个前端参数，page默认值为 1，pageSize默认值为 10
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = "pageUserList")
    public Result pageUserList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = adminService.pageUserList(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    @GetMapping(value = "getAdminById")
    public Result getAdminById(Integer id){
        Admin admin = adminService.getAdminById(id);
        //返回前端一个admin对象，封装在result中
        return Result.success(admin);
    }

    /**
     * 管理员登录，不推荐名称和密码的分开验证，有暴力破解的危险性
     * @param admin
     * @return
     */
    @PostMapping(value = "adminLogin")
    public Result adminLogin(@RequestBody Admin admin){
        //需要存储传递返回值，类型为Admin对象
        Admin a = adminService.adminLogin(admin);
        //登录成功，生成jwt令牌
        if(a != null){
            //创建一个map对象claims，用于存储登录信息
            Map<String, Object> claims = new HashMap<>();
            //添加需要存储的信息
            claims.put("id", admin.getId());
            claims.put("admin_name", admin.getAdminName());
            claims.put("admin_password", admin.getAdminPassword());
            //生成token，包含claims内容，也就是管理员登录的必要信息
            String token = jwt.generateJwt(claims);
            //将token值返回到前端
            return Result.success(token);
        }else {
            return Result.error("Admin name or Password is not correct.");
        }
    }
}
