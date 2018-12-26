package com.springboot.jingfei.SpringBoot.bean.xml;

import org.codehaus.groovy.util.StringUtil;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 修改报表设置可以通过此类进行
 */
@XmlRootElement(name = "Fields")
public class Fields {

    private String type;

    private String compare;

    private List<Field> fields;

    @XmlAttribute(name = "compare")
    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "Field")
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
