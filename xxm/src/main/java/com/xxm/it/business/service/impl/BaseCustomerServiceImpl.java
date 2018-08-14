package com.xxm.it.business.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IBaseCustomerDao;
import com.xxm.it.business.service.IBaseCustomerService;
import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class BaseCustomerServiceImpl implements IBaseCustomerService {

	private static Logger logger = Logger.getLogger(BaseCustomerServiceImpl.class);

	@Resource
	private IBaseCustomerDao baseCustomerDao;
	
	@Resource
	private IUserService userService;
	
	/**
	 * 添加客户信息
	 * 
	 * @param baseCustomerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public BaseCustomerVO addBaseCustomer(BaseCustomerVO baseCustomerVO) {
		try {
			UserVO userVO=userService.findLoginUser();
			if(userVO!=null){
				baseCustomerVO.setCreateBy(userVO.getUserId());
				baseCustomerVO.setLastUpdateBy(userVO.getUserId());
				baseCustomerDao.addBaseCustomer(baseCustomerVO);
				baseCustomerVO.setBaseCustomerId(baseCustomerVO.getBaseCustomerId());
				baseCustomerDao.addCustomer(baseCustomerVO);
				logger.info("添加客户成功！");
			}
		} catch (Exception e) {
			logger.info("添加客户失败！" + e.getMessage());
		}
		return baseCustomerVO;
	}
	
	/**
	 * 修改客户信息
	 * 
	 * @param baseCustomerVO
	 *            参数
	 * @return 
	 */
	public BaseCustomerVO updateBaseCustomer(BaseCustomerVO baseCustomerVO) {
		try {
			UserVO userVO=userService.findLoginUser();
			if(userVO!=null&&baseCustomerVO.getCustomerId()!=null&&!"".equals(baseCustomerVO.getCustomerId())){
				baseCustomerVO.setLastUpdateBy(userVO.getUserId());
				baseCustomerDao.updateCustomer(baseCustomerVO);
				BaseCustomerVO baseVO=baseCustomerDao.findCustomer(baseCustomerVO);
				if(baseVO!=null&&baseVO.getBaseCustomerId()!=null&&!"".equals(baseVO.getBaseCustomerId())){
					baseCustomerVO.setBaseCustomerId(baseVO.getBaseCustomerId());
					baseCustomerDao.updateBaseCustomer(baseCustomerVO);
				}
				baseCustomerVO.setExtendField2("success");
				logger.info("修改客户成功！");
			}
		} catch (Exception e) {
			logger.info("修改客户失败！" + e.getMessage());
		}
		return baseCustomerVO;
	}
	/**
	 * 修改基础客户信息
	 */
	@Override
	public Map<String, Object> updateBaseCustomerInfo(BaseCustomerVO baseCustomerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(baseCustomerVO.getBaseCustomerId() !=null&&!"".equals(baseCustomerVO.getBaseCustomerId())){
				baseCustomerDao.updateBaseCustomer(baseCustomerVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "修改客户信息成功！");
			}else{
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "修改客户信息失败！");
			}
			
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改客户信息失败！");
		}
		return resultMap;
	}
	
	
    /**
     * 查询客服基础信息列表
     */
	@Override
	public JSONObject findBaseCustomerList(BaseCustomerVO baseCustomerVO) {
		JSONObject jsonObject = new JSONObject();
		baseCustomerVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		baseCustomerVO.setCustomerLevel("1");
		int total = baseCustomerDao.findBaseCustomerListCount(baseCustomerVO);
		List<BaseCustomerVO> resultList = new ArrayList<BaseCustomerVO>();
		if(total > 0){
		 resultList = baseCustomerDao.findBaseCustomerList(baseCustomerVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
    /**
     * 删除潜在客户信息(仅仅改变status值，并不做删除数据操作)
     */
	@Override
	public Map<String, Object> deleteBaseCustomer(BaseCustomerVO baseCustomerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			baseCustomerVO.setStatus(BaseDataConstant.STATUS_INVALID);
			baseCustomerDao.deleteBaseCustomer(baseCustomerVO);	
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除客户信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除客户信息失败！");
		}
		return resultMap;
	}
    /**
     * 添加潜在客户数据
     */
	@Override
	public Map<String, Object> addBaseCustomerType(BaseCustomerVO baseCustomerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			baseCustomerVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			UserVO userVO=userService.findLoginUser();
			baseCustomerVO.setCreateBy(userVO.getUserId());
			baseCustomerVO.setLastUpdateBy(userVO.getUserId());
			baseCustomerDao.addBaseCustomerType(baseCustomerVO);
			baseCustomerVO.setBaseCustomerId(baseCustomerVO.getBaseCustomerId());
			baseCustomerDao.addCustomer(baseCustomerVO);
			if(baseCustomerVO.getCustomerLevel().equals("1")){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加潜在客户成功！");
			}else if(baseCustomerVO.getCustomerLevel().equals("2")){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加意向客户成功！");
			}else if(baseCustomerVO.getCustomerLevel().equals("3")){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加签约客户成功！");
			}else if(baseCustomerVO.getCustomerLevel().equals("")){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加客户成功！");
			}
		} catch (Exception e) {
			if(baseCustomerVO.getCustomerLevel().equals("1")){
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "添加潜在客户失败！");
			}else if(baseCustomerVO.getCustomerLevel().equals("2")){
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "添加意向客户失败！");
			}else if(baseCustomerVO.getCustomerLevel().equals("3")){
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "添加签约客户失败！");
			}
		}
		return resultMap;
		
	}

	/**
	 * 查询意向客户列表
	 */
	@Override
	public JSONObject intentionBaseCustomer(BaseCustomerVO baseCustomerVO) {
		JSONObject jsonObject = new JSONObject();
		baseCustomerVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		baseCustomerVO.setCustomerLevel("2");
		int total = baseCustomerDao.findBaseCustomerListCount(baseCustomerVO);
		List<BaseCustomerVO> resultList = new ArrayList<BaseCustomerVO>();
		if(total > 0){
		 resultList = baseCustomerDao.findBaseCustomerList(baseCustomerVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
    /**
     * 查询签约客户列表
     */
	@Override
	public JSONObject contractBaseCustomer(BaseCustomerVO baseCustomerVO) {
		JSONObject jsonObject = new JSONObject();
		baseCustomerVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		baseCustomerVO.setCustomerLevel("3");
		int total = baseCustomerDao.findBaseCustomerListCount(baseCustomerVO);
		List<BaseCustomerVO> resultList = new ArrayList<BaseCustomerVO>();
		if(total > 0){
		 resultList = baseCustomerDao.findBaseCustomerList(baseCustomerVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
	/**
	 * 查询基础客户信息详情
	 */
	@Override
	public BaseCustomerVO findBaseCustomer(BaseCustomerVO baseCustomerVO) {
		BaseCustomerVO baseCustomer = baseCustomerDao.findBaseCustomer(baseCustomerVO);
		return baseCustomer;
	}



}
