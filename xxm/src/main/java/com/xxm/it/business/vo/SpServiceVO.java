package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class SpServiceVO extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String spsId; //服务记录id
	private String spId; //sp商 id
	private String spName; //sp商 名称
	private String spsDate; //服务时间
	private String spsContent; //服务内容
	private String spsResult; //服务结果
	private String spsProgressVL; //处理进度id
	private String spsProgressName; //处理进度name
	private String status; //状态
	private String[] ids = new String[] {};
	public String getSpsId() {
		return spsId;
	}
	public void setSpsId(String spsId) {
		this.spsId = spsId;
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
	public String getSpsDate() {
		return spsDate;
	}
	public void setSpsDate(String spsDate) {
		this.spsDate = spsDate;
	}
	public String getSpsContent() {
		return spsContent;
	}
	public void setSpsContent(String spsContent) {
		this.spsContent = spsContent;
	}
	public String getSpsResult() {
		return spsResult;
	}
	public void setSpsResult(String spsResult) {
		this.spsResult = spsResult;
	}
	public String getSpsProgressVL() {
		return spsProgressVL;
	}
	public void setSpsProgressVL(String spsProgressVL) {
		this.spsProgressVL = spsProgressVL;
	}
	public String getSpsProgressName() {
		return spsProgressName;
	}
	public void setSpsProgressName(String spsProgressName) {
		this.spsProgressName = spsProgressName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
