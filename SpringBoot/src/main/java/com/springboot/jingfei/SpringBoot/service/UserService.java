package com.springboot.jingfei.SpringBoot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.dao.UserDao;
import com.springboot.jingfei.SpringBoot.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserService extends BaseService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUser(Map paramMap) throws Exception {
        PageInfo<User> userList = queryDataByPage(UserDao.class, this.userDao, "getAllUser", paramMap);
        System.out.println(userList);
        return userDao.getAllUser(paramMap);
    }
}
