package com.example.moviedownloadbtweb.service;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.domain.PageBean;

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
     * 删除电影信息
     * @param id
     */
    void deleteMovie(Integer id);

    /**
     * 根据点击次数列出电影信息，由大到小
     * @return
     */
    List<MovieBt> listMovieByClickNumber();

    /**
     * 访问该url后，数据库中的count_download次数+1
     */
    void countAddOne(String btDownloadUrl);

    /**
     * 分页查询电影信息的功能
     * @param page
     * @param pageSize
     * @return
     */
    PageBean pageMovieList(Integer page, Integer pageSize);
}
