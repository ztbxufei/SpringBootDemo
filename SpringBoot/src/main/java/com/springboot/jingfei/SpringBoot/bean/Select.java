package com.springboot.jingfei.SpringBoot.bean;

public class Select {

    private Integer code;
    private String text;

    public Select(){

    }

    public Select(Integer code, String text){
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
