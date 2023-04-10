package com.example.power.utils;
// ^^ @author ZJH111
// ^^ @date 2023/4/6

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

//设置拦截器
public class LoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //查看前端是否携带token
        if(Objects.equals(token, ""))
        {
            response.getWriter().write("notoken");
            return false;
        }
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String PWID = stringStringValueOperations.get("login:token:"+token);//获取redis存的value值 PWID
        if(PWID==null)
        {
            response.getWriter().write("outoftime");
            return false;
        }
        //保存用户信息到Threadlocal
        KeepUserInThreadlocal.save(PWID);

        //刷新token有效期 30分钟
        stringRedisTemplate.expire("login:token:"+token,5, TimeUnit.MINUTES);

        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
