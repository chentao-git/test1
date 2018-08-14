package com.xxm.it.piic.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 投保响应
 * @author Administrator
 *
 */
@XmlRootElement(name="ReturnInfo")
@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureReturnInfo {
	@XmlElement(name = "GeneralInfoReturn")
	private GeneralInfoReturnVO generalInfoReturnVO;
	@XmlElementWrapper(name = "PolicyInfoReturns") 
    @XmlElement(name = "PolicyInfoReturn")
	private List<PolicyinfoReturnVO> PolicyinfoReturnVO;
	
	public GeneralInfoReturnVO getGeneralInfoReturnVO() {
		return generalInfoReturnVO;
	}
	public void setGeneralInfoReturnVO(GeneralInfoReturnVO generalInfoReturnVO) {
		this.generalInfoReturnVO = generalInfoReturnVO;
	}
	public List<PolicyinfoReturnVO> getPolicyinfoReturnVO() {
		return PolicyinfoReturnVO;
	}
	public void setPolicyinfoReturnVO(List<PolicyinfoReturnVO> policyinfoReturnVO) {
		PolicyinfoReturnVO = policyinfoReturnVO;
	}
	
	
}
