package com.example.moviedownloadbtweb.service.Impl;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.mapper.MovieBtMapper;
import com.example.moviedownloadbtweb.service.MovieBtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 电影各项功能的实现类
 */
@Service
public class MovieBtServiceImpl implements MovieBtService {
    /**
     * 注入对应的mapper接口
     */
    @Autowired
    private MovieBtMapper movieBtMapper;

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
     * 删除电影信息，重写service接口中的方法，调用mapper接口中对应的delete方法
     * @param id
     */
    @Override
    public void deleteMovie(Integer id) {
        movieBtMapper.deleteMovie(id);
    }

    /**
     * 根据点击次数列出电影信息，由大到小，重写service接口中的方法，调用mapper接口中对应的select方法
     * @return
     */
    @Override
    public List<MovieBt> listMovieByClickNumber() {
        return movieBtMapper.listMovieByClickNumber();
    }

    /**
     * 访问该url后，数据库中的count_download次数+1
     * 同时添加注解@Transactional来启用事务管理，该注解确保操作的原子性
     * 当多个线程同时尝试更新同一个URL的点击次数时，Spring的事务管理器会确保每次只有一个线程能够成功更新数据，从而避免了数据不一致的问题
     * @param btDownloadUrl
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void countAddOne(String btDownloadUrl) {
        movieBtMapper.countAddOne(btDownloadUrl);
    }

    /**
     * 分页查询电影信息，重写service接口中的方法，调用mapper接口中对应的select方法和count方法，返回一个总记录数和一些数据列表，数据列表存入数组并封装到一个对象pageBean中
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean pageMovieList(Integer page, Integer pageSize) {
        //获取总记录数
        Integer count = movieBtMapper.countMovie();
        //indexStart为起始索引，计算起始索引的公式为（页码-1）*每页显示数，返回得到的数据列表存入数组中
        Integer indexStart = (page - 1) * pageSize;
        List<MovieBt> pageMovieList = movieBtMapper.pageMovie(indexStart, pageSize);
        //new一个pageBean对象来封装获取到的总记录数和返回的数据列表，对象中有两个参数，一个count接收总记录数，一个list接收数据列表
        return new PageBean(count, pageMovieList);
    }

    /**
     * 根据id查电影信息
     * @param id
     * @return
     */
    @Override
    public MovieBt getMovieById(Integer id) {
        return movieBtMapper.getMovieById(id);
    }

    /**
     * @param movieName
     * @return
     */
    @Override
    public List<MovieBt> searchMovieByMovieName(String movieName) {
        return movieBtMapper.searchMovieByMovieName(movieName);
    }
}
