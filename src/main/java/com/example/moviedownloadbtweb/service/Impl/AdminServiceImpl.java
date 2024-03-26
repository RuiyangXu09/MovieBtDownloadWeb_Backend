package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.domain.User;
import com.example.moviedownloadbtweb.mapper.AdminMapper;
import com.example.moviedownloadbtweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员功能的实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    /**
     * 注入对应的mapper层接口中的方法来操作数据库
     */
    @Autowired
    private AdminMapper adminMapper;
    /**
     * 重写service接口中的方法，调用mapper接口中对应的delete方法
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        adminMapper.deleteUser(id);
    }

    /**
     * 分页查询用户信息，重写service接口中的方法，调用mapper接口中对应的select方法和count方法，返回一个总记录数和一些数据列表，数据列表存入数组并封装到一个对象pageBean中
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean pageUserList(Integer page, Integer pageSize) {
        //获取总用户记录数
        Integer count = adminMapper.countUser();
        //定义起始索引值
        Integer indexStart = (page - 1) * pageSize;
        //定义一个数组接收返回的查询到的数据列表
        List<User> pageUserList = adminMapper.pageUserList(indexStart, pageSize);
        //new一个pageBean对象来封装获取到的总记录数和返回的数据列表，对象中有两个参数，一个count接收总记录数，一个list接收数据列表
        return new PageBean(count, pageUserList);
    }

    /**
     * 根据id查询用户信息
     * @param adminId
     * @return
     */
    @Override
    public Admin getAdminInfo(int adminId) {
        return adminMapper.getAdminInfo(adminId);
    }
}
