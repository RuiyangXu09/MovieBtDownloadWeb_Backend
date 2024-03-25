package com.example.moviedownloadbtweb.exception;

import com.example.moviedownloadbtweb.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获异常，以json格式抛出到前端返回，Exception.class意味着捕获所有异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception exception){
        exception.printStackTrace();
        return Result.error("Error." + exception.getMessage());
    }
}
