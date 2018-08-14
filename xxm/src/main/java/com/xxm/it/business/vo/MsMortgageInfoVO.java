package com.xxm.it.business.vo;

import java.util.ArrayList;
import java.util.List;

import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.system.vo.CommonVO;

/**
 * 车辆抵押登记信息传输对象
 * 
 * @author Administrator
 *
 */
public class MsMortgageInfoVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String mortgageInfoId;//
	private String proposalNo;//投保单号
	private String loanNo;//借据号
	private String applyNo;//申请书编号
	private String policyNo;//保单号
	private String appliName;//投保人名称
	private String insuredName;//被保险人名称
	private String unitAmount;//抵押保证金
	private String loanNo3;//抵押合同号
	private String guaranteeType;//抵(质)押物性质
	private String guaranteeTypeName;//抵(质)押物性质名称
	private String guaranteeName;//抵(质)押物名称
	private String deliverDate;//抵(质)押权生效日期
	private String paidTimes;//押品所有人所占份额
	private String repaidType;//是否需要办理保险
	private String repaidTypeName;//是否需要办理保险名称
	private String loanBankCode;//抵质押品法律有效性
	private String loanBankCodeName;//抵质押品法律有效性名称
	private String loanBankName;//抵质押品变现能力
	private String loanNature;//押品是否已出租
	private String loanNatureName;//押品是否已出租名称
	private String otherMortgage;//是否在其它机构设置抵质押
	private String otherNature;//行驶里程数(公里)
	private String useNatureCode;//是否运营车辆
	private String useNatureCodeName;//是否运营车辆名称
	private String preChar1;//权证完备状态
	private String preChar1Name;//权证完备状态名称
	private String landAgent;//抵押权证信息—抵押权人
	private String preChar2;//抵押权证信息—抵押人
	private String preChar3;//抵押权证信息—抵押期限
	private String preChar4;//抵押登记所在地
	private String loanYear;//抵押物状态—阶段
	private String loanYearName;//抵押物状态—阶段名称
	private String nonlocalFlag;//抵押物状态—是否扣押
	private String nonlocalFlagName;//抵押物状态—是否扣押名称
	private String licenseNo2;//行驶证编号
	private String stepHull;//所购车辆种类
	private String stepHullName;//所购车辆种类名称
	private String aliasName;//所在/注册省份
	private String modelCodeAlias;//所在/注册城市
	private String carDealerName;//品牌/生产厂商
	private String monopolyName;//变速类型
	private String makeDate;//出厂日期或报关日期
	private String suspendStartDate;//设计使用到期日期
	private String warrantorName;//押品所有人
	private String loanUsage;//押品所有人证件类型
	private String loanUsageName;//押品所有人证件类型名称
	private String loanNo5;//押品所有人证件号码
	private String vehCertNo;//机动车登记证书编号
	private String photoNo;//拍照号码
	private String communityProperty;//是否共有财产
	private String communityPropertyName;//是否共有财产名称
	private String makeStartDate;//租赁起始日
	private String makeEndDate;//租赁到期日
	private String mortRegDate;//抵押登记日期
	private String filekeyName;//影像名称
	private String mortgageInfoStatus;//状态
	private String leaseInfoId;//租赁信息ID
	private String applyInfoId;//  申请表ID
	private BaseLeaseInfoVO leaseInfoVO=new BaseLeaseInfoVO();//车辆
	private MortgageReturnInfoVO mortgageReturnInfoVO=new MortgageReturnInfoVO();//响应对象
	//响应对象list
	private List<MortgageReturnInfoVO> mortgageReturnInfoList=new ArrayList<MortgageReturnInfoVO>();
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
	public String getOtherNature() {
		return otherNature;
	}
	public void setOtherNature(String otherNature) {
		this.otherNature = otherNature;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLeaseInfoId() {
		return leaseInfoId;
	}
	public void setLeaseInfoId(String leaseInfoId) {
		this.leaseInfoId = leaseInfoId;
	}
	public String getMortgageInfoStatus() {
		return mortgageInfoStatus;
	}
	public void setMortgageInfoStatus(String mortgageInfoStatus) {
		this.mortgageInfoStatus = mortgageInfoStatus;
	}
	public MortgageReturnInfoVO getMortgageReturnInfoVO() {
		return mortgageReturnInfoVO;
	}
	public void setMortgageReturnInfoVO(MortgageReturnInfoVO mortgageReturnInfoVO) {
		this.mortgageReturnInfoVO = mortgageReturnInfoVO;
	}
	public List<MortgageReturnInfoVO> getMortgageReturnInfoList() {
		return mortgageReturnInfoList;
	}
	public void setMortgageReturnInfoList(List<MortgageReturnInfoVO> mortgageReturnInfoList) {
		this.mortgageReturnInfoList = mortgageReturnInfoList;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getRepaidTypeName() {
		return repaidTypeName;
	}
	public void setRepaidTypeName(String repaidTypeName) {
		this.repaidTypeName = repaidTypeName;
	}
	public String getLoanBankCodeName() {
		return loanBankCodeName;
	}
	public void setLoanBankCodeName(String loanBankCodeName) {
		this.loanBankCodeName = loanBankCodeName;
	}
	public String getLoanNatureName() {
		return loanNatureName;
	}
	public void setLoanNatureName(String loanNatureName) {
		this.loanNatureName = loanNatureName;
	}
	public String getUseNatureCodeName() {
		return useNatureCodeName;
	}
	public void setUseNatureCodeName(String useNatureCodeName) {
		this.useNatureCodeName = useNatureCodeName;
	}
	public String getPreChar1Name() {
		return preChar1Name;
	}
	public void setPreChar1Name(String preChar1Name) {
		this.preChar1Name = preChar1Name;
	}
	public String getLoanYearName() {
		return loanYearName;
	}
	public void setLoanYearName(String loanYearName) {
		this.loanYearName = loanYearName;
	}
	public String getNonlocalFlagName() {
		return nonlocalFlagName;
	}
	public void setNonlocalFlagName(String nonlocalFlagName) {
		this.nonlocalFlagName = nonlocalFlagName;
	}
	public String getStepHullName() {
		return stepHullName;
	}
	public void setStepHullName(String stepHullName) {
		this.stepHullName = stepHullName;
	}
	public String getLoanUsageName() {
		return loanUsageName;
	}
	public void setLoanUsageName(String loanUsageName) {
		this.loanUsageName = loanUsageName;
	}
	public String getCommunityPropertyName() {
		return communityPropertyName;
	}
	public void setCommunityPropertyName(String communityPropertyName) {
		this.communityPropertyName = communityPropertyName;
	}
	public BaseLeaseInfoVO getLeaseInfoVO() {
		return leaseInfoVO;
	}
	public void setLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO) {
		this.leaseInfoVO = leaseInfoVO;
	}
	public String getGuaranteeTypeName() {
		return guaranteeTypeName;
	}
	public void setGuaranteeTypeName(String guaranteeTypeName) {
		this.guaranteeTypeName = guaranteeTypeName;
	}

}
