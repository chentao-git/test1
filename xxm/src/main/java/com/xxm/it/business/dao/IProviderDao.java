package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.ProviderVO;
import com.xxm.it.business.vo.SpFollowUpVO;
import com.xxm.it.business.vo.SpProfitVO;
import com.xxm.it.business.vo.SpServiceVO;


public interface IProviderDao {
	/*
	 * 查询列表
	 */
	public List<ProviderVO> findProviderList(ProviderVO providerVO);
	/*
	 * 查询数据
	 */
	public ProviderVO findProvider(ProviderVO providerVO);
	/*
	 * 查询总数
	 */
	public int findProviderListCount(ProviderVO providerVO);
	/*
	 * 新增
	 */
	public void addProvider(ProviderVO providerVO);
	/*
	 * 删除
	 */
	public void deleteProvider(ProviderVO providerVO);
	/*
	 * 
	 */
//	public List<ProviderVO>findProviders(ProviderVO providerVO);
	/*
	 * 修改
	 */
	public void updateProvider(ProviderVO providerVO);
	/**
	 * 查询sp商跟进列表
	 * @param spFollowUpVO
	 * @return
	 */
	public List<SpFollowUpVO> findSpFollowUpList(SpFollowUpVO spFollowUpVO);
	/**
	 * 查询sp商跟进总数
	 * @param spFollowUpVO
	 * @return
	 */
	public int findSpFollowUpListCount(SpFollowUpVO spFollowUpVO);
	/**
	 * 新增跟进记录
	 * @param spFollowUpVO
	 */
	public void addSpFollowUp(SpFollowUpVO spFollowUpVO);
	/**
	 * 根据id去查询详情
	 * @param spFollowUpVO
	 * @return
	 */
	public SpFollowUpVO findSpFollowUp(SpFollowUpVO spFollowUpVO);
	/**
	 * 修改跟进记录
	 * @param SpFollowUpVO
	 */
	public void updateSpFollowUp(SpFollowUpVO SpFollowUpVO);
	/**
	 * 删除跟进记录
	 * @param spFollowUpVO
	 */
	public void deleteSpFollowUp(SpFollowUpVO spFollowUpVO);
	
	
	/*
	 * sp商服务列表
	 */
	public List<SpServiceVO> findSpServiceList(SpServiceVO spServiceVO);
	/*
	 * sp商服务总数
	 */
	public int findSpServiceListCount(SpServiceVO spServiceVO);
	/*
	 * 根据id查询sp商详情
	 */
	public SpServiceVO findSpService(SpServiceVO spServiceVO);
	/*
	 * 新增服务记录
	 */
	public void addSpService(SpServiceVO spServiceVO);
	/*
	 * 修改服务记录
	 */
	public void updateSpService(SpServiceVO spServiceVO);
	/*
	 * 删除服务记录
	 */
	public void deleteSpService(SpServiceVO spServiceVO);
	
	
	
	/**
	 * SP商分润列表
	 * @param SpProfitVO
	 * @return
	 */
	public List<SpProfitVO> findSpProfitList(SpProfitVO SpProfitVO);
	/**
	 * sp商分润总数
	 * @param spProfitVO
	 * @return
	 */
	public int findSpProfitListCount(SpProfitVO spProfitVO);
	/**
	 * 根据id去查询sp商分润详情
	 * @param spProfitVO
	 * @return
	 */
	public SpProfitVO findSpProfit(SpProfitVO spProfitVO);
	/**
	 * 添加sp商分润配置详情
	 * @param spProfitVO
	 */
	public void addSpProfitVO(SpProfitVO spProfitVO);
	/**
	 * 更新sp商分润配置
	 * @param spProfitVO
	 */
	public void updateSpProfit(SpProfitVO spProfitVO);
	/**
	 * 删除sp商分润配置
	 * @param spProfitVO
	 */
	public void deleteSpProfit(SpProfitVO spProfitVO);
}
