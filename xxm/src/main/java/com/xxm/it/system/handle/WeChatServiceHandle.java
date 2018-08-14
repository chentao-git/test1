package com.xxm.it.system.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmUtils;
import com.sun.net.ssl.internal.www.protocol.https.Handler;

import net.sf.json.JSONObject;

/**
 * 微信服务处理
 * 
 * @author Administrator
 *
 */
public class WeChatServiceHandle {

	private static Logger logger = Logger.getLogger(WeChatServiceHandle.class);

	/**
	 * 获取普通token
	 * 
	 * @return String access_token 授权token
	 * @throws IOException
	 */
	public static String getWxToken() {
		String result = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		HttpURLConnection connection = null;
		try {
			String appId = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_APPID);
			String secret = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_SECRET);
			String tokenURI = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_TOKEN_URI);
			logger.info("******* wxAppId = " + appId);
			logger.info("******* wxSecret = " + secret);
			URL url = new URL(null, MessageFormat.format(tokenURI, appId, secret), new Handler());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(30 * 1000);
			connection.connect();// 打开连接
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			in = new BufferedReader(inputStreamReader);
			String line;
			while ((line = in.readLine()) != null) {
				result = line;
			}
			// logger.info(result);
		} catch (MalformedURLException e) {
			result = null;
			logger.error("getToken error." + e.getMessage());
		} catch (IOException e) {
			result = null;
			logger.error("getToken error." + e.getMessage());
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("getToken inputStream colse error." + e.getMessage());
				}
			}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("getToken inputStreamReader colse error." + e.getMessage());
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("getToken in colse error." + e.getMessage());
				}
			}
			if (null != connection) {
				connection.disconnect();// 关闭连接
			}
		}
		if (null != result && !"".equals(result)) {
			JSONObject jsStr = JSONObject.fromObject(result);
			result = (String) jsStr.get("access_token");
		}
		return result;
	}

	/**
	 * 获取网页授权access_token
	 * 
	 * @param code
	 * @return result
	 * @throws IOException
	 */
	public static String getWxAccessToken(String code) {
		String result = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			// 获取参数
			String appId = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_APPID);
			String secret = WeChatConfig.getInstance().getParameter(XxmConstant.WeChatConstant.PROPERTIES_SECRET);
			String accessTokenURI = WeChatConfig.getInstance()
					.getParameter(XxmConstant.WeChatConstant.PROPERTIES_ACCESS_TOKEN_URI);
			// 调用服务
			URL url = new URL(null, MessageFormat.format(accessTokenURI, appId, secret, code), new Handler());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(30 * 1000);
			connection.connect();
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			in = new BufferedReader(inputStreamReader);
			String line;
			while ((line = in.readLine()) != null) {
				result = line;
			}
		} catch (MalformedURLException e) {
			result = null;
			logger.error("getToken error." + e.getMessage());
		} catch (IOException e) {
			result = null;
			logger.error("getToken error." + e.getMessage());
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("getToken inputStream colse error." + e.getMessage());
				}
			}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("getToken inputStreamReader colse error." + e.getMessage());
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("getToken in colse error." + e.getMessage());
				}
			}
			if (null != connection) {
				connection.disconnect();// 关闭连接
			}
		}
		return result;
	}

	/**
	 * 得到用户详情
	 * 
	 * @param token
	 *            授权token
	 * @param openid
	 *            用户唯一标识
	 * @return map 返回用户信息
	 * @throws IOException
	 */
	public static Map<String, String> getWxUseDetails(String token, String openid) {
		Map<String, String> map = new HashMap<String, String>();
		String result = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			// 获取微信用户信息服务地址
			String userInfoURI = WeChatConfig.getInstance()
					.getParameter(XxmConstant.WeChatConstant.PROPERTIES_USER_URI);
			// 调用服务
			URL url = new URL(null, MessageFormat.format(userInfoURI, token, openid), new Handler());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(30 * 1000);
			connection.connect();
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			in = new BufferedReader(inputStreamReader);
			String line;
			while ((line = in.readLine()) != null) {
				result = line;
			}
			JSONObject jsStr = new JSONObject();
			jsStr = JSONObject.fromObject(result);
			logger.info("转换前微信信息:" + jsStr);
			String open = (String) jsStr.get("openid");
			String nickname = XxmUtils.filterEmoji(String.valueOf(jsStr.get("nickname")));// 转换微信特殊字符
			String headimgurl = (String) jsStr.get("headimgurl");
			map.put("openId", open);// 微信OPENID 对应插入到微信号字段
			map.put("weName", nickname);// 微信昵称
			map.put("headImageUrl", headimgurl);// 微信头像地址
			logger.info("用户微信信息:" + map.toString());
		} catch (MalformedURLException e) {
			logger.error("getUseDetails error." + e.getMessage());
		} catch (IOException e) {
			logger.error("getUseDetails error." + e.getMessage());
		} catch (Exception e) {
			logger.error("getUseDetails error." + e.getMessage());
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("getUseDetails inputStream colse error." + e.getMessage());
				}
			}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("getUseDetails inputStreamReader colse error." + e.getMessage());
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("getUseDetails in colse error." + e.getMessage());
				}
			}
			if (null != connection) {
				connection.disconnect();// 关闭连接
			}
		}
		return map;
	}

	/**
	 * 接口签名
	 * 
	 * @return ticket
	 * @throws IOException
	 */
	public static String getWxTicket(String token) throws IOException {
		String Ticket = null;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			// 获取微信用户信息服务地址
			String ticketURI = WeChatConfig.getInstance()
					.getParameter(XxmConstant.WeChatConstant.PROPERTIES_TICKET_URI);
			// 调用服务
			URL url = new URL(null, MessageFormat.format(ticketURI, token), new Handler());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(30 * 1000);
			connection.connect();
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			in = new BufferedReader(inputStreamReader);
			String line;
			while ((line = in.readLine()) != null) {
				Ticket = line;
			}
			logger.info("result:" + Ticket);
			JSONObject json = JSONObject.fromObject(Ticket);
			Ticket = (String) json.get("ticket");
			logger.info("ticket:" + Ticket);
		} catch (MalformedURLException e) {
			logger.error("getUseDetails error." + e.getMessage());
		} catch (IOException e) {
			logger.error("getUseDetails error." + e.getMessage());
		} catch (Exception e) {
			logger.error("getUseDetails error." + e.getMessage());
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("getUseDetails inputStream colse error." + e.getMessage());
				}
			}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("getUseDetails inputStreamReader colse error." + e.getMessage());
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("getUseDetails in colse error." + e.getMessage());
				}
			}
			if (null != connection) {
				connection.disconnect();// 关闭连接
			}
		}
		return Ticket;
	}

	/**
	 * 获取签名
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return ret
	 */
	public static Map<String, String> getWxSign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String signURL;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		signURL = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		logger.info("sign URL" + signURL);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(signURL.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("getWxSign error." + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("getWxSign error." + e.getMessage());
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	public static void main(String[] args) {
		System.out.println(WeChatServiceHandle.getWxToken());

		Map<String, String> wxUseDetails = WeChatServiceHandle.getWxUseDetails(WeChatServiceHandle.getWxToken(),
				"oC7r7wD2C1u_0VpUj86N9OfWMf-c");
		System.out.println(wxUseDetails.toString());
	}

}
