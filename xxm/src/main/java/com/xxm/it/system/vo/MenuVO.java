package com.xxm.it.system.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String menuId;// 栏目ID
	private String menuName;// 栏目名称
	private String menuPath;// 路径
	private String parentMenuId;// 上级
	private String parentMenuName;// 上级
	private String menuType;// 栏目类型
	private String disabledFlag;// 状态（失效标记）
	private String menuGrade;// 1：一级菜单；2：二级菜单
	private String userId;// 用户ID
	private String remark;// 备注
	private String sortBy;// 排序字段
	private List<MenuVO> childMenus = new ArrayList<MenuVO>();// 存放下一级菜单

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(String disabledFlag) {
		this.disabledFlag = disabledFlag;
	}

	public String getMenuGrade() {
		return menuGrade;
	}

	public void setMenuGrade(String menuGrade) {
		this.menuGrade = menuGrade;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public List<MenuVO> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuVO> childMenus) {
		this.childMenus = childMenus;
	}

}
