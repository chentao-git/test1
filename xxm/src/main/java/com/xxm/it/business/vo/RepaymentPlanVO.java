package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 还款计划对象
 * 
 * @author Administrator
 *
 */
public class RepaymentPlanVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String repaymentPlanId;//还款计划ID
	private String periods;//期数
	private String returnPrincipal;//应还本金
	private String returnInterest;//应还利息
	private String returnDefaultInterest;//应还罚息
	private String guaranteeFee;//担保费
	private String amountPayable;//应还金额
	private String returnDate;//应还日期
	private String remarks;//备注
	private String quotedPriceId;//报价信息表ID
	private String startCreateDate;// 开始
	private String endCreateDate;// 结束
	private String status; //状态 0为逾期 1为已还款
	
	private String collectionCount; //催收次数
	
	private String applyInfoId; //申请id
	private String loanContractNo;//贷款合同编号
	private String productionVl;//产品信息
	private String productionName;
	private String applyInfoStatus;//申请信息状态
	
	private String baseCustomerName;//客户名称
	private String certType;//借款人证件类型
	private String certNo;//证件号
	private String mobileNo; //手机
	private String fixedLine; //电话
	
	
	public String getLoanContractNo() {
		return loanContractNo;
	}
	public void setLoanContractNo(String loanContractNo) {
		this.loanContractNo = loanContractNo;
	}
	public String getProductionVl() {
		return productionVl;
	}
	public void setProductionVl(String productionVl) {
		this.productionVl = productionVl;
	}
	public String getBaseCustomerName() {
		return baseCustomerName;
	}
	public void setBaseCustomerName(String baseCustomerName) {
		this.baseCustomerName = baseCustomerName;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getApplyInfoStatus() {
		return applyInfoStatus;
	}
	public void setApplyInfoStatus(String applyInfoStatus) {
		this.applyInfoStatus = applyInfoStatus;
	}
	public String getStartCreateDate() {
		return startCreateDate;
	}
	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}
	public String getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	public String getRepaymentPlanId() {
		return repaymentPlanId;
	}
	public void setRepaymentPlanId(String repaymentPlanId) {
		this.repaymentPlanId = repaymentPlanId;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getReturnPrincipal() {
		return returnPrincipal;
	}
	public void setReturnPrincipal(String returnPrincipal) {
		this.returnPrincipal = returnPrincipal;
	}
	public String getReturnInterest() {
		return returnInterest;
	}
	public void setReturnInterest(String returnInterest) {
		this.returnInterest = returnInterest;
	}
	public String getReturnDefaultInterest() {
		return returnDefaultInterest;
	}
	public void setReturnDefaultInterest(String returnDefaultInterest) {
		this.returnDefaultInterest = returnDefaultInterest;
	}
	public String getGuaranteeFee() {
		return guaranteeFee;
	}
	public void setGuaranteeFee(String guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}
	public String getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getQuotedPriceId() {
		return quotedPriceId;
	}
	public void setQuotedPriceId(String quotedPriceId) {
		this.quotedPriceId = quotedPriceId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFixedLine() {
		return fixedLine;
	}
	public void setFixedLine(String fixedLine) {
		this.fixedLine = fixedLine;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(String collectionCount) {
		this.collectionCount = collectionCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
