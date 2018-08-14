package com.xxm.it.business.dao;

import java.util.List;

import com.xxm.it.business.vo.ActIdGroupVO;
import com.xxm.it.system.vo.UserVO;

public interface IActIdGroupDao {
	/*
	 * 查询数据集合
	 */
	public List<ActIdGroupVO> findActIdGroupList(ActIdGroupVO actIdGroupVO);
	/*
	 * 查询数据数量
	 */
	public int findActIdGroupListCount(ActIdGroupVO actIdGroupVO);
	/*
	 * 查询数据信息
	 */
	public ActIdGroupVO findActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 增加数据
	 */
	public void addActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 修改数据
	 */
	public void updateActIdGroup(ActIdGroupVO actIdGroupVO);
	/*
	 * 删除数据
	 */
	public void deleteActIdGroup(ActIdGroupVO actIdGroupVO);

	public List<UserVO> findActIdGroups(ActIdGroupVO actIdGroupVO);
	/*
	 * 查询权限表
	 */
	public List<UserVO> findGroupUserList(ActIdGroupVO actIdGroupVO);
	/*
	 * 删除权限表
	 */
	public void deleteMembership(ActIdGroupVO actIdGroupVO);
	/*
	 * 增加Membership表数据
	 */
	public void addMembership(ActIdGroupVO actIdGroupVO);
}
