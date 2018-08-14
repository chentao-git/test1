package com.xxm.it.activiti.vo;

import java.io.Serializable;

/**
 * Entity: Leave
 *
 * @author HenryYan
 */
public class Leave extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String startTime;
	private String endTime;
	private String realityStartTime;
	private String realityEndTime;
	private String applyTime;
	private String leaveType;
	private String reason;

	// -- 临时属性 --//
	// // 流程任务
	// private Task task;
	//
	// private Map<String, Object> variables;
	//
	// // 运行中的流程实例
	// private ProcessInstance processInstance;
	//
	// // 历史的流程实例
	// private HistoricProcessInstance historicProcessInstance;
	//
	// // 流程定义
	// private ProcessDefinition processDefinition;

	private String processInstanceId;
	private String processDefinitionId;
	private boolean isSuspended;
	private int version;
	private String taskId;
	private String[] taskIds;
	private String taskName;
	private String taskCreateTime;
	private String taskAssignee;
	private String taskDefinitionKey;
	private String historicProcessStartTime;
	private String historicProcessEndTime;
	private String historicProcessDeleteReason;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(String realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	public String getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(String realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String[] getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String[] taskIds) {
		this.taskIds = taskIds;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCreateTime() {
		return taskCreateTime;
	}

	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public String getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getHistoricProcessStartTime() {
		return historicProcessStartTime;
	}

	public void setHistoricProcessStartTime(String historicProcessStartTime) {
		this.historicProcessStartTime = historicProcessStartTime;
	}

	public String getHistoricProcessEndTime() {
		return historicProcessEndTime;
	}

	public void setHistoricProcessEndTime(String historicProcessEndTime) {
		this.historicProcessEndTime = historicProcessEndTime;
	}

	public String getHistoricProcessDeleteReason() {
		return historicProcessDeleteReason;
	}

	public void setHistoricProcessDeleteReason(String historicProcessDeleteReason) {
		this.historicProcessDeleteReason = historicProcessDeleteReason;
	}

}
