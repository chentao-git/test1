package com.xxm.it.business.vo;

import java.util.ArrayList;
import java.util.List;

import com.xxm.it.system.vo.CommonVO;

/**
 * 方案信息对象
 * 
 * @author Administrator
 *
 */
public class MsInsuredPlanVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String insuredPlanId;//
	private String rationType;//方案代码
	private String applyInfoId;//申请id
	private List<MsSchemeVO> msSchemeList=new ArrayList<MsSchemeVO>();//条款信息
	private List<MsEngageVO> msEngageList=new ArrayList<MsEngageVO>();//特别约定信息
	public String getInsuredPlanId() {
		return insuredPlanId;
	}
	public void setInsuredPlanId(String insuredPlanId) {
		this.insuredPlanId = insuredPlanId;
	}
	public String getRationType() {
		return rationType;
	}
	public void setRationType(String rationType) {
		this.rationType = rationType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<MsSchemeVO> getMsSchemeList() {
		return msSchemeList;
	}
	public void setMsSchemeList(List<MsSchemeVO> msSchemeList) {
		this.msSchemeList = msSchemeList;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public List<MsEngageVO> getMsEngageList() {
		return msEngageList;
	}
	public void setMsEngageList(List<MsEngageVO> msEngageList) {
		this.msEngageList = msEngageList;
	}


}
