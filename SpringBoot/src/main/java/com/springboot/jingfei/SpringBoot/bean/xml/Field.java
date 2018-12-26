package com.springboot.jingfei.SpringBoot.bean.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Field")
public class Field {

    private FieldAttr attr;
    private FieldASelected selected;
    private FieldTitle title;

    @XmlElement(name = "Attr")
    public FieldAttr getAttr() {
        return attr;
    }

    public void setAttr(FieldAttr attr) {
        this.attr = attr;
    }

    @XmlElement(name = "Selected")
    public FieldASelected getSelected() {
        return selected;
    }

    public void setSelected(FieldASelected selected) {
        this.selected = selected;
    }

    @XmlElement(name = "Title")
    public FieldTitle getTitle() {
        return title;
    }

    public void setTitle(FieldTitle title) {
        this.title = title;
    }
}
