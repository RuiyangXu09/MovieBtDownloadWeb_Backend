package com.example.moviedownloadbtweb.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云上传oss的工具类，使用注解@Component，让这个类可以被IOC容器扫描到
 */
@Component
public class AliyunOss {
    /**
     * Endpoint的url
     */
    private String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    /**
     * 填写Bucket名称
     */
    private String bucketName = "moviebt";

    /**
     * 上传头像
     * @param file
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public String uploadAvatar(MultipartFile file) throws IOException, ClientException {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //需要配置环境变量，配置文档url：https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.41883b2blvwJp8#371a2dd1fetbj
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        //获取前端传递的上传的文件输出流
        InputStream inputStream = file.getInputStream();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        //获取上传文件的源文件名
        String filename = file.getOriginalFilename();
        //subString重新生成一个新文件名UUID格式
        String temp = filename.substring(filename.indexOf("."));
        String newFileName = "user_avatar/" + UUID.randomUUID() + temp;
        //上传文件
        ossClient.putObject(bucketName, newFileName, inputStream);
        //获取文件url
        String url = ossClient.generatePresignedUrl(bucketName, newFileName, new Date()).toString();
        //关闭ossClient
        ossClient.shutdown();

        //返回一个上传文件的路径url
        return url;
    }

    /**
     * 上传电影bt文件
     * @param file
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public String uploadMovieBt(MultipartFile file) throws IOException, ClientException {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //需要配置环境变量，配置文档url：https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.41883b2blvwJp8#371a2dd1fetbj
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        //获取前端传递的上传的文件输出流
        InputStream inputStream = file.getInputStream();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        //获取上传文件的源文件名
        String filename = file.getOriginalFilename();
        //subString重新生成一个新文件名UUID格式
        String temp = filename.substring(filename.indexOf("."));
        String newFileName = "bt_main/" + UUID.randomUUID() + temp;
        //上传文件
        ossClient.putObject(bucketName, newFileName, inputStream);
        //获取文件url
        String url = ossClient.generatePresignedUrl(bucketName, newFileName, new Date()).toString();
        //关闭ossClient
        ossClient.shutdown();

        //返回一个上传文件的路径url
        return url;
    }

    /**
     * 上传电影字幕文件
     * @param file
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public String uploadSubTitle(MultipartFile file) throws IOException, ClientException {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //需要配置环境变量，配置文档url：https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.41883b2blvwJp8#371a2dd1fetbj
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        //获取前端传递的上传的文件输出流
        InputStream inputStream = file.getInputStream();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        //获取上传文件的源文件名
        String filename = file.getOriginalFilename();
        //subString重新生成一个新文件名UUID格式
        String temp = filename.substring(filename.indexOf("."));
        String newFileName = "subTitle/" + UUID.randomUUID() + temp;
        //上传文件
        ossClient.putObject(bucketName, newFileName, inputStream);
        //获取文件url
        String url = ossClient.generatePresignedUrl(bucketName, newFileName, new Date()).toString();
        //关闭ossClient
        ossClient.shutdown();

        //返回一个上传文件的路径url
        return url;
    }

    /**
     * 上传电影封面
     * @param file
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public String uploadMovieCoverImage(MultipartFile file) throws IOException, ClientException {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        //需要配置环境变量，配置文档url：https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.41883b2blvwJp8#371a2dd1fetbj
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        //获取前端传递的上传的文件输出流
        InputStream inputStream = file.getInputStream();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        //获取上传文件的源文件名
        String filename = file.getOriginalFilename();
        //subString重新生成一个新文件名UUID格式
        String temp = filename.substring(filename.indexOf("."));
        String newFileName = "movie_cover_image/" + UUID.randomUUID() + temp;
        //上传文件
        ossClient.putObject(bucketName, newFileName, inputStream);
        //获取文件url
        String url = ossClient.generatePresignedUrl(bucketName, newFileName, new Date()).toString();
        //关闭ossClient
        ossClient.shutdown();

        //返回一个上传文件的路径url
        return url;
    }
}
