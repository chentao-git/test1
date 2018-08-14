package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.GpsInfoVO;
import com.xxm.it.business.vo.InstallVO;
import com.xxm.it.business.vo.ProviderVO;

public interface IGpsInfoDao {
	/**
	 * 查询gps列表
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public List<GpsInfoVO> findGpsInfoList(GpsInfoVO gpsInfoVO);

	/**
	 * 查询gps型号信息
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public GpsInfoVO findGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 查询gps型号总数
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public int findGpsInfoListCount(GpsInfoVO gpsInfoVO);

	/**
	 * 添加gps型号信息
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public void addGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 删除gps型号信息
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public void deleteGpsInfo(GpsInfoVO gpsInfoVO);

	/**
	 * 修改gps型号信息
	 * 
	 * @param GpsInfoVO
	 * @return
	 */
	public void updateGpsInfo(GpsInfoVO gpsInfoVO);
	/**
	 * 查询gps型号
	 * @return
	 */
	public List<GpsInfoVO> findGpsModelList();
	/**
	 * gps入仓
	 * @param gpsInfoVO
	 */
	public void addEntryGpsInfo(GpsInfoVO gpsInfoVO);
	/**
	 * sp商名称列表
	 * @return
	 */
	public List<ProviderVO> findProviderList(); 
	/**
	 * gps出仓
	 * @param gpsInfoVO
	 */
	public void addOutWarehouseInfo(GpsInfoVO gpsInfoVO);
	/**
	 * gps安装（根据申请编号查询车辆信息）
	 * @param gpsInfoVO
	 */
	public GpsInfoVO findLeaseInfo(GpsInfoVO gpsInfoVO);
	/**
	 * gps安装（添加安装信息）
	 * @param InstallVO
	 * @return
	 */
	public int addInstall(InstallVO InstallVO);
	/**
	 * gps安装（更新gps信息）
	 * @param gpsInfoVO
	 */
	public void updateGpsInfoVO(GpsInfoVO gpsInfoVO);
}
