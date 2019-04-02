package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.bean.DataBaseEntity;
import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.framework.controller.BaseController;
import com.springboot.jingfei.SpringBoot.service.DataBaseService;
import com.springboot.jingfei.SpringBoot.service.MenuService;
import com.springboot.jingfei.SpringBoot.service.ServiceLocator;
import com.springboot.jingfei.SpringBoot.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    public ModelAndView index(HttpServletRequest request){
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

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        Map map = ServiceLocator.getMap(TestInterface.class);
        System.out.println(map);
        return null;
    }
}
