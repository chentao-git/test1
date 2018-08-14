package com.xxm.it.business.vo;


import java.util.ArrayList;
import java.util.List;

import com.xxm.it.system.vo.CommonVO;

/**
 * 报价信息对象
 * 
 * @author Administrator
 *
 */
public class QuotedPriceVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String quotedPriceId;//报价ID
	private String actualPrice;// 成交价
	private String preRentRateVL;//首付比例
	private String preRentRateName;//首付比例名称
	private String supplierCashPool;//供应商保证金池
	private String signDate;//签约日期
	private String factoryDiscount;//厂家贴息
	private String consultingFee;//咨询费
	private String planVL;//方案
	private String planName;//方案名称
	private String purchasePrice;//
	private String serviceCharge;//手续费
	private String applyInfoId;//
	private String downPayment;//首付
	private String financingAmount;//融资金额
	private String rentTotalAmount;//租金总额
	private String interestTotalAmount;//利息总额
	private String amountInsured;//保证保险承保金额
	private String guidePrice;//车辆指导价
	private String collectPremium;//代收担保费
	private List<RepaymentPlanVO> repaymentPlanList=new ArrayList<RepaymentPlanVO>();
	public String getQuotedPriceId() {
		return quotedPriceId;
	}
	public void setQuotedPriceId(String quotedPriceId) {
		this.quotedPriceId = quotedPriceId;
	}
	public String getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getPreRentRateVL() {
		return preRentRateVL;
	}
	public void setPreRentRateVL(String preRentRateVL) {
		this.preRentRateVL = preRentRateVL;
	}
	public String getSupplierCashPool() {
		return supplierCashPool;
	}
	public void setSupplierCashPool(String supplierCashPool) {
		this.supplierCashPool = supplierCashPool;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getFactoryDiscount() {
		return factoryDiscount;
	}
	public void setFactoryDiscount(String factoryDiscount) {
		this.factoryDiscount = factoryDiscount;
	}
	public String getConsultingFee() {
		return consultingFee;
	}
	public void setConsultingFee(String consultingFee) {
		this.consultingFee = consultingFee;
	}
	public String getPlanVL() {
		return planVL;
	}
	public void setPlanVL(String planVL) {
		this.planVL = planVL;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<RepaymentPlanVO> getRepaymentPlanList() {
		return repaymentPlanList;
	}
	public void setRepaymentPlanList(List<RepaymentPlanVO> repaymentPlanList) {
		this.repaymentPlanList = repaymentPlanList;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPreRentRateName() {
		return preRentRateName;
	}
	public void setPreRentRateName(String preRentRateName) {
		this.preRentRateName = preRentRateName;
	}
	public String getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}
	public String getFinancingAmount() {
		return financingAmount;
	}
	public void setFinancingAmount(String financingAmount) {
		this.financingAmount = financingAmount;
	}
	public String getRentTotalAmount() {
		return rentTotalAmount;
	}
	public void setRentTotalAmount(String rentTotalAmount) {
		this.rentTotalAmount = rentTotalAmount;
	}
	public String getInterestTotalAmount() {
		return interestTotalAmount;
	}
	public void setInterestTotalAmount(String interestTotalAmount) {
		this.interestTotalAmount = interestTotalAmount;
	}
	public String getAmountInsured() {
		return amountInsured;
	}
	public void setAmountInsured(String amountInsured) {
		this.amountInsured = amountInsured;
	}
	public String getGuidePrice() {
		return guidePrice;
	}
	public void setGuidePrice(String guidePrice) {
		this.guidePrice = guidePrice;
	}
	public String getCollectPremium() {
		return collectPremium;
	}
	public void setCollectPremium(String collectPremium) {
		this.collectPremium = collectPremium;
	}


}
