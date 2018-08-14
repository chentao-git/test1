package com.xxm.it.system.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xxm.it.system.vo.AffiliatedVO;
import net.sf.json.JSONObject;

@Path(value = "/affiliatedService")
public interface AffiliatedService {
	/**
	 * 查询挂靠公司列表
	 * @param testUserVO
	 * @return
	 */
	@POST
	@Path("/findAffiliatedList")
	public JSONObject findCompanyList(AffiliatedVO affiliatedVO);
	/**
	 * 根据id去查询信息
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/findAffiliated")
	public AffiliatedVO findAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 添加挂靠公司
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/addAffiliated")
	public Map<String, Object> addAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 修改挂靠公司信息
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/updateAffiliated")
	public Map<String, Object> updateAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 删除挂靠公司 （仅仅修改status值并不删除数据）
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/deleteAffiliated")
	public Map<String, Object> deleteAffiliated(AffiliatedVO affiliatedVO);
	/**
	 * 根据id 操作公司id 分类id 去拿 操作公司名称 以及分类名称
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/findInfo")
	public AffiliatedVO findInfo(AffiliatedVO affiliatedVO);
	
	@POST
	@Path("/findByoperationCompany")
	public AffiliatedVO findByoperationCompany(AffiliatedVO affiliatedVO);
	
	/**
	 * 查询挂靠商基本信息（list）
	 * @param affiliatedVO
	 * @return
	 */
	@POST
	@Path("/findAffiliateds")
	public List<AffiliatedVO> findAffiliateds(AffiliatedVO affiliatedVO);
}
