package com.example.moviedownloadbtweb.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 过滤器拦截请求
 */
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Autowired
    Jwt jwt;
    /**
     * 初始化过滤器
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 过滤器主方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取请求对象和响应对象
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取请求的url
        String url = req.getRequestURI();
        //判断请求的url中是否包含login，如果有则说明是登录功能，放行
        if (url.contains("login")){
            filterChain.doFilter(req, resp);
            return;
        }
        //获取请求头中的token，都在req中
        String token = req.getHeader("token");
        //判断token是否存在，如果不存在，返回错误信息，使用该工具类判断字符串中是否有值（长度，因为字符串可以为空），取反就是没有长度（值）
        if (!StringUtils.hasLength(token)){
            //没有值，返回错误信息
            Result errorInfo = Result.error("Please Login.");
            //需要手动将result对象转换为json格式响应回前端，fastjson的方法toJSONString会将对象转换为字符串类型的json数据
            String notLoginMsg = JSONObject.toJSONString(errorInfo);
            //通过响应resp中的write方法响应给前端
            resp.getWriter().write(notLoginMsg);
            return;
        }
        //解析token，解析失败，返回错误信息
        try {
            //解析令牌，令牌的解析只要不报错就是成功，相反如果有任何错误就是失败
            jwt.parseJwt(token);
        }catch (Exception e){
            //解析失败，返回未登录错误信息
            e.printStackTrace();
            //没有值，返回错误信息
            Result errorInfo = Result.error("Please Login.");
            //需要手动将result对象转换为json格式响应回前端，fastjson的方法toJSONString会将对象转换为字符串类型的json数据
            String notLogin = JSONObject.toJSONString(errorInfo);
            //通过响应resp中的write方法响应给前端
            resp.getWriter().write(notLogin);
            return;
        }
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     *销毁过滤器
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
