package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.dao.IUserDao;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.MenuVO;
import com.xxm.it.system.vo.UserRoleVO;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class UserServiceImpl implements IUserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Resource
	private IUserDao userDao;

	/**
	 * 获取reques对象
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 用户列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findUserList(UserVO userVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = userDao.findUserListCount(userVO);
		// 查询集合数据
		List<UserVO> resultLsit = new ArrayList<UserVO>();
		if (total > 0) {
			resultLsit = userDao.findUserList(userVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 添加用户
	 * 
	 * @param userVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addUser(UserVO userVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 设置用户状态
			userVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			// 添加用户
			userDao.addUser(userVO);
			userDao.addUsers(userVO);
			// 增加用户角色
			List<UserRoleVO> userRoles = userVO.getUserRoles();
			if (null != userRoles && userRoles.size() > 0) {
				for (int i = 0; i < userRoles.size(); i++) {
					UserRoleVO userRoleVO = new UserRoleVO();
					userRoleVO.setUserId(userVO.getUserId());
					userRoleVO.setRoleId(userRoles.get(i).getRoleId());
					userDao.addUserRole(userRoleVO);
				}
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加用户成功！");
			logger.info("添加用户成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加用户失败！");
			logger.info("添加用户失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改用户
	 * 
	 * @param userVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> updateUser(UserVO userVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改用户
			userDao.updateUser(userVO);
			// 删除用户角色
			UserRoleVO delUserRoleVO = new UserRoleVO();
			delUserRoleVO.setUserId(userVO.getUserId());
			userDao.deleteUserRole(delUserRoleVO);
			// 增加用户角色
			List<UserRoleVO> userRoles = userVO.getUserRoles();
			if (null != userRoles && userRoles.size() > 0) {
				for (int i = 0; i < userRoles.size(); i++) {
					UserRoleVO userRoleVO = new UserRoleVO();
					userRoleVO.setUserId(userVO.getUserId());
					userRoleVO.setRoleId(userRoles.get(i).getRoleId());
					userDao.addUserRole(userRoleVO);
				}
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改用户成功！");
			logger.info("修改用户成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改用户失败！");
			logger.info("修改用户失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userVO
	 *            参数
	 * 
	 * @return resultUser 返回结果
	 */
	public UserVO findUser(UserVO userVO) {
		// 查询用户信息
		UserVO resultUser = userDao.findUser(userVO);
		// 查询用户角色信息
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setUserId(userVO.getUserId());
		List<UserRoleVO> userRoleList = userDao.findUserRoleList(userRoleVO);
		resultUser.setUserRoles(userRoleList);
		return resultUser;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param userVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> deleteUser(UserVO userVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改用户
			userVO.setStatus(BaseDataConstant.STATUS_INVALID);
			userDao.deleteUser(userVO);
			resultMap.put("result", Boolean.TRUE);
			logger.info("删除用户成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除用户失败！");
			logger.info("删除用户失败！" + e.getMessage());
		}
		return resultMap;
	}

	@Override
	public String login(UserVO userVO) {
		// 返回消息对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		// 查询用户详细信息
		UserVO userInfo = userDao.findUser(userVO);
		if (null != userInfo) {
			// 查询用户角色信息
			UserRoleVO userRoleVO = new UserRoleVO();
			userRoleVO.setUserId(userInfo.getUserId());
			List<UserRoleVO> userRoleList = userDao.findUserRoleList(userRoleVO);
			// 判断用户是否已经授权
			if (null != userRoleList && userRoleList.size() > 0) {
				// 查询用户权限信息
				List<MenuVO> userMenus = installMenuData(userDao.findUserPermissionsList(userInfo));
				userInfo.setUserMenus(userMenus);
				// 用户对象保存在回话中
				request.getSession().setAttribute("user", userInfo);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("userVO", userInfo);
			} else {
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "登录失败，当前用户未开通权限！");
			}
		} else {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "登录失败，请输入正确的帐号与密码！");
		}
		return JSONObject.fromObject(resultMap).toString();
	}

	/**
	 * 查询当前登陆用户信息
	 * 
	 * @param userVO
	 * @return
	 */
	@Override
	public UserVO findLoginUser() {
		// 声明用户对象
		UserVO userVO = null;
		Object obj = request.getSession().getAttribute("user");
		if (null != obj) {
			userVO = (UserVO) obj;
		}
		return userVO;
	}

	/**
	 * 重新组装菜单数据
	 * 
	 * @param userMenus
	 * @return
	 */
	private List<MenuVO> installMenuData(List<MenuVO> userMenus) {
		List<MenuVO> menuList = new ArrayList<MenuVO>();
		if (null != userMenus && userMenus.size() > 0) {
			for (int i = 0; i < userMenus.size(); i++) {
				MenuVO menuVO = userMenus.get(i);
				if ("0".equals(menuVO.getParentMenuId())) {
					installRecursionData(menuVO, userMenus);
					menuList.add(menuVO);
				}
			}
		}
		return menuList;
	}

	/**
	 * 递归，判断当前节点下面是否含有子节点
	 * 
	 * @param menuVO
	 * @param userMenus
	 */
	private void installRecursionData(MenuVO menuVO, List<MenuVO> userMenus) {
		List<MenuVO> childMenuList = new ArrayList<MenuVO>();
		for (int i = 0; i < userMenus.size(); i++) {
			MenuVO childMenuVO = userMenus.get(i);
			if (menuVO.getMenuId().equals(childMenuVO.getParentMenuId())) {
				installRecursionData(childMenuVO, userMenus);
				childMenuList.add(childMenuVO);
			}
		}
		menuVO.setChildMenus(childMenuList);
	}

	/**
	 * 注销当前用户
	 */
	public void quit() {
		logger.info("退出登录.");
		request.getSession().setAttribute("user", null);
	}
	public List<UserVO> findUsers(UserVO userVO) {
		// 查询集合数据
		List<UserVO> resultList = new ArrayList<UserVO>();
		resultList = userDao.findUsers(userVO);
		return resultList;
	}


}
