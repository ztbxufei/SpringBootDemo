package com.springboot.jingfei.SpringBoot.utils;

/**
 * @Author: jingfei
 * @Date: 2019/3/30 16:59
 * @Version 1.0
 */
public class Orange implements Fruit{
    @Override
    public void getName() {
        System.out.println(getClass().getSimpleName());
    }
}
