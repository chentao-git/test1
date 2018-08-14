package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.MsMortgageInfoVO;

public interface IMsMortgageInfoDao {
	/**
	 * 车辆抵押登记信息传输列表
	 * 
	 * @param msMortgageInfoVO
	 * @return
	 */
	public List<MsMortgageInfoVO> findMortgageInfoList(MsMortgageInfoVO msMortgageInfoVO);

	/**
	 * 车辆抵押登记信息传输总数
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public int findMortgageInfoListCount(MsMortgageInfoVO msMortgageInfoVO);
	
	/**
	 * 查询车辆抵押登记信息传输
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public MsMortgageInfoVO findMortgageInfo(MsMortgageInfoVO msMortgageInfoVO);
	
	/**
	 * 添加车辆抵押登记信息传输
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public void addMortgageInfo(MsMortgageInfoVO msMortgageInfoVO);
	
	/**
	 * 修改车辆抵押登记信息传输
	 * 
	 * @param msLoanInfoVO
	 * @return
	 */
	public void updateMortgageInfo(MsMortgageInfoVO msMortgageInfoVO);
}
