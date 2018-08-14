package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="UnderwriteReturnInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
/**
 * 审核结果通知返回
 * @author Administrator
 *
 */
public class AuditReturnInfo {
	@XmlTransient
	private String auditReturnInfoId;
	@XmlElement(name="PrejudicNo")
	private String prejudicNo;
	@XmlElement(name="ProposalNo")
	private String proposalNo;
	@XmlElement(name="ResultCode")
	private String resultCode;
	@XmlElement(name="ResultMessage")
	private String resultMessage;
	
	
	public String getPrejudicNo() {
		return prejudicNo;
	}
	public void setPrejudicNo(String prejudicNo) {
		this.prejudicNo = prejudicNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getAuditReturnInfoId() {
		return auditReturnInfoId;
	}
	public void setAuditReturnInfoId(String auditReturnInfoId) {
		this.auditReturnInfoId = auditReturnInfoId;
	}
	
	
	
}
