package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.bean.DataBaseEntity;
import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.framework.controller.BaseController;
import com.springboot.jingfei.SpringBoot.service.DataBaseService;
import com.springboot.jingfei.SpringBoot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private DataBaseService dataBaseService;

    @RequestMapping("/huiTab")
    public ModelAndView login(HttpServletRequest request){
        return returnView(request);
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws IllegalAccessException {
        User user = new User();
        user.setName("八戒");
        DataBaseEntity dataBaseEntity = DataBaseEntity.builder().setTableName("sys_user").setPrimaryKey("worknum").setPrimaryValue("1010").create(user);
        dataBaseService.updateField(dataBaseEntity);
        ModelAndView mv = returnView(request);
        HttpSession session = request.getSession(true);
        List<Menu> menuList = menuService.getMenuList();
        session.setAttribute("menuList", menuList);
        return mv;
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request){
        return returnView(request);
    }
}
