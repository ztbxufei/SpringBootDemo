package com.springboot.jingfei.SpringBoot.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class FieldAttr {

    private String name;
    private String value;

    @XmlAttribute(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
