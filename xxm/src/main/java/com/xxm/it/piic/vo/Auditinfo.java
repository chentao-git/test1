package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xxm.it.system.vo.CommonVO;

@XmlRootElement(name="UnderwriteInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
/**
 * 审核结果通知
 * @author Administrator
 *
 */
public class Auditinfo extends CommonVO {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private String auditInfoId;
   @XmlElement(name="AuditTpye")
   private String auditTpye;//审核结果类型 01 预审 02初审 03 终审
   @XmlElement(name="PrejudicNo")
   private String prejudicNo;//预审单号  CPI生成，推送预审结果时传入
   @XmlElement(name="ProposalNo")
   private String proposalNo;//投保单号 投保单号 初审回传
   @XmlElement(name="UnderwriteResult")
   private String underwriteResult;//审核结果代码 00：审核通过 01：审核不通过
   @XmlElement(name="UnderwriteTimes")
   private String underwriteTimes; //审核通过时间
   @XmlElement(name="ErrorMessage")
   private String errorMessage;//审核结果描述  针对审核结果的描述
   @XmlElement(name="LoanNo")
   private String loanNo; //借据号  如果申请状态为03，该字段必须有值
   @XmlElement(name="ApplyNo")
   private String applyNo;//申请书编号
   private String status; //0为未处理的数据 1处理的数据
   private String applyInfoId;
   
   
public String getAuditInfoId() {
	return auditInfoId;
}
public void setAuditInfoId(String auditInfoId) {
	this.auditInfoId = auditInfoId;
}
public String getAuditTpye() {
	return auditTpye;
}
public void setAuditTpye(String auditTpye) {
	this.auditTpye = auditTpye;
}
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
public String getUnderwriteResult() {
	return underwriteResult;
}
public void setUnderwriteResult(String underwriteResult) {
	this.underwriteResult = underwriteResult;
}
public String getUnderwriteTimes() {
	return underwriteTimes;
}
public void setUnderwriteTimes(String underwriteTimes) {
	this.underwriteTimes = underwriteTimes;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public String getLoanNo() {
	return loanNo;
}
public void setLoanNo(String loanNo) {
	this.loanNo = loanNo;
}
public String getApplyNo() {
	return applyNo;
}
public void setApplyNo(String applyNo) {
	this.applyNo = applyNo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getApplyInfoId() {
	return applyInfoId;
}
public void setApplyInfoId(String applyInfoId) {
	this.applyInfoId = applyInfoId;
}
   
}
