package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.MovieBt;

/**
 * 电影的各项功能
 */
public interface MovieBtService {
    /**
     * 新增电影bt
     * @param movie
     */
    void addMovie(MovieBt movie);

    /**
     * 修改电影信息
     * @param movieBt
     */
    void updateMovie(MovieBt movieBt);
}
