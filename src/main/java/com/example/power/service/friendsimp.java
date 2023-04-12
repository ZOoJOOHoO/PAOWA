package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/11

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.utils.KeepUserInThreadlocal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class friendsimp implements friends{

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    use_Mapper use_mapper;

    @Override
    public String ADDfriend(String FRI_PWID) {
        SetOperations<String, String> Redis_set = stringRedisTemplate.opsForSet();
        User user = use_mapper.select_By_pwid(FRI_PWID);
        if(user==null)
        {
            return "nouser";
        }
        Redis_set.add("PW:FRIENDS:"+KeepUserInThreadlocal.get(),FRI_PWID);
        return "success";
    }

    @Override
    public String Delfriend(String FRI_PWID) {
        SetOperations<String, String> Redis_set = stringRedisTemplate.opsForSet();
        Redis_set.remove("PW:FRIENDS:"+KeepUserInThreadlocal.get(),FRI_PWID);
        return "success";
    }

    @Override
    public List<User> FriendsData() {
        String s = KeepUserInThreadlocal.get();
        SetOperations<String, String> Redis_set = stringRedisTemplate.opsForSet();
        Set<String> members = Redis_set.members("PW:FRIENDS:" + KeepUserInThreadlocal.get());
        ArrayList<String> friendid = new ArrayList<>(members);
        ArrayList<User> users = new ArrayList<>();
        for (String s1 : friendid) {
            User user = use_mapper.select_By_pwid(s1);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<String> FriendsData2() throws JsonProcessingException {
        String s = KeepUserInThreadlocal.get();
        SetOperations<String, String> Redis_set = stringRedisTemplate.opsForSet();
        ValueOperations<String, String> Redis_string = stringRedisTemplate.opsForValue();
        Set<String> members = Redis_set.members("PW:FRIENDS:" + KeepUserInThreadlocal.get());
        ArrayList<String> friendid = new ArrayList<>(members);
        ArrayList<String> users_list = new ArrayList<>();
        for (String s1 : friendid) {
            if(Redis_string.getOperations().hasKey("PW:UserData:" + s1))
            {
                String userJSON = Redis_string.get("PW:UserData:" + s1);
                users_list.add(userJSON);
            }
            else
            {
                User user = use_mapper.select_By_pwid(s1);
                ObjectMapper objectMapper = new ObjectMapper();
                String userJSON = objectMapper.writeValueAsString(user);
                Redis_string.set("PW:UserData:" + s1, userJSON);
                users_list.add(userJSON);
            }
        }
        return users_list;
    }
}
