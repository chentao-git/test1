package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.AffiliatedVO;

public interface IAffiliatedDao {

	/**
	 * 查询挂靠商列表
	 * 
	 * @param AttachmentTextVO
	 * @return
	 */
	public List<AffiliatedVO> findCist(AffiliatedVO affiliatedVO);
	/**
	 * 查询数据总数
	 * @param affiliatedVO
	 * @return
	 */
	public int findListCount(AffiliatedVO affiliatedVO);
	/**
	 * 添加挂靠公司
	 * @param affiliatedVO
	 */
	public void addAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 根据id查询信息
	 * @param affiliatedVO
	 * @return
	 */
	public AffiliatedVO findAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 修改挂靠公司信息
	 * @param affiliatedVO
	 */
	public void updateAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 删除挂靠公司
	 * @param affiliatedVO
	 */
	public void deleteAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 根据id 操作公司id 分类id 去拿 操作公司名称 以及分类名称
	 * @param affiliatedVO
	 * @return
	 */
	public AffiliatedVO findInfo(AffiliatedVO affiliatedVO);
	/**
	 * 根据操作公司，分类去拿对应的名字
	 */
	public AffiliatedVO findByoperationCompany(AffiliatedVO affiliatedVO);
	
	/**
	 * 查询挂靠商（list）
	 * 
	 * @param affiliatedVO
	 * @return
	 */
	public List<AffiliatedVO> findAffiliateds(AffiliatedVO affiliatedVO);
}
