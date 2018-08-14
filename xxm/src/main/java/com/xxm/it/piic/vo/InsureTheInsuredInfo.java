package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureTheInsuredInfo {
	@XmlElement(name="InsuredSeqNo")
	private String insuredSeqNo;//被保险人序列号
	@XmlElement(name="InsuredName")
	private String insuredName;//被保险人全称
	@XmlElement(name="InsuredIdType")
	private String insuredIdType;//被保险人组织机构代码
	@XmlElement(name="InsuredIdNo")
	private String insuredIdNo;//被保险人组织机构证件号
	@XmlElement(name="Bankcode")
	private String bankcode;//银行代码
	//受益人信息
	@XmlElementWrapper(name = "Benefits") 
    @XmlElement(name = "Benefit")
	private List<InsureBeneficiary> insureBeneficiary;
	@XmlTransient
	private String insuredId;
	public String getInsuredSeqNo() {
		return insuredSeqNo;
	}
	public void setInsuredSeqNo(String insuredSeqNo) {
		this.insuredSeqNo = insuredSeqNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredIdType() {
		return insuredIdType;
	}
	public void setInsuredIdType(String insuredIdType) {
		this.insuredIdType = insuredIdType;
	}
	public String getInsuredIdNo() {
		return insuredIdNo;
	}
	public void setInsuredIdNo(String insuredIdNo) {
		this.insuredIdNo = insuredIdNo;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public List<InsureBeneficiary> getInsureBeneficiary() {
		return insureBeneficiary;
	}
	public void setInsureBeneficiary(List<InsureBeneficiary> insureBeneficiary) {
		this.insureBeneficiary = insureBeneficiary;
	}
	public String getInsuredId() {
		return insuredId;
	}
	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}
	
	
}
