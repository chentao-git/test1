package com.xxm.it.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.xxm.it.system.dao.ILawsuitDao;
import com.xxm.it.system.service.ILawsuitService;
import com.xxm.it.system.vo.FiledVO;
import com.xxm.it.system.vo.RemarkVO;

import net.sf.json.JSONObject;

@Component
@Transactional
public class LawsuitServiceImpl implements ILawsuitService {
	
	private static Logger logger = Logger.getLogger(LawsuitServiceImpl.class);
	
	@Resource
	private ILawsuitDao lawsuitDao;
	
	/***
	 * 查询诉讼集合
	 * ***/
	@Override
	public JSONObject applyInfoList(FiledVO filedvo) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = lawsuitDao.findFiledListCount(filedvo);
		// 查询集合数据
		List<FiledVO> resultLsit = new ArrayList<FiledVO>();
		if (total > 0) {
			resultLsit = lawsuitDao.findSellList(filedvo);
		}
		jsonObject.put("rows", resultLsit);
		jsonObject.put("total", total);

		return jsonObject;
	}
	
	/***
	 * 
	 * 根据贷款编号查询、客户信息、 贷款信息、 车辆信息
	 * ***/
	@Override
	public FiledVO loanNumberQuery(FiledVO filedvo) {
		FiledVO filed = lawsuitDao.loanNumberQuery(filedvo);
		return filed;
	}
	/***
	 * 
	 * 保存诉讼申请
	 * ***/
	@Override
	public Map<String, Object> saveApplyInfo(FiledVO filedvo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			FiledVO filed = new FiledVO();
			filed.setLoanNumber(filedvo.getLoanNumber());
			//根据贷款编号查询 贷款  客户 信息
			filed = lawsuitDao.loanNumberQuery(filed);
			filedvo.setCustomerName(filed.getCustomerName());
			filedvo.setCardNumber(filed.getCardNumber());
			filedvo.setLawsuitVehicle(filed.getLawsuitVehicle());
			filedvo.setAppliedAmount(filed.getAppliedAmount());
			//保存信息
			lawsuitDao.saveApplyInfo(filedvo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加诉讼信息成功！");
			logger.info("诉讼信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加诉讼信息失败！");
		}
		
		return resultMap;
		
		
	}
	/***
	 * 	诉讼审核查询
	 *
	 ***/
	@Override
	public FiledVO auditInfoQuery(FiledVO filedvo) {
		return lawsuitDao.auditInfoQuery(filedvo);
	}
	
	/**
	 * 诉讼详情查询
	 */
	@Override
	public FiledVO auditParticularsQuery(FiledVO filedvo) {
		
		filedvo = lawsuitDao.particularsfinedInfo(filedvo);
		
		List<String> list = lawsuitDao.auditParticularsQuery(filedvo);
		String [] str = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			str[i] = list.get(i);
		}
		filedvo.setRemark(str);
		return filedvo;
	}
	
	/**
	 * 诉讼跟踪信息添加
	 */
	@Override
	public Map<String, Object> addTrackingInfo(RemarkVO filedvo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			lawsuitDao.addTrackingInfo(filedvo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加信息成功！");
			logger.info("诉讼跟踪信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加信息失败！");
		}
		
		return resultMap;
	}
	/**
	 * 审核信息保存
	 */
	@Override
	public Map<String, Object> saveAuditInfo(FiledVO filedvo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			lawsuitDao.saveAuditInfo(filedvo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "信息提交成功！");
			logger.info("审核信息保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "信息提交失败！");
		}
		
		return resultMap;
	}
	
	/***
	 * 处理登记信息保存
	 */
	@Override
	public Map<String, Object> addRegisterInfo(FiledVO filedvo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			lawsuitDao.addRegisterInfo(filedvo);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "信息提交成功！");
			logger.info("处理登记信息保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "信息提交失败！");
		}
		
		return resultMap;
	}

}
