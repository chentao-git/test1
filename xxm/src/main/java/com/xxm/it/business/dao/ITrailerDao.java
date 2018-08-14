package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.TrailerFileVo;
import com.xxm.it.business.vo.TrailerVO;

public interface ITrailerDao {
	/*
	 * 查询拖车集合
	 */
	public List<TrailerVO> findTrailerList(TrailerVO trailerVO);

	/*
	 * 查询拖车数量
	 */
	public int findTrailerListCount(TrailerVO trailerVO);

	/*
	 * 查询拖车信息
	 */
	public TrailerVO findTrailer(TrailerVO trailerVO);

	/*
	 * 增加拖车
	 */
	public void addTrailer(TrailerVO trailerVO);

	/*
	 * 修改拖车
	 */
	public void updateTrailer(TrailerVO trailerVO);

	/*
	 * 查询详细信息
	 */
	public TrailerVO detailsQueryInfo(TrailerVO trailerVO);

	/*
	 * 保存人保委托信息
	 */
	public void saveFile(TrailerFileVo filevo);

	/*
	 * 委托文件信息查询
	 */
	public List<TrailerFileVo> queryTrailerImageInfo(TrailerFileVo filevo);
	
	/*
	 * 车辆入库信息查询
	 */
	public TrailerVO detailsQuery(TrailerVO trailerVO);
	
	/*
	 *贷款编号查询 
	 */
	public TrailerVO loanContractNoQuery(TrailerVO trailerVO);

}
