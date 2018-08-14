package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.RolePermissionsVO;
import com.xxm.it.system.vo.RoleVO;

public interface IRoleDao {

	/**
	 * 查询角色列表
	 * 
	 * @param roleVO
	 * @return
	 */
	public List<RoleVO> findRoleList(RoleVO roleVO);

	/**
	 * 查询角色列表总数
	 * 
	 * @param roleVO
	 * @return
	 */
	public int findRoleListCount(RoleVO roleVO);

	/**
	 * 添加角色
	 * 
	 * @param userVo
	 * @return
	 */
	public void addRole(RoleVO roleVO);

	/**
	 * 查询角色
	 * 
	 * @param userVo
	 * @return
	 */
	public RoleVO findRole(RoleVO roleVO);

	/**
	 * 修改角色
	 * 
	 * @param userVo
	 * @return
	 */
	public void updateRole(RoleVO roleVO);

	/**
	 * 删除角色
	 * 
	 * @param userVo
	 * @return
	 */
	public void deleteRole(RoleVO roleVO);

	/**
	 * 查询角色
	 * 
	 * @param userVo
	 * @return
	 */
	public List<RoleVO> findRoles(RoleVO roleVO);

	/**
	 * 查询角色权限
	 * 
	 * @param rpVO
	 * @return
	 */
	public List<RolePermissionsVO> findRolePermissionsList(RolePermissionsVO rpVO);

	/**
	 * 添加角色权限
	 * 
	 * @param rpVO
	 */
	public void addRolePermissions(RolePermissionsVO rpVO);

	/**
	 * 删除角色权限
	 * 
	 * @param rpVO
	 */
	public void deleteRolePermissions(RolePermissionsVO rpVO);

}
