package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PolicyReturnInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class PolicyReturnInfoVO {
    @XmlElement(name="Proposalno")
	private String proposalno;//投保单号
	@XmlElement(name="ResultCode")
	private String resultCode;//响应信息 节点值为”00”，不成功时为“-1”
	@XmlElement(name="ResultMessage")
	private String resultMessage;//响应信息描述
	
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
