package com.springboot.jingfei.SpringBoot.service;

import com.springboot.jingfei.SpringBoot.bean.DBCommonEntity;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.dao.UserDao;
import com.springboot.jingfei.SpringBoot.framework.service.BaseService;
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserService extends BaseService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DBCommonService dbCommonService;

    public Map getAllUser(Map paraMap) throws Exception {
        DBCommonEntity dbCommonEntity = DBCommonEntity.builder().setTableName("sys_user").setPrimaryKey("id").setPrimaryValue(75).create();
        dbCommonService.delete(dbCommonEntity);
        dbCommonEntity = DBCommonEntity.builder().setTableName("sys_user").setPrimaryKey("name").setPrimaryValue("张三").setClazz(User.class).create();
        List<User> userList = dbCommonService.select(dbCommonEntity);
        for(User user : userList){
            System.out.println(StringUtils.convertObjToMap(user));
        }
        return queryDataByPage(UserDao.class, this.userDao, "getAllUser", paraMap);
    }
}
