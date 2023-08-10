package com.example.power;

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.service.MYdataimp;
import com.example.power.service.TOP;
import com.example.power.service.TOPimp;
import com.example.power.service.friendsimp;
import com.example.power.utils.KeepUserInThreadlocal;
import com.example.power.utils.PWIDmake;
import com.example.power.utils.token;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class PowerApplicationTests {

    @Autowired
    private use_Mapper use_mapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    friendsimp friendsimp;

    @Autowired
    PWIDmake pwiDmake;

    @Autowired
    token token;

    @Autowired
    TOPimp toPimp;

    @Autowired
    MYdataimp mYdataimp;

    @Test
    void contextLoads() {
        mYdataimp.BITMAP_daka();
    }

}
