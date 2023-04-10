package com.example.power.utils;
// ^^ @author ZJH111
// ^^ @date 2023/4/6

public class KeepUserInThreadlocal {
    private static final ThreadLocal<String> PWID=new ThreadLocal<>();

    public static void save(String pwid)
    {
        PWID.set(pwid);
    }

    public static String get()
    {
        return PWID.get();
    }

    public static void remove()
    {
        PWID.remove();
    }
}
