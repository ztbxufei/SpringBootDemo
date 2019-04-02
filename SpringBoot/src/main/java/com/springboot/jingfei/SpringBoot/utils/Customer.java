package com.springboot.jingfei.SpringBoot.utils;

/**
 * @Author: jingfei
 * @Date: 2019/3/30 17:00
 * @Version 1.0
 */
public class Customer {

    private Fruit fruit;

    public Customer(Fruit fruit){
        this.fruit = fruit;
    }

    public void getFruit(){
        fruit.getName();
    }

}
