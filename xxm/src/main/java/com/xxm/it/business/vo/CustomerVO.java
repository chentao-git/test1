package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class CustomerVO extends CommonVO{
	private static final long serialVersionUID = 1L;
//	private String customerId;//
//	private String baseCustomerId;// 客户基础信息id
//	private String baseCustomerName;// 借款人姓名
//	private String baseCustomerSex;//客户性别
//	private String baseCustomerSexName;//客户性别名称
//	private String certType;//借款人证件类型
//	private String certTypeName;//借款人证件类型名称
//	private String certNo;//借款人证件号码
//	private String issuedDate;//证件签发日期
//	private String expirationDate;//证件失效日期
//	private String taxType;//国税类别，1-境内 2-境外 3-同时境内境外（目前只支持境内）
//	private String marriageStatus;//婚姻状况，10-未婚、20-已婚、40-离异、50-丧偶、90-其他
//	private String marriageStatusName;//婚姻状况名称
//	private String spouseName;//配偶姓名
//	private String spouseIdType;//配偶证件类型,20-身份证、22-护照、23-军官证、 25-港澳居民来往内地通行证、26-台湾居民来往大陆通行证、2X-其他证件、30-组织机构代码证 31-营业执照
//	private String spouseIdTypeName;//配偶证件类型名称
//	private String spouseIdNo;//配偶证件号码
//	private String spouseMobile;//配偶联系电话
//	private String educationType;//最高学历，00硕士及以上、 10本科、20大专、 30-高中、40-初中及以下
//	private String educationTypeName;//最高学历名称
//	private String liveAddr;//现居住地址
//	private String liveCity;//现居住城市
//	private String companyName;//现单位名称，如无单位填写“个体”
//	private String position;//职务，10-受薪人士、20-自雇人士、30-无业、40-学生
//	private String serviceYear;//工作年限
//	private String income;//收入
//	private String companyAddr;//单位地址，如前面单位性质非个体或无职业，该项需填写
//	private String unitKind;//贷款人工作单位所属行业
//	private String mobileNo;//借款人手机号
//	private String accNo;//借款人还款账号
//	private String bankFlag;//是否我行账号,1-我行账号2-他行账号
//	public String getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
//	public String getBaseCustomerId() {
//		return baseCustomerId;
//	}
//	public void setBaseCustomerId(String baseCustomerId) {
//		this.baseCustomerId = baseCustomerId;
//	}
//	public String getBaseCustomerName() {
//		return baseCustomerName;
//	}
//	public void setBaseCustomerName(String baseCustomerName) {
//		this.baseCustomerName = baseCustomerName;
//	}
//	public String getBaseCustomerSex() {
//		return baseCustomerSex;
//	}
//	public void setBaseCustomerSex(String baseCustomerSex) {
//		this.baseCustomerSex = baseCustomerSex;
//	}
//	public String getCertType() {
//		return certType;
//	}
//	public void setCertType(String certType) {
//		this.certType = certType;
//	}
//	public String getCertNo() {
//		return certNo;
//	}
//	public void setCertNo(String certNo) {
//		this.certNo = certNo;
//	}
//	public String getIssuedDate() {
//		return issuedDate;
//	}
//	public void setIssuedDate(String issuedDate) {
//		this.issuedDate = issuedDate;
//	}
//	public String getExpirationDate() {
//		return expirationDate;
//	}
//	public void setExpirationDate(String expirationDate) {
//		this.expirationDate = expirationDate;
//	}
//	public String getTaxType() {
//		return taxType;
//	}
//	public void setTaxType(String taxType) {
//		this.taxType = taxType;
//	}
//	public String getMarriageStatus() {
//		return marriageStatus;
//	}
//	public void setMarriageStatus(String marriageStatus) {
//		this.marriageStatus = marriageStatus;
//	}
//	public String getSpouseName() {
//		return spouseName;
//	}
//	public void setSpouseName(String spouseName) {
//		this.spouseName = spouseName;
//	}
//	public String getSpouseIdType() {
//		return spouseIdType;
//	}
//	public void setSpouseIdType(String spouseIdType) {
//		this.spouseIdType = spouseIdType;
//	}
//	public String getSpouseIdNo() {
//		return spouseIdNo;
//	}
//	public void setSpouseIdNo(String spouseIdNo) {
//		this.spouseIdNo = spouseIdNo;
//	}
//	public String getSpouseMobile() {
//		return spouseMobile;
//	}
//	public void setSpouseMobile(String spouseMobile) {
//		this.spouseMobile = spouseMobile;
//	}
//	public String getEducationType() {
//		return educationType;
//	}
//	public void setEducationType(String educationType) {
//		this.educationType = educationType;
//	}
//	public String getLiveAddr() {
//		return liveAddr;
//	}
//	public void setLiveAddr(String liveAddr) {
//		this.liveAddr = liveAddr;
//	}
//	public String getLiveCity() {
//		return liveCity;
//	}
//	public void setLiveCity(String liveCity) {
//		this.liveCity = liveCity;
//	}
//	public String getCompanyName() {
//		return companyName;
//	}
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//	public String getPosition() {
//		return position;
//	}
//	public void setPosition(String position) {
//		this.position = position;
//	}
//	public String getServiceYear() {
//		return serviceYear;
//	}
//	public void setServiceYear(String serviceYear) {
//		this.serviceYear = serviceYear;
//	}
//	public String getIncome() {
//		return income;
//	}
//	public void setIncome(String income) {
//		this.income = income;
//	}
//	public String getCompanyAddr() {
//		return companyAddr;
//	}
//	public void setCompanyAddr(String companyAddr) {
//		this.companyAddr = companyAddr;
//	}
//	public String getUnitKind() {
//		return unitKind;
//	}
//	public void setUnitKind(String unitKind) {
//		this.unitKind = unitKind;
//	}
//	public String getMobileNo() {
//		return mobileNo;
//	}
//	public void setMobileNo(String mobileNo) {
//		this.mobileNo = mobileNo;
//	}
//	public String getAccNo() {
//		return accNo;
//	}
//	public void setAccNo(String accNo) {
//		this.accNo = accNo;
//	}
//	public String getBankFlag() {
//		return bankFlag;
//	}
//	public void setBankFlag(String bankFlag) {
//		this.bankFlag = bankFlag;
//	}
//	public String getOpenBankName() {
//		return openBankName;
//	}
//	public void setOpenBankName(String openBankName) {
//		this.openBankName = openBankName;
//	}
//	public String getOpenBankNo() {
//		return openBankNo;
//	}
//	public void setOpenBankNo(String openBankNo) {
//		this.openBankNo = openBankNo;
//	}
//	public String getCertImageFile() {
//		return certImageFile;
//	}
//	public void setCertImageFile(String certImageFile) {
//		this.certImageFile = certImageFile;
//	}
//	public String getIdentification() {
//		return identification;
//	}
//	public void setIdentification(String identification) {
//		this.identification = identification;
//	}
//	public String getCensusRegisterPlace() {
//		return censusRegisterPlace;
//	}
//	public void setCensusRegisterPlace(String censusRegisterPlace) {
//		this.censusRegisterPlace = censusRegisterPlace;
//	}
//	public String getBirthDate() {
//		return birthDate;
//	}
//	public void setBirthDate(String birthDate) {
//		this.birthDate = birthDate;
//	}
//	public String getFixedLine() {
//		return fixedLine;
//	}
//	public void setFixedLine(String fixedLine) {
//		this.fixedLine = fixedLine;
//	}
//	public String getAccountName() {
//		return accountName;
//	}
//	public void setAccountName(String accountName) {
//		this.accountName = accountName;
//	}
//	public String getDeductionsObjectType() {
//		return deductionsObjectType;
//	}
//	public void setDeductionsObjectType(String deductionsObjectType) {
//		this.deductionsObjectType = deductionsObjectType;
//	}
//	public String getIsABC() {
//		return isABC;
//	}
//	public void setIsABC(String isABC) {
//		this.isABC = isABC;
//	}
//	public String getDraweeBankName() {
//		return draweeBankName;
//	}
//	public void setDraweeBankName(String draweeBankName) {
//		this.draweeBankName = draweeBankName;
//	}
//	public String getDraweeBankAccount() {
//		return draweeBankAccount;
//	}
//	public void setDraweeBankAccount(String draweeBankAccount) {
//		this.draweeBankAccount = draweeBankAccount;
//	}
//	public String getDraweeBank() {
//		return draweeBank;
//	}
//	public void setDraweeBank(String draweeBank) {
//		this.draweeBank = draweeBank;
//	}
//	public String getLargeLineNo() {
//		return LargeLineNo;
//	}
//	public void setLargeLineNo(String largeLineNo) {
//		LargeLineNo = largeLineNo;
//	}
//	public String getSpousePosition() {
//		return spousePosition;
//	}
//	public void setSpousePosition(String spousePosition) {
//		this.spousePosition = spousePosition;
//	}
//	public String getSpousePhone2() {
//		return spousePhone2;
//	}
//	public void setSpousePhone2(String spousePhone2) {
//		this.spousePhone2 = spousePhone2;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//	
//	public String getCertTypeName() {
//		return certTypeName;
//	}
//	public void setCertTypeName(String certTypeName) {
//		this.certTypeName = certTypeName;
//	}
//	public String getMarriageStatusName() {
//		return marriageStatusName;
//	}
//	public void setMarriageStatusName(String marriageStatusName) {
//		this.marriageStatusName = marriageStatusName;
//	}
//	public String getSpouseIdTypeName() {
//		return spouseIdTypeName;
//	}
//	public void setSpouseIdTypeName(String spouseIdTypeName) {
//		this.spouseIdTypeName = spouseIdTypeName;
//	}
//	public String getEducationTypeName() {
//		return educationTypeName;
//	}
//	public void setEducationTypeName(String educationTypeName) {
//		this.educationTypeName = educationTypeName;
//	}
//	public String getOpenBankBaseName() {
//		return openBankBaseName;
//	}
//	public void setOpenBankBaseName(String openBankBaseName) {
//		this.openBankBaseName = openBankBaseName;
//	}
//	public String getDeductionsObjectTypeName() {
//		return deductionsObjectTypeName;
//	}
//	public void setDeductionsObjectTypeName(String deductionsObjectTypeName) {
//		this.deductionsObjectTypeName = deductionsObjectTypeName;
//	}
//
//	public String getBaseCustomerSexName() {
//		return baseCustomerSexName;
//	}
//	public void setBaseCustomerSexName(String baseCustomerSexName) {
//		this.baseCustomerSexName = baseCustomerSexName;
//	}
//
//	private String openBankName;//借款人账号开户行名
//	private String openBankBaseName;//借款人账号开户行名（数据名称）
//	private String openBankNo;//借款人账号开户行号
//	private String certImageFile;//身份证影像文件压缩包
//	private String identification;//身份证号
//	private String censusRegisterPlace;//户籍所在地
//	private String birthDate;//出生年月日
//	private String fixedLine;//家庭固话
//	private String accountName;//账户名称
//	private String deductionsObjectType;//扣款对象分类
//	private String deductionsObjectTypeName;//扣款对象分类名称
//	private String isABC;//是否农行
//	private String isABCName;//是否农行名称
//	private String draweeBankName;//付款账户户名
//	private String draweeBankAccount;//付款银行
//	private String draweeBank;//付款银行
//	private String LargeLineNo;//大额行号
//	private String spousePosition;//配偶职业
//	private String spousePhone2;//配偶电话2
	
}
