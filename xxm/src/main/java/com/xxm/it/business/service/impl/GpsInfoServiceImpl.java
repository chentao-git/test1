package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IGpsInfoDao;
import com.xxm.it.business.service.IGpsInfoService;
import com.xxm.it.business.vo.BaseLeaseInfoVO;
import com.xxm.it.business.vo.GpsInfoVO;
import com.xxm.it.business.vo.InstallVO;
import com.xxm.it.business.vo.ProviderVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component
@Transactional
public class GpsInfoServiceImpl implements IGpsInfoService{
	private static Logger logger = Logger.getLogger(GpsInfoServiceImpl.class);
	@Resource
	private IGpsInfoDao gpsInfoDao;
	/**
	 * gps型号信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findGpsInfoList(GpsInfoVO gpsInfoVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = gpsInfoDao.findGpsInfoListCount(gpsInfoVO);
		// 查询集合数据
		List<GpsInfoVO> resultLsit = new ArrayList<GpsInfoVO>();
		if (total > 0) {
			resultLsit = gpsInfoDao.findGpsInfoList(gpsInfoVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 添加gps型号信息
	 * 
	 * @param gpsInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addGpsInfo(GpsInfoVO gpsInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 添加gps型号信息
			gpsInfoVO.setGpsPlaceVL("0");
			gpsInfoDao.addGpsInfo(gpsInfoVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加型号信息成功！");
			logger.info("添加型号信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加型号信息失败！");
			logger.info("添加型号信息失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改gps型号信息
	 * 
	 * @param gpsInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> updateGpsInfo(GpsInfoVO gpsInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改gps型号信息
			gpsInfoDao.updateGpsInfo(gpsInfoVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改型号信息成功！");
			logger.info("修改型号信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改型号信息失败！");
			logger.info("修改型号信息失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询gps型号信息
	 * 
	 * @param GpsInfoVO
	 *            参数
	 * 
	 * @return resultGpsInfo 返回结果
	 */
	@Override
	public GpsInfoVO findGpsInfo(GpsInfoVO gpsInfoVO) {
		// 查询gps型号信息
		GpsInfoVO resultGpsInfoVO = gpsInfoDao.findGpsInfo(gpsInfoVO);
		return resultGpsInfoVO;
	}

	/**
	 * 删除gps型号信息
	 * 
	 * @param gpsInfoVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> deleteGpsInfo(GpsInfoVO gpsInfoVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 删除gps型号信息
			gpsInfoDao.deleteGpsInfo(gpsInfoVO);
			resultMap.put("result", Boolean.TRUE);
			logger.info("删除型号信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除型号信息失败！");
			logger.info("删除型号信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	/**
	 * 查询gps型号列表
	 */
	@Override
	public JSONObject findGpsModelList() {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据
		List<GpsInfoVO> resultLsit = gpsInfoDao.findGpsModelList();
		jsonObject.put("rows", resultLsit);
		return jsonObject;
	}
	/**
	 * gps入仓
	 */
	@Override
	public Map<String, Object> addEntryGpsInfo(String objs) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONArray ja = JSONArray.fromObject(objs); 
			JSONObject jsonObject = ja.getJSONObject(0);
			GpsInfoVO gpsEntryInfoVO = (GpsInfoVO)JSONObject.toBean(jsonObject, GpsInfoVO.class);  
			for (int i = 1; i < ja.size(); i++) {  
				JSONObject jsonObject2 = ja.getJSONObject(i);
				GpsInfoVO gpsInfoVO = (GpsInfoVO)JSONObject.toBean(jsonObject2, GpsInfoVO.class);  
				
				//组装入仓数据
				gpsInfoVO.setEntryMan(gpsEntryInfoVO.getEntryMan());
				gpsInfoVO.setEntryType(gpsEntryInfoVO.getEntryType());
				gpsInfoVO.setEntryTime(gpsEntryInfoVO.getEntryTime());
				//根据入仓类型 计算标准售价
//				int sellingPrice;
			
//				if(entryType.equals("1")){
//					 int cost = Integer.parseInt(costing);
//					  sellingPrice = cost+100;
//				}else{
//					int cost = Integer.parseInt(costing);
//					 sellingPrice = cost-(cost/10);
//				}
//				String s = Integer.toString(sellingPrice);
				//组装标准售价
//				gpsInfoVO.setSellingPrice(s);
				gpsInfoVO.setGpsPlaceVL("1");
				gpsInfoDao.addEntryGpsInfo(gpsInfoVO);
			} 
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加入仓信息成功！");
			logger.info("添加入仓信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加入仓信息失败！");
			logger.info("添加入仓信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	/**
	 * sp商名称列表
	 */
	@Override
	public JSONObject findProviderList() {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据
		List<ProviderVO> resultLsit = gpsInfoDao.findProviderList();
		jsonObject.put("rows", resultLsit);
		return jsonObject;
	}
	/**
	 * gps销售
	 */
	@Override
	public Map<String, Object> addOutWarehouseInfo(String objs) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONArray ja = JSONArray.fromObject(objs); 
			JSONObject jsonObject = ja.getJSONObject(0);
			GpsInfoVO gpsEntryInfoVO = (GpsInfoVO)JSONObject.toBean(jsonObject, GpsInfoVO.class);
			for (int i = 1; i < ja.size(); i++) {  
				JSONObject jsonObject2 = ja.getJSONObject(i);
				GpsInfoVO gpsInfoVO = (GpsInfoVO)JSONObject.toBean(jsonObject2, GpsInfoVO.class);  
				//获取销售成本 以及销售类型
				String costing =  gpsInfoVO.getCosting();
				String entryType = gpsEntryInfoVO.getOutWarehouseType();
				//组装销售数据
				gpsInfoVO.setOutWarehouseMan(gpsEntryInfoVO.getOutWarehouseMan());
				gpsInfoVO.setEntryType(entryType);
				gpsInfoVO.setOutWarehouseTime(gpsEntryInfoVO.getOutWarehouseTime());
				gpsInfoVO.setSpId(gpsEntryInfoVO.getSpId());
			    	  //销售
				if(entryType.equals("1")){
					  gpsInfoVO.setGpsPlaceVL("2");
					  //退还厂家
				}else if(entryType.equals("2")){
					  gpsInfoVO.setGpsPlaceVL("4");
					  //报废
				}else if(entryType.equals("3")){
					  gpsInfoVO.setGpsPlaceVL("5");
				}
				gpsInfoDao.addOutWarehouseInfo(gpsInfoVO);
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加销售信息成功！");
			logger.info("添销售仓信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加销售信息失败！");
			logger.info("添加销售信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	/**
	 *  gps安装（根据申请编号查询车辆信息）
	 */
	@Override
	public GpsInfoVO findLeaseInfo(GpsInfoVO gpsInfoVO) {
		GpsInfoVO gpsInfo = gpsInfoDao.findLeaseInfo(gpsInfoVO);
		return gpsInfo;
	}

	@Override
	public Map<String, Object> addInstall(InstallVO installVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//添加信息到安装表 返回id
			gpsInfoDao.addInstall(installVO);
			System.out.println(installVO.getInstallId());
			GpsInfoVO gpsInfo = new GpsInfoVO();
			InstallVO install = new InstallVO();
			//将返回的安装id
			install.setInstallId(installVO.getInstallId());
			
			String leaseInfoId =  installVO.getBaseLeaseInfoVO().getLeaseInfoId();
			BaseLeaseInfoVO baseLeaseInfoVO=new BaseLeaseInfoVO();
			baseLeaseInfoVO.setLeaseInfoId(leaseInfoId);
			
			
			gpsInfo.setInstallVO(install);
			gpsInfo.setBaseLeaseInfoVO(baseLeaseInfoVO);
			gpsInfo.setGpsPlaceVL("3");
			gpsInfo.setPriductSN(installVO.getProductSn());
			gpsInfoDao.updateGpsInfoVO(gpsInfo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加安装信息成功！");
			logger.info("添销安装信息成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加安装信息失败！");
			logger.info("添加安装信息失败！" + e.getMessage());
		}
		return resultMap;
	}
	
	}

