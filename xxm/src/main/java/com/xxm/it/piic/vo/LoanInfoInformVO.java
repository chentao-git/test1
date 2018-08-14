package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xxm.it.system.vo.CommonVO;

@XmlRootElement(name="LoanInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class LoanInfoInformVO extends CommonVO{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="ProposalNo")
	private String proposalNo;//投保单号
	private String loanResult;//放款结果
	@XmlElement(name="LoanNo")
	private String loanNo;//借据号
	private String intRate;//正常执行利率
	private String odIntRate;//罚息执行利率
	private String atrsDueDay;//每月的还款日
	private String firstDueDt;//首次还款日
	private String firstPayAmt;//首次还款金额
	private String lastDueDt;//贷款到期日
	private String payRetCode;//受托支付结果
	private String status; //0 为未处理数据 1为处理的数据
	private String loanInfoInformId;
	
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getLoanResult() {
		return loanResult;
	}
	public void setLoanResult(String loanResult) {
		this.loanResult = loanResult;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIntRate() {
		return intRate;
	}
	public void setIntRate(String intRate) {
		this.intRate = intRate;
	}
	public String getOdIntRate() {
		return odIntRate;
	}
	public void setOdIntRate(String odIntRate) {
		this.odIntRate = odIntRate;
	}
	public String getAtrsDueDay() {
		return atrsDueDay;
	}
	public void setAtrsDueDay(String atrsDueDay) {
		this.atrsDueDay = atrsDueDay;
	}
	public String getFirstDueDt() {
		return firstDueDt;
	}
	public void setFirstDueDt(String firstDueDt) {
		this.firstDueDt = firstDueDt;
	}
	public String getFirstPayAmt() {
		return firstPayAmt;
	}
	public void setFirstPayAmt(String firstPayAmt) {
		this.firstPayAmt = firstPayAmt;
	}
	public String getLastDueDt() {
		return lastDueDt;
	}
	public void setLastDueDt(String lastDueDt) {
		this.lastDueDt = lastDueDt;
	}
	public String getPayRetCode() {
		return payRetCode;
	}
	public void setPayRetCode(String payRetCode) {
		this.payRetCode = payRetCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoanInfoInformId() {
		return loanInfoInformId;
	}
	public void setLoanInfoInformId(String loanInfoInformId) {
		this.loanInfoInformId = loanInfoInformId;
	}
	
}
