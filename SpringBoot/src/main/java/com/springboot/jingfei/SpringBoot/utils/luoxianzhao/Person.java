package com.springboot.jingfei.SpringBoot.utils.luoxianzhao;

/**
 * @Author: jingfei
 * @Date: 2019/3/30 14:43
 * @Version 1.0
 */
public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }
}
