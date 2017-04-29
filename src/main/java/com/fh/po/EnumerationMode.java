package com.fh.po;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
/**
 * @className：枚举类 EnumerationMode
 * @author：hingbox
 * @created：2017-04-29 17:40
 * @email：hingbox@163.com
 * @version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class EnumerationMode {

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "remark")
    private String remark;

    @XmlElement(name = "value")
    private List<ValueMode> values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ValueMode> getValues() {
        return values;
    }

    public void setValues(List<ValueMode> values) {
        this.values = values;
    }
}
