package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.domain.User;

import java.util.List;
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
}
