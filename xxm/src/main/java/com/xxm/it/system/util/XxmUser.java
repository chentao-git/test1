package com.xxm.it.system.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xxm.it.system.vo.UserVO;

/**
 * 用户 公共方法
 * 
 * @author Administrator
 *
 */
public class XxmUser {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(XxmUtils.class);

	/**
	 * 获取当前用户Id
	 * 
	 * @return userId 当前登录用户Id
	 */
	public static String getCurrentUserId() {
		UserVO userVO = getCurrentUserInfo();
		if (null != userVO) {
			return userVO.getUserId();
		}
		return null;
	}

	/**
	 * 获取当前用户名称
	 * 
	 * @return userId 当前登录用户名称
	 */
	public static String getCurrentUserName() {
		UserVO userVO = getCurrentUserInfo();
		if (null != userVO) {
			return userVO.getName();
		}
		return null;
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @return UserVO 当前登录用户信息
	 */
	public static UserVO getCurrentUserInfo() {
		// 声明用户对象
		UserVO userVO = null;
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			Object obj = request.getSession().getAttribute("user");
			if (null != obj) {
				userVO = (UserVO) obj;
			}
		} catch (Exception e) {
			logger.error("getCurrentUserId error." + e.getMessage());
		}
		return userVO;
	}

}
