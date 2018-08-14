package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IActIdGroupDao;
import com.xxm.it.business.service.IActIdGroupService;
import com.xxm.it.business.vo.ActIdGroupVO;
import com.xxm.it.system.vo.UserVO;

import net.sf.json.JSONObject;
@Component
@Transactional
public class ActIdGroupServiceImpl implements IActIdGroupService{
	private static Logger logger = Logger.getLogger(ActIdGroupServiceImpl.class);
	@Resource
	private IActIdGroupDao actIdGroupDao;
	/*
	 * 查询数据
	 */
	public JSONObject findActIdGroupList(ActIdGroupVO actIdGroupVO) {
		JSONObject jsonObject =new JSONObject();
		int total =actIdGroupDao.findActIdGroupListCount(actIdGroupVO);
		List<ActIdGroupVO> resultList = new ArrayList<ActIdGroupVO>();
		if(total>0){
			resultList = actIdGroupDao.findActIdGroupList(actIdGroupVO);
		}
		jsonObject.put("rows", resultList);
		jsonObject.put("total", total);
		return jsonObject;
	}
	/*
	 * 增加数据
	 */
	public Map<String, Object> addActIdGroup(ActIdGroupVO actIdGroupVO) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			
			actIdGroupDao.addActIdGroup(actIdGroupVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加数据成功!");
			logger.info("添加数据成功!");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加数据失败!");
			logger.info("添加数据失败!"+ e.getMessage());
		}
		
		return resultMap;
	}
	/*
	 * 查询数据
	 */
	public ActIdGroupVO findActIdGroup(ActIdGroupVO actIdGroupVO) {
		ActIdGroupVO resultActIdGroupVO =actIdGroupDao.findActIdGroup(actIdGroupVO);
		return resultActIdGroupVO;
	}
	/*
	 * 修改数据
	 */
	public Map<String, Object> updateActIdGroup(ActIdGroupVO actIdGroupVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			actIdGroupDao.updateActIdGroup(actIdGroupVO);
			resultMap.put("result",Boolean.TRUE);
			resultMap.put("msg", "修改数据成功!");
			logger.info("修改数据成功!");
		}catch(Exception e){
			resultMap.put("result",Boolean.FALSE);
			resultMap.put("msg", "修改数据失败!");
			logger.info("修改数据失败!"+ e.getMessage());
		}
		return resultMap;
	}
	/*
	 * 删除数据
	 */
	public Map<String, Object> deleteActIdGroup(ActIdGroupVO actIdGroupVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			actIdGroupDao.deleteActIdGroup(actIdGroupVO);
			resultMap.put("result",Boolean.TRUE);
			logger.info("删除数据成功");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除数据失败！");
			logger.info("删除数据失败！" + e.getMessage());
		}
		return resultMap;
	}
	@Override
	/*
	 * 查询user表数据
	 */
	public JSONObject findGroupUserList(ActIdGroupVO actIdGroupVO) {
		JSONObject jsonObject =new JSONObject();
		List<UserVO> resultList = new ArrayList<UserVO>();
		resultList = actIdGroupDao.findGroupUserList(actIdGroupVO);
		jsonObject.put("rows", resultList);
		return jsonObject;
	}

	
	/*
	 * 增加数据
	 */
	public Map<String, Object> addMembership(ActIdGroupVO actIdGroupVO) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			
			actIdGroupDao.deleteMembership(actIdGroupVO);
			
			for(int i = 0 ; i < actIdGroupVO.getgPUserIds().length ; i++){
				ActIdGroupVO vo = new ActIdGroupVO();
				vo.setUserId(actIdGroupVO.getgPUserIds()[i]);
				vo.setActIdGroupID(actIdGroupVO.getActIdGroupID());
				actIdGroupDao.addMembership(vo);
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加数据成功!");
			logger.info("添加数据成功!");
		}catch(Exception e){
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加数据失败!");
			logger.info("添加数据失败!"+ e.getMessage());
		}
		
		return resultMap;
	}
}
