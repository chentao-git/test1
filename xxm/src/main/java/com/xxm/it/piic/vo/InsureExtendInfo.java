package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureExtendInfo {
  @XmlAttribute(name="key") 
  private String key;
  @XmlElement(name = "ReturnPolicyno")
  private String returnPolicyno;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getReturnPolicyno() {
	return returnPolicyno;
}
public void setReturnPolicyno(String returnPolicyno) {
	this.returnPolicyno = returnPolicyno;
}
  
}
