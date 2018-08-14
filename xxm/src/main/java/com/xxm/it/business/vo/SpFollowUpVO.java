package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class SpFollowUpVO extends CommonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folowId; //进度表id
	private String followUpTypeVL; //跟进类别
	private String followUpDate; //跟进时间
	private String followUpTypeName; //跟进类别名称
	private String followUpContent; //跟进内容
	private String spId; //sp商 id
	private String spName; //sp商名称
	private String status; //状态
	private String[] followUpIds = new String[] {};//批量删除用于存放id
	public String getFolowId() {
		return folowId;
	}
	public void setFolowId(String folowId) {
		this.folowId = folowId;
	}
	public String getFollowUpTypeVL() {
		return followUpTypeVL;
	}
	public void setFollowUpTypeVL(String followUpTypeVL) {
		this.followUpTypeVL = followUpTypeVL;
	}
	public String getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}
	public String getFollowUpTypeName() {
		return followUpTypeName;
	}
	public void setFollowUpTypeName(String followUpTypeName) {
		this.followUpTypeName = followUpTypeName;
	}
	public String getFollowUpContent() {
		return followUpContent;
	}
	public void setFollowUpContent(String followUpContent) {
		this.followUpContent = followUpContent;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getFollowUpIds() {
		return followUpIds;
	}
	public void setFollowUpIds(String[] followUpIds) {
		this.followUpIds = followUpIds;
	}
	
	
}
