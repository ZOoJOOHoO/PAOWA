package com.example.power.controller;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.service.MYdataimp;
import com.example.power.service.TOPimp;
import com.example.power.utils.KeepUserInThreadlocal;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("data")
public class data {
    @Autowired
    private use_Mapper use_mapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    TOPimp topimp;
    @Autowired
    MYdataimp mydataimp;

    //个人信息
    @PostMapping ("/Uname")
    public String UName(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        return mydataimp.Uname(paras);
    }
    @PostMapping ("/UA")
    public String A(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        return mydataimp.UA(paras);
    }
    @PostMapping ("/UB")
    public String B(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        return mydataimp.UB(paras);
    }
    @PostMapping ("/UC")
    public String C(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        return mydataimp.UC(paras);
    }
    @PostMapping("/MYdata")
    public String USERdata() throws JsonProcessingException {
        return mydataimp.MYdata2();
    }


    //top
    @GetMapping("/top")
    public List<User> all() throws JsonProcessingException {
        return topimp.TOP(50);
    }
    @GetMapping("/topA")
    public List<User> allA() throws JsonProcessingException {
        return topimp.TOPA(50);
    }
    @GetMapping("/topB")
    public List<User> allB() throws JsonProcessingException {
        return topimp.TOPB(50);
    }
    @GetMapping("/topC")
    public List<User> allC() throws JsonProcessingException {
        return topimp.TOPC(50);
    }
}
