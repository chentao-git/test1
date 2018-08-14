package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

@XmlRootElement(name="MortgageReturnInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class MortgageReturnInfoVO  extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String requestId;
	@XmlElement(name = "Proposalno")
	private String proposalno; //投保单号
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
	public String getProposalno() {
		return proposalno;
	}
	public void setProposalno(String proposalno) {
		this.proposalno = proposalno;
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
