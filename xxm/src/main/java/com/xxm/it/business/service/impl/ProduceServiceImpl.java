package com.xxm.it.business.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IProduceDao;
import com.xxm.it.business.service.IProduceService;
import com.xxm.it.business.vo.ProduceVO;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class ProduceServiceImpl implements IProduceService {

	private static Logger logger = Logger.getLogger(ProduceServiceImpl.class);
	@Resource
	private IProduceDao produceDao;
	@Resource
	private IUserService userService;
    /**
     * 查询产品信息列表
     */
	@Override
	public JSONObject findProductList(ProduceVO produceVO) {
		JSONObject jsonObject = new JSONObject();
		int total = produceDao.findProduceListCount(produceVO);
		List<ProduceVO> resultList = new ArrayList<ProduceVO>();
		if(total > 0){
			resultList = produceDao.findProduceList(produceVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
    /**
     * 添加产品
     */
	@Override
	public Map<String, Object> addProduce(ProduceVO produceVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			produceVO.setCreateBy(userVO.getUserId());
			produceVO.setLastUpdateBy(userVO.getUserId());
			produceDao.addProduce(produceVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加产品成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加产品失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id去查询信息
	 */
	@Override
	public ProduceVO findProduce(ProduceVO produceVO) {
		ProduceVO result = produceDao.findProduce(produceVO);
		return result;
	}
	/**
	 * 上传合同配置
	 */
	@Override
	public ProduceVO uploadContract(ProduceVO produceVO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
