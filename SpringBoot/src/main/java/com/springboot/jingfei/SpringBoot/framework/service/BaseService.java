package com.springboot.jingfei.SpringBoot.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {

    /**
     * 分页操作
     * @param clazz  传进来的dao
     * @param method 查询的方法名称
     * @param para   查询参数
     * @param <T>    查询出来的实体类
     * @return
     */
    public <T> Map queryDataByPage(Class clazz, Object object, String method, Map para) throws Exception {
        Map resultMap = new HashMap();
        if("true".equals(para.get("bServerSide"))){ // 服务端分页
            Method methodName = clazz.getDeclaredMethod(method, new Class[]{Map.class});
            Integer rows = Integer.parseInt(para.get("iDisplayLength").toString()); // 每页行数
            Integer start = Integer.parseInt(para.get("iDisplayStart").toString()); // 起始行数
            Integer page = start / rows + 1; //当前页数
            PageHelper.startPage(page,rows);
            List<T> pageList = (List<T>) methodName.invoke(object, new Object[]{para});
            PageInfo<T> pageInfo = new PageInfo<>(pageList);
            resultMap.put("sEcho", para.get("sEcho"));
            resultMap.put("iTotalRecords",pageInfo.getTotal());
            resultMap.put("iTotalDisplayRecords",pageInfo.getTotal());
            resultMap.put("aaData", pageInfo.getList());
        } else { // 客户端分页
            Method methodName = clazz.getDeclaredMethod(method, new Class[]{Map.class});
            List<T> pageList = (List<T>) methodName.invoke(object, new Object[]{para});
            resultMap.put("sEcho", para.get("sEcho"));
            resultMap.put("iTotalRecords",pageList.size());
            resultMap.put("iTotalDisplayRecords",pageList.size());
            resultMap.put("aaData", pageList);
        }
        return resultMap;
    }
}
