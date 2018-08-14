package com.xxm.it.activiti_piic.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.PolicyInfos;
import com.xxm.it.piic.vo.SignReturnInfoVO;
import com.xxm.it.system.vo.FlowLogVO;

import net.sf.json.JSONObject;

/**
 * PIIC服务处理接口类
 * 
 * @author Administrator
 *
 */
@Path(value = "/piicApplyService")
public interface IPIICApplyService {

	/**
	 * 当前代办人申请信息列表查询
	 * 
	 * @param applyVO
	 *            申请信息
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApply(ApplyVO applyVO);

	/**
	 * 当前节点下代办人申请信息列表查询
	 * 
	 * @param taskDefinitionKey
	 *            节点Id
	 * @param applyVO
	 *            申请信息
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApply(String taskDefinitionKey, ApplyVO applyVO);

	/**
	 * 当前所有人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findAllApply(ApplyVO applyVO);

	/**
	 * 所有已结束申请信息列表查询
	 * 
	 * @param taskDefinitionKey
	 *            节点Id
	 * @param applyVO
	 *            申请信息
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findApplyFinished(ApplyVO applyVO);

	/**
	 * 读取流程详细信息数据
	 *
	 * @param id
	 * @return Map<String, Object>
	 */
	@POST
	@Path("/getApplyWithVars")
	public List<Map<String, Object>> getApplyWithVars(ApplyVO applyVO);

	/**
	 * 查询流程日志
	 * 
	 * @param applyVO
	 * @return List<FlowLogVO>
	 */
	@POST
	@Path("/findFlowLogList")
	public List<FlowLogVO> findFlowLogList(ApplyVO applyVO);

	/**
	 * 创建申请
	 * 
	 * @param applyVO
	 *            参数
	 */
	public void createApply(ApplyVO applyVO) throws Exception;

	/**
	 * 签收任务
	 * 
	 * @param applyVO
	 *            字符参数
	 */
	@POST
	@Path("/claimTask")
	public Map<String, Object> claimTask(ApplyVO applyVO) throws Exception;

	/**
	 * 处理任务
	 * 
	 * @param applyVO
	 *            字符参数
	 * @return resultMap 结果参数
	 */
	@POST
	@Path("/completeTask")
	public Map<String, Object> completeTask(ApplyVO applyVO) throws Exception;

	/**
	 * 银行预审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 */
	@POST
	@Path("/bankAudit")
	public void bankAudit(String applyInfoId, Auditinfo audit) throws Exception;

	/**
	 * 补充资料
	 * 
	 * @param applyVO
	 *            申请信息
	 */
	public void dataSupplement(ApplyVO applyVO) throws Exception;

	/**
	 * 投保
	 * 
	 * @param applyVO
	 *            申请信息
	 * @throws Exception
	 */
	@POST
	@Path("/bankInsure")
	public void bankInsure(ApplyVO applyVO) throws Exception;

	/**
	 * 银行初审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 * @throws Exception
	 */
	@POST
	@Path("/startAuditResult")
	public void startAuditResult(String applyInfoId, Auditinfo audit) throws Exception;

	/**
	 * 银行终审
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            结果消息
	 * @throws Exception
	 */
	@POST
	@Path("/endAuditResult")
	public void endAuditResult(String applyInfoId, Auditinfo audit) throws Exception;

	/**
	 * 签约信息查询
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param signReturnInfoVO
	 *            签约接口放回对象
	 * @throws Exception
	 */
	@POST
	@Path("/signInfo")
	public void signInfo(String applyInfoId, SignReturnInfoVO signReturnInfoVO) throws Exception;

	/**
	 * 补充放款影像
	 * 
	 * @param applyVO
	 * @throws Exception
	 */
	public void loanMakeUp(ApplyVO applyVO) throws Exception;

	/**
	 * 放款申请
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param transferLoan
	 *            放款响应放回结果对象
	 * @throws Exception
	 */
	@POST
	@Path("/loanApply")
	public void loanApply(String applyInfoId, LoanReturnInfoVO transferLoan) throws Exception;

	/**
	 * 放款通知
	 * 
	 * @param applyInfoId
	 *            申请单Id
	 * @param audit
	 *            请求报文信息
	 * @throws Exception
	 */
	public void loanNotice(String applyInfoId, LoanInfoInformVO audit) throws Exception;

	/**
	 * 补充抵押影响
	 * 
	 * @param applyVO
	 * @throws Exception
	 */
	public void mortgageMakeUp(ApplyVO applyVO) throws Exception;

	/**
	 * 补充抵押证明/GRS安装
	 * 
	 * @param applyInfoId
	 * @param audit
	 * @throws Exception
	 */
	public void mortgage(String applyInfoId, MortgageReturnInfoVO info) throws Exception;

	/**
	 * 接受保单号
	 * 
	 * @param applyInfoId
	 *            申请Id
	 * @param info
	 *            接收保单号请求对象
	 * @throws Exception
	 */
	public void receivePolicyNumber(String applyInfoId, PolicyInfos info) throws Exception;
}
