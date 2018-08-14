package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.MenuVO;

public interface IMenuDao {

	/**
	 * 添加栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public void addMenu(MenuVO menuVO);

	/**
	 * 查询栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public MenuVO findMenu(MenuVO menuVO);

	/**
	 * 修改栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public void updateMenu(MenuVO menuVO);

	/**
	 * 批量修改菜单的位置
	 * 
	 * @param menuVO
	 */
	public void updateBatchMenu(MenuVO menuVO);

	/**
	 * 删除栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public void deleteMenu(MenuVO menuVO);

	/**
	 * 根据用户查询栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public List<MenuVO> findUserMenuOne(MenuVO menuVO);

	/**
	 * 查询栏目
	 * 
	 * @param menuVO
	 * @return
	 */
	public List<MenuVO> findMenus(MenuVO menuVO);
}
