package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BusinessOpportunityVO;
import com.xxm.it.business.vo.CustomerScheduleVO;
import com.xxm.it.business.vo.ServiceRecordVO;

import net.sf.json.JSONObject;

@Path(value = "/customerFileService")
public interface ICustomerFileService {
	/**
	 * 查询客户信息列表
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/findCustomerList")
    public JSONObject findCustomerList(BaseCustomerVO baseCustomerVO);
	/**
	 * 查询客户档案进度表列表
	 * @param customerScheduleVO
	 * @return
	 */
	@POST
	@Path("/findCustomerScheduleList")
    public JSONObject findCustomerScheduleList(CustomerScheduleVO customerScheduleVO);
	/**
	 * 添加跟进记录
	 * @param customerScheduleVO
	 * @return
	 */
	@POST
	@Path("/addCustomerSchedule")
    public Map<String, Object> addCustomerSchedule(CustomerScheduleVO customerScheduleVO);
	/**
	 * 根据id查询当前这个进度表信息
	 * @param customerScheduleVO
	 * @return
	 */
	@POST
	@Path("/findCustomerSchedule")
	public CustomerScheduleVO findCustomerSchedule(CustomerScheduleVO customerScheduleVO);
	/**
	 * 更新跟进记录
	 * @param customerScheduleVO
	 * @return
	 */
	@POST
	@Path("/updateCustomerSchedule")
	public Map<String, Object> updateCustomerSchedule(CustomerScheduleVO customerScheduleVO);
	/**
	 * 删除跟进记录
	 * @param customerScheduleVO
	 * @return
	 */
	@POST
	@Path("/deleteCustomerSchedule")
	public Map<String, Object> deleteCustomerSchedule(CustomerScheduleVO customerScheduleVO);
	
	
	
	/**
	 * 查询商机列表
	 * @param businessOpportunityVO
	 * @return
	 */
	@POST
	@Path("/findBusinessOpportunityList")
	public JSONObject findBusinessOpportunityList(BusinessOpportunityVO businessOpportunityVO);
	/**
	 * 添加商机
	 * @param businessOpportunityVO
	 * @return
	 */
	@POST
	@Path("/addBusinessOpportunity")
	public Map<String, Object> addBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
	/**
	 * 根据id去查询商机详情信息 
	 * @param businessOpportunityVO
	 * @return
	 */
	@POST
	@Path("/findBusinessOpportunity")
	public BusinessOpportunityVO findBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
	/**
	 * 更新商机
	 * @param businessOpportunityVO
	 * @return
	 */
	@POST
	@Path("/updateBusinessOpportunity")
	public Map<String, Object> updateBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
	/**
	 * 删除商机
	 * @param businessOpportunityVO
	 * @return
	 */
	@POST
	@Path("/deleteBusinessOpportunity")
	public Map<String, Object> deleteBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
	
	
	
	/**
	 * 查询服务记录列表
	 * @param serviceRecordVO
	 * @return
	 */
	@POST
	@Path("/findServiceRecordList")
	public JSONObject findServiceRecordList(ServiceRecordVO serviceRecordVO);
	/**
	 * 添加服务记录
	 * @param serviceRecordVO
	 * @return
	 */
	@POST
	@Path("/addServiceRecord")
	public Map<String, Object> addServiceRecord(ServiceRecordVO serviceRecordVO);
	/**
	 * 根据id去查询详情信息
	 * @param serviceRecordVO
	 * @return
	 */
	@POST
	@Path("/findServiceRecord")
	public ServiceRecordVO findServiceRecord(ServiceRecordVO serviceRecordVO);
	/**
	 * 修改服务记录
	 * @param serviceRecordVO
	 * @return
	 */
	@POST
	@Path("/updateServiceRecord")
	public Map<String, Object> updateServiceRecord(ServiceRecordVO serviceRecordVO);
	/**
	 * 删除服务记录
	 * @param serviceRecordVO
	 * @return
	 */
	@POST
	@Path("/deleteServiceRecord")
	public Map<String, Object> deleteServiceRecord(ServiceRecordVO serviceRecordVO);
	/**
	 * 修改锁定人
	 * @param baseCustomerVO
	 * @return
	 */
	@POST
	@Path("/updateLastUpdateByName")
	public Map<String, Object> updateLastUpdateByName(BaseCustomerVO baseCustomerVO);
}
