package com.springboot.jingfei.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 注意事项：启动类所处于的包必须要包含要访问的路径所在的包
 * version1
 * @author ZXPH
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
