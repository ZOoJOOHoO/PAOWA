package com.example.power.controller;
// ^^ @author ZJH111
// ^^ @date 2023/4/11

import com.example.power.pojo.User;
import com.example.power.service.friendsimp;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("friends")
public class friends {
    @Autowired
    friendsimp friendsimp;

    @PostMapping("/add")
    public String add(HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader reader = httpServletRequest.getReader();
        String pwid = reader.readLine();
        return friendsimp.ADDfriend(pwid);
    }

    @PostMapping("/del")
    public String del(@RequestBody String body) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user = objectMapper.readValue(body, User.class);
        return friendsimp.Delfriend(user.getPWID());
    }

    @PostMapping("/frienddata")
    public List<User> data() {
        return friendsimp.FriendsData();
    }
}
