package com.xxm.it.system.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.xxm.it.system.service.IBaseDataService;

/**
 * 初始化类 用于加载共享数据
 * 
 * @author Administrator
 *
 */
public class XxmInit implements ServletContextListener {

	private static Logger logger = Logger.getLogger(XxmInit.class);

	/**
	 * 初始化信息
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("....start....init base data......");
		initBaseData(event);
		logger.info("....end....init base data......");
	}

	/**
	 * 销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 初始化基础数据
	 * 
	 * @param event
	 */
	public void initBaseData(ServletContextEvent event) {
		// ApplicationContext context = (ApplicationContext)
		// event.getServletContext()
		// .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		// IBaseDataService baseDataService =
		// context.getBean("baseDataServiceImpl", BaseDataServiceImpl.class);

		// 注入流程服务
		IBaseDataService baseDataService = (IBaseDataService) new XxmAppContext().getBean("baseDataServiceImpl");
		// 初始化基础数据
		baseDataService.initBaseData();
	}

}
