package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class ServiceRecordVO extends CommonVO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceRecordId; //服务记录id
	private String baseCustomerId; //客户id
	private String baseCustomerName; //客户名称
	private String serviceTime; //服务时间
	private String contentRegistration; //内容登记
	private String processingResults; //处理结果
	private String processingProgressVL; //处理进度id
	private String processingProgressName; //处理进度name
	private String status; //状态
	private String createBy;
	private String createDate;
	private String lastUpdateBy;
	private String lastUpdateByName;
	private String lastUpdateDate;
	private String[] ids = new String[] {};
	
	
	public String getServiceRecordId() {
		return serviceRecordId;
	}
	public void setServiceRecordId(String serviceRecordId) {
		this.serviceRecordId = serviceRecordId;
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
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getContentRegistration() {
		return contentRegistration;
	}
	public void setContentRegistration(String contentRegistration) {
		this.contentRegistration = contentRegistration;
	}
	public String getProcessingResults() {
		return processingResults;
	}
	public void setProcessingResults(String processingResults) {
		this.processingResults = processingResults;
	}
	public String getProcessingProgressVL() {
		return processingProgressVL;
	}
	public void setProcessingProgressVL(String processingProgressVL) {
		this.processingProgressVL = processingProgressVL;
	}
	public String getProcessingProgressName() {
		return processingProgressName;
	}
	public void setProcessingProgressName(String processingProgressName) {
		this.processingProgressName = processingProgressName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public String getLastUpdateByName() {
		return lastUpdateByName;
	}
	public void setLastUpdateByName(String lastUpdateByName) {
		this.lastUpdateByName = lastUpdateByName;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	
}
