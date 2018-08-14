package com.xxm.it.activiti_piic.handler;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxm.it.activiti_piic.manage.PIICApplyManager;

/**
 * 流程处理类
 * 
 * @author Administrator
 *
 */
public class FlowHandler {

	protected PIICApplyManager applyManager;

	protected RuntimeService runtimeService;

	protected TaskService taskService;

	protected HistoryService historyService;

	protected RepositoryService repositoryService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	public void setPIICApplyManager(PIICApplyManager applyManager) {
		this.applyManager = applyManager;
	}

	@Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

}
