package com.springboot.jingfei.SpringBoot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuService {
    @Autowired
    private ReportDao reportDao;

    public String getMenuList(){
        List<Menu> menuList = reportDao.getMenuList();
        return JSON.toJSONString(menuList);
    }
}
