package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.MsSignInfoVO;

public interface IMsSignInfoDao {
	/**
	 * 签约状态查询列表
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public List<MsSignInfoVO> findSignInfoList(MsSignInfoVO msSignInfoVO);

	/**
	 * 签约状态查询表列总数
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public int findSignInfoListCount(MsSignInfoVO msSignInfoVO);
	
	/**
	 * 新增签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public void addSignInfo(MsSignInfoVO msSignInfoVO);
	
	/**
	 * 签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public MsSignInfoVO findSignInfo(MsSignInfoVO msSignInfoVO);
	
	/**
	 * 签约状态查询（list）
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public List<MsSignInfoVO> findSignInfos(MsSignInfoVO msSignInfoVO);
	
	/**
	 * 修改签约状态查询
	 * 
	 * @param msSignInfoVO
	 * @return
	 */
	public void updateSignInfo(MsSignInfoVO msSignInfoVO);
}
