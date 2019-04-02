package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jingfei
 * @Date: 2019/3/27 11:15
 * @Version 1.0
 */
public class InnerClass {

    public void setClass(){

    }



    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("jingfei");
        list.add("zhangfei");
        InnerClass innerClass = new InnerClass();
        // lambda表达式遍历list之使用double冒号运算符，double冒号运算前面是引用对象后面是引用内容
        list.forEach(innerClass::output1);
        list.forEach(InnerClass::output);
        // lambda表达式遍历list之使用普通方式
        list.forEach(object->System.out.println(object));
        Map map = new HashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        // 匿名内部类
        map.forEach((k,v)->{
            if(StringUtils.isNotEmpty(k.toString())){
                System.out.println(v);
            }
        });
    }

    public static void output(String s){
        System.out.println(s);
    }

    public void output1(String s){
        System.out.println(s);
    }
}
