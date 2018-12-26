package com.springboot.jingfei.SpringBoot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.jingfei.SpringBoot.bean.ReportSetting;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportService {

    private static String type = "专业版";

    @Autowired
    private ReportDao reportDao;

    public void updateReportSetting(){
        Map paramMap = new HashMap();
        paramMap.put("type", type);
        List<ReportSetting> reportList = reportDao.getAllReportSetting(paramMap);
        int count = 0; // 记录更新了多少行
        for(ReportSetting report : reportList){
            JSONArray jsonArray = JSON.parseArray(report.getJsonText());
            for(Object object : jsonArray){
                JSONObject jsonObject = (JSONObject) object;
                String describe = jsonObject.getString("describe");
                if("车辆信息".equals(describe)){
                    JSONArray titles = jsonObject.getJSONArray("titles");
                    getJsonArray(titles);
                    break;
                }
            }
            if(JSON.toJSONString(jsonArray).equals(report.getJsonText())) { // 判断json字符串是否发生了变化
                Map map = new HashMap();
                map.put("userId", report.getUserId());
                map.put("jsonText", JSON.toJSONString(jsonArray));
                map.put("type", type);
                count += reportDao.updateJsonText(map);
            }
        }
        System.out.println("总共更新了：" + count + "行");
    }

    private void getJsonArray(JSONArray titles){
        Map map1 = new HashMap(); // 解压时间
        Map map2 = new HashMap(); // 解压字段
        map1.put("attr","mortgage_status"); // 判断是否含有mortgage_status，有的话就不添加
        map1.put("selected",false);
        map1.put("title","是否解押");
        boolean flag = true;
        for(Object object : titles){
            JSONObject jsonObject = (JSONObject) object;
            if(jsonObject.getString("attr").equals("mortgage_status")){
                flag = false;
                break;
            }
        }
        if(flag) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map1));
            titles.add(jsonObject);
        }
        map2.put("attr","solution_mortgage_time"); // 判断是否含有solution_mortgage_time，有的话就不添加
        map2.put("selected",false);
        map2.put("title","解押时间");
        flag = true;
        for(Object object : titles){
            JSONObject jsonObject = (JSONObject) object;
            if("solution_mortgage_time".equals(jsonObject.getString("attr"))){
                flag = false;
                break;
            }
        }
        if(flag) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map2));
            titles.add(jsonObject);
        }
    }
}
