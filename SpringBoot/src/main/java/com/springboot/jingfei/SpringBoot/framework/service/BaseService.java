package com.springboot.jingfei.SpringBoot.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class BaseService {

    /**
     * @param clazz  传进来的dao
     * @param method 查询的方法名称
     * @param para   查询参数
     * @param <T>    查询出来的实体类
     * @return
     */
    public <T> PageInfo<T> queryDataByPage(Class clazz, Object object, String method, Map para) throws Exception {
//        if(para.containsKey("page")){// 分页操作
//            Method methodName = clazz.getDeclaredMethod(method, new Class[]{Map.class});
//            PageHelper.startPage(Integer.parseInt(para.get("page").toString()),Integer.parseInt(para.get("rows").toString()));
//            List<T> pageList = (List<T>) methodName.invoke(object, new Object[]{para});
//            PageInfo<T> pageInfo = new PageInfo<>(pageList);
//            System.out.println(pageInfo);
//            return pageInfo;
//        }
        return null;
    }
}
