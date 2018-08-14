package com.xxm.it.business.dao;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ClearingVO;

import net.sf.json.JSONObject;

public interface IClearingDao {
	/**
	 * 结清列表查询
	 * 
	 * @param ClearingVO
	 * @return
	 */
	public List<ClearingVO> findClearingList(ClearingVO clearingVO);
	
	/**
	 * 结清总数
	 * 
	 * @param ClearingVO
	 * @return
	 */
	public int findClearingListCount(ClearingVO clearingVO);
	/**
	 * 添加结清数据
	 * 
	 * @param ClearingVO
	 * @return
	 */
	public void addClearing(ClearingVO clearingVO);
	
}
