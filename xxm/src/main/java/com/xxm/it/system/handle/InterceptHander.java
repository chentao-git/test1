package com.xxm.it.system.handle;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import com.xxm.it.system.util.XxmUser;
import com.xxm.it.system.vo.UserVO;

/**
 * 拦截处理类
 * 
 * @author Administrator
 *
 */
public class InterceptHander {
	// 日志
	private static Logger logger = Logger.getLogger(InterceptHander.class);

	/**
	 * 事前处理方法
	 * 
	 * @param joinPoint
	 * @throws Exception
	 */
	public void before(JoinPoint joinPoint) throws Exception {
		Signature signature = joinPoint.getSignature();
		// 被调用类class
		String className = signature.getDeclaringTypeName();
		// 被调用类方法
		String methodName = signature.getName();
		logger.info("***************className:" + className);
		logger.info("***************methodName:" + methodName);
		// 过滤某些类的部分方法
		if ("com.xxm.it.system.service.impl.BaseDataServiceImpl".equals(className)) {
			if ("initBaseData".equals(methodName)) {
				return;
			}
		}
		if ("com.xxm.it.system.service.impl.UserServiceImpl".equals(className)) {
			if ("login".equals(methodName)) {
				return;
			}
			if ("quit".equals(methodName)) {
				return;
			}
		}
		// 判断用户是否存在
		UserVO userVO = XxmUser.getCurrentUserInfo();
		if (null == userVO) {
			throw new Exception("The user has not logged in or the system is out of time.");
		}
	}

	/**
	 * 事后处理方法
	 * 
	 * @param joinPoint
	 * @param retValue
	 *            接收方法返回值
	 */
	public void after(JoinPoint joinPoint, Object retValue) {

	}

}
