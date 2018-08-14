package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class InstallVO extends CommonVO{
	private static final long serialVersionUID = 1L;

	//xxm_client_repertory_t
	private String installId;//Id
	private String productSn;//产品序列号
	private String installBy;//安装人
	private String installDate;//安装时间
	private String installSite;//安装地点
	private String installCarSite;//安装车上位置
	private String installIphone;//安装电话
	private String partSite;//位置零件
	private String startInstallDate;// 开始
	private String endInstallDate;// 结束

	//车辆对象
	private BaseLeaseInfoVO baseLeaseInfoVO=new BaseLeaseInfoVO();
	private ApplyVO applyVO=new ApplyVO();
	
	public ApplyVO getApplyVO() {
		return applyVO;
	}
	public void setApplyVO(ApplyVO applyVO) {
		this.applyVO = applyVO;
	}
	public String getStartInstallDate() {
		return startInstallDate;
	}
	public void setStartInstallDate(String startInstallDate) {
		this.startInstallDate = startInstallDate;
	}
	public String getEndInstallDate() {
		return endInstallDate;
	}
	public void setEndInstallDate(String endInstallDate) {
		this.endInstallDate = endInstallDate;
	}
	public String getPartSite() {
		return partSite;
	}
	public void setPartSite(String partSite) {
		this.partSite = partSite;
	}
	public String getProductSn() {
		return productSn;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getInstallId() {
		return installId;
	}
	public void setInstallId(String installId) {
		this.installId = installId;
	}
	public String getInstallBy() {
		return installBy;
	}

	public void setInstallBy(String installBy) {
		this.installBy = installBy;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getInstallSite() {
		return installSite;
	}

	public void setInstallSite(String installSite) {
		this.installSite = installSite;
	}

	public String getInstallCarSite() {
		return installCarSite;
	}

	public void setInstallCarSite(String installCarSite) {
		this.installCarSite = installCarSite;
	}

	public String getInstallIphone() {
		return installIphone;
	}

	public void setInstallIphone(String installIphone) {
		this.installIphone = installIphone;
	}
	public BaseLeaseInfoVO getBaseLeaseInfoVO() {
		return baseLeaseInfoVO;
	}
	public void setBaseLeaseInfoVO(BaseLeaseInfoVO baseLeaseInfoVO) {
		this.baseLeaseInfoVO = baseLeaseInfoVO;
	}

}
