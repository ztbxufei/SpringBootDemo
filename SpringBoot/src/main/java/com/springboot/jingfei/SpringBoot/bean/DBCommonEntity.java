package com.springboot.jingfei.SpringBoot.bean;

public class DBCommonEntity {

    private String primaryKey;
    private Object primaryValue;
    private Object entity;
    private String tableName;

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

    public static class DBCommonBuilder {

        private DBCommonEntity entity = new DBCommonEntity();

        public DBCommonBuilder setPrimaryKey(String board) {
            entity.setPrimaryKey(board);
            return this;
        }

        public DBCommonBuilder setEntity(Object object) {
            entity.setEntity(object);
            return this;
        }

        public DBCommonBuilder setTableName(String tableName) {
            entity.setTableName(tableName);
            return this;
        }

        public DBCommonBuilder setPrimaryValue(Object primaryValue) {
            entity.setPrimaryValue(primaryValue);
            return this;
        }

        public DBCommonEntity create(){
            return entity;
        }
    }
    public static DBCommonBuilder builder(){
        return new DBCommonBuilder();
    }
}
