package com.springboot.jingfei.SpringBoot.utils;

import com.springboot.jingfei.SpringBoot.utils.luoxianzhao.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: jingfei
 * @Date: 2019/3/29 18:51
 * @Version 1.0
 */
public class stream {
    public static void main(String[] args) {
        // 输出0到10的整数
        //IntStream.range(0,10).forEach(System.out::println);

        // 创建一个1-100的list，输出list的大小
        List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        System.out.println(list.stream().count());

        // 计算列表中元素的平均数
        Double average = list.stream().collect(Collectors.averagingInt(item -> item));
        System.out.println(average);

        // 对列表中元素进行统计
        IntSummaryStatistics iss = list.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println(iss); // 输出结果为{count=99, sum=4950, min=1, average=50.000000, max=99}，可以使用get方法获取各个属性

        // 根据list生成map
        Map<Integer, Integer> map = list.stream().collect(Collectors.toMap(p -> p, q -> q * 3));
        System.out.println(map.get(1));

        // 获取列表中的最大值
        List<Integer> randomList = new Random().ints(-100, 100).boxed().limit(200).collect(Collectors.toList());
        Optional<Integer> max = randomList.stream().reduce(Math::max);
        max.ifPresent(System.out::println);

        // 从一堆姓名列表中找出以字母“C”开头的姓名
        String[] names = {"Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell", "Rose Thompson", "Rachel Barnes", "Eugene Ramirez", "Earl Flores", "Janice Reed", "Sarah Miller", "Patricia Kelly", "Carl Hall", "Craig Wright", "Martha Phillips", "Thomas Howard", "Steve Martinez", "Diana Bailey", "Kathleen Hughes", "Russell Anderson", "Theresa Perry"};
        List<String> nameList = Arrays.asList(names).stream().filter(name -> name.startsWith("C")).collect(Collectors.toList());
        System.out.println(nameList);

        // 对names中所有姓名进行大写、排序再输出
        Arrays.asList(names).stream().map(String::toUpperCase).sorted().forEach(System.out::println);

        // 对list<Person>按年龄进行分组
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personList.add(new Person("jingfei"+new Random().nextInt(100), new Random().nextInt(25)));
        }
        Map<Integer, List<Person>> mapList = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(mapList);

        // 统计personList的平均年龄
        Double averagePerson = personList.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println(averagePerson);

        // 统计personList的年纪
        IntSummaryStatistics issPersonList = personList.stream().collect(Collectors.summarizingInt(item->item.getAge()));
        System.out.println(issPersonList);
    }
}
