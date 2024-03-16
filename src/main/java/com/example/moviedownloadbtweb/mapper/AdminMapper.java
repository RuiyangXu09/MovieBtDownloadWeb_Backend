package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理员管理
 */
@Mapper
public interface AdminMapper {
    /**
     * 查询全部用户信息
     */
    @Select("SELECT * FROM user")
    List<User> listUser();

    /**
     * 删除用户信息 by id
     * @param id
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(Integer id);
}
