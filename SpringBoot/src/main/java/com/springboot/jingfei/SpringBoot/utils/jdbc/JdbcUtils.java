package com.springboot.jingfei.SpringBoot.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {
    private static final String URL="jdbc:oracle:thin:@121.43.37.25:1521:ORCL";
    private static final String USER="zxtestdb";
    private static final String PASSWORD="testzx$7efSJz5cysN162o";

    public static Connection getInstance() throws Exception {
        long start = System.currentTimeMillis();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        long end = System.currentTimeMillis();
        System.out.println("获取连接使用时间：" + (end - start));
        return conn;
    }

}
