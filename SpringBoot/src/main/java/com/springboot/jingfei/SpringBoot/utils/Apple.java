package com.springboot.jingfei.SpringBoot.utils;

import java.io.File;
import java.util.StringTokenizer;

/**
 * @Author: jingfei
 * @Date: 2019/3/30 16:59
 * @Version 1.0
 */
public class Apple implements Fruit{

    @Override
    public void getName() {
        System.out.println(getClass().getSimpleName());
    }

    public static void main(String[] args){
        //加载<JAVA_HOME>/lib/ext目录中的类库
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
                System.out.println(dirs[i].getName());
            }
        }
    }
}
