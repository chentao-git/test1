package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti_piic.service.IPIICApplyService;
import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.dao.IBaseCustomerDao;
import com.xxm.it.business.dao.IMsLoanInfoDao;
import com.xxm.it.business.dao.IMsMortgageInfoDao;
import com.xxm.it.business.dao.IMsSignInfoDao;
import com.xxm.it.business.service.IApplyService;
import com.xxm.it.business.service.IBaseCustomerService;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.business.vo.CarInsuranceVO;
import com.xxm.it.business.vo.GuaranteeInsuranceVO;
import com.xxm.it.business.vo.MsBeneficiaryVO;
import com.xxm.it.business.vo.MsCoinVO;
import com.xxm.it.business.vo.MsEngageVO;
import com.xxm.it.business.vo.MsInsuredPlanVO;
import com.xxm.it.business.vo.MsInsuredVO;
import com.xxm.it.business.vo.MsLoanInfoVO;
import com.xxm.it.business.vo.MsMortgageInfoVO;
import com.xxm.it.business.vo.MsSchemeVO;
import com.xxm.it.business.vo.MsSignInfoVO;
import com.xxm.it.business.vo.QuotedPriceVO;
import com.xxm.it.business.vo.RepaymentPlanVO;
import com.xxm.it.business.vo.SponsorVO;
import com.xxm.it.business.vo.SupplementBaseVO;
import com.xxm.it.piic.dao.ITransferPICCDao;
import com.xxm.it.piic.service.ITransferPICCService;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.LoanVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.MortgageinfoVO;
import com.xxm.it.piic.vo.PrejudicationVO;
import com.xxm.it.piic.vo.RejudicationReturnVO;
import com.xxm.it.piic.vo.SignInfoVO;
import com.xxm.it.piic.vo.SignReturnInfoVO;
import com.xxm.it.system.dao.IAttachmentDao;
import com.xxm.it.system.dao.ILeaseInfoDao;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.service.LeaseInfoService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.util.XxmUtils;
import com.xxm.it.system.vo.AttachmentTextVO;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class ApplyServiceImpl implements IApplyService {

	private static Logger logger = Logger.getLogger(ApplyServiceImpl.class);

	@Resource
	private IApplyDao applyDao;

	@Resource
	private IUserService userService;

	@Resource
	private IBaseCustomerService BaseCustomerService;// 客户service

	@Resource
	private IBaseCustomerDao baseCustomerDao;// 客户dao

	@Resource
	private LeaseInfoService leaseInfoService;// 租赁service

	@Resource
	private ILeaseInfoDao leaseInfoDao;// 租赁信息dao

	@Resource
	private IAttachmentDao attachmentDao;// 附件dao

	@Resource
	private IPIICApplyService piicApplyService;//流程服务处理类
	
	@Resource
	private IMsLoanInfoDao msLoanInfoDao;// 请求放款dao
	
	@Resource
	private IMsMortgageInfoDao msMortgageInfoDao;// 车辆抵押登记信息传输dao
	
	@Resource
	private IMsSignInfoDao msSignInfoDao;// 签约状态查询dao
	
	@Resource
	private ITransferPICCService transferPICCServiceImpl;// 接口表service

	@Resource
	private ITransferPICCDao transferPICCDao;// 接口表dao

	/**
	 * 当前代办人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApplyInfoList(ApplyVO applyVO) {
		JSONObject jsonObject = piicApplyService.findApply(applyVO);
		return jsonObject;
	}
	
	/**
	 * 当前所有人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findAllApplyInfoList(ApplyVO applyVO) {
		JSONObject jsonObject = piicApplyService.findAllApply(applyVO);
		return jsonObject;
	}
	
	/**
	 * 当前代办人补充资料信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findMakeUpApplyInfoList(ApplyVO applyVO) {
		String taskDefinitionKey = "makeUpInfo";//补充资料节点Id
		JSONObject jsonObject = piicApplyService.findApply(taskDefinitionKey,applyVO);
		return jsonObject;
	}

	/**
	 * 添加申请信息
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addApply(ApplyVO applyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				// 客户信息添加
				BaseCustomerVO baseCustomerVO = applyVO.getCustomerVO();
				if (baseCustomerVO != null) {
					baseCustomerVO = BaseCustomerService.addBaseCustomer(baseCustomerVO);
				}
				applyVO.setCreateBy(userVO.getUserId());
				applyVO.setLastUpdateBy(userVO.getUserId());
				applyVO.setApplyCustomerId(baseCustomerVO.getCustomerId());
				applyVO.setLoanContractNo("LNO" + XxmUtils.getDateString(new Date()));
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_INITIAL);
				applyDao.addApply(applyVO);
				PrejudicationVO prejudicationVO= new PrejudicationVO();
				prejudicationVO.setApplyInfoId(applyVO.getApplyInfoId());
				RejudicationReturnVO bankpretrial = transferPICCServiceImpl.bankpretrial(prejudicationVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加申请数据成功！");
				logger.info("添加申请信息成功！");
				if ("00".equals(bankpretrial.getResultCode())) {
					// 创建并启动流程信息
					piicApplyService.createApply(applyVO);
				}
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加申请信息失败！");
			logger.info("添加用户失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 资料补充
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> dataSupplement(ApplyVO applyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				// 客户信息修改
				BaseCustomerVO baseCustomerVO = applyVO.getCustomerVO();
				if (baseCustomerVO != null) {
					BaseCustomerService.updateBaseCustomer(baseCustomerVO);
				}
				// 补充基本信息
				SupplementBaseVO supplementBaseVO = applyVO.getSupplementBaseVO();
				if (supplementBaseVO != null && supplementBaseVO.getSupplementBaseId() != null
						&& !"".equals(supplementBaseVO.getSupplementBaseId())) {
					applyDao.updateSupplementBase(supplementBaseVO);
				} else if (supplementBaseVO != null) {
					supplementBaseVO.setApplyInfoId(applyVO.getApplyInfoId());
					applyDao.addSupplementBase(supplementBaseVO);
				}
				// 报价信息
				QuotedPriceVO quotedPriceVO = applyVO.getQuotedPriceVO();
				if (quotedPriceVO != null && quotedPriceVO.getQuotedPriceId() != null
						&& !"".equals(quotedPriceVO.getQuotedPriceId())) {
					applyDao.updateQuotedPrice(quotedPriceVO);
				} else if (quotedPriceVO != null) {
					quotedPriceVO.setApplyInfoId(applyVO.getApplyInfoId());
					applyDao.addQuotedPrice(quotedPriceVO);
				}
				// 还款计划
				RepaymentPlanVO repaymentPlanVO = applyVO.getRepaymentPlanVO();
				if (repaymentPlanVO != null) {
					repaymentPlanVO.setQuotedPriceId(quotedPriceVO.getQuotedPriceId());
					applyDao.addRepaymentPlan(repaymentPlanVO);
				}
				// 租赁物业务信息
				BaseLeaseInfoVO leaseInfoVO = applyVO.getLeaseInfoVO();
				if (leaseInfoVO != null && leaseInfoVO.getLeaseInfoId() != null
						&& !"".equals(leaseInfoVO.getLeaseInfoId())) {
					leaseInfoDao.updateBusinessLeaseInfo(leaseInfoVO);
				} else if (leaseInfoVO != null) {
					leaseInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
					leaseInfoDao.addBusinessLeaseInfo(leaseInfoVO);
				}
				// 车产保险
				CarInsuranceVO carInsuranceVO = applyVO.getCarInsuranceVO();
				if (carInsuranceVO != null && carInsuranceVO.getCarInsuranceId() != null
						&& !"".equals(carInsuranceVO.getCarInsuranceId())) {
					applyDao.updateCarInsurance(carInsuranceVO);
				} else if (carInsuranceVO != null) {
					carInsuranceVO.setApplyInfoId(applyVO.getApplyInfoId());
					applyDao.addCarInsurance(carInsuranceVO);
				}
				// 保证保险
				GuaranteeInsuranceVO guaranteeInsuranceVO = applyVO.getGuaranteeInsuranceVO();
				if (guaranteeInsuranceVO != null && guaranteeInsuranceVO.getGuaranteeInsuranceId() != null
						&& !"".equals(guaranteeInsuranceVO.getGuaranteeInsuranceId())) {
					applyDao.updateGuaranteeInsurance(guaranteeInsuranceVO);
				} else if (guaranteeInsuranceVO != null) {
					guaranteeInsuranceVO.setApplyInfoId(applyVO.getApplyInfoId());
					applyDao.addGuaranteeInsurance(guaranteeInsuranceVO);
				}
				// 删除担保人
				SponsorVO newSponsorVO = new SponsorVO();
				newSponsorVO.setApplyInfoId(applyVO.getApplyInfoId());
				applyDao.deleteSponsor(newSponsorVO);
				// 新增担保人
				List<SponsorVO> sponsorList = applyVO.getSponsorList();
				if (sponsorList != null && sponsorList.size() > 0) {
					for (SponsorVO sponsorVO : sponsorList) {
						sponsorVO.setApplyInfoId(applyVO.getApplyInfoId());
						applyDao.addSponsor(sponsorVO);
					}
				}
				// 附件
				List<AttachmentTextVO> attachmentTextList = applyVO.getAttachmentTextList();
				if (attachmentTextList != null && attachmentTextList.size() > 0) {
					for (AttachmentTextVO attachmentTextVO : attachmentTextList) {
						attachmentTextVO.setAttachmentItemID(applyVO.getApplyInfoId());
						attachmentTextVO.setAttachmentStatus(BaseDataConstant.STATUS_EFFECTIVE);//有效
						attachmentDao.updateAttachment(attachmentTextVO);
					}
				}

				// 投保单归属机构信息
				MsCoinVO msCoinVO = applyVO.getMsCoinVO();
				if (msCoinVO != null && msCoinVO.getCoinId() != null && !"".equals(msCoinVO.getCoinId())) {
					applyDao.updateMsCoin(msCoinVO);
				} else if (msCoinVO != null) {
					msCoinVO.setApplyInfoId(applyVO.getApplyInfoId());
					applyDao.addMsCoin(msCoinVO);
				}

				// 方案信息
				MsInsuredPlanVO msInsuredPlanVO = applyVO.getMsInsuredPlanVO();
				if (msInsuredPlanVO != null) {
					if (msInsuredPlanVO.getInsuredPlanId() != null && !"".equals(msInsuredPlanVO.getInsuredPlanId())) {
						applyDao.updateMsInsuredplan(msInsuredPlanVO);
					} else {
						msInsuredPlanVO.setApplyInfoId(applyVO.getApplyInfoId());
						applyDao.addMsInsuredplan(msInsuredPlanVO);
					}
					List<MsSchemeVO> msSchemeList = msInsuredPlanVO.getMsSchemeList();
					if (msSchemeList != null && msSchemeList.size() > 0) {
						MsSchemeVO msSchemeVO = msSchemeList.get(0);
						if (msSchemeVO.getSchemeId() != null && !"".equals(msSchemeVO.getSchemeId())) {
							applyDao.updateMsScheme(msSchemeVO);
						} else {
							msSchemeVO.setInsuredPlanId(msInsuredPlanVO.getInsuredPlanId());
							applyDao.addMsScheme(msSchemeVO);
						}
					}
					List<MsEngageVO> engageList = msInsuredPlanVO.getMsEngageList();
					if (engageList != null && engageList.size() > 0) {
						MsEngageVO msEngageVO = engageList.get(0);
						if (msEngageVO.getEngageId() != null && !"".equals(msEngageVO.getEngageId())) {
							applyDao.updateMsEngage(msEngageVO);
						} else {
							msEngageVO.setInsuredPlanId(msInsuredPlanVO.getInsuredPlanId());
							applyDao.addMsEngage(msEngageVO);
						}
					}
				}

				// 被保人
				MsInsuredVO msInsuredVO = applyVO.getMsInsuredVO();
				if (msInsuredVO != null) {
					if (msInsuredVO.getInsuredId() != null && !"".equals(msInsuredVO.getInsuredId())) {
						applyDao.updateMsInsured(msInsuredVO);
					} else {
						msInsuredVO.setApplyInfoId(applyVO.getApplyInfoId());
						applyDao.addMsInsured(msInsuredVO);
					}
					MsBeneficiaryVO msBeneficiaryVO = msInsuredVO.getMsBeneficiaryVO();
					if (msBeneficiaryVO != null && msBeneficiaryVO.getBeneficiaryId() != null
							&& !"".equals(msBeneficiaryVO.getBeneficiaryId())) {
						applyDao.updateMsBeneficiary(msBeneficiaryVO);
					} else if (msBeneficiaryVO != null) {
						msBeneficiaryVO.setInsuredId(msInsuredVO.getInsuredId());
						applyDao.addMsBeneficiary(msBeneficiaryVO);
					}
				}
				
				// 车辆抵押登记信息传输
				MsMortgageInfoVO msMortgageInfoVO = applyVO.getMsMortgageInfoVO();
				if (msMortgageInfoVO.getMortgageInfoId() != null && !"".equals(msMortgageInfoVO.getMortgageInfoId())) {
					msMortgageInfoDao.updateMortgageInfo(msMortgageInfoVO);
				} else {
					msMortgageInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
					msMortgageInfoDao.addMortgageInfo(msMortgageInfoVO);
				}
				
				// 请求放款
				MsLoanInfoVO msLoanInfoVO = applyVO.getMsLoanInfoVO();
				if (msLoanInfoVO.getLoanInfoId() != null && !"".equals(msLoanInfoVO.getLoanInfoId())) {
					msLoanInfoDao.updateLoanInfo(msLoanInfoVO);
				} else {
					msLoanInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
					msLoanInfoDao.addLoanInfo(msLoanInfoVO);
				}
				applyVO.setLastUpdateBy(userVO.getUserId());
				if ("1".equals(applyVO.getOperationType())) {// 保存
					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SUPPLEMENT_SAVE);
					applyDao.updateApplyInfo(applyVO);
					resultMap.put("result", Boolean.TRUE);
					resultMap.put("msg", "保存申请数据成功！");
					logger.info("保存申请信息成功！");
				} else {// 提交
					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SUPPLEMENT_SUBMIT);
					applyDao.updateApplyInfo(applyVO);
					//任务节点设置
					piicApplyService.dataSupplement(applyVO);
					resultMap.put("result", Boolean.TRUE);
					resultMap.put("msg", "提交申请数据成功！");
					logger.info("提交申请信息成功！");
				}
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加申请信息失败！");
			logger.info("添加请信息失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询该项目的全部资料
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public ApplyVO findProjectData(ApplyVO applyVO) {
		ApplyVO newApplyVO = new ApplyVO();
//		if (applyVO.getApplyInfoId() != null && !"".equals(applyVO.getApplyInfoId())) {
//			newApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
			newApplyVO = applyDao.findApplyInfo(applyVO);
			if(newApplyVO!=null){
				// 客户
				BaseCustomerVO customerVO = new BaseCustomerVO();
				customerVO.setCustomerId(newApplyVO.getApplyCustomerId());
				customerVO = baseCustomerDao.findCustomer(customerVO);
				newApplyVO.setCustomerVO(customerVO);
				// 查询补充基本信息
				SupplementBaseVO supplementBaseVO = new SupplementBaseVO();
				supplementBaseVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				supplementBaseVO = applyDao.findSupplementBase(supplementBaseVO);
				newApplyVO.setSupplementBaseVO(supplementBaseVO);
				// 报价信息
				QuotedPriceVO quotedPriceVO = new QuotedPriceVO();
				quotedPriceVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				quotedPriceVO = applyDao.findQuotedPrice(quotedPriceVO);
				if (quotedPriceVO != null) {
					// 还款计划
					RepaymentPlanVO repaymentPlanVO = new RepaymentPlanVO();
					repaymentPlanVO.setQuotedPriceId(quotedPriceVO.getQuotedPriceId());
					List<RepaymentPlanVO> repaymentPlanList = applyDao.findRepaymentPlans(repaymentPlanVO);
					quotedPriceVO.setRepaymentPlanList(repaymentPlanList);
					newApplyVO.setQuotedPriceVO(quotedPriceVO);
				}
				// 租凭物信息
				BaseLeaseInfoVO leaseInfoVO = new BaseLeaseInfoVO();
				leaseInfoVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				leaseInfoVO = leaseInfoDao.findBusinessLeaseInfo(leaseInfoVO);
				newApplyVO.setLeaseInfoVO(leaseInfoVO);
				// 车辆保险对象
				CarInsuranceVO carInsuranceVO = new CarInsuranceVO();
				carInsuranceVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				carInsuranceVO = applyDao.findCarInsurance(carInsuranceVO);
				newApplyVO.setCarInsuranceVO(carInsuranceVO);
				// 保证保险对象
				GuaranteeInsuranceVO guaranteeInsuranceVO = new GuaranteeInsuranceVO();
				guaranteeInsuranceVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				guaranteeInsuranceVO = applyDao.findGuaranteeInsurance(guaranteeInsuranceVO);
				newApplyVO.setGuaranteeInsuranceVO(guaranteeInsuranceVO);
				// 担保人
				SponsorVO sponsorVO = new SponsorVO();
				sponsorVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				List<SponsorVO> sponsorList = applyDao.findSponsors(sponsorVO);
				newApplyVO.setSponsorList(sponsorList);
				// 附件
				AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
				attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
				attachmentTextVO.setAttachmentItemID(newApplyVO.getApplyInfoId());
				attachmentTextVO.setAttachmentStatus(BaseDataConstant.STATUS_EFFECTIVE);//有效
				List<AttachmentTextVO> attachmentTextList = attachmentDao.findAttachments(attachmentTextVO);
				newApplyVO.setAttachmentTextList(attachmentTextList);
	
				// 投保单归属机构信息
				MsCoinVO msCoinVO = new MsCoinVO();
				msCoinVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				msCoinVO = applyDao.findMsCoin(msCoinVO);
				newApplyVO.setMsCoinVO(msCoinVO);
	
				// 查询方案信息
				MsInsuredPlanVO msInsuredPlanVO = new MsInsuredPlanVO();
				msInsuredPlanVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				msInsuredPlanVO = applyDao.findMsInsuredPlan(msInsuredPlanVO);
				if (msInsuredPlanVO != null) {
					// 查询条款信息
					MsSchemeVO msSchemeVO = new MsSchemeVO();
					msSchemeVO.setInsuredPlanId(msInsuredPlanVO.getInsuredPlanId());
					List<MsSchemeVO> msSchemeList = applyDao.findMsSchemes(msSchemeVO);
					msInsuredPlanVO.setMsSchemeList(msSchemeList);
					// 查询特殊约定信息
					MsEngageVO msEngageVO = new MsEngageVO();
					msEngageVO.setInsuredPlanId(msInsuredPlanVO.getInsuredPlanId());
					List<MsEngageVO> engageList = applyDao.findMsEngages(msEngageVO);
					msInsuredPlanVO.setMsEngageList(engageList);
				}
				newApplyVO.setMsInsuredPlanVO(msInsuredPlanVO);
	
				// 查询被保人信息
				MsInsuredVO msInsuredVO = new MsInsuredVO();
				msInsuredVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				msInsuredVO = applyDao.findMsInsured(msInsuredVO);
				if (msInsuredVO != null) {
					// 查询受益人信息
					MsBeneficiaryVO msBeneficiaryVO = new MsBeneficiaryVO();
					msBeneficiaryVO.setInsuredId(msInsuredVO.getInsuredId());
					msBeneficiaryVO = applyDao.findMsBeneficiary(msBeneficiaryVO);
					msInsuredVO.setMsBeneficiaryVO(msBeneficiaryVO);
				}
				newApplyVO.setMsInsuredVO(msInsuredVO);
				
				// 放款信息
				MsLoanInfoVO msLoanInfoVO = new MsLoanInfoVO();
				msLoanInfoVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				msLoanInfoVO = msLoanInfoDao.findLoanInfo(msLoanInfoVO);
				if(msLoanInfoVO!=null){//响应信息
					LoanReturnInfoVO loanReturnInfoVO=new LoanReturnInfoVO();
					loanReturnInfoVO.setRequestId(msLoanInfoVO.getLoanInfoId());
					List<LoanReturnInfoVO> loanReturnInfoList=transferPICCDao.findLoanReturnInfo(loanReturnInfoVO);
					if(loanReturnInfoList!=null&&loanReturnInfoList.size()>0){
						//放款通知信息
						LoanInfoInformVO loanInfoInformVO=null;
						for(LoanReturnInfoVO returnInfoVO:loanReturnInfoList){
							if(returnInfoVO.getProposalno()!=null&&!"".equals(returnInfoVO.getProposalno())){
								loanInfoInformVO=new LoanInfoInformVO();
								loanInfoInformVO.setProposalNo(returnInfoVO.getProposalno());
								loanInfoInformVO=transferPICCDao.findLoanNoticeInfo(loanInfoInformVO);
								returnInfoVO.setLoanInfoInformVO(loanInfoInformVO);
							}
						}
						msLoanInfoVO.setLoanReturnInfoList(loanReturnInfoList);
					}
					newApplyVO.setMsLoanInfoVO(msLoanInfoVO);
				}
				
				// 抵押登记信息
				MsMortgageInfoVO msMortgageInfoVO = new MsMortgageInfoVO();
				msMortgageInfoVO.setApplyInfoId(newApplyVO.getApplyInfoId());
				msMortgageInfoVO = msMortgageInfoDao.findMortgageInfo(msMortgageInfoVO);
				if(msMortgageInfoVO!=null){//响应信息
					MortgageReturnInfoVO mortgageReturnInfoVO = new MortgageReturnInfoVO();
					mortgageReturnInfoVO.setRequestId(msMortgageInfoVO.getMortgageInfoId());
					List<MortgageReturnInfoVO> mortgageReturnInfoList = transferPICCDao
							.findMortgageReturninfo(mortgageReturnInfoVO);
					msMortgageInfoVO.setMortgageReturnInfoList(mortgageReturnInfoList);
					newApplyVO.setMsMortgageInfoVO(msMortgageInfoVO);
				}
			}
//		}
		return newApplyVO;
	}

	/**
	 * 删除申请信息
	 * 
	 * @param userVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> deleteApplyInfo(ApplyVO applyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_DISCARD);
				applyVO.setLastUpdateBy(userVO.getUserId());
				applyDao.deleteApplyInfo(applyVO);
				resultMap.put("result", Boolean.TRUE);
				logger.info("删除申请信息成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除用户失败！");
			logger.info("删除用户失败！" + e.getMessage());
		}
		return resultMap;
	}
	/**
	 * 查询申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	public ApplyVO findApplyInfo(ApplyVO applyVO) {
		ApplyVO newApplyVO = new ApplyVO();
		if(applyVO != null){
			newApplyVO = applyDao.findApplyInfo(applyVO);
			return newApplyVO;	
		}else{
			return null;
		}	
	}
	/**
	 * 客户详情资料
	 */
	@Override
	public ApplyVO findCustomerDate(ApplyVO applyVO) {
		ApplyVO newApplyVO = new ApplyVO();
		//根据基础客户id去申请信息
		newApplyVO = applyDao.findApplyInfo(applyVO);
		if(newApplyVO != null){
			// 客户
			BaseCustomerVO customerVO = new BaseCustomerVO();
			customerVO.setCustomerId(newApplyVO.getApplyCustomerId());
			customerVO = baseCustomerDao.findCustomer(customerVO);
			newApplyVO.setCustomerVO(customerVO);
			
			// 附件
			AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
			attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
			attachmentTextVO.setAttachmentItemID(newApplyVO.getApplyInfoId());
			attachmentTextVO.setAttachmentStatus(BaseDataConstant.STATUS_EFFECTIVE);//有效
			List<AttachmentTextVO> attachmentTextList = attachmentDao.findAttachments(attachmentTextVO);
			newApplyVO.setAttachmentTextList(attachmentTextList);
			
			// 担保人
			SponsorVO sponsorVO = new SponsorVO();
			sponsorVO.setApplyInfoId(newApplyVO.getApplyInfoId());
			List<SponsorVO> sponsorList = applyDao.findSponsors(sponsorVO);
			newApplyVO.setSponsorList(sponsorList);
			
			// 租凭物信息
			BaseLeaseInfoVO leaseInfoVO = new BaseLeaseInfoVO();
			leaseInfoVO.setApplyInfoId(newApplyVO.getApplyInfoId());
			leaseInfoVO = leaseInfoDao.findBusinessLeaseInfo(leaseInfoVO);
			newApplyVO.setLeaseInfoVO(leaseInfoVO);
			return newApplyVO;
		}else{
			// 客户
			BaseCustomerVO customerVO = new BaseCustomerVO();
			customerVO.setBaseCustomerId(applyVO.getCustomerVO().getBaseCustomerId());
			customerVO = baseCustomerDao.findBaseCustomer(customerVO);
			ApplyVO newApply = new ApplyVO();
			newApply.setCustomerVO(customerVO);
			return newApply;
		}
		
	}
	/**
	 * 贷款信息列表
	 */
	@Override
	public JSONObject findApplyList(ApplyVO applyVO) {
		JSONObject jsonObject = piicApplyService.findApplyFinished(applyVO);
		return jsonObject;
	}
	
	/**
	 * 添加签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
//	public Map<String, Object> addSignInfo(MsSignInfoVO msSignInfoVO) {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		try {
//			int k=-1;
//			if(msSignInfoVO.getApplyInfoId()!= null && !"".equals(msSignInfoVO.getApplyInfoId())){
//				ApplyVO applyVO =new ApplyVO();
//				applyVO.setApplyInfoId(msSignInfoVO.getApplyInfoId());
//				//查询申请信息（拿预审单号）
//				applyVO = applyDao.findApplyInfo(applyVO);
//				if(applyVO!=null){
//					RejudicationReturnVO rejudicationReturnVO=applyVO.getRejudicationReturnVO();
//					if(rejudicationReturnVO!=null&&rejudicationReturnVO.getPrejudicNo()!=null
//							&&!"".equals(rejudicationReturnVO.getPrejudicNo())){
//						Auditinfo auditInfo=new Auditinfo();
//						auditInfo.setPrejudicNo(rejudicationReturnVO.getPrejudicNo());
//						//查询审核结果通知请求（拿投保单号、申请书编号）
//						auditInfo=transferPICCDao.findAuditNoticeInfo(auditInfo);
//						if(auditInfo!=null){
//							msSignInfoVO.setProposalNo(auditInfo.getProposalNo());
//							msSignInfoVO.setApplyNo(auditInfo.getApplyNo());
//							msSignInfoDao.addSignInfo(msSignInfoVO);
//							// 接口调用
//							SignInfoVO signInfoVO = new SignInfoVO();
//							signInfoVO.setSignInfoId(msSignInfoVO.getSignInfoId());
//							transferPICCServiceImpl.contractStatu(signInfoVO);
//							
//							k=1;//成功标志
//						}
//					}
//				}
//			}
//			if(k==1){
//				resultMap.put("result", Boolean.TRUE);
//				resultMap.put("msg", "签约状态查询信息成功！");
//				logger.info("签约状态查询信息成功");
//			}else{
//				resultMap.put("result", Boolean.FALSE);
//				resultMap.put("msg", "签约状态查询信息失败！");
//			}
//		} catch (Exception e) {
//			resultMap.put("result", Boolean.FALSE);
//			resultMap.put("msg", "签约状态查询信息失败！");
//			logger.info("签约状态查询信息失败！" + e.getMessage());
//		}
//		return resultMap;
//	}
	
	/**
	 * 根据申请id获取签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public List<MsSignInfoVO> findSignInfos(MsSignInfoVO msSignInfoVO) {
		List<MsSignInfoVO> msSignInfoList=null;
		if(msSignInfoVO.getApplyInfoId()!= null && !"".equals(msSignInfoVO.getApplyInfoId())){
			msSignInfoList= msSignInfoDao.findSignInfos(msSignInfoVO);
		}
		return msSignInfoList;
	}
	
	/**
	 * 新增请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> requestLoanInfo(MsLoanInfoVO msLoanInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 请求放款
			if (msLoanInfoVO.getLoanInfoId() != null && !"".equals(msLoanInfoVO.getLoanInfoId())) {
				// 接口调用
				LoanVO loanVO = new LoanVO();
				loanVO.setLoanInfoId(msLoanInfoVO.getLoanInfoId());
				//调用放款申请接口
				LoanReturnInfoVO transferLoan = transferPICCServiceImpl.transferLoan(loanVO);
				//执行请求放款流程
				piicApplyService.loanApply(msLoanInfoVO.getApplyInfoId(), transferLoan);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "提交请求放款成功！");
				logger.info("提交请求放款成功！");
			}else{
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "提交请求放款失败！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "提交请求放款失败！");
			logger.info("提交请求放款失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 获取请求放款响应信息
	 * 
	 * @param loanReturnInfoVO
	 * @return
	 */
	public List<LoanReturnInfoVO> findLoanReturnInfos(LoanReturnInfoVO loanReturnInfoVO) {
		List<LoanReturnInfoVO> loanReturnInfoList=null;
		if(loanReturnInfoVO.getRequestId()!=null&&!"".equals(loanReturnInfoVO.getRequestId())){
			loanReturnInfoList=transferPICCDao.findLoanReturnInfo(loanReturnInfoVO);
			if(loanReturnInfoList!=null&&loanReturnInfoList.size()>0){
				//放款通知信息
				LoanInfoInformVO loanInfoInformVO=null;
				for(LoanReturnInfoVO returnInfoVO:loanReturnInfoList){
					if(returnInfoVO.getProposalno()!=null&&!"".equals(returnInfoVO.getProposalno())){
						loanInfoInformVO=new LoanInfoInformVO();
						loanInfoInformVO.setProposalNo(returnInfoVO.getProposalno());
						loanInfoInformVO=transferPICCDao.findLoanNoticeInfo(loanInfoInformVO);
						returnInfoVO.setLoanInfoInformVO(loanInfoInformVO);
					}
				}
			}
		}
		return loanReturnInfoList;
	}
	
	/**
	 * 请求车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> requestMortgageInfo(MsMortgageInfoVO msMortgageInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 请求放款
			if (msMortgageInfoVO.getMortgageInfoId() != null && !"".equals(msMortgageInfoVO.getMortgageInfoId())) {
				// 接口调用
				MortgageinfoVO mortgageinfoVO = new MortgageinfoVO();
				mortgageinfoVO.setMortgageInfoId(msMortgageInfoVO.getMortgageInfoId());
				transferPICCServiceImpl.vehicleMortgageInfo(mortgageinfoVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "车辆抵押登记信息传输成功！");
				logger.info("车辆抵押登记信息传输成功！");
			}else{
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "车辆抵押登记信息传输失败！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "车辆抵押登记信息传输失败！");
			logger.info("车辆抵押登记信息传输失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 查询车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return msMortgageInfoVO 返回结果消息
	 */
	public List<MortgageReturnInfoVO> findMortgageReturnInfos(MortgageReturnInfoVO mortgageReturnInfoVO) {
		List<MortgageReturnInfoVO> mortgageReturnInfoList =null;
		if (mortgageReturnInfoVO.getRequestId() != null && !"".equals(mortgageReturnInfoVO.getRequestId())) {
			// 车辆抵押响应信息（list）
			mortgageReturnInfoList = transferPICCDao.findMortgageReturninfo(mortgageReturnInfoVO);
		}
		return mortgageReturnInfoList;
	}
	
	/**
	 * 查询放款通知详细
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public LoanInfoInformVO findLoanNoticeInfo(LoanInfoInformVO loanInfoInformVO) {
		return transferPICCDao.findLoanNoticeInfo(loanInfoInformVO);
	}
	
	
	/**
	 * 请求签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> requestSignInfos() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SignInfoVO signInfoVO=new SignInfoVO();
			signInfoVO.setApplyInfoStatus("-1");
			List<SignInfoVO> signInfoList=transferPICCDao.findSignInfos(signInfoVO);
			if(signInfoList!=null&signInfoList.size()>0){
				for(int i=0;i<signInfoList.size();i++){
					// 接口调用
//					signInfoVO=new SignInfoVO();
//					signInfoVO.setSignInfoId(signInfoVO.getSignInfoId());
					transferPICCServiceImpl.contractStatu(signInfoList.get(i));
				}
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "请求签约状态查询信息失败！");
			logger.info("请求签约状态查询信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 添加签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addSignInfo(Auditinfo auditinfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(auditinfo!=null&&"03".equals(auditinfo.getAuditTpye())
					&&"00".equals(auditinfo.getUnderwriteResult())){//终审并通过
				ApplyVO applyVO =new ApplyVO();
				applyVO.setProposalNo(auditinfo.getProposalNo());
				//查询申请信息
				applyVO = applyDao.findApplyInfo(applyVO);
				if(applyVO!=null){
					MsSignInfoVO msSignInfoVO=new MsSignInfoVO();
					msSignInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
					msSignInfoVO.setProposalNo(auditinfo.getProposalNo());
					msSignInfoVO.setApplyNo(auditinfo.getApplyNo());
					msSignInfoDao.addSignInfo(msSignInfoVO);
					// 接口调用
					SignInfoVO signInfoVO = new SignInfoVO();
					signInfoVO.setSignInfoId(msSignInfoVO.getSignInfoId());
					transferPICCServiceImpl.contractStatu(signInfoVO);
				}
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "签约状态查询信息失败！");
			logger.info("签约状态查询信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 签约状态查询（可放款状态）修改申请状态
	 * 
	 * @param signReturnInfoVO
	 *            参数
	 * 
	 */
	public void signInfoLoan(SignReturnInfoVO signReturnInfoVO) {
		//节点值为”00”并可放款状态
		if(signReturnInfoVO!=null&&"00".equals(signReturnInfoVO.getResultCode())
				&&"COMP".equals(signReturnInfoVO.getApplState())){
			MsSignInfoVO msSignInfoVO=new MsSignInfoVO();
			msSignInfoVO.setSignInfoId(signReturnInfoVO.getRequestId());
			msSignInfoVO=msSignInfoDao.findSignInfo(msSignInfoVO);
			//修改申请表状态
			if(msSignInfoVO!=null){
				ApplyVO applyVO=new ApplyVO();
				applyVO.setApplyInfoId(msSignInfoVO.getApplyInfoId());
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_SUCCEED);
				transferPICCDao.uadateApplyInfo(applyVO);
			}
		}
	}
	
	/**
	 * 放款通知（放款成功）修改申请状态
	 * 
	 * @param loanInfoInformVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public void loanInfoSuccess(LoanInfoInformVO loanInfoInformVO) {
		//01:放款成功
		if(loanInfoInformVO!=null&&"01".equals(loanInfoInformVO.getLoanResult())
				&&loanInfoInformVO.getProposalNo()!=null&&!"".equals(loanInfoInformVO.getProposalNo())){
			//根据投保单号修改申请状态
			updateStatusByNo(loanInfoInformVO.getProposalNo(),BaseDataConstant.APPLY_STATUS_LOAN_SUCCEED);
		}
	}
	
	/**
	 * 车辆抵押登记（成功）修改申请状态
	 * 
	 * @param mortgageReturnInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public void mortgageSuccess(MortgageReturnInfoVO mortgageReturnInfoVO) {
		//00:响应成功
		if(mortgageReturnInfoVO!=null&&"00".equals(mortgageReturnInfoVO.getResultCode())){
			//根据投保单号修改申请状态
			updateStatusByNo(mortgageReturnInfoVO.getProposalno(),BaseDataConstant.APPLY_STATUS_MORTGAGE_SUCCEED);
		}
	}
	
	/**
	 * 根据投保单号修改申请状态
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public void updateStatusByNo(String proposalNo,String status) {
		//01:放款成功
		if(proposalNo!=null&&!"".equals(proposalNo)){
			//查询申请信息
			ApplyVO applyVO =new ApplyVO();
			applyVO.setProposalNo(proposalNo);
			applyVO = applyDao.findApplyInfo(applyVO);
			//修改申请表状态
			if(applyVO!=null){
				applyVO.setApplyInfoStatus(status);
				transferPICCDao.uadateApplyInfo(applyVO);
			}
		}
	}
	
	/**
	 * 查询申请单的审核结果通知日志
	 * 
	 * @param auditInfo
	 *            参数
	 * @return List<Auditinfo>
	 */
	public List<Auditinfo> findAuditInfos(Auditinfo auditInfo) {
		return transferPICCDao.findAuditNoticeInfos(auditInfo);
	}
	
	/**
	 * 手动点击添加签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> manualSignReturninfo(SignInfoVO signInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean sign = false;
		//签约接口返回对象
		SignReturnInfoVO contractStatu = null;
		try {
			if(signInfoVO.getApplyInfoId()!= null && !"".equals(signInfoVO.getApplyInfoId())){
				signInfoVO=transferPICCDao.findSignInfo(signInfoVO);
				if(signInfoVO!=null){
					//调用签约查询接口
					contractStatu = transferPICCServiceImpl.contractStatu(signInfoVO);
					//调用签约流程
					piicApplyService.signInfo(signInfoVO.getApplyInfoId(),contractStatu);
					sign = true;
				}
			}
			if(sign){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "签约状态查询信息成功！");
				logger.info("签约状态查询信息成功");
			}else{
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "签约状态查询信息失败！");
				logger.info("签约状态查询信息失败");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "签约状态查询信息失败！");
			logger.info("签约状态查询信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	
	
	/**
	 * 补充影像（提交）
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> loanUpload(ApplyVO applyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 附件
			List<AttachmentTextVO> attachmentTextList = applyVO.getAttachmentTextList();
			if (attachmentTextList != null && attachmentTextList.size() > 0) {
				for (AttachmentTextVO attachmentTextVO : attachmentTextList) {
					attachmentTextVO.setAttachmentItemID(applyVO.getApplyInfoId());
					attachmentTextVO.setAttachmentStatus(BaseDataConstant.STATUS_EFFECTIVE);//有效
					attachmentDao.updateAttachment(attachmentTextVO);
				}
			}
			if("loanUpload".equals(applyVO.getOperationType())){//放款影像补充(跳过审核)
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_PERMIT);
				//放款影像流程步骤
				piicApplyService.loanMakeUp(applyVO);
			}else if("loanUpload_edit".equals(applyVO.getOperationType())){//放款影像补充(编辑过，需要审核)
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_UPLOAD);
				//放款影像流程步骤
				piicApplyService.loanMakeUp(applyVO);
			}else if("mortgageUpload".equals(applyVO.getOperationType())){//抵押影像补充(跳过审核)
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_MORTGAGE_PERMIT);
				//抵押影像流程步骤
				piicApplyService.mortgageMakeUp(applyVO);
			}else{//抵押影像补充(编辑过，需要审核)
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_MORTGAGE_UPLOAD);
				//抵押影像流程步骤
				piicApplyService.mortgageMakeUp(applyVO);
			}
			applyDao.updateApplyInfo(applyVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "提交成功！");
			logger.info("提交成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "提交失败！");
			logger.info("提交失败！" + e.getMessage());
		}
		return resultMap;
	}
}
