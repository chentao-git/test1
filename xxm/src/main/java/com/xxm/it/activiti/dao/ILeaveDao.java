package com.xxm.it.activiti.dao;

import com.xxm.it.activiti.vo.Leave;

/**
 * 请假持久接口
 * 
 * @author Administrator
 *
 */
public interface ILeaveDao {

	/**
	 * 查询请假申请
	 * 
	 * @param leave
	 */
	public void findLeaveList(Leave leave);

	/**
	 * 查询请假申请总数
	 * 
	 * @param leave
	 */
	public int findLeaveListCount(Leave leave);

	/**
	 * 查询请假申请详细信息
	 * 
	 * @param leave
	 */
	public Leave findLeaveInfo(Leave leave);

	/**
	 * 增加请假申请
	 * 
	 * @param leave
	 */
	public void addLeave(Leave leave);

	/**
	 * 修改请假申请
	 * 
	 * @param leave
	 */
	public void updateLeave(Leave leave);

}
