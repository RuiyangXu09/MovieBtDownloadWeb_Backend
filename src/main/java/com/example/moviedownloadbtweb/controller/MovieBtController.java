package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.service.MovieBtService;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电影种子信息controller
 */
@RestController
    @RequestMapping(value = "/api/v1/movie")
public class MovieBtController {
    //注入对应的service层
    @Autowired
    MovieBtService movieBtService;

    /**
     * 新增电影bt信息
     * @param movie
     * @return
     */
    @PostMapping(value = "addMovie")
    public Result addMovie(@RequestBody MovieBt movie){
        movieBtService.addMovie(movie);
        return Result.success();
    }

    /**
     * 修改电影信息
     * @param movieBt
     * @return
     */
    @PutMapping(value = "updateMovie")
    public Result updateMovie(@RequestBody MovieBt movieBt){
        movieBtService.updateMovie(movieBt);
        return Result.success();
    }

    /**
     * 列出电影信息
     */
    @GetMapping(value = "listMovie")
    public Result listMovie(){
        List<MovieBt> movieBtList = movieBtService.listMovie();
        return Result.success(movieBtList);
    }

    /**
     * 删除电影信息
     */
    @DeleteMapping(value = "deleteMovie")
    public Result deleteMovie(Integer id){
        movieBtService.deleteMovie(id);
        return Result.success();
    }

    /**
     * 根据点击次数列出电影信息，由大到小
     */
    @GetMapping(value = "listMovieByClickNumber")
    public Result listMovieByClickNumber(){
        List<MovieBt> countMovie = movieBtService.listMovieByClickNumber();
        return Result.success(countMovie);
    }

    /**
     * 访问该url后，数据库中的count_download次数+1
     */
    @GetMapping(value = "countNumberAddOne")
    public Result countNumberAddOne(String btDownloadUrl){
        movieBtService.countAddOne(btDownloadUrl);
        return Result.success();
    }
}
