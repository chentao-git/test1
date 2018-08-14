package com.xxm.it.system.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象
 * 
 * @author Administrator
 *
 */
public class RoleVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String roleId;// 角色ID
	private String roleName;// 角色名称
	private String validDate;// 角色有效期限
	private String startValidDate;// 开始
	private String endValidDate;// 结束
	private String status;// 状态
	private String remark;// 备注
	private String type;// 种类
	private String typeName;// 种类名称
	private String menuNodes;// 存放菜单json字符数据
	private String[] roleIds = new String[] {};// 存放roleIds集合
	private List<RolePermissionsVO> rpVOList = new ArrayList<RolePermissionsVO>();// 存放权限信息信息

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getStartValidDate() {
		return startValidDate;
	}

	public void setStartValidDate(String startValidDate) {
		this.startValidDate = startValidDate;
	}

	public String getEndValidDate() {
		return endValidDate;
	}

	public void setEndValidDate(String endValidDate) {
		this.endValidDate = endValidDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenuNodes() {
		return menuNodes;
	}

	public void setMenuNodes(String menuNodes) {
		this.menuNodes = menuNodes;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<RolePermissionsVO> getRpVOList() {
		return rpVOList;
	}

	public void setRpVOList(List<RolePermissionsVO> rpVOList) {
		this.rpVOList = rpVOList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
