package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.User;

/**
 * 用户的各项功能
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    void registerUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getUserInfo(int userId);

    /**
     * 检查email是否重复
     * @param user
     * @return
     */
    boolean checkDuplicateEmail(User user);
}
