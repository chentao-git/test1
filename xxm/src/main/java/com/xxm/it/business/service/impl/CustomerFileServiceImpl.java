package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.ICustomerFileDao;
import com.xxm.it.business.service.ICustomerFileService;
import com.xxm.it.business.vo.BaseCustomerVO;
import com.xxm.it.business.vo.BusinessOpportunityVO;
import com.xxm.it.business.vo.CustomerScheduleVO;
import com.xxm.it.business.vo.ServiceRecordVO;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class CustomerFileServiceImpl implements ICustomerFileService{
	@Resource
	private ICustomerFileDao customerFileDao;
	@Resource
	private IUserService userService;
    /**
     * 查询客户信息列表
     */
	@Override
	public JSONObject findCustomerList(BaseCustomerVO baseCustomerVO) {
		JSONObject jsonObject = new JSONObject();
		baseCustomerVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		List<BaseCustomerVO> resultList = new ArrayList<BaseCustomerVO>();
		int total = customerFileDao.findCustomerCount(baseCustomerVO);
		if(total > 0){
			resultList = customerFileDao.findCustomerList(baseCustomerVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
	/**
	 * 查询客户档案进度表列表
	 */
	@Override
	public JSONObject findCustomerScheduleList(CustomerScheduleVO customerScheduleVO) {
		JSONObject jsonObject = new JSONObject();
		customerScheduleVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		List<CustomerScheduleVO> resultList = new ArrayList<CustomerScheduleVO>();
		int total = customerFileDao.findCustomerScheduleCount(customerScheduleVO);
		if(total>0){
		   resultList = customerFileDao.findCustomerScheduleList(customerScheduleVO);
		  }
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
	/**
	 * 添加跟进记录
	 */
	@Override
	public Map<String, Object> addCustomerSchedule(CustomerScheduleVO customerScheduleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			customerScheduleVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			customerScheduleVO.setCreateBy(userVO.getUserId());
			customerScheduleVO.setLastUpdateBy(userVO.getUserId());
			customerFileDao.addCustomerSchedule(customerScheduleVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加跟进记录成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加跟进记录失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id查询当前这个进度表信息
	 */
	@Override
	public CustomerScheduleVO findCustomerSchedule(CustomerScheduleVO customerScheduleVO) {
		CustomerScheduleVO result = customerFileDao.findCustomerSchedule(customerScheduleVO);
		return result;
	}
	/**
	 *更新跟进记录 
	 */
	@Override
	public Map<String, Object> updateCustomerSchedule(CustomerScheduleVO customerScheduleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			customerScheduleVO.setLastUpdateBy(userVO.getUserId());
			customerFileDao.updateCustomerSchedule(customerScheduleVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改跟进记录成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改跟进记录失败！");
		}
		return resultMap;
	}
	/**
	 * 删除跟进记录
	 */
	@Override
	public Map<String, Object> deleteCustomerSchedule(CustomerScheduleVO customerScheduleVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			customerScheduleVO.setStatus(BaseDataConstant.STATUS_INVALID);
			customerFileDao.deleteCustomerSchedule(customerScheduleVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除跟进记录成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除跟进记录失败！");
		}
		return resultMap;
	}
	
	
	/**
	 * 查询商机列表
	 */
	@Override
	public JSONObject findBusinessOpportunityList(BusinessOpportunityVO businessOpportunityVO) {
		JSONObject jsonObject = new JSONObject();
		businessOpportunityVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		int total = customerFileDao.findBusinessOpportunityCount(businessOpportunityVO);
		List<BusinessOpportunityVO> resultList = new ArrayList<BusinessOpportunityVO>();
		if(total > 0){
			resultList = customerFileDao.findBusinessOpportunityList(businessOpportunityVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
	/**
	 * 添加商机
	 */
	@Override
	public Map<String, Object> addBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			businessOpportunityVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			businessOpportunityVO.setCreateBy(userVO.getUserId());
			businessOpportunityVO.setLastUpdateBy(userVO.getUserId());
			customerFileDao.addBusinessOpportunity(businessOpportunityVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加商机成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加商机失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id去查询商机详情信息 
	 */
	@Override
	public BusinessOpportunityVO findBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO) {
		BusinessOpportunityVO result = customerFileDao.findBusinessOpportunity(businessOpportunityVO);
		return result;
	}
	/**
	 * 修改商机
	 */
	@Override
	public Map<String, Object> updateBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			businessOpportunityVO.setLastUpdateBy(userVO.getUserId());
            customerFileDao.updateBusinessOpportunity(businessOpportunityVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改商机成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改商机失败！");
		}
		return resultMap;
	}
	/**
	 * 删除商机
	 */
	@Override
	public Map<String, Object> deleteBusinessOpportunity(BusinessOpportunityVO businessOpportunityVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			businessOpportunityVO.setStatus(BaseDataConstant.STATUS_INVALID);
            customerFileDao.deleteBusinessOpportunity(businessOpportunityVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除商机成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除商机失败！");
		}
		return resultMap;
	}
	
	
	
	/**
	 * 查询服务记录列表
	 */
	@Override
	public JSONObject findServiceRecordList(ServiceRecordVO serviceRecordVO) {
		JSONObject jsonObject = new JSONObject();
		serviceRecordVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
		int total = customerFileDao.findServiceRecordCount(serviceRecordVO);
		List<ServiceRecordVO> resultList = new ArrayList<ServiceRecordVO>();
		if(total > 0){
		resultList = customerFileDao.findServiceRecordList(serviceRecordVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}
	/**
	 * 添加服务记录
	 */
	@Override
	public Map<String, Object> addServiceRecord(ServiceRecordVO serviceRecordVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			serviceRecordVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			serviceRecordVO.setCreateBy(userVO.getUserId());
			serviceRecordVO.setLastUpdateBy(userVO.getUserId());
			customerFileDao.addServiceRecord(serviceRecordVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加服务记录成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加服务记录失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id去查询详情信息
	 */
	@Override
	public ServiceRecordVO findServiceRecord(ServiceRecordVO serviceRecordVO) {
		ServiceRecordVO result = customerFileDao.findServiceRecord(serviceRecordVO);
		return result;
	}
	/**
	 * 修改服务记录
	 */
	@Override
	public Map<String, Object> updateServiceRecord(ServiceRecordVO serviceRecordVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = userService.findLoginUser();
			serviceRecordVO.setLastUpdateBy(userVO.getUserId());
            customerFileDao.updateServiceRecord(serviceRecordVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改服务记录成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改服务记录失败！");
		}
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteServiceRecord(ServiceRecordVO serviceRecordVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			serviceRecordVO.setStatus(BaseDataConstant.STATUS_INVALID);
            customerFileDao.deleteServiceRecord(serviceRecordVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除服务记录成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除服务记录失败！");
		}
		return resultMap;
	}
	/**
	 * 修改锁定人
	 * @param baseCustomerVO
	 * @return
	 */
	@Override
	public Map<String, Object> updateLastUpdateByName(BaseCustomerVO baseCustomerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String type = baseCustomerVO.getExtendField1();
			if(type.equals("1")){//释放
				baseCustomerVO.setLockMan("");
			}else if(type.equals("2")){
				UserVO userVO = userService.findLoginUser();
				baseCustomerVO.setLockMan(userVO.getUserId());
			}
			customerFileDao.updateLastUpdateByName(baseCustomerVO);
			if(type.equals("1")){//释放
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "锁定人释放成功");
			}else if(type.equals("2")){
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "锁定人锁定成功");
			}
			
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "锁定人锁定失败！");
		}
		return resultMap;
	}

}
