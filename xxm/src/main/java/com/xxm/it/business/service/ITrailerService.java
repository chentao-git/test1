package com.xxm.it.business.service;

import java.util.List;
import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.TrailerFileVo;
import com.xxm.it.business.vo.TrailerVO;

import net.sf.json.JSONObject;

@Path(value = "/trailerService")
public interface ITrailerService {

	/**
	 * 拖车信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	@POST
	@Path("/findTrailerList")
	public JSONObject findTrailerList(TrailerVO trailerVO);
	
	/**
	 * 贷款编号查询信息
	 * @return
	 */
	@POST
	@Path("/loanContractNoQuery")
	public TrailerVO queryLoanNo(TrailerVO trailerVO);

	/**
	 * 拖车添加
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/addTrailer")
	public Map<String, Object> addTrailer(TrailerVO trailerVO);

	/**
	 * 拖车审核
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/trailerAppove")
	public Map<String, Object> trailerAppove(TrailerVO trailerVO);

	/**
	 * 车辆入库
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/carGarage")
	public Map<String, Object> carGarage(TrailerVO trailerVO);

	/**
	 * 最终处理
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	@POST
	@Path("/finalProcessing")
	public Map<String, Object> finalProcessing(TrailerVO trailerVO);

	/**
	 * 详情查询
	 * 
	 * @param trailerVO
	 * @return
	 */
	@POST
	@Path("/detailsQueryInfo")
	public TrailerVO detailsQueryInfo(TrailerVO trailerVO);
	
	/**
	 * 人保委托信息保存
	 * 
	 * @param filevo
	 * @return
	 */
	@POST
	@Path("/trailerFileInfo")
	public Map<String, Object> saveFile(TrailerFileVo filevo);

	/**
	 * 查询人保委托的文件信息
	 * 
	 * @return
	 */
	@POST
	@Path("/queryTrailerImageInfo")
	public List<TrailerFileVo> queryTrailerImageInfo(TrailerFileVo filevo);
	/**
	 * 车辆入库信息查询
	 * @param trailerVO
	 * @return
	 */
	@POST
	@Path("/detailsQuery")
	public TrailerVO detailsQuery(TrailerVO trailerVO);
}
