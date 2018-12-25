package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.Test;
import com.springboot.jingfei.SpringBoot.utils.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {

    private final static int count = 10000;

    private final static int threadNum = 10;

    public void startInsertData() {
        for (int i = 0; i < threadNum; i++) {
            int start = i * count / threadNum;
            int end = (i+1) * count / threadNum -1;
            new Thread(() -> insertData(start, end)).start();
        }
    }

    public int insertData(int start, int end) {
        try {
            long startTime = System.currentTimeMillis();
            Connection connection = JdbcUtils.getInstance();
            Statement st;
            long start1 = System.currentTimeMillis();
            st = connection.createStatement();
            long start2 = System.currentTimeMillis();
            System.out.println("创建statement所用时间："+ (start2-start1));
            List<Test> list = new ArrayList<>();
            String prifix = "insert all ";
            String sql = "";
            int j = 0;
            for (int i = start; i <= end; i++,j++) {
                Test test = new Test();
                test.setName("test" + i);
                test.setSex(String.valueOf(new Random().nextInt(2)));
                test.setName1("test" + i);
                test.setSex1(String.valueOf(new Random().nextInt(2)));
                test.setName2("test" + i);
                test.setSex2(String.valueOf(new Random().nextInt(2)));
                test.setName3("test" + i);
                test.setSex3(String.valueOf(new Random().nextInt(2)));
                test.setName4("test" + i);
                test.setSex4(String.valueOf(new Random().nextInt(2)));
                test.setName5("test" + i);
                test.setSex5(String.valueOf(new Random().nextInt(2)));
                list.add(test);
                sql += "into test1 values (test_sequence.nextval," + test.getName() + "," + test.getSex() + "," + test.getName1() + "," + test.getSex1()
                        + "," + test.getName2() + "," + test.getSex2() + "," + test.getName3() + "," + test.getSex3() + "," + test.getName4() + "," + test.getSex4()
                        + "," + test.getName5() + "," + test.getSex5() + ")";
                if(j >= 500){
                    String finalString = "select 1 from dual";
                    sql = prifix + sql + finalString;
                    st.addBatch(sql);
                    st.executeBatch();
                    connection.commit();
                    sql = "";
                    j = 0;
                }
            }
            String finalString = "select 1 from dual";
            sql = prifix + sql + finalString;
            st.addBatch(sql);
            st.executeBatch();
            connection.commit();
            st.close();
            connection.close();
            long endTime = System.currentTimeMillis();
            System.out.println("使用时间：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
