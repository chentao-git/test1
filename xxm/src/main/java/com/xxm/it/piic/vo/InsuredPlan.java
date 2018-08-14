package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsuredPlan {
	@XmlTransient
	private String insuredPlanId;
	@XmlElement(name="RationType")
	private String rationType;//方案代码
	@XmlElementWrapper(name = "Schemes") 
	@XmlElement(name="Scheme")
	private List<InsuredScheme> insuredSchemeList;//条款信息
	@XmlElementWrapper(name = "Engages") 
	@XmlElement(name="Engage")
	private List<InsuredEngage> insuredEngageList;//特别约定信息
	
	public String getInsuredPlanId() {
		return insuredPlanId;
	}
	public void setInsuredPlanId(String insuredPlanId) {
		this.insuredPlanId = insuredPlanId;
	}
	public String getRationType() {
		return rationType;
	}
	public void setRationType(String rationType) {
		this.rationType = rationType;
	}
	public List<InsuredScheme> getInsuredSchemeList() {
		return insuredSchemeList;
	}
	public void setInsuredSchemeList(List<InsuredScheme> insuredSchemeList) {
		this.insuredSchemeList = insuredSchemeList;
	}
	public List<InsuredEngage> getInsuredEngageList() {
		return insuredEngageList;
	}
	public void setInsuredEngageList(List<InsuredEngage> insuredEngageList) {
		this.insuredEngageList = insuredEngageList;
	}
	
	
	
	
}
