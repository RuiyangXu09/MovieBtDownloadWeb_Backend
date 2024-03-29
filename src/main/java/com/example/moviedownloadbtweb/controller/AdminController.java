package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.service.AdminService;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import com.example.moviedownloadbtweb.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 管理员controller
 */
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    /**
     * 注入adminService的bean
     */
    @Autowired
    private AdminService adminService;
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
    public Result<PageBean> pageUserList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = adminService.pageUserList(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 根据id查询管理员信息
     * @return
     */
    @GetMapping(value = "getAdminInfo")
    public Result<Admin> getAdminInfo(){
        //获取解析后的token值，map形式
        Map<String, Object> adminMap = ThreadLocalUtils.get();
        //将键值对中key为id的数据取出，强转为id类型数据，赋值给adminId
        int adminId = (int) adminMap.get("id");
        Admin admin = adminService.getAdminInfo(adminId);
        //返回前端一个admin对象，封装在result中
        return Result.success(admin);
    }
}
