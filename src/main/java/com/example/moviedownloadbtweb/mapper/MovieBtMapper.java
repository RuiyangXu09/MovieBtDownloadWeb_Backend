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
    @Insert("INSERT INTO movie(movie_name, category, create_time, update_time, bt_download_url, subtitle_download_url) " +
            "VALUES (#{movieName}, #{category}, #{createTime}, #{updateTime}, #{btDownloadUrl}, #{subtitleDownloadUrl})")
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
    @Select("SELECT id, movie_name, category, update_time, bt_download_url, subtitle_download_url FROM movie")
    List<MovieBt> listMovie();

    /**
     * 删除电影信息的sql，by id
     * @param id
     */
    @Delete("DELETE FROM movie WHERE id = #{id}")
    void deleteMovie(Integer id);
}
