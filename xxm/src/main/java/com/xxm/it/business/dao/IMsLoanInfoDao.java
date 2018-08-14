package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.MsLoanInfoVO;

public interface IMsLoanInfoDao {
	/**
	 * 请求放款列表
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public List<MsLoanInfoVO> findLoanInfoList(MsLoanInfoVO msLoanInfoVO);

	/**
	 * 请求放款列总数
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public int findLoanInfoListCount(MsLoanInfoVO msLoanInfoVO);
	
	/**
	 * 查询请求放款
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public MsLoanInfoVO findLoanInfo(MsLoanInfoVO msLoanInfoVO);
	
	/**
	 * 新增请求放款
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public void addLoanInfo(MsLoanInfoVO msLoanInfoVO);
	
	/**
	 * 修改请求放款
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public void updateLoanInfo(MsLoanInfoVO msLoanInfoVO);
}
