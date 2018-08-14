package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

public class ApplicantVO  extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String baseCustomerId;//客户"基本"信息Id
	@XmlTransient
	private String customerId;//客户"业务"信息Id
	@XmlElement(name="AppliName")
	private String baseCustomerName;// 借款人姓名
	@XmlElement(name="AppliIdType")
	private String certType;//借款人证件类型
	@XmlElement(name="AppliIdNo")
	private String certNo;//借款人证件号码
	@XmlElement(name="AppliSex")
	private String baseCustomerSex;//客户性别
	@XmlElement(name="AppliBirthday")
	private String birthDate;//出生年月日
	@XmlElement(name="AppliIdMobile")
	private String mobileNo;//借款人手机号
	@XmlElement(name="AppliIdEmail")
	private String appliIdEmail;//投保人邮箱
	@XmlElement(name="AppliAddress")
	private String liveAddrs;//现居住地址
	@XmlElement(name="AppliPost")
	private String appliPost;//家庭邮编
	@XmlElement(name="familyTelCode")
	private String familyTelcode;//住宅电话区号
	@XmlElement(name="familyTel")
	private String fixedLine;//家庭固话
	@XmlElement(name="paymentAcctNo")
	private String paymentAcctno;//还款账户
	@XmlElement(name="IdvalidStart")
	private String idvalidStart;//证件有效期自
	@XmlElement(name="IdvalidEnd")
	private String idvalidEnd;//证件有效期至
	@XmlElement(name="IssCtry")
	private String issCtry;//发证国家
	
	public String getBaseCustomerId() {
		return baseCustomerId;
	}
	public void setBaseCustomerId(String baseCustomerId) {
		this.baseCustomerId = baseCustomerId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBaseCustomerName() {
		return baseCustomerName;
	}
	public void setBaseCustomerName(String baseCustomerName) {
		this.baseCustomerName = baseCustomerName;
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
	public String getBaseCustomerSex() {
		return baseCustomerSex;
	}
	public void setBaseCustomerSex(String baseCustomerSex) {
		this.baseCustomerSex = baseCustomerSex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAppliIdEmail() {
		return appliIdEmail;
	}
	public void setAppliIdEmail(String appliIdEmail) {
		this.appliIdEmail = appliIdEmail;
	}
	public String getLiveAddrs() {
		return liveAddrs;
	}
	public void setLiveAddrs(String liveAddrs) {
		this.liveAddrs = liveAddrs;
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
	public String getFixedLine() {
		return fixedLine;
	}
	public void setFixedLine(String fixedLine) {
		this.fixedLine = fixedLine;
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
	
	
}
