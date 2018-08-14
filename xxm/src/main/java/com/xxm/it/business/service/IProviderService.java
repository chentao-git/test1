package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ProviderVO;
import com.xxm.it.business.vo.SpFollowUpVO;
import com.xxm.it.business.vo.SpProfitVO;
import com.xxm.it.business.vo.SpServiceVO;

import net.sf.json.JSONObject;

@Path(value="/providerService")	
public interface IProviderService {
	/*
	 * 
	 */
	@POST
	@Path("/findProviderList")
	public JSONObject findProviderList(ProviderVO providerVO);
	/*
	 * 
	 */
	@POST
	@Path("/addProvider")
	public Map<String, Object> addProvider(ProviderVO providerVO);
	/*
	 * 
	 */
	@POST
	@Path("/findProvider")
	public ProviderVO findProvider(ProviderVO providerVO); 
	/*
	 * 
	 */
	@POST
	@Path("/deleteProvider")
	public Map<String, Object> deleteProvider(ProviderVO providerVO); 
	/*
	 * 
	 */
	@POST
	@Path("/updateProvider")
	public Map<String, Object> updateProvider(ProviderVO providerVO);
	
	
	/**
	 * sp跟进列表
	 * @param spFollowUpVO
	 * @return
	 */
	@POST
	@Path(value="/findSpFollowUpList")
	public JSONObject findSpFollowUpList(SpFollowUpVO spFollowUpVO);
	/**
	 * 添加跟进记录
	 * @param spFollowUpVO
	 * @return
	 */
	@POST
	@Path(value="/addSpFollowUp")
	public Map<String, Object> addSpFollowUp(SpFollowUpVO spFollowUpVO);
	/**
	 * 根据id去查询详情
	 * @param spFollowUpVO
	 * @return
	 */
	@POST
	@Path(value="/findSpFollowUp")
	public SpFollowUpVO findSpFollowUp(SpFollowUpVO spFollowUpVO);
	/**
	 * 修改跟进记录
	 * @param spFollowUpVO
	 * @return
	 */
	@POST
	@Path(value="/updateSpFollowUp")
	public Map<String, Object> updateSpFollowUp(SpFollowUpVO spFollowUpVO);
	/**
	 * 删除跟进记录
	 * @param spFollowUpVO
	 * @return
	 */
	@POST
	@Path(value="/deleteSpFollowUp")
	public Map<String, Object> deleteSpFollowUp(SpFollowUpVO spFollowUpVO);
	
	
	
	/**
	 * 查询sp商服务列表
	 * @param spServiceVO
	 * @return
	 */
	@POST
	@Path(value="/findSpServiceList")
	public JSONObject findSpServiceList(SpServiceVO spServiceVO);
	/**
	 * 新增sp商服务记录
	 * @param spServiceVO
	 * @return
	 */
	@POST
	@Path(value="/addSpService")
	public Map<String, Object> addSpService(SpServiceVO spServiceVO);
	/**
	 * 根据sp商id去查询详情
	 * @param spServiceVO
	 * @return
	 */
	@POST
	@Path(value="/findSpService")
	public SpServiceVO findSpService(SpServiceVO spServiceVO);
	/**
	 * 更新sp商记录
	 * @param spServiceVO
	 * @return
	 */
	@POST
	@Path(value="/updateSpService")
	public Map<String, Object> updateSpService(SpServiceVO spServiceVO);
	/**
	 * 删除sp商服务记录
	 * @param spServiceVO
	 * @return
	 */
	@POST
	@Path(value="/deleteSpService")
	public Map<String, Object> deleteSpService(SpServiceVO spServiceVO);
	
	
	
	/**
	 * sp商分润配置列表
	 * @param SpProfitVO
	 * @return
	 */
	@POST
	@Path(value="/findSpProfitList")
	public JSONObject findSpProfitList(SpProfitVO SpProfitVO);
	/**
	 * 根据id去查询sp商分润配置详情
	 * @param SpProfitVO
	 * @return
	 */
	@POST
	@Path(value="/findSpProfit")
	public SpProfitVO findSpProfit(SpProfitVO SpProfitVO);
	/**
	 * 新增sp商分润配置
	 * @param spProfitVO
	 * @return
	 */
	@POST
	@Path(value="/addSpProfitVO")
	public Map<String, Object> addSpProfitVO(SpProfitVO spProfitVO);
	/**
	 * 跟新sp商分润配置
	 * @param spProfitVO
	 * @return
	 */
	@POST
	@Path(value="/updateSpProfit")
	public Map<String, Object> updateSpProfit(SpProfitVO spProfitVO);
	/**
	 * 删除sp商分润配置
	 * @param spProfitVO
	 * @return
	 */
	@POST
	@Path(value="/deleteSpProfit")
	public Map<String, Object> deleteSpProfit(SpProfitVO spProfitVO);
}
