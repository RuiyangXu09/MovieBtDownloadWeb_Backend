package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.MovieBt;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 电影管理的mapper接口，映射文件名：MovieBtMapper.xml
 */
@Mapper
public interface MovieBtMapper {
    /**
     * 新增电影bt信息的sql
     * @param movie
     */
    void addMovie(MovieBt movie);

    /**
     * 根据id修改电影信息的sql
     * @param movieBt
     */
    void updateMovie(MovieBt movieBt);

    /**
     * 删除电影信息的sql，by id
     * @param id
     */
    void deleteMovie(Integer id);

    /**
     * 根据点击次数列出电影信息，由大到小的sql
     * @return
     */
    List<MovieBt> listMovieByClickNumber();

    /**
     * 访问该url后，数据库中的count_download次数+1的sql
     * 在数据库中维护点击次数，并在每次请求时直接更新数据库。利用数据库的事务管理、并发控制和持久化特性来确保数据的准确性和一致性
     * 利用了数据库的事务性和原子性来确保每次点击都被正确记录，并且即使在并发环境下也能保持数据的一致性
     * 同时，数据也被持久化保存在数据库中，不会因为服务重启而丢失
     * @param btDownloadUrl
     */
    void countAddOne(String btDownloadUrl);

    /**
     * 查询数据库中，电影的总记录数的sql
     * @return
     */
    Integer countMovie();

    /**
     * 分页的sql，接收两个参数，一个起始索引，一个每页的数据列表返回数
     * @param indexStart
     * @param pageSize
     * @return
     */
    List<MovieBt> pageMovie(Integer indexStart, Integer pageSize);

    /**
     * 根据id查电影信息
     * @param id
     * @return
     */
    MovieBt getMovieById(Integer id);
}
