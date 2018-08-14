package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class GpsInfoVO extends CommonVO{
	private static final long serialVersionUID = 1L;
	private String gpsId;//Id
	private String model;//型号
	private String brand;//品牌
	private String manufacturers;//厂家
	private String remark;//其他说明
	private String sellingPrice;//标准售价
	private String costing;//标准成本
	private String startCreateDate;// 开始
	private String endCreateDate;// 结束
	private String[] gpsIds = new String[] {};
	private String gpsPlaceVL; //位置
	private String gpsPlaceName; //位置名称
	private String priductSN;//产品序列号
	private String entryType; //入仓类型
	private String entryMan; //入仓人
	private String entryTime; //入仓时间
	private String outWarehouseType;//出仓类型
	private String outWarehouseMan;//出仓人
	private String outWarehouseTime;//出仓时间
	private String spId;
	
	private InstallVO installVO=new InstallVO();
	private BaseLeaseInfoVO baseLeaseInfoVO=new BaseLeaseInfoVO();
	private ProviderVO providerVO=new ProviderVO();
	private ApplyVO applyVO=new ApplyVO();
	
	public ApplyVO getApplyVO() {
		return applyVO;
	}
	public void setApplyVO(ApplyVO applyVO) {
		this.applyVO = applyVO;
	}

	public InstallVO getInstallVO() {
		return installVO;
	}
	public void setInstallVO(InstallVO installVO) {
		this.installVO = installVO;
	}
	
	public ProviderVO getProviderVO() {
		return providerVO;
	}
	public void setProviderVO(ProviderVO providerVO) {
		this.providerVO = providerVO;
	}
	public String getStartCreateDate() {
		return startCreateDate;
	}
	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}
	public String getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	public String[] getGpsIds() {
		return gpsIds;
	}
	public void setGpsIds(String[] gpsIds) {
		this.gpsIds = gpsIds;
	}
	
	public String getGpsId() {
		return gpsId;
	}
	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getCosting() {
		return costing;
	}
	public void setCosting(String costing) {
		this.costing = costing;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGpsPlaceVL() {
		return gpsPlaceVL;
	}
	public void setGpsPlaceVL(String gpsPlaceVL) {
		this.gpsPlaceVL = gpsPlaceVL;
	}
	public String getGpsPlaceName() {
		return gpsPlaceName;
	}
	public void setGpsPlaceName(String gpsPlaceName) {
		this.gpsPlaceName = gpsPlaceName;
	}
	public String getPriductSN() {
		return priductSN;
	}
	public void setPriductSN(String priductSN) {
		this.priductSN = priductSN;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public String getEntryMan() {
		return entryMan;
	}
	public void setEntryMan(String entryMan) {
		this.entryMan = entryMan;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getOutWarehouseType() {
		return outWarehouseType;
	}
	public void setOutWarehouseType(String outWarehouseType) {
		this.outWarehouseType = outWarehouseType;
	}
	public String getOutWarehouseMan() {
		return outWarehouseMan;
	}
	public void setOutWarehouseMan(String outWarehouseMan) {
		this.outWarehouseMan = outWarehouseMan;
	}
	public String getOutWarehouseTime() {
		return outWarehouseTime;
	}
	public void setOutWarehouseTime(String outWarehouseTime) {
		this.outWarehouseTime = outWarehouseTime;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public BaseLeaseInfoVO getBaseLeaseInfoVO() {
		return baseLeaseInfoVO;
	}
	public void setBaseLeaseInfoVO(BaseLeaseInfoVO baseLeaseInfoVO) {
		this.baseLeaseInfoVO = baseLeaseInfoVO;
	}
	
}
