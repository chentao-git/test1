package com.xxm.it.system.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.AttachmentTextVO;

/**
 * 文件上传 逻辑接口类
 * 
 * @author Administrator
 *
 */
@Path(value = "/uploadFileService")
public interface IUploadFileService {

	/**
	 * 上传文件
	 * 
	 * @param policyVo
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@POST
	@Path("/uploadFile")
	public String uploadFile() throws Exception;

	/**
	 * 文件临时目录地址
	 * 
	 * @return uploadPath 文件路径
	 * @throws Exception
	 */
	public String getFileTempCatalogPath(String Str) throws Exception;

	/**
	 * 上传视频
	 * 
	 * @param policyVo
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@POST
	@Path("/uploadVideo")
	public String uploadVideo() throws Exception;

	/**
	 * 上传图片
	 * 
	 * @param policyVo
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@POST
	@Path("/uploadImg")
	public String uploadImg() throws Exception;
	
	/**
	 * 上传文档
	 * 
	 * @param policyVo
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@POST
	@Path("/uploadDocument")
	public String uploadDocument() throws Exception;

	/**
	 * 删除附件
	 * 
	 * @param attrVO
	 * @return Map<String, Object>
	 * 
	 */
	@POST
	@Path("/deleteAttachment")
	public Map<String, Object> deleteAttachment(AttachmentTextVO attrVO);

}
