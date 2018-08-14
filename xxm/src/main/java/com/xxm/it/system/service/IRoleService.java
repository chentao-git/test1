package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.RoleVO;

import net.sf.json.JSONObject;

@Path(value = "/roleService")
public interface IRoleService {

	/**
	 * 
	 * 角色列表查询
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/findRoleList")
	public JSONObject findRoleList(RoleVO roleVO);

	/**
	 * 
	 * 新增角色
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/addRole")
	public Map<String, Object> addRole(RoleVO roleVO);

	/**
	 * 
	 * 编辑角色
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/updateRole")
	public Map<String, Object> updateRole(RoleVO roleVO);

	/**
	 * 
	 * 角色查询
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/findRole")
	public RoleVO findRole(RoleVO roleVO);

	/**
	 * 
	 * 角色删除
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/deleteRole")
	public Map<String, Object> deleteRole(RoleVO roleVO);

	/**
	 * 角色授权
	 * 
	 * @return resultMap 返回参数
	 */
	@POST
	@Path("/updateRoleAuth")
	public Map<String, Object> updateRoleAuth(RoleVO roleVO);

	/**
	 * 
	 * 角色搜索
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/findRoles")
	public List<RoleVO> findRoles(RoleVO roleVO);

}
