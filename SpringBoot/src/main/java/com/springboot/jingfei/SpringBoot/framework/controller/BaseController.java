package com.springboot.jingfei.SpringBoot.framework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.jingfei.SpringBoot.utils.JsonListUtil;
import org.apache.poi.ss.formula.functions.T;
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

    /**
     * 从传递过来的参数里面获取表头信息
     * @param paramMap
     * @return
     */
    public LinkedHashMap getLinkedHashMap(Map paramMap){
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Object headDataObject = paramMap.get("headData");
        if(headDataObject != null){
            String headData = paramMap.get("headData").toString();
            List<Map> mapList = JsonListUtil.jsonToList(headData, Map.class);
            for(Map map : mapList){
                linkedHashMap.put(map.get("name"), map.get("value"));
            }
        }
        return linkedHashMap;
    }

    public <T> List<T> getEntityList(Map map){
        Object entityObject = map.get("aaData");
        List<T> list = new ArrayList<>();
        if(entityObject != null){
            list = (List<T>) entityObject;
        }
        return list;
    }
}
