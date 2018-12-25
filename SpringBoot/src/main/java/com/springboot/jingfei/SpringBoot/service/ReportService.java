package com.springboot.jingfei.SpringBoot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.springboot.jingfei.SpringBoot.bean.ReportSetting;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    public void updateReportSetting(){
        // 先获取所有的专业版报表数据
        List<ReportSetting> reportList = reportDao.getAllReportSetting();
        for(ReportSetting report : reportList){
            JSONArray jsonArray = JSON.parseArray(report.getJsonText());
            for(Object object : jsonArray){
                JSONObject jsonObject = (JSONObject) object;
                String describe = jsonObject.getString("describe");
                if("车辆信息".equals(describe)){
                    JSONArray titles = jsonObject.getJSONArray("titles");
                    JSONArray paramArray = getJsonArray();
                    titles.addAll(paramArray);
                    break;
                }
            }
            String userName = "fkzj";
            Map map = new HashMap();
            map.put("userId",userName);
            map.put("jsonText", JSON.toJSONString(jsonArray));
            map.put("type","专业版");
            if(report.getUserId().equals(userName)){
                reportDao.updateJsonText(map);
            }
        }
    }

    private JSONArray getJsonArray(){
        Map map1 = new HashMap(); // 解压时间
        Map map2 = new HashMap(); // 解压字段
        JSONArray jsonArray = new JSONArray();
        map1.put("attr","mortgage_status");
        map1.put("selected",false);
        map1.put("title","是否解押");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map1));
        jsonArray.add(jsonObject);
        map2.put("attr","solution_mortgage_time");
        map2.put("selected",false);
        map2.put("title","解押时间");
        jsonObject = JSON.parseObject(JSON.toJSONString(map2));
        jsonArray.add(jsonObject);
        return jsonArray;
    }
}
