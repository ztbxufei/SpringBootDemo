package com.springboot.jingfei.SpringBoot.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class FieldASelected {
    private String name;
    private boolean value;

    @XmlAttribute(name = "value")
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
