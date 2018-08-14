package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.MsLoanInfoVO;
import com.xxm.it.business.vo.MsMortgageInfoVO;
import com.xxm.it.business.vo.MsSignInfoVO;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.PushPolicyInfoVO;

import net.sf.json.JSONObject;

@Path(value = "/enteringService")
public interface IEnteringService {

	/**
	 * 申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findApplyInfoList")
	public JSONObject findApplyInfoList(ApplyVO applyVO);

	/**
	 * 申请表添加
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/addApply")
	public Map<String, Object> addApply(ApplyVO applyVO);

	/**
	 * 查询预审信息
	 * 
	 * @param applyVO
	 *            参数
	 * @return
	 */
	@POST
	@Path("/findApplyInfo")
	public ApplyVO findApplyInfo(ApplyVO applyVO);

	/**
	 * 资料补充
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/dataSupplement")
	public Map<String, Object> dataSupplement(ApplyVO applyVO);

	/**
	 * 查询该项目的全部资料
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/findProjectData")
	public ApplyVO findProjectData(ApplyVO applyVO);

	/**
	 * 删除申请信息
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/deleteApplyInfo")
	public Map<String, Object> deleteApplyInfo(ApplyVO applyVO);

	/**
	 * 签约状态列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findSignInfoList")
	public JSONObject findSignInfoList(MsSignInfoVO msSignInfoVO);

	/**
	 * 添加签约状态查询
	 * 
	 * @param msSignInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/addSignInfo")
	public Map<String, Object> addSignInfo(MsSignInfoVO msSignInfoVO);

	/**
	 * 新增请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/loanInfoEdit")
	public Map<String, Object> loanInfoEdit(MsLoanInfoVO msLoanInfoVO);

	/**
	 * 请求放款列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findLoanInfoList")
	public JSONObject findLoanInfoList(MsLoanInfoVO msLoanInfoVO);

	/**
	 * 查询请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return MsLoanInfoVO 返回结果消息
	 */
	@POST
	@Path("/findLoaninfo")
	public MsLoanInfoVO findLoaninfo(MsLoanInfoVO msLoanInfoVO);

	/**
	 * 车辆抵押登记信息传输列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findMortgageInfoList")
	public JSONObject findMortgageInfoList(MsMortgageInfoVO msMortgageInfoVO);

	/**
	 * 查询车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return msMortgageInfoVO 返回结果消息
	 */
	@POST
	@Path("/findMortgageInfo")
	public MsMortgageInfoVO findMortgageInfo(MsMortgageInfoVO msMortgageInfoVO);

	/**
	 * 新增车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/mortgageInfoEdit")
	public Map<String, Object> mortgageInfoEdit(MsMortgageInfoVO msMortgageInfoVO);

	/**
	 * 签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	@POST
	@Path("/findSignInfo")
	public MsSignInfoVO findSignInfo(MsSignInfoVO msSignInfoVO);

	/**
	 * 查询审核结果通知集合数据
	 * 
	 * @param auditInfo
	 */
	@POST
	@Path("/findAuditList")
	public JSONObject findAuditList(Auditinfo auditInfo);

	/**
	 * 查询审核结果通知详情
	 * 
	 * @param auditInfo
	 */
	@POST
	@Path("/findAuditNoticeInfo")
	public Auditinfo findAuditNoticeInfo(Auditinfo auditInfo);

	/**
	 * 查询放款通知集合数据
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	@POST
	@Path("/findLoanNoticeList")
	public JSONObject findLoanNoticeList(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 查询放款通知详细
	 * 
	 * @param loanInfoInformVO
	 * @return
	 */
	@POST
	@Path("/findLoanNoticeInfo")
	public LoanInfoInformVO findLoanNoticeInfo(LoanInfoInformVO loanInfoInformVO);

	/**
	 * 查询推送保单号list信息
	 * 
	 * @param pushPolicyInfoVO
	 * @return
	 */
	@POST
	@Path("/findPushPolicyList")
	public JSONObject findPushPolicyList(PushPolicyInfoVO pushPolicyInfoVO);

	/**
	 * 查询推送保单号详情
	 * 
	 * @param PushPolicyInfoVO
	 * @return
	 */
	@POST
	@Path("/findPushPolicyInfo")
	public PushPolicyInfoVO findPushPolicyInfo(PushPolicyInfoVO PushPolicyInfoVO);
}
