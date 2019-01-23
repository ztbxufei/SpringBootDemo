package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.DataBaseEntity;
import com.springboot.jingfei.SpringBoot.bean.Select;
import com.springboot.jingfei.SpringBoot.dao.UserDao;
import com.springboot.jingfei.SpringBoot.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserService extends BaseService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DataBaseService dataBaseService;

    public Map getAllUser(Map paraMap) throws Exception {
        return queryDataByPage(UserDao.class, this.userDao, "getAllUser", paraMap);
    }

    public List<Select> getSelectCodeList(){
        DataBaseEntity dataBaseEntity = DataBaseEntity.builder().setTableName("sys_code").setPrimaryKey("childmsg").setPrimaryValue("sys_dinct").create();
        List<Select> selectList = dataBaseService.selectList(dataBaseEntity, Select.class);
        return selectList;
    }
}
