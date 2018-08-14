package com.xxm.it.system.vo;

/**
 * 用户角色对象
 * 
 * @author Administrator
 *
 */
public class UserRoleVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String ruId;// 主键
	private String userId;// 用户Id
	private String roleId;// 角色id

	public String getRuId() {
		return ruId;
	}

	public void setRuId(String ruId) {
		this.ruId = ruId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
