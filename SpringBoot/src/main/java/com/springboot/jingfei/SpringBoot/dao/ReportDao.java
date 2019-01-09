package com.springboot.jingfei.SpringBoot.dao;

import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.bean.ReportSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportDao {
    List<ReportSetting> getAllReportSetting(Map map);
    int updateJsonText(Map map);
    List<Menu> getMenuList();
}
