package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ApplyInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureApplyInfo {
//	@XmlJavaTypeAdapter(value = DataAdapter.class)  
	@XmlElement(name = "GeneralInfo")
	private GeneralInfoVO generalInfoVO;
	@XmlElementWrapper(name = "PolicyInfos") 
    @XmlElement(name = "PolicyInfo")
	private List<InsurePolicyInfo> InsurePolicyInfo;
	
	
	public GeneralInfoVO getGeneralInfoVO() {
		return generalInfoVO;
	}
	public void setGeneralInfoVO(GeneralInfoVO generalInfoVO) {
		this.generalInfoVO = generalInfoVO;
	}
	public List<InsurePolicyInfo> getInsurePolicyInfo() {
		return InsurePolicyInfo;
	}
	public void setInsurePolicyInfo(List<InsurePolicyInfo> insurePolicyInfo) {
		InsurePolicyInfo = insurePolicyInfo;
	}
	
	
}
