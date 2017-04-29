package com.fh.po;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * @className：枚举类 EnumerationMode
 * @author：hingbox
 * @created：2017-04-29 17:40
 * @email：hingbox@163.com
 * @version 1.0
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "enumerations")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnumerationModes {

   @XmlElement(name = "enumeration")
   private List<EnumerationMode> enumeration;

    public List<EnumerationMode> getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(List<EnumerationMode> enumeration) {
        this.enumeration = enumeration;
    }
}
