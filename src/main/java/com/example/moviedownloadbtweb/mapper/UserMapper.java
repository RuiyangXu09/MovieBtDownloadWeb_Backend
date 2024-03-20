package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户的管理的mapper接口，映射文件名：UserMapper.xml
 */
@Mapper
public interface UserMapper {
    /**
     * 用户注册的sql
     * @param user
     */
    void registerUser(User user);

    /**
     * 修改用户信息的sql
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询用户信息
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
