package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class ProviderVO extends CommonVO{
	private static final long serialVersionUID = 1L;
	private String spId;//服务商ID
	private String spName;//服务商名称
	private String spContacts;//服务商联系人
	private String spPhone;//电话
	private String spMobile;//手机
	private String spFax;//传真
	private String spMail;//邮件
	private String spAddress;//地址
	private String spUrl;//网站
	private String spBankAccountName;//银行账户名称
	private String spBankAccount;//银行账户
	private String spBankCardNumber;//银行卡号
	private String spLoginName;//登录名
	private String spBusiness;//业务归属人
	private String spSuperior;//上级SP商
	private String spCategory;//sp商类别
	private String spInsurance;//保险公司
	private String spStatus;//状态
	private String Balance;//余额
	private String[] spIds = new String[] {};// 存放Ids集合
	public String[] getSpIds() {
		return spIds;
	}
	public void setSpIds(String[] spIds) {
		this.spIds = spIds;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	private String insurance;//余额
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getSpContacts() {
		return spContacts;
	}
	public void setSpContacts(String spContacts) {
		this.spContacts = spContacts;
	}
	public String getSpPhone() {
		return spPhone;
	}
	public void setSpPhone(String spPhone) {
		this.spPhone = spPhone;
	}
	public String getSpMobile() {
		return spMobile;
	}
	public void setSpMobile(String spMobile) {
		this.spMobile = spMobile;
	}
	public String getSpFax() {
		return spFax;
	}
	public void setSpFax(String spFax) {
		this.spFax = spFax;
	}
	public String getSpMail() {
		return spMail;
	}
	public void setSpMail(String spMail) {
		this.spMail = spMail;
	}
	public String getSpAddress() {
		return spAddress;
	}
	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}
	public String getSpUrl() {
		return spUrl;
	}
	public void setSpUrl(String spUrl) {
		this.spUrl = spUrl;
	}
	public String getSpBankAccountName() {
		return spBankAccountName;
	}
	public void setSpBankAccountName(String spBankAccountName) {
		this.spBankAccountName = spBankAccountName;
	}
	public String getSpBankAccount() {
		return spBankAccount;
	}
	public void setSpBankAccount(String spBankAccount) {
		this.spBankAccount = spBankAccount;
	}
	public String getSpBankCardNumber() {
		return spBankCardNumber;
	}
	public void setSpBankCardNumber(String spBankCardNumber) {
		this.spBankCardNumber = spBankCardNumber;
	}
	public String getSpLoginName() {
		return spLoginName;
	}
	public void setSpLoginName(String spLoginName) {
		this.spLoginName = spLoginName;
	}
	public String getSpBusiness() {
		return spBusiness;
	}
	public void setSpBusiness(String spBusiness) {
		this.spBusiness = spBusiness;
	}
	public String getSpSuperior() {
		return spSuperior;
	}
	public void setSpSuperior(String spSuperior) {
		this.spSuperior = spSuperior;
	}
	public String getSpCategory() {
		return spCategory;
	}
	public void setSpCategory(String spCategory) {
		this.spCategory = spCategory;
	}
	public String getSpInsurance() {
		return spInsurance;
	}
	public void setSpInsurance(String spInsurance) {
		this.spInsurance = spInsurance;
	}
	public String getSpStatus() {
		return spStatus;
	}
	public void setSpStatus(String spStatus) {
		this.spStatus = spStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
