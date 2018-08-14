package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.dao.IAttachmentDao;
import com.xxm.it.system.service.IAttachmentService;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.vo.AttachmentTextVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class AttachmentServiceImpl implements IAttachmentService {
	private static Logger logger = Logger.getLogger(AttachmentServiceImpl.class); // ？.？
	@Resource
	private IAttachmentDao attachmentDao;

	/**
	 * 附件列表查询
	 * 
	 * @param 查询参数
	 * @return JSONObject 返回JSON对象结果
	 */
	public JSONObject findAttList(AttachmentTextVO attachmentTextVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = attachmentDao.findAttachmentListCount(attachmentTextVO);
		// 查询集合数据
		List<AttachmentTextVO> resultLsit = new ArrayList<AttachmentTextVO>();
		if (total > 0) {
			resultLsit = attachmentDao.findAttachmentList(attachmentTextVO);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);

		return jsonObject;
	}

	/**
	 * 添加附件
	 * 
	 * @param attachmentTextVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> addAttachment(AttachmentTextVO attachmentTextVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 添加附件
			attachmentDao.addAttachment(attachmentTextVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加附件成功！");
			logger.info("添加附件成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加附件失败！");
			logger.info("添加附件失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改附件信息
	 * 
	 * @param attachmentTextVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> updateAttachment(AttachmentTextVO attachmentTextVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 修改附件
			attachmentDao.updateAttachment(attachmentTextVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "修改附件成功！");
			logger.info("修改附件成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改附件失败！");
			logger.info("修改附件失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 查询附件信息
	 * 
	 * @param attachmentTextVO
	 *            参数
	 * 
	 * @return resultAttachment 返回结果
	 */
	public AttachmentTextVO findAttachment(AttachmentTextVO attachmentTextVO) {
		// 查询附件信息
		AttachmentTextVO resultAttachment = attachmentDao.findAttachment(attachmentTextVO);
		return resultAttachment;
	}

	/**
	 * 查询附件集合信息
	 * 
	 * @param attachmentTextVO
	 *            参数
	 * 
	 * @return resultAttachment 返回结果
	 */
	public List<AttachmentTextVO> findAttachments(AttachmentTextVO attachmentTextVO) {
		// 查询附件信息
		List<AttachmentTextVO> findAttachments = attachmentDao.findAttachments(attachmentTextVO);
		return findAttachments;
	}

	/**
	 * 删除附件信息
	 * 
	 * @param attachmentTextVO
	 *            参数
	 * @return resultMap 返回结果消息
	 */
	public Map<String, Object> deleteAttachment(AttachmentTextVO attachmentTextVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 删除附件
			attachmentTextVO.setAttachmentStatus(BaseDataConstant.STATUS_DELETE);
			attachmentDao.deleteAttachment(attachmentTextVO);
			resultMap.put("result", Boolean.TRUE);
			logger.info("删除附件成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除附件失败！");
			logger.info("删除附件失败！" + e.getMessage());
		}
		return resultMap;
	}

}