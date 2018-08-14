package com.xxm.it.business.service;


import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ActIdGroupVO;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Path(value="/actIdGroupService")
public interface IActIdGroupService {
	/*
	 * 查询数据
	 */
	@POST
	@Path("/findActIdGroupList")
	public JSONObject findActIdGroupList(ActIdGroupVO actIdGroupVO);
	/*
	 * 查询数据
	 */
	@POST
	@Path("/findGroupUserList")
	public JSONObject findGroupUserList(ActIdGroupVO actIdGroupVO);
	/*
	 * 增加数据
	 */
	@POST
	@Path("/addActIdGroup")
	public Map<String,Object> addActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 查询数据资料
	 */
	@POST
	@Path("/findActIdGroup")
	public ActIdGroupVO findActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 修改数据
	 */
	@POST
	@Path("/updateActIdGroup")
	public Map<String,Object> updateActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 删除数据
	 */
	@POST
	@Path("/deleteActIdGroup")
	public Map<String,Object> deleteActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 增加数据
	 */
	@POST
	@Path("/addMembership")
	public Map<String,Object> addMembership(ActIdGroupVO actIdGroupVO);
}
