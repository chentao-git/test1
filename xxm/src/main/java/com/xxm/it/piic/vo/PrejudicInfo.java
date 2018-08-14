package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrejudicInfo")
//@XmlType(propOrder={"id","name","age","birthDay","list","teacher","map"}) //指定序列成的xml节点顺序
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class PrejudicInfo {
	@XmlElement(name = "GeneralInfo")
	private GeneralInfoVO generalInfoVO;
	@XmlElement(name = "Prejudication")
	private PrejudicationVO prejudicationVO;

	
	public PrejudicationVO getPrejudicationVO() {
		return prejudicationVO;
	}

	public void setPrejudicationVO(PrejudicationVO prejudicationVO) {
		this.prejudicationVO = prejudicationVO;
	}
	
	public GeneralInfoVO getGeneralInfoVO() {
		return generalInfoVO;
	}

	public void setGeneralInfoVO(GeneralInfoVO generalInfoVO) {
		this.generalInfoVO = generalInfoVO;
	}
	
	
   
}
