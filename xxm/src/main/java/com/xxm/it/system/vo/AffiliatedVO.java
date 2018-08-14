package com.xxm.it.system.vo;

public class AffiliatedVO extends CommonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyAffiliatedId; //id
	private String operationCompanyVl; //操作公司
	private String operationCompanyName; //操作公司名称
	private String categoryVL; //分类
	private String categoryName; //分类名称
	private String businessParnterNo; //商业伙伴编号
	private String companyAffiliatedName; //挂靠商名称
	private String businessParnterType; //商业伙伴类型
	private String orgCode; //组织结构代码
	private String businessLicenseNo; //营业执照号
	private String fax; //传真
	private String postCode; //邮编
	private String contactPerson; //联系人
	private String contactPhone; //联系电话
	private String contactAddress; //联系地址
	private String certNo; //证件号码
	private String status; //状态 0表示可查 1表示不可查
	private String[] affiliatedIds = new String[] {}; //批量删除时存放id的集合
	
	
	public String getCompanyAffiliatedId() {
		return companyAffiliatedId;
	}
	public void setCompanyAffiliatedId(String companyAffiliatedId) {
		this.companyAffiliatedId = companyAffiliatedId;
	}
	public String getOperationCompanyVl() {
		return operationCompanyVl;
	}
	public void setOperationCompanyVl(String operationCompanyVl) {
		this.operationCompanyVl = operationCompanyVl;
	}
	public String getCategoryVL() {
		return categoryVL;
	}
	public void setCategoryVL(String categoryVL) {
		this.categoryVL = categoryVL;
	}
	public String getBusinessParnterNo() {
		return businessParnterNo;
	}
	public void setBusinessParnterNo(String businessParnterNo) {
		this.businessParnterNo = businessParnterNo;
	}
	public String getCompanyAffiliatedName() {
		return companyAffiliatedName;
	}
	public void setCompanyAffiliatedName(String companyAffiliatedName) {
		this.companyAffiliatedName = companyAffiliatedName;
	}
	public String getBusinessParnterType() {
		return businessParnterType;
	}
	public void setBusinessParnterType(String businessParnterType) {
		this.businessParnterType = businessParnterType;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getAffiliatedIds() {
		return affiliatedIds;
	}
	public void setAffiliatedIds(String[] affiliatedIds) {
		this.affiliatedIds = affiliatedIds;
	}
	public String getOperationCompanyName() {
		return operationCompanyName;
	}
	public void setOperationCompanyName(String operationCompanyName) {
		this.operationCompanyName = operationCompanyName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
