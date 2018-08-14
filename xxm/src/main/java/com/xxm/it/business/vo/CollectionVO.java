package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;
/**
 * 催收表
 * @author Administrator
 *
 */
public class CollectionVO extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String collectionId;//催收ID
	private String applyInfoId;//申请ID
	private String collectionMethodVL;//催收方式
	private String collectingTime; //催收时间 
	private String collector; //催收人
	private String contactNumber; //联系电话
	private String contactscontacts; //联系人
	private String doorToDoorPersonnel; //上门人员
	private String doorToDoorAddress; //上门地址
	private String takeSteps; //采取措施
	private String processingResults; //处理结果
	
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getCollectionMethodVL() {
		return collectionMethodVL;
	}
	public void setCollectionMethodVL(String collectionMethodVL) {
		this.collectionMethodVL = collectionMethodVL;
	}
	public String getCollectingTime() {
		return collectingTime;
	}
	public void setCollectingTime(String collectingTime) {
		this.collectingTime = collectingTime;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactscontacts() {
		return contactscontacts;
	}
	public void setContactscontacts(String contactscontacts) {
		this.contactscontacts = contactscontacts;
	}
	public String getDoorToDoorPersonnel() {
		return doorToDoorPersonnel;
	}
	public void setDoorToDoorPersonnel(String doorToDoorPersonnel) {
		this.doorToDoorPersonnel = doorToDoorPersonnel;
	}
	public String getDoorToDoorAddress() {
		return doorToDoorAddress;
	}
	public void setDoorToDoorAddress(String doorToDoorAddress) {
		this.doorToDoorAddress = doorToDoorAddress;
	}
	public String getTakeSteps() {
		return takeSteps;
	}
	public void setTakeSteps(String takeSteps) {
		this.takeSteps = takeSteps;
	}
	public String getProcessingResults() {
		return processingResults;
	}
	public void setProcessingResults(String processingResults) {
		this.processingResults = processingResults;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	
	
}
