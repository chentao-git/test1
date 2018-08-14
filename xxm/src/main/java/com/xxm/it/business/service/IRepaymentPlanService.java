package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.CollectionVO;
import com.xxm.it.business.vo.RepaymentPlanVO;

import net.sf.json.JSONObject;

@Path(value="/repaymentService")
public interface IRepaymentPlanService {
	/*
	 * 查询列表
	 */
	@POST
	@Path("/findRepaymentPlanList")
	public JSONObject findRepaymentPlanList(RepaymentPlanVO repaymentPlanVO);
	/*
	 * 查询列表
	 */
	@POST
	@Path("/findRepaymentPlans")
	public JSONObject findRepaymentPlans(RepaymentPlanVO repaymentPlanVO);
	
	/**
	 * 根据id查询详情
	 * @param repaymentPlanVO
	 * @return
	 */
	@POST
	@Path("/findRepaymentPlan")
	public RepaymentPlanVO findRepaymentPlan(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 根据贷款合同编号查询详情
	 * @param repaymentPlanVO
	 * @return
	 */
	@POST
	@Path("/findRepaymentPlanss")
	public RepaymentPlanVO findRepaymentPlanss(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 还款更新
	 * @param repaymentPlanVO
	 * @return
	 */
	@POST
	@Path("/updateRepaymentPlan")
	public Map<String, Object> updateRepaymentPlan(RepaymentPlanVO repaymentPlanVO);
	/**
	 * 添加催收记录
	 * @param collectionVO
	 * @return
	 */
	@POST
	@Path("/addCollection")
	public Map<String, Object> addCollection(CollectionVO collectionVO);
	
}
