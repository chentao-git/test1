package com.xxm.it.activiti.vo;

/**
 * 统一定义id的entity基类.
 * <p/>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 *
 * @author calvin
 */
// JPA 基类的标识
public abstract class IdEntity {

	protected Long id;

	private int limit;// 分页数大小
	private int offset;// 分页页码

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
