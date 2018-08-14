package com.xxm.it.activiti.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti.dao.ILeaveDao;
import com.xxm.it.activiti.vo.Leave;

/**
 * 请假实体管理
 *
 * @author HenryYan
 */
@Component
@Transactional(readOnly = true)
public class LeaveManager {

	private ILeaveDao leaveDao;

	public Leave getLeave(Long id) {
		Leave leave = new Leave();
		leave.setId(id);
		return leaveDao.findLeaveInfo(leave);
	}

	public void saveLeave(Leave entity) {
		if (entity.getId() == null) {
			entity.setApplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			leaveDao.addLeave(entity);
		} else {
			leaveDao.updateLeave(entity);
		}
	}

	public void updateLeave(Leave entity) {
		leaveDao.updateLeave(entity);
	}

	@Autowired
	public void setLeaveDao(ILeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}

}
