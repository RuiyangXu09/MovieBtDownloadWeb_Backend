package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;

/**
 * 登录功能的接口
 */
public interface LoginService {
    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin adminLogin(Admin admin);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);
}
