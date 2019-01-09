package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/HuiTab")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("HuiTab");
        return mv;
    }

    @RequestMapping("/ZTree")
    public ModelAndView ZTree(){
        String json = menuService.getMenuList();
        ModelAndView mv = new ModelAndView("ZTree");
        mv.addObject("menuList",json);
        mv.addObject("name","123456");
        return mv;
    }
}
