package com.xxm.it.activiti.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.service.IActivitiService;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * 流程管理控制器
 *
 * 
 */
@Component
@Transactional
public class ActivitiServiceImpl implements IActivitiService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	protected RepositoryService repositoryService;

	protected RuntimeService runtimeService;

	protected TaskService taskService;

	protected WorkflowTraceService traceService;

	/**
	 * 输出跟踪流程信息
	 *
	 * @param processInstanceId
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> traceProcess(String processInstanceId) throws Exception {
		List<Map<String, Object>> activityInfos = traceService.traceProcess(processInstanceId);
		return activityInfos;
	}

	/**
	 * 读取资源，通过流程ID
	 *
	 * @param jsonStr
	 *            resourceType 资源类型(xml|image) processInstanceId 流程实例ID
	 * @return msgMap 返回结果
	 * @throws Exception
	 */
	public Map<String, Object> loadByProcessInstance(String jsonStr) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		// 将json字符串转JSON对象
		JSONObject json = JSONObject.fromObject(jsonStr);
		String resourceType = json.getString("type");
		String processInstanceId = json.getString("pid");
		InputStream resourceAsStream = null;
		byte[] bytes;
		try {
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
			String resourceName = "";
			if (resourceType.equals("image")) {
				resourceName = processDefinition.getDiagramResourceName();
			} else if (resourceType.equals("xml")) {
				resourceName = processDefinition.getResourceName();
			}
			resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
			// base64编码对象
			BASE64Encoder base64Encoder = new BASE64Encoder();
			// in.available()返回文件的字节长度
			bytes = new byte[resourceAsStream.available()];
			// 将文件中的内容读入到数组中
			resourceAsStream.read(bytes);
			// 将字节流数组转换为字符串
			String encode = base64Encoder.encode(bytes);
			msgMap.put("imgData", encode);
			msgMap.put("result", Boolean.TRUE);
		} catch (IOException e) {
			msgMap.put("result", Boolean.FALSE);
			msgMap.put("msg", "loadByProcessInstance error." + e.getMessage());
			logger.error("loadByProcessInstance error." + e.getMessage());
			e.printStackTrace();
		} finally {
			if (null != resourceAsStream) {
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					logger.error("loadByProcessInstance close stream error." + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return msgMap;
	}

	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
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
	public void setTraceService(WorkflowTraceService traceService) {
		this.traceService = traceService;
	}
}
