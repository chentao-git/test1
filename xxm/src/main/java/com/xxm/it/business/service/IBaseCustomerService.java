package com.xxm.it.business.service;


import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.BaseCustomerVO;

import net.sf.json.JSONObject;


@Path(value = "/baseCustomerService")
public interface IBaseCustomerService {

	/**
	 * 添加客户信息
	 * 
	 * @param baseCustomerVO
	 *            参数
	 * @return 
	 */
	public BaseCustomerVO addBaseCustomer(BaseCustomerVO baseCustomerVO);

	/**
	 * 修改客户信息
	 * 
	 * @param baseCustomerVO
	 *            参数
	 * @return 
	 */
	@POST
	@Path("/updateBaseCustomer")
	public BaseCustomerVO updateBaseCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 修改基础客户信息
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/updateBaseCustomerInfo")
	public Map<String, Object> updateBaseCustomerInfo(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询客户信息列表
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/findBaseCustomerList")
	public JSONObject findBaseCustomerList(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询基础客户信息详情
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/findBaseCustomer")
	public BaseCustomerVO findBaseCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 删除潜在客户信息
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/deleteBaseCustomer")
	public Map<String, Object> deleteBaseCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 添加潜在客户信息
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/addBaseCustomerType")
	public Map<String, Object> addBaseCustomerType(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询意向客户列表
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/intentionBaseCustomer")
	public JSONObject intentionBaseCustomer(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询签约客户列表
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/contractBaseCustomer")
	public JSONObject contractBaseCustomer(BaseCustomerVO baseCustomerVO);
    
}
