package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.MenuVO;

@Path(value = "/menuService")
public interface IMenuService {

	/**
	 * 
	 * 栏目列表查询
	 * 
	 * @param menuVO
	 * @return
	 */
	@POST
	@Path("/findMenuList")
	public List<MenuVO> findMenuList(MenuVO menuVO);

	/**
	 * 
	 * 编辑栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	@POST
	@Path("/addMenu")
	public Map<String, Object> addMenu(MenuVO menuVO);

	/**
	 * 
	 * 编辑栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	@POST
	@Path("/updateMenu")
	public Map<String, Object> updateMenu(MenuVO menuVO);

	/**
	 * 修改批量栏目
	 * 
	 * @param menuVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/updateBatchMenu")
	public Map<String, Object> updateBatchMenu(String menuJson);

	/**
	 * 
	 * 栏目查询
	 * 
	 * @param menuVO
	 * @return
	 */
	@POST
	@Path("/findMenu")
	public MenuVO findMenu(MenuVO menuVO);

	/**
	 * 
	 * 栏目删除
	 * 
	 * @param menuVO
	 * @return
	 */
	@POST
	@Path("/deleteMenu")
	public Map<String, Object> deleteMenu(MenuVO menuVO);

}
