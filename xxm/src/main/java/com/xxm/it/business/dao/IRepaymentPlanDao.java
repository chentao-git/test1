package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.CollectionVO;
import com.xxm.it.business.vo.RepaymentPlanVO;

public interface IRepaymentPlanDao {
	/*
	 * 查询列表
	 */
	public List<RepaymentPlanVO> findRepaymentPlanList(RepaymentPlanVO repaymentPlanVO);

	/*
	 * 查询信息
	 */
	public RepaymentPlanVO findRepaymentPlan(RepaymentPlanVO repaymentPlanVO);

	/*
	 * 查询总数
	 */
	public int findRepaymentPlanListCount(RepaymentPlanVO repaymentPlanVO);
	
	public List<RepaymentPlanVO> findRepaymentPlans(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 查询信息
	 * @param repaymentPlanVO
	 * @return
	 */
	public RepaymentPlanVO findRepaymentPlanss(RepaymentPlanVO repaymentPlanVO);
	
	/**
	 * 还款更新
	 * @param repaymentPlanVO
	 */
	public void updateRepaymentPlan(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 添加催收记录
	 * @param collectionVO
	 */
	public void addCollection(CollectionVO collectionVO);

}
