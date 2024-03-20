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
     *根据id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);
}
