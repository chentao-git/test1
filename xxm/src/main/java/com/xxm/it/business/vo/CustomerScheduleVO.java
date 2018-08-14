package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class CustomerScheduleVO extends CommonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scheduleId; //进度表id
	private String followUpCategory; //跟进类别
	private String followUpTime; //跟进时间
	private String followUpCategoryName; //跟进类别名称
	private String contentRegistration; //内容登记
	private String baseCustomerId; //客户id
	private String baseCustomerName; //客户名称
	private String status; //状态
	private String[] scheduleIds = new String[] {};//批量删除用于存放id
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getFollowUpCategory() {
		return followUpCategory;
	}
	public void setFollowUpCategory(String followUpCategory) {
		this.followUpCategory = followUpCategory;
	}
	public String getContentRegistration() {
		return contentRegistration;
	}
	public void setContentRegistration(String contentRegistration) {
		this.contentRegistration = contentRegistration;
	}
	public String getBaseCustomerId() {
		return baseCustomerId;
	}
	public void setBaseCustomerId(String baseCustomerId) {
		this.baseCustomerId = baseCustomerId;
	}
	public String getBaseCustomerName() {
		return baseCustomerName;
	}
	public void setBaseCustomerName(String baseCustomerName) {
		this.baseCustomerName = baseCustomerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFollowUpCategoryName() {
		return followUpCategoryName;
	}
	public void setFollowUpCategoryName(String followUpCategoryName) {
		this.followUpCategoryName = followUpCategoryName;
	}
	public String getFollowUpTime() {
		return followUpTime;
	}
	public void setFollowUpTime(String followUpTime) {
		this.followUpTime = followUpTime;
	}
	public String[] getScheduleIds() {
		return scheduleIds;
	}
	public void setScheduleIds(String[] scheduleIds) {
		this.scheduleIds = scheduleIds;
	}
	
	
}
