package com.xxm.it.business.dao;


import java.util.List;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.business.vo.CarInsuranceVO;
import com.xxm.it.business.vo.GuaranteeInsuranceVO;
import com.xxm.it.business.vo.MsBeneficiaryVO;
import com.xxm.it.business.vo.MsCoinVO;
import com.xxm.it.business.vo.MsEngageVO;
import com.xxm.it.business.vo.MsInsuredPlanVO;
import com.xxm.it.business.vo.MsInsuredVO;
import com.xxm.it.business.vo.MsSchemeVO;
import com.xxm.it.business.vo.QuotedPriceVO;
import com.xxm.it.business.vo.RepaymentPlanVO;
import com.xxm.it.business.vo.SponsorVO;
import com.xxm.it.business.vo.SupplementBaseVO;

public interface IApplyDao {

	/**
	 * 查询申请信息列表
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<ApplyVO> findApplyInfoList(ApplyVO applyVO);

	/**
	 * 查询申请信息列表总数
	 * 
	 * @param applyVO
	 * @return
	 */
	public int findApplyInfoListCount(ApplyVO applyVO);
	
	/**
	 * 添加申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	public void addApply(ApplyVO applyVO);

	/**
	 * 查询申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	public ApplyVO findApplyInfo(ApplyVO applyVO);
	
	/**
	 * 查询申请信息（返回list）
	 * 
	 * @param applyVO
	 * @return
	 */
	public List<ApplyVO> findApplyInfos(ApplyVO applyVO);
	
	/**
	 * 修改申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	public void updateApplyInfo(ApplyVO applyVO);
	
	/**
	 * 新增补充基本信息
	 * 
	 * @param supplementBaseVO
	 * @return
	 */
	public void addSupplementBase(SupplementBaseVO supplementBaseVO);
	/**
	 * 新增报价信息
	 * 
	 * @param quotedPriceVO
	 * @return
	 */
	public void addQuotedPrice(QuotedPriceVO quotedPriceVO);
	/**
	 * 新增还款计划
	 * 
	 * @param repaymentPlanVO
	 * @return
	 */
	public void addRepaymentPlan(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 新增基础租赁信息
	 * 
	 * @param baseLeaseInfoVO
	 * @return
	 */
	public void addBaseLeaseInfo(BaseLeaseInfoVO baseLeaseInfoVO);
	/**
	 * 新增车辆保险
	 * 
	 * @param carInsuranceVO
	 * @return
	 */
	public void addCarInsurance(CarInsuranceVO carInsuranceVO);
	/**
	 * 新增保证保险
	 * 
	 * @param guaranteeInsuranceVO
	 * @return
	 */
	public void addGuaranteeInsurance(GuaranteeInsuranceVO guaranteeInsuranceVO);
	/**
	 * 新增担保人
	 * 
	 * @param sponsorVO
	 * @return
	 */
	public void addSponsor(SponsorVO sponsorVO);
	
	/**
	 * 查询基本信息补充
	 * 
	 * @param SupplementBaseVO
	 * @return
	 */
	public SupplementBaseVO findSupplementBase(SupplementBaseVO SupplementBaseVO);
	
	/**
	 * 查询报价信息
	 * 
	 * @param QuotedPriceVO
	 * @return
	 */
	public QuotedPriceVO findQuotedPrice(QuotedPriceVO QuotedPriceVO);
	
	/**
	 * 查询还款计划
	 * 
	 * @param RepaymentPlanVO
	 * @return
	 */
	public List<RepaymentPlanVO> findRepaymentPlans(RepaymentPlanVO RepaymentPlanVO);
	
	/**
	 * 查询车辆保险对象
	 * 
	 * @param CarInsuranceVO
	 * @return
	 */
	public CarInsuranceVO findCarInsurance(CarInsuranceVO CarInsuranceVO);
	
	/**
	 * 查询保证保险对象
	 * 
	 * @param GuaranteeInsuranceVO
	 * @return
	 */
	public GuaranteeInsuranceVO findGuaranteeInsurance(GuaranteeInsuranceVO GuaranteeInsuranceVO);
	
	/**
	 * 查询担保人
	 * 
	 * @param SponsorVO
	 * @return
	 */
	public List<SponsorVO> findSponsors(SponsorVO SponsorVO);
	
	/**
	 * 修改补充基本信息
	 * 
	 * @param supplementBaseVO
	 * @return
	 */
	public void updateSupplementBase(SupplementBaseVO supplementBaseVO);
	
	/**
	 * 修改报价信息
	 * 
	 * @param quotedPriceVO
	 * @return
	 */
	public void updateQuotedPrice(QuotedPriceVO quotedPriceVO);
	
	/**
	 * 修改车辆保险信息
	 * 
	 * @param carInsuranceVO
	 * @return
	 */
	public void updateCarInsurance(CarInsuranceVO carInsuranceVO);
	
	/**
	 * 修改保证保险信息
	 * 
	 * @param guaranteeInsuranceVO
	 * @return
	 */
	public void updateGuaranteeInsurance(GuaranteeInsuranceVO guaranteeInsuranceVO);
	
	/**
	 * 删除担保人
	 * 
	 * @param sponsorVO
	 * @return
	 */
	public void deleteSponsor(SponsorVO sponsorVO);
	
	/**
	 * 删除申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	public void deleteApplyInfo(ApplyVO applyVO);
	
	/**
	 * 新增投保单归属机构信息
	 * 
	 * @param msCoinVO
	 * @return
	 */
	public void addMsCoin(MsCoinVO msCoinVO);
	
	/**
	 * 修改投保单归属机构信息
	 * 
	 * @param msCoinVO
	 * @return
	 */
	public void updateMsCoin(MsCoinVO msCoinVO);
	
	/**
	 *  新增方案信息
	 * 
	 * @param msInsuredPlanVO
	 * @return
	 */
	public void addMsInsuredplan(MsInsuredPlanVO msInsuredPlanVO);
	
	/**
	 *  修改方案信息
	 * 
	 * @param msInsuredPlanVO
	 * @return
	 */
	public void updateMsInsuredplan(MsInsuredPlanVO msInsuredPlanVO);
	
	/**
	 *  新增条款信息
	 * 
	 * @param msSchemeVO
	 * @return
	 */
	public void addMsScheme(MsSchemeVO msSchemeVO);
	
	/**
	 *  修改条款信息
	 * 
	 * @param msSchemeVO
	 * @return
	 */
	public void updateMsScheme(MsSchemeVO msSchemeVO);
	
	/**
	 *  新增特别约定信息
	 * 
	 * @param msEngageVO
	 * @return
	 */
	public void addMsEngage(MsEngageVO msEngageVO);
	
	/**
	 *  修改特别约定信息
	 * 
	 * @param msEngageVO
	 * @return
	 */
	public void updateMsEngage(MsEngageVO msEngageVO);
	
	/**
	 *  新增被保人
	 * 
	 * @param msInsuredVO
	 * @return
	 */
	public void addMsInsured(MsInsuredVO msInsuredVO);
	
	/**
	 *  修改被保人
	 * 
	 * @param msInsuredVO
	 * @return
	 */
	public void updateMsInsured(MsInsuredVO msInsuredVO);
	
	/**
	 *  新增受益人
	 * 
	 * @param msBeneficiaryVO
	 * @return
	 */
	public void addMsBeneficiary(MsBeneficiaryVO msBeneficiaryVO);
	
	/**
	 *  修改受益人
	 * 
	 * @param msBeneficiaryVO
	 * @return
	 */
	public void updateMsBeneficiary(MsBeneficiaryVO msBeneficiaryVO);
	
	/**
	 *  查询投保单归属机构信息
	 * 
	 * @param msCoinVO
	 * @return
	 */
	public MsCoinVO findMsCoin(MsCoinVO msCoinVO);
	
	/**
	 *  查询方案信息
	 * 
	 * @param msInsuredPlanVO
	 * @return
	 */
	public MsInsuredPlanVO findMsInsuredPlan(MsInsuredPlanVO msInsuredPlanVO);
	
	/**
	 *  查询条款信息
	 * 
	 * @param msSchemeVO
	 * @return
	 */
	public List<MsSchemeVO> findMsSchemes(MsSchemeVO msSchemeVO);
	
	/**
	 *  查询约定特殊信息
	 * 
	 * @param msEngageVO
	 * @return
	 */
	public List<MsEngageVO> findMsEngages(MsEngageVO msEngageVO);
	
	/**
	 *  查询被保人信息
	 * 
	 * @param msInsuredVO
	 * @return
	 */
	public MsInsuredVO findMsInsured(MsInsuredVO msInsuredVO);
	
	/**
	 *  查询受益人信息
	 * 
	 * @param msBeneficiaryVO
	 * @return
	 */
	public MsBeneficiaryVO findMsBeneficiary(MsBeneficiaryVO msBeneficiaryVO);
}
