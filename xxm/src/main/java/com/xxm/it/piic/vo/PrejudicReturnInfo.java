package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrejudicReturnInfo")
//@XmlType(propOrder={"id","name","age","birthDay","list","teacher","map"}) //指定序列成的xml节点顺序
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class PrejudicReturnInfo {
	@XmlElement(name = "GeneralInfoReturn")
	private GeneralInfoReturnVO generalInfoReturnVO;
	@XmlElement(name = "PrejudicationReturn")
	private RejudicationReturnVO rejudicationReturnVO;
	
	public GeneralInfoReturnVO getGeneralInfoReturnVO() {
		return generalInfoReturnVO;
	}
	public void setGeneralInfoReturnVO(GeneralInfoReturnVO generalInfoReturnVO) {
		this.generalInfoReturnVO = generalInfoReturnVO;
	}
	public RejudicationReturnVO getRejudicationReturnVO() {
		return rejudicationReturnVO;
	}
	public void setRejudicationReturnVO(RejudicationReturnVO rejudicationReturnVO) {
		this.rejudicationReturnVO = rejudicationReturnVO;
	}
	
}
