package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.system.dao.ILeaseInfoDao;
import com.xxm.it.system.service.LeaseInfoService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class LeaseInfoServiceImpl implements LeaseInfoService{
	@Resource
	private ILeaseInfoDao iLeaseInfoDao;
	/**
	 * 获取reques对象
	 */
	@Autowired
	private HttpServletRequest request;
	/**
	 * 查询凭租物信息列表
	 */
	@Override
	public JSONObject findLeaseInfoList(BaseLeaseInfoVO leaseInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = iLeaseInfoDao.findListCount(leaseInfoVO);
		// 查询集合数据
		List<BaseLeaseInfoVO> resultLsit = new ArrayList<BaseLeaseInfoVO>();
		if(total>0){
			resultLsit = iLeaseInfoDao.findLeaseInfoList(leaseInfoVO);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", resultLsit);
		return jsonObject;
	}
	/**
	 * 添加凭租物
	 */
	@Override
	public Map<String, Object> addLeaseInfo(BaseLeaseInfoVO leaseInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = findLoginUser();
			leaseInfoVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			leaseInfoVO.setCreateBy(userVO.getUserId());
			iLeaseInfoDao.addLeaseInfo(leaseInfoVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加凭租物成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加凭租物失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id去查询详情信息
	 */
	@Override
	public BaseLeaseInfoVO findLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO) {
		BaseLeaseInfoVO resultLeaseInfoVO = iLeaseInfoDao.findLeaseInfoVO(leaseInfoVO);
		return resultLeaseInfoVO;
	}
	/**
	 * 修改凭租物信息
	 */
	@Override
	public Map<String, Object> updateLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = findLoginUser();
			leaseInfoVO.setLastUpdateBy(userVO.getUserId());
			iLeaseInfoDao.updateLeaseInfoVO(leaseInfoVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改凭租物信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改凭租物信息失败！");
		}
		return resultMap;
	}
	/**
	 * 根据车产类型的值去拿name
	 */
	@Override
	public BaseLeaseInfoVO findcarTypeName(BaseLeaseInfoVO leaseInfoVO) {
		BaseLeaseInfoVO resultLeaseInfoVO = iLeaseInfoDao.findcarTypeName(leaseInfoVO);
		return resultLeaseInfoVO;
	}
	/**
	 * 删除凭租物（仅仅改变status并不去删除数据）
	 */
	@Override
	public Map<String, Object> deleteLeaseInfo(BaseLeaseInfoVO leaseInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			leaseInfoVO.setStatus(BaseDataConstant.STATUS_INVALID);
			iLeaseInfoDao.deleteLeaseInfo(leaseInfoVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除凭租物信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除凭租物信息失败！");
		}
		return resultMap;
	}
	
	
	
	private UserVO findLoginUser() {
		// 声明用户对象
		UserVO userVO = null;
		Object obj = request.getSession().getAttribute("user");
		if (null != obj) {
			userVO = (UserVO) obj;
		}
		return userVO;
	}


	





	
	

}
