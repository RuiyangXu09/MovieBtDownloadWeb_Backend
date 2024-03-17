package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.MovieBt;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 电影管理
 */
@Mapper
public interface MovieBtMapper {
    /**
     * 新增电影bt信息的sql
     * @param movie
     */
    @Insert("INSERT INTO movie(movie_name, category, create_time, update_time, bt_download_url, subtitle_download_url, count_download) " +
            "VALUES (#{movieName}, #{category}, #{createTime}, #{updateTime}, #{btDownloadUrl}, #{subtitleDownloadUrl}, 1)")
    void addMovie(MovieBt movie);

    /**
     * 修改电影信息的sql
     * @param movieBt
     */
    @Update("UPDATE movie SET movie_name = #{movieName}, category = #{category}, update_time = #{updateTime}, bt_download_url = #{btDownloadUrl}, subtitle_download_url = #{subtitleDownloadUrl} " +
            "WHERE id = #{id}")
    void updateMovie(MovieBt movieBt);

    /**
     * 查询电影信息的sql
     * @return
     */
    @Select("SELECT id, movie_name, category, create_time, update_time, bt_download_url, subtitle_download_url, count_download FROM movie")
    List<MovieBt> listMovie();

    /**
     * 删除电影信息的sql，by id
     * @param id
     */
    @Delete("DELETE FROM movie WHERE id = #{id}")
    void deleteMovie(Integer id);

    /**
     * 根据点击次数列出电影信息，由大到小的sql
     * @return
     */
    @Select("SELECT id, movie_name, category, create_time, update_time, bt_download_url, subtitle_download_url FROM movie ORDER BY count_download DESC ")
    List<MovieBt> listMovieByClickNumber();

    /**
     * 访问该url后，数据库中的count_download次数+1的sql
     * 在数据库中维护点击次数，并在每次请求时直接更新数据库。利用数据库的事务管理、并发控制和持久化特性来确保数据的准确性和一致性
     * 利用了数据库的事务性和原子性来确保每次点击都被正确记录，并且即使在并发环境下也能保持数据的一致性
     * 同时，数据也被持久化保存在数据库中，不会因为服务重启而丢失
     */
    @Update("UPDATE movie SET count_download = count_download + 1 WHERE bt_download_url = #{btDownloadUrl}")
    void countAddOne(String btDownloadUrl);
}
