package com.xxm.it.activiti.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.vo.Leave;
import com.xxm.it.system.util.XxmConstant;

/**
 * 请假流程Service
 *
 * @author HenryYan
 */
@Component
@Transactional
public class LeaveWorkflowService {

	private static Logger logger = LoggerFactory.getLogger(LeaveWorkflowService.class);

	private LeaveManager leaveManager;

	private RuntimeService runtimeService;

	protected TaskService taskService;

	protected HistoryService historyService;

	protected RepositoryService repositoryService;

	@Autowired
	private IdentityService identityService;

	/**
	 * 启动流程
	 *
	 * @param entity
	 */
	public ProcessInstance startWorkflow(Leave entity, Map<String, Object> variables) {
		leaveManager.saveLeave(entity);
		logger.debug("save entity: {}", entity);
		String businessKey = entity.getId().toString();
		ProcessInstance processInstance = null;
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(entity.getUserId());
			// 部署流程文件
			repositoryService.createDeployment().name("leave").addClasspathResource("diagrams/leave/leave.bpmn")
					.deploy();
			// 启动流程
			processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);
			String processInstanceId = processInstance.getId();
			entity.setProcessInstanceId(processInstanceId);
			logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}",
					new Object[] { "leave", businessKey, processInstanceId, variables });
			leaveManager.updateLeave(entity);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

	/**
	 * 查询待办任务
	 *
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findTodoTasks(String userId, Leave entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Leave> results = new ArrayList<Leave>();
		// 根据当前人的ID查询
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
		List<Task> tasks = taskQuery.listPage(entity.getOffset(), entity.getLimit());
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			if (businessKey == null) {
				continue;
			}
			Leave leave = leaveManager.getLeave(new Long(businessKey));
			// 设置task
			leave.setTaskId(task.getId());
			leave.setTaskName(task.getName());
			leave.setTaskCreateTime(
					new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2).format(task.getCreateTime()));
			leave.setTaskAssignee(task.getAssignee());
			leave.setTaskDefinitionKey(task.getTaskDefinitionKey());
			// 设置processInstance
			leave.setProcessInstanceId(processInstance.getId());
			leave.setProcessDefinitionId(processInstance.getProcessDefinitionId());
			leave.setSuspended(processInstance.isSuspended());
			// 流程定义
			ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
			leave.setVersion(processDefinition.getVersion());
			// leave.setTask(task);
			// leave.setProcessInstance(processInstance);
			// leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
			results.add(leave);
		}
		map.put("rows", results);
		map.put("total", taskQuery.count());
		return map;
	}

	/**
	 * 读取运行中的流程
	 *
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findRunningProcessInstaces(Leave entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Leave> results = new ArrayList<Leave>();
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave").active()
				.orderByProcessInstanceId().desc();
		List<ProcessInstance> list = query.listPage(entity.getOffset(), entity.getLimit());
		// 关联业务实体
		for (ProcessInstance processInstance : list) {
			String businessKey = processInstance.getBusinessKey();
			if (businessKey == null) {
				continue;
			}
			Leave leave = leaveManager.getLeave(new Long(businessKey));
			leave.setProcessInstanceId(processInstance.getId());
			leave.setProcessDefinitionId(processInstance.getProcessDefinitionId());
			leave.setSuspended(processInstance.isSuspended());
			// 流程定义
			ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
			leave.setVersion(processDefinition.getVersion());
			// 设置当前任务信息
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active()
					.orderByTaskCreateTime().desc().listPage(0, 1);
			leave.setTaskId(tasks.get(0).getId());
			leave.setTaskName(tasks.get(0).getName());
			leave.setTaskCreateTime(new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2)
					.format(tasks.get(0).getCreateTime()));
			leave.setTaskAssignee(tasks.get(0).getAssignee());
			results.add(leave);
		}
		map.put("rows", results);
		map.put("total", query.count());
		return map;
	}

	/**
	 * 读取已结束中的流程
	 *
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findFinishedProcessInstaces(Leave entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Leave> results = new ArrayList<Leave>();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey("leave").finished().orderByProcessInstanceEndTime().desc();
		List<HistoricProcessInstance> list = query.listPage(entity.getOffset(), entity.getLimit());
		// 关联业务实体
		for (HistoricProcessInstance historicProcessInstance : list) {
			String businessKey = historicProcessInstance.getBusinessKey();
			Leave leave = leaveManager.getLeave(new Long(businessKey));
			// leave.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
			// 流程定义
			ProcessDefinition processDefinition = getProcessDefinition(
					historicProcessInstance.getProcessDefinitionId());
			leave.setVersion(processDefinition.getVersion());
			// leave.setHistoricProcessInstance(historicProcessInstance);
			SimpleDateFormat sdf = new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2);
			leave.setHistoricProcessStartTime(sdf.format(historicProcessInstance.getStartTime()));
			leave.setHistoricProcessEndTime(sdf.format(historicProcessInstance.getEndTime()));
			leave.setHistoricProcessDeleteReason(historicProcessInstance.getDeleteReason());
			results.add(leave);
		}
		map.put("rows", results);
		map.put("total", query.count());
		return map;
	}

	/**
	 * 查询流程定义对象
	 *
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	@Autowired
	public void setLeaveManager(LeaveManager leaveManager) {
		this.leaveManager = leaveManager;
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
