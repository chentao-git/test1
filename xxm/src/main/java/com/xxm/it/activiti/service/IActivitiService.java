package com.xxm.it.activiti.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 流程管理控制器
 *
 * 
 */
@Path(value = "/activitiService")
public interface IActivitiService {

	/**
	 * 输出跟踪流程信息
	 *
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/trace")
	public List<Map<String, Object>> traceProcess(String processInstanceId) throws Exception;

	/**
	 * 读取资源，通过流程ID
	 *
	 * @param jsonStr
	 *            resourceType 资源类型(xml|image) processInstanceId 流程实例ID
	 * @return msgMap 返回结果
	 * @throws Exception
	 */
	@POST
	@Path("/processInstance")
	public Map<String, Object> loadByProcessInstance(String jsonStr);

}
