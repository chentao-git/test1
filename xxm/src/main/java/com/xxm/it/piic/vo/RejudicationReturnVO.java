package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.xxm.it.system.vo.CommonVO;

/**
 * 银行预审响应
 * @author Administrator
 *
 */
//@XmlType(propOrder={"id","name","age","birthDay","list","teacher","map"}) //指定序列成的xml节点顺序
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class RejudicationReturnVO extends CommonVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String prejudicationReturnId;
	
	private String requestId;
	@XmlElement(name = "PrejudicNo")
	private String prejudicNo;
	@XmlElement(name = "ResultCode")
	private String resultCode;
	@XmlElement(name = "ResultMessage")
	private String resultMessage;
	
	public String getPrejudicationReturnId() {
		return prejudicationReturnId;
	}
	public void setPrejudicationReturnId(String prejudicationReturnId) {
		this.prejudicationReturnId = prejudicationReturnId;
	}
	public String getPrejudicNo() {
		return prejudicNo;
	}
	public void setPrejudicNo(String prejudicNo) {
		this.prejudicNo = prejudicNo;
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
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
