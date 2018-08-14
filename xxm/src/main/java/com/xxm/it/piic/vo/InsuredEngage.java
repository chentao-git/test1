package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsuredEngage {
	@XmlElement(name="EngageCode")
	private String engageCode;//特别约定代码
	@XmlElement(name="EngageName")
	private String engageName;//特别约定名称
	@XmlElement(name="Engagedesc")
	private String engagedesc;//特别约定具体内容
	
	public String getEngageCode() {
		return engageCode;
	}
	public void setEngageCode(String engageCode) {
		this.engageCode = engageCode;
	}
	public String getEngageName() {
		return engageName;
	}
	public void setEngageName(String engageName) {
		this.engageName = engageName;
	}
	public String getEngagedesc() {
		return engagedesc;
	}
	public void setEngagedesc(String engagedesc) {
		this.engagedesc = engagedesc;
	}
	
}
