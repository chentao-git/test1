package com.xxm.it.system.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmUtils;

import net.sf.json.JSONObject;

public class WeChatTemplateHandle {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(WeChatTemplateHandle.class);

	/**
	 * 
	 * 发送模板消息
	 * 
	 * @param accessToken
	 * 
	 * @param jsonData
	 * @throws IOException
	 */

	@SuppressWarnings("rawtypes")
	public static void sendTemplateMsg(String openId, String remark, String result, Map<String, String> map)
			throws IOException {
		String token = WeChatServiceHandle.getWxToken();
		if (null == token) {
			logger.error("accessToken is null.sendTemplateMsg error.");
			throw new IOException("accessToken is null.sendTemplateMsg error.");
		}
		String templateId = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_MESSAGEID);
		logger.info("accessToken:" + token);
		logger.info("openId:" + openId);
		logger.info("templateId:" + templateId);
		String firstData = "您好，欢迎使用投保系统！";

		// 手机品牌、型号
		String phone = map.get("brands") + map.get("model");
		// 手机IMEI
		String imeiStr = map.get("phoneImei");

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", openId);
		jsonObj.put("template_id", templateId);
		jsonObj.put("url", "www.baidu.com");
		jsonObj.put("topcolor", "#FF0000");

		Map<String, String> first = new HashMap<String, String>();
		first.put("value", firstData);
		first.put("color", "#173177");

		Map<String, String> projectName = new HashMap<String, String>();
		projectName.put("value", phone);
		projectName.put("color", "#173177");

		Map<String, String> imei = new HashMap<String, String>();
		imei.put("value", imeiStr);
		imei.put("color", "#173177");

		Map<String, String> time = new HashMap<String, String>();
		time.put("value", XxmUtils.getCurrentDate(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME3));
		time.put("color", "#173177");

		Map<String, String> results = new HashMap<String, String>();
		results.put("value", result);
		results.put("color", "#173177");

		Map<String, String> remarks = new HashMap<String, String>();
		remarks.put("value", remark);
		remarks.put("color", "#173177");

		Map<String, Map> data = new HashMap<String, Map>();
		data.put("first", first);
		data.put("keyword1", projectName);
		data.put("keyword2", imei);
		data.put("keyword3", time);
		data.put("keyword4", results);
		data.put("remark", remarks);

		jsonObj.put("data", data);
		logger.info("send message jsonObj:" + jsonObj);
		// 获取微信模板地址
		String send_templatemsg_url = WeChatConfig.getInstance()
				.getParameter(XxmConstant.WeChatConstant.PROPERTIES_TEMPLATE_URI);
		String requestUrl = MessageFormat.format(send_templatemsg_url, token);
		// 请求
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonObj.toString());
		if (jsonObject != null && "0".equals(jsonObject.getString("errcode"))) {
			logger.info("发送模板消息成功！");
		} else if (jsonObject != null && !"0".equals(jsonObject.getString("errcode"))) {
			logger.info(jsonObject.getString("errcode") + ":" + jsonObject.getString("errmsg"));
		} else {
			logger.info("发送模板消息失败！");
		}
	}

	/**
	 * 
	 * 发起https请求并获取结果
	 * 
	 * 
	 * 
	 * @param requestUrl
	 *            请求地址
	 * 
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * 
	 * @param outputStr
	 *            提交的数据
	 * 
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		HttpsURLConnection httpUrlConn = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WeChatX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setConnectTimeout(30 * 1000);
			httpUrlConn.setReadTimeout(30 * 1000);
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes(XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING));
				outputStream.flush();
			}

			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			jsonObject = null;
			logger.error("httpRequest() error:" + ce.getMessage());
		} catch (Exception e) {
			jsonObject = null;
			logger.error("httpRequest() error:" + e.getMessage());
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("httpRequest() outputStream close error:" + e.getMessage());
				}
			}
			// 释放资源
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				logger.error("httpRequest() bufferedReader close error:" + e.getMessage());
			}
			try {
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
			} catch (IOException e) {
				logger.error("httpRequest() inputStreamReader close error:" + e.getMessage());
			}
			try {
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("httpRequest() inputStream close error:" + e.getMessage());
			}
			if (null != httpUrlConn) {
				httpUrlConn.disconnect();
			}
		}
		return jsonObject;
	}

	public static void main(String[] args) throws IOException {
		String openId = "oC7r7wD2C1u_0VpUj86N9OfWMf-c";
		String remark = "非常谢谢！";
		String result = "投保成功！感谢您的使用！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("brands", "iphone6");
		map.put("model", " plus");
		map.put("phoneImei", "412343512354");
		WeChatTemplateHandle.sendTemplateMsg(openId, remark, result, map);
	}

}
