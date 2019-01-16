package com.springboot.jingfei.SpringBoot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuService {
    @Autowired
    private ReportDao reportDao;

    public List<Map<Menu,List<Menu>>> getMenuList(){
        List<Menu> menuList = reportDao.getMenuList();
        List<Map<Menu,List<Menu>>> listMap = new ArrayList<>();
        for(Menu menu : menuList){
            if("1".equals(menu.getIsParent())){
                Map<Menu,List<Menu>> map = new HashMap();
                getListByPid(menu, map, menuList);
                listMap.add(map);
            }
        }
        return listMap;
    }

    // 根据主菜单id， 获取主菜单下的所有菜单
    private void getListByPid(Menu menu, Map<Menu,List<Menu>> map, List<Menu> list){
        List<Menu> subList = new ArrayList();
        for(Menu subMenu : list){
            if(subMenu.getPid().equals(menu.getId())){
                subList.add(subMenu);
            }
        }
        // 主菜单为key， 子菜单的集合为value
        map.put(menu, subList);
    }
}
