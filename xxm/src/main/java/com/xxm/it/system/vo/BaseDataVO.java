package com.xxm.it.system.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础数据实体类
 * 
 * @author Administrator
 *
 */
public class BaseDataVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String baseId;// 主键
	private String baseParentId;// 父节点Id
	private String baseName;// 名称
	private String parentBaseName;// 父节点名称
	private String baseCode;// 编码
	private String baseCodeValue;// 编码值
	private String level;// 级别
	private String validFlag;// 状态
	private String remark;// 备注
	private String[] baseIds = new String[] {};// baseIs集合
	private List<String> baseIdList = null;// 父节点Id集合
	private String[] baseCodeValueArray = null;// 编码值集合
	private String matchBaseCode;// 模糊匹配基础数据编码
	private String matchBaseName;// 模糊匹配基础数据名称
	private List<BaseDataVO> childrenList = new ArrayList<BaseDataVO>();// 子节点集合

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getBaseParentId() {
		return baseParentId;
	}

	public void setBaseParentId(String baseParentId) {
		this.baseParentId = baseParentId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getParentBaseName() {
		return parentBaseName;
	}

	public void setParentBaseName(String parentBaseName) {
		this.parentBaseName = parentBaseName;
	}

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	public String getBaseCodeValue() {
		return baseCodeValue;
	}

	public void setBaseCodeValue(String baseCodeValue) {
		this.baseCodeValue = baseCodeValue;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getBaseIds() {
		return baseIds;
	}

	public void setBaseIds(String[] baseIds) {
		this.baseIds = baseIds;
	}

	public List<String> getBaseIdList() {
		return baseIdList;
	}

	public void setBaseIdList(List<String> baseIdList) {
		this.baseIdList = baseIdList;
	}

	public String[] getBaseCodeValueArray() {
		return baseCodeValueArray;
	}

	public void setBaseCodeValueArray(String[] baseCodeValueArray) {
		this.baseCodeValueArray = baseCodeValueArray;
	}

	public String getMatchBaseName() {
		return matchBaseName;
	}

	public void setMatchBaseName(String matchBaseName) {
		this.matchBaseName = matchBaseName;
	}

	public String getMatchBaseCode() {
		return matchBaseCode;
	}

	public void setMatchBaseCode(String matchBaseCode) {
		this.matchBaseCode = matchBaseCode;
	}

	public List<BaseDataVO> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<BaseDataVO> childrenList) {
		this.childrenList = childrenList;
	}

}
