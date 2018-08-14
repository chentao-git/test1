package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.dao.IMenuDao;
import com.xxm.it.system.service.IMenuService;
import com.xxm.it.system.vo.MenuVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@Transactional
public class MenuServiceImpl implements IMenuService {

	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Resource
	private IMenuDao menuDao;

	/**
	 * 
	 * 栏目列表查询
	 * 
	 * @param menuVO
	 * @return
	 */
	public List<MenuVO> findMenuList(MenuVO menuVO) {
		return menuDao.findMenus(menuVO);
	}

	/**
	 * 
	 * 栏目查询（以id获取MenuVO对象）
	 * 
	 * @param menuVO
	 * @return
	 */
	@Override
	public MenuVO findMenu(MenuVO menuVO) {
		return menuDao.findMenu(menuVO);
	}

	/**
	 * 
	 * 添加栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	@Override
	public Map<String, Object> addMenu(MenuVO menuVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 添加栏目
			menuDao.addMenu(menuVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加节点成功！");
			logger.info("添加节点成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加节点失败！");
			logger.info("添加节点失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改栏目
	 * 
	 * @param menuVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> updateMenu(MenuVO menuVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 添加栏目
			menuDao.updateMenu(menuVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改节点成功！");
			logger.info("修改节点成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改节点失败！");
			logger.info("修改节点失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改批量栏目
	 * 
	 * @param menuVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> updateBatchMenu(String menuJson) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 设置批量修改菜单集合
			List<MenuVO> menuList = new ArrayList<MenuVO>();
			if (null != menuJson && !"".equals(menuJson)) {
				// JSON数组对象
				JSONArray jsonArray = JSONArray.fromObject(menuJson);
				// 判断是否为空
				if (null != jsonArray && !jsonArray.isEmpty()) {
					Object[] os = jsonArray.toArray();
					for (int i = 0; i < os.length; i++) {
						// 递归资子元素
						recursionJSONData(menuList, JSONObject.fromObject(os[i]));
					}
				}
				if (menuList.size() > 0) {
					// 循环修改栏目菜单
					for (int j = 0; j < menuList.size(); j++) {
						MenuVO menuVO = menuList.get(j);
						menuVO.setSortBy(j + "");
						menuDao.updateBatchMenu(menuVO);
					}
				}
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "保存栏目成功！");
			logger.info("修改栏目成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "保存栏目失败！");
			logger.info("保存栏目失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 
	 * 栏目删除
	 * 
	 * @param menuVO
	 * @return
	 */
	@Override
	public Map<String, Object> deleteMenu(MenuVO menuVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 删除栏目
			menuDao.deleteMenu(menuVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除节点成功！");
			logger.info("删除节点成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除节点失败！");
			logger.info("删除节点失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 递归循环解析json数据
	 * 
	 * @param menuList
	 * @param jsonObj
	 */
	private void recursionJSONData(List<MenuVO> menuList, JSONObject jsonObj) {
		MenuVO menuVO = new MenuVO();
		// 栏目Id
		menuVO.setMenuId(jsonObj.get("menuId").toString());
		// 栏目父Id
		menuVO.setParentMenuId(jsonObj.get("parentMenuId").toString());
		menuList.add(menuVO);
		// 判断是否含有下一级栏目
		if (null != jsonObj.get("children")) {
			JSONArray obj = JSONArray.fromObject(jsonObj.get("children"));
			if (null != obj && !obj.isEmpty()) {
				Object[] os = obj.toArray();
				for (int i = 0; i < os.length; i++) {
					// 调用自身方法
					recursionJSONData(menuList, JSONObject.fromObject(os[i]));
				}
			}
		}
	}

}
