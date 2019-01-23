package com.springboot.jingfei.SpringBoot.bean;

public class DataBaseEntity {

    private String primaryKey;
    private Object primaryValue;
    private Object entity;
    private String tableName;
    private String orderField; // 只有选择时起效果 orderField  orderDesc 配套使用
    private String orderDesc; // 只有选择时起效果 orderField  orderDesc 配套使用， 默认desc排序

    public static String DESC = "desc";
    public static String ASC = "asc";
    public static String KEY = "id";

    public DataBaseEntity(){
        this.orderDesc = ASC;
        this.primaryKey = KEY;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String isOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Object getPrimaryValue() {
        return primaryValue;
    }

    public void setPrimaryValue(Object primaryValue) {
        this.primaryValue = primaryValue;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static class DataBaseBuilder {

        private DataBaseEntity entity = new DataBaseEntity();

        public DataBaseBuilder setOrderDesc(String orderDesc){
            entity.setOrderDesc(orderDesc);
            return this;
        }

        public DataBaseBuilder setOrderField(String orderField){
            entity.setOrderField(orderField);
            return this;
        }

        public DataBaseBuilder setPrimaryKey(String board) {
            entity.setPrimaryKey(board);
            return this;
        }

        public DataBaseBuilder setEntity(Object object) {
            entity.setEntity(object);
            return this;
        }

        public DataBaseBuilder setTableName(String tableName) {
            entity.setTableName(tableName);
            return this;
        }

        public DataBaseBuilder setPrimaryValue(Object primaryValue) {
            entity.setPrimaryValue(primaryValue);
            return this;
        }

        public DataBaseEntity create(){
            return entity;
        }
    }
    public static DataBaseBuilder builder(){
        return new DataBaseBuilder();
    }
}
