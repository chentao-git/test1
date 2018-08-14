package com.xxm.it.business.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * 签约信息查询定时调度 <br>
 * Spring动态周期定时任务<br>
 * 在不停应用的情况下更改任务执行周期
 * 
 */
@Component
@EnableScheduling
public class SignInfoQueryCronConfig implements SchedulingConfigurer {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(SignInfoQueryCronConfig.class);

	/**
	 * 当前执行频率
	 */
	private static String cron = "* 0/10 * * * ?";

	/**
	 * 设置cron参数
	 * 
	 * @param _cron
	 *            表达式
	 */
	public static void setCron(String cron_) {
		cron = cron_;
	}

	/**
	 * 监听并重新触发调度
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		taskRegistrar.addTriggerTask(new SignInfoQueryTask(), new Trigger() {
//			@Override
//			public Date nextExecutionTime(TriggerContext triggerContext) {
//				logger.info("触发 SignInfoQueryTask 任务,定时频率为【" + cron + "】");
				// 任务触发，可修改任务的执行周期
//				CronTrigger trigger = new CronTrigger(cron);
//				Date nextExec = trigger.nextExecutionTime(triggerContext);
//				return nextExec;
//			}
//		});
	}
}