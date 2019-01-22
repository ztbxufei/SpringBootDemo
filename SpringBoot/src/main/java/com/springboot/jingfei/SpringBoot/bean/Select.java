package com.springboot.jingfei.SpringBoot.bean;

public class Select {

    private String code;
    private String text;

    public Select(String code, String text){
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
