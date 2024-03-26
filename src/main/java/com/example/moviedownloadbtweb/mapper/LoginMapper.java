package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.Admin;
import com.example.moviedownloadbtweb.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录功能的mapper接口，映射文件名：LoginMapper.xml
 */
@Mapper
public interface LoginMapper {
    /**
     * 管理员登录的sql
     * @param admin
     * @return
     */
    Admin adminLogin(Admin admin);

    /**
     * 用户登录的sql
     * @return
     */
    User userLogin(User user);

    /**
     * 查找用户名
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
