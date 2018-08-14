package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="MortgageInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class MortgageinfoVO {
	@XmlTransient
	private String mortgageInfoId;
	@XmlTransient
	private String applyInfoId;
	@XmlElement(name = "ProposalNo")
	private String proposalNo; //投保单号	
	@XmlElement(name = "loanNo")
	private String loanNo;//借据号	
	@XmlElement(name = "applyNo")
	private String applyNo;//申请书编号	
	@XmlElement(name = "policyNo")
	private String policyNo;//保单号	
	@XmlElement(name = "AppliName")
	private String appliName;//投保人名称	
	@XmlElement(name = "InsuredName")
	private String insuredName;//被保险人名称	
	@XmlElement(name = "UnitAmount")
	private String unitAmount;//抵押保证金	
	@XmlElement(name = "LoanNo3")
	private String loanNo3;//抵押合同号	
	@XmlElement(name = "GuaranteeType")
	private String guaranteeType;//抵(质)押物性质	
	@XmlElement(name = "GuaranteeName")
	private String guaranteeName;//抵(质)押物名称	
	@XmlElement(name = "DeliverDate")
	private String deliverDate;//抵(质)押权生效日期	
	@XmlElement(name = "PaidTimes")
	private String paidTimes;//押品所有人所占份额	
	@XmlElement(name = "RepaidType")
	private String repaidType;//是否需要办理保险	
	@XmlElement(name = "LoanBankCode")
	private String loanBankCode;//抵质押品法律有效性	
	@XmlElement(name = "LoanBankName")
	private String loanBankName;//抵质押品变现能力	
	@XmlElement(name = "LoanNature")
	private String loanNature;//押品是否已出租	
	@XmlElement(name = "OtherMortgage")
	private String otherMortgage;//是否在其它机构设置抵质押	
	@XmlElement(name = "OtherNature")
	private String oherNature;//行驶里程数(公里)	
	@XmlElement(name = "UseNatureCode")
	private String useNatureCode;//是否运营车辆	
	@XmlElement(name = "PreChar1")
	private String preChar1;//权证完备状态	
	@XmlElement(name = "LandAgent")
	private String landAgent;//抵押权证信息—抵押权人	
	@XmlElement(name = "PreChar2")
	private String preChar2;//抵押权证信息—抵押人	
	@XmlElement(name = "PreChar3")
	private String preChar3;//抵押权证信息—抵押期限	
	@XmlElement(name = "PreChar4")
	private String preChar4;//抵押登记所在地	
	@XmlElement(name = "LoanYear")
	private String loanYear;//抵押物状态—阶段	
	@XmlElement(name = "NonlocalFlag")
	private String nonlocalFlag;//抵押物状态—是否扣押	
	@XmlElement(name = "LicenseNo2")
	private String licenseNo2;//行驶证编号	
	@XmlElement(name = "StepHull")
	private String stepHull;//所购车辆种类	
	@XmlElement(name = "AliasName")
	private String aliasName;//所在/注册省份	
	@XmlElement(name = "ModelCodeAlias")
	private String modelCodeAlias;//所在/注册城市	
	@XmlElement(name = "CarDealerName")
	private String carDealerName;//品牌/生产厂商	
	@XmlElement(name = "ExhaustScale")
	private String exhaustScale;//排量
	 @XmlElement(name = "VefaNo")
	private String frameNumber; //车架号
	 @XmlElement(name = "EngiNo")
	private String engineNumber;//发动机号
	 @XmlElement(name = "CarColor")
	private String carColor; // 车颜色
	 @XmlElement(name = "BrandName")
	private String brandName;//车型代码
	 @XmlElement(name = "ModelName") 
	 private String ModelName;//车型名称
	 @XmlElement(name = "EnrollDate")
	private String enrollDate;//初登日期
	 @XmlElement(name = "RbEvaluate")
	private String rbEvaluate;//人保评估价
	 @XmlElement(name = "LicenseNo")
	private String plateNumber; //车牌号
	 @XmlElement(name = "MonopolyName")
	private String monopolyName;//变速类型	
	 @XmlElement(name = "MakeDate")
	private String makeDate;//出厂日期或报关日期	
	 @XmlElement(name = "SuspendStartDate")
	private String suspendStartDate;//设计使用到期日期	
	 @XmlElement(name = "WarrantorName")
	private String warrantorName;//押品所有人	
	 @XmlElement(name = "LoanUsage")
	private String loanUsage;//押品所有人证件类型	
	 @XmlElement(name = "LoanNo5")
	private String loanNo5;//押品所有人证件号码	
	 @XmlElement(name = "VehCertNo")
	private String vehCertNo;//机动车登记证书编号	
	 @XmlElement(name = "PhotoNo")
	private String photoNo;//拍照号码	
	 @XmlElement(name = "CommunityProperty")
	private String communityProperty;//是否共有财产	
	 @XmlElement(name = "MakeStartDate")
	private String makeStartDate;//租赁起始日	
	 @XmlElement(name = "MakeEndDate")
	private String makeEndDate;//租赁到期日	
	 @XmlElement(name = "MortRegDate")
	private String mortRegDate;//抵押登记日期
	 @XmlElement(name = "FilekeyName")
	private String filekeyName;//影像名称	
	public String getMortgageInfoId() {
		return mortgageInfoId;
	}
	public void setMortgageInfoId(String mortgageInfoId) {
		this.mortgageInfoId = mortgageInfoId;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
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
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getAppliName() {
		return appliName;
	}
	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(String unitAmount) {
		this.unitAmount = unitAmount;
	}
	public String getLoanNo3() {
		return loanNo3;
	}
	public void setLoanNo3(String loanNo3) {
		this.loanNo3 = loanNo3;
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
	public String getPaidTimes() {
		return paidTimes;
	}
	public void setPaidTimes(String paidTimes) {
		this.paidTimes = paidTimes;
	}
	public String getRepaidType() {
		return repaidType;
	}
	public void setRepaidType(String repaidType) {
		this.repaidType = repaidType;
	}
	public String getLoanBankCode() {
		return loanBankCode;
	}
	public void setLoanBankCode(String loanBankCode) {
		this.loanBankCode = loanBankCode;
	}
	public String getLoanBankName() {
		return loanBankName;
	}
	public void setLoanBankName(String loanBankName) {
		this.loanBankName = loanBankName;
	}
	public String getLoanNature() {
		return loanNature;
	}
	public void setLoanNature(String loanNature) {
		this.loanNature = loanNature;
	}
	public String getOtherMortgage() {
		return otherMortgage;
	}
	public void setOtherMortgage(String otherMortgage) {
		this.otherMortgage = otherMortgage;
	}
	public String getOherNature() {
		return oherNature;
	}
	public void setOherNature(String oherNature) {
		this.oherNature = oherNature;
	}
	public String getUseNatureCode() {
		return useNatureCode;
	}
	public void setUseNatureCode(String useNatureCode) {
		this.useNatureCode = useNatureCode;
	}
	public String getPreChar1() {
		return preChar1;
	}
	public void setPreChar1(String preChar1) {
		this.preChar1 = preChar1;
	}
	public String getLandAgent() {
		return landAgent;
	}
	public void setLandAgent(String landAgent) {
		this.landAgent = landAgent;
	}
	public String getPreChar2() {
		return preChar2;
	}
	public void setPreChar2(String preChar2) {
		this.preChar2 = preChar2;
	}
	public String getPreChar3() {
		return preChar3;
	}
	public void setPreChar3(String preChar3) {
		this.preChar3 = preChar3;
	}
	public String getPreChar4() {
		return preChar4;
	}
	public void setPreChar4(String preChar4) {
		this.preChar4 = preChar4;
	}
	public String getLoanYear() {
		return loanYear;
	}
	public void setLoanYear(String loanYear) {
		this.loanYear = loanYear;
	}
	public String getNonlocalFlag() {
		return nonlocalFlag;
	}
	public void setNonlocalFlag(String nonlocalFlag) {
		this.nonlocalFlag = nonlocalFlag;
	}
	public String getLicenseNo2() {
		return licenseNo2;
	}
	public void setLicenseNo2(String licenseNo2) {
		this.licenseNo2 = licenseNo2;
	}
	public String getStepHull() {
		return stepHull;
	}
	public void setStepHull(String stepHull) {
		this.stepHull = stepHull;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getModelCodeAlias() {
		return modelCodeAlias;
	}
	public void setModelCodeAlias(String modelCodeAlias) {
		this.modelCodeAlias = modelCodeAlias;
	}
	public String getCarDealerName() {
		return carDealerName;
	}
	public void setCarDealerName(String carDealerName) {
		this.carDealerName = carDealerName;
	}
	public String getExhaustScale() {
		return exhaustScale;
	}
	public void setExhaustScale(String exhaustScale) {
		this.exhaustScale = exhaustScale;
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
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getRbEvaluate() {
		return rbEvaluate;
	}
	public void setRbEvaluate(String rbEvaluate) {
		this.rbEvaluate = rbEvaluate;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getMonopolyName() {
		return monopolyName;
	}
	public void setMonopolyName(String monopolyName) {
		this.monopolyName = monopolyName;
	}
	public String getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}
	public String getSuspendStartDate() {
		return suspendStartDate;
	}
	public void setSuspendStartDate(String suspendStartDate) {
		this.suspendStartDate = suspendStartDate;
	}
	public String getWarrantorName() {
		return warrantorName;
	}
	public void setWarrantorName(String warrantorName) {
		this.warrantorName = warrantorName;
	}
	public String getLoanUsage() {
		return loanUsage;
	}
	public void setLoanUsage(String loanUsage) {
		this.loanUsage = loanUsage;
	}
	public String getLoanNo5() {
		return loanNo5;
	}
	public void setLoanNo5(String loanNo5) {
		this.loanNo5 = loanNo5;
	}
	public String getVehCertNo() {
		return vehCertNo;
	}
	public void setVehCertNo(String vehCertNo) {
		this.vehCertNo = vehCertNo;
	}
	public String getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}
	public String getCommunityProperty() {
		return communityProperty;
	}
	public void setCommunityProperty(String communityProperty) {
		this.communityProperty = communityProperty;
	}
	public String getMakeStartDate() {
		return makeStartDate;
	}
	public void setMakeStartDate(String makeStartDate) {
		this.makeStartDate = makeStartDate;
	}
	public String getMakeEndDate() {
		return makeEndDate;
	}
	public void setMakeEndDate(String makeEndDate) {
		this.makeEndDate = makeEndDate;
	}
	public String getMortRegDate() {
		return mortRegDate;
	}
	public void setMortRegDate(String mortRegDate) {
		this.mortRegDate = mortRegDate;
	}
	public String getFilekeyName() {
		return filekeyName;
	}
	public void setFilekeyName(String filekeyName) {
		this.filekeyName = filekeyName;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String modelName) {
		ModelName = modelName;
	}
	 
	 
}
