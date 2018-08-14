package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 受益人信息对象
 * 
 * @author Administrator
 *
 */
public class MsBeneficiaryVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String beneficiaryId;//
	private String beneficiarySeqNo;//受益人序号
	private String beneficiary;//受益人名称
	private String insuredId;//被保人信息id
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getBeneficiarySeqNo() {
		return beneficiarySeqNo;
	}
	public void setBeneficiarySeqNo(String beneficiarySeqNo) {
		this.beneficiarySeqNo = beneficiarySeqNo;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getInsuredId() {
		return insuredId;
	}
	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
