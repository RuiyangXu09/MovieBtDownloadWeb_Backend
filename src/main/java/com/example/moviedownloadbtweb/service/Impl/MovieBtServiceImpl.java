package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.mapper.MovieBtMapper;
import com.example.moviedownloadbtweb.service.MovieBtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 电影各项功能的实现类
 */
@Service
public class MovieBtServiceImpl implements MovieBtService {
    //注入对应的mapper接口
    @Autowired
    MovieBtMapper movieBtMapper;

    /**
     * 新增电影bt，重写mapper接口中的方法，时间设置为LocalDate.now()
     * @param movie
     */
    @Override
    public void addMovie(MovieBt movie) {
        movie.setCreateTime(LocalDate.now());
        movie.setUpdateTime(LocalDate.now());
        movieBtMapper.addMovie(movie);
    }
}
