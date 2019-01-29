package com.springboot.jingfei.SpringBoot.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    /**
     * 将类转换为Map
     * @param object
     * @return
     */
    public static Map<String,Object> convertObjToMap(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                resultMap.put(field.getName().toLowerCase(), field.get(object));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 将Map转换为实体类
     * @param map,clazz
     * @return
     */
    public static <T> Object convertMapToObj(Map map, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Object obj = new Object();
        try {
            obj = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName().toUpperCase();
                if(!map.containsKey(key)){
                    key = key.toLowerCase();
                    if(!map.containsKey(key)){
                        key = field.getName();
                    }
                }
                field.set(obj, map.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
    public static boolean isNotEmpty(String str){
        if(str == null || str.equals("") || str.length() == 1){
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str){
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(Collection e){
        if(e == null || e.size() == 0){
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Collection e){
        return !isNotEmpty(e);
    }
}
