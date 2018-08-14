package com.xxm.it.system.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户对象
 * 
 * @author Administrator
 *
 */
public class UserVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String userId;// 用户id
	private String name;// 姓名
	private String account;// 帐号
	private String password;// 密码
	private String phone;// 电话
	private String sex;// 性别
	private String age;// 年龄
	private String paperType;// 证件类型
	private String paperNo;// 证件编号
	private String type;// 种类
	private String typeName;// 种类名称
	private String status;// 状态，1代表可查，0代表删除
	private String employeeNo;// 工号
	private String[] userIds = new String[] {};// 存放userIds集合
	private List<UserRoleVO> userRoles = new ArrayList<UserRoleVO>();// 用户角色集合
	private List<MenuVO> userMenus = new ArrayList<MenuVO>();// 用户菜单集合

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public List<UserRoleVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleVO> userRoles) {
		this.userRoles = userRoles;
	}

	public List<MenuVO> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<MenuVO> userMenus) {
		this.userMenus = userMenus;
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
