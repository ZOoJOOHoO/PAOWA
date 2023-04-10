package com.example.power.utils;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.mapper.use_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PWIDmake {
    @Autowired
    private use_Mapper use_mapper;

    public String PWImake()
    {
        int id = use_mapper.PW_MAXID();
        String PWID="PW"+id;
        return  PWID;
    }
}
