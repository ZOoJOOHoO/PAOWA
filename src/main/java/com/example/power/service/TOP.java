package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/8

import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TOP {
    List<User> TOPA(int number) throws JsonProcessingException;
    List<User> TOPB(int number);
    List<User> TOPC(int number);
    List<User> TOP(int number);
}
