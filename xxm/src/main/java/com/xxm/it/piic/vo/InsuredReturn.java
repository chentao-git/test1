package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

/**
 * 投保被保险人响应
 * @author Administrator
 *
 */
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsuredReturn extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String policyInfoReturnId;
	@XmlElement(name = "InsuredSeqNo")
	private String insuredSeqNo;//被保险人序列号
	@XmlElement(name = "CheckResult")
	private String checkResult; //被保险人响应代码
	@XmlElement(name = "CheckMessage")
	private String checkMessage; //被保险人响应信息
	public String getInsuredSeqNo() {
		return insuredSeqNo;
	}
	public void setInsuredSeqNo(String insuredSeqNo) {
		this.insuredSeqNo = insuredSeqNo;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckMessage() {
		return checkMessage;
	}
	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}
	public String getPolicyInfoReturnId() {
		return policyInfoReturnId;
	}
	public void setPolicyInfoReturnId(String policyInfoReturnId) {
		this.policyInfoReturnId = policyInfoReturnId;
	}
	
	
	
}
