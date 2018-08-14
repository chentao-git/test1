package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureBeneficiary {
//	@XmlElement(name="BeneficiarySeqNo")
//	private String beneficiarySeqNo;//受益人序号
	@XmlElement(name="BenefitName")
	private String beneficiary;//受益人名称
//	public String getBeneficiarySeqNo() {
//		return beneficiarySeqNo;
//	}
//	public void setBeneficiarySeqNo(String beneficiarySeqNo) {
//		this.beneficiarySeqNo = beneficiarySeqNo;
//	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	
	
}
