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

import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.dao.IBaseCustomerDao;
import com.xxm.it.business.dao.IMsLoanInfoDao;
import com.xxm.it.business.dao.IMsMortgageInfoDao;
import com.xxm.it.business.dao.IMsSignInfoDao;
import com.xxm.it.business.service.IBaseCustomerService;
import com.xxm.it.business.service.IEnteringService;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.business.vo.MsBeneficiaryVO;
import com.xxm.it.business.vo.MsCoinVO;
import com.xxm.it.business.vo.MsEngageVO;
import com.xxm.it.business.vo.MsInsuredPlanVO;
import com.xxm.it.business.vo.MsInsuredVO;
import com.xxm.it.business.vo.MsLoanInfoVO;
import com.xxm.it.business.vo.MsMortgageInfoVO;
import com.xxm.it.business.vo.MsSchemeVO;
import com.xxm.it.business.vo.MsSignInfoVO;
import com.xxm.it.business.vo.SupplementBaseVO;
import com.xxm.it.piic.dao.ITransferPICCDao;
import com.xxm.it.piic.service.ITransferPICCService;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.LoanVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.MortgageinfoVO;
import com.xxm.it.piic.vo.PolicyinfoReturnVO;
import com.xxm.it.piic.vo.PrejudicationVO;
import com.xxm.it.piic.vo.PushPolicyInfoVO;
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
public class EnteringServiceImpl implements IEnteringService {

	private static Logger logger = Logger.getLogger(EnteringServiceImpl.class);

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
	private IMsSignInfoDao msSignInfoDao;// 签约状态查询dao

	@Resource
	private IMsLoanInfoDao msLoanInfoDao;// 请求放款dao

	@Resource
	private IMsMortgageInfoDao msMortgageInfoDao;// 车辆抵押登记信息传输dao

	@Resource
	private ITransferPICCService transferPICCServiceImpl;// 接口表service

	@Resource
	private ITransferPICCDao transferPICCDao;// 接口表dao

	/**
	 * 申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApplyInfoList(ApplyVO applyVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = applyDao.findApplyInfoListCount(applyVO);
		// 查询集合数据
		List<ApplyVO> resultLsit = new ArrayList<ApplyVO>();
		if (total > 0) {
			resultLsit = applyDao.findApplyInfoList(applyVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
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
				if(applyVO.getApplyInfoId()!=null&&!"".equals(applyVO.getApplyInfoId())){
					// 客户信息修改
					BaseCustomerVO baseCustomerVO = applyVO.getCustomerVO();
					if (baseCustomerVO != null) {
						baseCustomerVO = BaseCustomerService.updateBaseCustomer(baseCustomerVO);
					}
					applyVO.setLastUpdateBy(userVO.getUserId());
					applyVO.setApplyCustomerId(baseCustomerVO.getCustomerId());
//					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_INFO_STATUS_INIT);
					applyDao.updateApplyInfo(applyVO);
				}else{
					// 客户信息添加
					BaseCustomerVO baseCustomerVO = applyVO.getCustomerVO();
					if (baseCustomerVO != null) {
						baseCustomerVO = BaseCustomerService.addBaseCustomer(baseCustomerVO);
					}
					applyVO.setCreateBy(userVO.getUserId());
					applyVO.setLastUpdateBy(userVO.getUserId());
					applyVO.setApplyCustomerId(baseCustomerVO.getCustomerId());
					applyVO.setLoanContractNo("LNO" + XxmUtils.getDateString(new Date()));
//					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_INFO_STATUS_INIT);
					applyDao.addApply(applyVO);
				}
				// 接口调用
				PrejudicationVO prejudicationVO = new PrejudicationVO();
				prejudicationVO.setApplyInfoId(applyVO.getApplyInfoId());
				transferPICCServiceImpl.bankpretrial(prejudicationVO);

				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加申请数据成功！");
				logger.info("添加申请信息成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加申请信息失败！");
			logger.info("添加用户失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询预审信息
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public ApplyVO findApplyInfo(ApplyVO applyVO) {
		ApplyVO newApplyVO = new ApplyVO();
		if (applyVO.getApplyInfoId() != null && !"".equals(applyVO.getApplyInfoId())) {
			newApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
			newApplyVO = applyDao.findApplyInfo(newApplyVO);
			// 客户
			BaseCustomerVO customerVO = new BaseCustomerVO();
			customerVO.setCustomerId(newApplyVO.getApplyCustomerId());
			customerVO = baseCustomerDao.findCustomer(customerVO);
			newApplyVO.setCustomerVO(customerVO);

			// 预审响应信息（list）
			RejudicationReturnVO rejudicationReturnVO = new RejudicationReturnVO();
			rejudicationReturnVO.setRequestId(newApplyVO.getApplyInfoId());
			List<RejudicationReturnVO> rejudicationReturnList = transferPICCDao
					.findRejudicationReturn(rejudicationReturnVO);
			newApplyVO.setRejudicationReturnList(rejudicationReturnList);
		}
		return newApplyVO;
	}

	/**
	 * 担保接口信息录入
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
				if ("1".equals(applyVO.getOperationType())) {// 保存
//					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_INFO_STATUS_INIT);
				} else {// 提交
//					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_INFO_STATUS_SUPPLEMENT);
				}
				applyVO.setLastUpdateBy(userVO.getUserId());
				applyDao.updateApplyInfo(applyVO);

				// 客户信息修改
				BaseCustomerVO baseCustomerVO = applyVO.getCustomerVO();
				if (baseCustomerVO != null) {
					baseCustomerVO = BaseCustomerService.updateBaseCustomer(baseCustomerVO);
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
				// 租赁物业务信息
				BaseLeaseInfoVO leaseInfoVO = applyVO.getLeaseInfoVO();
				if (leaseInfoVO != null && leaseInfoVO.getLeaseInfoId() != null
						&& !"".equals(leaseInfoVO.getLeaseInfoId())) {
					leaseInfoDao.updateBusinessLeaseInfo(leaseInfoVO);
				} else if (leaseInfoVO != null) {
					leaseInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
					leaseInfoDao.addBusinessLeaseInfo(leaseInfoVO);
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

				// 附件
				List<AttachmentTextVO> attachmentTextList = applyVO.getAttachmentTextList();
				if (attachmentTextList != null && attachmentTextList.size() > 0) {
					for (AttachmentTextVO attachmentTextVO : attachmentTextList) {
						attachmentTextVO.setAttachmentItemID(applyVO.getApplyInfoId());
						attachmentDao.updateAttachment(attachmentTextVO);
					}
				}

				if ("0".equals(applyVO.getOperationType())) {// 提交
					// 接口调用
					ApplyVO aVO = new ApplyVO();
					aVO.setApplyInfoId(applyVO.getApplyInfoId());
					transferPICCServiceImpl.insure(aVO);
				}

				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加申请数据成功！");
				logger.info("添加申请信息成功！");
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
		if (applyVO.getApplyInfoId() != null && !"".equals(applyVO.getApplyInfoId())) {
			newApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
			newApplyVO = applyDao.findApplyInfo(newApplyVO);
			// 客户
			BaseCustomerVO customerVO = new BaseCustomerVO();
			customerVO.setCustomerId(newApplyVO.getApplyCustomerId());
			customerVO = baseCustomerDao.findCustomer(customerVO);
			newApplyVO.setCustomerVO(customerVO);
			// 查询补充基本信息
			SupplementBaseVO supplementBaseVO = new SupplementBaseVO();
			supplementBaseVO.setApplyInfoId(applyVO.getApplyInfoId());
			supplementBaseVO = applyDao.findSupplementBase(supplementBaseVO);
			newApplyVO.setSupplementBaseVO(supplementBaseVO);
			// 租凭物信息
			BaseLeaseInfoVO leaseInfoVO = new BaseLeaseInfoVO();
			leaseInfoVO.setApplyInfoId(applyVO.getApplyInfoId());
			leaseInfoVO = leaseInfoDao.findBusinessLeaseInfo(leaseInfoVO);
			newApplyVO.setLeaseInfoVO(leaseInfoVO);
			// 附件
			AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
			attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
			attachmentTextVO.setAttachmentItemID(applyVO.getApplyInfoId());
			List<AttachmentTextVO> attachmentTextList = attachmentDao.findAttachments(attachmentTextVO);
			newApplyVO.setAttachmentTextList(attachmentTextList);

			// 投保单归属机构信息
			MsCoinVO msCoinVO = new MsCoinVO();
			msCoinVO.setApplyInfoId(applyVO.getApplyInfoId());
			msCoinVO = applyDao.findMsCoin(msCoinVO);
			newApplyVO.setMsCoinVO(msCoinVO);

			// 查询方案信息
			MsInsuredPlanVO msInsuredPlanVO = new MsInsuredPlanVO();
			msInsuredPlanVO.setApplyInfoId(applyVO.getApplyInfoId());
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
			msInsuredVO.setApplyInfoId(applyVO.getApplyInfoId());
			msInsuredVO = applyDao.findMsInsured(msInsuredVO);
			if (msInsuredVO != null) {
				// 查询受益人信息
				MsBeneficiaryVO msBeneficiaryVO = new MsBeneficiaryVO();
				msBeneficiaryVO.setInsuredId(msInsuredVO.getInsuredId());
				msBeneficiaryVO = applyDao.findMsBeneficiary(msBeneficiaryVO);
				msInsuredVO.setMsBeneficiaryVO(msBeneficiaryVO);
			}
			newApplyVO.setMsInsuredVO(msInsuredVO);

			// 投保响应信息（list）
			PolicyinfoReturnVO policyinfoReturnVO = new PolicyinfoReturnVO();
			policyinfoReturnVO.setApplyInfoId(newApplyVO.getApplyInfoId());
			List<PolicyinfoReturnVO> policyinfoReturnList = transferPICCDao.findPolicyInfoReturn(policyinfoReturnVO);
			newApplyVO.setPolicyinfoReturnList(policyinfoReturnList);
		}
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
//				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_INFO_STATUS_DISCARD);
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
	 * 签约状态列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findSignInfoList(MsSignInfoVO msSignInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = msSignInfoDao.findSignInfoListCount(msSignInfoVO);
		// 查询集合数据
		List<MsSignInfoVO> resultLsit = new ArrayList<MsSignInfoVO>();
		if (total > 0) {
			resultLsit = msSignInfoDao.findSignInfoList(msSignInfoVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public MsSignInfoVO findSignInfo(MsSignInfoVO msSignInfoVO) {
		msSignInfoVO = msSignInfoDao.findSignInfo(msSignInfoVO);
		// 投保响应信息（list）
		SignReturnInfoVO signReturnInfoVO = new SignReturnInfoVO();
		signReturnInfoVO.setRequestId(msSignInfoVO.getSignInfoId());
		List<SignReturnInfoVO> signReturnInfoList = transferPICCDao.findSignreturnInfo(signReturnInfoVO);
		msSignInfoVO.setSignReturnInfoList(signReturnInfoList);
		return msSignInfoVO;
	}

	/**
	 * 添加签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addSignInfo(MsSignInfoVO msSignInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				msSignInfoVO.setLastUpdateBy(userVO.getUserId());
				if (msSignInfoVO.getSignInfoId() != null && !"".equals(msSignInfoVO.getSignInfoId())) {
					msSignInfoDao.updateSignInfo(msSignInfoVO);
				} else {
					msSignInfoVO.setCreateBy(userVO.getUserId());
					msSignInfoDao.addSignInfo(msSignInfoVO);
				}
				// 接口调用
				SignInfoVO signInfoVO = new SignInfoVO();
				signInfoVO.setSignInfoId(msSignInfoVO.getSignInfoId());
				transferPICCServiceImpl.contractStatu(signInfoVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加签约状态查询信息成功！");
				logger.info("添加签约状态查询信息成功");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加签约状态查询信息失败！");
			logger.info("添加签约状态查询信息失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 新增请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> loanInfoEdit(MsLoanInfoVO msLoanInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				// 客户信息添加
				BaseCustomerVO customerVO = msLoanInfoVO.getCustomerVO();
				if (customerVO != null && customerVO.getCustomerId() != null
						&& !"".equals(customerVO.getCustomerId())) {
					customerVO = BaseCustomerService.updateBaseCustomer(customerVO);
				} else if (customerVO != null) {
					customerVO = BaseCustomerService.addBaseCustomer(customerVO);
					msLoanInfoVO.setCustomerId(customerVO.getCustomerId());
				}
				// 租赁物业务信息
				BaseLeaseInfoVO leaseInfoVO = msLoanInfoVO.getLeaseInfoVO();
				leaseInfoVO.setLastUpdateBy(userVO.getUserId());
				if (leaseInfoVO != null && leaseInfoVO.getLeaseInfoId() != null
						&& !"".equals(leaseInfoVO.getLeaseInfoId())) {
					leaseInfoDao.updateBusinessLeaseInfo(leaseInfoVO);
				} else if (leaseInfoVO != null) {
					leaseInfoVO.setCreateBy(userVO.getUserId());
					leaseInfoDao.addBusinessLeaseInfo(leaseInfoVO);
					msLoanInfoVO.setLeaseInfoId(leaseInfoVO.getLeaseInfoId());
				}
				// 请求放款
				msLoanInfoVO.setLastUpdateBy(userVO.getUserId());
				if (msLoanInfoVO.getLoanInfoId() != null && !"".equals(msLoanInfoVO.getLoanInfoId())) {
					msLoanInfoDao.updateLoanInfo(msLoanInfoVO);
				} else {
					msLoanInfoVO.setCreateBy(userVO.getUserId());
					msLoanInfoDao.addLoanInfo(msLoanInfoVO);
				}
				//提交
				if("1".equals(msLoanInfoVO.getLoaninfoStatus())){
					// 接口调用
					LoanVO loanVO = new LoanVO();
					loanVO.setLoanInfoId(msLoanInfoVO.getLoanInfoId());
					transferPICCServiceImpl.transferLoan(loanVO);
				}
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "提交请求放款数据成功！");
				logger.info("提交请求放款成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "提交请求放款失败！");
			logger.info("提交请求放款失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 请求放款列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findLoanInfoList(MsLoanInfoVO msLoanInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = msLoanInfoDao.findLoanInfoListCount(msLoanInfoVO);
		// 查询集合数据
		List<MsLoanInfoVO> resultLsit = new ArrayList<MsLoanInfoVO>();
		if (total > 0) {
			resultLsit = msLoanInfoDao.findLoanInfoList(msLoanInfoVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 查询请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return MsLoanInfoVO 返回结果消息
	 */
	public MsLoanInfoVO findLoaninfo(MsLoanInfoVO msLoanInfoVO) {
		MsLoanInfoVO loanInfoVO = new MsLoanInfoVO();
		if (msLoanInfoVO.getLoanInfoId() != null && !"".equals(msLoanInfoVO.getLoanInfoId())) {
			loanInfoVO.setLoanInfoId(msLoanInfoVO.getLoanInfoId());
			loanInfoVO = msLoanInfoDao.findLoanInfo(loanInfoVO);
			// 客户
			BaseCustomerVO customerVO = new BaseCustomerVO();
			customerVO.setCustomerId(loanInfoVO.getCustomerId());
			customerVO = baseCustomerDao.findCustomer(customerVO);
			loanInfoVO.setCustomerVO(customerVO);
			// 租凭物信息
			BaseLeaseInfoVO leaseInfoVO = new BaseLeaseInfoVO();
			leaseInfoVO.setLeaseInfoId(loanInfoVO.getLeaseInfoId());
			leaseInfoVO = leaseInfoDao.findBusinessLeaseInfo(leaseInfoVO);
			loanInfoVO.setLeaseInfoVO(leaseInfoVO);

			// 请求放款响应信息（list）
			LoanReturnInfoVO loanReturnInfoVO = new LoanReturnInfoVO();
			loanReturnInfoVO.setRequestId(loanInfoVO.getLoanInfoId());
			List<LoanReturnInfoVO> loanReturnInfoList = transferPICCDao.findLoanReturnInfo(loanReturnInfoVO);
			loanInfoVO.setLoanReturnInfoList(loanReturnInfoList);
		}
		return loanInfoVO;
	}

	/**
	 * 车辆抵押登记信息传输列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findMortgageInfoList(MsMortgageInfoVO msMortgageInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = msMortgageInfoDao.findMortgageInfoListCount(msMortgageInfoVO);
		// 查询集合数据
		List<MsMortgageInfoVO> resultLsit = new ArrayList<MsMortgageInfoVO>();
		if (total > 0) {
			resultLsit = msMortgageInfoDao.findMortgageInfoList(msMortgageInfoVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 查询车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return msMortgageInfoVO 返回结果消息
	 */
	public MsMortgageInfoVO findMortgageInfo(MsMortgageInfoVO msMortgageInfoVO) {
		MsMortgageInfoVO mortgageInfoVO = new MsMortgageInfoVO();
		if (msMortgageInfoVO.getMortgageInfoId() != null && !"".equals(msMortgageInfoVO.getMortgageInfoId())) {
			mortgageInfoVO.setMortgageInfoId(msMortgageInfoVO.getMortgageInfoId());
			mortgageInfoVO = msMortgageInfoDao.findMortgageInfo(mortgageInfoVO);
			// 租凭物信息
			BaseLeaseInfoVO leaseInfoVO = new BaseLeaseInfoVO();
			leaseInfoVO.setLeaseInfoId(mortgageInfoVO.getLeaseInfoId());
			leaseInfoVO = leaseInfoDao.findBusinessLeaseInfo(leaseInfoVO);
			mortgageInfoVO.setLeaseInfoVO(leaseInfoVO);

			// 车辆抵押响应信息（list）
			MortgageReturnInfoVO mortgageReturnInfoVO = new MortgageReturnInfoVO();
			mortgageReturnInfoVO.setRequestId(mortgageInfoVO.getMortgageInfoId());
			List<MortgageReturnInfoVO> mortgageReturnInfoList = transferPICCDao
					.findMortgageReturninfo(mortgageReturnInfoVO);
			mortgageInfoVO.setMortgageReturnInfoList(mortgageReturnInfoList);

		}
		return mortgageInfoVO;
	}

	/**
	 * 新增车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> mortgageInfoEdit(MsMortgageInfoVO msMortgageInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			if (userVO != null) {
				// 租赁物业务信息
				BaseLeaseInfoVO leaseInfoVO = msMortgageInfoVO.getLeaseInfoVO();
				leaseInfoVO.setLastUpdateBy(userVO.getUserId());
				if (leaseInfoVO != null && leaseInfoVO.getLeaseInfoId() != null
						&& !"".equals(leaseInfoVO.getLeaseInfoId())) {
					leaseInfoDao.updateBusinessLeaseInfo(leaseInfoVO);
				} else if (leaseInfoVO != null) {
					leaseInfoVO.setCreateBy(userVO.getUserId());
					leaseInfoDao.addBusinessLeaseInfo(leaseInfoVO);
					msMortgageInfoVO.setLeaseInfoId(leaseInfoVO.getLeaseInfoId());
				}
				// 车辆抵押登记信息传输
				msMortgageInfoVO.setLastUpdateBy(userVO.getUserId());
				if (msMortgageInfoVO.getMortgageInfoId() != null && !"".equals(msMortgageInfoVO.getMortgageInfoId())) {
					msMortgageInfoDao.updateMortgageInfo(msMortgageInfoVO);
				} else {
					msMortgageInfoVO.setCreateBy(userVO.getUserId());
					msMortgageInfoDao.addMortgageInfo(msMortgageInfoVO);
				}
				//提交
				if("1".equals(msMortgageInfoVO.getMortgageInfoStatus())){
					// 接口调用
					MortgageinfoVO mortgageinfoVO = new MortgageinfoVO();
					mortgageinfoVO.setMortgageInfoId(msMortgageInfoVO.getMortgageInfoId());
					transferPICCServiceImpl.vehicleMortgageInfo(mortgageinfoVO);
				}
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "提交车辆抵押登记信息传输成功！");
				logger.info("提交车辆抵押登记信息传输成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "提交车辆抵押登记信息传输失败！");
			logger.info("提交车辆抵押登记信息传输失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询审核结果通知集合数据
	 * 
	 * @param auditInfo
	 */
	public JSONObject findAuditList(Auditinfo auditInfo) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = transferPICCDao.findAuditNoticeListCount(auditInfo);
		// 查询集合数据
		List<Auditinfo> resultLsit = new ArrayList<Auditinfo>();
		if (total > 0) {
			resultLsit = transferPICCDao.findAuditNoticeList(auditInfo);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 查询审核结果通知详情
	 * 
	 * @param auditInfo
	 */
	public Auditinfo findAuditNoticeInfo(Auditinfo auditInfo) {
		return transferPICCDao.findAuditNoticeInfo(auditInfo);
	}

	/**
	 * 查询放款通知集合数据
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public JSONObject findLoanNoticeList(LoanInfoInformVO loanInfoInformVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = transferPICCDao.findLoanNoticeListCount(loanInfoInformVO);
		// 查询集合数据
		List<LoanInfoInformVO> resultLsit = new ArrayList<LoanInfoInformVO>();
		if (total > 0) {
			resultLsit = transferPICCDao.findLoanNoticeList(loanInfoInformVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
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
	 * 查询推送保单号list信息
	 * 
	 * @param pushPolicyInfoList
	 * @return
	 */
	public JSONObject findPushPolicyList(PushPolicyInfoVO pushPolicyInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = transferPICCDao.findPushPolicyListCount(pushPolicyInfoVO);
		// 查询集合数据
		List<PushPolicyInfoVO> resultLsit = new ArrayList<PushPolicyInfoVO>();
		if (total > 0) {
			resultLsit = transferPICCDao.findPushPolicyList(pushPolicyInfoVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 查询推送保单号详情
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	public PushPolicyInfoVO findPushPolicyInfo(PushPolicyInfoVO PushPolicyInfoVO) {
		return transferPICCDao.findPushPolicyInfo(PushPolicyInfoVO);
	}

}
