package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.dao.IClearingDao;
import com.xxm.it.business.service.IClearingService;
import com.xxm.it.business.vo.ClearingVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class ClearingServiceImpl implements IClearingService {
	@Resource
	private IClearingDao clearingDao; //结清列表dao
	@Resource
	private IApplyDao applyDao;//申请信息dao

	/**
	 * 
	 * 结清列表
	 * 
	 * @param ClearingVO
	 * @return
	 */
	public JSONObject findClearingList(ClearingVO clearingVO) {
		JSONObject jsonObject = new JSONObject();
		int total = clearingDao.findClearingListCount(clearingVO);
		List<ClearingVO> resultList = new ArrayList<ClearingVO>();
		if (total > 0) {
			resultList = clearingDao.findClearingList(clearingVO);
		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);

		return jsonObject;
	}
	/**
	 * 添加结清数据
	 * 
	 * @param ClearingVO
	 * @return
	 */
	public Map<String, Object> addClearing(ClearingVO clearingVO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			clearingDao.addClearing(clearingVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加结清数据成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加结清数据失败！");
		}
		return resultMap;
	}
}
