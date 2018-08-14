package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;
import com.xxm.it.system.vo.LeaseInfoVO;

/**
 * 拖车对象
 * 
 * @author Administrator
 *
 */
public class TrailerVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String trailerId;//
	private String proposer;//申请人
	private String applyDate;//申请时间
	private String applyRemarks;//申请备注
	private String vehicleItems;//车上物品
	private String storageDate;//入库时间
	private String storagePlace;//入库地点
	private String acceptance;//验收人
	private String isReport;//是否报案
	private String isRelationed;//是否已联系客户
	private String storageRemarks;//入库备注
	private String trailerStatus;//状态
	private String applyInfoId;//申请表id
	private String startDate;//申请开始时间
	private String endDate;//申请结束时间
	private String auditBy;//审核人
	private String auditDate;//审核时间
	private String auditRemarcks;//审核备注
	private String processingRemarcks;//处理备注
	private String processingResults;//处理结果
	//客户对象
	private BaseCustomerVO customerVO=new BaseCustomerVO();
	//申请信息对象
	private ApplyVO applyVO = new ApplyVO();
	//租赁信息对象
	private LeaseInfoVO leaseInfoVO=new LeaseInfoVO();
	//图片上传信息对象
	private TrailerFileVo trailerFileVO = new TrailerFileVo();
	
	public String getTrailerId() {
		return trailerId;
	}
	public void setTrailerId(String trailerId) {
		this.trailerId = trailerId;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyRemarks() {
		return applyRemarks;
	}
	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}
	public String getVehicleItems() {
		return vehicleItems;
	}
	public void setVehicleItems(String vehicleItems) {
		this.vehicleItems = vehicleItems;
	}
	public String getStorageDate() {
		return storageDate;
	}
	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}
	public String getStoragePlace() {
		return storagePlace;
	}
	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	public String getIsReport() {
		return isReport;
	}
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}
	public String getIsRelationed() {
		return isRelationed;
	}
	public void setIsRelationed(String isRelationed) {
		this.isRelationed = isRelationed;
	}
	public String getStorageRemarks() {
		return storageRemarks;
	}
	public void setStorageRemarks(String storageRemarks) {
		this.storageRemarks = storageRemarks;
	}
	public String getTrailerStatus() {
		return trailerStatus;
	}
	public void setTrailerStatus(String trailerStatus) {
		this.trailerStatus = trailerStatus;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public BaseCustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(BaseCustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	public ApplyVO getApplyVO() {
		return applyVO;
	}
	public void setApplyVO(ApplyVO applyVO) {
		this.applyVO = applyVO;
	}
	public LeaseInfoVO getLeaseInfoVO() {
		return leaseInfoVO;
	}
	public void setLeaseInfoVO(LeaseInfoVO leaseInfoVO) {
		this.leaseInfoVO = leaseInfoVO;
	}
	public String getAuditBy() {
		return auditBy;
	}
	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditRemarcks() {
		return auditRemarcks;
	}
	public void setAuditRemarcks(String auditRemarcks) {
		this.auditRemarcks = auditRemarcks;
	}
	public String getProcessingRemarcks() {
		return processingRemarcks;
	}
	public void setProcessingRemarcks(String processingRemarcks) {
		this.processingRemarcks = processingRemarcks;
	}
	public String getProcessingResults() {
		return processingResults;
	}
	public void setProcessingResults(String processingResults) {
		this.processingResults = processingResults;
	}
	public TrailerFileVo getTrailerFileVO() {
		return trailerFileVO;
	}
	public void setTrailerFileVO(TrailerFileVo trailerFileVO) {
		this.trailerFileVO = trailerFileVO;
	}
}
