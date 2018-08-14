package com.xxm.it.piic.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.xxm.it.system.util.XxmUtils;

public class PICCServiceHandler {

	private static Logger logger = Logger.getLogger(PICCServiceHandler.class);

	/**
	 * 调用字符接口
	 * 
	 * @param url
	 *            路径对象
	 * @param content
	 * @return 结果信息
	 */
	public static String callCharacterInterface(URL url, String content) {
		String result = "";
		// http连接对象
		HttpURLConnection httpURLConnection = null;
		// 输出流对象
		OutputStream outputStream = null;
		try {
			// 建立http连接
			httpURLConnection = (HttpURLConnection) url.openConnection();
			//数据库读取时间配置
			int connectTimeout = Integer.parseInt(XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "ConnectTimeout"));
			// 设置连接主机超时
			httpURLConnection.setConnectTimeout(connectTimeout);
			//数据库读取时间配置
			int readTimeout = Integer.parseInt(XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "ReadTimeout"));
			// 设置从主机读取数据超时
			httpURLConnection.setReadTimeout(readTimeout);
			// 设置允许输出
			httpURLConnection.setDoOutput(true);
			// 设置允许输入
			httpURLConnection.setDoInput(true);
			// 设置不用缓存
			httpURLConnection.setUseCaches(false);
			// 设置传递方式
			httpURLConnection.setRequestMethod(XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "RequestMethod"));
			// 设置维持长连接
			httpURLConnection.setRequestProperty("Connection", XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "Connection"));
			// 设置文件字符集:
			httpURLConnection.setRequestProperty("Charset", XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "Charset"));
			// 转换为字节数组
			byte[] data = content.getBytes();
			// 设置文件长度
			httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
			// 设置文件类型:
			httpURLConnection.setRequestProperty("contentType", XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "contentType"));
			// 开始连接请求
			httpURLConnection.connect();
			outputStream = httpURLConnection.getOutputStream();
			// 写入请求的字符串
			outputStream.write(data);
			// 获得服务器响应的结果和状态码
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				result = analyzeResult(httpURLConnection);
				logger.info("连接成功!");
			} else {
				logger.info("连接失败！错误码：" + responseCode);
			}
			outputStream.flush();
		} catch (Exception e) {
			logger.error("callCharacterInterface method error. " + e.getMessage());
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("callFileStreamInterface outputStream close error. " + e.getMessage());
				}
			}
			if (null != httpURLConnection) {
				// 关闭连接对象
				httpURLConnection.disconnect();
			}
		}
		return result;
	}

	/**
	 * 调用文件流接口
	 * 
	 * @param url
	 *            链接对象
	 * @param content
	 *            文件流
	 * @return 结果消息
	 */
	public static String callFileStreamInterface(URL url, byte[] content) {
		String result = null;
		// 链接对象
		HttpURLConnection httpURLConnection = null;
		// 输出流，向服务器输出数据
		OutputStream outputStream = null;
		try {
			// 建立一个HttpURLConnection
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// 设置传递方式
			httpConnection.setRequestMethod(XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "RequestMethod"));
			httpConnection.setRequestProperty("content-type", "text/xml,charset=utf-8");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setAllowUserInteraction(true);
			httpConnection.connect();

			// 发送数据
			outputStream = httpConnection.getOutputStream();
			outputStream.write(content);
			outputStream.flush();
			outputStream.close();

			int responseCode = httpConnection.getResponseCode();
			logger.debug("---------影像传输状态码--------："+ responseCode);
			// 获得服务器响应的结果和状态码
			if (responseCode == 200) {
				result = analyzeResult(httpConnection);
				logger.debug("------------------影像传输结果------------------："+ result);
				logger.info("连接成功!!");
			} else {
				logger.info("连接失败！错误码：" + responseCode);
			}
			httpConnection.disconnect();
		} catch (IOException e) {
			logger.error("callFileStreamInterface method error. " + e.getMessage());
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("callFileStreamInterface outputStream close error. " + e.getMessage());
				}
			}
			if (null != httpURLConnection) {
				// 关闭连接对象
				httpURLConnection.disconnect();
			}
		}
		return result;
	}

	/**
	 * 分析结果
	 * 
	 * @param httpURLConnection
	 *            链接对象
	 * @return String 返回内容
	 */
	private static String analyzeResult(HttpURLConnection httpURLConnection) {
		String result = "";
		// 字节流去读取数据
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader responseReader = null;
		try {
			inputStream = httpURLConnection.getInputStream();
			// 根据设置的编码方式去字节流中读取数据为字符流（InputStream提供的是字节流的读取，而非文本读取，这是和Reader类的根本区别。用Reader读取出
			// 来的是char数组或者String ，使用InputStream读取出来的是byte数组）
			inputStreamReader = new InputStreamReader(inputStream, "GBK");
			// InputStreamReader是对字节流读取，是字节流通向字符流的桥梁。
			// BufferedReader从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
			responseReader = new BufferedReader(inputStreamReader);
			String readLine;
			StringBuffer responseSb = new StringBuffer();
			while ((readLine = responseReader.readLine()) != null) {
				responseSb.append(readLine).append("\r\n");
			}
			// 转成字符串
			result = new String(responseSb.toString());
		} catch (IOException e) {
			logger.error("analyzeResult method error. " + e.getMessage());
		} finally {
			if (null != responseReader) {
				try {
					responseReader.close();
				} catch (IOException e) {
					logger.error("analyzeResult responseReader close error. " + e.getMessage());
				}
			}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("analyzeResult inputStreamReader close error. " + e.getMessage());
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("analyzeResult inputStream close error. " + e.getMessage());
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
	}

}
