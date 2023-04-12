package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/11

import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface friends {
    String ADDfriend(String FRI_PWID);
    String Delfriend(String FRI_PWID);
    List<User> FriendsData();
    List<String> FriendsData2() throws JsonProcessingException;
}
