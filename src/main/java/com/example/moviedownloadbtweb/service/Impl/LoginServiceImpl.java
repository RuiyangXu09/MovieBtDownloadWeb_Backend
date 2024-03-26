package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.LoginMapper;
import com.example.moviedownloadbtweb.service.LoginService;
import com.example.moviedownloadbtweb.utils.Result;
import org.mindrot.jbcrypt.BCrypt;
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
        //getUserByUsername方法返回查询到的user信息，封装到一个对象中
        User userFormDatabase = loginMapper.getUserByUsername(user.getUsername());
        //验证对象是否存在
        if (userFormDatabase != null){
            //验证用户输入的密码是否与数据库中的哈希值匹配
            boolean passwordMatched = BCrypt.checkpw(user.getPassword(), userFormDatabase.getPassword());
            //匹配就返回true
            if (passwordMatched){
                //返回一个user对象，封装了所有字段信息
                return loginMapper.userLogin(userFormDatabase);
            }else {
                //密码不匹配，抛出异常，交给controller层返回result的信息
                return null;
            }
        }else {
            //对象不存在，抛出异常，交给controller层返回result的信息
            return null;
        }
    }
}
