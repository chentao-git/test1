package com.xxm.it.business.vo;


import java.util.ArrayList;
import java.util.List;

import com.xxm.it.piic.vo.PolicyinfoReturnVO;
import com.xxm.it.piic.vo.RejudicationReturnVO;
import com.xxm.it.system.vo.AttachmentTextVO;
import com.xxm.it.system.vo.CommonVO;

/**
 * 申请对象
 * 
 * @author Administrator
 *
 */
public class ApplyVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String applyInfoId;//  申请表ID
	private String loanContractNo;//贷款合同编号
	private String productionVl;//产品信息
	private String productionName;
	private String stockCarVl;//是否存量车，1为是，0为否
	private String stockCarName;//是否存量车名称
	private String operatingCompanyVl;//操作公司
	private String autoDealerVl;//汽车经销商
	private String autoDealerName;//汽车经销商名称
	private String insuranceCompanyVl;//保险公司
	private String insuranceCompanyName;//保险公司名称
	private String managementOfCapitalVl;//资方管理
	private String managementOfCapitalName;//资方管理名称
	private String loanBankVl;//贷款银行
	private String loanBusinessVl;//贷款业务
	private String vehicleTypeVl;//车辆类型
	private String applyCustomerId;//客户业务ID
	private String companyAffiliatedId;//挂靠商ID
	private String applyInfoStatus;//申请信息状态
	private String applyInfoStatusName;//申请信息状态名称

	private String mktProdType;//流程标志
	private String mktProdTypeName;//流程标志名称
	private String cooprProdType;//合作商产品类型
	private String cooprProdTypeName;//合作商产品类型名称
	private String insuredPlanId;//方案信息id
	private String citemCarId;//车辆信息id
	private String insuredCreditInvest;//资信等级信息id
	private String citemCreditId;//贷款信息id
	private String applicantId;//投保人信息id
	private String fileKeyList;//附件信息（影像资料名称）每张图片名称不能超过80个字节长度
	private String prejudicNo;//预审单号
	private String salesman;//业务员
	private String salesmanName;//业务员名称
	private String proposalNo;//投保单号 投保单号 初审回传
	
	private String bankCode;//银行代码
	private String maincomCode;//归属机构代码
	
	private String applyNo;//申请书编号

//	private String customerId;//客户"业务"信息Id
	private String operationType;//操作类型  （提交、保存等等）
	private String[] applyInfoIds=new String[]{};//存放申请表ID集合
	private String isValid;//0有效，1作废 2银行预审接口 3投保接口
	//客户对象
	private BaseCustomerVO customerVO=new BaseCustomerVO();
	//补充基本信息对象
	private SupplementBaseVO supplementBaseVO=new SupplementBaseVO();
	//报价信息对象
	private QuotedPriceVO quotedPriceVO=new QuotedPriceVO();
	//还款计划对象
	private RepaymentPlanVO repaymentPlanVO=new RepaymentPlanVO();
	//租赁信息对象
	private BaseLeaseInfoVO leaseInfoVO=new BaseLeaseInfoVO();
	//车辆保险对象
	private CarInsuranceVO carInsuranceVO=new CarInsuranceVO();
	//保证保险对象
	private GuaranteeInsuranceVO guaranteeInsuranceVO=new GuaranteeInsuranceVO();
	//担保人集合
	private List<SponsorVO> sponsorList=new ArrayList<SponsorVO>();
	//附件集合
	private List<AttachmentTextVO> attachmentTextList=new ArrayList<AttachmentTextVO>();
	//投保单归属机构对象
	private MsCoinVO msCoinVO=new MsCoinVO();
	//方案信息对象
	private MsInsuredPlanVO msInsuredPlanVO=new MsInsuredPlanVO();
	//被保人信息对象
	private MsInsuredVO msInsuredVO=new MsInsuredVO();
	//预审响应对象
	private RejudicationReturnVO rejudicationReturnVO=new RejudicationReturnVO();
	//预审响应对象List
	private List<RejudicationReturnVO> rejudicationReturnList=new ArrayList<RejudicationReturnVO>();
	//投保响应对象
	private PolicyinfoReturnVO policyinfoReturnVO=new PolicyinfoReturnVO();
	//投保响应对象List
	private List<PolicyinfoReturnVO> policyinfoReturnList=new ArrayList<PolicyinfoReturnVO>();
	//请求放款对象
	private MsLoanInfoVO msLoanInfoVO =new MsLoanInfoVO();
	//车辆抵押登记信息对象
	private MsMortgageInfoVO msMortgageInfoVO=new MsMortgageInfoVO();
	public MsCoinVO getMsCoinVO() {
		return msCoinVO;
	}
	public void setMsCoinVO(MsCoinVO msCoinVO) {
		this.msCoinVO = msCoinVO;
	}
	public MsInsuredPlanVO getMsInsuredPlanVO() {
		return msInsuredPlanVO;
	}
	public void setMsInsuredPlanVO(MsInsuredPlanVO msInsuredPlanVO) {
		this.msInsuredPlanVO = msInsuredPlanVO;
	}
	public MsInsuredVO getMsInsuredVO() {
		return msInsuredVO;
	}
	public void setMsInsuredVO(MsInsuredVO msInsuredVO) {
		this.msInsuredVO = msInsuredVO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getLoanContractNo() {
		return loanContractNo;
	}
	public void setLoanContractNo(String loanContractNo) {
		this.loanContractNo = loanContractNo;
	}
	public String getProductionVl() {
		return productionVl;
	}
	public void setProductionVl(String productionVl) {
		this.productionVl = productionVl;
	}
	public String getStockCarVl() {
		return stockCarVl;
	}
	public void setStockCarVl(String stockCarVl) {
		this.stockCarVl = stockCarVl;
	}
	public String getOperatingCompanyVl() {
		return operatingCompanyVl;
	}
	public void setOperatingCompanyVl(String operatingCompanyVl) {
		this.operatingCompanyVl = operatingCompanyVl;
	}
	public String getAutoDealerVl() {
		return autoDealerVl;
	}
	public void setAutoDealerVl(String autoDealerVl) {
		this.autoDealerVl = autoDealerVl;
	}
	public String getInsuranceCompanyVl() {
		return insuranceCompanyVl;
	}
	public void setInsuranceCompanyVl(String insuranceCompanyVl) {
		this.insuranceCompanyVl = insuranceCompanyVl;
	}
	public String getManagementOfCapitalVl() {
		return managementOfCapitalVl;
	}
	public void setManagementOfCapitalVl(String managementOfCapitalVl) {
		this.managementOfCapitalVl = managementOfCapitalVl;
	}
	public String getLoanBankVl() {
		return loanBankVl;
	}
	public void setLoanBankVl(String loanBankVl) {
		this.loanBankVl = loanBankVl;
	}
	public String getLoanBusinessVl() {
		return loanBusinessVl;
	}
	public void setLoanBusinessVl(String loanBusinessVl) {
		this.loanBusinessVl = loanBusinessVl;
	}
	public String getVehicleTypeVl() {
		return vehicleTypeVl;
	}
	public void setVehicleTypeVl(String vehicleTypeVl) {
		this.vehicleTypeVl = vehicleTypeVl;
	}
	public String getApplyCustomerId() {
		return applyCustomerId;
	}
	public void setApplyCustomerId(String applyCustomerId) {
		this.applyCustomerId = applyCustomerId;
	}
	public String getCompanyAffiliatedId() {
		return companyAffiliatedId;
	}
	public void setCompanyAffiliatedId(String companyAffiliatedId) {
		this.companyAffiliatedId = companyAffiliatedId;
	}
	public String getApplyInfoStatus() {
		return applyInfoStatus;
	}
	public void setApplyInfoStatus(String applyInfoStatus) {
		this.applyInfoStatus = applyInfoStatus;
	}
	public BaseCustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(BaseCustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	public SupplementBaseVO getSupplementBaseVO() {
		return supplementBaseVO;
	}
	public void setSupplementBaseVO(SupplementBaseVO supplementBaseVO) {
		this.supplementBaseVO = supplementBaseVO;
	}
	public QuotedPriceVO getQuotedPriceVO() {
		return quotedPriceVO;
	}
	public void setQuotedPriceVO(QuotedPriceVO quotedPriceVO) {
		this.quotedPriceVO = quotedPriceVO;
	}
	public RepaymentPlanVO getRepaymentPlanVO() {
		return repaymentPlanVO;
	}
	public void setRepaymentPlanVO(RepaymentPlanVO repaymentPlanVO) {
		this.repaymentPlanVO = repaymentPlanVO;
	}
	public CarInsuranceVO getCarInsuranceVO() {
		return carInsuranceVO;
	}
	public void setCarInsuranceVO(CarInsuranceVO carInsuranceVO) {
		this.carInsuranceVO = carInsuranceVO;
	}
	public GuaranteeInsuranceVO getGuaranteeInsuranceVO() {
		return guaranteeInsuranceVO;
	}
	public void setGuaranteeInsuranceVO(GuaranteeInsuranceVO guaranteeInsuranceVO) {
		this.guaranteeInsuranceVO = guaranteeInsuranceVO;
	}
	public List<SponsorVO> getSponsorList() {
		return sponsorList;
	}
	public void setSponsorList(List<SponsorVO> sponsorList) {
		this.sponsorList = sponsorList;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String[] getApplyInfoIds() {
		return applyInfoIds;
	}
	public void setApplyInfoIds(String[] applyInfoIds) {
		this.applyInfoIds = applyInfoIds;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getStockCarName() {
		return stockCarName;
	}
	public void setStockCarName(String stockCarName) {
		this.stockCarName = stockCarName;
	}
	public String getAutoDealerName() {
		return autoDealerName;
	}
	public void setAutoDealerName(String autoDealerName) {
		this.autoDealerName = autoDealerName;
	}
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public String getManagementOfCapitalName() {
		return managementOfCapitalName;
	}
	public void setManagementOfCapitalName(String managementOfCapitalName) {
		this.managementOfCapitalName = managementOfCapitalName;
	}
	public List<AttachmentTextVO> getAttachmentTextList() {
		return attachmentTextList;
	}
	public void setAttachmentTextList(List<AttachmentTextVO> attachmentTextList) {
		this.attachmentTextList = attachmentTextList;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getApplyInfoStatusName() {
		return applyInfoStatusName;
	}
	public void setApplyInfoStatusName(String applyInfoStatusName) {
		this.applyInfoStatusName = applyInfoStatusName;
	}
	public String getMktProdType() {
		return mktProdType;
	}
	public void setMktProdType(String mktProdType) {
		this.mktProdType = mktProdType;
	}
	public String getCooprProdType() {
		return cooprProdType;
	}
	public void setCooprProdType(String cooprProdType) {
		this.cooprProdType = cooprProdType;
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
	public String getFileKeyList() {
		return fileKeyList;
	}
	public void setFileKeyList(String fileKeyList) {
		this.fileKeyList = fileKeyList;
	}
	public String getPrejudicNo() {
		return prejudicNo;
	}
	public void setPrejudicNo(String prejudicNo) {
		this.prejudicNo = prejudicNo;
	}
	public String getMktProdTypeName() {
		return mktProdTypeName;
	}
	public void setMktProdTypeName(String mktProdTypeName) {
		this.mktProdTypeName = mktProdTypeName;
	}
	public String getCooprProdTypeName() {
		return cooprProdTypeName;
	}
	public void setCooprProdTypeName(String cooprProdTypeName) {
		this.cooprProdTypeName = cooprProdTypeName;
	}
	public RejudicationReturnVO getRejudicationReturnVO() {
		return rejudicationReturnVO;
	}
	public void setRejudicationReturnVO(RejudicationReturnVO rejudicationReturnVO) {
		this.rejudicationReturnVO = rejudicationReturnVO;
	}
	public List<RejudicationReturnVO> getRejudicationReturnList() {
		return rejudicationReturnList;
	}
	public void setRejudicationReturnList(List<RejudicationReturnVO> rejudicationReturnList) {
		this.rejudicationReturnList = rejudicationReturnList;
	}
	public List<PolicyinfoReturnVO> getPolicyinfoReturnList() {
		return policyinfoReturnList;
	}
	public void setPolicyinfoReturnList(List<PolicyinfoReturnVO> policyinfoReturnList) {
		this.policyinfoReturnList = policyinfoReturnList;
	}
	public PolicyinfoReturnVO getPolicyinfoReturnVO() {
		return policyinfoReturnVO;
	}
	public void setPolicyinfoReturnVO(PolicyinfoReturnVO policyinfoReturnVO) {
		this.policyinfoReturnVO = policyinfoReturnVO;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public MsLoanInfoVO getMsLoanInfoVO() {
		return msLoanInfoVO;
	}
	public void setMsLoanInfoVO(MsLoanInfoVO msLoanInfoVO) {
		this.msLoanInfoVO = msLoanInfoVO;
	}
	public MsMortgageInfoVO getMsMortgageInfoVO() {
		return msMortgageInfoVO;
	}
	public void setMsMortgageInfoVO(MsMortgageInfoVO msMortgageInfoVO) {
		this.msMortgageInfoVO = msMortgageInfoVO;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public BaseLeaseInfoVO getLeaseInfoVO() {
		return leaseInfoVO;
	}
	public void setLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO) {
		this.leaseInfoVO = leaseInfoVO;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getMaincomCode() {
		return maincomCode;
	}
	public void setMaincomCode(String maincomCode) {
		this.maincomCode = maincomCode;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

}
