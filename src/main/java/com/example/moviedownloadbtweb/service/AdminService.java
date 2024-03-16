package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.User;

import java.util.List;
/**
 * 管理员的各项功能
 */
public interface AdminService {
    /**
     * 查询全部用户数据
     */
    List<User> listUser();

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);
}
