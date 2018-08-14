package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class ActIdGroupVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String actIdGroupID;//
	private String actIdGroupREV;//
	private String actIdGroupNAME;//
	private String actIdGroupTYPE;//
	private String[] actIdGroupIDs = new String[]{};//存放ID集合
	private String[] gPUserIds = new String[]{};//存放ID集合
	private String userId;
	
	public String[] getgPUserIds() {
		return gPUserIds;
	}
	public void setgPUserIds(String[] gPUserIds) {
		this.gPUserIds = gPUserIds;
	}
	public String getActIdGroupID() {
		return actIdGroupID;
	}
	public void setActIdGroupID(String actIdGroupID) {
		this.actIdGroupID = actIdGroupID;
	}
	public String getActIdGroupREV() {
		return actIdGroupREV;
	}
	public void setActIdGroupREV(String actIdGroupREV) {
		this.actIdGroupREV = actIdGroupREV;
	}
	public String getActIdGroupNAME() {
		return actIdGroupNAME;
	}
	public void setActIdGroupNAME(String actIdGroupNAME) {
		this.actIdGroupNAME = actIdGroupNAME;
	}
	public String getActIdGroupTYPE() {
		return actIdGroupTYPE;
	}
	public void setActIdGroupTYPE(String actIdGroupTYPE) {
		this.actIdGroupTYPE = actIdGroupTYPE;
	}
	public String[] getActIdGroupIDs() {
		return actIdGroupIDs;
	}
	public void setActIdGroupIDs(String[] actIdGroupIDs) {
		this.actIdGroupIDs = actIdGroupIDs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
