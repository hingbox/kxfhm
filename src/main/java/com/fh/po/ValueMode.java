package com.fh.po;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @className：枚举类 EnumerationMode
 * @author：hingbox
 * @created：2017-04-29 17:40
 * @email：hingbox@163.com
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ValueMode {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "val")
    private String val;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
