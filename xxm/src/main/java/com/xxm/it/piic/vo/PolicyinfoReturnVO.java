package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.vo.CommonVO;

/**
 * 投保响应对象
 * 
 * @author Administrator
 *
 */
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class PolicyinfoReturnVO extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String policyInfoReturnId;//
	@XmlElement(name = "SerialNo")
	private String serialNo;//请求序列号
	@XmlElement(name = "ProposalNo")
	private String proposalNo;//投保单号
	@XmlElement(name = "SaveResult")
	private String saveResult;//响应代码
	@XmlElement(name = "SaveMessage")
	private String saveMessage;//响应信息
	@XmlElement(name = "SaveTimes")
	private String saveTimes;//处理结束时间
	@XmlTransient
	private String applyInfoId;//申请id
	@XmlElementWrapper(name = "InsuredReturns") 
    @XmlElement(name = "InsuredReturn")
	private List<InsuredReturn> insuredReturn;//被保险人响应信息	
	public String getPolicyInfoReturnId() {
		return policyInfoReturnId;
	}
	public void setPolicyInfoReturnId(String policyInfoReturnId) {
		this.policyInfoReturnId = policyInfoReturnId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getSaveResult() {
		return saveResult;
	}
	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}
	public String getSaveMessage() {
		return saveMessage;
	}
	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}
	public String getSaveTimes() {
		return saveTimes;
	}
	public void setSaveTimes(String saveTimes) {
		this.saveTimes = saveTimes;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public List<InsuredReturn> getInsuredReturn() {
		return insuredReturn;
	}
	public void setInsuredReturn(List<InsuredReturn> insuredReturn) {
		this.insuredReturn = insuredReturn;
	}

}
