package com.example.power.config;
// ^^ @author ZJH111
// ^^ @date 2023/4/6

import com.example.power.utils.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webconfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    //添加拦截器 将写好的拦截器被识别到
    //注意html资源也会被拦截
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
                //排除不需要拦截的地址
                .excludePathPatterns(
                        "/Login_Register/**",
                        "/data/top",
                        "/data/topA",
                        "/data/topB",
                        "/data/topC",

                        //对webapp内的资源进行放行
                        "/login.html",
                        "/js/**",
                        "/element-ui/**"
                );
    }
}
