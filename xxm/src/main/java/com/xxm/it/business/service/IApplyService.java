package com.xxm.it.business.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.MsLoanInfoVO;
import com.xxm.it.business.vo.MsMortgageInfoVO;
import com.xxm.it.business.vo.MsSignInfoVO;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.SignInfoVO;

import net.sf.json.JSONObject;


@Path(value = "/applyService")
public interface IApplyService {

	/**
	 * 当前代办人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findApplyInfoList")
	public JSONObject findApplyInfoList(ApplyVO applyVO);
	/**
	 * 查询贷款信息列表
	 * @param applyVO
	 * @return
	 */
	@POST
	@Path("/findApplyList")
	public JSONObject findApplyList(ApplyVO applyVO);

	/**
	 * 当前所有人申请信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findAllApplyInfoList")
	public JSONObject findAllApplyInfoList(ApplyVO applyVO);
	
	/**
	 * 当前代办人补充资料信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findMakeUpApplyInfoList")
	public JSONObject findMakeUpApplyInfoList(ApplyVO applyVO);
	
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
	 * 查询申请信息
	 * 
	 * @param applyVO
	 * @return
	 */
	@POST
	@Path("/findApplyInfo")
	public ApplyVO findApplyInfo(ApplyVO applyVO);
	/**
	 * 客户详情资料
	 * @param applyVO
	 * @return
	 */
	@POST
	@Path("/findCustomerDate")
	public ApplyVO findCustomerDate(ApplyVO applyVO);
	
	/**
	 * 添加签约状态查询
	 * 
	 * @param auditinfo
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/addSignInfo")
	public Map<String, Object> addSignInfo(Auditinfo auditinfo);
	
	/**
	 * 根据申请id获取签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	@POST
	@Path("/findSignInfos")
	public List<MsSignInfoVO> findSignInfos(MsSignInfoVO msSignInfoVO);
	
	/**
	 * 新增请求放款
	 * 
	 * @param msLoanInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/requestLoanInfo")
	public Map<String, Object> requestLoanInfo(MsLoanInfoVO msLoanInfoVO);
	
	/**
	 * 获取请求放款响应信息
	 * 
	 * @param loanReturnInfoVO
	 * @return
	 */
	@POST
	@Path("/findLoanReturnInfos")
	public List<LoanReturnInfoVO> findLoanReturnInfos(LoanReturnInfoVO loanReturnInfoVO);
	
	/**
	 * 请求车辆抵押登记信息传输
	 * 
	 * @param msMortgageInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/requestMortgageInfo")
	public Map<String, Object> requestMortgageInfo(MsMortgageInfoVO msMortgageInfoVO);
	
	/**
	 * 查询车辆抵押登记响应信息
	 * 
	 * @param mortgageReturnInfoVO
	 *            参数
	 * @return 
	 */
	@POST
	@Path("/findMortgageReturnInfos")
	public List<MortgageReturnInfoVO> findMortgageReturnInfos(MortgageReturnInfoVO mortgageReturnInfoVO);
	
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
	 * 请求签约状态查询
	 * 
	 * @param 
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/requestSignInfos")
	public Map<String, Object> requestSignInfos();
	
	/**
	 * 查询申请单的审核结果通知日志
	 * 
	 * @param auditInfo
	 *            参数
	 * @return List<Auditinfo>
	 */
	@POST
	@Path("/findAuditInfos")
	public List<Auditinfo> findAuditInfos(Auditinfo auditInfo);
	
	/**
	 * 手动点击添加签约状态查询
	 * 
	 * @param signInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/manualSignReturninfo")
	public Map<String, Object> manualSignReturninfo(SignInfoVO signInfoVO);
	
	/**
	 * 补充影像（提交）
	 * 
	 * @param applyVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/loanUpload")
	public Map<String, Object> loanUpload(ApplyVO applyVO);
}
