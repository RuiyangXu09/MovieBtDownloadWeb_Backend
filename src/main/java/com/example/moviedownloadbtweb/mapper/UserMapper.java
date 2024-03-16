package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 用户的管理
 */
@Mapper
public interface UserMapper {
    /**
     * 用户注册的sql
     * @param user
     */
    @Insert("INSERT INTO user(username, password, avatar_url) VALUES (#{username}, #{password}, #{avatarUrl})")
    void registerUser(User user);

    /**
     * 修改用户信息的sql
     * @param user
     */
    @Update("UPDATE user SET username = #{username}, password = #{password}, avatar_url = #{avatarUrl} WHERE id = #{id}")
    void updateUser(User user);
}
