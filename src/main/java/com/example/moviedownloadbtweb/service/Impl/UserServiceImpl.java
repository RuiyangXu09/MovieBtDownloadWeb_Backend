package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.UserMapper;
import com.example.moviedownloadbtweb.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
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
        //使用bcrypt对用户密码加密
        String hashedUserPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedUserPassword);
        userMapper.registerUser(user);
    }

    /**
     * 用户修改信息重写service接口的方法，调用mapper接口中对应的update方法
     * @param user
     */
    @Override
    public void updateUser(User user) {
        String hashedUserPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedUserPassword);
        userMapper.updateUser(user);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public User getUserInfo(int userId) {
        return userMapper.getUserInfo(userId);
    }

    /**
     * 检查email是否重复
     * @param user
     * @return
     */
    @Override
    public boolean checkDuplicateEmail(User user) {
        return userMapper.checkDuplicateEmail(user);
    }
}
