package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/10

import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MYdata {
    User MYdata();

    //直接返回JSON字符串
    String MYdata2() throws JsonProcessingException;

    String Uname(String changename) throws JsonProcessingException;

    String UA(String A) throws JsonProcessingException;

    String UB(String B) throws JsonProcessingException;

    String UC(String C) throws JsonProcessingException;

    void BITMAP_daka();
}
