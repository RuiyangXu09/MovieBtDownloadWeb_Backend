package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.service.MovieBtService;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
