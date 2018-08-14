package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 保证保险对象
 * 
 * @author Administrator
 *
 */
public class GuaranteeInsuranceVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String guaranteeInsuranceId;//保证保险ID
	private String carTypeVL;//
	private String carTypeVLName;//
	private String mainInsuranceCompany;//主联保险公司
	private String insuranceCompanyVL;//保险公司
	private String letterIntentNo;//承诺意向书编号
	private String applyInfoId;//
	public String getGuaranteeInsuranceId() {
		return guaranteeInsuranceId;
	}
	public void setGuaranteeInsuranceId(String guaranteeInsuranceId) {
		this.guaranteeInsuranceId = guaranteeInsuranceId;
	}
	public String getCarTypeVL() {
		return carTypeVL;
	}
	public void setCarTypeVL(String carTypeVL) {
		this.carTypeVL = carTypeVL;
	}
	public String getMainInsuranceCompany() {
		return mainInsuranceCompany;
	}
	public void setMainInsuranceCompany(String mainInsuranceCompany) {
		this.mainInsuranceCompany = mainInsuranceCompany;
	}
	public String getInsuranceCompanyVL() {
		return insuranceCompanyVL;
	}
	public void setInsuranceCompanyVL(String insuranceCompanyVL) {
		this.insuranceCompanyVL = insuranceCompanyVL;
	}
	public String getLetterIntentNo() {
		return letterIntentNo;
	}
	public void setLetterIntentNo(String letterIntentNo) {
		this.letterIntentNo = letterIntentNo;
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
	public String getCarTypeVLName() {
		return carTypeVLName;
	}
	public void setCarTypeVLName(String carTypeVLName) {
		this.carTypeVLName = carTypeVLName;
	}

}
