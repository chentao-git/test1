package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class TrailerFileVo extends CommonVO{

	private static final long serialVersionUID = 1L;
	
	private String trailerFileId;//投保委托文件ID
	private String trailerId;//拖车拖回ID
	private String fileId;//上传文件ID
	private String fileName;//文件名称
	private String fileURL;//文件地址
	private String uploadName;//上传人
	private String uploadDate;//上传时间
	private String [] fileIds = new String [] {};
	private String [] fileURLs = new String [] {};
	private String [] fileNames = new String[] {};
	public String getTrailerFileId() {
		return trailerFileId;
	}
	public void setTrailerFileId(String trailerFileId) {
		this.trailerFileId = trailerFileId;
	}
	public String getTrailerId() {
		return trailerId;
	}
	public void setTrailerId(String trailerId) {
		this.trailerId = trailerId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String[] getFileIds() {
		return fileIds;
	}
	public void setFileIds(String[] fileIds) {
		this.fileIds = fileIds;
	}
	public String[] getFileURLs() {
		return fileURLs;
	}
	public void setFileURLs(String[] fileURLs) {
		this.fileURLs = fileURLs;
	}
	public String[] getFileNames() {
		return fileNames;
	}
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}
	
}
