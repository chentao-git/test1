package com.xxm.it.business.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

/**
 * 补充基本信息对象
 * 
 * @author Administrator
 *
 */
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class SupplementBaseVO extends CommonVO {

	@XmlTransient
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String supplementBaseId;//补充基本信息ID
	@XmlTransient
	private String paymentApproachVL;//还款途径
	@XmlTransient
	private String paymentApproachName;//还款途径名称
	@XmlTransient
	private String paymentMethodName;//还款方式名称
	@XmlTransient
	private String paymentPeriods;//还款期数
	@XmlTransient
	private String isAutoPay;//是否支持自动扣款还款
	@XmlTransient
	private String isAutoPayName;//是否支持自动扣款还款名称
	@XmlTransient
	private String interestCalculatedVL;//利率计算方式
	@XmlTransient
	private String interestCalculatedName;//利率计算方式名称
	@XmlTransient
	private String payBearerVL;//还款手续承担方
	@XmlTransient
	private String payBearerName;//还款手续承担方名称
	@XmlTransient
	private String serviceCharge;//手续费，百分比
	@XmlTransient
	private String paymentDateTypeVL;//还款日期类型
	@XmlTransient
	private String paymentDateTypeName;//还款日期类型名称
	@XmlTransient
	private String paymentDateNum;//还款日期数
	@XmlTransient
	private String guaranteeCalculatedVL;//担保费计算方式
	@XmlTransient
	private String guaranteeCalculatedName;//担保费计算方式名称
	@XmlTransient
	private String agentServiceCharge;//中介手续费，百分比
	@XmlTransient
	private String guarantee;//担保费，百分比
	@XmlTransient
	private String rateOfPremium;//保险费率，百分比
	@XmlTransient
	private String prepaymentVL;//允许提前还款
	@XmlTransient
	private String prepaymentName;//允许提前还款名称
	@XmlTransient
	private String defaultInterestVL;//罚息计算基础
	@XmlTransient
	private String defaultInterestName;//罚息计算基础名称
	@XmlTransient
	private String letOffDate;//免罚期限
	@XmlTransient
	private String overdueInterest;//逾期罚息，百分比
	@XmlTransient
	private String applyInfoId;//
	@XmlTransient
	private String applyLoansAmount;//申请贷款数额(元)
	@XmlTransient
	private String tateModeName; //利率模式名称

	
	@XmlElement(name="CarDealerName")
	private String autoDealerName; //汽车经销商代码/名称
	@XmlElement(name="AffiliatedUnitCode")
	private String orgCode; //挂靠单位代码
	@XmlElement(name="AffiliatedUnitName")
	private String companyAffiliatedName; //挂靠单位名称
	@XmlElement(name="NoFirstFlag")
	private String noFirstFlag;//投保人是否非首次购车
	@XmlTransient
	private String noFirstFlagName;//投保人是否非首次购车名称
	@XmlElement(name="TaxiComQuantity")
	private String taxiComQuantity;//出租车企业贷款购车数量
	@XmlElement(name="EnterpQuantity")
	private String enterpQuantity;//企事业单位贷款团购数量
	@XmlElement(name="GuaranteeType")
	private String guaranteeType;//抵(质)押物性质
	@XmlTransient
	private String guaranteeTypeName;//抵(质)押物性质名称
	@XmlElement(name="GuaranteeName")
	private String guaranteeName;//抵(质)押物名称
	@XmlElement(name="DeliverDate")
	private String deliverDate;//抵(质)押权生效日期
	@XmlElement(name="MortgageNo")
	private String mortgageNo;//登记证号
//	@XmlElement(name="LoanContractNo")
	@XmlTransient
	private String loanContractNo;//借款合同号
	@XmlElement(name="LoanType")
	private String paymentMethodVL;//还款方式
	@XmlElement(name="LoanStartDate")
	private String loanStartDate;//贷款期限起期
	@XmlElement(name="LoanEndDate")
	private String loanEndDate;//贷款期限止期
	@XmlElement(name="FirstpayRAate")
	private String firstpayRAate;//首付款比例
	@XmlElement(name="FirstPaid")
	private String firstPaid; //首付金额
	
	@XmlElement(name="FirstRate")
	private String firstRate;//未保责任比例
	@XmlElement(name="OwnLiabRatio")
	private String ownLiabRatio;//被保险人自担责任比例
	@XmlElement(name="OtherLiabRatio")
	private String otherLiabRatio;//第三方承担责任比例
	@XmlElement(name="Recognizance")
	private String recognizance;//责任保证金
	@XmlElement(name="LoanAmount")
	private String loanAmount;//贷款本金
	@XmlElement(name="LoanRate")
	private String loanRate;//贷款利率
	@XmlElement(name="LoanNo4")
	private String interest;//利息
	@XmlElement(name="PerRepaidAmount")
	private String perRepaidAmount;//月均还款额
	@XmlElement(name="RepyFq")
	private String repyFq;//还款频率
	@XmlTransient
	private String applyPurpost;//申请用途
	
	@XmlElement(name="LoanPurpost")
	private String loanPurpost;//资金用途
	@XmlTransient
	private String loanPurpostName;//资金用途名称
	@XmlElement(name="UseNature")
	private String useNature;//原车辆使用性质
	@XmlElement(name="usesTp")
	private String usesTp;//车辆用途
	@XmlElement(name="IsLoanAccount")
	private String isLoanAccount;//入账是否借款人账号
	@XmlTransient
	private String isLoanAccountName;//入账是否借款人账号 名称
	@XmlElement(name="LoanAcount")
	private String loanAcount;//贷款接收账号名称
	@XmlElement(name="LoanAcountNo")
	private String loanAcountNo;//贷款接收账号
	@XmlElement(name="LoanAcountBank")
	private String loanAcountBank;//贷款接收账号开户行名称
	@XmlElement(name="LoanAcountBankNo")
	private String loanAcountBankNo;//贷款接收账号开户行联行行号
	@XmlElement(name="ApplyCurrency")
	private String applyCurrency;//申请币种
	@XmlElement(name="ApplyAmt")
	private String applyAmt;//申请金额
	@XmlElement(name="applNosInst")
	private String applNosInst;//申请期限
	@XmlElement(name="applyDate")
	private String applyDate;//申请日期
	@XmlElement(name="atrsDueDay")
	private String atrsDueDay;//还款日
	@XmlElement(name="TateMode")
	private String tateMode; //利率模式
	@XmlElement(name="NextRepcOption")
	private String nextRepcOption;//利率调整方式
	@XmlElement(name="FixRateInd")
	private String fixRateInd;//是否固定利率
	@XmlTransient
	private String fixRateIndName;//是否固定利率名称
	@XmlElement(name="IntAdjPct")
	private String intAdjPct;//正常利率上浮比例
	@XmlElement(name="custRate")
	private String custRate;//客户利率
	
	@XmlElement(name="ApplyNo")
	private String applyNo;//申请书编号
	@XmlElement(name="MktProdType")
	private String mktProdType;//流程标志
	@XmlTransient
	private String mktProdTypeName;//流程标志名称
	@XmlElement(name="contct")
	private String contct;//联系方式
	@XmlTransient
	private String contctName;//联系方式名称
	@XmlElement(name="telNum")
	private String telNum;//电话号码
	@XmlElement(name="dlerPh")
	private String dlerPh;//经销商联系电话
	@XmlElement(name="LinkType")
	private String linkType;//联系人类别
	@XmlTransient
	private String linkTypeName;//联系人类别名称
	@XmlElement(name="LinkName")
	private String linkName;//联系人姓名
	@XmlElement(name="LinkPhone")
	private String linkPhone;//联系人电话
	
	@XmlTransient
	private String serialNo;//请求序列号
	@XmlTransient
	private String riskCode;//险种代码
	@XmlTransient
	private String startDate;//起保日期
	@XmlTransient
	private String endDate;//终保日期
	@XmlTransient
	private String startHour;//起保小时
	@XmlTransient
	private String endHour;//终保小时
	@XmlTransient
	private String sumAmount;//保单总保险金额
	@XmlTransient
	private String sumPremium;//保单总保险费
	@XmlTransient
	private String arguSolution;//争议解决方式
	@XmlTransient
	private String arguSolutionName;//争议解决方式名称
	@XmlTransient
	private String iscoinTypeFlag;//是否是联保业务出单
	@XmlTransient
	private String iscoinTypeFlagName;//是否是联保业务出单
	
	public String getSupplementBaseId() {
		return supplementBaseId;
	}
	public void setSupplementBaseId(String supplementBaseId) {
		this.supplementBaseId = supplementBaseId;
	}
	public String getPaymentApproachVL() {
		return paymentApproachVL;
	}
	public void setPaymentApproachVL(String paymentApproachVL) {
		this.paymentApproachVL = paymentApproachVL;
	}
	public String getPaymentMethodVL() {
		return paymentMethodVL;
	}
	public void setPaymentMethodVL(String paymentMethodVL) {
		this.paymentMethodVL = paymentMethodVL;
	}
	public String getPaymentPeriods() {
		return paymentPeriods;
	}
	public void setPaymentPeriods(String paymentPeriods) {
		this.paymentPeriods = paymentPeriods;
	}
	public String getIsAutoPay() {
		return isAutoPay;
	}
	public void setIsAutoPay(String isAutoPay) {
		this.isAutoPay = isAutoPay;
	}
	public String getInterestCalculatedVL() {
		return interestCalculatedVL;
	}
	public void setInterestCalculatedVL(String interestCalculatedVL) {
		this.interestCalculatedVL = interestCalculatedVL;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getPayBearerVL() {
		return payBearerVL;
	}
	public void setPayBearerVL(String payBearerVL) {
		this.payBearerVL = payBearerVL;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getPaymentDateTypeVL() {
		return paymentDateTypeVL;
	}
	public void setPaymentDateTypeVL(String paymentDateTypeVL) {
		this.paymentDateTypeVL = paymentDateTypeVL;
	}
	public String getPaymentDateNum() {
		return paymentDateNum;
	}
	public void setPaymentDateNum(String paymentDateNum) {
		this.paymentDateNum = paymentDateNum;
	}
	public String getGuaranteeCalculatedVL() {
		return guaranteeCalculatedVL;
	}
	public void setGuaranteeCalculatedVL(String guaranteeCalculatedVL) {
		this.guaranteeCalculatedVL = guaranteeCalculatedVL;
	}
	public String getAgentServiceCharge() {
		return agentServiceCharge;
	}
	public void setAgentServiceCharge(String agentServiceCharge) {
		this.agentServiceCharge = agentServiceCharge;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getRateOfPremium() {
		return rateOfPremium;
	}
	public void setRateOfPremium(String rateOfPremium) {
		this.rateOfPremium = rateOfPremium;
	}
	public String getPrepaymentVL() {
		return prepaymentVL;
	}
	public void setPrepaymentVL(String prepaymentVL) {
		this.prepaymentVL = prepaymentVL;
	}
	public String getDefaultInterestVL() {
		return defaultInterestVL;
	}
	public void setDefaultInterestVL(String defaultInterestVL) {
		this.defaultInterestVL = defaultInterestVL;
	}
	public String getLetOffDate() {
		return letOffDate;
	}
	public void setLetOffDate(String letOffDate) {
		this.letOffDate = letOffDate;
	}
	public String getOverdueInterest() {
		return overdueInterest;
	}
	public void setOverdueInterest(String overdueInterest) {
		this.overdueInterest = overdueInterest;
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
	public String getPaymentApproachName() {
		return paymentApproachName;
	}
	public void setPaymentApproachName(String paymentApproachName) {
		this.paymentApproachName = paymentApproachName;
	}
	public String getPaymentMethodName() {
		return paymentMethodName;
	}
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}
	public String getIsAutoPayName() {
		return isAutoPayName;
	}
	public void setIsAutoPayName(String isAutoPayName) {
		this.isAutoPayName = isAutoPayName;
	}
	public String getInterestCalculatedName() {
		return interestCalculatedName;
	}
	public void setInterestCalculatedName(String interestCalculatedName) {
		this.interestCalculatedName = interestCalculatedName;
	}
	public String getPayBearerName() {
		return payBearerName;
	}
	public void setPayBearerName(String payBearerName) {
		this.payBearerName = payBearerName;
	}
	public String getPaymentDateTypeName() {
		return paymentDateTypeName;
	}
	public void setPaymentDateTypeName(String paymentDateTypeName) {
		this.paymentDateTypeName = paymentDateTypeName;
	}
	public String getGuaranteeCalculatedName() {
		return guaranteeCalculatedName;
	}
	public void setGuaranteeCalculatedName(String guaranteeCalculatedName) {
		this.guaranteeCalculatedName = guaranteeCalculatedName;
	}
	public String getPrepaymentName() {
		return prepaymentName;
	}
	public void setPrepaymentName(String prepaymentName) {
		this.prepaymentName = prepaymentName;
	}
	public String getDefaultInterestName() {
		return defaultInterestName;
	}
	public void setDefaultInterestName(String defaultInterestName) {
		this.defaultInterestName = defaultInterestName;
	}
	public String getNoFirstFlag() {
		return noFirstFlag;
	}
	public void setNoFirstFlag(String noFirstFlag) {
		this.noFirstFlag = noFirstFlag;
	}
	public String getTaxiComQuantity() {
		return taxiComQuantity;
	}
	public void setTaxiComQuantity(String taxiComQuantity) {
		this.taxiComQuantity = taxiComQuantity;
	}
	public String getEnterpQuantity() {
		return enterpQuantity;
	}
	public void setEnterpQuantity(String enterpQuantity) {
		this.enterpQuantity = enterpQuantity;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getGuaranteeName() {
		return guaranteeName;
	}
	public void setGuaranteeName(String guaranteeName) {
		this.guaranteeName = guaranteeName;
	}
	public String getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}
	public String getMortgageNo() {
		return mortgageNo;
	}
	public void setMortgageNo(String mortgageNo) {
		this.mortgageNo = mortgageNo;
	}
	public String getLoanContractNo() {
		return loanContractNo;
	}
	public void setLoanContractNo(String loanContractNo) {
		this.loanContractNo = loanContractNo;
	}
	public String getLoanStartDate() {
		return loanStartDate;
	}
	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}
	public String getLoanEndDate() {
		return loanEndDate;
	}
	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public String getFirstpayRAate() {
		return firstpayRAate;
	}
	public void setFirstpayRAate(String firstpayRAate) {
		this.firstpayRAate = firstpayRAate;
	}
	public String getFirstRate() {
		return firstRate;
	}
	public void setFirstRate(String firstRate) {
		this.firstRate = firstRate;
	}
	public String getOwnLiabRatio() {
		return ownLiabRatio;
	}
	public void setOwnLiabRatio(String ownLiabRatio) {
		this.ownLiabRatio = ownLiabRatio;
	}
	public String getOtherLiabRatio() {
		return otherLiabRatio;
	}
	public void setOtherLiabRatio(String otherLiabRatio) {
		this.otherLiabRatio = otherLiabRatio;
	}
	public String getRecognizance() {
		return recognizance;
	}
	public void setRecognizance(String recognizance) {
		this.recognizance = recognizance;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}
	public String getPerRepaidAmount() {
		return perRepaidAmount;
	}
	public void setPerRepaidAmount(String perRepaidAmount) {
		this.perRepaidAmount = perRepaidAmount;
	}
	public String getRepyFq() {
		return repyFq;
	}
	public void setRepyFq(String repyFq) {
		this.repyFq = repyFq;
	}
	public String getLoanPurpost() {
		return loanPurpost;
	}
	public void setLoanPurpost(String loanPurpost) {
		this.loanPurpost = loanPurpost;
	}
	public String getUseNature() {
		return useNature;
	}
	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}
	public String getUsesTp() {
		return usesTp;
	}
	public void setUsesTp(String usesTp) {
		this.usesTp = usesTp;
	}
	public String getIsLoanAccount() {
		return isLoanAccount;
	}
	public void setIsLoanAccount(String isLoanAccount) {
		this.isLoanAccount = isLoanAccount;
	}
	public String getLoanAcount() {
		return loanAcount;
	}
	public void setLoanAcount(String loanAcount) {
		this.loanAcount = loanAcount;
	}
	public String getLoanAcountNo() {
		return loanAcountNo;
	}
	public void setLoanAcountNo(String loanAcountNo) {
		this.loanAcountNo = loanAcountNo;
	}
	public String getLoanAcountBank() {
		return loanAcountBank;
	}
	public void setLoanAcountBank(String loanAcountBank) {
		this.loanAcountBank = loanAcountBank;
	}
	public String getLoanAcountBankNo() {
		return loanAcountBankNo;
	}
	public void setLoanAcountBankNo(String loanAcountBankNo) {
		this.loanAcountBankNo = loanAcountBankNo;
	}
	public String getApplyCurrency() {
		return applyCurrency;
	}
	public void setApplyCurrency(String applyCurrency) {
		this.applyCurrency = applyCurrency;
	}
	public String getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}
	public String getApplNosInst() {
		return applNosInst;
	}
	public void setApplNosInst(String applNosInst) {
		this.applNosInst = applNosInst;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getAtrsDueDay() {
		return atrsDueDay;
	}
	public void setAtrsDueDay(String atrsDueDay) {
		this.atrsDueDay = atrsDueDay;
	}
	public String getNextRepcOption() {
		return nextRepcOption;
	}
	public void setNextRepcOption(String nextRepcOption) {
		this.nextRepcOption = nextRepcOption;
	}
	public String getFixRateInd() {
		return fixRateInd;
	}
	public void setFixRateInd(String fixRateInd) {
		this.fixRateInd = fixRateInd;
	}
	public String getIntAdjPct() {
		return intAdjPct;
	}
	public void setIntAdjPct(String intAdjPct) {
		this.intAdjPct = intAdjPct;
	}
	public String getCustRate() {
		return custRate;
	}
	public void setCustRate(String custRate) {
		this.custRate = custRate;
	}
	public String getApplyPurpost() {
		return applyPurpost;
	}
	public void setApplyPurpost(String applyPurpost) {
		this.applyPurpost = applyPurpost;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getMktProdType() {
		return mktProdType;
	}
	public void setMktProdType(String mktProdType) {
		this.mktProdType = mktProdType;
	}
	public String getContct() {
		return contct;
	}
	public void setContct(String contct) {
		this.contct = contct;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getDlerPh() {
		return dlerPh;
	}
	public void setDlerPh(String dlerPh) {
		this.dlerPh = dlerPh;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
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
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}
	public String getArguSolution() {
		return arguSolution;
	}
	public void setArguSolution(String arguSolution) {
		this.arguSolution = arguSolution;
	}
	public String getIscoinTypeFlag() {
		return iscoinTypeFlag;
	}
	public void setIscoinTypeFlag(String iscoinTypeFlag) {
		this.iscoinTypeFlag = iscoinTypeFlag;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getAutoDealerName() {
		return autoDealerName;
	}
	public void setAutoDealerName(String autoDealerName) {
		this.autoDealerName = autoDealerName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCompanyAffiliatedName() {
		return companyAffiliatedName;
	}
	public void setCompanyAffiliatedName(String companyAffiliatedName) {
		this.companyAffiliatedName = companyAffiliatedName;
	}
	public String getFirstPaid() {
		return firstPaid;
	}
	public void setFirstPaid(String firstPaid) {
		this.firstPaid = firstPaid;
	}
	public String getTateMode() {
		return tateMode;
	}
	public void setTateMode(String tateMode) {
		this.tateMode = tateMode;
	}
	public String getNoFirstFlagName() {
		return noFirstFlagName;
	}
	public void setNoFirstFlagName(String noFirstFlagName) {
		this.noFirstFlagName = noFirstFlagName;
	}
	public String getGuaranteeTypeName() {
		return guaranteeTypeName;
	}
	public void setGuaranteeTypeName(String guaranteeTypeName) {
		this.guaranteeTypeName = guaranteeTypeName;
	}
	public String getIsLoanAccountName() {
		return isLoanAccountName;
	}
	public void setIsLoanAccountName(String isLoanAccountName) {
		this.isLoanAccountName = isLoanAccountName;
	}
	public String getFixRateIndName() {
		return fixRateIndName;
	}
	public void setFixRateIndName(String fixRateIndName) {
		this.fixRateIndName = fixRateIndName;
	}
	public String getMktProdTypeName() {
		return mktProdTypeName;
	}
	public void setMktProdTypeName(String mktProdTypeName) {
		this.mktProdTypeName = mktProdTypeName;
	}
	public String getContctName() {
		return contctName;
	}
	public void setContctName(String contctName) {
		this.contctName = contctName;
	}
	public String getLinkTypeName() {
		return linkTypeName;
	}
	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}
	public String getArguSolutionName() {
		return arguSolutionName;
	}
	public void setArguSolutionName(String arguSolutionName) {
		this.arguSolutionName = arguSolutionName;
	}
	public String getIscoinTypeFlagName() {
		return iscoinTypeFlagName;
	}
	public void setIscoinTypeFlagName(String iscoinTypeFlagName) {
		this.iscoinTypeFlagName = iscoinTypeFlagName;
	}
	public String getApplyLoansAmount() {
		return applyLoansAmount;
	}
	public void setApplyLoansAmount(String applyLoansAmount) {
		this.applyLoansAmount = applyLoansAmount;
	}
	public String getTateModeName() {
		return tateModeName;
	}
	public void setTateModeName(String tateModeName) {
		this.tateModeName = tateModeName;
	}
	public String getLoanPurpostName() {
		return loanPurpostName;
	}
	public void setLoanPurpostName(String loanPurpostName) {
		this.loanPurpostName = loanPurpostName;
	}


}
