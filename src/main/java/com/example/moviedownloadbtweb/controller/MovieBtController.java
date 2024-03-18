package com.example.moviedownloadbtweb.controller;

import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.service.MovieBtService;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 分页查询电影信息，如果前端未传递page参数，则默认值设为 1，如果前端为传递pageSize参数，则默认值设为 6
     * 使用注解@RequestParam设定默认page参数和pageSize参数
     */
    @GetMapping("pageMovieList")
    public Result pageMovieList(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "6") Integer pageSize){
        //使用pageBean封装获取的总记录数count和返回的限定条数的数据列表，pageMovie接收前端传递的两个参数page和pageSize
        PageBean pageBean = movieBtService.pageMovieList(page, pageSize);
        //将对象pageBean封装到Result中响应给前端即可
        return Result.success(pageBean);
    }
}
