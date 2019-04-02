package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.DataBaseEntity;
import com.springboot.jingfei.SpringBoot.dao.DataBaseDao;
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class DataBaseService {

    @Autowired
    private DataBaseDao dataBaseDao;

    /**
     * 插入一条数据
     *
     * @param dataBaseEntity
     */
    public void insert(DataBaseEntity dataBaseEntity) {
        Map dbEntityMap = StringUtils.convertObjToMap(dataBaseEntity);
        dataBaseDao.insert(dbEntityMap);
    }

    /**
     * 根据主键删除数据
     *
     * @param dataBaseEntity
     * @return
     */
    public int delete(DataBaseEntity dataBaseEntity) {
        Map dbEntityMap = StringUtils.convertObjToMap(dataBaseEntity);
        return dataBaseDao.delete(dbEntityMap);
    }

    /**
     * 根据主键选择多条数据
     *
     * @param dataBaseEntity
     * @return
     */
    public List selectList(DataBaseEntity dataBaseEntity, Class clazz) {
        Map dbEntityMap = StringUtils.convertObjToMap(dataBaseEntity);
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
        Map dbEntityMap = StringUtils.convertObjToMap(dataBaseEntity);
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
        copyBeanFiledValue(oldObject.getClass(),newObject,oldObject);
        dataBaseEntity.setEntity(oldObject);
        // 先删除后增加
        updateObject(dataBaseEntity);
    }

    /**
     * 将from对象的字段属性  赋值给to
     * @param cls
     * @param from
     * @param to
     * @throws IllegalAccessException
     */
    public void copyBeanFiledValue(Class cls, Object from, Object to) throws IllegalAccessException {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(from);
            if (val != null) {
                field.set(to, val);
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
