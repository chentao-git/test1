package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.dao.IRoleDao;
import com.xxm.it.system.service.IRoleService;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.util.XxmUtils;
import com.xxm.it.system.vo.RolePermissionsVO;
import com.xxm.it.system.vo.RoleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@Transactional
public class RoleServiceImpl implements IRoleService {

	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Resource
	private IUserService userService;

	@Resource
	private IRoleDao roleDao;

	/**
	 * 角色列表查询
	 * 
	 * @return
	 */
	public JSONObject findRoleList(RoleVO roleVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = roleDao.findRoleListCount(roleVO);
		// 查询集合数据
		List<RoleVO> resultLsit = new ArrayList<RoleVO>();
		if (total > 0) {
			resultLsit = roleDao.findRoleList(roleVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 添加角色
	 */
	@Override
	public Map<String, Object> addRole(RoleVO roleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 设置角色状态
			roleVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			// 添加角色
			roleDao.addRole(roleVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加角色成功！");
			logger.info("添加角色成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加角色失败！");
			logger.info("添加角色失败！" + e.getMessage());
		}

		return resultMap;
	}

	/**
	 * 修改角色
	 */
	public Map<String, Object> updateRole(RoleVO roleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改角色
			if (roleVO.getRoleId() != null && !"".equals(roleVO.getRoleId())) {
				roleDao.updateRole(roleVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "修改角色成功！");
				logger.info("修改角色成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改角色失败！");
			logger.info("修改角色失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询角色详情
	 */
	@Override
	public RoleVO findRole(RoleVO roleVO) {
		RoleVO resultUser = roleDao.findRole(roleVO);
		// 查询用户角色信息
		RolePermissionsVO rpVO = new RolePermissionsVO();
		rpVO.setRoleId(roleVO.getRoleId());
		List<RolePermissionsVO> rpVOList = roleDao.findRolePermissionsList(rpVO);
		resultUser.setRpVOList(rpVOList);
		return resultUser;
	}

	/**
	 * 删除角色
	 */
	@Override
	public Map<String, Object> deleteRole(RoleVO roleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改角色状态
			roleVO.setStatus(BaseDataConstant.STATUS_INVALID);
			roleDao.deleteRole(roleVO);
			resultMap.put("result", Boolean.TRUE);
			logger.info("删除角色成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除角色失败！");
			logger.info("删除角色失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 角色授权
	 * 
	 * @return resultMap 返回参数
	 */
	public Map<String, Object> updateRoleAuth(RoleVO roleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String menuJson = roleVO.getMenuNodes();
			// 批量菜单权限集合
			List<RolePermissionsVO> menuIdList = new ArrayList<RolePermissionsVO>();
			if (null != menuJson && !"".equals(menuJson)) {
				// JSON数组对象
				JSONArray jsonArray = JSONArray.fromObject(menuJson);
				// 判断是否为空
				if (null != jsonArray && !jsonArray.isEmpty()) {
					Object[] os = jsonArray.toArray();
					for (int i = 0; i < os.length; i++) {
						JSONObject jsonObj = JSONObject.fromObject(os[i]);
						RolePermissionsVO rpVO = new RolePermissionsVO();
						String menuId = jsonObj.get("menuId").toString();
						// 排除根目录
						if (!"0".equals(menuId)) {
							rpVO.setMenuId(menuId);// 栏目Id
							rpVO.setRoleId(roleVO.getRoleId());// 角色Id
							menuIdList.add(rpVO);
						}
					}
				}
				// 删除角色权限
				RolePermissionsVO delRolePermVO = new RolePermissionsVO();
				delRolePermVO.setRoleId(roleVO.getRoleId());
				roleDao.deleteRolePermissions(delRolePermVO);
				// 添加角色权限
				if (menuIdList.size() > 0) {
					for (int j = 0; j < menuIdList.size(); j++) {
						roleDao.addRolePermissions(menuIdList.get(j));
					}
				}
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "角色授权成功！");
			logger.info("角色授权成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "角色授权失败！");
			logger.info("角色授权失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询有效角色集合
	 */
	public List<RoleVO> findRoles(RoleVO roleVO) {
		// 设置有效时间
		roleVO.setStartValidDate(XxmUtils.getCurrentDate(BaseDataConstant.FORMATE_DATA2));
		// 设置角色状态
		roleVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		List<RoleVO> resultList = roleDao.findRoles(roleVO);
		return resultList;
	}

}
