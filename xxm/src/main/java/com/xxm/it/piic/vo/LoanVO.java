package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="LoanInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class LoanVO {
	@XmlTransient
	private String loanInfoId;
	@XmlTransient
	private String applyInfoId; //申请id
	@XmlElement(name="ProposalNo")
	private String proposalNo;// 投保单号
	@XmlElement(name="ApplyLoanTimes")
	private String applyLoanTimes;// 申请放款时间
	@XmlElement(name="Applicant")
	private String applicant;	//申请人
	@XmlElement(name="BankCode")
	private String bankCode;//银行代码
	@XmlElement(name="LoanNo")
	private String loanNo; //借据号
	@XmlElement(name="ApplyNo")
	private String applyNo;//申请书号
	@XmlElement(name="EacNo")
	private String eacNo;// 放/还款账户
	@XmlElement(name="IdType")
	private String idType;	//证件类型
	@XmlElement(name="IdNo")
	private String idNo;//证件号码
	@XmlElement(name="CustName")
	private String custName; //客户名称
	@XmlElement(name="AtrsDueDay")
	private String atrsDueDay; //扣款日
	@XmlElement(name="TacNo")
	private String tacNo; //放款卡号
	@XmlElement(name="VefaNo")
	private String vefaNo;// 车架号
	@XmlElement(name="EngiNo")
	private String engiNo;//发动机号
	@XmlElement(name="CarColor")
	private String carColor;//车辆颜色
	@XmlElement(name="BrandName")
	private String brandName;//车型代码
	
	@XmlElement(name="ModelName")
	private String modelName; //车型名称
	@XmlElement(name="EnrollDate")
	private String enrollDate;//初登日期
	@XmlElement(name="RbEvaluate")
	private String rbEvaluate;//人保评估价
	@XmlElement(name="OtherNature")
	private String otherNature;//行驶里程数(公里)
	
	@XmlElement(name="ExhaustScale")
	private String exhaustScale;//排量
	@XmlElement(name="LicenseNo")
	private String LicenseNo; //号牌号码
	@XmlElement(name="UseNature")
	private String useNature;	//车辆使用性质
	@XmlElement(name="DealAmount")
	private String dealAmount;//发票金额
	@XmlElement(name="CoverNo")
	private String coverNo; //商业车险保单号
	@XmlElement(name="MortRegDate")
	private String mortRegDate; //抵押登记日期
	@XmlElement(name="PayJnlNo")
	private String payJnlNo; //租赁公司购车付款流水号
	@XmlElement(name="PayDate")
	private String payDate; //租赁公司购车付款流水号
	@XmlElement(name="PayAmount")
	private String payAmount; //租赁公司购车付款金额
	@XmlElement(name="PayeeName")
	private String payeeName;// 租赁公司购车收款方
	@XmlElement(name="PayeeAcct")
	private String payeeAcct;// 租赁公司购车收款账号	
	@XmlElement(name="DownPayment")
	private String downPayment;	//首付款金额
	@XmlElement(name="LeaseCtctNo")
	private String leaseCtctNo;//租赁合同编号
	@XmlElement(name="LeaseStarTime")
	private String leaseStarTime; //租赁合同生效日
	@XmlElement(name="LeaseEndTime")
	private String leaseEndTime; //租赁合同到期日
	@XmlElement(name="LeaseCtctAm")
	private String leaseCtctAm; //租赁合同金额	
	@XmlElement(name="FilekeyName")
	private String filekeyName; //影像名称	
	
	public String getProposalNo() {
		return proposalNo;
	}
	
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getApplyLoanTimes() {
		return applyLoanTimes;
	}
	
	public void setApplyLoanTimes(String applyLoanTimes) {
		this.applyLoanTimes = applyLoanTimes;
	}

	public String getApplicant() {
		return applicant;
	}
	
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

	public String getEacNo() {
		return eacNo;
	}
	
	public void setEacNo(String eacNo) {
		this.eacNo = eacNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAtrsDueDay() {
		return atrsDueDay;
	}
	
	public void setAtrsDueDay(String atrsDueDay) {
		this.atrsDueDay = atrsDueDay;
	}

	public String getTacNo() {
		return tacNo;
	}
	
	public void setTacNo(String tacNo) {
		this.tacNo = tacNo;
	}

	public String getVefaNo() {
		return vefaNo;
	}
	
	public void setVefaNo(String vefaNo) {
		this.vefaNo = vefaNo;
	}

	public String getEngiNo() {
		return engiNo;
	}
	
	public void setEngiNo(String engiNo) {
		this.engiNo = engiNo;
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

	public String getExhaustScale() {
		return exhaustScale;
	}
	
	public void setExhaustScale(String exhaustScale) {
		this.exhaustScale = exhaustScale;
	}

	public String getLicenseNo() {
		return LicenseNo;
	}
	
	public void setLicenseNo(String licenseNo) {
		LicenseNo = licenseNo;
	}

	public String getUseNature() {
		return useNature;
	}
	
	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}

	public String getDealAmount() {
		return dealAmount;
	}
	
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getCoverNo() {
		return coverNo;
	}
	
	public void setCoverNo(String coverNo) {
		this.coverNo = coverNo;
	}

	public String getMortRegDate() {
		return mortRegDate;
	}
	
	public void setMortRegDate(String mortRegDate) {
		this.mortRegDate = mortRegDate;
	}

	public String getPayJnlNo() {
		return payJnlNo;
	}
	
	public void setPayJnlNo(String payJnlNo) {
		this.payJnlNo = payJnlNo;
	}

	public String getPayDate() {
		return payDate;
	}
	
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayAmount() {
		return payAmount;
	}
	
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayeeName() {
		return payeeName;
	}
	
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeAcct() {
		return payeeAcct;
	}
	
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}

	public String getDownPayment() {
		return downPayment;
	}
	
	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	public String getLeaseCtctNo() {
		return leaseCtctNo;
	}
	
	public void setLeaseCtctNo(String leaseCtctNo) {
		this.leaseCtctNo = leaseCtctNo;
	}

	public String getLeaseStarTime() {
		return leaseStarTime;
	}
	
	public void setLeaseStarTime(String leaseStarTime) {
		this.leaseStarTime = leaseStarTime;
	}

	public String getLeaseEndTime() {
		return leaseEndTime;
	}
	
	public void setLeaseEndTime(String leaseEndTime) {
		this.leaseEndTime = leaseEndTime;
	}

	public String getLeaseCtctAm() {
		return leaseCtctAm;
	}
	
	public void setLeaseCtctAm(String leaseCtctAm) {
		this.leaseCtctAm = leaseCtctAm;
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
	public String getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

}
