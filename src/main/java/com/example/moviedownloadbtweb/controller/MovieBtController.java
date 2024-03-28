package com.example.moviedownloadbtweb.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.moviedownloadbtweb.domain.MovieBt;
import com.example.moviedownloadbtweb.domain.PageBean;
import com.example.moviedownloadbtweb.service.MovieBtService;
import com.example.moviedownloadbtweb.utils.AliyunOss;
import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 电影种子信息controller
 */
@RestController
    @RequestMapping(value = "/api/v1/movie")
public class MovieBtController {
    /**
     *注入MovieBtService的bean
     */
    @Autowired
    private MovieBtService movieBtService;
    /**
     * 注入阿里云oss的bean
     */
    @Autowired
    private AliyunOss aliyunOss;

    /**
     * 新增电影bt信息
     * @param movie
     * @return
     */
    @PostMapping(value = "addMovie")
    public Result addMovie(@RequestBody MovieBt movie){
        //验证电影名是否为空
        if (movie.getMovieName() != null && !movie.getMovieName().isEmpty()){
            movieBtService.addMovie(movie);
            return Result.success();
        }else {
            return Result.error("Movie Name could not be empty.");
        }
    }

    /**
     * 修改电影信息
     * @param movieBt
     * @return
     */
    @PutMapping(value = "updateMovie")
    public Result updateMovie(@RequestBody MovieBt movieBt){
        //验证电影名是否为空
        if (movieBt.getMovieName() != null && !movieBt.getMovieName().isEmpty()){
            movieBtService.updateMovie(movieBt);
            return Result.success();
        }else {
            return Result.error("Movie Name could not be empty.");
        }
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
    public Result<List<MovieBt>> listMovieByClickNumber(){
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
    public Result<PageBean<MovieBt>> pageMovieList(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "6") Integer pageSize){
        //使用pageBean封装获取的总记录数count和返回的限定条数的数据列表，pageMovie接收前端传递的两个参数page和pageSize
        PageBean<MovieBt> pageBean = movieBtService.pageMovieList(page, pageSize);
        //将对象pageBean封装到Result中响应给前端即可
        return Result.success(pageBean);
    }

    /**
     * 上传电影，文件上传到oss中后，获取url，传递到前端表单中，前端表单一起将参数传入对应接口放入数据库中
     * @param btDownloadUrl
     * @return
     * @throws IOException
     * @throws ClientException
     */
    @PostMapping(value = "uploadMovie")
    public Result uploadMovie(@RequestBody MultipartFile btDownloadUrl) throws IOException, ClientException {
        String url = aliyunOss.uploadMovieBt(btDownloadUrl);

        return Result.success(url);
    }

    /**
     * 上传字幕，文件上传到oss中后，获取url，传递到前端表单中，前端表单一起将参数传入对应接口放入数据库中
     * @param subtitleDownloadUrl
     * @return
     * @throws IOException
     * @throws ClientException
     */
    @PostMapping(value = "uploadSubTitle")
    public Result uploadSubTitle(@RequestBody MultipartFile subtitleDownloadUrl) throws IOException, ClientException {
        String url = aliyunOss.uploadSubTitle(subtitleDownloadUrl);

        return Result.success(url);
    }

    /**
     * 根据id查电影信息
     * @param id
     * @return
     */
    @GetMapping(value = "getMovieById")
    public Result<MovieBt> getMovieById(Integer id){
        MovieBt movieBt = movieBtService.getMovieById(id);
        //返回前端一个MovieBt对象，封装在result中
        return Result.success(movieBt);
    }

    /**
     * 查询电影，因为模糊查询有多条数据，所以要用一个list来接收
     * @param movieName
     * @return
     */
    @GetMapping(value = "searchMovieByMovieName")
    public Result<List<MovieBt>> searchMovieByMovieName(String movieName){
        List<MovieBt> movieList = movieBtService.searchMovieByMovieName(movieName);
        return Result.success(movieList);
    }
}
