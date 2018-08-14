package com.xxm.it.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.business.task.SignInfoQueryCronConfig;
import com.xxm.it.system.dao.IBaseDataDao;
import com.xxm.it.system.service.IBaseDataService;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmEhcacheUtils;
import com.xxm.it.system.vo.BaseDataVO;

import net.sf.json.JSONObject;

/**
 * 基础数据逻辑处理实现类
 * 
 * @author Administrator
 *
 */
@Component
@Transactional
public class BaseDataServiceImpl implements IBaseDataService {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(BaseDataServiceImpl.class);

	/**
	 * 数据持久层实现类
	 */
	@Resource
	private IBaseDataDao baseDataDao;

	@Resource
	private IUserService userService;

	/**
	 * 分页查询基础数据信息
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVo>
	 */
	public JSONObject findBaseDataList(BaseDataVO baseDataVO) {
		JSONObject jsonObject = new JSONObject();
		// 查询集合数据总数
		int total = baseDataDao.findBaseDataListCount(baseDataVO);
		// 查询集合数据
		List<BaseDataVO> baseDataList = new ArrayList<BaseDataVO>();
		if (total > 0) {
			baseDataList = baseDataDao.findBaseDataList(baseDataVO);
		}
		jsonObject.put("rows", baseDataList);
		jsonObject.put("total", total);
		return jsonObject;
	}

	/**
	 * 添加基础数据
	 * 
	 * @param baseDataVO
	 * @return resultMap
	 */
	public Map<String, Object> addBaseData(BaseDataVO baseDataVO) {
		// 消息对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (null == baseDataVO.getBaseParentId() || "".equals(baseDataVO.getBaseParentId())) {
				baseDataVO.setBaseParentId("0");
			} else {
				// 设置子项编码
				BaseDataVO bdVO = new BaseDataVO();
				bdVO.setBaseId(baseDataVO.getBaseParentId());
				BaseDataVO baseData = baseDataDao.findBaseDataById(bdVO);
				baseDataVO.setBaseCode(baseData.getBaseCode());
			}
			// 添加基础数据
			baseDataDao.createBaseData(baseDataVO);
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("msg", "添加基础数据成功！");
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "添加基础数据失败！");
			logger.info("添加基础数据失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 修改基础数据
	 * 
	 * @param baseDataVO
	 * @return resultMap
	 */
	public Map<String, Object> updateBaseData(BaseDataVO baseDataVO) {
		// 消息对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			BaseDataVO BaseDataVO = new BaseDataVO();
			BaseDataVO.setBaseId(baseDataVO.getBaseId());
			BaseDataVO = baseDataDao.findBaseDataById(BaseDataVO);
			if (null != BaseDataVO) {
				// 当父配置的编码修改了，所有子项的编码也要修改。
				baseDataDao.updateBaseCode(BaseDataVO.getBaseCode(), baseDataVO.getBaseCode());
				baseDataDao.updateBaseData(baseDataVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "修改基础数据成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "修改基础数据失败！");
			logger.info("修改基础数据失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 删除基础数据
	 * 
	 * @param baseDataVO
	 * @return resultMap
	 */
	public Map<String, Object> deleteBaseData(BaseDataVO baseDataVO) {
		// 消息对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 查询是否存在子节点
			// 判断是否存在子节点，如果存在则不允许删除
			List<BaseDataVO> baseDataList = baseDataDao.findExistsParentBaseData(baseDataVO);
			if (null != baseDataList && baseDataList.size() > 0) {
				String baseNames = "";
				for (int i = 0; i < baseDataList.size(); i++) {
					baseNames += baseDataList.get(i).getBaseName();
					if (i != baseDataList.size() - 1) {
						baseNames += ",";
					}
				}
				resultMap.put("result", Boolean.FALSE);
				resultMap.put("msg", "编码名称[" + baseNames + "]存在子节点数据，不允许删除！");
			} else {
				// 删除基础数据
				baseDataDao.deleteBaseData(baseDataVO);
				resultMap.put("result", Boolean.TRUE);
				resultMap.put("msg", "删除基础数据成功！");
			}
		} catch (Exception e) {
			resultMap.put("result", Boolean.FALSE);
			resultMap.put("msg", "删除基础数据失败！");
			logger.error("删除基础数据失败！" + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 根据baseId查询基础数据信息
	 * 
	 * @param vaseDataVo
	 * @return BaseDataVo
	 */
	public BaseDataVO findBaseDataInfo(BaseDataVO baseDataVO) {
		return baseDataDao.findBaseDataById(baseDataVO);
	}

	/**
	 * 根据条件查询基础数据信息
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVo>
	 */
	public List<BaseDataVO> findBaseDataListByCode(BaseDataVO baseDataVO) {
		return baseDataDao.findBaseDataListByCode(baseDataVO);
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据
	 * 
	 * @param baseDataVO
	 * @return BaseDataVO
	 */
	public BaseDataVO findBaseDataListByCodeEhcache(BaseDataVO baseDataVO) {
		// 放入缓存中
		Object object = XxmEhcacheUtils.get(XxmEhcacheUtils.SHARE_CONFIG_CACHE, baseDataVO.getBaseCode());
		if (null != object) {
			return (BaseDataVO) object;
		}
		return null;
	}

	/**
	 * 初始化基础数据
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVo>
	 */
	public void initBaseData() {
		BaseDataVO parmBaseDataVO = new BaseDataVO();
		parmBaseDataVO.setValidFlag(XxmConstant.BaseDataConstant.STATUS_EFFECTIVE);
		// 查询所有基础数据列表
		List<BaseDataVO> allBaseDataList = baseDataDao.findAllBaseDataList(parmBaseDataVO);
		// 判断集合是否为空
		if (null != allBaseDataList && !allBaseDataList.isEmpty()) {
			for (int i = 0; i < allBaseDataList.size(); i++) {
				BaseDataVO baseDataVO = allBaseDataList.get(i);
				int level = 0;
				if (null != baseDataVO.getLevel() && level == Integer.parseInt(baseDataVO.getLevel())) {
					// 递归子节点数据
					baseDataVO.setChildrenList(recursionBaseData(allBaseDataList, level + 1, baseDataVO.getBaseId()));
					logger.debug("base data code " + baseDataVO.getBaseCode() + ":"
							+ JSONObject.fromObject(baseDataVO).toString());
					// 放入缓存中
					XxmEhcacheUtils.put(XxmEhcacheUtils.SHARE_CONFIG_CACHE, baseDataVO.getBaseCode(), baseDataVO);
				}
			}
		}
	}

	/**
	 * 递归子节点数据
	 * 
	 * @param list
	 * @param level
	 * @param baseId
	 * @return
	 */
	private List<BaseDataVO> recursionBaseData(List<BaseDataVO> list, int level, String baseId) {
		List<BaseDataVO> childList = new ArrayList<BaseDataVO>();
		for (int i = 0; i < list.size(); i++) {
			BaseDataVO baseDataVO = list.get(i);
			if (null != baseDataVO.getLevel() && level == Integer.parseInt(baseDataVO.getLevel())
					&& baseId.equals(baseDataVO.getBaseParentId())) {
				// 递归子节点数据
				baseDataVO.setChildrenList(recursionBaseData(list, level + 1, baseDataVO.getBaseId()));
				childList.add(baseDataVO);
			}
		}
		return childList;
	}

	/**
	 * 初始化定时任务配置数据
	 * 
	 * @param baseDataVO
	 * @return List<BaseDataVo>
	 */
	public void initTaskBaseData(BaseDataVO baseDataVO) {
		baseDataVO.setValidFlag(XxmConstant.BaseDataConstant.STATUS_EFFECTIVE);
		// 查询所有基础数据列表
		List<BaseDataVO> baseDataList = baseDataDao.findBaseDataListByCode(baseDataVO);
		// 判断集合是否为空
		if (null != baseDataList && !baseDataList.isEmpty()) {
			for (int i = 0; i < baseDataList.size(); i++) {
				BaseDataVO bdataVO = baseDataList.get(i);
				if (null != bdataVO.getLevel() && 1 == Integer.parseInt(bdataVO.getLevel())) {
					// 签约信息查询定时任务配置
					if ("SignInfoQueryCron".equals(bdataVO.getBaseCodeValue())) {
						SignInfoQueryCronConfig.setCron(bdataVO.getExtendField1());
					}
				}
			}
		}
	}

	/**
	 * 通过反射的方式执行某个对象的方法
	 * 
	 * @param cls
	 *            对象类型
	 * @param obj
	 *            对象
	 * @param methodName
	 *            对象方法名称
	 * @return result 反射方法返回值
	 */
	public static void execDynamicMethod(Class<?> cls, String methodName, Object... args) {
		try {
			// 创建对象的实例
			Object obj = cls.newInstance();
			// 多参数类型设置
			Class<?>[] argsClass = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				argsClass[i] = args[i].getClass();
			}
			Method method = cls.getDeclaredMethod(methodName, argsClass);
			method.invoke(obj, args);
		} catch (InstantiationException e) {
			logger.error("execDynamicMethod InstantiationException:" + e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("execDynamicMethod IllegalAccessException:" + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.error("execDynamicMethod NoSuchMethodException:" + e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			logger.error("execDynamicMethod SecurityException:" + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error("execDynamicMethod IllegalArgumentException:" + e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("execDynamicMethod InvocationTargetException:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
