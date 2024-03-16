package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.MovieBt;

import java.util.List;

/**
 * 电影的各项功能
 */
public interface MovieBtService {
    /**
     * 新增电影信息
     * @param movie
     */
    void addMovie(MovieBt movie);

    /**
     * 修改电影信息
     * @param movieBt
     */
    void updateMovie(MovieBt movieBt);

    /**
     * 查询电影信息
     * @return
     */
    List<MovieBt> listMovie();

    /**
     * 删除电影信息
     * @param id
     */
    void deleteMovie(Integer id);
}
