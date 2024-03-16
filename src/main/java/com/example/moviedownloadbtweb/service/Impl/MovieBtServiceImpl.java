package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.mapper.MovieBtMapper;
import com.example.moviedownloadbtweb.service.MovieBtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 电影各项功能的实现类
 */
@Service
public class MovieBtServiceImpl implements MovieBtService {
    //注入对应的mapper接口
    @Autowired
    MovieBtMapper movieBtMapper;

    /**
     * 新增电影bt，重写service接口中的方法，时间设置为LocalDate.now()，调用mapper接口中对应的insert方法
     * @param movie
     */
    @Override
    public void addMovie(MovieBt movie) {
        movie.setCreateTime(LocalDate.now());
        movie.setUpdateTime(LocalDate.now());
        movieBtMapper.addMovie(movie);
    }

    /**
     * 修改电影信息，重写service接口中的方法，将updateTime设置为LocalDate.now()，调用mapper接口中对应的update方法
     * @param movieBt
     */
    @Override
    public void updateMovie(MovieBt movieBt) {
        movieBt.setUpdateTime(LocalDate.now());
        movieBtMapper.updateMovie(movieBt);
    }

    /**
     * 查询电影信息，重写service接口中的方法，调用mapper接口中对应的select方法
     * @return
     */
    @Override
    public List<MovieBt> listMovie() {
        return movieBtMapper.listMovie();
    }

    /**
     * 删除电影信息，重写service接口中的方法，调用mapper接口中对应的delete方法
     * @param id
     */
    @Override
    public void deleteMovie(Integer id) {
        movieBtMapper.deleteMovie(id);
    }
}
