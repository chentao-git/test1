package com.xxm.it.piic.dao;

import java.util.List;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.piic.vo.ApplicantVO;
import com.xxm.it.piic.vo.AuditReturnInfo;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.GeneralInfoVO;
import com.xxm.it.piic.vo.InsureBeneficiary;
import com.xxm.it.piic.vo.InsureCoin;
import com.xxm.it.piic.vo.InsurePolicyInfo;
import com.xxm.it.piic.vo.InsureTheInsuredInfo;
import com.xxm.it.piic.vo.InsuredEngage;
import com.xxm.it.piic.vo.InsuredPlan;
import com.xxm.it.piic.vo.InsuredReturn;
import com.xxm.it.piic.vo.InsuredScheme;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanInfoReturnInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.LoanVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.MortgageinfoVO;
import com.xxm.it.piic.vo.PolicyReturnInfoVO;
import com.xxm.it.piic.vo.PolicyinfoReturnVO;
import com.xxm.it.piic.vo.PrejudicationVO;
import com.xxm.it.piic.vo.PushPolicyInfoVO;
import com.xxm.it.piic.vo.RejudicationReturnVO;
import com.xxm.it.piic.vo.ResponseInfoVO;
import com.xxm.it.piic.vo.SignInfoVO;
import com.xxm.it.piic.vo.SignReturnInfoVO;

public interface ITransferPICCDao {

	/**
	 * 查询预审信息
	 * 
	 * @param AttachmentTextVO
	 * @return
	 */
	public PrejudicationVO findPrejudication(PrejudicationVO prejudicationVO);

	/**
	 * 添加银行预审响应信息
	 * 
	 * @param RejudicationReturnVO
	 * @return
	 */
	public int addRejudicationReturn(RejudicationReturnVO rejudicationReturnVO);

	/**
	 * 添加接口响应记录
	 * 
	 * @param responseInfoVO
	 */
	public void addResponseInfo(ResponseInfoVO responseInfoVO);

	/**
	 * 银行预审后修改申请状态
	 * 
	 * @param applyVO
	 */
	public void uadateApplyInfo(ApplyVO applyVO);
	/**
	 * 银行预审后修改申请书编号
	 * @param auditinfo
	 */
	public void updateApplyNo(Auditinfo auditinfo);
	/**
	 * 查询接口报文通用信息
	 * 
	 * @return
	 */
	public GeneralInfoVO findGeneralInfo();

	/**
	 * 请求放款
	 * 
	 * @param loanVO
	 * @return
	 */
	public LoanVO findLoan(LoanVO loanVO);

	/**
	 * 请求放款响应信息
	 * 
	 * @param loanReturnInfoVO
	 * @return
	 */
	public int addLoanReturnInfo(LoanReturnInfoVO loanReturnInfoVO);

	/**
	 * 添加审核结果通知接口数据
	 * 
	 * @param auditInfo
	 */
	public int addAuditInfo(Auditinfo auditInfo);

	/**
	 * 审核结果通知接口返回信息
	 * 
	 * @return
	 */
	public AuditReturnInfo findAuditReturnInfo(int id);

	/**
	 * 添加放款通知接口数据
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public int addLoanInfoInform(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 放款通知接口返回信息
	 * 
	 * @param id
	 * @return
	 */
	public LoanInfoReturnInformVO findLoanInfoReturnInform(int id);

	/**
	 * 添加推送保单号list信息
	 * 
	 * @param pushPolicyInfoList
	 * @return
	 */
	public int addPushPolicyInfo(PushPolicyInfoVO pushPolicyInfoVO);

	/**
	 * 推送保单号返回信息
	 * 
	 * @param id
	 * @return
	 */
	public PolicyReturnInfoVO findPolicyReturnInfo(int id);

	/**
	 * 查询接口报文通用信息是否存在
	 * 
	 * @param generalInfoVO
	 * @return
	 */
	public int findGeneralInfoNum(GeneralInfoVO generalInfoVO);

	/**
	 * 投保信息 （根据申请id去查询投保信息）
	 * 
	 * @param applyInfoId
	 * @return
	 */
	public List<InsurePolicyInfo> findInsurePolicyList(ApplyVO applyVO);

	/**
	 * 投保信息 (投保单归属机构信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<InsureCoin> findInsureCoin(ApplyVO applyVO);

	/**
	 * 投保信息 (方案信息)
	 * 
	 * @param insuredPlan
	 * @return
	 */
	public InsuredPlan findInsuredPlan(ApplyVO applyVO);

	/**
	 * 投保信息(条款信息)
	 */
	public List<InsuredScheme> findInsuredScheme(InsuredPlan insuredPlan);

	/**
	 * 投保信息(特别约定信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<InsuredEngage> findInsuredEngage(InsuredPlan insuredPlan);

	/**
	 * 投保信息(车辆基础信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public InsurePolicyInfo findInsurePolicyInfo(ApplyVO applyVO);

	/**
	 * 投保信息(投保人基础信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public InsurePolicyInfo findCustomerInsurePolicyInfo(ApplyVO applyVO);

	/**
	 * 投保信息(贷款信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public InsurePolicyInfo findCitemInsurePolicyInfo(ApplyVO applyVO);

	/**
	 * 投保信息(投保人信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public ApplicantVO findApplicantInsurePolicyInfo(ApplyVO applyVO);

	/**
	 * 投保信息(受保人信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<InsureTheInsuredInfo> findInsureTheInsuredInfoList(ApplyVO applyVO);

	/**
	 * 投保信息(受益人信息)
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<InsureBeneficiary> findInsureBeneficiaryList(InsureTheInsuredInfo insureTheInsuredInfo);

	/**
	 * 投保响应信息
	 * 
	 * @param policyinfoReturnVO
	 * @return
	 */
	public int addPolicyinfoReturn(PolicyinfoReturnVO policyinfoReturnVO);

	public void addInsuredReturn(InsuredReturn insuredReturn);

	/**
	 * 签约状态查询
	 * 
	 * @param signInfoVO
	 * @return
	 */
	public SignInfoVO findSignInfo(SignInfoVO signInfoVO);
	
	/**
	 * 签约状态查询(List)
	 * 
	 * @param signInfoVO
	 * @return
	 */
	public List<SignInfoVO> findSignInfos(SignInfoVO signInfoVO);

	/**
	 * 预审响应信息（list）
	 * 
	 * @param signInfoVO
	 * @return
	 */
	public List<RejudicationReturnVO> findRejudicationReturn(RejudicationReturnVO rejudicationReturnVO);

	/**
	 * 投保响应信息（list）
	 * 
	 * @param policyinfoReturnVO
	 * @return
	 */
	public List<PolicyinfoReturnVO> findPolicyInfoReturn(PolicyinfoReturnVO policyinfoReturnVO);

	/**
	 * 请求放款响应信息（list）
	 * 
	 * @param loanReturnInfoVO
	 * @return
	 */
	public List<LoanReturnInfoVO> findLoanReturnInfo(LoanReturnInfoVO loanReturnInfoVO);

	/**
	 * 添加签约状态响应信息
	 * 
	 * @param signReturnInfoVO
	 */
	public void addSignReturnInfo(SignReturnInfoVO signReturnInfoVO);

	/**
	 * 车辆抵押信息登记信息
	 * 
	 * @param mortgageinfoVO
	 * @return
	 */
	public MortgageinfoVO findMortgageinfo(MortgageinfoVO mortgageinfoVO);

	/**
	 * 车辆抵押信息登记响应信息
	 * 
	 * @param mortgageReturnInfoVO
	 */
	public void addMortgageReturnInfoVO(MortgageReturnInfoVO mortgageReturnInfoVO);

	/**
	 * 签约响应信息（list）
	 * 
	 * @param signReturnInfoVO
	 * @return
	 */
	public List<SignReturnInfoVO> findSignreturnInfo(SignReturnInfoVO signReturnInfoVO);

	/**
	 * 车辆抵押响应信息（list）
	 * 
	 * @param mortgageReturnInfoVO
	 * @return
	 */
	public List<MortgageReturnInfoVO> findMortgageReturninfo(MortgageReturnInfoVO mortgageReturnInfoVO);

	/**
	 * 查询审核结果通知集合数据
	 * 
	 * @param auditInfo
	 */
	public List<Auditinfo> findAuditNoticeList(Auditinfo auditInfo);

	/**
	 * 查询审核结果通知集合数据总数
	 * 
	 * @param auditInfo
	 */
	public int findAuditNoticeListCount(Auditinfo auditInfo);

	/**
	 * 查询审核结果通知详情
	 * 
	 * @param auditInfo
	 */
	public Auditinfo findAuditNoticeInfo(Auditinfo auditInfo);
	
	/**
	 * 查询审核结果通知详情,返回list
	 * 
	 * @param auditInfo
	 */
	public List<Auditinfo> findAuditNoticeInfos(Auditinfo auditInfo);

	/**
	 * 查询放款通知集合数据
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public List<LoanInfoInformVO> findLoanNoticeList(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 查询放款通知集合数据总数
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public int findLoanNoticeListCount(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 查询放款通知详细
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public LoanInfoInformVO findLoanNoticeInfo(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 查询推送保单号list信息
	 * 
	 * @param pushPolicyInfoList
	 * @return
	 */
	public List<PushPolicyInfoVO> findPushPolicyList(PushPolicyInfoVO pushPolicyInfoVO);

	/**
	 * 查询推送保单号总数
	 * 
	 * @param pushPolicyInfoList
	 * @return
	 */
	public int findPushPolicyListCount(PushPolicyInfoVO pushPolicyInfoVO);

	/**
	 * 查询推送保单号详情
	 * 
	 * @param pushPolicyInfoVO
	 * @return
	 */
	public PushPolicyInfoVO findPushPolicyInfo(PushPolicyInfoVO pushPolicyInfoVO);
	/**
	 * 审核结果通知(根据请求过来的预审单号去拿到申请id)
	 * @return
	 */
	public PrejudicationVO findBankPretrial(Auditinfo auditinfo);
	/**
	 * 审核结果通知(初审终审根据请求过来的投保单号去拿到申请id)
	 * @param auditinfo
	 * @return
	 */
	public PolicyinfoReturnVO findApplyInfoId(Auditinfo auditinfo);
}
