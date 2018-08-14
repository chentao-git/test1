package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class SpProfitVO extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String profitId; //分润配置id
	private String spId; //sp商 id
	private String spName;//sp商名称
	private String profitTypeVL; //分润类别
	private String profitTypeName; //分润类别名称
	private String profitPrice; //销售分润数
	private String productVL; //产品类别
	private String productName;//产品类别名称
	private String status;
	private String[] profitIds = new String[] {} ;
	
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProfitId() {
		return profitId;
	}
	public void setProfitId(String profitId) {
		this.profitId = profitId;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getProfitTypeVL() {
		return profitTypeVL;
	}
	public void setProfitTypeVL(String profitTypeVL) {
		this.profitTypeVL = profitTypeVL;
	}
	public String getProfitPrice() {
		return profitPrice;
	}
	public void setProfitPrice(String profitPrice) {
		this.profitPrice = profitPrice;
	}
	public String getProductVL() {
		return productVL;
	}
	public void setProductVL(String productVL) {
		this.productVL = productVL;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getProfitIds() {
		return profitIds;
	}
	public void setProfitIds(String[] profitIds) {
		this.profitIds = profitIds;
	}
	public String getProfitTypeName() {
		return profitTypeName;
	}
	public void setProfitTypeName(String profitTypeName) {
		this.profitTypeName = profitTypeName;
	}
}
