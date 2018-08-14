package com.xxm.it.system.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.system.service.IAttachmentService;
import com.xxm.it.system.service.IUploadFileService;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.vo.AttachmentTextVO;

import net.sf.json.JSONObject;

/**
 * 文件上传 接口实现类
 * 
 * @author Administrator
 *
 */
@Component
@Transactional
public class UploadFileServiceImpl implements IUploadFileService {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(UploadFileServiceImpl.class);

	/**
	 * 获取reques对象
	 */
	@Autowired
	private HttpServletRequest request;

	@Resource
	private IUserService userService;

	@Resource
	private IAttachmentService attachmentService;

	/**
	 * 上传文件
	 * 
	 * @param policyVo
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String uploadFile() throws Exception {
		// 返回消息对象
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			// 设置请求编码
			request.setCharacterEncoding(XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			// 配置上传参数
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
			factory.setSizeThreshold(XxmConstant.BaseDataConstant.MEMORY_THRESHOLD);
			// 设置临时存储目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大文件上传值
			upload.setFileSizeMax(XxmConstant.BaseDataConstant.MAX_FILE_SIZE);
			// 设置最大请求值 (包含文件和表单数据)
			upload.setSizeMax(XxmConstant.BaseDataConstant.MAX_REQUEST_SIZE);
			// 临时文件目录
			String uploadPath = getFileTempCatalogPath(XxmConstant.BaseDataConstant.TEMP_FILE_CATALOG);
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);
			String filePath = "";// 文件路径
			if (formItems != null && formItems.size() > 0) {
				// 处理在非表单字段，例如文件对象
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();// 上传文件的真实名称
						String newFileName = System.currentTimeMillis() + "_" + fileName;
						filePath = uploadPath + File.separatorChar + newFileName;
						logger.info("文件重命名地址：" + filePath);
						// 保存文件到硬盘
						item.write(new File(filePath));
					}
				}
			}
			msgMap.put("flag", true);
			msgMap.put("msg", "上传文件成功！");
			msgMap.put("url", filePath);// 返回文件地址
		} catch (Exception ex) {
			msgMap.put("flag", false);
			msgMap.put("msg", "上传文件失败，请重试！ERROR INFO:" + ex.getMessage());
			logger.error("上传文件失败！" + ex);
			throw new Exception(ex);
		}
		String jsonMsg = JSONObject.fromObject(msgMap).toString();
		logger.info("返回消息：" + jsonMsg);
		return jsonMsg;
	}

	/**
	 * 获取服务器文件临时目录地址，不存在则重新创建
	 * 
	 * @return uploadPath 临时文件目录路径
	 * @throws Exception
	 */
	public String getFileTempCatalogPath(String Str) throws Exception {
		// 项目服务器地址
		String appPath = new File(request.getSession().getServletContext().getRealPath("/")).getParentFile()
				.getParentFile().getPath();
		// 文件存放临时目录地址
		String uploadPath = appPath + Str;
		logger.error("文件存放临时目录地址：" + uploadPath);
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		// 文件存放到file目录下,如果该文件夹不存在,则创建
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			boolean mkdirs = uploadDir.mkdirs();
			if (!mkdirs) {
				logger.error("getFileTempCatalogPath():create directory error.");
				throw new Exception("创建文件目录失败！ " + uploadDir);
			}
		}
		return uploadPath;
	}

	/**
	 * 上传视频
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String uploadVideo() throws Exception {
		// 临时文件目录
		return uploadAttachment(XxmConstant.BaseDataConstant.MAX_VIDEO_FILE_SIZE,XxmConstant.BaseDataConstant.MAX_VIDEO_REQUEST_SIZE
				, XxmConstant.BaseDataConstant.VIDEO_FILE_CATALOG,XxmConstant.BaseDataConstant.ATTACHMENT_TYPE_VIDEO);
	}

	/**
	 * 上传图片
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String uploadImg() throws Exception {
		return uploadAttachment(XxmConstant.BaseDataConstant.MAX_IMG_FILE_SIZE,XxmConstant.BaseDataConstant.MAX_IMG_REQUEST_SIZE
				, XxmConstant.BaseDataConstant.IMG_FILE_CATALOG,XxmConstant.BaseDataConstant.ATTACHMENT_TYPE_IMG);
		
	}
	
	/**
	 * 上传文档
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String uploadDocument() throws Exception {
		// 临时文件目录
		return uploadAttachment(XxmConstant.BaseDataConstant.MAX_FILE_SIZE,XxmConstant.BaseDataConstant.MAX_REQUEST_SIZE, 
				XxmConstant.BaseDataConstant.TEMP_FILE_CATALOG,XxmConstant.BaseDataConstant.ATTACHMENT_TYPE_DOCUMENT);
	}

	/**
	 * 上传附件
	 * 
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String uploadAttachment(int fileSizeMax, int fileMinSize, String catalog,String attachmentType) throws Exception {
		// 返回消息对象
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			// 设置请求编码
			request.setCharacterEncoding(XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			// 配置上传参数
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
			factory.setSizeThreshold(XxmConstant.BaseDataConstant.MEMORY_THRESHOLD);
			// 设置临时存储目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大文件上传值
			upload.setFileSizeMax(fileSizeMax);
			// 设置最大请求值 (包含文件和表单数据)
			upload.setSizeMax(fileMinSize);
			// 上传地址
			String uploadPath = getFileTempCatalogPath(catalog);
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);
			// 文件路径
			String filePath = "";
			// 返回图片路径
			String serverFilePath = "";
			// 文件服务器地址
			String serverFileDir = catalog + File.separatorChar;
			//前台参数
			String attachmentItemID=request.getParameter("attachmentItemID");
			String extendField1=request.getParameter("extendField1");
			String extendField2=request.getParameter("extendField2");
			String attachmentItem=request.getParameter("attachmentItem");
			if (formItems != null && formItems.size() > 0) {
				// 存放附件Id字符
				String attrId = "";
				String attrUrl = "";
				String attrName= "";
				// 处理在非表单字段，例如文件对象
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();// 上传文件的真实名称
						String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
						filePath = uploadPath + File.separatorChar + newFileName;
						logger.info("文件重命名地址：" + filePath);
						// 保存文件到硬盘
						item.write(new File(filePath));
						serverFilePath = serverFileDir + newFileName;
						// 保存附件信息
						AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
						attachmentTextVO.setAttachmentName(item.getName());
						attachmentTextVO.setAttachmentSysName(newFileName);
						attachmentTextVO.setAttachmentPath(serverFilePath);
						attachmentTextVO.setAttachmentType(attachmentType);
						attachmentTextVO.setAttachmentItem(attachmentItem);
						attachmentTextVO.setAttachmentItemID(attachmentItemID);
						attachmentTextVO.setExtendField1(extendField1);//大类
						attachmentTextVO.setExtendField2(extendField2);//小类
						attachmentService.addAttachment(attachmentTextVO);
						attrId += (attachmentTextVO.getAttachmentID() + ",");
						attrUrl += (serverFilePath + ",");
						attrName +=(item.getName()+",");
					}
				}
				if (!"".equals(attrId)) {
					attrId = attrId.substring(0, attrId.lastIndexOf(","));
				}
				if (!"".equals(attrUrl)) {
					attrUrl = attrUrl.substring(0, attrUrl.lastIndexOf(","));
				}
				msgMap.put("attrId", attrId);// 返回批量附件信息
				msgMap.put("url", attrUrl);// 返回批量附件路径
				msgMap.put("attrName", attrName);// 返回批量附件名称
			}
			if (msgMap.get("attrId") != null) {
				msgMap.put("flag", true);
				msgMap.put("msg", "上传文件成功！");
			} else {
				msgMap.put("flag", false);
				msgMap.put("msg", "上传文件失败！");
			}
		} catch (Exception ex) {
			msgMap.put("flag", false);
			msgMap.put("msg", "上传文件失败，请重试！ERROR INFO:" + ex.getMessage());
			logger.error("上传文件失败！" + ex);
			throw new Exception(ex);
		}
		String jsonMsg = JSONObject.fromObject(msgMap).toString();
		logger.info("返回消息：" + jsonMsg);
		return jsonMsg;
	}

	/**
	 * 删除附件
	 * 
	 * @param attrVO
	 * @return Map<String, Object>
	 * 
	 */
	public Map<String, Object> deleteAttachment(AttachmentTextVO attrVO) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			// 删除附件
			String[] attachemntIds = new String[] { attrVO.getAttachmentID() };
			attrVO.setAttachmentIds(attachemntIds);
			attachmentService.deleteAttachment(attrVO);
			msgMap.put("flag", Boolean.TRUE);
			msgMap.put("msg", "删除文件成功！");
		} catch (Exception ex) {
			msgMap.put("flag", Boolean.FALSE);
			msgMap.put("msg", "删除文件失败，请重试！ERROR INFO:" + ex.getMessage());
			logger.error("删除文件失败！" + ex);
		}
		return msgMap;
	}

}
