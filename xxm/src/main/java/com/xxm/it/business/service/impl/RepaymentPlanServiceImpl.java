package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IRepaymentPlanDao;
import com.xxm.it.business.service.IRepaymentPlanService;
import com.xxm.it.business.vo.CollectionVO;
import com.xxm.it.business.vo.RepaymentPlanVO;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;
@Component
@Transactional
public class RepaymentPlanServiceImpl implements IRepaymentPlanService{
//	private static Logger logger = Logger.getLogger(RepaymentPlanServiceImpl.class);
	@Resource
	private IUserService userService;
	@Resource
	private IRepaymentPlanDao repaymentDao;
	/*
	 * 查询数据
	 */
	public JSONObject findRepaymentPlanList(RepaymentPlanVO repaymentPlanVO){
		JSONObject jsonObject = new JSONObject();
		repaymentPlanVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		int total =repaymentDao.findRepaymentPlanListCount(repaymentPlanVO);
		List<RepaymentPlanVO> resultList = new ArrayList<RepaymentPlanVO>();
		if(total>0){
			resultList =repaymentDao.findRepaymentPlanList(repaymentPlanVO);
		}
			jsonObject.put("rows", resultList);
			jsonObject.put("total", total);
		
		return jsonObject;
	}
	/*
	 * 查询数据
	 */
	@Override
	public JSONObject findRepaymentPlans(RepaymentPlanVO repaymentPlanVO) {
		JSONObject jsonObject = new JSONObject();
		repaymentPlanVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		int total =repaymentDao.findRepaymentPlanListCount(repaymentPlanVO);
		List<RepaymentPlanVO> resultList = new ArrayList<RepaymentPlanVO>();
		if(total>0){
			resultList =repaymentDao.findRepaymentPlans(repaymentPlanVO);
		}
			jsonObject.put("rows", resultList);
			jsonObject.put("total", total);
		
		return jsonObject;
	}
	/**
	 * 查询详情
	 */
	@Override
	public RepaymentPlanVO findRepaymentPlan(RepaymentPlanVO repaymentPlanVO) {
		repaymentPlanVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		RepaymentPlanVO result = repaymentDao.findRepaymentPlan(repaymentPlanVO);
		return result;
	}
	@Override
	public RepaymentPlanVO findRepaymentPlanss(RepaymentPlanVO repaymentPlanVO) {
		repaymentPlanVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		RepaymentPlanVO result = repaymentDao.findRepaymentPlanss(repaymentPlanVO);
		return result;
	}
	/**
	 * 添加催收记录
	 */
	@Override
	public Map<String, Object> addCollection(CollectionVO collectionVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			collectionVO.setCreateBy(userVO.getUserId());
			collectionVO.setLastUpdateBy(userVO.getUserId());
			repaymentDao.addCollection(collectionVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加催收记录成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加催收记录失败！");
		}
		return resultMap;
	}
	/**
	 * 还款更新
	 */
	@Override
	public Map<String, Object> updateRepaymentPlan(RepaymentPlanVO repaymentPlanVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			repaymentPlanVO.setStatus(BaseDataConstant.STATUS_INVALID);
			repaymentDao.updateRepaymentPlan(repaymentPlanVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "还款成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "还款失败！");
		}
		return resultMap;
	}

	
}
