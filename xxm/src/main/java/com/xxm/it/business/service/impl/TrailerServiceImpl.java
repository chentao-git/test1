package com.xxm.it.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.dao.ITrailerDao;
import com.xxm.it.business.service.ITrailerService;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.business.vo.TrailerFileVo;
import com.xxm.it.business.vo.TrailerVO;
import com.xxm.it.system.dao.IAttachmentDao;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;

import net.sf.json.JSONObject;

@Component
@Transactional
public class TrailerServiceImpl implements ITrailerService {

	private static Logger logger = Logger.getLogger(TrailerServiceImpl.class);

	@Resource
	private IApplyDao applyDao;

	@Resource
	private IAttachmentDao attachmentDao;// 附件dao

	@Resource
	private ITrailerDao trailerDao;// 拖车dao

	/**
	 * 拖车信息列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findTrailerList(TrailerVO trailerVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = trailerDao.findTrailerListCount(trailerVO);
		// 查询集合数据
		List<TrailerVO> resultLsit = new ArrayList<TrailerVO>();
		if (total > 0) {
			resultLsit = trailerDao.findTrailerList(trailerVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 贷款编号查询
	 */
	@Override
	public TrailerVO queryLoanNo(TrailerVO trailerVO) {
		// TODO Auto-generated method stub
		return trailerDao.loanContractNoQuery(trailerVO);
	}


	/**
	 * 添加拖车信息
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addTrailer(TrailerVO trailerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			trailerVO.setTrailerStatus(BaseDataConstant.TRAILER_APPLY);
			trailerDao.addTrailer(trailerVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "申请拖车成功！");
			logger.info("申请拖车成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "申请拖车失败！");
		}
		return resultMap;
	}

	/**
	 * 拖车审核
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> trailerAppove(TrailerVO trailerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			trailerVO.setTrailerStatus(BaseDataConstant.TRAILER_APPOVE);
			trailerDao.updateTrailer(trailerVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "审核成功！");
			logger.info("申请拖车成功");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "审核失败！");
		}
		return resultMap;
	}

	/**
	 * 车辆入库
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> carGarage(TrailerVO trailerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (trailerVO.getTrailerId() != null && !"".equals(trailerVO.getTrailerId())) {
				TrailerFileVo filevo = trailerVO.getTrailerFileVO();
				for (int i = 0; i < filevo.getFileNames().length; i++) {
					filevo.setFileName(filevo.getFileNames()[i]);
					filevo.setFileURL(filevo.getFileURLs()[i]);
					filevo.setFileId(filevo.getFileIds()[i]);
					trailerDao.saveFile(filevo);
				}
				trailerVO.setTrailerStatus(BaseDataConstant.TRAILER_IN_STORAGE);
				trailerDao.updateTrailer(trailerVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "车辆入库成功！");
				logger.info("车辆入库文件信息保存成功");
				logger.info("车辆入库信息保存成功");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "车辆入库失败！");
		}
		return resultMap;
	}

	/**
	 * 最终处理
	 * 
	 * @param trailerVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> finalProcessing(TrailerVO trailerVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (trailerVO.getTrailerId() != null && !"".equals(trailerVO.getTrailerId())) {
				trailerVO.setTrailerStatus(BaseDataConstant.TRAILER_ACCOMPLISH);
				trailerDao.updateTrailer(trailerVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "处理成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "处理失败！");
		}
		return resultMap;
	}

	/**
	 * 详情查询
	 */
	@Override
	public TrailerVO detailsQueryInfo(TrailerVO trailerVO) {
		TrailerVO trailerVOs = trailerDao.detailsQueryInfo(trailerVO);
		return trailerVOs;
	}

	/**
	 * 保存人保委托的文件信息
	 */
	@Override
	public Map<String, Object> saveFile(TrailerFileVo filevo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			for (int i = 0; i < filevo.getFileNames().length; i++) {
				filevo.setFileName(filevo.getFileNames()[i]);
				filevo.setFileURL(filevo.getFileURLs()[i]);
				filevo.setFileId(filevo.getFileIds()[i]);
				trailerDao.saveFile(filevo);
			}
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "保存成功！");
			logger.info("人保委托信息保存成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "保存失败！");
		}
		return resultMap;
	}

	/**
	 * 查询人保委托的文件信息
	 */
	@Override
	public List<TrailerFileVo> queryTrailerImageInfo(TrailerFileVo filevo) {
		if (filevo.getTrailerId() != null && !"".equals(filevo.getTrailerId())) {
			List<TrailerFileVo> listTrfivo = trailerDao.queryTrailerImageInfo(filevo);
			return listTrfivo;
		}
		return null;
	}

	/**
	 * 车辆入库信息查询
	 */
	@Override
	public TrailerVO detailsQuery(TrailerVO trailerVO) {
		// TODO Auto-generated method stub
		return trailerDao.detailsQuery(trailerVO);
	}

	
}
