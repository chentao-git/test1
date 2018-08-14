package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.AttachmentTextVO;

import net.sf.json.JSONObject;

@Path(value = "/attrService") // 找到IAttachmentService接口
public interface IAttachmentService {

	/**
	 * 附件列表查询
	 * 
	 * @return
	 */
	@POST
	@Path("/findAttList")
	public JSONObject findAttList(AttachmentTextVO attachmentTextVO);

	/**
	 * 
	 * 增加附件
	 * 
	 * @param attachmentVo
	 * @return
	 */
	@POST
	@Path("/addAttachment")
	public Map<String, Object> addAttachment(AttachmentTextVO attachmentTextVO);

	/**
	 * 
	 * 附件查询
	 * 
	 * @param attachmentTextVo
	 * @return
	 */
	@POST
	@Path("/findAttachments")
	public List<AttachmentTextVO> findAttachments(AttachmentTextVO attachmentTextVO);

	/**
	 * 
	 * 附件删除
	 * 
	 * @param attachmentTextVo
	 * @return
	 */
	@POST
	@Path("/deleteAttachment")
	public Map<String, Object> deleteAttachment(AttachmentTextVO attachmentTextVo);

	/**
	 * 修改附件信息
	 * 
	 * @return JSONObject
	 */
	@POST
	@Path("/updateAttachment")
	public Map<String, Object> updateAttachment(AttachmentTextVO attachmentTextVo);
}
