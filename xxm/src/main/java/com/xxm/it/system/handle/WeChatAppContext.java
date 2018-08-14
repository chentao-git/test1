package com.xxm.it.system.handle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 自定义ApplicationContext对象
 * 
 * @author Administrator
 *
 */
public class WeChatAppContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 当继承了ApplicationContextAware类之后，程序在调用 getBean(String)的时候会自动调用该方法
	 */
	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		WeChatAppContext.applicationContext = applicationContext;
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
