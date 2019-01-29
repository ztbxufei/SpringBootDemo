package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class MenuService {
    @Autowired
    private ReportDao reportDao;

    public List<Menu> getMenuList(){
        List<Menu> menuList = reportDao.getMenuList();
        for(Menu menu : menuList){
            getListByPid(menu,menuList);
        }
        return menuList;
    }

    // 根据主菜单id， 获取主菜单下的所有菜单
    private void getListByPid(Menu menu, List<Menu> list){
        for(Menu subMenu : list){
            if(subMenu.getPid().equals(menu.getId())){
                menu.getMenuList().add(subMenu);
            }
        }
    }
}
