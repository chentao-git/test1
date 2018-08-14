package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

/**
 * 条款信息对象
 * 
 * @author Administrator
 *
 */
public class MsSchemeVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String schemeId;//
	private String schemeCode;//编号
	private String schemeAmount;//保额
	private String schemePremium;//保费
	private String insuredPlanId;//方案信息id
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeAmount() {
		return schemeAmount;
	}
	public void setSchemeAmount(String schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	public String getSchemePremium() {
		return schemePremium;
	}
	public void setSchemePremium(String schemePremium) {
		this.schemePremium = schemePremium;
	}
	public String getInsuredPlanId() {
		return insuredPlanId;
	}
	public void setInsuredPlanId(String insuredPlanId) {
		this.insuredPlanId = insuredPlanId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
