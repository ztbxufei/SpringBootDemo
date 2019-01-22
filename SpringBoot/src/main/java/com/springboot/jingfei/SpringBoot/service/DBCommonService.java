package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.DBCommonEntity;
import com.springboot.jingfei.SpringBoot.dao.DBCommonDao;
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DBCommonService {

    @Autowired
    private DBCommonDao dbCommonDao;

    /**
     * 插入一条数据
     * @param dbCommonEntity
     */
    public void insert(DBCommonEntity dbCommonEntity) {
        Object database = dbCommonEntity.getEntity();
        String tableName = dbCommonEntity.getTableName();

        Map dbEntityMap = new HashMap();
        dbEntityMap.put("tableName", tableName);
        dbEntityMap.put("database", StringUtils.convertObjToMap(database));

        dbCommonDao.insert(dbEntityMap);
    }

    /**
     * 根据主键删除数据
     * @param dbCommonEntity
     * @return
     */
    public int delete(DBCommonEntity dbCommonEntity){
        String tableName = dbCommonEntity.getTableName();
        String primaryKey = dbCommonEntity.getPrimaryKey();
        Object primaryValue = dbCommonEntity.getPrimaryValue();

        Map dbEntityMap = new HashMap();
        dbEntityMap.put("primaryKey",primaryKey);
        dbEntityMap.put("primaryValue",primaryValue);
        dbEntityMap.put("tableName", tableName);

        return dbCommonDao.delete(dbEntityMap);
    }

    /**
     * 根据主键选择数据
     * @param dbCommonEntity
     * @return
     */
    public List selectList(DBCommonEntity dbCommonEntity){
        String tableName = dbCommonEntity.getTableName();
        String primaryKey = dbCommonEntity.getPrimaryKey();
        Object primaryValue = dbCommonEntity.getPrimaryValue();

        Map dbEntityMap = new HashMap();
        dbEntityMap.put("primaryKey",primaryKey);
        dbEntityMap.put("primaryValue",primaryValue);
        dbEntityMap.put("tableName", tableName);

        List<Map> objectMapList = dbCommonDao.selectList(dbEntityMap);
        List objectList = new ArrayList<>();
        for(Map objectMap : objectMapList){
            objectList.add(StringUtils.convertMapToObj(objectMap, dbCommonEntity.getClazz()));
        }
        return objectList;
    }

    /**
     * 适用于根据条件只能获取一条数据的情况
     * @param dbCommonEntity
     * @return
     */
    public Object select(DBCommonEntity dbCommonEntity){
        List<Object> objectList =selectList(dbCommonEntity);
        if(!StringUtils.isEmpty(objectList)){
            return objectList.get(0);
        }
        return null;
    }
}
