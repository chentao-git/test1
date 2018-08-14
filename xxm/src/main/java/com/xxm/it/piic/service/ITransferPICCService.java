package com.xxm.it.piic.service;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.piic.vo.InsureReturnInfo;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.LoanVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.MortgageinfoVO;
import com.xxm.it.piic.vo.PrejudicationVO;
import com.xxm.it.piic.vo.RejudicationReturnVO;
import com.xxm.it.piic.vo.SignInfoVO;
import com.xxm.it.piic.vo.SignReturnInfoVO;

@Path(value="/ITransferPICCService")
public interface ITransferPICCService {
	
	
	/**
	 * 放款
	 * @return 
	 * @throws Exception 
	 */
	@POST
	@Path("/transferLoan")
	public LoanReturnInfoVO   transferLoan(LoanVO loanVO) throws Exception;
	/**
	 * 银行预审申请
	 * @throws Exception 
	 */
	@POST
	@Path("/bankpretrial")
	public RejudicationReturnVO bankpretrial(PrejudicationVO prejudicationVO) throws Exception;
	
	
	/**
	 * 签约状态
	 * @return 
	 * @throws Exception 
	 */
	@POST
	@Path("/contractStatu")
	public SignReturnInfoVO contractStatu(SignInfoVO signInfoVO) throws Exception;
	/**
	 * 车辆抵押信息
	 * @return 
	 * @throws Exception 
	 */
	@POST
	@Path("/vehicleMortgageInfo")
	public MortgageReturnInfoVO vehicleMortgageInfo(MortgageinfoVO mortgageinfoVO) throws Exception;
	/**
	 * 新增影像附件
	 */
	@POST
	@Path("/addFile")
	public void addFile();
	/**
	 * 投保
	 * @throws Exception 
	 */
	@POST
	@Path("/insure")
	public InsureReturnInfo insure(ApplyVO applyVO) throws Exception;
	/**
	 * 审核结果通知
	 * @throws Exception 
	 */
	@POST
	@Path("/auditInfo")
	public String auditInfo(String param) throws Exception;
	/**
	 * 放款通知
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("/loaninfoInform")
	public String loaninfo(String param) throws Exception;
	/**
	 * 推送保单号
	 * @param param
	 * @return
	 */
	@POST
	@Path("/applyInfo")
	public String applyInfo(String param);
}
