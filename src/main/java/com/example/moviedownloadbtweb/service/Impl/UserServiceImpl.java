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
    /**
     * 注入UserMapper的bean
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册，重写service接口中的方法，调用mapper接口中对应的insert方法
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userMapper.registerUser(user);
    }

    /**
     * 用户修改信息重写service接口的方法，调用mapper接口中对应的update方法
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    /**
     * 检查username是否重复
     * @param user
     * @return
     */
    @Override
    public boolean checkDuplicateUsername(User user) {
        return userMapper.checkDuplicateUsername(user);
    }

    /**
     * @param avatarUrl
     */
}
