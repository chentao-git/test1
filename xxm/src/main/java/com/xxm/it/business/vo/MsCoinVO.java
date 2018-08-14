package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

/**
 * 投保单归属机构信息对象
 * 
 * @author Administrator
 *
 */
public class MsCoinVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String coinId;//
	private String maincomcode;//主联机构代码
	private String depcomcode;//从联机构代码
	private String mainhandlercode;//主联归属业务员代码
	private String dephandlercode;//从联归属业务员代码
	private String applyInfoId;//申请id
	public String getCoinId() {
		return coinId;
	}
	public void setCoinId(String coinId) {
		this.coinId = coinId;
	}
	public String getMaincomcode() {
		return maincomcode;
	}
	public void setMaincomcode(String maincomcode) {
		this.maincomcode = maincomcode;
	}
	public String getDepcomcode() {
		return depcomcode;
	}
	public void setDepcomcode(String depcomcode) {
		this.depcomcode = depcomcode;
	}
	public String getMainhandlercode() {
		return mainhandlercode;
	}
	public void setMainhandlercode(String mainhandlercode) {
		this.mainhandlercode = mainhandlercode;
	}
	public String getDephandlercode() {
		return dephandlercode;
	}
	public void setDephandlercode(String dephandlercode) {
		this.dephandlercode = dephandlercode;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
