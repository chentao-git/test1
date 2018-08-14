package com.xxm.it.system.service;



import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.xxm.it.system.vo.FiledVO;
import com.xxm.it.system.vo.RemarkVO;

import net.sf.json.JSONObject;

@Path(value="lawsuitService")
public interface ILawsuitService {

	/**
	 * 
	 * 根据贷款编号查询贷款客户信息
	 * 
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/loanNumberQuery")
	public FiledVO loanNumberQuery(FiledVO filedvo);
	
	/**
	 * 
	 * 保存诉讼申请信息
	 * 
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/addApplyInfo")
	public Map<String, Object> saveApplyInfo(FiledVO filedvo);
	/**
	 * 
	 * 保存诉讼申请信息
	 * 
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/applyInfoList")
	public JSONObject applyInfoList(FiledVO filedvo);

	/**
	 * 
	 * 诉讼审核信息查询
	 * 
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/auditInfoQuery")
	public FiledVO auditInfoQuery(FiledVO filedvo);
	
	/**
	 * 诉讼申请详情信息查询
	 *
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/auditParticularsQuery")
	public FiledVO auditParticularsQuery(FiledVO filedvo);
	
	/***
	 * 诉讼跟踪信息添加
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/addTrackingInfo")
	public Map<String, Object> addTrackingInfo(RemarkVO filedvo);
	
	/**
	 * 保存审核人信息
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/saveAuditInfo")
	public Map<String, Object> saveAuditInfo(FiledVO filedvo);
	
	/**
	 * 处理登记信息保存 
	 * @param filedvo
	 * @return
	 */
	@POST
	@Path("/addRegisterInfo")
	public Map<String, Object> addRegisterInfo(FiledVO filedvo);
}
