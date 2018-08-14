package com.xxm.it.business.dao;


import java.util.List;

import com.xxm.it.business.vo.ProduceVO;

public interface IProduceDao {
	
	/**
	 * 查询产品列表
	 * 
	 * @param produceVO
	 * @return
	 */
	public List<ProduceVO> findProduceList(ProduceVO produceVO);
	
	/**
	 * 查询产品列表总数
	 * 
	 * @param produceVO
	 * @return
	 */
	public int findProduceListCount(ProduceVO produceVO);
	
	/**
	 * 添加产品
	 * 
	 * @param produceVO
	 * @return
	 */
	public void addProduce(ProduceVO produceVO);
	
	/**
	 * 查询产品
	 * 
	 * @param produceVO
	 * @return
	 */
	public ProduceVO findProduce(ProduceVO produceVO);
	
	/**
	 * 修改产品
	 * 
	 * @param produceVO
	 * @return
	 */
	public void updateProduce(ProduceVO produceVO);
	
}
