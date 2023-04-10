package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/8

//存排名 中间用redis价格缓存
import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TOPimp implements TOP{
    @Autowired
    private use_Mapper use_mapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<User> TOPA(int number) throws JsonProcessingException {
        ZSetOperations<String, String> Reddis_ZSET = stringRedisTemplate.opsForZSet();
        ValueOperations<String, String> Redis_String = stringRedisTemplate.opsForValue();

        if(stringRedisTemplate.hasKey("TOPA"))
        {
            ArrayList<User> userArrayList = new ArrayList<>();
            Set<String> topa = Reddis_ZSET.reverseRange("TOPA", 0, -1);
            ArrayList<String> topa_list = new ArrayList<>(topa);
            for (String each : topa_list) {
                String user_json = Redis_String.get("TOPA:"+each);
                User user = objectMapper.readValue(user_json, User.class);
                userArrayList.add(user);
            }
            return userArrayList;
        }

        List<User> users = use_mapper.selectTOPA(number);

        for (User user : users) {
            String s = objectMapper.writeValueAsString(user);
            Redis_String.set("TOPA:"+user.getPWID(),s);
            Reddis_ZSET.add("TOPA",user.getPWID(),user.getBenchPress());
        }

        return null;
    }

    @Override
    public List<User> TOPB(int number) {
        List<User> users = use_mapper.selectTOPB(number);
        return null;
    }

    @Override
    public List<User> TOPC(int number) {
        List<User> users = use_mapper.selectTOPC(number);
        return null;
    }

    @Override
    public List<User> TOP(int number) {
        List<User> users = use_mapper.selectTOP(number);
        return null;
    }
}
