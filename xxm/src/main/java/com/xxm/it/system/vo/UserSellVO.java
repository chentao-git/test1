package com.xxm.it.system.vo;

/**
 * 汽车经销商对象
 * 
 * @author Administrator
 *
 */

public class UserSellVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sellId; // 汽车经销商ID
	private String sellName;// 名称
	private String companyName;// 公司名称
	private String workingGroup;// 经销类型
	private String phone;// 联系电话
	private String address;// 地址
	private String status;// 状态
	private String createDate;//创建时间
	private String lastUpdateDate;//更新时间
	private String[] sellIds = new String[] {};// 存放roleIds集合
	public String getSellId() {
		return sellId;
	}
	public void setSellId(String sellId) {
		this.sellId = sellId;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkingGroup() {
		return workingGroup;
	}
	public void setWorkingGroup(String workingGroup) {
		this.workingGroup = workingGroup;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String[] getSellIds() {
		return sellIds;
	}
	public void setSellIds(String[] sellIds) {
		this.sellIds = sellIds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}