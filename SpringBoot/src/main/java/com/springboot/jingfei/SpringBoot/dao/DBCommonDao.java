package com.springboot.jingfei.SpringBoot.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DBCommonDao {
    // 增加一条数据
    void insert(Map map);
    // 删除一条数据
    int delete(Map map);
}
