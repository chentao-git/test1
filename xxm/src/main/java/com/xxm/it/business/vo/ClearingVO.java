package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class ClearingVO extends CommonVO {
	private static final long serialVersionUID = 1L;
	private String clearingId;// 结清表ID主键
	private String applyInfoId;// 申请表ID外键
	private String clearingType;// 结清类型
	private String clearingDate;// 结清时间
	private String clearingPerson;// 经手人
	private String registrant;// 登记人
	private String remark;// 备注
	private String extendField1;// 扩展字段
	private String extendField2;

	private String loanContractNo;// 贷款合同编号
	private String baseCustomerName;// 客户名称
	private String productionVl;// 产品信息
	private String productionName;//产品名称
	private String applyInfoStatus;// 申请信息状态
	private String certType;// 借款人证件类型
	private String certNo;// 证件号
	private String mobileNo; // 手机
	private String fixedLine; // 电话
	private String salesman;//业务员

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getProductionVl() {
		return productionVl;
	}

	public void setProductionVl(String productionVl) {
		this.productionVl = productionVl;
	}

	public String getProductionName() {
		return productionName;
	}

	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}

	public String getApplyInfoStatus() {
		return applyInfoStatus;
	}

	public void setApplyInfoStatus(String applyInfoStatus) {
		this.applyInfoStatus = applyInfoStatus;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFixedLine() {
		return fixedLine;
	}

	public void setFixedLine(String fixedLine) {
		this.fixedLine = fixedLine;
	}

	public String getBaseCustomerName() {
		return baseCustomerName;
	}

	public void setBaseCustomerName(String baseCustomerName) {
		this.baseCustomerName = baseCustomerName;
	}

	public String getLoanContractNo() {
		return loanContractNo;
	}

	public void setLoanContractNo(String loanContractNo) {
		this.loanContractNo = loanContractNo;
	}

	public String getClearingId() {
		return clearingId;
	}

	public void setClearingId(String clearingId) {
		this.clearingId = clearingId;
	}

	public String getApplyInfoId() {
		return applyInfoId;
	}

	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}

	public String getClearingType() {
		return clearingType;
	}

	public void setClearingType(String clearingType) {
		this.clearingType = clearingType;
	}

	public String getClearingDate() {
		return clearingDate;
	}

	public void setClearingDate(String clearingDate) {
		this.clearingDate = clearingDate;
	}

	public String getClearingPerson() {
		return clearingPerson;
	}

	public void setClearingPerson(String clearingPerson) {
		this.clearingPerson = clearingPerson;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExtendField1() {
		return extendField1;
	}

	public void setExtendField1(String extendField1) {
		this.extendField1 = extendField1;
	}

	public String getExtendField2() {
		return extendField2;
	}

	public void setExtendField2(String extendField2) {
		this.extendField2 = extendField2;
	}

}
