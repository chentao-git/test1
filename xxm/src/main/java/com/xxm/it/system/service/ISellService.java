package com.xxm.it.system.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.UserSellVO;

import net.sf.json.JSONObject;

@Path(value="sellService")
public interface ISellService {

	/**
	 * 
	 * 汽车经销商列表查询
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/findSellList")
	public JSONObject findSellList(UserSellVO sellVo);
	
	/**
	 * 
	 * 汽车经销商信息保存
	 * 
	 * @param roleVO
	 * @return
	 */
	@POST
	@Path("/saveSell")
	public Map<String, Object> addSell(UserSellVO sellVo);

	/**
	 * 
	 * 根据ID查询汽车经销商信息
	 * 
	 * @param roleVO
	 * @return UserSellVO
	 */
	@POST
	@Path("/querySellIdInfo")
	public UserSellVO querySellId(UserSellVO sellVo);
	
	/**
	 * 
	 * 修改汽车经销商信息保存
	 * 
	 * @param roleVO
	 * @return UserSellVO
	 */
	@POST
	@Path("/updateSellInfo")
	public Map<String, Object> updateSellInfo(UserSellVO sellVo);
	
	/**
	 * 
	 * 删除汽车经销商信息
	 * 
	 * @param roleVO
	 * @return UserSellVO
	 */
	
	@POST
	@Path("/deleteInfo")
	public Map<String, Object> deleteSellInfo(UserSellVO sellVo);
	
	
}
