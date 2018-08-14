package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

//银行预审
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class PrejudicationVO {
	@XmlTransient
	private String applyInfoId; //申请id
	@XmlElement(name="MktProdType")
	private String mktProdType; //流程标志
	@XmlElement(name="cooprProdType")
	private String cooprProdType; //合作商产品类型
	@XmlElement(name="AppliName")
	private String appliName; //投保人姓名
	@XmlElement(name="AppliIdType")
	private String appliIdType; //投保人证件类型
	@XmlElement(name="AppliIdNo")
	private String appliIdNo; //投保人证件号
	@XmlElement(name="AppliSex")
	private String appliSex; //投保人性别
	@XmlElement(name="AppliBirth")
	private String appliBirth; //投保人出生日期
	@XmlElement(name="AppliIdMobile")
	private String appliIdMobile; //投保人手机号
	@XmlElement(name="Bankcode")
	private String backCode; //银行代码
	@XmlElement(name="Maincomcode")
	private String maincomCode;//归属机构代码
	
	public String getMktProdType() {
		return mktProdType;
	}
	public void setMktProdType(String mktProdType) {
		this.mktProdType = mktProdType;
	}
	
	public String getCooprProdType() {
		return cooprProdType;
	}
	public void setCooprProdType(String cooprProdType) {
		this.cooprProdType = cooprProdType;
	}
	
	public String getAppliName() {
		return appliName;
	}
	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}
	
	public String getAppliIdType() {
		return appliIdType;
	}
	public void setAppliIdType(String appliIdType) {
		this.appliIdType = appliIdType;
	}
	
	public String getAppliIdNo() {
		return appliIdNo;
	}
	public void setAppliIdNo(String appliIdNo) {
		this.appliIdNo = appliIdNo;
	}
	
	public String getAppliSex() {
		return appliSex;
	}
	public void setAppliSex(String appliSex) {
		this.appliSex = appliSex;
	}
	
	public String getAppliBirth() {
		return appliBirth;
	}
	public void setAppliBirth(String appliBirth) {
		this.appliBirth = appliBirth;
	}
	
	public String getAppliIdMobile() {
		return appliIdMobile;
	}
	public void setAppliIdMobile(String appliIdMobile) {
		this.appliIdMobile = appliIdMobile;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getBackCode() {
		return backCode;
	}
	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}
	public String getMaincomCode() {
		return maincomCode;
	}
	public void setMaincomCode(String maincomCode) {
		this.maincomCode = maincomCode;
	}
	
}
