package com.xxm.it.system.util;

import java.io.File;

/**
 * 常量类
 * 
 * @author Administrator
 *
 */
public class XxmConstant {

	/**
	 * 订单常量信息
	 * 
	 * @author Administrator
	 *
	 */
	public interface BaseDataConstant {
		/**
		 * 时间格式类型
		 */
		public final static String FORMATE_DATA1 = "yyyyMMdd";
		public final static String FORMATE_DATA2 = "yyyy-MM-dd";
		public final static String FORMATE_DATA3 = "yyyy年MM月dd日";
		public final static String FORMATE_DATA_TIME1 = "yyyyMMddHHmmss";
		public final static String FORMATE_DATA_TIME2 = "yyyy-MM-dd HH:mm:ss";
		public final static String FORMATE_DATA_TIME3 = "yyyy年MM月dd日 HH:mm:ss";

		/**
		 * 状态：0表示有效，1表示失效,-1表示作废
		 */
		public final static String STATUS_EFFECTIVE = "0";
		public final static String STATUS_INVALID = "1";
		public final static String STATUS_DELETE = "-1";

		/**
		 * InputStreamReader 编码
		 */
		public final static String INPUT_STREAM_READER_ENCODING = "UTF-8";

		/**
		 * 附件上传配置
		 */
		public static final int MEMORY_THRESHOLD = 3 * 1024 * 1024; // 3MB

		public static final int MAX_FILE_SIZE = 10 * 1024 * 1024;// 单个文件最大10MB
		public static final int MAX_REQUEST_SIZE = 20 * 1024 * 1024;// 批量文件最大200MB

		public static final int MAX_IMG_FILE_SIZE = 20 * 1024 * 1024; // 单张图片最大20MB
		public static final int MAX_IMG_REQUEST_SIZE = 200 * 1024 * 1024; // 批量上传最大图片200MB

		public static final int MAX_VIDEO_FILE_SIZE = 100 * 1024 * 1024; // 单个视频最大50MB
		public static final int MAX_VIDEO_REQUEST_SIZE = 500 * 1024 * 1024; // 批量视频最大500MB

		public final static String EXCEL_PREFIX_FOR_2007 = ".xlsx";// Excel2007版本及以上文件后缀
		public final static String EXCEL_PREFIX_FOR_2003 = ".xls";// Excel2003版本文件后缀
		public final static String CSV_PREFIX = ".csv";// CSV版本文件后缀

		public final static String TEMP_FILE_CATALOG = File.separatorChar + "upload" + File.separatorChar + "temp";// 上传文件临时目录
		public final static String IMG_FILE_CATALOG = File.separatorChar + "upload" + File.separatorChar + "imgFile";// 图片上传目录
		public final static String VIDEO_FILE_CATALOG = File.separatorChar + "upload" + File.separatorChar
				+ "videoFile";// 视频上传目录

		/**
		 * 管理员Id常量
		 */
		public final static String ADMIN_ID = "31";

		/**
		 * 附件模块
		 * 
		 * type:ACTIVITY
		 */
		public final static String ATTACHMENT_ITEM_ACTIVITY = "activity";

		/**
		 * 附件模块
		 * 
		 * type:项目申请
		 */
		public final static String ATTACHMENT_ITEM_APPLY_INFO = "applyInfo";

		/**
		 * 附件类型
		 * 
		 * 类型：图片
		 */
		public final static String ATTACHMENT_TYPE_IMG = "1";

		/**
		 * 附件类型
		 * 
		 * 类型：视屏
		 */
		public final static String ATTACHMENT_TYPE_VIDEO = "2";

		/**
		 * 附件类型
		 * 
		 * 类型：文档
		 */
		public final static String ATTACHMENT_TYPE_DOCUMENT = "3";

		/**
		 * 申请信息状态-作废
		 */
		public final static String APPLY_STATUS_DISCARD = "-2";

		/**
		 * 申请信息状态-新建项目状态
		 */
		public final static String APPLY_STATUS_INITIAL = "0";

		/**
		 * 申请信息状态-补充资料（保存）
		 */
		public final static String APPLY_STATUS_SUPPLEMENT_SAVE = "1";

		/**
		 * 申请信息状态-补充资料（提交）
		 */
		public final static String APPLY_STATUS_SUPPLEMENT_SUBMIT = "2";

		/**
		 * 申请信息状态-审核结果通知（预审）:审核通过
		 */
		public final static String APPLY_STATUS_PRETRIAL_PASS = "3";
		/**
		 * 申请信息状态-审核结果通知（预审）:审核不通过
		 */
		public final static String APPLY_STATUS_PRETRIAL_FAIL = "4";
		/**
		 * 申请信息状态-审核结果通知（预审）:待查
		 */
		public final static String APPLY_STATUS_PRETRIAL_UNDETERMINED = "5";

		/**
		 * 申请信息状态-审核结果通知（初审）:审核通过
		 */
		public final static String APPLY_STATUS_FIRST_TRIAL_PASS = "6";
		/**
		 * 申请信息状态-审核结果通知（初审）:审核不通过
		 */
		public final static String APPLY_STATUS_FIRST_TRIAL_FAIL = "7";
		/**
		 * 申请信息状态-审核结果通知（初审）:待查
		 */
		public final static String APPLY_STATUS_FIRST_TRIAL_UNDETERMINED = "8";

		/**
		 * 申请信息状态-审核结果通知（终审）:审核通过
		 */
		public final static String APPLY_STATUS_FINAL_JUDGMENT_PASS = "9";
		/**
		 * 申请信息状态-审核结果通知（终审）:审核不通过
		 */
		public final static String APPLY_STATUS_FINAL_JUDGMENT_FAIL = "10";
		/**
		 * 申请信息状态-审核结果通知（终审）:待查
		 */
		public final static String APPLY_STATUS_FINAL_JUDGMENT_UNDETERMINED = "11";

		/**
		 * 申请信息状态-签约状态查询（可放款状态）
		 */
		public final static String APPLY_STATUS_SIGNINFO_SUCCEED = "12";

		/**
		 * 申请信息状态-放款通知（放款成功）
		 */
		public final static String APPLY_STATUS_LOAN_SUCCEED = "13";

		/**
		 * 车辆抵押登记
		 */
		public final static String APPLY_STATUS_MORTGAGE_SUCCEED = "14";
		
		/**
		 * 补充放款影像（提交）
		 */
		public final static String APPLY_STATUS_LOAN_UPLOAD = "15";
		
		/**
		 * 放款影像审核（同意）
		 */
		public final static String APPLY_STATUS_LOAN_PERMIT = "16";
		
		/**
		 * 补充抵押影像（提交）
		 */
		public final static String APPLY_STATUS_MORTGAGE_UPLOAD = "17";
		
		/**
		 * 抵押影像审核（同意）
		 */
		public final static String APPLY_STATUS_MORTGAGE_PERMIT = "18";

		/**
		 * 拖车信息状态 ：申请中
		 */
		public final static String TRAILER_APPLY = "1";
		/**
		 * 拖车信息状态 ：已审核通过
		 */
		public final static String TRAILER_APPOVE = "2";
		/**
		 * 拖车信息状态 ：已入库
		 */
		public final static String TRAILER_IN_STORAGE = "3";
		/**
		 * 拖车信息状态 ：已完成
		 */
		public final static String TRAILER_ACCOMPLISH = "4";

	}

	/**
	 * 微信常量信息
	 * 
	 * @author Administrator
	 *
	 */
	public interface WeChatConstant {

		/**
		 * 微信属性配置文件
		 */
		public final static String PROPERTIES_FILE_NAME = "wxConfig.properties";

		/**
		 * wxAppId
		 */
		public final static String PROPERTIES_APPID = "wxAppId";

		/**
		 * wxSecret
		 */
		public final static String PROPERTIES_SECRET = "wxSecret";

		/**
		 * wxMessageID
		 */
		public final static String PROPERTIES_MESSAGEID = "wxMessageID";

		/**
		 * wxTokenURI
		 */
		public final static String PROPERTIES_TOKEN_URI = "wxTokenURI";

		/**
		 * wxAccessTokenURI
		 */
		public final static String PROPERTIES_ACCESS_TOKEN_URI = "wxAccessTokenURI";

		/**
		 * wxUserInfoURI
		 */
		public final static String PROPERTIES_USER_URI = "wxUserInfoURI";

		/**
		 * wxTicketURI
		 */
		public final static String PROPERTIES_TICKET_URI = "wxTicketURI";

		/**
		 * wxAuthorizeURI
		 */
		public final static String PROPERTIES_AUTHORIZE_URI = "wxAuthorizeURI";

		/**
		 * wxTemplateURI
		 */
		public final static String PROPERTIES_TEMPLATE_URI = "wxTemplateURI";

	}

	/**
	 * 接口常量
	 * 
	 * @author Administrator
	 *
	 */
	public interface MSConstant {
		/**
		 * 银行预审
		 */
		public final static String PREJUDIC_INFO = "PrejudicInfo";
		/**
		 * 默认创建人
		 */
		public final static String CREATEBY = "0";
		/**
		 * 默认修改人
		 */
		public final static String LASTUPDATEBY = "0";
		/**
		 * 白名单附件
		 */
		public final static String APPLYINFO = "APPLY";
		/**
		 * 放款附件
		 */
		public final static String APPLYINFO_LOAN = "LOAN";
		/**
		 * 贷后附件
		 */
		public final static String APPLYINFO_VEHICLEMORTGAGEINFO = "LOANAFTER";
	}

}
