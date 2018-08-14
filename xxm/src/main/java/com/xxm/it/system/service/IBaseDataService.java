package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.BaseDataVO;

import net.sf.json.JSONObject;

/**
 * 基础数据逻辑处理接口类
 * 
 * @author Administrator
 *
 */
@Path(value = "/baseDataService")
public interface IBaseDataService {
	/**
	 * 根据条件查询基础数据信息
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVO>
	 */
	@POST
	@Path("/findBaseDataListByCode")
	public List<BaseDataVO> findBaseDataListByCode(BaseDataVO baseDataVO);

	/**
	 * 查询基础数据信息
	 * 
	 * @param baseDataVO
	 * @return JSONObject
	 */
	@POST
	@Path("/findBaseDataList")
	public JSONObject findBaseDataList(BaseDataVO baseDataVO);

	/**
	 * 添加基础数据
	 * 
	 * @param baseDataVO
	 * @return int
	 */
	@POST
	@Path("/addBaseData")
	public Map<String, Object> addBaseData(BaseDataVO baseDataVO);

	/**
	 * 修改基础数据
	 * 
	 * @param baseDataVO
	 * @return int
	 */
	@POST
	@Path("/updateBaseData")
	public Map<String, Object> updateBaseData(BaseDataVO baseDataVO);

	/**
	 * 删除基础数据
	 * 
	 * @param baseDataVO
	 * @return int
	 */
	@POST
	@Path("/deleteBaseData")
	public Map<String, Object> deleteBaseData(BaseDataVO baseDataVO);

	/**
	 * 根据baseId查询基础数据信息
	 * 
	 * @param baseDataVO
	 * @return baseDataVO
	 */
	@POST
	@Path("/findBaseDataInfo")
	public BaseDataVO findBaseDataInfo(BaseDataVO baseDataVO);

	/**
	 * 根据基础数据code从缓存中获取基础数据
	 * 
	 * @param baseDataVO
	 * @return BaseDataVO
	 */
	@POST
	@Path("/findBaseDataListByCodeEhcache")
	public BaseDataVO findBaseDataListByCodeEhcache(BaseDataVO baseDataVO);

	/**
	 * 初始化基础数据
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVo>
	 */
	@POST
	@Path("/initBaseData")
	public void initBaseData();

	/**
	 * 初始化定时任务配置数据
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVo>
	 */
	@POST
	@Path("/initTaskBaseData")
	public void initTaskBaseData(BaseDataVO baseDataVO);

}
