package com.xxm.it.system.dao;

import java.util.List;
import com.xxm.it.system.vo.FiledVO;
import com.xxm.it.system.vo.RemarkVO;

public interface ILawsuitDao {
	/***
	 * 根据贷款编号查询信息
	 * @param filedvo
	 * @return
	 */
	FiledVO loanNumberQuery(FiledVO filedvo);

	/***
	 * 查询诉讼申请列表总数
	 * @param filedvo
	 * @return
	 */
	int findFiledListCount(FiledVO filedvo);
	
	/***
	 * 查询诉讼申请列表信息
	 * @param filedvo
	 * @return
	 */
	List<FiledVO> findSellList(FiledVO filedvo);
	
	/***
	 * 保存诉讼申请信息
	 * @param filedvo
	 */
	void saveApplyInfo(FiledVO filedvo);
	
	/****
	 * 查询诉讼审核信息
	 * @param filedvo
	 * @return
	 */
	FiledVO auditInfoQuery(FiledVO filedvo);
	
	/***
	 * 诉讼备注跟踪查询
	 * @param filedvo
	 * @return
	 */
	List<String> auditParticularsQuery(FiledVO filedvo);
	/***
	 * 诉讼备注详情查询
	 * @param filedvo
	 * @return
	 */
	FiledVO particularsfinedInfo(FiledVO filedvo);
	/**
	 * 添加诉讼跟踪信息
	 * @param filedvo
	 */
	void addTrackingInfo(RemarkVO filedvo);
	/**
	 * 保存审核信息
	 * @param filedvo
	 */
	void saveAuditInfo(FiledVO filedvo);
	
	/**
	 * 处理登记信息保存 
	 * @param filedvo
	 */
	void addRegisterInfo(FiledVO filedvo);
	
	
}
