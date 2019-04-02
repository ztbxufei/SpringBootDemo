package com.springboot.jingfei.SpringBoot.service;

/**
 * @Author: jingfei
 * @Date: 2019/3/8 21:35
 * @Version 1.0
 */
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取接口的所有实现类
 * 需放在spring容器中管理
 */
@Component
public class ServiceLocator implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    /**
     * 获取应用上下文并获取相应的接口实现类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Map<String, TestInterface> getMap(Class clazz) {
        //根据接口类型返回相应的所有bean
        Map<String, TestInterface> map = applicationContext.getBeansOfType(clazz);
        map.forEach((k,v)-> {
            if(k.equals("E")){
                System.out.println(k);
            }
        });
        return map;
    }
}
