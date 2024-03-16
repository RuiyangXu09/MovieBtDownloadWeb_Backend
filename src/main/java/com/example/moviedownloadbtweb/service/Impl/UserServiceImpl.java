package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.UserMapper;
import com.example.moviedownloadbtweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户功能的实现类
 */
@Service
public class UserServiceImpl implements UserService {
    //注入对应的mapper层接口来完成用户注册
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册，重写mapper接口中的方法
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userMapper.registerUser(user);
    }

    /**
     * 用户修改信息重写mapper接口的方法
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
