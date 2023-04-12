package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/8

import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TOP {
    List<User> TOPA(int number) throws JsonProcessingException;
    List<User> TOPB(int number) throws JsonProcessingException;
    List<User> TOPC(int number) throws JsonProcessingException;
    List<User> TOP(int number) throws JsonProcessingException;

    List<String> TOPA2(int number) throws JsonProcessingException;
    List<String> TOPB2(int number) throws JsonProcessingException;
    List<String> TOPC2(int number) throws JsonProcessingException;
    List<String> TOP2(int number) throws JsonProcessingException;
}
