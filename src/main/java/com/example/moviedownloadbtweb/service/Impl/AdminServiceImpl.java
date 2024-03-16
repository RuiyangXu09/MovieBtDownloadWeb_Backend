package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.AdminMapper;
import com.example.moviedownloadbtweb.mapper.UserMapper;
import com.example.moviedownloadbtweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员功能的实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    //注入对应的mapper层接口中的方法来操作数据库
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 重写service接口中的方法，调用mapper接口中对应的select方法
     * @return
     */
    @Override
    public List<User> listUser() {
        //调用mapper接口中的方法来完成查询
        return adminMapper.listUser();
    }

    /**
     * 重写service接口中的方法，调用mapper接口中对应的delete方法
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        adminMapper.deleteUser(id);
    }
}
