package com.springboot.jingfei.SpringBoot.utils.luoxianzhao;

/**
 * @Author: jingfei
 * @Date: 2019/3/31 18:05
 * @Version 1.0
 */
public class LambdaPerson {
    private String firstName, lastName, job, gender;
    private int salary, age;

    public LambdaPerson(String firstName, String lastName, String job,
                  String gender, int age, int salary)       {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", age=" + age + "}";
    }
}
