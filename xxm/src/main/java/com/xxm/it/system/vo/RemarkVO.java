package com.xxm.it.system.vo;
/***
 * 诉讼跟踪备注
 * @author Administrator
 *
 */
public class RemarkVO extends CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String followId; //根据ID
	private String filed; //诉讼申请ID
	private String content; //跟踪内容
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
