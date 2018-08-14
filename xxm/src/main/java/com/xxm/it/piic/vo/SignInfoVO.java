package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="SignInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class SignInfoVO { //签约状态查询
	@XmlElement(name = "ProposalNo")
	private String proposalNo;
	@XmlElement(name = "ApplyNo")
	private String applyNo;
	@XmlTransient
	private String signInfoId;
	@XmlTransient
	private String applyInfoStatus;//申请信息状态
	@XmlTransient
	private String applyInfoId;
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getSignInfoId() {
		return signInfoId;
	}
	public void setSignInfoId(String signInfoId) {
		this.signInfoId = signInfoId;
	}
	public String getApplyInfoStatus() {
		return applyInfoStatus;
	}
	public void setApplyInfoStatus(String applyInfoStatus) {
		this.applyInfoStatus = applyInfoStatus;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	
	
}
