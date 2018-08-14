package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.AttachmentTextVO;

public interface IAttachmentDao {

	/**
	 * 查询附件列表
	 * 
	 * @param AttachmentTextVO
	 * @return
	 */
	public List<AttachmentTextVO> findAttachmentList(AttachmentTextVO attachmentTextVO);

	/**
	 * 查询附件列表总数
	 * 
	 * @param attachmentTextVO
	 * @return
	 */

	public int findAttachmentListCount(AttachmentTextVO attachmentTextVO);

	/**
	 * 添加附件
	 * 
	 * @param attachmentTextVO
	 * @return
	 */
	public void addAttachment(AttachmentTextVO attachmentTextVO);

	/**
	 * 查询附件
	 * 
	 * @param attachmentTextVO
	 * @return
	 */
	public AttachmentTextVO findAttachment(AttachmentTextVO attachmentTextVO);

	/**
	 * 删除附件
	 * 
	 * @param attachmentIds
	 * @return
	 */
	public void deleteAttachment(AttachmentTextVO attachmentTextVO);
	
	/**
	 * 删除附件
	 * 
	 * @param attachmentIds
	 * @return
	 */
	public void deleteAttachments(AttachmentTextVO attachmentTextVO);

	/**
	 * 查询附件
	 * 
	 * @param attachmentTextVO
	 * @return
	 */
	public List<AttachmentTextVO> findAttachments(AttachmentTextVO attachmentTextVO);

	/**
	 * 修改附件
	 * 
	 * @param attachmentTextVO
	 * @return
	 */
	public void updateAttachment(AttachmentTextVO attachmentTextVO);

}
