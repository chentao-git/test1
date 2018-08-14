package com.xxm.it.activiti.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.handler.LeaveManager;
import com.xxm.it.activiti.handler.LeaveWorkflowService;
import com.xxm.it.activiti.service.ILeaveService;
import com.xxm.it.activiti.vo.Leave;
import com.xxm.it.activiti.vo.Variable;
import com.xxm.it.system.util.XxmUser;

import net.sf.json.JSONObject;

@Component
@Transactional
public class LeaveServiceImpl implements ILeaveService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected LeaveManager leaveManager;

	@Autowired
	protected LeaveWorkflowService workflowService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	/**
	 * 启动请假流程
	 *
	 * @param leave
	 */
	public Map<String, Object> startWorkflow(Leave leave) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			leave.setUserId(XxmUser.getCurrentUserId());
			Map<String, Object> variables = new HashMap<String, Object>();
			ProcessInstance processInstance = workflowService.startWorkflow(leave, variables);
			logger.debug("ActivityId:" + processInstance.getActivityId() + "  Id:" + processInstance.getId());
			msgMap.put("result", Boolean.TRUE);
			msgMap.put("msg", "任务申请成功！");
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				msgMap.put("result", Boolean.FALSE);
				msgMap.put("msg", "没有部署流程!" + e);
				logger.warn("没有部署流程!", e);
			} else {
				msgMap.put("result", Boolean.FALSE);
				msgMap.put("msg", "启动请假流程失败：" + e);
				logger.error("启动请假流程失败：", e);
			}
		} catch (Exception e) {
			msgMap.put("result", Boolean.FALSE);
			msgMap.put("msg", "启动请假流程失败：" + e);
			logger.error("启动请假流程失败：", e);
		}
		return msgMap;
	}

	/**
	 * 任务列表
	 *
	 * @param leave
	 */
	public JSONObject taskList(Leave paramLeave) {
		String userId = XxmUser.getCurrentUserId();
		Map<String, Object> map = workflowService.findTodoTasks(userId, paramLeave);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 读取运行中的流程实例
	 *
	 * @return
	 */
	public JSONObject runningList(Leave paramLeave) {
		Map<String, Object> map = workflowService.findRunningProcessInstaces(paramLeave);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 读取结束中的流程实例
	 *
	 * @return
	 */
	public JSONObject finishedList(Leave paramLeave) {
		Map<String, Object> map = workflowService.findFinishedProcessInstaces(paramLeave);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", map.get("rows"));
		jsonObject.put("total", map.get("total"));
		return jsonObject;
	}

	/**
	 * 签收任务
	 */
	public Map<String, Object> claim(Leave paramLeave) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			String[] taskIds = paramLeave.getTaskIds();
			if (null != taskIds) {
				String userId = XxmUser.getCurrentUserId();
				for (int i = 0; i < taskIds.length; i++) {
					// 任务签收
					taskService.claim(taskIds[i], userId);
				}
			}
			msgMap.put("result", Boolean.TRUE);
			msgMap.put("msg", "任务签收成功！");
			logger.error("任务签收成功！");
		} catch (Exception e) {
			msgMap.put("result", Boolean.FALSE);
			msgMap.put("msg", "任务签收失败：" + e);
			logger.error("任务签收失败：" + e);
		}
		return msgMap;
	}

	/**
	 * 读取详细数据
	 *
	 * @param id
	 * @return
	 */
	public Leave getLeave(Leave paramLeave) {
		Leave leave = leaveManager.getLeave(paramLeave.getId());
		return leave;
	}

	/**
	 * 读取详细数据
	 *
	 * @param id
	 * @return
	 */
	public Map<String, Object> getLeaveWithVars(Leave paramLeave) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Leave leave = leaveManager.getLeave(paramLeave.getId());
		Map<String, Object> variables = taskService.getVariables(paramLeave.getTaskId());
		msgMap.put("leave", leave);
		msgMap.put("variables", variables);
		return msgMap;
	}

	/**
	 * 完成任务
	 *
	 * @param id
	 * @return
	 */
	public Map<String, Object> complete(String jsonStr) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			// 将json字符串转JSON对象
			JSONObject json = JSONObject.fromObject(jsonStr);
			String taskId = json.getString("taskId");
			String keys = json.getString("keys");
			String values = json.getString("values");
			String types = json.getString("types");
			// 设置参数
			Variable var = new Variable();
			var.setKeys(keys);
			var.setValues(values);
			var.setTypes(types);
			// 获取参数的map集合
			Map<String, Object> variables = var.getVariableMap();
			taskService.complete(taskId, variables);
			msgMap.put("result", Boolean.TRUE);
			msgMap.put("msg", "任务执行成功！");
		} catch (Exception e) {
			msgMap.put("result", Boolean.FALSE);
			msgMap.put("msg", "任务执行失败！");
			logger.error("error on complete task");
		}
		return msgMap;
	}

}
