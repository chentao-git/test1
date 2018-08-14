package com.xxm.it.activiti_piic.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.handler.LeaveWorkflowService;
import com.xxm.it.activiti.vo.Variable;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.vo.FlowLogVO;

/**
 * PIIC流程处理类
 * 
 * @author Administrator
 *
 */
@Component
@Transactional
public class PIICFlowHandler extends FlowHandler {

	private static Logger logger = LoggerFactory.getLogger(LeaveWorkflowService.class);

	/**
	 * 启动流程
	 *
	 * @param applyVO
	 * @throws Exception
	 */
	public ProcessInstance startWorkflow(ApplyVO applyVO, Map<String, Object> variables) throws Exception {
		String businessKey = applyVO.getApplyInfoId().toString();
		ProcessInstance processInstance = null;
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(applyVO.getCreateBy());
			// 部署流程文件
			repositoryService.createDeployment().name("PIICFlow").addClasspathResource("diagrams/piic/PIICFlow.bpmn")
					.deploy();
			// 启动流程
			processInstance = runtimeService.startProcessInstanceByKey("PIICFlow", businessKey, variables);
			String processInstanceId = processInstance.getId();
			applyVO.setProcessInstanceId(processInstanceId);
			logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}",
					new Object[] { "PIICFlow", businessKey, processInstanceId, variables });
			// leaveManager.updateLeave(applyVO);
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

	/**
	 * 查询当前用户待办任务
	 *
	 * @param userId
	 *            用户ID
	 * @param applyVO
	 *            申请信息
	 * @return map 返回数据结果
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findTodoTasks(String userId, ApplyVO applyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplyVO> results = new ArrayList<ApplyVO>();
		// 根据当前人的ID查询
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(applyVO.getOffset(), applyVO.getLimit());
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			if (businessKey == null) {
				continue;
			}
			// 设置申请Id
			applyVO.setApplyInfoId(businessKey);
			ApplyVO apply = applyManager.getPIICApplyInfo(applyVO);
			if (null != apply) {
				// 设置task
				apply.setTaskId(task.getId());
				apply.setTaskName(task.getName());
				apply.setTaskCreateTime(new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2)
						.format(task.getCreateTime()));
				apply.setTaskAssignee(task.getAssignee());
				apply.setTaskAssigneeName(applyManager.getUserName(task.getAssignee()));
				apply.setTaskDefinitionKey(task.getTaskDefinitionKey());
				// 设置processInstance
				apply.setProcessInstanceId(processInstance.getId());
				apply.setProcessDefinitionId(processInstance.getProcessDefinitionId());
				apply.setSuspended(processInstance.isSuspended());
				// 流程定义
				ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
				apply.setVersion(processDefinition.getVersion());
				results.add(apply);
			}
		}
		map.put("rows", results);
		map.put("total", taskQuery.count());
		return map;
	}

	/**
	 * 查询某个人物节点下，当前用户待办任务
	 *
	 * @param userId
	 *            用户ID
	 * @param taskDefinitionKey
	 *            任务节点名称
	 * @param applyVO
	 *            申请信息
	 * @return map 返回数据结果
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findTodoTasks(String userId, String taskDefinitionKey, ApplyVO applyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplyVO> results = new ArrayList<ApplyVO>();
		// 根据当前节点、用户人的ID查询
		TaskQuery taskQuery = taskService.createTaskQuery().taskDefinitionKeyLike(taskDefinitionKey)
				.taskAssignee(userId);
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(applyVO.getOffset(), applyVO.getLimit());
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			if (businessKey == null) {
				continue;
			}
			// 设置申请Id
			applyVO.setApplyInfoId(businessKey);
			ApplyVO apply = applyManager.getPIICApplyInfo(applyVO);
			if (null != apply) {
				// 设置task
				apply.setTaskId(task.getId());
				apply.setTaskName(task.getName());
				apply.setTaskCreateTime(new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2)
						.format(task.getCreateTime()));
				apply.setTaskAssignee(task.getAssignee());
				apply.setTaskAssigneeName(applyManager.getUserName(task.getAssignee()));
				apply.setTaskDefinitionKey(task.getTaskDefinitionKey());
				// 设置processInstance
				apply.setProcessInstanceId(processInstance.getId());
				apply.setProcessDefinitionId(processInstance.getProcessDefinitionId());
				apply.setSuspended(processInstance.isSuspended());
				// 流程定义
				ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
				apply.setVersion(processDefinition.getVersion());
				results.add(apply);
			}
		}
		map.put("rows", results);
		map.put("total", taskQuery.count());
		return map;
	}

	/**
	 * 查询所有运行中的流程
	 * 
	 * @param applyVO
	 *            申请信息
	 * @return map 返回数据结果
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findRunningProcessInstaces(ApplyVO applyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplyVO> results = new ArrayList<ApplyVO>();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey("PIICFlow").active()
				.orderByTaskCreateTime().desc();
		List<Task> tasks = taskQuery.listPage(applyVO.getOffset(), applyVO.getLimit());
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			if (businessKey == null) {
				continue;
			}
			// 设置申请Id
			applyVO.setApplyInfoId(businessKey);
			ApplyVO apply = applyManager.getPIICApplyInfo(applyVO);
			if (null != apply) {
				// 设置task
				apply.setTaskId(task.getId());
				apply.setTaskName(task.getName());
				apply.setTaskCreateTime(new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2)
						.format(task.getCreateTime()));
				apply.setTaskAssignee(task.getAssignee());
				apply.setTaskAssigneeName(applyManager.getUserName(task.getAssignee()));
				apply.setTaskDefinitionKey(task.getTaskDefinitionKey());
				// 设置processInstance
				apply.setProcessInstanceId(processInstance.getId());
				apply.setProcessDefinitionId(processInstance.getProcessDefinitionId());
				apply.setSuspended(processInstance.isSuspended());
				// 流程定义
				ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
				apply.setVersion(processDefinition.getVersion());
				results.add(apply);
			}
		}
		map.put("rows", results);
		map.put("total", taskQuery.count());
		return map;
	}

	/**
	 * 查询所有已结束中的流程
	 *
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findFinishedProcessInstaces(ApplyVO applyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplyVO> results = new ArrayList<ApplyVO>();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey("PIICFlow").finished().orderByProcessInstanceEndTime().desc();
		List<HistoricProcessInstance> list = query.listPage(applyVO.getOffset(), applyVO.getLimit());
		// 关联业务实体
		for (HistoricProcessInstance historicProcessInstance : list) {
			String businessKey = historicProcessInstance.getBusinessKey();
			// 查询申请单信息
			ApplyVO apply = applyManager.getPIICApplyInfo(businessKey);
			// 流程定义
			ProcessDefinition processDefinition = getProcessDefinition(
					historicProcessInstance.getProcessDefinitionId());
			apply.setVersion(processDefinition.getVersion());
			// apply.setHistoricProcessInstance(historicProcessInstance);
			SimpleDateFormat sdf = new SimpleDateFormat(XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2);
			apply.setHistoricProcessStartTime(sdf.format(historicProcessInstance.getStartTime()));
			apply.setHistoricProcessEndTime(sdf.format(historicProcessInstance.getEndTime()));
			apply.setHistoricProcessDeleteReason(historicProcessInstance.getDeleteReason());
			results.add(apply);
		}
		map.put("rows", results);
		map.put("total", query.count());
		return map;
	}

	/**
	 * 查询任务信息
	 * 
	 * @param businessKey
	 * @return Task
	 */
	public Task getTask(String businessKey) {
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).active().singleResult();
		if (null != task) {
			return task;
		}
		return null;
	}

	/**
	 * 获取业务Id
	 * 
	 * @param userId
	 *            用户
	 * @param taskId
	 *            任务id
	 */
	public String getBusinessKey(String taskId) {
		String businessKey = "";
		// 根据当前节点、用户人的ID查询
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (null != task) {
			// 根据流程的业务ID查询实体并关联
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			businessKey = processInstance.getBusinessKey();
		}
		return businessKey;
	}

	/**
	 * 查询流程定义对象
	 *
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	/**
	 * 读取流程详细信息数据
	 *
	 * @param id
	 * @return
	 */
	public Map<String, Object> getApplyWithVars(ApplyVO applyVO) {
		Map<String, Object> variables = null;
		try {
			variables = taskService.getVariables(applyVO.getTaskId());
		} catch (Exception e) {
			logger.error("getApplyWithVars error." + e.getMessage());
		}
		return variables;
	}

	/**
	 * 流程签收任务
	 * 
	 * @param userId
	 *            处理用户
	 * @param applyVO
	 *            申请信息
	 * @throws Exception
	 */
	public void claim(String userId, String taskId) throws Exception {
		try {
			if (null != taskId && !"".equals(taskId)) {
				// 任务签收
				taskService.claim(taskId, userId);
				logger.info("claim task success.");
			} else {
				throw new Exception("taskId is null.");
			}
		} catch (Exception e) {
			throw new Exception("error on claim task." + e);
		}
	}

	/**
	 * 流程批量签收任务
	 * 
	 * @param userId
	 *            处理用户
	 * @param applyVO
	 *            申请信息
	 * @throws Exception
	 */
	public void claimBatch(String userId, String[] taskId) throws Exception {
		try {
			if (null != taskId && taskId.length > 0) {
				for (int i = 0; i < taskId.length; i++) {
					// 任务签收
					taskService.claim(taskId[i], userId);
				}
				logger.info("claim task success.");
			} else {
				throw new Exception("taskId is null.");
			}
		} catch (Exception e) {
			throw new Exception("error on claim task." + e);
		}
	}

	/**
	 * 流程完成任务
	 *
	 * @param paramMap
	 *            参数
	 * @return result 结果
	 * @throws Exception
	 */
	public void complete(Map<String, Object> paramMap) throws Exception {
		try {
			String taskId = String.valueOf(paramMap.get("taskId"));
			String keys = String.valueOf(paramMap.get("keys"));
			String values = String.valueOf(paramMap.get("values"));
			String types = String.valueOf(paramMap.get("types"));
			// 设置参数
			Variable var = new Variable();
			var.setKeys(keys);
			var.setValues(values);
			var.setTypes(types);
			// 获取参数的map集合
			Map<String, Object> variables = var.getVariableMap();
			taskService.complete(taskId, variables);
		} catch (Exception e) {
			throw new Exception("error on complete task." + e);
		}
	}

	/**
	 * 流程完成任务
	 *
	 * @param paramMap
	 *            参数
	 * @return result 结果
	 * @throws Exception
	 */
	public void complete(String taskId, Map<String, Object> variables) throws Exception {
		try {
			taskService.complete(taskId, variables);
		} catch (Exception e) {
			throw new Exception("error on complete task." + e);
		}
	}

	/**
	 * 查询流程日志
	 * 
	 * @return List<FlowLogVO>
	 */
	public List<FlowLogVO> findFlowLogList(FlowLogVO flowLogVO) {
		return applyManager.findFlowLogList(flowLogVO);
	}

	/**
	 * 创建日志
	 * 
	 * @param userId
	 *            处理人
	 * @param businessKey
	 *            业务id
	 * @param taskDefinitionKey
	 *            当前步骤
	 * @param result
	 *            处理结果
	 * @param reason
	 *            原因
	 * @param remark
	 *            备注
	 * @throws Exception
	 */
	public void createFlowLog(String userId, String businessKey, String taskDefinitionKey, String result, String reason,
			String remark) throws Exception {
		try {
			FlowLogVO flowLogVO = new FlowLogVO();
			if (null != userId && !"".equals(userId)) {
				flowLogVO.setUserId(userId);
			}
			flowLogVO.setBusinessKey(businessKey);
			flowLogVO.setTaskDefinitionKey(taskDefinitionKey);
			flowLogVO.setResult(result);
			flowLogVO.setReason(reason);
			flowLogVO.setRemark(remark);
			applyManager.createFlowLog(flowLogVO);
		} catch (Exception e) {
			throw new Exception("error on complete task." + e);
		}
	}

}
