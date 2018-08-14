package com.xxm.it.business.service;



import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.business.vo.ProduceVO;

import net.sf.json.JSONObject;

@Path(value = "/productService")
public interface IProduceService {
	/**
	 * 查询产品信息列表
	 * @param produceVO
	 * @return
	 */
	@POST
	@Path(value="/findProductList")
    public JSONObject findProductList(ProduceVO produceVO);
	/**
	 * 添加产品信息
	 * @param produceVO
	 * @return
	 */
	@POST
	@Path(value="/addProduce")
	public Map<String, Object> addProduce(ProduceVO produceVO);
	/**
	 * 根据id查询信息
	 * @param produceVO
	 * @return
	 */
	@POST
	@Path(value="/findProduce")
	public ProduceVO findProduce(ProduceVO produceVO);
	/**
	 * 上传合同配置
	 * @param produceVO
	 * @return
	 */
	@POST
	@Path(value="/uploadContract")
	public ProduceVO uploadContract(ProduceVO produceVO);
}
