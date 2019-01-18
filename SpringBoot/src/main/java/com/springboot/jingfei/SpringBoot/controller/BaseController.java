package com.springboot.jingfei.SpringBoot.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    /**
     * 纯粹的返回视图，中间不加任何渲染
     * 前提是路径名称和视图名称一致
     * @param request
     * @return
     */
    public ModelAndView returnView(HttpServletRequest request){
        String filePath = request.getServletPath();
        filePath = filePath.substring(1, filePath.length());
        ModelAndView modelAndView = new ModelAndView(filePath);
        return modelAndView;
    }

}
