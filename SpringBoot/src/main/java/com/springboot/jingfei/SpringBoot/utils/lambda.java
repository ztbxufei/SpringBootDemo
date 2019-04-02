package com.springboot.jingfei.SpringBoot.utils;

import com.springboot.jingfei.SpringBoot.utils.luoxianzhao.LambdaPerson;
import com.springboot.jingfei.SpringBoot.utils.luoxianzhao.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author: jingfei
 * @Date: 2019/3/29 18:50
 * @Version 1.0
 */
public class lambda {
    public static void main(String[] args){
        List<LambdaPerson> javaProgrammers = new ArrayList<LambdaPerson>() {
            {
                add(new LambdaPerson("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new LambdaPerson("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new LambdaPerson("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new LambdaPerson("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new LambdaPerson("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new LambdaPerson("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new LambdaPerson("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new LambdaPerson("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new LambdaPerson("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new LambdaPerson("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<LambdaPerson> phpProgrammers = new ArrayList<LambdaPerson>() {
            {
                add(new LambdaPerson("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new LambdaPerson("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new LambdaPerson("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new LambdaPerson("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new LambdaPerson("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new LambdaPerson("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new LambdaPerson("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new LambdaPerson("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new LambdaPerson("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new LambdaPerson("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        // lambda表达式遍历集合
        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s;", p.getFirstName(),p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s;", p.getFirstName(),p.getLastName()));
        System.out.println();

        // 给每个程序员加薪5%
        Consumer<LambdaPerson> giveRaise = e -> e.setSalary(e.getSalary() * 105 / 100);
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        // 先排序后输出
        System.out.println("先排序后输出: ");
        javaProgrammers.stream().sorted(Comparator.comparing(LambdaPerson::getFirstName)).forEach((p) ->{System.out.printf("%s %s的薪资: %s;", p.getFirstName(),p.getLastName(), p.getSalary());System.out.println();});

        // 定义过滤器
        System.out.println("定义过滤器: ");
        Predicate<LambdaPerson> agePredicate = (p) -> p.getAge() > 24;
        Predicate<LambdaPerson> salaryPredicate = (p) -> p.getSalary() > 1000;
        Predicate<LambdaPerson> genderPredicate = (p) -> "female".equals(p.getGender());
        javaProgrammers.stream().filter(agePredicate).filter(salaryPredicate).filter(genderPredicate).collect(Collectors.toList()).forEach(System.out::println);

        // 限制结果集的个数
        System.out.println("限制结果集的个数: ");
        javaProgrammers.stream().limit(3).forEach(System.out::println);

        // 获取结果集
        System.out.println("获取结果集: ");
        IntSummaryStatistics iss = javaProgrammers.stream().collect(Collectors.summarizingInt(LambdaPerson::getSalary));
        System.out.println(iss);

        // 获取所有的姓名作为集合
        System.out.println("获取所有的姓名作为集合: ");
        javaProgrammers.stream().map(LambdaPerson::getFirstName).collect(Collectors.toList()).forEach(System.out::println);

        // 求工资的最大值和最小值
        System.out.println("求工资的最大值和最小值: ");
        System.out.println(javaProgrammers.stream().max(Comparator.comparing(LambdaPerson::getSalary)).get());
        System.out.println(javaProgrammers.stream().min(Comparator.comparing(LambdaPerson::getSalary)).get());
    }
}
