package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

@XmlRootElement(name="SignreturnInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class SignReturnInfoVO extends CommonVO{ //签约状态查询响应信息
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String requestId;
	@XmlElement(name = "ApplState")
	private String applState;//当前申请状态
	@XmlElement(name = "LoanAmt")
	private String loanAmt;//银行审批金额
	@XmlElement(name = "IntAdjPct")
	private String intAdjPct;//正常利率上浮比例
	@XmlElement(name = "ResultCode")
	private String resultCode; //响应信息
	@XmlElement(name = "ResultMessage")
	private String resultMessage; //响应信息描述
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getApplState() {
		return applState;
	}
	public void setApplState(String applState) {
		this.applState = applState;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getIntAdjPct() {
		return intAdjPct;
	}
	public void setIntAdjPct(String intAdjPct) {
		this.intAdjPct = intAdjPct;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
