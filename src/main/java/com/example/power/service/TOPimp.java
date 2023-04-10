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
        if(stringRedisTemplate.hasKey("PW:TOPA"))
        {
            ArrayList<User> userArrayList = new ArrayList<>();
            Set<String> topa = Reddis_ZSET.reverseRange("PW:TOPA", 0, -1);
            ArrayList<String> topa_list = new ArrayList<>(topa);
            for (String each : topa_list) {
                String user_json = Redis_String.get("PW:TOPA:"+each);
                User user = objectMapper.readValue(user_json, User.class);
                userArrayList.add(user);
            }
            return userArrayList;
        }
        List<User> users = use_mapper.selectTOPA(number);
        for (User user : users) {
            String s = objectMapper.writeValueAsString(user);
            Redis_String.set("PW:TOPA:"+user.getPWID(),s);
            Reddis_ZSET.add("PW:TOPA",user.getPWID(),user.getBenchPress());
        }
        return users;
    }

    @Override
    public List<User> TOPB(int number) throws JsonProcessingException {
        ZSetOperations<String, String> Reddis_ZSET = stringRedisTemplate.opsForZSet();
        ValueOperations<String, String> Redis_String = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey("PW:TOPB"))
        {
            ArrayList<User> userArrayList = new ArrayList<>();
            Set<String> topb = Reddis_ZSET.reverseRange("PW:TOPB", 0, -1);
            ArrayList<String> topb_list = new ArrayList<>(topb);
            for (String each : topb_list) {
                String user_json = Redis_String.get("PW:TOPB:"+each);
                User user = objectMapper.readValue(user_json, User.class);
                userArrayList.add(user);
            }
            return userArrayList;
        }
        List<User> users = use_mapper.selectTOPB(number);
        for (User user : users) {
            String s = objectMapper.writeValueAsString(user);
            Redis_String.set("PW:TOPB:"+user.getPWID(),s);
            Reddis_ZSET.add("PW:TOPB",user.getPWID(),user.getPullHard());
        }
        return users;
    }

    @Override
    public List<User> TOPC(int number) throws JsonProcessingException {
        ZSetOperations<String, String> Reddis_ZSET = stringRedisTemplate.opsForZSet();
        ValueOperations<String, String> Redis_String = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey("PW:TOPC"))
        {
            ArrayList<User> userArrayList = new ArrayList<>();
            Set<String> topc = Reddis_ZSET.reverseRange("PW:TOPC", 0, -1);
            ArrayList<String> topc_list = new ArrayList<>(topc);
            for (String each : topc_list) {
                String user_json = Redis_String.get("PW:TOPC:"+each);
                User user = objectMapper.readValue(user_json, User.class);
                userArrayList.add(user);
            }
            return userArrayList;
        }
        List<User> users = use_mapper.selectTOPC(number);
        for (User user : users) {
            String s = objectMapper.writeValueAsString(user);
            Redis_String.set("PW:TOPC:"+user.getPWID(),s);
            Reddis_ZSET.add("PW:TOPC",user.getPWID(),user.getDeepSquat());
        }
        return users;
    }

    @Override
    public List<User> TOP(int number) throws JsonProcessingException {
        ZSetOperations<String, String> Reddis_ZSET = stringRedisTemplate.opsForZSet();
        ValueOperations<String, String> Redis_String = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey("PW:TOPALL"))
        {
            ArrayList<User> userArrayList = new ArrayList<>();
            Set<String> top = Reddis_ZSET.reverseRange("PW:TOPALL", 0, -1);
            ArrayList<String> top_list = new ArrayList<>(top);
            for (String each : top_list) {
                String user_json = Redis_String.get("PW:TOPALL:"+each);
                User user = objectMapper.readValue(user_json, User.class);
                userArrayList.add(user);
            }
            return userArrayList;
        }
        List<User> users = use_mapper.selectTOP(number);
        for (User user : users) {
            String s = objectMapper.writeValueAsString(user);
            Redis_String.set("PW:TOPALL:"+user.getPWID(),s);
            Reddis_ZSET.add("PW:TOPALL",user.getPWID(),user.getTotal());
        }
        return users;
    }
}
