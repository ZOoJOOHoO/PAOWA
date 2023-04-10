package com.example.power.service;
// ^^ @author ZJH111
// ^^ @date 2023/4/10

import com.example.power.mapper.use_Mapper;
import com.example.power.pojo.User;
import com.example.power.utils.KeepUserInThreadlocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MYdataimp implements MYdata{
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    use_Mapper use_mapper;

    @Override
    public String Uname(String changename) {
        use_mapper.Uname(changename,KeepUserInThreadlocal.get());
        HashOperations<String, Object, Object> Redishash = stringRedisTemplate.opsForHash();
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"username",changename);
        return "success";
    }

    @Override
    public String UA(String A) {
        use_mapper.updata_A(Integer.parseInt(A),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        HashOperations<String, Object, Object> Redishash = stringRedisTemplate.opsForHash();
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"benchPress",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getBenchPress()));
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"total",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getTotal()));
        return "success";
    }

    @Override
    public String UB(String B) {
        use_mapper.updata_B(Integer.parseInt(B),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        HashOperations<String, Object, Object> Redishash = stringRedisTemplate.opsForHash();
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"pullHard",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getPullHard()));
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"total",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getTotal()));
        return "success";
    }

    @Override
    public String UC(String C) {
        use_mapper.updata_C(Integer.parseInt(C),KeepUserInThreadlocal.get());
        use_mapper.updata_total(KeepUserInThreadlocal.get());
        HashOperations<String, Object, Object> Redishash = stringRedisTemplate.opsForHash();
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"deepSquat",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getDeepSquat()));
        Redishash.put("PW:UserData:"+KeepUserInThreadlocal.get(),"total",String.valueOf(use_mapper.select_By_pwid(KeepUserInThreadlocal.get()).getTotal()));
        return "success";
    }


    @Override
    public User MYdata() {
        HashOperations<String, Object, Object> RedisHASH = stringRedisTemplate.opsForHash();
        //缓存中有
        if(RedisHASH.getOperations().hasKey("PW:UserData:"+KeepUserInThreadlocal.get()))
        {
            //System.out.println("redis");
            //entries用于获取hash类型的键下所有的field和value。它会返回一个Map类型
            Map<Object, Object> UserDataMap = RedisHASH.entries("PW:UserData:" + KeepUserInThreadlocal.get());
            User user = new User();
            user.setUsername((String) UserDataMap.get("username"));
            user.setPWID((String) UserDataMap.get("pwid"));
            user.setBenchPress(Integer.parseInt((String) UserDataMap.get("benchPress")));
            user.setPullHard(Integer.parseInt((String) UserDataMap.get("pullHard")));
            user.setDeepSquat(Integer.parseInt((String) UserDataMap.get("deepSquat")));
            user.setTotal(Integer.parseInt((String) UserDataMap.get("total")));
            return user;
        }else
        {
            //System.out.println("mysql");
            User user = use_mapper.select_By_pwid(KeepUserInThreadlocal.get());
            HashMap<Object, Object> UserMap = new HashMap<>();
            UserMap.put("username",user.getUsername());
            UserMap.put("pwid",user.getPWID());
            UserMap.put("benchPress",String.valueOf(user.getBenchPress()));
            UserMap.put("pullHard",String.valueOf(user.getPullHard()));
            UserMap.put("deepSquat",String.valueOf(user.getDeepSquat()));
            UserMap.put("total",String.valueOf(user.getTotal()));
            RedisHASH.putAll("PW:UserData:"+KeepUserInThreadlocal.get(),UserMap);
            return user;
        }
    }
}
