package com.xxm.it.business.task;

import org.apache.log4j.Logger;

import com.xxm.it.business.service.IApplyService;
import com.xxm.it.system.util.XxmAppContext;

/**
 * 签约信息查询定时调度
 * 
 * @author Administrator
 *
 */
public class SignInfoQueryTask implements Runnable {

	private static Logger logger = Logger.getLogger(SignInfoQueryTask.class);

	private IApplyService applyService;

	@Override
	public void run() {
		if (null == applyService) {
			applyService = (IApplyService) new XxmAppContext().getBean("applyServiceImpl");
			logger.info("注入..applyServiceImpl服务...");
		}
		// 签约信息查询
		// applyService.requestSignInfos();
		// logger.info("执行..SignInfoQueryTask...");
	}

}
