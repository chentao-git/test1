package com.xxm.it.system.vo;

/**
 *讼申请VO
 * 
 * @author Administrator
 *
 */
public class FiledVO extends CommonVO{

	private static final long serialVersionUID = 1L;
	private String applyInfoId; //贷款申请ID
	private String filedId;// 诉讼申请ID
	private String loanNumber; // 贷款编号
	private String lawsuitVehicle;//车辆编号
	private String customerName;// 客户名称
	private String cardNumber; // 身份证号码
	private String customerPhone;// 客户手机号
	private String appliedAmount;// 申请金额
	private String appliedDate; //申请时间
	
	private String frameNumber;//车架号
	private String engineNumber;//发动机号
	private String mortgageInfoId;//抵押物
	
	private String periods;// 期数
	private String refundCapital;// 已还本金
	private String refundPeriods;// 已还期数
	private String refundInterest;// 已还利息
	private String refundPenalty; // 已交罚息
	private String overduePeriods;// 逾期期数
	private String overdueAmount;// 逾期总金额
	private String residueCapital;// 剩余本金
	private String interestPayable;// 应交利息
	private String interestPenalty;// 应交罚息
	private String infoStatus;// 申请状态
	
	private String applyName;//申请人
	private String applyDate;//审核时间
	private String applyRemark;//申请备注
	private String auditName;// 审核人
	private String auditDate;// 审核时间
	private String auditRemark;//审核备注
	private String startDate; //开始时间
	private String endDate; //结束时间
	private String registerRemark;//登记备注
	private String registerStatus;//登记处理
	private String[] remark = new String[] {};//跟进备注集合
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getLawsuitVehicle() {
		return lawsuitVehicle;
	}
	public void setLawsuitVehicle(String lawsuitVehicle) {
		this.lawsuitVehicle = lawsuitVehicle;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getAppliedAmount() {
		return appliedAmount;
	}
	public void setAppliedAmount(String appliedAmount) {
		this.appliedAmount = appliedAmount;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getFrameNumber() {
		return frameNumber;
	}
	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getMortgageInfoId() {
		return mortgageInfoId;
	}
	public void setMortgageInfoId(String mortgageInfoId) {
		this.mortgageInfoId = mortgageInfoId;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getRefundCapital() {
		return refundCapital;
	}
	public void setRefundCapital(String refundCapital) {
		this.refundCapital = refundCapital;
	}
	public String getRefundPeriods() {
		return refundPeriods;
	}
	public void setRefundPeriods(String refundPeriods) {
		this.refundPeriods = refundPeriods;
	}
	public String getRefundInterest() {
		return refundInterest;
	}
	public void setRefundInterest(String refundInterest) {
		this.refundInterest = refundInterest;
	}
	public String getRefundPenalty() {
		return refundPenalty;
	}
	public void setRefundPenalty(String refundPenalty) {
		this.refundPenalty = refundPenalty;
	}
	public String getOverduePeriods() {
		return overduePeriods;
	}
	public void setOverduePeriods(String overduePeriods) {
		this.overduePeriods = overduePeriods;
	}
	public String getOverdueAmount() {
		return overdueAmount;
	}
	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	public String getResidueCapital() {
		return residueCapital;
	}
	public void setResidueCapital(String residueCapital) {
		this.residueCapital = residueCapital;
	}
	public String getInterestPayable() {
		return interestPayable;
	}
	public void setInterestPayable(String interestPayable) {
		this.interestPayable = interestPayable;
	}
	public String getInterestPenalty() {
		return interestPenalty;
	}
	public void setInterestPenalty(String interestPenalty) {
		this.interestPenalty = interestPenalty;
	}
	public String getInfoStatus() {
		return infoStatus;
	}
	public void setInfoStatus(String infoStatus) {
		this.infoStatus = infoStatus;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyRemark() {
		return applyRemark;
	}
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegisterRemark() {
		return registerRemark;
	}
	public void setRegisterRemark(String registerRemark) {
		this.registerRemark = registerRemark;
	}
	public String getRegisterStatus() {
		return registerStatus;
	}
	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}
	public String[] getRemark() {
		return remark;
	}
	public void setRemark(String[] remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
