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

    @RequestMapping("/huiTab")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("huiTab");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
