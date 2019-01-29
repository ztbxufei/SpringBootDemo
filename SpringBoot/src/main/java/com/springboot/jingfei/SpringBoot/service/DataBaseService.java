package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.DataBaseEntity;
import com.springboot.jingfei.SpringBoot.dao.DataBaseDao;
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataBaseService {

    @Autowired
    private DataBaseDao dataBaseDao;

    /**
     * 插入一条数据
     *
     * @param dataBaseEntity
     */
    public void insert(DataBaseEntity dataBaseEntity) {
        Map dbEntityMap = new HashMap();
        dbEntityMap.put("tableName", dataBaseEntity.getTableName());
        dbEntityMap.put("database", StringUtils.convertObjToMap(dataBaseEntity.getEntity()));

        dataBaseDao.insert(dbEntityMap);
    }

    /**
     * 根据主键删除数据
     *
     * @param dataBaseEntity
     * @return
     */
    public int delete(DataBaseEntity dataBaseEntity) {
        Map dbEntityMap = new HashMap();
        dbEntityMap.put("primaryKey", dataBaseEntity.getPrimaryKey());
        dbEntityMap.put("primaryValue", dataBaseEntity.getPrimaryValue());
        dbEntityMap.put("tableName", dataBaseEntity.getTableName());

        return dataBaseDao.delete(dbEntityMap);
    }

    /**
     * 根据主键选择多条数据
     *
     * @param dataBaseEntity
     * @return
     */
    public List selectList(DataBaseEntity dataBaseEntity, Class clazz) {
        Map dbEntityMap = new HashMap();
        dbEntityMap.put("primaryKey", dataBaseEntity.getPrimaryKey());
        dbEntityMap.put("primaryValue", dataBaseEntity.getPrimaryValue());
        dbEntityMap.put("tableName", dataBaseEntity.getTableName());
        dbEntityMap.put("orderField", dataBaseEntity.getOrderField());
        dbEntityMap.put("orderDesc", dataBaseEntity.isOrderDesc());

        List<Map> objectMapList = dataBaseDao.selectList(dbEntityMap);
        List<Object> objectList = new ArrayList<>();
        for (Map objectMap : objectMapList) {
            objectList.add(StringUtils.convertMapToObj(objectMap, clazz));
        }
        return objectList;
    }

    /**
     * 适用于根据条件只能获取一条数据的情况
     *
     * @param dataBaseEntity
     * @return
     */
    public <T> T select(DataBaseEntity dataBaseEntity, Class<T> clazz) {
        Map dbEntityMap = new HashMap();
        dbEntityMap.put("primaryKey", dataBaseEntity.getPrimaryKey());
        dbEntityMap.put("primaryValue", dataBaseEntity.getPrimaryValue());
        dbEntityMap.put("tableName", dataBaseEntity.getTableName());
        Map objectMap = dataBaseDao.select(dbEntityMap);
        return (T) StringUtils.convertMapToObj(objectMap, clazz);
    }

    /**
     * 更新指定字段
     * @param dataBaseEntity
     */
    public void updateField(DataBaseEntity dataBaseEntity) throws IllegalAccessException {
        Object oldObject = select(dataBaseEntity, dataBaseEntity.getEntity().getClass());
        Object newObject = dataBaseEntity.getEntity();
        assignBeanAttrPara(oldObject.getClass(),newObject,oldObject);
        dataBaseEntity.setEntity(oldObject);
        // 先删除后增加
        updateObject(dataBaseEntity);
    }

    public void assignBeanAttrPara(Class cls, Object from, Object to) throws IllegalAccessException {
        {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields)
            {
                field.setAccessible(true);
                Object val = field.get(from);
                if (val != null) {
                    field.set(to, val);
                }
            }
        }
    }

    /**
     * 更新整个对象
     * @param dataBaseEntity
     */
    public void updateObject(DataBaseEntity dataBaseEntity) {
        // 先删除后插入
        delete(dataBaseEntity);
        insert(dataBaseEntity);
    }
}
