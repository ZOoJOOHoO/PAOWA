package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/11

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.utils.KeepUserInThreadlocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

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
}
