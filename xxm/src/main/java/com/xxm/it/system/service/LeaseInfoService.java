package com.xxm.it.system.service;


import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.BaseLeaseInfoVO;

import net.sf.json.JSONObject;

@Path(value = "/leaseInfoService")
public interface LeaseInfoService {
	/**
	 * 查询凭租物信息列表
	 * @param testUserVO
	 * @return
	 */
	@POST
	@Path("/findLeaseInfoList")
	public JSONObject findLeaseInfoList(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 添加凭租物
	 * @param leaseInfoVO
	 * @return
	 */
	@POST
	@Path("/addLeaseInfo")
	public Map<String, Object> addLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 根据id去查询详情信息
	 * @param leaseInfoVO
	 * @return
	 */
	@POST
	@Path("/findLeaseInfoVO")
	public BaseLeaseInfoVO findLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 修改凭租物
	 * @param leaseInfoVO
	 * @return
	 */
	@POST
	@Path("/updateLeaseInfoVO")
	public Map<String, Object> updateLeaseInfoVO(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 根据车产类型的值去拿name
	 * @param leaseInfoVO
	 * @return
	 */
	@POST
	@Path("/findcarTypeName")
	public BaseLeaseInfoVO findcarTypeName(BaseLeaseInfoVO leaseInfoVO);
	/**
	 * 删除凭租物（仅仅修改status并不去删除数据）
	 * @param leaseInfoVO
	 * @return
	 */
	@POST
	@Path("/deleteLeaseInfo")
	public Map<String, Object> deleteLeaseInfo(BaseLeaseInfoVO leaseInfoVO);
}
