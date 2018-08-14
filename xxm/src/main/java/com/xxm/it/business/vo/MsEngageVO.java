package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

/**
 * 特别约定信息对象
 * 
 * @author Administrator
 *
 */
public class MsEngageVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String engageId;//
	private String engageCode;//特别约定代码
	private String engageName;//特别约定名称
	private String engagedesc;//特别约定具体内容
	private String insuredPlanId;//方案信息id
	public String getEngageId() {
		return engageId;
	}
	public void setEngageId(String engageId) {
		this.engageId = engageId;
	}
	public String getEngageCode() {
		return engageCode;
	}
	public void setEngageCode(String engageCode) {
		this.engageCode = engageCode;
	}
	public String getEngageName() {
		return engageName;
	}
	public void setEngageName(String engageName) {
		this.engageName = engageName;
	}
	public String getEngagedesc() {
		return engagedesc;
	}
	public void setEngagedesc(String engagedesc) {
		this.engagedesc = engagedesc;
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
