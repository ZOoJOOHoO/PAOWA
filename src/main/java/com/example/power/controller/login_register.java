package com.example.power.controller;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.utils.PWIDmake;
import com.example.power.utils.token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("Login_Register")
@RestController
public class login_register {

    @Autowired
    private PWIDmake pwiDmake;

    @Autowired
    private use_Mapper use_mapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private token token;


    @PostMapping("/register")
    public String register(@RequestBody String body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(body, User.class);
        //System.out.println(user);
        String s = pwiDmake.PWImake();
        use_mapper.JOIN_PW(s,user.getUsername(),user.getPassword());
        return s;
    }

    @PostMapping("/login")
    public String login(@RequestBody String body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(body, User.class);
        User user1 = use_mapper.select_login(user);

        if(user1==null)
        {
            return "nouser";
        }
        //生成token
        String generateToken = token.generateToken(12);
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        //将<token,用户id>存入redis   设置30分钟有效
        stringStringValueOperations.set("login:token:"+generateToken,user1.getPWID(),5, TimeUnit.MINUTES);
        return generateToken;
    }

    @PostMapping("/name")
    public String getusername(HttpServletRequest httpServletRequest) throws IOException {
        String PWID = httpServletRequest.getReader().readLine();
        User user = use_mapper.select_By_pwid(PWID);
        return user.getUsername();
    }


}
