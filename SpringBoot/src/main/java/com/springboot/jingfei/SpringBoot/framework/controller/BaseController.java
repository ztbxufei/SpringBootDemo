package com.springboot.jingfei.SpringBoot.framework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        Map map = new HashMap();
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            if(paraName.equals("aoData")){
                Map mapEntry = request.getParameterMap();
                String mapList = ((String[]) mapEntry.get(paraName))[0];
                JSONArray jsonArray = JSON.parseArray(mapList);
                for(Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject) object;
                    map.put(jsonObject.getString("name"), jsonObject.getString("value"));
                }
            }
            map.put(paraName, request.getParameter(paraName));
        }
        return map;
    }
}
