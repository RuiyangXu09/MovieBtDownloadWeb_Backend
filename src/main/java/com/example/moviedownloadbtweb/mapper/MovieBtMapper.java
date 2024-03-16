package com.example.moviedownloadbtweb.mapper;

import com.example.moviedownloadbtweb.domain.MovieBt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电影管理
 */
@Mapper
public interface MovieBtMapper {
    /**
     * 新增电影bt信息
     * @param movie
     */
    @Insert("INSERT INTO movie(movie_name, category, create_time, update_time, bt_download_url, subtitle_download_url) " +
            "VALUES (#{movieName}, #{category}, #{createTime}, #{updateTime}, #{btDownloadUrl}, #{subtitleDownloadUrl})")
    void addMovie(MovieBt movie);
}
