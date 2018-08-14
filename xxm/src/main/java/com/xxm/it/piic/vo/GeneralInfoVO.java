package com.xxm.it.piic.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(value=XmlAccessType.FIELD) //访问类型改为字段
public class GeneralInfoVO {
	   @XmlTransient
	   private String generalInfoId;
	   @XmlElement(name="PlateformCode")
	   private String plateformCode; //平台项目标识，网销系统提供
	   @XmlElement(name="UUID")
	   private String uuid;
	   @XmlElement(name="Md5Value")
	   private String md5Value;
   
	public String getGeneralInfoId() {
		return generalInfoId;
	}
	public void setGeneralInfoId(String generalInfoId) {
		this.generalInfoId = generalInfoId;
	}

	public String getPlateformCode() {
		return plateformCode;
	}
	public void setPlateformCode(String plateformCode) {
		this.plateformCode = plateformCode;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMd5Value() {
		return md5Value;
	}
	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

   
}
