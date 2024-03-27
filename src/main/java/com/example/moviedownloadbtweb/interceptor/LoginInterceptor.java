package com.example.moviedownloadbtweb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.moviedownloadbtweb.utils.Jwt;
import com.example.moviedownloadbtweb.utils.Result;
import com.example.moviedownloadbtweb.utils.ThreadLocalUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 拦截器功能，拦截所有token不合法，未持有token的请求
 * 实现spring组件中提供的接口HandlerInterceptor中的方法
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 注入令牌
     */
    @Autowired
    private Jwt jwt;

    /**
     * 让目标资源运行前执行该方法，需要返回值为true则放行，拦截器校验应使用在第一个preHandle方法中，因为只有这个是运行在controller方法之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先获取访问的url
        String url = request.getRequestURI();
        //判断是否包含login字段，包含就放行，return true;
        if (url.contains("login")){
            //放行
            return true;
        } else if (url.contains("register")) {
            //判断是否包含register字段，包含就放行，return true;
            return true;
        } else {
            //其他路径都要检测是否包含token
            String token = request.getHeader("token");
            //判断token是否存在，如果不存在，返回错误信息，使用该工具类判断字符串中是否有值（长度，因为字符串可以为空），取反就是没有长度（值）
            if (!StringUtils.hasLength(token)){
                Result errorInfo = Result.error("Authorization Failed.");
                //将result类型的error信息转换为json字符串
                String notLoginMsg = JSONObject.toJSONString(errorInfo);
                //信息返回给前端
                response.getWriter().write(notLoginMsg);
                //未获得token，return false不放行
                return false;
            }

            //解析token，解析失败，返回错误信息
            try {
                //解析token得到的数据赋值给map类型的变量claims
                Map<String, Object> claims = jwt.parseJwt(token);
                //启用ThreadLocal，将根据不同token解析得到的键值对变量claims设置到线程中
                ThreadLocalUtils.set(claims);
            }catch (Exception e){
                e.printStackTrace();
                Result errorInfo = Result.error("Authorization Failed.");
                //将result类型的error信息转换为json字符串
                String notLoginMsg = JSONObject.toJSONString(errorInfo);
                //信息返回给前端，设置http响应码为401
                response.setStatus(401);
                response.getWriter().write(notLoginMsg);
                //token错误，return false不放行
                return false;
            }
            //放行
            return true;
        }
    }

    /**
     * 在view层渲染完毕后，运行该方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal对象的数据，防止内存泄露
        ThreadLocalUtils.remove();
    }
}
