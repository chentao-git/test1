package com.xxm.it.business.vo;


import com.xxm.it.system.vo.CommonVO;

/**
 * 车辆保险对象
 * 
 * @author Administrator
 *
 */
public class CarInsuranceVO extends CommonVO {

	private static final long serialVersionUID = 1L;
	private String carInsuranceId;//车辆保险ID
	private String carTypeVL;//类型
	private String carTypeVLName;//类型
	private String carTypeName;//类型名称
	private String thirdInsuranceVL;//商业第三者责任险
	private String thirdInsuranceName;//商业第三者责任险名称
	private String riskGlassBreakage;//玻璃单独破碎险
	private String otherInsurance1;//
	private String otherInsurance2;//
	private String otherInsurance3;//
	private String otherInsurance4;//
	private String otherInsurance5;//
	private String majorInsuranceVLS;//主要险种
	private String applyInfoId;//
	public String getCarInsuranceId() {
		return carInsuranceId;
	}
	public void setCarInsuranceId(String carInsuranceId) {
		this.carInsuranceId = carInsuranceId;
	}
	public String getCarTypeVL() {
		return carTypeVL;
	}
	public void setCarTypeVL(String carTypeVL) {
		this.carTypeVL = carTypeVL;
	}
	public String getThirdInsuranceVL() {
		return thirdInsuranceVL;
	}
	public void setThirdInsuranceVL(String thirdInsuranceVL) {
		this.thirdInsuranceVL = thirdInsuranceVL;
	}
	public String getRiskGlassBreakage() {
		return riskGlassBreakage;
	}
	public void setRiskGlassBreakage(String riskGlassBreakage) {
		this.riskGlassBreakage = riskGlassBreakage;
	}
	public String getOtherInsurance1() {
		return otherInsurance1;
	}
	public void setOtherInsurance1(String otherInsurance1) {
		this.otherInsurance1 = otherInsurance1;
	}
	public String getOtherInsurance2() {
		return otherInsurance2;
	}
	public void setOtherInsurance2(String otherInsurance2) {
		this.otherInsurance2 = otherInsurance2;
	}
	public String getOtherInsurance3() {
		return otherInsurance3;
	}
	public void setOtherInsurance3(String otherInsurance3) {
		this.otherInsurance3 = otherInsurance3;
	}
	public String getOtherInsurance4() {
		return otherInsurance4;
	}
	public void setOtherInsurance4(String otherInsurance4) {
		this.otherInsurance4 = otherInsurance4;
	}
	public String getOtherInsurance5() {
		return otherInsurance5;
	}
	public void setOtherInsurance5(String otherInsurance5) {
		this.otherInsurance5 = otherInsurance5;
	}
	public String getMajorInsuranceVLS() {
		return majorInsuranceVLS;
	}
	public void setMajorInsuranceVLS(String majorInsuranceVLS) {
		this.majorInsuranceVLS = majorInsuranceVLS;
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
	public String getCarTypeName() {
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}
	public String getThirdInsuranceName() {
		return thirdInsuranceName;
	}
	public void setThirdInsuranceName(String thirdInsuranceName) {
		this.thirdInsuranceName = thirdInsuranceName;
	}
	public String getCarTypeVLName() {
		return carTypeVLName;
	}
	public void setCarTypeVLName(String carTypeVLName) {
		this.carTypeVLName = carTypeVLName;
	}

}
