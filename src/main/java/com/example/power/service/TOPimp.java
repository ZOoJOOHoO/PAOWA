package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/8

//存排名 中间用redis价格缓存
import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        stringRedisTemplate.expire("PW:TOPA",5, TimeUnit.MINUTES);
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
        stringRedisTemplate.expire("PW:TOPB",5, TimeUnit.MINUTES);
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
        stringRedisTemplate.expire("PW:TOPC",5, TimeUnit.MINUTES);
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
        stringRedisTemplate.expire("PW:TOPALL",5, TimeUnit.MINUTES);
        return users;


    }
    @Override
    public List<String> TOPA2(int number) throws JsonProcessingException {
        ListOperations<String, String> redis_list = stringRedisTemplate.opsForList();
        if(redis_list.getOperations().hasKey("PW:TOPALIST"))
        {
            List<String> list_userJSON = redis_list.range("PW:TOPALIST", 0, -1);
            return list_userJSON;
        }
        else
        {
            List<User> users = use_mapper.selectTOPA(number);
            ObjectMapper objectMapper1 = new ObjectMapper();
            for (User user : users) {
                String s = objectMapper1.writeValueAsString(user);
                redis_list.rightPush("PW:TOPALIST",s);
            }
            stringRedisTemplate.expire("PW:TOPALIST",5,TimeUnit.MINUTES);
            List<String> list_userJSON = redis_list.range("PW:TOPALIST", 0, -1);
            return list_userJSON;
        }
    }

    @Override
    public List<String> TOPB2(int number) throws JsonProcessingException {
        ListOperations<String, String> redis_list = stringRedisTemplate.opsForList();
        if(redis_list.getOperations().hasKey("PW:TOPBLIST"))
        {
            List<String> list_userJSON = redis_list.range("PW:TOPBLIST", 0, -1);
            return list_userJSON;
        }
        else
        {
            List<User> users = use_mapper.selectTOPB(number);
            ObjectMapper objectMapper1 = new ObjectMapper();
            for (User user : users) {
                String s = objectMapper1.writeValueAsString(user);
                redis_list.rightPush("PW:TOPBLIST",s);
            }
            stringRedisTemplate.expire("PW:TOPBLIST",5,TimeUnit.MINUTES);
            List<String> list_userJSON = redis_list.range("PW:TOPBLIST", 0, -1);
            return list_userJSON;
        }
    }

    @Override
    public List<String> TOPC2(int number) throws JsonProcessingException {
        ListOperations<String, String> redis_list = stringRedisTemplate.opsForList();
        if(redis_list.getOperations().hasKey("PW:TOPCLIST"))
        {
            List<String> list_userJSON = redis_list.range("PW:TOPCLIST", 0, -1);
            return list_userJSON;
        }
        else
        {
            List<User> users = use_mapper.selectTOPC(number);
            ObjectMapper objectMapper1 = new ObjectMapper();
            for (User user : users)
            {
                String s = objectMapper1.writeValueAsString(user);
                redis_list.rightPush("PW:TOPCLIST",s);
            }
            stringRedisTemplate.expire("PW:TOPCLIST",5,TimeUnit.MINUTES);
            List<String> list_userJSON = redis_list.range("PW:TOPCLIST", 0, -1);
            return list_userJSON;
        }
    }

    @Override
    public List<String> TOP2(int number) throws JsonProcessingException {
        ListOperations<String, String> redis_list = stringRedisTemplate.opsForList();
        if(redis_list.getOperations().hasKey("PW:TOPLIST"))
        {
            List<String> list_userJSON = redis_list.range("PW:TOPLIST", 0, -1);
            return list_userJSON;
        }
        else
        {
            List<User> users = use_mapper.selectTOP(number);
            ObjectMapper objectMapper1 = new ObjectMapper();
            for (User user : users)
            {
                String s = objectMapper1.writeValueAsString(user);
                redis_list.rightPush("PW:TOPLIST",s);
            }
            stringRedisTemplate.expire("PW:TOPLIST",5,TimeUnit.MINUTES);
            List<String> list_userJSON = redis_list.range("PW:TOPLIST", 0, -1);
            return list_userJSON;
        }
    }
}
