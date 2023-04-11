package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/11

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.utils.KeepUserInThreadlocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
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
}
