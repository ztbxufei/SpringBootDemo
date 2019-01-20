package com.springboot.jingfei.SpringBoot.framework.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    /**
     * 纯粹的返回视图，中间不加任何渲染
     * 前提是路径名称和视图名称一致
     * @param request
     * @return
     */
    public ModelAndView returnView(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        try {
            String filePath = request.getServletPath();
            filePath = filePath.substring(1, filePath.length());
            modelAndView = new ModelAndView(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    public Map getParameterMap(HttpServletRequest request){
        Map paramMap = new HashMap();
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String key = enu.nextElement().toString();
            paramMap.put(key, request.getParameter(key));
        }
        return paramMap;
    }

}
