package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 担保人对象
 * 
 * @author Administrator
 *
 */
public class SponsorVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String sponsorId;//担保人ID
	private String sponsorName;//姓名
	private String sponsorSex;//性别
	private String sponsorSexName;//性别名称
	private String certTypeVL;//证件类型
	private String certTypeName;//证件类型名称
	private String certNo;//证件号码
	private String sponsorMobile;// 手机
	private String relationVL;//关系
	private String relationName;//关系名称
	private String nativePlace;//籍贯
	private String sponsorAge;//担保人年龄
	private String educationTypeVL;//最高学历
	private String educationTypeName;//最高学历名称
	private String companyName;//公司名称
	private String companyAddr;//地址
	private String income;//收入
	private String companyPhone;//单位电话
	private String position;//任职岗位
	private String otherIncome;//其他收入
	private String telephone;//固话
	private String applyInfoId;//
	public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getSponsorSex() {
		return sponsorSex;
	}
	public void setSponsorSex(String sponsorSex) {
		this.sponsorSex = sponsorSex;
	}
	public String getCertTypeVL() {
		return certTypeVL;
	}
	public void setCertTypeVL(String certTypeVL) {
		this.certTypeVL = certTypeVL;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getSponsorMobile() {
		return sponsorMobile;
	}
	public void setSponsorMobile(String sponsorMobile) {
		this.sponsorMobile = sponsorMobile;
	}
	public String getRelationVL() {
		return relationVL;
	}
	public void setRelationVL(String relationVL) {
		this.relationVL = relationVL;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getSponsorAge() {
		return sponsorAge;
	}
	public void setSponsorAge(String sponsorAge) {
		this.sponsorAge = sponsorAge;
	}
	public String getEducationTypeVL() {
		return educationTypeVL;
	}
	public void setEducationTypeVL(String educationTypeVL) {
		this.educationTypeVL = educationTypeVL;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getSponsorSexName() {
		return sponsorSexName;
	}
	public void setSponsorSexName(String sponsorSexName) {
		this.sponsorSexName = sponsorSexName;
	}
	public String getCertTypeName() {
		return certTypeName;
	}
	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getEducationTypeName() {
		return educationTypeName;
	}
	public void setEducationTypeName(String educationTypeName) {
		this.educationTypeName = educationTypeName;
	}

}
