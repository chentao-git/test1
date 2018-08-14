package com.xxm.it.system.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.xxm.it.system.util.XxmUser;

/**
 * 公共VO对象
 * 
 * @author Administrator
 *
 */
@XmlAccessorType(value = XmlAccessType.FIELD) // 访问类型改为字段
public class CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlTransient
	private int limit;// 分页数大小
	@XmlTransient
	private int offset;// 分页页码
	@XmlTransient
	private String order;// 排序
	@XmlTransient
	private String createDate; // 创建时间
	@XmlTransient
	private String createBy; // 创建人Id
	@XmlTransient
	private String lastUpdateDate; // 最后更新时间
	@XmlTransient
	private String lastUpdateBy; // 最后更新人Id
	@XmlTransient
	private String createByName; // 创建人名称
	@XmlTransient
	private String lastUpdateByName; // 最后更新人名称
	@XmlTransient
	private String extendField1;// 扩展字段
	@XmlTransient
	private String extendField2;// 扩展字段
	@XmlTransient
	private String extendField3;// 扩展字段
	@XmlTransient
	private String extendField4;// 扩展字段

	// 流程处理属性
	@XmlTransient
	private String processInstanceId;
	@XmlTransient
	private String processDefinitionId;
	@XmlTransient
	private boolean isSuspended;
	@XmlTransient
	private int version;
	@XmlTransient
	private String taskId;
	@XmlTransient
	private String[] taskIds;
	@XmlTransient
	private String taskName;
	@XmlTransient
	private String taskCreateTime;
	@XmlTransient
	private String taskAssignee;
	@XmlTransient
	private String taskAssigneeName;
	@XmlTransient
	private String taskDefinitionKey;
	@XmlTransient
	private String historicProcessStartTime;
	@XmlTransient
	private String historicProcessEndTime;
	@XmlTransient
	private String historicProcessDeleteReason;
	@XmlTransient
	private String keys;
	@XmlTransient
	private String values;
	@XmlTransient
	private String types;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		if (null == this.createBy || "".equals(this.createBy)) {
			this.createBy = XxmUser.getCurrentUserId();
		}
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		if (null == this.lastUpdateBy || "".equals(this.lastUpdateBy)) {
			this.lastUpdateBy = XxmUser.getCurrentUserId();
		}
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getLastUpdateByName() {
		return lastUpdateByName;
	}

	public void setLastUpdateByName(String lastUpdateByName) {
		this.lastUpdateByName = lastUpdateByName;
	}

	public String getExtendField1() {
		return extendField1;
	}

	public void setExtendField1(String extendField1) {
		this.extendField1 = extendField1;
	}

	public String getExtendField2() {
		return extendField2;
	}

	public void setExtendField2(String extendField2) {
		this.extendField2 = extendField2;
	}

	public String getExtendField3() {
		return extendField3;
	}

	public void setExtendField3(String extendField3) {
		this.extendField3 = extendField3;
	}

	public String getExtendField4() {
		return extendField4;
	}

	public void setExtendField4(String extendField4) {
		this.extendField4 = extendField4;
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

	public String getTaskAssigneeName() {
		return taskAssigneeName;
	}

	public void setTaskAssigneeName(String taskAssigneeName) {
		this.taskAssigneeName = taskAssigneeName;
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

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
