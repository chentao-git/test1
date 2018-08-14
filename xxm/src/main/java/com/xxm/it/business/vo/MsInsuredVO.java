package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

/**
 * 被保人信息对象
 * 
 * @author Administrator
 *
 */
public class MsInsuredVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String insuredId;//
	private String insuredSeqNo;//被保险人序列号
	private String insuredName;//被保险人全称
	private String insuredIdType;//被保险人组织机构代码
	private String insuredIdNo;//被保险人组织机构证件号
	private String bankcode;//银行代码
	private String applyInfoId;//申请id
	private MsBeneficiaryVO msBeneficiaryVO=new MsBeneficiaryVO();
	public String getInsuredId() {
		return insuredId;
	}
	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}
	public String getInsuredSeqNo() {
		return insuredSeqNo;
	}
	public void setInsuredSeqNo(String insuredSeqNo) {
		this.insuredSeqNo = insuredSeqNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredIdType() {
		return insuredIdType;
	}
	public void setInsuredIdType(String insuredIdType) {
		this.insuredIdType = insuredIdType;
	}
	public String getInsuredIdNo() {
		return insuredIdNo;
	}
	public void setInsuredIdNo(String insuredIdNo) {
		this.insuredIdNo = insuredIdNo;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
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
	public MsBeneficiaryVO getMsBeneficiaryVO() {
		return msBeneficiaryVO;
	}
	public void setMsBeneficiaryVO(MsBeneficiaryVO msBeneficiaryVO) {
		this.msBeneficiaryVO = msBeneficiaryVO;
	}

}
