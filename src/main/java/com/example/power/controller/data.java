package com.example.power.controller;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
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

    @PostMapping ("/Uname")
    public String UName(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        use_mapper.Uname(paras,KeepUserInThreadlocal.get());
        return "success";
    }

    @PostMapping ("/UA")
    public String A(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        use_mapper.updata_A(Integer.parseInt(paras),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        return "success";
    }

    @PostMapping ("/UB")
    public String B(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        use_mapper.updata_B(Integer.parseInt(paras),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        return "success";
    }
    @PostMapping ("/UC")
    public String C(HttpServletRequest request) throws IOException {
        BufferedReader br=request.getReader();
        String paras = br.readLine();
        use_mapper.updata_C(Integer.parseInt(paras),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        return "success";
    }



    @GetMapping("/all")
    public List<User> all() throws JsonProcessingException {
        //return use_mapper.selectTOPA(10);
        return topimp.TOPA(10);
    }

    @GetMapping("/topA")
    public List<User> allA()
    {
        return use_mapper.selectTOPA(50);
    }

    @GetMapping("/topB")
    public List<User> allB()
    {
        return use_mapper.selectTOPB(50);
    }

    @GetMapping("/topC")
    public List<User> allC()
    {
        return use_mapper.selectTOPC(50);
    }
}
