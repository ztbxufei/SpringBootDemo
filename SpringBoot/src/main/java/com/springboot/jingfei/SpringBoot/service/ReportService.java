package com.springboot.jingfei.SpringBoot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.jingfei.SpringBoot.bean.ReportSetting;
import com.springboot.jingfei.SpringBoot.bean.xml.Field;
import com.springboot.jingfei.SpringBoot.bean.xml.Fields;
import com.springboot.jingfei.SpringBoot.constant.Constant;
import com.springboot.jingfei.SpringBoot.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportService {

    private static String type = "";
    private static String compare = "";
    private static String describeType = "";
    private static Fields normalFields = null;

    @Autowired
    private ReportDao reportDao;

    static {
        try {
            JAXBContext context = JAXBContext.newInstance(new Class[]{Fields.class});
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = ResourceUtils.getFile("classpath:xml/report.xml");
            Fields fields = (Fields) unmarshaller.unmarshal(file);
            normalFields = fields;
            type = normalFields.getType();
            compare = normalFields.getCompare();
            describeType = normalFields.getDescribe();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateReportSetting(String flag) {
        Map paramMap = new HashMap();
        paramMap.put("type", type);
        List<ReportSetting> reportList = reportDao.getAllReportSetting(paramMap);
        int count = 0; // 记录更新了多少行
        for (ReportSetting report : reportList) {
            JSONArray jsonArray = JSON.parseArray(report.getJsonText());
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String describe = jsonObject.getString("describe");
                if (describeType.equals(describe)) {
                    JSONArray titles = jsonObject.getJSONArray("titles");
                    getJsonArray(titles, flag);
                    break;
                }
            }
            if (!JSON.toJSONString(jsonArray).equals(report.getJsonText())) { // 判断json字符串是否发生了变化
                Map map = new HashMap();
                map.put("userId", report.getUserId());
                map.put("jsonText", JSON.toJSONString(jsonArray));
                map.put("type", type);
                count += reportDao.updateJsonText(map);
            }
        }
        System.out.println("总共更新了：" + count + "行");
    }

    private void getJsonArray(JSONArray titles, String flag) {
        List<Field> fieldsList = normalFields.getFields();
        List<Map> mapList = new ArrayList<>();
        for (Field field : fieldsList) {
            Map map = new HashMap();
            map.put(field.getAttr().getName(), field.getAttr().getValue());
            map.put(field.getSelected().getName(), field.getSelected().getValue());
            map.put(field.getTitle().getName(), field.getTitle().getValue());
            map.put(field.getPrevious().getName(), field.getPrevious().getValue());
            mapList.add(map);
        }
        for (Map subMap : mapList) {
            titles = insertIntoArray(titles, subMap, flag);
        }
    }

    // jsonObject数据格式{"name":"", "attr":"", "selected":""}
    // flag 表示是更新还是删除
    private JSONArray insertIntoArray(JSONArray titles, Map map, String delFlag) {
        boolean flag = true;
        JSONArray jsonArray = new JSONArray();
        if(Constant.UPDATE.equals(delFlag)) {
            for (Object object : titles) {
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.getString("attr").equals(map.get(compare).toString())) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            for(Object object : titles){
                JSONObject jsonObject = (JSONObject) object;
                // 判断是否是需要加入的字段的上一个节点，若果是先加节点后加字段
                if(Constant.UPDATE.equals(delFlag)) {
                    if (jsonObject.getString("name").equals(map.get("previous"))) {
                        jsonArray.add(JSON.toJSONString(object));
                        jsonArray.add(JSON.toJSONString(map));
                    }
                    jsonArray.add(JSON.toJSONString(object));
                } else if(Constant.DELETE.equals(delFlag)){
                    if(jsonObject.getString("name").equals(map.get(compare))){
                        jsonArray.remove(jsonObject);
                        break;
                    }
                }
            }
        }
        if(jsonArray != null && jsonArray.size() > 0){
            return jsonArray;
        }
        return titles;
    }
}
