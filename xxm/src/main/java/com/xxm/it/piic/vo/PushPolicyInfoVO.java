package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xxm.it.system.vo.CommonVO;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
//推送保单号
public class PushPolicyInfoVO extends CommonVO{  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  
	@XmlElement(name="SerialNo")
	private String serialNo;//请求序列号
	@XmlElement(name="PolicyNo")
	private String policyNo;//保单号
	@XmlElement(name="ProposalNo")
	private String proposalNo;//投保单号
	@XmlElement(name="PolicyUrl")
	private String policyUrl;//保单查询链接
	private String pushPolicyInfosId; 
	private String status;
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyUrl() {
		return policyUrl;
	}
	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}
	public String getPushPolicyInfosId() {
		return pushPolicyInfosId;
	}
	public void setPushPolicyInfosId(String pushPolicyInfosId) {
		this.pushPolicyInfosId = pushPolicyInfosId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
