package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IProviderDao;
import com.xxm.it.business.service.IProviderService;
import com.xxm.it.business.vo.ProviderVO;
import com.xxm.it.business.vo.SpFollowUpVO;
import com.xxm.it.business.vo.SpProfitVO;
import com.xxm.it.business.vo.SpServiceVO;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class ProviderServiceImpl implements IProviderService {
	private static Logger logger = Logger.getLogger(ProviderServiceImpl.class);
	@Resource
	private IProviderDao providerDao;
	@Resource
	private IUserService userService;

	/*
	 * 信息查询
	 */
	public JSONObject findProviderList(ProviderVO providerVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合总数
		int total = providerDao.findProviderListCount(providerVO);
		// 查询集合数据
		List<ProviderVO> resultList = new ArrayList<ProviderVO>();
		if (total > 0) {
			resultList = providerDao.findProviderList(providerVO);

		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);
		return jsonObject;
	}
	public Map<String,Object> addProvider(ProviderVO providerVO){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			providerVO.setSpStatus(BaseDataConstant.STATUS_EFFECTIVE);
			providerDao.addProvider(providerVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加服务商资料成功");
			logger.info("添加服务商资料成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加服务商资料失败");
			logger.info("添加服务商资料失败"+e.getMessage());
		}
		return resultMap;
		
	}
	
	@Override
	public ProviderVO findProvider(ProviderVO providerVO) {
		ProviderVO resultProviderVO = providerDao.findProvider(providerVO);
		return resultProviderVO;
	}
	
	public Map<String,Object> deleteProvider(ProviderVO providerVO){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			providerVO.setSpStatus(BaseDataConstant.STATUS_INVALID);
			providerDao.deleteProvider(providerVO);
			resultMap.put("result", Boolean.TRUE);
			logger.info("删除服务商信息成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除服务商资料失败");
			logger.info("删除服务商资料失败"+e.getMessage());
		}
		return resultMap;
	}

	public Map<String, Object> updateProvider(ProviderVO providerVO) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			providerDao.updateProvider(providerVO);
			resultMap.put("result",Boolean.TRUE);
			resultMap.put("msg","修改服务商资料成功");
			logger.info("修改服务商资料成功");
		}catch(Exception e){
			resultMap.put("result",Boolean.FALSE);
			resultMap.put("msg", "修改服务商资料失败");
			logger.info("修改服务商资料失败"+e.getMessage());
		}
		return resultMap;
	}
	
	
	
	/**
	 * sp跟进列表
	 */
	@Override
	public JSONObject findSpFollowUpList(SpFollowUpVO spFollowUpVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合总数
		int total = providerDao.findSpFollowUpListCount(spFollowUpVO);
		// 查询集合数据
		List<SpFollowUpVO> resultList = new ArrayList<SpFollowUpVO>();
		if (total > 0) {
			resultList = providerDao.findSpFollowUpList(spFollowUpVO);
		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);
		return jsonObject;
	}
	/**
	 * 添加跟进记录
	 */
	@Override
	public Map<String, Object> addSpFollowUp(SpFollowUpVO spFollowUpVO) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			UserVO user = userService.findLoginUser();
			spFollowUpVO.setCreateBy(user.getUserId());
			spFollowUpVO.setLastUpdateBy(user.getUserId());
			spFollowUpVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			providerDao.addSpFollowUp(spFollowUpVO);;
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加跟进记录成功");
			logger.info("添加跟进记录成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加跟进记录失败");
			logger.info("添加跟进记录失败"+e.getMessage());
		}
		return resultMap;
	}
	/**
	 * 根据id去查询详情
	 */
	@Override
	public SpFollowUpVO findSpFollowUp(SpFollowUpVO spFollowUpVO) {
		SpFollowUpVO result = providerDao.findSpFollowUp(spFollowUpVO);
		return result;
	}
	@Override
	public Map<String, Object> updateSpFollowUp(SpFollowUpVO spFollowUpVO) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			UserVO user = userService.findLoginUser();
			spFollowUpVO.setLastUpdateBy(user.getUserId());
			providerDao.updateSpFollowUp(spFollowUpVO);
			resultMap.put("result",Boolean.TRUE);
			resultMap.put("msg","修改跟进记录成功");
			logger.info("修改跟进记录成功");
		}catch(Exception e){
			resultMap.put("result",Boolean.FALSE);
			resultMap.put("msg", "修改跟进记录失败");
			logger.info("修改跟进记录失败"+e.getMessage());
		}
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteSpFollowUp(SpFollowUpVO spFollowUpVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			spFollowUpVO.setStatus(BaseDataConstant.STATUS_INVALID);
			providerDao.deleteSpFollowUp(spFollowUpVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除跟进记录成功");
			logger.info("删除跟进信息成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除跟进记录失败");
			logger.info("删除跟进记录失败"+e.getMessage());
		}
		return resultMap;
	}
	
	
	
	
	/**
	 * sp商服务列表
	 */
	@Override
	public JSONObject findSpServiceList(SpServiceVO spServiceVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合总数
		int total = providerDao.findSpServiceListCount(spServiceVO);
		// 查询集合数据
		List<SpServiceVO> resultList = new ArrayList<SpServiceVO>();
		if (total > 0) {
			resultList = providerDao.findSpServiceList(spServiceVO);
		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);
		return jsonObject;
	}
	/**
	 * 新增sp商服务记录
	 */
	@Override
	public Map<String, Object> addSpService(SpServiceVO spServiceVO) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
        	UserVO user = userService.findLoginUser();
        	spServiceVO.setCreateBy(user.getUserId());
        	spServiceVO.setLastUpdateBy(user.getUserId());
        	spServiceVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			providerDao.addSpService(spServiceVO);
        	resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加sp商服务记录成功");
			logger.info("添加sp商服务记录成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加sp商服务记录失败");
			logger.info("添加sp商服务记录失败");
		}
		return resultMap;
	}
	@Override
	public SpServiceVO findSpService(SpServiceVO spServiceVO) {
		SpServiceVO result = providerDao.findSpService(spServiceVO);
		return result;
	}
	@Override
	public Map<String, Object> updateSpService(SpServiceVO spServiceVO) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			UserVO user = userService.findLoginUser();
			spServiceVO.setLastUpdateBy(user.getUserId());
			providerDao.updateSpService(spServiceVO);
			resultMap.put("result",Boolean.TRUE);
			resultMap.put("msg","修改服务记录成功");
			logger.info("修改服务记录成功");
		}catch(Exception e){
			resultMap.put("result",Boolean.FALSE);
			resultMap.put("msg", "修改服务记录失败");
			logger.info("修改服务记录失败"+e.getMessage());
		}
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteSpService(SpServiceVO spServiceVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			spServiceVO.setStatus(BaseDataConstant.STATUS_INVALID);
			providerDao.deleteSpService(spServiceVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除服务记录成功");
			logger.info("删除服务信息成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除服务记录失败");
			logger.info("删除服务记录失败"+e.getMessage());
		}
		return resultMap;
	}
	
	
	
	/**
	 * sp商分润配置列表
	 */
	@Override
	public JSONObject findSpProfitList(SpProfitVO SpProfitVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合总数
		int total = providerDao.findSpProfitListCount(SpProfitVO);
		// 查询集合数据
		List<SpProfitVO> resultList = new ArrayList<SpProfitVO>();
		if (total > 0) {
			resultList = providerDao.findSpProfitList(SpProfitVO);
		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);
		return jsonObject;
	}
	/**
	 * 根据id去查询sp商分润详情
	 */
	@Override
	public SpProfitVO findSpProfit(SpProfitVO SpProfitVO) {
		SpProfitVO result = providerDao.findSpProfit(SpProfitVO);
		return result;
	}
	/**
	 * 新增sp商分润配置
	 */
	@Override
	public Map<String, Object> addSpProfitVO(SpProfitVO spProfitVO) {
		 Map<String, Object> resultMap = new HashMap<String, Object>();
	        try {
	        	UserVO user = userService.findLoginUser();
	        	spProfitVO.setCreateBy(user.getUserId());
	        	spProfitVO.setLastUpdateBy(user.getUserId());
	        	spProfitVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
				providerDao.addSpProfitVO(spProfitVO);
	        	resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "添加sp商分润配置成功");
				logger.info("添加sp商分润配置成功");
			} catch (Exception e) {
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "添加sp商分润配置失败");
				logger.info("添加sp商分润配置失败");
			}
			return resultMap;
	}
	/**
	 * 修改sp商分润配置
	 */
	@Override
	public Map<String, Object> updateSpProfit(SpProfitVO spProfitVO) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			UserVO user = userService.findLoginUser();
			spProfitVO.setLastUpdateBy(user.getUserId());
			providerDao.updateSpProfit(spProfitVO);
			resultMap.put("result",Boolean.TRUE);
			resultMap.put("msg","修改sp商分润配置成功");
			logger.info("修改sp商分润配置成功");
		}catch(Exception e){
			resultMap.put("result",Boolean.FALSE);
			resultMap.put("msg", "修改sp商分润配置失败");
			logger.info("修改sp商分润配置失败"+e.getMessage());
		}
		return resultMap;
	}
	/**
	 * 删除sp商分润配置
	 */
	@Override
	public Map<String, Object> deleteSpProfit(SpProfitVO spProfitVO) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			spProfitVO.setStatus(BaseDataConstant.STATUS_INVALID);
			providerDao.deleteSpProfit(spProfitVO);;
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除sp商分润配置成功");
			logger.info("删除sp商分润配置成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除sp商分润配置失败");
			logger.info("删除sp商分润配置失败"+e.getMessage());
		}
		return resultMap;
	}



}
