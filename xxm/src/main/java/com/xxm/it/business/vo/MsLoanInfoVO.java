package com.xxm.it.business.vo;

import java.util.ArrayList;
import java.util.List;

import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.system.vo.CommonVO;

/**
 * 请求放款对象
 * 
 * @author Administrator
 *
 */
public class MsLoanInfoVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String loanInfoId;//
	private String proposalNo;//投保单号
	private String applyLoanTimes;//申请放款时间
	private String applicant;//申请人
	private String bankCode;//银行代码
	private String loanNo;//借据号
	private String applyNo;//申请书号
	private String eacNo;//放/还款账户
	private String atrsDueDay;//扣款日
	private String tacNo;//放款卡号
	private String coverNo;//商业车险保单号
	private String mortRegDate;//抵押登记日期
	private String payJnlNo;//租赁公司购车付款流水号
	private String payDate;//租赁公司购车付款日期
	private String payAmount;//租赁公司购车付款金额
	private String payeeName;//租赁公司购车收款方
	private String payeeAcct;//租赁公司购车收款账号
	private String downPayment;//首付款金额
	private String leaseCtctNo;//租赁合同编号
	private String leaseStarTime;//租赁合同生效日
	private String leaseEndTime;//租赁合同到期日
	private String leaseCtctAm;//租赁合同金额
	private String filekeyName;//影像名称
	private String applyInfoId;//申请id
	private String customerId;//客户信息id
	private String leaseInfoId;//租赁信息ID
	private String loaninfoStatus;//状态
	private BaseCustomerVO customerVO=new BaseCustomerVO();//客户
	private BaseLeaseInfoVO leaseInfoVO=new BaseLeaseInfoVO();//车辆
	private LoanReturnInfoVO loanReturnInfoVO=new LoanReturnInfoVO();//响应对象
	//响应对象list
	private List<LoanReturnInfoVO> loanReturnInfoList=new ArrayList<LoanReturnInfoVO>();
	public String getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BaseCustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(BaseCustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLeaseInfoId() {
		return leaseInfoId;
	}
	public void setLeaseInfoId(String leaseInfoId) {
		this.leaseInfoId = leaseInfoId;
	}
	public String getLoaninfoStatus() {
		return loaninfoStatus;
	}
	public void setLoaninfoStatus(String loaninfoStatus) {
		this.loaninfoStatus = loaninfoStatus;
	}
	public LoanReturnInfoVO getLoanReturnInfoVO() {
		return loanReturnInfoVO;
	}
	public void setLoanReturnInfoVO(LoanReturnInfoVO loanReturnInfoVO) {
		this.loanReturnInfoVO = loanReturnInfoVO;
	}
	public List<LoanReturnInfoVO> getLoanReturnInfoList() {
		return loanReturnInfoList;
	}
	public void setLoanReturnInfoList(List<LoanReturnInfoVO> loanReturnInfoList) {
		this.loanReturnInfoList = loanReturnInfoList;
	}
	public BaseLeaseInfoVO getLeaseInfoVO() {
		return leaseInfoVO;
	}
	public void setLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO) {
		this.leaseInfoVO = leaseInfoVO;
	}

	

}
