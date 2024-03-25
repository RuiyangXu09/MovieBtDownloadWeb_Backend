package com.example.moviedownloadbtweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 开启servlet组件支持
 */
@ServletComponentScan
@SpringBootApplication
public class MovieDownloadBtWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieDownloadBtWebApplication.class, args);
    }

}
