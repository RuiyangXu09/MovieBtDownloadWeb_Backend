package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.LoginMapper;
import com.example.moviedownloadbtweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录功能的实现类
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * 注入对应的mapper层接口中的方法来操作数据库
     */
    @Autowired
    LoginMapper loginMapper;
    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @Override
    public Admin adminLogin(Admin admin) {
        return loginMapper.adminLogin(admin);
    }

    /**
     *用户登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        return loginMapper.userLogin(user);
    }
}
