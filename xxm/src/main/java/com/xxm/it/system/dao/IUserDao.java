package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.MenuVO;
import com.xxm.it.system.vo.UserRoleVO;
import com.xxm.it.system.vo.UserVO;

public interface IUserDao {

	/**
	 * 查询用户列表
	 * 
	 * @param userVO
	 * @return
	 */
	public List<UserVO> findUserList(UserVO userVO);

	/**
	 * 查询用户列表总数
	 * 
	 * @param userVO
	 * @return
	 */
	public int findUserListCount(UserVO userVO);

	/**
	 * 添加用户
	 * 
	 * @param userVO
	 * @return
	 */
	public void addUser(UserVO userVO);

	/**
	 * 查询用户
	 * 
	 * @param userVO
	 * @return
	 */
	public UserVO findUser(UserVO userVO);

	/**
	 * 修改用户
	 * 
	 * @param userVo
	 * @return
	 */
	public void updateUser(UserVO userVO);

	/**
	 * 删除用户
	 * 
	 * @param userIds
	 * @return
	 */
	public void deleteUser(UserVO userVO);

	/**
	 * 查询用户
	 * 
	 * @param userVo
	 * @return
	 */
	public List<UserVO> findUsers(UserVO userVO);

	/**
	 * 查询用户角色信息
	 * 
	 * @param userVO
	 * @return
	 */
	public List<UserRoleVO> findUserRoleList(UserRoleVO userRoleVO);

	/**
	 * 增加用户角色信息
	 * 
	 * @param userRoleVO
	 */
	public void addUserRole(UserRoleVO userRoleVO);

	/**
	 * 删除用户角色信息
	 * 
	 * @param userRoleVO
	 */
	public void deleteUserRole(UserRoleVO userRoleVO);

	/**
	 * 查询用户所有菜单权限信息
	 * 
	 * @param userVO
	 * @return
	 */
	public List<MenuVO> findUserPermissionsList(UserVO userVO);
	/**
	 * 
	 * 添加用户
	 * 
	 * @param userVo
	 * @return
	 */
	public void addUsers(UserVO userVo);
}
