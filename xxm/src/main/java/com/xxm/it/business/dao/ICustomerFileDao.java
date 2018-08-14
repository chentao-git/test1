package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BusinessOpportunityVO;
import com.xxm.it.business.vo.CustomerScheduleVO;
import com.xxm.it.business.vo.ServiceRecordVO;

public interface ICustomerFileDao {
	/**
	 * 查询客户信息列表
	 * @param baseCustomerVO
	 * @return
	 */
  public List<BaseCustomerVO> findCustomerList(BaseCustomerVO baseCustomerVO);
    /**
     * 查询客户信息总数
     * @param baseCustomerVO
     * @return
     */
  public int findCustomerCount(BaseCustomerVO baseCustomerVO);
	 /**
	  * 修改锁定人
	  */
  public void updateLastUpdateByName(BaseCustomerVO baseCustomerVO);
  
  
  /**
     * 查询进度表列表
     * @param customerScheduleVO
     * @return
     */
  public List<CustomerScheduleVO> findCustomerScheduleList(CustomerScheduleVO customerScheduleVO);
    /**
     * 查询进度表信息总数
     * @param customerScheduleVO
     * @return
     */
  public int findCustomerScheduleCount(CustomerScheduleVO customerScheduleVO);
  /**
   * 添加跟进记录
   * @param customerScheduleVO
   */
  public void addCustomerSchedule(CustomerScheduleVO customerScheduleVO);
  /**
   * 根据id去查询跟进记录
   * @param customerScheduleVO
   * @return
   */
  public CustomerScheduleVO findCustomerSchedule(CustomerScheduleVO customerScheduleVO);
  /**
   * 更新跟进记录
   * @param customerScheduleVO
   */
  public void updateCustomerSchedule(CustomerScheduleVO customerScheduleVO);
  /**
   * 删除跟进记录
   * @param customerScheduleVO
   */
  public void deleteCustomerSchedule(CustomerScheduleVO customerScheduleVO);
  
  
  
  /**
   * 查询商机列表
   * @param businessOpportunityVO
   * @return
   */
  public List<BusinessOpportunityVO> findBusinessOpportunityList(BusinessOpportunityVO businessOpportunityVO);
  /**
   * 查询商机列表总数
   * @param businessOpportunityVO
   * @return
   */
  public int findBusinessOpportunityCount(BusinessOpportunityVO businessOpportunityVO);
  /**
   * 添加商机
   * @param businessOpportunityVO
   */
  public void addBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
  /**
   * 根据id去查询详情信息
   * @param businessOpportunityVO
   * @return
   */
  public BusinessOpportunityVO findBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
  /**
   * 更新商机
   * @param businessOpportunityVO
   */
  public void updateBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
  /**
   * 删除商机
   * @param businessOpportunityVO
   */
  public void deleteBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO);
  
  
  
  /**
   * 查询服务记录列表
   * @param serviceRecordVO
   * @return
   */
  public List<ServiceRecordVO> findServiceRecordList(ServiceRecordVO serviceRecordVO);
  /**
   * 查询服务记录列表总数
   * @param serviceRecordVO
   * @return
   */
  public int findServiceRecordCount(ServiceRecordVO serviceRecordVO);
  /**
   * 添加服务记录
   * @param serviceRecordVO
   */
  public void addServiceRecord(ServiceRecordVO serviceRecordVO);
  /**
   * 根据id去查询详情信息
   * @param serviceRecordVO
   * @return
   */
  public ServiceRecordVO findServiceRecord(ServiceRecordVO serviceRecordVO);
  /**
   * 更新服务记录
   * @param serviceRecordVO
   */
  public void updateServiceRecord(ServiceRecordVO serviceRecordVO);
  /**
   * 删除服务记录
   * @param serviceRecordVO
   */
  public void deleteServiceRecord(ServiceRecordVO serviceRecordVO);
}
