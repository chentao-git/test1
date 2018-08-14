package com.xxm.it.piic.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 影像资料传输接口
 * 
 * @author Administrator
 *
 */
@Path(value = "/PICCUploadFileService")
public interface IPICCUploadFileService {

	/**
	 * 上传视频
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	@POST
	@Path("/uploadVideo")
	public String uploadVideo() throws Exception;

	/**
	 * 上传图片
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	@POST
	@Path("/uploadImg")
	public String uploadImg() throws Exception;

	/**
	 * 上传文档
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	@POST
	@Path("/uploadDocument")
	public String uploadDocument() throws Exception;

}
