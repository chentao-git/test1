package com.xxm.it.business.dao;


import java.util.List;

import com.xxm.it.business.vo.BaseCustomerVO;

public interface IBaseCustomerDao {

	/**
	 * 添加基础客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public void addBaseCustomer(BaseCustomerVO baseCustomerVO);
	
	/**
	 * 添加客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public void addCustomer(BaseCustomerVO baseCustomerVO);

	/**
	 * 查询基础客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public BaseCustomerVO findBaseCustomer(BaseCustomerVO baseCustomerVO);
	
	/**
	 * 查询基础客户信息（返回list）
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public List<BaseCustomerVO> findBaseCustomers(BaseCustomerVO baseCustomerVO);

	/**
	 * 修改基础客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public void updateBaseCustomer(BaseCustomerVO baseCustomerVO);
	
	/**
	 * 修改客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public void updateCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询客户信息
	 * 
	 * @param baseCustomerVO
	 * @return
	 */
	public BaseCustomerVO findCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询潜在客户类型基础信息列表
	 * @param baseCustomerVO
	 * @return
	 */
	public  List<BaseCustomerVO> findBaseCustomerList(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询潜在客户基础信息总数
	 * @param baseCustomerVO
	 * @return
	 */
	public int findBaseCustomerListCount(BaseCustomerVO baseCustomerVO);
	/**
	 * 删除潜在客户基础信息(仅仅改变status值)
	 * @param baseCustomerVO
	 */
	public void deleteBaseCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 添加客户基础信息
	 * @param baseCustomerVO
	 */
	public void addBaseCustomerType(BaseCustomerVO baseCustomerVO);
	
}
