package com.xxm.it.activiti.dao;

/**
 * Activiti相关DAO操作
 * 
 * @author: Henry Yan
 */
public interface ActivitiDao {

	// @PersistenceContext
	// private EntityManager entityManager;

	// /**
	// * 流程完成后清理detail表中的表单类型数据
	// * @param processInstanceId
	// * @return
	// */
	// public int deleteFormPropertyByProcessInstanceId(String
	// processInstanceId) {
	// int i = entityManager.createNativeQuery("delete from act_hi_detail where
	// proc_inst_id_ = ? and type_ = 'FormProperty' ")
	// .setParameter(1, processInstanceId).executeUpdate();
	// return i;
	// }

	/**
	 * 流程完成后清理detail表中的表单类型数据
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public int deleteFormPropertyByProcessInstanceId(String processInstanceId);

}
