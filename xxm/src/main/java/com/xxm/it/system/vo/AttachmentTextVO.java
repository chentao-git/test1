package com.xxm.it.system.vo;

public class AttachmentTextVO extends CommonVO {
	private static final long serialVersionUID = 1L;// 序列化
	private String attachmentID;// 附件id
	private String attachmentName;// 附件名称
	private String attachmentSysName;// 系统存放名称
	private String attachmentItem;// 模块
	private String attachmentItemID;// 模块id
	private String attachmentType;// 附件类型
	private String attachmentPath;// 存放路径
	private String attachmentStatus;// 状态
	private String[] attachmentIds = new String[] {};// 存放批量attachmentIds

	public String getAttachmentID() {
		return attachmentID;
	}

	public void setAttachmentID(String attachmentID) {
		this.attachmentID = attachmentID;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentSysName() {
		return attachmentSysName;
	}

	public void setAttachmentSysName(String attachmentSysName) {
		this.attachmentSysName = attachmentSysName;
	}

	public String getAttachmentItem() {
		return attachmentItem;
	}

	public void setAttachmentItem(String attachmentItem) {
		this.attachmentItem = attachmentItem;
	}

	public String getAttachmentItemID() {
		return attachmentItemID;
	}

	public void setAttachmentItemID(String attachmentItemID) {
		this.attachmentItemID = attachmentItemID;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String[] getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String[] attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getAttachmentStatus() {
		return attachmentStatus;
	}

	public void setAttachmentStatus(String attachmentStatus) {
		this.attachmentStatus = attachmentStatus;
	}

}
