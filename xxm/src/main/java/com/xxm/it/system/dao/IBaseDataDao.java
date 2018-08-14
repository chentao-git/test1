package com.xxm.it.system.dao;

import java.util.List;

import com.xxm.it.system.vo.BaseDataVO;
import com.xxm.it.system.vo.FlowLogVO;

/**
 * 基础数据持久类
 * 
 * @author Administrator
 *
 */
public interface IBaseDataDao {

	/**
	 * 分页查询基础数据信息
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVo>
	 */
	public List<BaseDataVO> findBaseDataList(BaseDataVO vaseDataVo);

	/**
	 * 查询基础数据总数
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVo>
	 */
	public int findBaseDataListCount(BaseDataVO vaseDataVo);

	/**
	 * 查询是否存在子节点数据
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVo>
	 */
	public List<BaseDataVO> findExistsParentBaseData(BaseDataVO vaseDataVo);

	/**
	 * 添加基础数据
	 * 
	 * @param vaseDataVo
	 * @return int
	 */
	public int createBaseData(BaseDataVO vaseDataVo);

	/**
	 * 修改基础数据
	 * 
	 * @param vaseDataVo
	 * @return int
	 */
	public int updateBaseData(BaseDataVO vaseDataVo);

	/**
	 * 删除基础数据
	 * 
	 * @param baseId
	 * @return int
	 */
	public int deleteBaseData(BaseDataVO baseId);

	/**
	 * 根据基础Id查询基础数据
	 * 
	 * @param baseId
	 * @return BaseDataVo
	 */
	public BaseDataVO findBaseDataById(BaseDataVO vaseDataVo);

	/**
	 * 修改基础数据编码（当父配置编码修改了子项的编码都要修改）
	 * 
	 * @param oldCode(旧的编码),newCode(新的编码)
	 * @return
	 */
	public void updateBaseCode(String oldCode, String newCode);

	/**
	 * 根据code查询基础数据信息
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVo>
	 */
	public List<BaseDataVO> findBaseDataListByCode(BaseDataVO vaseDataVo);

	/**
	 * 查询所有基础数据列表信息
	 * 
	 * @param vaseDataVo
	 * @return List<BaseDataVO>
	 */
	public List<BaseDataVO> findAllBaseDataList(BaseDataVO vaseDataVo);

	/**
	 * 创建流程日志
	 * 
	 * @param flowLogVO
	 *            流程日志对象
	 */
	public void createFlowLog(FlowLogVO flowLogVO);

	/**
	 * 查询流程日志
	 * 
	 * @param flowLogVO
	 * @return
	 */
	public List<FlowLogVO> findFlowLogList(FlowLogVO flowLogVO);
}
