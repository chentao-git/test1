package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.GpsInfoVO;
import com.xxm.it.business.vo.InstallVO;

import net.sf.json.JSONObject;

@Path(value = "/gpsInfoService")
public interface IGpsInfoService {
	/**
	 * gps型号列表查询
	 * 
	 * @return
	 */
	@POST
	@Path("/findGpsInfoList")
	public JSONObject findGpsInfoList(GpsInfoVO gpsInfoVO);

	/**
	 * 
	 * 编辑gps型号信息
	 * 
	 * @param couponVO
	 * @return
	 */
	@POST
	@Path("/addGpsInfo")
	public Map<String, Object> addGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 修改gps型号信息
	 * 
	 * @return JSONObject
	 */
	@POST
	@Path("/updateGpsInfo")
	public Map<String, Object> updateGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 
	 * 查询该项目的全部资料
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	@POST
	@Path("/findGpsInfo")
	public GpsInfoVO findGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 
	 * 删除gps型号信息
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	@POST
	@Path("/deleteGpsInfo")
	public Map<String, Object> deleteGpsInfo(GpsInfoVO gpsInfoVO);
	/**
	 * 查询gps型号列表
	 * @return
	 */
	@POST
	@Path("/findGpsModelList")
	public JSONObject findGpsModelList();
	/**
	 * gps入仓
	 * @param objs
	 * @return
	 */
	@POST
	@Path("/addEntryGpsInfo")
	public Map<String, Object> addEntryGpsInfo(String objs);
	/**
	 * sp商名称列表
	 * @return
	 */
	@POST
	@Path("/findProviderList")
	public JSONObject findProviderList();
	/**
	 * gps销售
	 * @return
	 */
	@POST
	@Path("/addOutWarehouseInfo")
	public Map<String, Object> addOutWarehouseInfo(String objs);
	/**
	 * gps安装（根据申请编号查询车辆信息）
	 * @param gpsInfoVO
	 * @return
	 */
	@POST
	@Path("/findLeaseInfo")
	public GpsInfoVO findLeaseInfo(GpsInfoVO gpsInfoVO);
	/**
	 * gps安装（添加安装信息）
	 * @param InstallVO
	 * @return
	 */
	@POST
	@Path("/addInstall")
	public Map<String, Object> addInstall(InstallVO InstallVO);
}
