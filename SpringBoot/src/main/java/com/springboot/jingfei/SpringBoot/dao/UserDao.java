package com.springboot.jingfei.SpringBoot.dao;

import com.springboot.jingfei.SpringBoot.bean.Test;
import com.springboot.jingfei.SpringBoot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    List<User> getAllUser(Map map);
    int insertData(List<Test> list);
}
