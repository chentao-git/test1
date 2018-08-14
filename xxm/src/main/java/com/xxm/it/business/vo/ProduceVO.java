package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 产品对象
 * 
 * @author Administrator
 *
 */
public class ProduceVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String productId;//产品id
	private String productName;//产品名称
	private String periods;//期数(月)
	private String publishDate;//发布日期
	private String haltSalesDate;//停售日期
	private String suggestApplyAmount;//建议申请数额(元)
	private String minApplyAmount;//最低申请数额(元)
	private String maxApplyAmount;//最高申请数额(元)
	private String aheadInterest;//提前还贷罚息(%)
	private String impunityDeadline;//免罚期限(天)
	private String overdueInterest;//逾期还贷罚息(%)
	private String monthSpreads;//月利差(%)
	private String serviceCharge;//手续费(%)
	private String intermediaryFee;//中介手续费(%)
	private String securityDeposit;//担保费(%)
	private String securityDepositCountVl;//担保费计算方式(%)
	private String deductionCountVl;//提成计算基准(%)
	private String directSellingDeduction;//直销模式提成%
	private String agencyDeduction;//代理模式提成%
	private String repaymentDateTypeVl;//还款日期类型
	private String repaymentDate;//还款日期
	private String stockCarVl;//存量车
	private String needGuarantorInfoVl;//担保人信息
	private String aheadPrepaymentVl;//提前还贷
	private String rateMethodVl;//利率计算方式
	private String rateMethodName;
	private String rate;//利率
	private String repaymentMethodVl;//还款方式
	private String repaymentMethodName;//还款方式name
	private String produceStatus;//产品状态
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getHaltSalesDate() {
		return haltSalesDate;
	}
	public void setHaltSalesDate(String haltSalesDate) {
		this.haltSalesDate = haltSalesDate;
	}
	public String getSuggestApplyAmount() {
		return suggestApplyAmount;
	}
	public void setSuggestApplyAmount(String suggestApplyAmount) {
		this.suggestApplyAmount = suggestApplyAmount;
	}
	public String getMinApplyAmount() {
		return minApplyAmount;
	}
	public void setMinApplyAmount(String minApplyAmount) {
		this.minApplyAmount = minApplyAmount;
	}
	public String getMaxApplyAmount() {
		return maxApplyAmount;
	}
	public void setMaxApplyAmount(String maxApplyAmount) {
		this.maxApplyAmount = maxApplyAmount;
	}
	public String getAheadInterest() {
		return aheadInterest;
	}
	public void setAheadInterest(String aheadInterest) {
		this.aheadInterest = aheadInterest;
	}
	public String getImpunityDeadline() {
		return impunityDeadline;
	}
	public void setImpunityDeadline(String impunityDeadline) {
		this.impunityDeadline = impunityDeadline;
	}
	public String getOverdueInterest() {
		return overdueInterest;
	}
	public void setOverdueInterest(String overdueInterest) {
		this.overdueInterest = overdueInterest;
	}
	public String getMonthSpreads() {
		return monthSpreads;
	}
	public void setMonthSpreads(String monthSpreads) {
		this.monthSpreads = monthSpreads;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getIntermediaryFee() {
		return intermediaryFee;
	}
	public void setIntermediaryFee(String intermediaryFee) {
		this.intermediaryFee = intermediaryFee;
	}
	public String getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public String getSecurityDepositCountVl() {
		return securityDepositCountVl;
	}
	public void setSecurityDepositCountVl(String securityDepositCountVl) {
		this.securityDepositCountVl = securityDepositCountVl;
	}
	public String getDeductionCountVl() {
		return deductionCountVl;
	}
	public void setDeductionCountVl(String deductionCountVl) {
		this.deductionCountVl = deductionCountVl;
	}
	public String getDirectSellingDeduction() {
		return directSellingDeduction;
	}
	public void setDirectSellingDeduction(String directSellingDeduction) {
		this.directSellingDeduction = directSellingDeduction;
	}
	public String getAgencyDeduction() {
		return agencyDeduction;
	}
	public void setAgencyDeduction(String agencyDeduction) {
		this.agencyDeduction = agencyDeduction;
	}
	public String getRepaymentDateTypeVl() {
		return repaymentDateTypeVl;
	}
	public void setRepaymentDateTypeVl(String repaymentDateTypeVl) {
		this.repaymentDateTypeVl = repaymentDateTypeVl;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public String getStockCarVl() {
		return stockCarVl;
	}
	public void setStockCarVl(String stockCarVl) {
		this.stockCarVl = stockCarVl;
	}
	public String getNeedGuarantorInfoVl() {
		return needGuarantorInfoVl;
	}
	public void setNeedGuarantorInfoVl(String needGuarantorInfoVl) {
		this.needGuarantorInfoVl = needGuarantorInfoVl;
	}
	public String getAheadPrepaymentVl() {
		return aheadPrepaymentVl;
	}
	public void setAheadPrepaymentVl(String aheadPrepaymentVl) {
		this.aheadPrepaymentVl = aheadPrepaymentVl;
	}
	public String getRateMethodVl() {
		return rateMethodVl;
	}
	public void setRateMethodVl(String rateMethodVl) {
		this.rateMethodVl = rateMethodVl;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getRepaymentMethodVl() {
		return repaymentMethodVl;
	}
	public void setRepaymentMethodVl(String repaymentMethodVl) {
		this.repaymentMethodVl = repaymentMethodVl;
	}
	public String getProduceStatus() {
		return produceStatus;
	}
	public void setProduceStatus(String produceStatus) {
		this.produceStatus = produceStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRepaymentMethodName() {
		return repaymentMethodName;
	}
	public void setRepaymentMethodName(String repaymentMethodName) {
		this.repaymentMethodName = repaymentMethodName;
	}
	public String getRateMethodName() {
		return rateMethodName;
	}
	public void setRateMethodName(String rateMethodName) {
		this.rateMethodName = rateMethodName;
	}

}
