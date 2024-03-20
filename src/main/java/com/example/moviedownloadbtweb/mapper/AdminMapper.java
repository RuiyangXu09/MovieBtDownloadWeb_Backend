package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理员管理的mapper接口，映射文件名：AdminMapper.xml
 */
@Mapper
public interface AdminMapper {
    /**
     * 删除用户信息 by id的sql
     * @param id
     */
    void deleteUser(Integer id);

    /**
     *查询数据库中，用户的总记录数
     * @return
     */
    Integer countUser();

    /**
     *用户信息分页功能，接收两个参数，一个起始索引，一个每页的数据列表返回数
     * @param indexStart
     * @param pageSize
     * @return
     */
    List<User> pageUserList(Integer indexStart, Integer pageSize);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Admin getAdminById(Integer id);

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin adminLogin(Admin admin);
}
