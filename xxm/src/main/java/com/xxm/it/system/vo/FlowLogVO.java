package com.xxm.it.system.vo;

public class FlowLogVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String logId;// 日志Id
	private String result;// 结果
	private String reason;// 原因
	private String businessKey;// 业务Id
	private String userId;// 审批人
	private String userName;// 审批人姓名
	private String taskDefinitionKey;// 步骤节点
	private String processingTime;// 审批时间
	private String remark;// 描述

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
