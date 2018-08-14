package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.business.vo.SupplementBaseVO;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsurePolicyInfo { //投保单/保单信息
	@XmlElement(name="SerialNo")
	private String serialNo; //请求序列号
//	 @XmlJavaTypeAdapter(value=DataAdapter.class)
	@XmlElement(name="RiskCode")
	private String riskCode; //险种代码
	@XmlElement(name="StartDate")
	private String startDate; //起保日期
	@XmlElement(name="EndDate")
	private String endDate;//终保日期
	@XmlElement(name="StartHour")
	private String startHour;//起保小时
	@XmlElement(name="EndHour")
	private String endHour;//终保小时
	@XmlElement(name="SumAmount")
	private String sumAmount;//保单总保险金额
	@XmlElement(name="SumPremium")
	private String sumPremium;//保单总保险费
	@XmlElement(name="ArguSolution")
	private String arguSolution;//争议解决方式
	@XmlElement(name="FileKeyList")
	private String fileKeyList;//附件信息（影像资料名称）
	@XmlElement(name="IscoinTypeFlag")
	private String sscoinTypeFlag;//是否是联保业务出单
	@XmlElement(name="PrejudicNo")
	private String prejudicNo; //预审单号
	
	@XmlTransient
	private String insuredPlanId; //方案信息id
	@XmlTransient
	private String citemCarId; //车辆信息id
	@XmlTransient
	private String insuredCreditInvest; //资信等级信息id
	@XmlTransient
	private String citemCreditId; //贷款信息id
	@XmlTransient
	private String applicantId ; //投保人信息id
	
	@XmlElementWrapper(name = "Coins") 
    @XmlElement(name = "Coin")
	private List<InsureCoin> insureCoin; //投保单归属机构信息
	@XmlElement(name = "InsuredPlan")
	private InsuredPlan insuredPlan; //方案信息
	@XmlElement(name = "CitemCar",required=true,nillable=true)
	BaseLeaseInfoVO baseLeaseInfo = new BaseLeaseInfoVO(); //车辆基础信息
	
	@XmlElement(name = "InsuredCreditInvest")
	BaseCustomerVO baseCustomer = new BaseCustomerVO(); //投保人基础信息
	@XmlElement(name = "CitemCredit")
	SupplementBaseVO supplementBase = new SupplementBaseVO(); //贷款信息
	//投保人信息
	@XmlElement(name = "Applicant")
	private ApplicantVO applicantVO; //投保人基础信息
	
	//被保人信息
	@XmlElementWrapper(name = "Insureds") 
    @XmlElement(name = "Insured")
	private List<InsureTheInsuredInfo> insureTheInsuredInfo;
	
	//扩展信息
	@XmlElementWrapper(name = "ExtendInfos") 
    @XmlElement(name = "ExtendInfo")
	private List<InsureExtendInfo> insureExtendInfoList;
	
	
	public List<InsureTheInsuredInfo> getInsureTheInsuredInfo() {
		return insureTheInsuredInfo;
	}
	public void setInsureTheInsuredInfo(List<InsureTheInsuredInfo> insureTheInsuredInfo) {
		this.insureTheInsuredInfo = insureTheInsuredInfo;
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
	public String getFileKeyList() {
		return fileKeyList;
	}
	public void setFileKeyList(String fileKeyList) {
		this.fileKeyList = fileKeyList;
	}
	public String getSscoinTypeFlag() {
		return sscoinTypeFlag;
	}
	public void setSscoinTypeFlag(String sscoinTypeFlag) {
		this.sscoinTypeFlag = sscoinTypeFlag;
	}
	public String getPrejudicNo() {
		return prejudicNo;
	}
	public void setPrejudicNo(String prejudicNo) {
		this.prejudicNo = prejudicNo;
	}
	public List<InsureCoin> getInsureCoin() {
		return insureCoin;
	}
	public void setInsureCoin(List<InsureCoin> insureCoin) {
		this.insureCoin = insureCoin;
	}
	public InsuredPlan getInsuredPlan() {
		return insuredPlan;
	}
	public void setInsuredPlan(InsuredPlan insuredPlan) {
		this.insuredPlan = insuredPlan;
	}
	public String getInsuredPlanId() {
		return insuredPlanId;
	}
	public void setInsuredPlanId(String insuredPlanId) {
		this.insuredPlanId = insuredPlanId;
	}
	public String getCitemCarId() {
		return citemCarId;
	}
	public void setCitemCarId(String citemCarId) {
		this.citemCarId = citemCarId;
	}
	public String getInsuredCreditInvest() {
		return insuredCreditInvest;
	}
	public void setInsuredCreditInvest(String insuredCreditInvest) {
		this.insuredCreditInvest = insuredCreditInvest;
	}
	public String getCitemCreditId() {
		return citemCreditId;
	}
	public void setCitemCreditId(String citemCreditId) {
		this.citemCreditId = citemCreditId;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public BaseLeaseInfoVO getBaseLeaseInfo() {
		return baseLeaseInfo;
	}
	public void setBaseLeaseInfo(BaseLeaseInfoVO baseLeaseInfo) {
		this.baseLeaseInfo = baseLeaseInfo;
	}
	public BaseCustomerVO getBaseCustomer() {
		return baseCustomer;
	}
	public void setBaseCustomer(BaseCustomerVO baseCustomer) {
		this.baseCustomer = baseCustomer;
	}
	public SupplementBaseVO getSupplementBase() {
		return supplementBase;
	}
	public void setSupplementBase(SupplementBaseVO supplementBase) {
		this.supplementBase = supplementBase;
	}
	public List<InsureExtendInfo> getInsureExtendInfoList() {
		return insureExtendInfoList;
	}
	public void setInsureExtendInfoList(List<InsureExtendInfo> insureExtendInfoList) {
		this.insureExtendInfoList = insureExtendInfoList;
	}
	public ApplicantVO getApplicantVO() {
		return applicantVO;
	}
	public void setApplicantVO(ApplicantVO applicantVO) {
		this.applicantVO = applicantVO;
	}
	
	
	
}
