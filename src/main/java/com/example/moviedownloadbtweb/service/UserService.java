package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.User;

/**
 * 用户的各项功能
 */
public interface UserService {
    /**
     * 用户注册
     */
    void registerUser(User user);

    /**
     * 修改用户信息
     */
    void updateUser(User user);
}
