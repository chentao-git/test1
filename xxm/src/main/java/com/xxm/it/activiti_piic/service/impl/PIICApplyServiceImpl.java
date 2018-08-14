package com.xxm.it.activiti_piic.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.vo.Variable;
import com.xxm.it.activiti_piic.handler.PIICFlowHandler;
import com.xxm.it.activiti_piic.service.IPIICApplyService;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.piic.dao.ITransferPICCDao;
import com.xxm.it.piic.service.ITransferPICCService;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.GeneralInfoReturnVO;
import com.xxm.it.piic.vo.InsureReturnInfo;
import com.xxm.it.piic.vo.InsuredReturn;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.PolicyInfos;
import com.xxm.it.piic.vo.PolicyinfoReturnVO;
import com.xxm.it.piic.vo.PushPolicyInfoVO;
import com.xxm.it.piic.vo.SignReturnInfoVO;
import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.util.XxmUser;
import com.xxm.it.system.util.XxmUtils;
import com.xxm.it.system.vo.FlowLogVO;

import net.sf.json.JSONObject;

/**
 * PIIC服务处理实现类
 * 
 * @author Administrator
 *
 */
@Component
@Transactional
public class PIICApplyServiceImpl implements IPIICApplyService {

	// 日志
	private static Logger logger = Logger.getLogger(PIICApplyServiceImpl.class);

	@Autowired
	protected PIICFlowHandler piicFlowService;// 流程处理类

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
	public JSONObject findApply(ApplyVO applyVO) {
		String userId = XxmUser.getCurrentUserId();
		Map<String, Object> map = piicFlowService.findTodoTasks(userId, applyVO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 当前节点下代办人申请信息列表查询
	 * 
	 * @param taskDefinitionKey
	 *            节点Id
	 * @param applyVO
	 *            申请信息
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApply(String taskDefinitionKey, ApplyVO applyVO) {
		String userId = XxmUser.getCurrentUserId();
		Map<String, Object> map = piicFlowService.findTodoTasks(userId, taskDefinitionKey, applyVO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 当前所有人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findAllApply(ApplyVO applyVO) {
		Map<String, Object> map = piicFlowService.findRunningProcessInstaces(applyVO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 所有已结束申请信息列表查询
	 * 
	 * @param taskDefinitionKey
	 *            节点Id
	 * @param applyVO
	 *            申请信息
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApplyFinished(ApplyVO applyVO) {
		Map<String, Object> map = piicFlowService.findFinishedProcessInstaces(applyVO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 读取流程详细信息数据
	 *
	 * @param id
	 * @return applyWithVarsList
	 */
	public List<Map<String, Object>> getApplyWithVars(ApplyVO applyVO) {
		Map<String, Object> applyWithVars = piicFlowService.getApplyWithVars(applyVO);
		// 重组后集合
		List<Map<String, Object>> applyWithVarsList = new ArrayList<Map<String, Object>>();
		if (null != applyWithVars && applyWithVars.size() > 0) {
			List<String> taskDefinitionKeyList = new ArrayList<String>();
			// 第一次循环，获取节点的key
			Iterator<String> iterator = applyWithVars.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				int proIndex = key.lastIndexOf("ProcessingTime");
				if (proIndex != -1) {
					String TaskDefinitionKey = key.substring(0, proIndex);
					taskDefinitionKeyList.add(applyWithVars.get(key) + "_" + TaskDefinitionKey);
				}
			}
			// 排序
			Collections.sort(taskDefinitionKeyList);
			// 根据节点key重组数据
			for (int i = 0; i < taskDefinitionKeyList.size(); i++) {
				Map<String, Object> vars = new HashMap<String, Object>();
				String str = taskDefinitionKeyList.get(i);
				String[] sp = str.split("_");
				Iterator<String> iterator2 = applyWithVars.keySet().iterator();
				while (iterator2.hasNext()) {
					String key = iterator2.next();
					int proIndex = key.lastIndexOf(sp[1]);
					if (proIndex != -1) {
						vars.put(key, applyWithVars.get(key));
					}
				}
				applyWithVarsList.add(vars);
			}
		}
		return applyWithVarsList;
	}

	/**
	 * 查询流程日志
	 * 
	 * @param applyVO
	 * @return List<FlowLogVO>
	 */
	public List<FlowLogVO> findFlowLogList(ApplyVO applyVO) {
		FlowLogVO flowLogVO = new FlowLogVO();
		flowLogVO.setBusinessKey(applyVO.getApplyInfoId());
		return piicFlowService.findFlowLogList(flowLogVO);
	}

	/**
	 * 创建申请
	 * 
	 * @param applyVO
	 *            参数
	 * @throws Exception
	 */
	public void createApply(ApplyVO applyVO) throws Exception {
		String taskDefinitionKey = "startApply";
		// 启动流程信息
		Map<String, Object> parmaMap = new HashMap<String, Object>();
		parmaMap.put("keys", taskDefinitionKey + "Remark");
		parmaMap.put("values", "成功创建项目申请！");
		parmaMap.put("types", "S");
		// 处理参数
		handleVariable(XxmUser.getCurrentUserName(), taskDefinitionKey, parmaMap);
		// 设置参数
		Variable var = new Variable();
		var.setKeys(parmaMap.get("keys").toString());
		var.setValues(parmaMap.get("values").toString());
		var.setTypes(parmaMap.get("types").toString());
		// 获取参数的map集合
		Map<String, Object> variables = var.getVariableMap();
		ProcessInstance startWorkflow = piicFlowService.startWorkflow(applyVO, variables);
		logger.info(startWorkflow.getActivityId() + " " + startWorkflow.getName());
		// 添加日志
		piicFlowService.createFlowLog(XxmUser.getCurrentUserId(), applyVO.getApplyInfoId(), taskDefinitionKey, "true",
				"成功创建项目申请！", "");
	}

	/**
	 * 签收任务
	 * 
	 * @param applyVO
	 *            字符参数
	 * @return resultMap 结果参数
	 */
	public Map<String, Object> claimTask(ApplyVO applyVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		// 判断任务id是否存在
		if (null != applyVO) {
			piicFlowService.claimBatch(userId, applyVO.getTaskIds());
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "签收任务成功！");
		} else {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "签收任务失败！");
		}
		return resultMap;
	}

	/**
	 * 处理任务
	 * 
	 * @param applyVO
	 *            字符参数
	 * @return resultMap 结果参数
	 */
	public Map<String, Object> completeTask(ApplyVO applyVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		try {
			String taskDefinitionKey = applyVO.getTaskDefinitionKey();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", applyVO.getTaskId());
			paramMap.put("keys", applyVO.getKeys());
			paramMap.put("values", applyVO.getValues());
			paramMap.put("types", applyVO.getTypes());
			// 处理参数
			handleVariable(XxmUser.getCurrentUserName(), taskDefinitionKey, paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "处理任务成功！");
			String[] values = applyVO.getValues().split(",", -1);
			if (values.length == 2) {
				// 添加日志
				piicFlowService.createFlowLog(userId, applyVO.getApplyInfoId(), taskDefinitionKey, values[0], "",
						values[1]);
			}
			if (values.length == 3) {
				// 添加日志
				piicFlowService.createFlowLog(userId, applyVO.getApplyInfoId(), taskDefinitionKey, values[0], values[1],
						values[2]);
			}
			if ("true".equals(values[0])) {
				// 补充放款影像完毕后，风控主管审核
				if ("windControlAudit2".equals(taskDefinitionKey)) {
					ApplyVO updataApplyVO = new ApplyVO();
					updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
					updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_PERMIT);
					// 修改申请单状态
					transferPICCDao.uadateApplyInfo(updataApplyVO);
				}
				// 补充抵押影像完毕后，风控主管审核
				else if ("windControlAudit3".equals(taskDefinitionKey)) {
					ApplyVO updataApplyVO = new ApplyVO();
					updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
					updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_MORTGAGE_PERMIT);
					// 修改申请单状态
					transferPICCDao.uadateApplyInfo(updataApplyVO);
				}
				// 风控主管审核
				else if ("windControlSupervisorAudit".equals(taskDefinitionKey)) {
					// 投保
					bankInsure(applyVO);
				}
			} else {
				// 补充放款影像完毕后，风控主管审核
				if ("windControlAudit2".equals(taskDefinitionKey)) {
					ApplyVO updataApplyVO = new ApplyVO();
					updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
					updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SIGNINFO_SUCCEED);
					// 修改申请单状态
					transferPICCDao.uadateApplyInfo(updataApplyVO);
				}
				// 补充抵押影像完毕后，风控主管审核
				else if ("windControlAudit3".equals(taskDefinitionKey)) {
					ApplyVO updataApplyVO = new ApplyVO();
					updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
					updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_SUCCEED);
					// 修改申请单状态
					transferPICCDao.uadateApplyInfo(updataApplyVO);
				} else {
					ApplyVO updataApplyVO = new ApplyVO();
					updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
					updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SUPPLEMENT_SAVE);
					// 修改申请单状态
					transferPICCDao.uadateApplyInfo(updataApplyVO);
				}
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			logger.error("处理任务失败！" + e.getMessage());
			throw new RuntimeException("处理任务失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 银行预审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 * @throws Exception
	 */
	public void bankAudit(String applyInfoId, Auditinfo audit) throws Exception {
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		// 根据申请单获取task对象
		Task task = piicFlowService.getTask(applyInfoId);
		// 判断任务是否存在
		if (null != task) {
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			// 预审结果信息
			StringBuffer result = new StringBuffer();
			// 审核通过
			if (audit.getUnderwriteResult().equals("00")) {
				result.append("true,");
				result.append("预审单号：" + audit.getPrejudicNo() + "，预审通过。");
			} else {
				result.append("false,");
				result.append(
						"预审单号：" + audit.getPrejudicNo() + "，预审不通过。" + XxmUtils.isEmptyChar(audit.getErrorMessage()));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "bankAuditPass,bankAuditReason");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S");
			// 处理参数
			handleVariable("", "bankAudit", paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog("", applyInfoId, "bankAudit", values[0], values[1], "");
		}
	}

	/**
	 * 补充资料
	 * 
	 * @param applyVO
	 *            申请信息
	 * @throws Exception
	 */
	public void dataSupplement(ApplyVO applyVO) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		// 完成
		if (BaseDataConstant.APPLY_STATUS_SUPPLEMENT_SUBMIT.equals(applyVO.getOperationType())) {
			String taskId = applyVO.getTaskId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", applyVO.getTaskDefinitionKey() + "Reason");
			paramMap.put("values", "资料已经补充完毕！");
			paramMap.put("types", "S");
			// 处理参数
			handleVariable(XxmUser.getCurrentUserName(), applyVO.getTaskDefinitionKey(), paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			// 添加日志
			piicFlowService.createFlowLog(userId, applyVO.getApplyInfoId(), applyVO.getTaskDefinitionKey(), "true",
					"资料已经补充完毕！", "");
		}
	}

	/**
	 * 投保
	 * 
	 * @param applyVO
	 *            申请信息
	 * @throws Exception
	 */
	public void bankInsure(ApplyVO applyVO) throws Exception {
		logger.info("风控主管审核通过，开始投保。");
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		// 任务Id
		String taskId = piicFlowService.getTask(applyVO.getApplyInfoId()).getId();
		ApplyVO appVO = new ApplyVO();
		appVO.setApplyInfoId(applyVO.getApplyInfoId());
		// 调用投保接口
		InsureReturnInfo insure = transferPICCServiceImpl.insure(appVO);
		boolean resSign = true;
		// 投保结果信息
		StringBuffer result = new StringBuffer();
		// 消息头
		GeneralInfoReturnVO generalInfoReturnVO = insure.getGeneralInfoReturnVO();
		// 获取code码
		String code = generalInfoReturnVO.getErrorCode();
		if (null != generalInfoReturnVO && "00".equals(code)) {
			// 投保信息
			List<PolicyinfoReturnVO> policyinfoReturnList = insure.getPolicyinfoReturnVO();
			if (null != policyinfoReturnList && policyinfoReturnList.size() > 0) {
				PolicyinfoReturnVO policyinfoReturnVO = policyinfoReturnList.get(0);
				// 投保信息通过
				if ("00".equals(policyinfoReturnVO.getSaveResult())) {
					// 被保人
					List<InsuredReturn> insuredReturn = policyinfoReturnVO.getInsuredReturn();
					if (null != insuredReturn && insuredReturn.size() > 0) {
						InsuredReturn inRe = insuredReturn.get(0);
						// 投保人信息通过
						if ("00".equals(inRe.getCheckResult())) {
							result.append("true,");
							result.append("投保成功。");
						} else {
							resSign = false;
							result.append("false,");
							result.append("投保失败。" + XxmUtils.isEmptyChar(inRe.getCheckResult()));
						}
					}
				} else {
					resSign = false;
					result.append("false,");
					result.append("投保失败。" + XxmUtils.isEmptyChar(policyinfoReturnVO.getSaveMessage()));
				}
			}
		} else {
			resSign = false;
			result.append("false,");
			result.append("投保失败。系统数据异常。");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskId", taskId);
		paramMap.put("keys", "insurePass,insureReason");
		paramMap.put("values", result.toString());
		paramMap.put("types", "B,S");
		// 处理参数
		handleVariable("", "insure", paramMap);
		// 签收任务
		piicFlowService.claim(userId, taskId);
		// 完成任务
		piicFlowService.complete(paramMap);
		String[] values = result.toString().split(",", -1);
		// 添加日志
		piicFlowService.createFlowLog("", applyVO.getApplyInfoId(), "insure", values[0], values[1], "");
		// 投保失败，修改申请单状态
		if (!resSign) {
			ApplyVO updataApplyVO = new ApplyVO();
			updataApplyVO.setApplyInfoId(applyVO.getApplyInfoId());
			updataApplyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SUPPLEMENT_SAVE);
			// 修改申请单状态
			transferPICCDao.uadateApplyInfo(updataApplyVO);
		}
	}

	/**
	 * 银行初审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 * @throws Exception
	 */
	public void startAuditResult(String applyInfoId, Auditinfo audit) throws Exception {
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		// 根据申请单获取task对象
		Task task = piicFlowService.getTask(applyInfoId);
		// 判断任务是否存在
		if (null != task) {
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			// 初审结果
			StringBuffer result = new StringBuffer();
			// 审核通过
			if (audit.getUnderwriteResult().equals("00")) {
				result.append("true,");
				result.append("投保单号:" + audit.getProposalNo() + "，初审通过。");
			} else {
				result.append("false,");
				result.append(
						"投保单号:" + audit.getProposalNo() + "，初审不通过。" + XxmUtils.isEmptyChar(audit.getErrorMessage()));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "startAuditResultPass,startAuditResultReason");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S");
			// 处理参数
			handleVariable("", "startAuditResult", paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog("", applyInfoId, "startAuditResult", values[0], values[1], "");
		}
	}

	/**
	 * 银行终审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 * @throws Exception
	 */
	public void endAuditResult(String applyInfoId, Auditinfo audit) throws Exception {
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		// 根据申请单获取task对象
		Task task = piicFlowService.getTask(applyInfoId);
		// 判断任务是否存在
		if (null != task) {
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			// 终审结果
			StringBuffer result = new StringBuffer();
			// 审核通过
			if (audit.getUnderwriteResult().equals("00")) {
				result.append("true,");
				result.append("投保单号:" + audit.getProposalNo() + "，终审通过。");
			} else {
				result.append("false,");
				result.append(
						"投保单号:" + audit.getProposalNo() + "，终审不通过。" + XxmUtils.isEmptyChar(audit.getErrorMessage()));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "endAuditResultPass,endAuditResultReason");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S");
			// 处理参数
			handleVariable("", "endAuditResult", paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog("", applyInfoId, "endAuditResult", values[0], values[1], "");
		}
	}

	/**
	 * 签约信息查询
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param signInfoVO
	 *            签约接口放回对象
	 * @throws Exception
	 */
	public void signInfo(String applyInfoId, SignReturnInfoVO signReturnInfoVO) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "" == userId) {
			userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		}
		if (null != signReturnInfoVO) {
			// 获取任务Id
			Task task = piicFlowService.getTask(applyInfoId);
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			// 签约结果
			StringBuffer result = new StringBuffer();
			// 签约成功
			if ("00".equals(signReturnInfoVO.getResultCode()) && "COMP".equals(signReturnInfoVO.getApplState())) {
				result.append("true,");
				result.append(signReturnInfoVO.getApplState() + ",");
				result.append(signReturnInfoVO.getResultMessage());
			} else {
				result.append("false,");
				result.append(signReturnInfoVO.getApplState() + ",");
				result.append(signReturnInfoVO.getResultMessage());
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "signInfoPass,signInfoReason,signInfoRemark");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S,S");
			// 处理参数
			handleVariable("", "signInfo", paramMap);
			// 完成任务
			piicFlowService.complete(paramMap);
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog((null == userId || "" == userId) ? "" : userId, applyInfoId, "signInfo",
					values[0], values[1], values[2]);
		}
	}

	/**
	 * 补充放款影像
	 * 
	 * @param applyVO
	 * @throws Exception
	 */
	public void loanMakeUp(ApplyVO applyVO) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		// 获取任务Id
		Task task = piicFlowService.getTask(applyVO.getApplyInfoId());
		String taskId = task.getId();
		// 签收任务
		piicFlowService.claim(userId, taskId);
		// 放款申请结果
		StringBuffer result = new StringBuffer();
		// 已补充资料待风控审核
		if (BaseDataConstant.APPLY_STATUS_LOAN_UPLOAD.equals(applyVO.getApplyInfoStatus())) {
			result.append("true,");
			result.append("已补充放款影像！");
		} else {
			result.append("false,");
			result.append("未补充放款影像！");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskId", taskId);
		paramMap.put("keys", "makeUpLoanPass,makeUpLoanReason");
		paramMap.put("values", result.toString());
		paramMap.put("types", "B,S");
		// 处理参数
		handleVariable(XxmUser.getCurrentUserName(), "makeUpLoan", paramMap);
		// 完成任务
		piicFlowService.complete(paramMap);
		String[] values = result.toString().split(",", -1);
		// 添加日志
		piicFlowService.createFlowLog(userId, applyVO.getApplyInfoId(), "makeUpLoan", values[0], values[1], "");
	}

	/**
	 * 放款申请
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param transferLoan
	 *            放款响应放回结果对象
	 * @throws Exception
	 */
	public void loanApply(String applyInfoId, LoanReturnInfoVO transferLoan) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		if (null != transferLoan) {
			// 放款申请结果
			StringBuffer result = new StringBuffer();
			// 放款成功
			if ("00".equals(transferLoan.getResultCode())) {
				// 获取任务Id
				Task task = piicFlowService.getTask(applyInfoId);
				String taskId = task.getId();
				// 签收任务
				piicFlowService.claim(userId, taskId);
				result.append("true,");
				result.append("投保单号：" + transferLoan.getProposalno() + " 已经成功提交放款申请！,");
				result.append(transferLoan.getResultMessage());
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("taskId", taskId);
				paramMap.put("keys", "loanApplyPass,loanApplyReason,loanApplyRemark");
				paramMap.put("values", result.toString());
				paramMap.put("types", "B,S,S");
				// 处理参数
				handleVariable(XxmUser.getCurrentUserName(), "loanApply", paramMap);
				// 完成任务
				piicFlowService.complete(paramMap);
			} else {
				result.append("false,");
				result.append(",");
				result.append(transferLoan.getResultMessage());
			}
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog(userId, applyInfoId, "loanApply", values[0], values[1], values[2]);
		}
	}

	/**
	 * 放款通知
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            请求报文信息
	 * @throws Exception
	 */
	public void loanNotice(String applyInfoId, LoanInfoInformVO audit) throws Exception {
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		if (null != audit) {
			// 放款申请结果
			StringBuffer result = new StringBuffer();
			// 放款成功
			if ("01".equals(audit.getLoanResult())) {
				// 获取任务Id
				Task task = piicFlowService.getTask(applyInfoId);
				String taskId = task.getId();
				// 签收任务
				piicFlowService.claim(userId, taskId);
				result.append("true,");
				result.append(audit.getLoanResult() + ",投保单号：" + audit.getProposalNo());
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("taskId", taskId);
				paramMap.put("keys", "loanNoticePass,loanNoticeReason,loanNoticeRemark");
				paramMap.put("values", result.toString());
				paramMap.put("types", "B,S,S");
				// 处理参数
				handleVariable(XxmUser.getCurrentUserName(), "loanNotice", paramMap);
				// 完成任务
				piicFlowService.complete(paramMap);
			} else {
				result.append("true,");
				result.append(audit.getLoanResult() + ",");
			}
			String[] values = result.toString().split(",", -1);
			// 添加日志
			piicFlowService.createFlowLog("", applyInfoId, "loanNotice", values[0], values[1], values[2]);
		}
	}

	/**
	 * 补充抵押影响
	 * 
	 * @param applyVO
	 * @throws Exception
	 */
	public void mortgageMakeUp(ApplyVO applyVO) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		// 获取任务Id
		Task task = piicFlowService.getTask(applyVO.getApplyInfoId());
		String taskId = task.getId();
		// 签收任务
		piicFlowService.claim(userId, taskId);
		// 放款申请结果
		StringBuffer result = new StringBuffer();
		// 已补充影像资料待风控审核
		if (BaseDataConstant.APPLY_STATUS_MORTGAGE_UPLOAD.equals(applyVO.getApplyInfoStatus())) {
			result.append("true,");
			result.append("已补充抵押影像！");
		} else {
			result.append("false,");
			result.append("未补充抵押影像！");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskId", taskId);
		paramMap.put("keys", "makeUpMortgagePass,makeUpMortgageReason");
		paramMap.put("values", result.toString());
		paramMap.put("types", "B,S");
		// 处理参数
		handleVariable(XxmUser.getCurrentUserName(), "makeUpMortgage", paramMap);
		// 完成任务
		piicFlowService.complete(paramMap);
		String[] values = result.toString().split(",", -1);
		// 添加日志
		piicFlowService.createFlowLog(userId, applyVO.getApplyInfoId(), "makeUpMortgage", values[0], values[1], "");
	}

	/**
	 * 补充抵押证明/GRS安装
	 * 
	 * @param applyInfoId
	 * @param audit
	 * @throws Exception
	 */
	public void mortgage(String applyInfoId, MortgageReturnInfoVO info) throws Exception {
		String userId = XxmUser.getCurrentUserId();
		if (null == userId || "".equals(userId)) {
			throw new Exception("User unlogged system. userId is null.");
		}
		// 放款申请结果
		StringBuffer result = new StringBuffer();
		// 已补充影像资料待风控审核
		if ("00".equals(info.getResultCode())) {
			result.append("true,");
			result.append("投保单号：" + info.getProposalno() + ",");
			result.append("已经补充抵押证明，并完成GRS安装");
			// 获取任务Id
			Task task = piicFlowService.getTask(applyInfoId);
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "mortgagePass,mortgageReason,mortgageRemark");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S,S");
			// 完成任务
			piicFlowService.complete(paramMap);
		} else {
			result.append("false,");
			result.append("投保单号：" + info.getProposalno() + ",");
			result.append(info.getResultMessage());
		}
		String[] values = result.toString().split(",", -1);
		// 添加日志
		piicFlowService.createFlowLog(userId, applyInfoId, "mortgage", values[0], values[1], values[2]);
	}

	/**
	 * 接受保单号
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param info
	 *            接收保单号请求对象
	 * @throws Exception
	 */
	public void receivePolicyNumber(String applyInfoId, PolicyInfos info) throws Exception {
		String userId = XxmConstant.BaseDataConstant.ADMIN_ID;// 管理员签收
		// 放款申请结果
		StringBuffer result = new StringBuffer();
		// 保单号
		String policyNo = "";
		List<PushPolicyInfoVO> pushPolicyInfoList = info.getPushPolicyInfoList();
		if (null != pushPolicyInfoList && pushPolicyInfoList.size() > 0) {
			PushPolicyInfoVO pushPolicyInfoVO = pushPolicyInfoList.get(0);
			policyNo = pushPolicyInfoVO.getPolicyNo();
		}
		// 判断投保单号是否为空
		if (null != policyNo && !"".equals(policyNo)) {
			result.append("true,");
			result.append("保单号：" + policyNo);
			// 获取任务Id
			Task task = piicFlowService.getTask(applyInfoId);
			String taskId = task.getId();
			// 签收任务
			piicFlowService.claim(userId, taskId);
			// 签收任务
			piicFlowService.claim(userId, taskId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskId", taskId);
			paramMap.put("keys", "receivePolicyNumberPass,receivePolicyNumberReason");
			paramMap.put("values", result.toString());
			paramMap.put("types", "B,S");
			// 完成任务
			piicFlowService.complete(paramMap);
		} else {
			result.append("false,");
			result.append("接收保单号失败！");
		}
		String[] values = result.toString().split(",", -1);
		// 添加日志
		piicFlowService.createFlowLog("", applyInfoId, "receivePolicyNumber", values[0], values[1], "");
	}

	/**
	 * 处理参数
	 * 
	 * @param variable
	 */
	private void handleVariable(String userName, String taskDefinitionKey, Map<String, Object> variable) {
		String keys = variable.get("keys").toString();
		String values = variable.get("values").toString();
		String types = variable.get("types").toString();
		// 当前处理处理用户
		keys += ("," + taskDefinitionKey + "Handler");
		values += ("," + userName);
		types += (",S");
		// 当前处理处理时间
		keys += ("," + taskDefinitionKey + "ProcessingTime");
		values += ("," + XxmUtils.getCurrentDate(BaseDataConstant.FORMATE_DATA_TIME2));
		types += (",S");
		variable.put("keys", keys);
		variable.put("values", values);
		variable.put("types", types);
	}

}
