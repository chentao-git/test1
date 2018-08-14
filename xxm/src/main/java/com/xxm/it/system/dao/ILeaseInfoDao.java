package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.business.vo.BaseLeaseInfoVO;


public interface ILeaseInfoDao {
	/**
	 * 查询凭租物信息列表
	 * @param leaseInfoVO
	 * @return
	 */
	public List<BaseLeaseInfoVO> findLeaseInfoList(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 查询数据总数
	 * @param leaseInfoVO
	 * @return
	 */
	public int findListCount(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 添加凭租物信息
	 * @param leaseInfoVO
	 */
	public void addLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 根据id去查询详情信息
	 * @param leaseInfoVO
	 * @return
	 */
    public BaseLeaseInfoVO findLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO);
    /**
     * 修改凭租物信息
     * @param leaseInfoVO
     */
    public void updateLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO);
    /**
     * 根据车产类型值去拿name
     * @param leaseInfoVO
     * @return
     */
    public BaseLeaseInfoVO findcarTypeName(BaseLeaseInfoVO leaseInfoVO);
    /**
     * 删除凭租物信息（仅仅改变status值 并不删除数据）
     * @param leaseInfoVO
     */
    public void deleteLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
    
    /**
	 * 新增“业务”租赁信息
	 * 
	 * @param leaseInfoVO
	 * @return
	 */
	public void addBusinessLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
	
	/**
	 * 查询“业务”租凭物信息
	 * 
	 * @param leaseInfoVO
	 * @return
	 */
	public BaseLeaseInfoVO findBusinessLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
	
	/**
	 * 修改“业务”租凭物信息
	 * 
	 * @param leaseInfoVO
	 * @return
	 */
	public void updateBusinessLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
}
