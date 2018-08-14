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

import com.xxm.it.system.dao.IAffiliatedDao;
import com.xxm.it.system.service.AffiliatedService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.AffiliatedVO;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class AffiliatedServiceImpl implements AffiliatedService{
	@Resource
	private IAffiliatedDao iAffiliatedDao;
	/**
	 * 获取reques对象
	 */
	@Autowired
	private HttpServletRequest request;


	/**
	 * 查询挂靠公司列表
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@Override
	public JSONObject findCompanyList(AffiliatedVO affiliatedVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = iAffiliatedDao.findListCount(affiliatedVO);
		// 查询集合数据
		List<AffiliatedVO> resultLsit = new ArrayList<AffiliatedVO>();
		if(total >0){
		resultLsit = iAffiliatedDao.findCist(affiliatedVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}
    /**
     * 添加挂靠公司
     * resultMap 返回结果消息
     */
	@Override
	public Map<String, Object> addAffiliated(AffiliatedVO affiliatedVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = findLoginUser();
			affiliatedVO.setStatus(BaseDataConstant.STATUS_EFFECTIVE);
			affiliatedVO.setCreateBy(userVO.getUserId());
			iAffiliatedDao.addAffiliated(affiliatedVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加挂靠公司成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加挂靠公司失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id去查询信息
	 */
	@Override
	public AffiliatedVO findAffiliated(AffiliatedVO affiliatedVO) {
		AffiliatedVO resuleAffiliatedVO = iAffiliatedDao.findAffiliated(affiliatedVO);
		return resuleAffiliatedVO;
	}
	/**
	 *修改挂靠公司信息 
	 */
	@Override
	public Map<String, Object> updateAffiliated(AffiliatedVO affiliatedVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserVO userVO = findLoginUser();
			affiliatedVO.setLastUpdateBy(userVO.getUserId());
			iAffiliatedDao.updateAffiliated(affiliatedVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改挂靠公司信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改挂靠公司信息失败！");
		}
		return resultMap;
	}
	/**
	 * 删除挂靠公司 （仅仅修改status值并不删除数据）
	 */
	@Override
	public Map<String, Object> deleteAffiliated(AffiliatedVO affiliatedVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			affiliatedVO.setStatus(BaseDataConstant.STATUS_INVALID);
			iAffiliatedDao.deleteAffiliated(affiliatedVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "删除挂靠公司成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除挂靠公司信息失败！");
		}
		return resultMap;
	}
	/**
	 * 根据id 操作公司id 分类id 去拿 操作公司名称 以及分类名称
	 */
	@Override
	public AffiliatedVO findInfo(AffiliatedVO affiliatedVO) {
		AffiliatedVO resuleAffiliatedVO = iAffiliatedDao.findInfo(affiliatedVO);
		return resuleAffiliatedVO;
	}
	
	@Override
	public AffiliatedVO findByoperationCompany(AffiliatedVO affiliatedVO) {
		AffiliatedVO resuleAffiliatedVO = iAffiliatedDao.findByoperationCompany(affiliatedVO);
		return resuleAffiliatedVO;
	}
	
	/**
	 * 查询挂靠商基本信息（list）
	 */
	@Override
	public List<AffiliatedVO> findAffiliateds(AffiliatedVO affiliatedVO) {
		List<AffiliatedVO> list = iAffiliatedDao.findAffiliateds(affiliatedVO);
		return list;
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
