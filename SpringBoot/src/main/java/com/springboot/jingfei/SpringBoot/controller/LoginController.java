package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.bean.Menu;
import com.springboot.jingfei.SpringBoot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/huiTab")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("huiTab");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("index");
        HttpSession session = request.getSession(true);
        List<Map<Menu,List<Menu>>> menuList = menuService.getMenuList();
        session.setAttribute("menuList", menuList);
        return mv;
    }
}
