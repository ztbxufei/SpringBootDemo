package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController注解返回json字符串
 * RestController注解是被Controller和ResponseBody注解修饰的
 */

@RestController
public class HelloWorldController {

    @Autowired
    private UserService userService;

	@RequestMapping("hello")
    @SysLog(name = "index方法", value="系统登录入口")
	public String index() {
        List<User> userList = userService.getUserList();
        for (User user : userList){
            System.out.println("name: " + user.getName() + "\t sex: " + user.getSex());
        }
        return "OK";
    }
}
