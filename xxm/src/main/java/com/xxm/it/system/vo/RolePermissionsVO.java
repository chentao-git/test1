package com.xxm.it.system.vo;

/**
 * 角色权限表
 * 
 * @author Administrator
 *
 */
public class RolePermissionsVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String permissionsId;// 主键Id
	private String roleId;// 外键，对应角色Id
	private String menuId;// 外键，对应栏目Id

	public String getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
