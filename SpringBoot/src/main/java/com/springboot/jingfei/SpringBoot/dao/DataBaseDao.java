package com.springboot.jingfei.SpringBoot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataBaseDao {
    int insert(Map map);

    List<Map> selectList(Map map);

    Map select(Map map);

    int delete(Map map);

    int update(Map map);
}
