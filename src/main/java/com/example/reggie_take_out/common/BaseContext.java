package com.example.reggie_take_out.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static long getCurrentId() {
        return threadLocal.get();
    }
}
