package com.xxm.it.system.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.dao.ISellDao;
import com.xxm.it.system.service.ISellService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserSellVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class SellServiceImpl implements ISellService {
	
	@Resource
	private ISellDao sellDao;
	
	/**
	 * 查询查询经销商公司列表
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@Override
	public JSONObject findSellList(UserSellVO sellVo) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = sellDao.findSellListCount(sellVo);
		// 查询集合数据
		List<UserSellVO> resultLsit = new ArrayList<UserSellVO>();
		if(total > 0){
			resultLsit = sellDao.findSellList(sellVo);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		
		return jsonObject;
	}
	
	/**
	 * 保存经销商公司列表
	 * 
	 * 
	 */
	@Override
	public Map<String, Object> addSell(UserSellVO sellVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			sellVo.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			sellDao.addSell(sellVo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加汽车经销公司成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加汽车经销公司失败！");
		}
		return resultMap;
	}
	
	/**
	 * 
	 * 根据ID查询汽车经销商信息
	 * 
	 */
	@Override
	public UserSellVO querySellId(UserSellVO sellVo) {
		// TODO Auto-generated method stub
		return sellDao.querySellId(sellVo);
	}
	
	/**
	 * 
	 *修改经销商信息
	 * 
	 */
	@Override
	public Map<String, Object> updateSellInfo(UserSellVO sellVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			sellDao.updateSellInfo(sellVo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "信息修改成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "信息修改失败！");
		}
		return resultMap;
	}

	/**
	 * 
	 * 删除经销商信息
	 * 
	 */
	@Override
	public Map<String, Object> deleteSellInfo(UserSellVO sellVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			sellDao.deleteSellInfo(sellVo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "信息删除成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "信息删除失败！");
		}
		return resultMap;
	}
	
	
}
