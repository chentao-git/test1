package com.xxm.it.business.service;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ClearingVO;

import net.sf.json.JSONObject;
@Path(value = "/clearingService")
public interface IClearingService {
	/**
	 * 
	 * 结清列表
	 * 
	 * @param ClearingVO
	 * @return
	 */
	@POST
	@Path("/findClearingList")
	public JSONObject findClearingList(ClearingVO clearingVO);
	/**
	 * 添加结清数据
	 * 
	 * @param ClearingVO
	 * @return
	 */
	@POST
	@Path("/addClearing")
	public Map<String, Object> addClearing(ClearingVO clearingVO);
}
