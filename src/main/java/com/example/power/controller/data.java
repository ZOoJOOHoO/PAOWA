package com.example.power.controller;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.mapper.use_Mapper;
import com.example.power.service.MYdataimp;
import com.example.power.service.TOPimp;
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

    @GetMapping("/sign")
    public String sign()
    {
        mydataimp.BITMAP_daka();
        return "success";
    }


    //top
    @GetMapping("/top")
    public List<String> all() throws JsonProcessingException {
        return topimp.TOP2(50);
    }
    @GetMapping("/topA")
    public List<String> allA() throws JsonProcessingException {
        return topimp.TOPA2(50);
    }
    @GetMapping("/topB")
    public List<String> allB() throws JsonProcessingException {
        return topimp.TOPB2(50);
    }
    @GetMapping("/topC")
    public List<String> allC() throws JsonProcessingException {
        return topimp.TOPC2(50);
    }
}
