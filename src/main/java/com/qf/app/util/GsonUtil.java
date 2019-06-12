package com.qf.app.util;

import com.google.gson.Gson;

/**
 * 封装的GSON工具类.
 * @author 郑大仙丶
 * @date 2019-06-12 15:07
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    // 序列化操作
    public static String toJson(Object obj){
        String json = gson.toJson(obj);
        return json;
    }


    // 反序列化操作
    public static <T> T fromJson(String json,Class<T> clazz){
        T t = gson.fromJson(json, clazz);
        return t;
    }
}
