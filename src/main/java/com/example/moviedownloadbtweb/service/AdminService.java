package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.PageBean;

/**
 * 管理员的各项功能
 */
public interface AdminService {
    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 分页查询用户信息
     * @param page
     * @param pageSize
     * @return
     */
    PageBean pageUserList(Integer page, Integer pageSize);

    /**
     * 根据id查询用户信息
     * @param adminId
     * @return
     */
    Admin getAdminInfo(int adminId);
}
