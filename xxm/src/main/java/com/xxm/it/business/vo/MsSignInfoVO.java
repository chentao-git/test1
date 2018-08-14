package com.xxm.it.business.vo;

import java.util.ArrayList;
import java.util.List;

import com.xxm.it.piic.vo.SignReturnInfoVO;
import com.xxm.it.system.vo.CommonVO;

/**
 * 签约状态查询对象
 * 
 * @author Administrator
 *
 */
public class MsSignInfoVO extends CommonVO{
	private static final long serialVersionUID =1L;
	private String signInfoId;//
	private String proposalNo;//投保单号
	private String applyNo;//申请书编号
	private String applyInfoId;//申请表ID
	private String applyInfoStatus;//申请信息状态
	private SignReturnInfoVO signReturnInfoVO=new SignReturnInfoVO();//签约响应对象
	//签约响应对象List
	private List<SignReturnInfoVO> signReturnInfoList=new ArrayList<SignReturnInfoVO>();
	public String getSignInfoId() {
		return signInfoId;
	}
	public void setSignInfoId(String signInfoId) {
		this.signInfoId = signInfoId;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SignReturnInfoVO getSignReturnInfoVO() {
		return signReturnInfoVO;
	}
	public void setSignReturnInfoVO(SignReturnInfoVO signReturnInfoVO) {
		this.signReturnInfoVO = signReturnInfoVO;
	}
	public List<SignReturnInfoVO> getSignReturnInfoList() {
		return signReturnInfoList;
	}
	public void setSignReturnInfoList(List<SignReturnInfoVO> signReturnInfoList) {
		this.signReturnInfoList = signReturnInfoList;
	}
	public String getApplyInfoId() {
		return applyInfoId;
	}
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	public String getApplyInfoStatus() {
		return applyInfoStatus;
	}
	public void setApplyInfoStatus(String applyInfoStatus) {
		this.applyInfoStatus = applyInfoStatus;
	}

	

}
