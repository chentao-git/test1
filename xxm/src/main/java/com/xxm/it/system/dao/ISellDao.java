package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.UserSellVO;

public interface ISellDao {
	
	/**
	 * 查询经销商列表
	 * 
	 * @param sellVO
	 * @return
	 */
	public List<UserSellVO> findSellList(UserSellVO sellVO);
	
	/**
	 * 查询经销商列表总数
	 * 
	 * @param sellVO
	 * @return
	 */
	public int findSellListCount(UserSellVO sellVo);
	/**
	 * 查询经销商列表总数
	 * 
	 * @param sellVO
	 * @return
	 */
	public void addSell(UserSellVO sellVo);
	
	/**
	 * 查询经销商类型分类
	 * 
	 * @param sellVO
	 * @return
	 */
	public UserSellVO findeInfo(UserSellVO sellVo);
	
	/**
	 * 根据ID查询经销商详细信息
	 * 
	 * @param sellVO
	 * @return
	 */
	public UserSellVO querySellId(UserSellVO sellVo);
		
	/**
	 * 根据ID修改信息
	 * 
	 * @param sellVO
	 * @return
	 */
	public void updateSellInfo(UserSellVO sellVo);
	
	/**
	 * 删除经销商信息
	 * 
	 * @param sellVO
	 * @return
	 */
	public void deleteSellInfo(UserSellVO sellVo);
	
	
}
