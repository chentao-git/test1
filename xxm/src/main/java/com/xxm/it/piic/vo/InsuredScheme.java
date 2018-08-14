package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsuredScheme {
	@XmlElement(name="SchemeCode")
	private String schemeCode; //编号
	@XmlElement(name="SchemeAmount")
	private String schemeAmount; //保额
	@XmlElement(name="SchemePremium")
	private String schemePremium; //保费
	
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeAmount() {
		return schemeAmount;
	}
	public void setSchemeAmount(String schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	public String getSchemePremium() {
		return schemePremium;
	}
	public void setSchemePremium(String schemePremium) {
		this.schemePremium = schemePremium;
	}
	
	
}
