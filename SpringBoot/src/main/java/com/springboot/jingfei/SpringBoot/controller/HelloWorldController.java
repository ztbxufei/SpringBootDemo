package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController注解返回json字符串
 * RestController注解是被Controller和ResponseBody注解修饰的
 */

@RestController
public class HelloWorldController {
	@RequestMapping("hello")
    @SysLog(name = "index方法", value="系统登录入口")
	public String index() {
        return "Hello World!";
    }
}
