package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Path(value = "/userService")
public interface IUserService {

	/**
	 * 用户列表查询
	 * 
	 * @return
	 */
	@POST
	@Path("/findUserList")
	public JSONObject findUserList(UserVO userVO);

	/**
	 * 
	 * 编辑用户
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/addUser")
	public Map<String, Object> addUser(UserVO userVo);

	/**
	 * 修改用户
	 * 
	 * @return JSONObject
	 */
	@POST
	@Path("/updateUser")
	public Map<String, Object> updateUser(UserVO userVO);

	/**
	 * 
	 * 用户查询
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/findUser")
	public UserVO findUser(UserVO userVo);

	/**
	 * 
	 * 用户删除
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/deleteUser")
	public Map<String, Object> deleteUser(UserVO userVo);

	/**
	 * 
	 * 登录
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/login")
	public String login(UserVO userVO);

	/**
	 * 
	 * 获取登录用户
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/findLoginUser")
	public UserVO findLoginUser();

	/**
	 * 注销当前用户
	 */
	@POST
	@Path("/quit")
	public void quit();
	/**
	 * 
	 * 用户查询
	 * 
	 * @param userVo
	 * @return
	 */
	@POST
	@Path("/findUsers")
	public List<UserVO> findUsers(UserVO userVO);
}
