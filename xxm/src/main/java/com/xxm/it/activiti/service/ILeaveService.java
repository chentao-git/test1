package com.xxm.it.activiti.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.activiti.vo.Leave;

import net.sf.json.JSONObject;

/**
 * 请假服务类接口类
 * 
 * @author Administrator
 *
 */
@Path(value = "/leaveService")
public interface ILeaveService {

	/**
	 * 启动请假流程
	 *
	 * @param leave
	 */
	@POST
	@Path("/start")
	public Map<String, Object> startWorkflow(Leave leave);

	/**
	 * 任务列表
	 *
	 * @param leave
	 */
	@POST
	@Path("/taskList")
	public JSONObject taskList(Leave paramLeave);

	/**
	 * 读取运行中的流程实例
	 *
	 * @return
	 */
	@POST
	@Path("/runningList")
	public JSONObject runningList(Leave paramLeave);

	/**
	 * 读取运行中的流程实例
	 *
	 * @return
	 */
	@POST
	@Path("/finishedList")
	public JSONObject finishedList(Leave paramLeave);

	/**
	 * 签收任务
	 */
	@POST
	@Path("/claim")
	public Map<String, Object> claim(Leave paramLeave);

	/**
	 * 读取详细数据
	 *
	 * @param id
	 * @return
	 */
	@POST
	@Path("/getLeave")
	public Leave getLeave(Leave paramLeave);

	/**
	 * 读取详细数据
	 *
	 * @param id
	 * @return
	 */
	@POST
	@Path("/getLeaveWithVars")
	public Map<String, Object> getLeaveWithVars(Leave paramLeave);

	/**
	 * 完成任务
	 *
	 * @param id
	 * @return
	 */
	@POST
	@Path("/complete")
	public Map<String, Object> complete(String jsonStr);
}
