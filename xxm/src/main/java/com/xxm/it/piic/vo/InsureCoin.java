package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class InsureCoin {
	@XmlElement(name="Maincomcode")
	private String maincomcode;
	@XmlElement(name="Depcomcode")
	private String depcomcode;
	@XmlElement(name="Mainhandlercode")
	private String mainhandlercode;
	@XmlElement(name="Dephandlercode")
	private String dephandlercode;
	
	
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
	
	
}
