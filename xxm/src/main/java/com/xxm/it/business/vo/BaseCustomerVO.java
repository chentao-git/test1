package com.xxm.it.business.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

/**
 * 接口投保客户信息
 * 
 * @author Administrator
 *
 */
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class BaseCustomerVO extends CommonVO {
	@XmlTransient
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String baseCustomerId;//客户"基本"信息Id
	@XmlTransient
	private String customerId;//客户"业务"信息Id
	@XmlTransient
	private String baseCustomerSexName;//客户性别名称
	@XmlTransient
	private String certTypeName;//借款人证件类型名称
	@XmlTransient
	private String issuedDate;//证件签发日期
	@XmlTransient
	private String expirationDate;//证件失效日期
	@XmlTransient
	private String taxType;//国税类别，1-境内 2-境外 3-同时境内境外（目前只支持境内）
	@XmlTransient
	private String marriageStatusName;//婚姻状况名称
	@XmlTransient
	private String spouseName;//配偶姓名
	@XmlTransient
	private String spouseIdType;//配偶证件类型,20-身份证、22-护照、23-军官证、 25-港澳居民来往内地通行证、26-台湾居民来往大陆通行证、2X-其他证件、30-组织机构代码证 31-营业执照
	@XmlTransient
	private String spouseIdTypeName;//配偶证件类型名称
	@XmlTransient
	private String spouseIdNo;//配偶证件号码
	@XmlTransient
	private String spouseMobile;//配偶联系电话
	@XmlTransient
	private String educationTypeName;//最高学历名称
	@XmlTransient
	private String liveCity;//现居住城市
	@XmlTransient
	private String companyName;//现单位名称，如无单位填写“个体”
	@XmlTransient
	private String income;//收入
	@XmlTransient
	private String unitKind;//贷款人工作单位所属行业，A-农、林、牧、渔业、B-采掘业等
	@XmlTransient
	private String accNo;//借款人还款账号
	@XmlTransient
	private String bankFlag;//是否我行账号,1-我行账号2-他行账号
	@XmlTransient
	private String openBankName;//借款人账号开户行名
	@XmlTransient
	private String openBankBaseName;//借款人账号开户行名（数据名称）
	@XmlTransient
	private String openBankNo;//借款人账号开户行号
	@XmlTransient
	private String certImageFile;//身份证影像文件压缩包
	@XmlTransient
	private String censusRegisterPlace;//户籍所在地
	@XmlTransient
	private String accountName;//账户名称
	@XmlTransient
	private String deductionsObjectType;//扣款对象分类
	@XmlTransient
	private String deductionsObjectTypeName;//扣款对象分类名称
	@XmlTransient
	private String isABC;//是否农行
	@XmlTransient
	private String isABCName;//是否农行名称
	@XmlTransient
	private String draweeBankName;//付款账户户名
	@XmlTransient
	private String draweeBankAccount;//付款银行
	@XmlTransient
	private String draweeBank;//付款银行
	@XmlTransient
	private String largeLineNo;//大额行号
	@XmlTransient
	private String spousePosition;//配偶职业
	@XmlTransient
	private String spousePhone2;//配偶电话2
	@XmlTransient
	private String identification;//身份证号
	@XmlTransient
	private String lockMan; // 锁定人
	@XmlTransient
	private String lockManName; //锁定人姓名
	
	
	
	@XmlElement(name="AppliAge")
	private String appliAge; //  投保人年龄
	@XmlElement(name="AppliHealth")
	private String appliHealth;//健康状况
	@XmlTransient
	private String appliHealthName;//健康状况名称
	@XmlElement(name="AppliMarriage")
	private String marriageStatus;//婚姻状况，10-未婚、20-已婚、40-离异、50-丧偶、90-其他
	@XmlElement(name="AppliEducation")
	private String educationType;//最高学历，00硕士及以上、 10本科、20大专、 30-高中、40-初中及以下
	@XmlElement(name="AppliResidence")
	private String appliResidence;//户籍状况
	@XmlTransient
	private String appliResidenceName;//户籍状况名称
	@XmlElement(name="AppliWork")
	private String appliWork;//职业代码
	@XmlTransient
	private String appliWorkName;//职业代码名称
	@XmlElement(name="AppliWorkYears")
	private String serviceYear;//工作年限
	@XmlElement(name="AppliDuty")
	private String position;//职务，10-受薪人士、20-自雇人士、30-无业、40-学生
	@XmlElement(name="IncomeSource")
	private String incomeSource;//主要收入来源
	@XmlTransient
	private String incomeSourceName;//主要收入来源名称
	@XmlElement(name="Piscale")
	private String piscale;//所购车辆净价格与前一年家庭总收入的比例
	@XmlElement(name="MonthEconomy")
	private String monthEconomy;//月均收入-已有借款月还款额-本项借款月还款额)/人口数-扣除额
	@XmlElement(name="HouseStat")
	private String houseStat;//住房产权
	@XmlTransient
	private String houseStatName;//住房产权名称
	@XmlElement(name="HouseSource")
	private String houseSource;//住房来源
	@XmlTransient
	private String houseSourceName;//住房来源名称
	@XmlElement(name="HouseType")
	private String houseType;//住房类型
	@XmlTransient
	private String houseTypeName;//住房类型名称
	@XmlElement(name="FamilySts")
	private String familySts;//居住状况
	@XmlTransient
	private String familyStsName;//居住状况名称
	@XmlElement(name="FamilyYear")
	private String familyYear;//居住年限
	@XmlElement(name="homeAddr")
	private String liveAddr;//现居住地址
	@XmlElement(name="RepayRec")
	private String repayRec;//信用还款记录
	@XmlTransient
	private String repayRecName;//信用还款记录名称
	@XmlElement(name="PubRec")
	private String pubRec;//公用事业付费记录
	@XmlTransient
	private String pubRecName;//公用事业付费记录名称
	@XmlElement(name="OthRec")
	private String othRec;//其他记录
	@XmlTransient
	private String othRecName;//其他记录名称
	@XmlElement(name="Remark")
	private String remark;//资信调查报告
	
	@XmlElement(name="SumScore")
	private String sumScore;//得分合计
	@XmlElement(name="CreditManCode")
	private String creditManCode;//资信调查人
	@XmlElement(name="CreditFlag")
	private String creditFlag;//统谈资信标志
	@XmlElement(name="companyAdd")
	private String companyAddr;//单位地址，如前面单位性质非个体或无职业，该项需填写
	@XmlElement(name="companyZip")
	private String companyZip;//公司邮编
	@XmlElement(name="companyTelCode")
	private String companyTelCode;//单位电话区号
	@XmlElement(name="companyTel")
	private String companyTel;//单位电话
	@XmlElement(name="applyPurpose")
	private String applyPurpose;//申请用途
	@XmlElement(name="monthlyWages")
	private String monthlyWages;//个人月收入
	@XmlElement(name="MothEp")
	private String mothEp;//月支出
	@XmlElement(name="unitType")
	private String unitType;//单位性质
	@XmlTransient
	private String unitTypeName;//单位性质名称
	@XmlElement(name="employment")
	private String employment;//工作单位
	@XmlElement(name="department")
	private String department;//部门
	@XmlElement(name="position")
	private String positions;//职位
	@XmlTransient
	private String positionsName;//职位名称
	@XmlElement(name="yearIncome")
	private String yearIncome;//年收入
	
	
	
	@XmlTransient
	private String baseCustomerName;// 借款人姓名
	@XmlTransient
	private String certType;//借款人证件类型
	@XmlTransient
	private String certNo;//借款人证件号码
	@XmlTransient
	private String baseCustomerSex;//客户性别
	@XmlTransient
	private String birthDate;//出生年月日
	@XmlTransient
	private String mobileNo;//借款人手机号
	@XmlTransient
	private String appliIdEmail;//投保人邮箱
	@XmlTransient
	private String liveAddrs;//现居住地址
	@XmlTransient
	private String appliPost;//家庭邮编
	@XmlTransient
	private String familyTelcode;//住宅电话区号
	@XmlTransient
	private String fixedLine;//家庭固话
	@XmlTransient
	private String paymentAcctno;//还款账户
	@XmlTransient
	private String idvalidStart;//证件有效期自
	@XmlTransient
	private String idvalidEnd;//证件有效期至
	@XmlTransient
	private String issCtry;//发证国家

	@XmlTransient
	private String status; //0为正常数据 1为删除数据 
	@XmlTransient
	private String[] customerIds = new String[] {}; //批量删除时存放id的集合
	@XmlTransient
	private String customerLevel; //类型状态 1为潜在客户 2为意向客户 3为签约客户
	
	public String getBaseCustomerId() {
		return baseCustomerId;
	}
	public void setBaseCustomerId(String baseCustomerId) {
		this.baseCustomerId = baseCustomerId;
	}
	public String getBaseCustomerName() {
		return baseCustomerName;
	}
	public void setBaseCustomerName(String baseCustomerName) {
		this.baseCustomerName = baseCustomerName;
	}
	public String getBaseCustomerSex() {
		return baseCustomerSex;
	}
	public void setBaseCustomerSex(String baseCustomerSex) {
		this.baseCustomerSex = baseCustomerSex;
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
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getMarriageStatus() {
		return marriageStatus;
	}
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseIdType() {
		return spouseIdType;
	}
	public void setSpouseIdType(String spouseIdType) {
		this.spouseIdType = spouseIdType;
	}
	public String getSpouseIdNo() {
		return spouseIdNo;
	}
	public void setSpouseIdNo(String spouseIdNo) {
		this.spouseIdNo = spouseIdNo;
	}
	public String getSpouseMobile() {
		return spouseMobile;
	}
	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}
	public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	public String getLiveAddr() {
		return liveAddr;
	}
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getServiceYear() {
		return serviceYear;
	}
	public void setServiceYear(String serviceYear) {
		this.serviceYear = serviceYear;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getUnitKind() {
		return unitKind;
	}
	public void setUnitKind(String unitKind) {
		this.unitKind = unitKind;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getBankFlag() {
		return bankFlag;
	}
	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}
	public String getOpenBankName() {
		return openBankName;
	}
	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}
	public String getOpenBankNo() {
		return openBankNo;
	}
	public void setOpenBankNo(String openBankNo) {
		this.openBankNo = openBankNo;
	}
	public String getCertImageFile() {
		return certImageFile;
	}
	public void setCertImageFile(String certImageFile) {
		this.certImageFile = certImageFile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCensusRegisterPlace() {
		return censusRegisterPlace;
	}
	public void setCensusRegisterPlace(String censusRegisterPlace) {
		this.censusRegisterPlace = censusRegisterPlace;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFixedLine() {
		return fixedLine;
	}
	public void setFixedLine(String fixedLine) {
		this.fixedLine = fixedLine;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDeductionsObjectType() {
		return deductionsObjectType;
	}
	public void setDeductionsObjectType(String deductionsObjectType) {
		this.deductionsObjectType = deductionsObjectType;
	}
	public String getIsABC() {
		return isABC;
	}
	public void setIsABC(String isABC) {
		this.isABC = isABC;
	}
	public String getDraweeBankName() {
		return draweeBankName;
	}
	public void setDraweeBankName(String draweeBankName) {
		this.draweeBankName = draweeBankName;
	}
	public String getDraweeBankAccount() {
		return draweeBankAccount;
	}
	public void setDraweeBankAccount(String draweeBankAccount) {
		this.draweeBankAccount = draweeBankAccount;
	}
	public String getDraweeBank() {
		return draweeBank;
	}
	public void setDraweeBank(String draweeBank) {
		this.draweeBank = draweeBank;
	}
	public String getSpousePosition() {
		return spousePosition;
	}
	public void setSpousePosition(String spousePosition) {
		this.spousePosition = spousePosition;
	}
	public String getSpousePhone2() {
		return spousePhone2;
	}
	public void setSpousePhone2(String spousePhone2) {
		this.spousePhone2 = spousePhone2;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCertTypeName() {
		return certTypeName;
	}
	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}
	public String getLargeLineNo() {
		return largeLineNo;
	}
	public void setLargeLineNo(String largeLineNo) {
		this.largeLineNo = largeLineNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public String getBaseCustomerSexName() {
		return baseCustomerSexName;
	}
	public void setBaseCustomerSexName(String baseCustomerSexName) {
		this.baseCustomerSexName = baseCustomerSexName;
	}
	public String getMarriageStatusName() {
		return marriageStatusName;
	}
	public void setMarriageStatusName(String marriageStatusName) {
		this.marriageStatusName = marriageStatusName;
	}
	public String getSpouseIdTypeName() {
		return spouseIdTypeName;
	}
	public void setSpouseIdTypeName(String spouseIdTypeName) {
		this.spouseIdTypeName = spouseIdTypeName;
	}
	public String getEducationTypeName() {
		return educationTypeName;
	}
	public void setEducationTypeName(String educationTypeName) {
		this.educationTypeName = educationTypeName;
	}
	public String getOpenBankBaseName() {
		return openBankBaseName;
	}
	public void setOpenBankBaseName(String openBankBaseName) {
		this.openBankBaseName = openBankBaseName;
	}
	public String getDeductionsObjectTypeName() {
		return deductionsObjectTypeName;
	}
	public void setDeductionsObjectTypeName(String deductionsObjectTypeName) {
		this.deductionsObjectTypeName = deductionsObjectTypeName;
	}
	public String getIsABCName() {
		return isABCName;
	}
	public void setIsABCName(String isABCName) {
		this.isABCName = isABCName;
	}
	public String getAppliHealth() {
		return appliHealth;
	}
	public void setAppliHealth(String appliHealth) {
		this.appliHealth = appliHealth;
	}
	public String getAppliResidence() {
		return appliResidence;
	}
	public void setAppliResidence(String appliResidence) {
		this.appliResidence = appliResidence;
	}
	public String getAppliWork() {
		return appliWork;
	}
	public void setAppliWork(String appliWork) {
		this.appliWork = appliWork;
	}
	public String getIncomeSource() {
		return incomeSource;
	}
	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}
	public String getPiscale() {
		return piscale;
	}
	public void setPiscale(String piscale) {
		this.piscale = piscale;
	}
	public String getMonthEconomy() {
		return monthEconomy;
	}
	public void setMonthEconomy(String monthEconomy) {
		this.monthEconomy = monthEconomy;
	}
	public String getHouseStat() {
		return houseStat;
	}
	public void setHouseStat(String houseStat) {
		this.houseStat = houseStat;
	}
	public String getHouseSource() {
		return houseSource;
	}
	public void setHouseSource(String houseSource) {
		this.houseSource = houseSource;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getFamilySts() {
		return familySts;
	}
	public void setFamilySts(String familySts) {
		this.familySts = familySts;
	}
	public String getFamilyYear() {
		return familyYear;
	}
	public void setFamilyYear(String familyYear) {
		this.familyYear = familyYear;
	}
	public String getRepayRec() {
		return repayRec;
	}
	public void setRepayRec(String repayRec) {
		this.repayRec = repayRec;
	}
	public String getPubRec() {
		return pubRec;
	}
	public void setPubRec(String pubRec) {
		this.pubRec = pubRec;
	}
	public String getOthRec() {
		return othRec;
	}
	public void setOthRec(String othRec) {
		this.othRec = othRec;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSumScore() {
		return sumScore;
	}
	public void setSumScore(String sumScore) {
		this.sumScore = sumScore;
	}
	public String getCreditManCode() {
		return creditManCode;
	}
	public void setCreditManCode(String creditManCode) {
		this.creditManCode = creditManCode;
	}
	public String getCreditFlag() {
		return creditFlag;
	}
	public void setCreditFlag(String creditFlag) {
		this.creditFlag = creditFlag;
	}
	public String getCompanyZip() {
		return companyZip;
	}
	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}
	public String getCompanyTelCode() {
		return companyTelCode;
	}
	public void setCompanyTelCode(String companyTelCode) {
		this.companyTelCode = companyTelCode;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getApplyPurpose() {
		return applyPurpose;
	}
	public void setApplyPurpose(String applyPurpose) {
		this.applyPurpose = applyPurpose;
	}
	public String getMonthlyWages() {
		return monthlyWages;
	}
	public void setMonthlyWages(String monthlyWages) {
		this.monthlyWages = monthlyWages;
	}
	public String getMothEp() {
		return mothEp;
	}
	public void setMothEp(String mothEp) {
		this.mothEp = mothEp;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getAppliIdEmail() {
		return appliIdEmail;
	}
	public void setAppliIdEmail(String appliIdEmail) {
		this.appliIdEmail = appliIdEmail;
	}
	public String getAppliPost() {
		return appliPost;
	}
	public void setAppliPost(String appliPost) {
		this.appliPost = appliPost;
	}
	public String getFamilyTelcode() {
		return familyTelcode;
	}
	public void setFamilyTelcode(String familyTelcode) {
		this.familyTelcode = familyTelcode;
	}
	public String getPaymentAcctno() {
		return paymentAcctno;
	}
	public void setPaymentAcctno(String paymentAcctno) {
		this.paymentAcctno = paymentAcctno;
	}
	public String getIdvalidStart() {
		return idvalidStart;
	}
	public void setIdvalidStart(String idvalidStart) {
		this.idvalidStart = idvalidStart;
	}
	public String getIdvalidEnd() {
		return idvalidEnd;
	}
	public void setIdvalidEnd(String idvalidEnd) {
		this.idvalidEnd = idvalidEnd;
	}
	public String getIssCtry() {
		return issCtry;
	}
	public void setIssCtry(String issCtry) {
		this.issCtry = issCtry;
	}
	public String getAppliAge() {
		return appliAge;
	}
	public void setAppliAge(String appliAge) {
		this.appliAge = appliAge;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public String getLiveAddrs() {
		return liveAddrs;
	}
	public void setLiveAddrs(String liveAddrs) {
		this.liveAddrs = liveAddrs;
	}
	public String getAppliHealthName() {
		return appliHealthName;
	}
	public void setAppliHealthName(String appliHealthName) {
		this.appliHealthName = appliHealthName;
	}
	public String getIncomeSourceName() {
		return incomeSourceName;
	}
	public void setIncomeSourceName(String incomeSourceName) {
		this.incomeSourceName = incomeSourceName;
	}
	public String getHouseStatName() {
		return houseStatName;
	}
	public void setHouseStatName(String houseStatName) {
		this.houseStatName = houseStatName;
	}
	public String getHouseSourceName() {
		return houseSourceName;
	}
	public void setHouseSourceName(String houseSourceName) {
		this.houseSourceName = houseSourceName;
	}
	public String getHouseTypeName() {
		return houseTypeName;
	}
	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	public String getFamilyStsName() {
		return familyStsName;
	}
	public void setFamilyStsName(String familyStsName) {
		this.familyStsName = familyStsName;
	}
	public String getRepayRecName() {
		return repayRecName;
	}
	public void setRepayRecName(String repayRecName) {
		this.repayRecName = repayRecName;
	}
	public String getPubRecName() {
		return pubRecName;
	}
	public void setPubRecName(String pubRecName) {
		this.pubRecName = pubRecName;
	}
	public String getOthRecName() {
		return othRecName;
	}
	public void setOthRecName(String othRecName) {
		this.othRecName = othRecName;
	}
	public String getUnitTypeName() {
		return unitTypeName;
	}
	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}
	public String getLockMan() {
		return lockMan;
	}
	public void setLockMan(String lockMan) {
		this.lockMan = lockMan;
	}
	public String getLockManName() {
		return lockManName;
	}
	public void setLockManName(String lockManName) {
		this.lockManName = lockManName;
	}
	public String getAppliResidenceName() {
		return appliResidenceName;
	}
	public void setAppliResidenceName(String appliResidenceName) {
		this.appliResidenceName = appliResidenceName;
	}
	public String getAppliWorkName() {
		return appliWorkName;
	}
	public void setAppliWorkName(String appliWorkName) {
		this.appliWorkName = appliWorkName;
	}
	public String getPositionsName() {
		return positionsName;
	}
	public void setPositionsName(String positionsName) {
		this.positionsName = positionsName;
	}

}
