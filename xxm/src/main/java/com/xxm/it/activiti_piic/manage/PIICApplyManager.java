package com.xxm.it.activiti_piic.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.system.dao.IBaseDataDao;
import com.xxm.it.system.dao.IUserDao;
import com.xxm.it.system.vo.FlowLogVO;
import com.xxm.it.system.vo.UserVO;

/**
 * PIIC申请实体管理
 *
 * @author HenryYan
 */
@Component
@Transactional(readOnly = true)
public class PIICApplyManager {

	private IApplyDao applyDao;

	private IUserDao userDao;

	private IBaseDataDao baseDataDao;

	/**
	 * 查询申请信息
	 * 
	 * @param applyInfoId
	 *            申Id
	 * @return
	 */
	public ApplyVO getPIICApplyInfo(String applyInfoId) {
		ApplyVO applyVO = new ApplyVO();
		applyVO.setApplyInfoId(applyInfoId);
		return applyDao.findApplyInfo(applyVO);
	}

	/**
	 * 查询用户姓名
	 * 
	 * @param userId
	 *            参数
	 * 
	 * @return resultUser 返回结果
	 */
	public String getUserName(String userId) {
		if (null != userId && !"".equals(userId)) {
			UserVO user = new UserVO();
			user.setUserId(userId);
			UserVO userVO = userDao.findUser(user);
			if (null != userVO) {
				return userVO.getName();
			}
		}
		return userId;
	}

	/**
	 * 查询申请信息
	 * 
	 * @param ApplyVO
	 *            申请参数
	 * @return
	 */
	public ApplyVO getPIICApplyInfo(ApplyVO applyVO) {
		return applyDao.findApplyInfo(applyVO);
	}

	/**
	 * 创建日志
	 * 
	 * @param flowLogVO
	 */
	public void createFlowLog(FlowLogVO flowLogVO) {
		baseDataDao.createFlowLog(flowLogVO);
	}

	/**
	 * 查询流程日志
	 * 
	 * @param flowLogVO
	 * @return list
	 */
	public List<FlowLogVO> findFlowLogList(FlowLogVO flowLogVO) {
		return baseDataDao.findFlowLogList(flowLogVO);
	}

	@Autowired
	public void setLeaveDao(IApplyDao applyDao) {
		this.applyDao = applyDao;
	}

	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setBaseDataDao(IBaseDataDao baseDataDao) {
		this.baseDataDao = baseDataDao;
	}

}
