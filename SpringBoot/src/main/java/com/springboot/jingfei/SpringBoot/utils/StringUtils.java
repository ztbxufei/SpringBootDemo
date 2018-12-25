package com.springboot.jingfei.SpringBoot.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
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
                resultMap.put(field.getName(), field.get(object));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
}
