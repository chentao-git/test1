package com.xxm.it.piic.service.impl;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxm.it.activiti_piic.service.IPIICApplyService;
import com.xxm.it.business.dao.IApplyDao;
import com.xxm.it.business.service.IApplyService;
import com.xxm.it.business.vo.ApplyVO;
import com.xxm.it.piic.dao.ITransferPICCDao;
import com.xxm.it.piic.handler.PICCServiceHandler;
import com.xxm.it.piic.marshal.GetEncoding;
import com.xxm.it.piic.marshal.JaxbReadXml;
import com.xxm.it.piic.marshal.JaxbWriteXml;
import com.xxm.it.piic.md5.MD5Tool;
import com.xxm.it.piic.service.ITransferPICCService;
import com.xxm.it.piic.vo.ApplicantVO;
import com.xxm.it.piic.vo.AuditReturnInfo;
import com.xxm.it.piic.vo.Auditinfo;
import com.xxm.it.piic.vo.GeneralInfoReturnVO;
import com.xxm.it.piic.vo.GeneralInfoVO;
import com.xxm.it.piic.vo.InsureApplyInfo;
import com.xxm.it.piic.vo.InsureBeneficiary;
import com.xxm.it.piic.vo.InsureCoin;
import com.xxm.it.piic.vo.InsurePolicyInfo;
import com.xxm.it.piic.vo.InsureReturnInfo;
import com.xxm.it.piic.vo.InsureTheInsuredInfo;
import com.xxm.it.piic.vo.InsuredEngage;
import com.xxm.it.piic.vo.InsuredPlan;
import com.xxm.it.piic.vo.InsuredReturn;
import com.xxm.it.piic.vo.InsuredScheme;
import com.xxm.it.piic.vo.LoanInfoInformVO;
import com.xxm.it.piic.vo.LoanInfoReturnInformVO;
import com.xxm.it.piic.vo.LoanReturnInfoVO;
import com.xxm.it.piic.vo.LoanVO;
import com.xxm.it.piic.vo.MortgageReturnInfoVO;
import com.xxm.it.piic.vo.MortgageinfoVO;
import com.xxm.it.piic.vo.PolicyInfos;
import com.xxm.it.piic.vo.PolicyReturnInfoVO;
import com.xxm.it.piic.vo.PolicyinfoReturnVO;
import com.xxm.it.piic.vo.PrejudicInfo;
import com.xxm.it.piic.vo.PrejudicReturnInfo;
import com.xxm.it.piic.vo.PrejudicationVO;
import com.xxm.it.piic.vo.PushPolicyInfoVO;
import com.xxm.it.piic.vo.RejudicationReturnVO;
import com.xxm.it.piic.vo.SignInfoVO;
import com.xxm.it.piic.vo.SignReturnInfoVO;
import com.xxm.it.system.service.IAttachmentService;
import com.xxm.it.system.service.IUserService;
import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmConstant.BaseDataConstant;
import com.xxm.it.system.util.XxmConstant.MSConstant;
import com.xxm.it.system.util.XxmUtils;
import com.xxm.it.system.vo.AttachmentTextVO;
import com.xxm.it.system.vo.UserVO;

@Component
@Transactional
public class TransferPICCServiceImpl implements ITransferPICCService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ITransferPICCDao transferPICCDao;
	@Resource
	private IUserService userService;
	@Resource
	private IPIICApplyService piicApplyService;
	@Autowired
	private IApplyDao applyDao;
	@Autowired
	private IAttachmentService attachmentService;
	@Resource
	private IApplyService applyService;

	/**
	 * 放款
	 * @return 
	 */
	public LoanReturnInfoVO transferLoan(LoanVO loanVO) throws Exception {
		// 创建url资源
		String xml = null;
		String urls = XxmUtils.getBaseDataExtendField1("SERVICE_URL_CONFIG", "transferLoan");
		URL url = new URL(urls);
		LoanReturnInfoVO info = new LoanReturnInfoVO();
		try {
			LoanVO loan = transferPICCDao.findLoan(loanVO);
			// 附件
			AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
			attachmentTextVO.setAttachmentItemID(loan.getApplyInfoId());
			attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
			attachmentTextVO.setExtendField1(MSConstant.APPLYINFO_LOAN);
			attachmentTextVO.setAttachmentStatus("0");
			// 附件名称
			List<AttachmentTextVO> AttachmentTextVOList = attachmentService.findAttachments(attachmentTextVO);
			String fileKeyList = "";
			int s = AttachmentTextVOList.size() - 1;
			// 将附件名称串接起来
			for (int j = 0; j < AttachmentTextVOList.size(); j++) {
				AttachmentTextVO attachmentText = AttachmentTextVOList.get(j);
				String str = attachmentText.getAttachmentSysName();
				String strName = str.substring(0, str.indexOf("."));
				fileKeyList += strName;
				if (j != s) {
					fileKeyList += ",";
				}
			}
			loan.setFilekeyName(fileKeyList);
			//实体类转化为xml
			Marshaller marshaller = JaxbWriteXml.getMarshaller(LoanVO.class);
			StringWriter writer = new StringWriter();
			marshaller.marshal(loan, writer);
			xml = writer.toString();
			logger.debug("请求放款xml："+xml);
				
			//连接url
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			logger.debug("请求放款响应xml："+response);
			// 将返回的xml转为实体类
			info = JaxbReadXml.xml2Object(response, LoanReturnInfoVO.class);
			// 组装数据
			info.setRequestId(loanVO.getLoanInfoId());
			UserVO userVO = userService.findLoginUser();
			info.setCreateBy(userVO.getUserId());
			info.setLastUpdateBy(userVO.getUserId());

			// 插入响应表
			int loanReturnInfoIdNum = transferPICCDao.addLoanReturnInfo(info);
			logger.debug("放款响应信息插入数据库成功：" + loanReturnInfoIdNum+"条");
		} catch (JAXBException e) {
			logger.error("解析实体对象为xml错误：", e);
			throw new JAXBException(e);
		} catch(Exception e){
			throw new Exception(e);
		}
		return info;
	}

	/**
	 * 银行预审申请
	 */
	public RejudicationReturnVO bankpretrial(PrejudicationVO prejudicationVO) throws Exception {
		String xml = null;
		String urls = XxmUtils.getBaseDataExtendField1("SERVICE_URL_CONFIG", "bankapplyinterface");
		URL url = new URL(urls);
		RejudicationReturnVO rejudicationReturn = new RejudicationReturnVO();
		try {
			PrejudicationVO prejudication = transferPICCDao.findPrejudication(prejudicationVO);
			String date = XxmUtils.formatDate(prejudication.getAppliBirth(),
					XxmConstant.BaseDataConstant.FORMATE_DATA_TIME2, XxmConstant.BaseDataConstant.FORMATE_DATA2);
			prejudication.setAppliBirth(date);
			GeneralInfoVO generalInfo = transferPICCDao.findGeneralInfo();
			// 查询uuid 以及拼接MD5校验
			ApplyVO applyVO = new ApplyVO();
			applyVO.setApplyInfoId(prejudicationVO.getApplyInfoId());

			// 拼接uuid 然后加密
			ApplyVO applyInfo = applyDao.findApplyInfo(applyVO);
			String md5CertType = applyInfo.getCustomerVO().getCertType();

			long md5FrontLong = System.currentTimeMillis();
			String md5Front = String.valueOf(md5FrontLong);
			generalInfo.setUuid(md5Front);
			md5Front += md5CertType + generalInfo.getMd5Value();
			// 加密后
			String md5 = MD5Tool.string2MD5(md5Front);
			generalInfo.setMd5Value(md5);

			PrejudicInfo prejudicInfo = new PrejudicInfo();
			// 报文体
			prejudicInfo.setPrejudicationVO(prejudication);
			// 报文头
			prejudicInfo.setGeneralInfoVO(generalInfo);
			Marshaller marshaller = JaxbWriteXml.getMarshaller(PrejudicInfo.class);
			StringWriter writer = new StringWriter();
			marshaller.marshal(prejudicInfo, writer);
			xml = writer.toString();
			logger.debug("预审xml：" + xml);
			// 连接url
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			logger.debug("预审响应xml：" + response);
			// 将返回的xml转为实体类
			PrejudicReturnInfo info = JaxbReadXml.xml2Object(response, PrejudicReturnInfo.class);
			rejudicationReturn = info.getRejudicationReturnVO();
			rejudicationReturn.setRequestId(prejudicationVO.getApplyInfoId());
			UserVO userVO = userService.findLoginUser();
			rejudicationReturn.setCreateBy(userVO.getUserId());
			rejudicationReturn.setLastUpdateBy(userVO.getUserId());
			// 插入响应表
			transferPICCDao.addRejudicationReturn(rejudicationReturn);
			logger.debug("银行预审响应信息插入数据库成功：" + rejudicationReturn.getPrejudicationReturnId());
		} catch (JAXBException e) {
			logger.error("解析实体对象为xml错误：", e);
			throw new JAXBException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return rejudicationReturn;
	}

	/**
	 * 签约状态
	 * @return 
	 */
	@Override
	public SignReturnInfoVO contractStatu(SignInfoVO signInfoVO) throws Exception{
		String xml = null;
		String urls = XxmUtils.getBaseDataExtendField1("SERVICE_URL_CONFIG", "contractStatu");
		URL url = new URL(urls);
		SignReturnInfoVO info = new SignReturnInfoVO();
		try {
			SignInfoVO signInfo = transferPICCDao.findSignInfo(signInfoVO);
			Marshaller marshaller = JaxbWriteXml.getMarshaller(SignInfoVO.class);
			StringWriter writer = new StringWriter();
			marshaller.marshal(signInfo, writer);
			xml = writer.toString();
			logger.debug("签约查询xml："+ xml);
			//连接url
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			logger.debug("签约查询响应xml："+ response);
			// 将返回的xml转为实体类
			info = JaxbReadXml.xml2Object(response, SignReturnInfoVO.class);
			// 组装数据
			info.setRequestId(signInfoVO.getSignInfoId());
			UserVO userVO = userService.findLoginUser();
			info.setCreateBy(userVO.getUserId());
			info.setLastUpdateBy(userVO.getUserId());
			// 可放款 改变申请信息状态
			if (info.getResultCode().equals("00")) {
				if (info.getApplState().equals("COMP")) {
					ApplyVO applyVO = new ApplyVO();
					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_SIGNINFO_SUCCEED);
					applyVO.setApplyInfoId(signInfoVO.getApplyInfoId());
					transferPICCDao.uadateApplyInfo(applyVO);
				}
			}
			// 插入响应表
			transferPICCDao.addSignReturnInfo(info);
		} catch (JAXBException e) {
			logger.error("解析实体对象为xml错误：", e);
			throw new JAXBException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return info;
	}

	/**
	 * 车辆抵押信息
	 * @return 
	 */
	@Override
	public MortgageReturnInfoVO vehicleMortgageInfo(MortgageinfoVO mortgageinfoVO) throws Exception{
		String xml = null;
		String urls = XxmUtils.getBaseDataExtendField1("SERVICE_URL_CONFIG", "vehicleMortgageInfo");
		URL url = new URL(urls);
		MortgageReturnInfoVO info = new MortgageReturnInfoVO();
		try {
			//查询出数据
			MortgageinfoVO mortgageinfo = transferPICCDao.findMortgageinfo(mortgageinfoVO);
			// 附件
			AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
			attachmentTextVO.setAttachmentItemID(mortgageinfo.getApplyInfoId());
			attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
			attachmentTextVO.setExtendField1(MSConstant.APPLYINFO_VEHICLEMORTGAGEINFO);
			attachmentTextVO.setAttachmentStatus("0");
			// 附件名称
			List<AttachmentTextVO> AttachmentTextVOList = attachmentService.findAttachments(attachmentTextVO);
			String fileKeyList = "";
			int s = AttachmentTextVOList.size() - 1;
			// 将附件名称串接起来
			for (int j = 0; j < AttachmentTextVOList.size(); j++) {
				AttachmentTextVO attachmentText = AttachmentTextVOList.get(j);
				String str = attachmentText.getAttachmentSysName();
				String strName = str.substring(0, str.indexOf("."));
				fileKeyList += strName;
				if (j != s) {
					fileKeyList += ",";
				}
			}
			mortgageinfo.setFilekeyName(fileKeyList);
			//实体类转化为xml
			Marshaller marshaller = JaxbWriteXml.getMarshaller(MortgageinfoVO.class);
			StringWriter writer = new StringWriter();
			marshaller.marshal(mortgageinfo, writer);
			xml = writer.toString();
			logger.debug("车辆抵押信息xml："+ xml);
			//连接url
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			logger.debug("车辆抵押信息响应xml："+ response);
			// 将返回的xml转为实体类
			info = JaxbReadXml.xml2Object(response, MortgageReturnInfoVO.class);
			// 组装数据
			info.setRequestId(mortgageinfoVO.getMortgageInfoId());
			UserVO userVO = userService.findLoginUser();
			info.setCreateBy(userVO.getUserId());
			info.setLastUpdateBy(userVO.getUserId());
			// 改变申请信息状态
			if (info.getResultCode().equals("00")) {
				ApplyVO applyVO = new ApplyVO();
				applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_MORTGAGE_SUCCEED);
				applyVO.setApplyInfoId(mortgageinfo.getApplyInfoId());
				transferPICCDao.uadateApplyInfo(applyVO);
			}
			// 插入响应表
			transferPICCDao.addMortgageReturnInfoVO(info);
			//调用抵押证明流程
			piicApplyService.mortgage(mortgageinfo.getApplyInfoId(), info);
		} catch (JAXBException e) {
			logger.error("解析实体对象为xml错误：", e);
			throw new JAXBException(e);
		} catch (Exception e) {
			throw new Exception(e); 
		}
		return info;
	}

	/**
	 * 新增影像附件
	 */
	@Override
	public void addFile() {
		String xml = "<?xml version='1.0' encoding='gb2312' standalone='yes'?>" + "<AddFileInfo>"
				+ "<ProposalNo></ProposalNo>" + "<FilekeyName></FilekeyName>" + "<AddTimes></AddTimes>"
				+ "</AddFileInfo>";
		try {
			URL url = new URL("http://partnertest.mypicc.com.cn/ecooperation/Uinsured/addFile.do");
			// System.out.println(httpPost(xml, url));
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			System.out.println(response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 投保
	 * 
	 * @return
	 */
	@Override
	public InsureReturnInfo insure(ApplyVO applyVO) throws Exception {
		String xml = null;
		String urls = XxmUtils.getBaseDataExtendField1("SERVICE_URL_CONFIG", "proposalForCMBC");
		URL url = new URL(urls);
		GeneralInfoVO generalInfo = new GeneralInfoVO();
		InsureReturnInfo info = new InsureReturnInfo();
		try {
			// 查询报文头信息
			generalInfo = transferPICCDao.findGeneralInfo();
			// 投保单归属机构信息
			List<InsureCoin> insureCoinList = transferPICCDao.findInsureCoin(applyVO);
			// 方案信息
			InsuredPlan insuredPlan = transferPICCDao.findInsuredPlan(applyVO);
			// 条款信息
			List<InsuredScheme> insuredSchemeList = transferPICCDao.findInsuredScheme(insuredPlan);
			// 特别约定信息
			List<InsuredEngage> insuredEngageList = transferPICCDao.findInsuredEngage(insuredPlan);
			insuredPlan.setInsuredSchemeList(insuredSchemeList);
			insuredPlan.setInsuredEngageList(insuredEngageList);
			// car基础信息
			InsurePolicyInfo carInsurePolicyInfo = transferPICCDao.findInsurePolicyInfo(applyVO);
			// 投保人基础信息
			InsurePolicyInfo customerInsurePolicyInfo = transferPICCDao.findCustomerInsurePolicyInfo(applyVO);
			// 贷款信息
			InsurePolicyInfo citemInsurePolicyInfo = transferPICCDao.findCitemInsurePolicyInfo(applyVO);
			// 投保人信息
			ApplicantVO applicantVO = transferPICCDao.findApplicantInsurePolicyInfo(applyVO);
			// 被保人信息
			List<InsureTheInsuredInfo> insureTheInsuredInfoList = transferPICCDao.findInsureTheInsuredInfoList(applyVO);
			for (int i = 0; i < insureTheInsuredInfoList.size(); i++) {
				InsureTheInsuredInfo insureTheInsuredInfo = insureTheInsuredInfoList.get(i);
				// 受益人信息
				List<InsureBeneficiary> insureBeneficiaryList = transferPICCDao
						.findInsureBeneficiaryList(insureTheInsuredInfo);
				insureTheInsuredInfoList.get(i).setInsureBeneficiary(insureBeneficiaryList);
			}

			// 存放保单总保险费 用于MD5加密校验
			String md5SumPremium = "";

			// 查询报文体信息
			List<InsurePolicyInfo> insurePolicy = transferPICCDao.findInsurePolicyList(applyVO);
			List<InsurePolicyInfo> insurePolicyList = new ArrayList<InsurePolicyInfo>();
			// 组装报文体
			for (int i = 0; i < insurePolicy.size(); i++) {
				// InsurePolicyInfo insurePolicyInfo = new InsurePolicyInfo();
				InsurePolicyInfo insurePolicyInfo = insurePolicy.get(i);
				// 附件
				AttachmentTextVO attachmentTextVO = new AttachmentTextVO();
				attachmentTextVO.setAttachmentItemID(applyVO.getApplyInfoId());
				attachmentTextVO.setAttachmentItem(BaseDataConstant.ATTACHMENT_ITEM_APPLY_INFO);
				attachmentTextVO.setExtendField1(MSConstant.APPLYINFO);
				attachmentTextVO.setAttachmentStatus("0");
				// 附件名称
				List<AttachmentTextVO> AttachmentTextVOList = attachmentService.findAttachments(attachmentTextVO);
				String fileKeyList = "";
				int s = AttachmentTextVOList.size() - 1;
				// 将附件名称串接起来
				for (int j = 0; j < AttachmentTextVOList.size(); j++) {
					AttachmentTextVO attachmentText = AttachmentTextVOList.get(j);
					String str = attachmentText.getAttachmentSysName();
					String strName = str.substring(0, str.indexOf("."));
					fileKeyList += strName;
					if (j != s) {
						fileKeyList += ",";
					}
				}
				insurePolicyInfo.setFileKeyList(fileKeyList);

				insurePolicyInfo.setInsureCoin(insureCoinList);
				insurePolicyInfo.setInsuredPlan(insuredPlan);
				insurePolicyInfo.setInsuredPlan(insuredPlan);
				insurePolicyInfo.setBaseLeaseInfo(carInsurePolicyInfo.getBaseLeaseInfo());
				insurePolicyInfo.setBaseCustomer(customerInsurePolicyInfo.getBaseCustomer());
				insurePolicyInfo.setSupplementBase(citemInsurePolicyInfo.getSupplementBase());
				insurePolicyInfo.setApplicantVO(applicantVO);
				insurePolicyInfo.setInsureTheInsuredInfo(insureTheInsuredInfoList);
				// 将保单总保险费组合起来用作MD5校验
				md5SumPremium += insurePolicyInfo.getSumPremium();

				insurePolicyList.add(insurePolicyInfo);
			}
			// 拼接uuid 然后加密
			long md5FrontLong = System.currentTimeMillis();
			String md5Front = String.valueOf(md5FrontLong);
			generalInfo.setUuid(md5Front);
			md5Front += md5SumPremium + generalInfo.getMd5Value();
			// 加密后
			String md5 = MD5Tool.string2MD5(md5Front);
			generalInfo.setMd5Value(md5);

			// 组装 报文头 和 报文体
			InsureApplyInfo insureApplyInfo = new InsureApplyInfo();
			insureApplyInfo.setInsurePolicyInfo(insurePolicyList);
			insureApplyInfo.setGeneralInfoVO(generalInfo);

			Marshaller marshaller;

			marshaller = JaxbWriteXml.getMarshaller(InsureApplyInfo.class);
			StringWriter writer = new StringWriter();
			marshaller.marshal(insureApplyInfo, writer);
			xml = writer.toString();
			System.out.println(GetEncoding.getEncoding(xml));
			System.out.println("-------------------------------编码--------------------------------------------");
			xml = new String(xml.getBytes(), "GBK");
			logger.debug("投保xml：" + xml);
			System.out.println(GetEncoding.getEncoding(xml));
			System.out.println("-------------------------------请求编码--------------------------------------------");
			//url请求
			String response = PICCServiceHandler.callCharacterInterface(url, xml);
			System.out.println(GetEncoding.getEncoding(response));
			System.out.println("-----------------------------------响应编码----------------------------------------");
			logger.debug("投保响应xml：" + response);
			// 将返回的xml转为实体类
			info = JaxbReadXml.xml2Object(response, InsureReturnInfo.class);
			GeneralInfoReturnVO generalReturnInfo = info.getGeneralInfoReturnVO();
			logger.debug("响应的状态码：" + generalReturnInfo.getErrorCode());
			if (generalReturnInfo.getErrorCode().equals("00")) {
				List<PolicyinfoReturnVO> PolicyinfoReturnList = info.getPolicyinfoReturnVO();
				UserVO userVO = userService.findLoginUser();
				for (int i = 0; i < PolicyinfoReturnList.size(); i++) {
					PolicyinfoReturnVO policyinfoReturnVO = PolicyinfoReturnList.get(i);
					// 组装数据
					policyinfoReturnVO.setApplyInfoId(applyVO.getApplyInfoId());

					policyinfoReturnVO.setCreateBy(userVO.getUserId());
					policyinfoReturnVO.setLastUpdateBy(userVO.getUserId());
					// 添加响应信息
					transferPICCDao.addPolicyinfoReturn(policyinfoReturnVO);

					List<InsuredReturn> insuredReturnList = policyinfoReturnVO.getInsuredReturn();
					for (int j = 0; j < insuredReturnList.size(); j++) {
						InsuredReturn insuredReturn = insuredReturnList.get(j);
						// 组装数据
						insuredReturn.setPolicyInfoReturnId(policyinfoReturnVO.getPolicyInfoReturnId());
						insuredReturn.setCreateBy(userVO.getUserId());
						insuredReturn.setLastUpdateBy(userVO.getUserId());
						// 添加被保人响应信息
						transferPICCDao.addInsuredReturn(insuredReturn);
					}
				}
			}
		} catch (JAXBException e) {
			logger.error("解析实体对象为xml错误：", e);
			throw new JAXBException(e);
		} catch (UnsupportedEncodingException e) {
			logger.error("转化编码发生错误：", e);
			throw new UnsupportedEncodingException();
		} catch(Exception e){
			throw new Exception(e);
		}
		return info;
	}

	// main
	public static void main(String[] args) {
		// new TransferPICCServiceImpl().vehicleMortgageInfo();
		// new TransferPICCServiceImpl().transferLoan();
	}

	/**
	 * 审核结果通知
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String auditInfo(String param) throws Exception {
		System.out.println("---------------------------------------------------");
		// 查看编码
		String encoding = GetEncoding.getEncoding(param);
		String xml = null;
		if (!param.equals("")) {
			logger.debug("审核结果通知请求数据：" + param + "编码//////////////////////////////////////////" + encoding);
			// 将传来的xml转化为对象
			Object auditinfo = JaxbReadXml.convertXmlStrToObject(Auditinfo.class, param);
			// Object 强转为实体类对象
			Auditinfo audit = (Auditinfo) auditinfo;
			audit.setStatus("0");
			int count = 0;
			try {
				// 给予默认创建修改人
				audit.setCreateBy(MSConstant.CREATEBY);
				audit.setLastUpdateBy(MSConstant.LASTUPDATEBY);
				count = transferPICCDao.addAuditInfo(audit);
				ApplyVO applyVO = new ApplyVO();
				// 根据请求过来审核结果类型去查询相应的申请id 然后根据申请id去修改申请状态
				// 预审
				if (audit.getAuditTpye().equals("01")) {
					// 查询申请id
					PrejudicationVO prejudication = transferPICCDao.findBankPretrial(audit);
					// 审核通过
					if (audit.getUnderwriteResult().equals("00")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_PRETRIAL_PASS);
						// 审核不通过
					} else if (audit.getUnderwriteResult().equals("01")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_PRETRIAL_FAIL);
						// 审核待查
					} else if (audit.getUnderwriteResult().equals("02")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_PRETRIAL_UNDETERMINED);
					}
					// 跟新
					applyVO.setApplyInfoId(prejudication.getApplyInfoId());
					// 更新申请状态
					transferPICCDao.uadateApplyInfo(applyVO);
					// 银行预审流程
					piicApplyService.bankAudit(prejudication.getApplyInfoId(), audit);
					// 初审
				} else if (audit.getAuditTpye().equals("02")) {
					PolicyinfoReturnVO policyinfoReturn = transferPICCDao.findApplyInfoId(audit);
					// 审核通过
					if (audit.getUnderwriteResult().equals("00")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FIRST_TRIAL_PASS);
						// 审核不通过
					} else if (audit.getUnderwriteResult().equals("01")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FIRST_TRIAL_FAIL);
						// 审核待查
					} else if (audit.getUnderwriteResult().equals("02")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FIRST_TRIAL_UNDETERMINED);
					}
					// 跟新申请状态
					applyVO.setApplyInfoId(policyinfoReturn.getApplyInfoId());
					transferPICCDao.uadateApplyInfo(applyVO);
					// 银行初审流程
					piicApplyService.startAuditResult(policyinfoReturn.getApplyInfoId(), audit);
				} else if (audit.getAuditTpye().equals("03")) {
					PolicyinfoReturnVO policyinfoReturn = transferPICCDao.findApplyInfoId(audit);
					// 审核通过
					if (audit.getUnderwriteResult().equals("00")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FINAL_JUDGMENT_PASS);
						// 审核不通过
					} else if (audit.getUnderwriteResult().equals("01")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FINAL_JUDGMENT_FAIL);
						// 审核待查
					} else if (audit.getUnderwriteResult().equals("02")) {
						applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_FINAL_JUDGMENT_UNDETERMINED);
					}
					// 跟新申请状态
					applyVO.setApplyInfoId(policyinfoReturn.getApplyInfoId());
					transferPICCDao.uadateApplyInfo(applyVO);
					// 添加签约请求数据
					applyService.addSignInfo(audit);
					// 银行终审流程
					piicApplyService.endAuditResult(policyinfoReturn.getApplyInfoId(), audit);
				}
			if (count != 0) {
				int id = 2;
				// 查出要返回的数据
				AuditReturnInfo auditReturn = transferPICCDao.findAuditReturnInfo(id);
				auditReturn.setPrejudicNo(audit.getPrejudicNo());
				auditReturn.setProposalNo(audit.getProposalNo());
				// 将数据转为xml 返回出去
				StringWriter writer = null;
				//对象转xml
				Marshaller marshaller = JaxbWriteXml.getMarshaller(AuditReturnInfo.class);
				writer = new StringWriter();
				marshaller.marshal(auditReturn, writer);
				xml = writer.toString();
				xml = new String(xml.getBytes(), "GBK");
			}
			}  catch (Exception e) {
				int id = 1;
				// 查出要返回的数据
				AuditReturnInfo auditReturn = transferPICCDao.findAuditReturnInfo(id);
				Marshaller marshaller = JaxbWriteXml.getMarshaller(AuditReturnInfo.class);
				// 将数据转为xml 返回出去
				StringWriter writer = new StringWriter();
				marshaller.marshal(auditReturn, writer);
				xml = writer.toString();
				throw new Exception(e);
			}
		}
		String encod = GetEncoding.getEncoding(xml);
		logger.debug("审核结果通知返回数据：" + xml + "编码//////////////////////////////////////////" + encod);
		return xml;
	}

	/**
	 * 放款通知
	 * @throws Exception 
	 */
	@Override
	public String loaninfo(String param) throws Exception {
		String xml = null;
		if (!param.equals("")) {
			// JaxbReadXml jaxbReadXml = new JaxbReadXml();
			System.out.println(param);
			logger.debug("放款通知请求数据：" + param);
			// 将传来的xml转化为对象
			Object auditinfo = JaxbReadXml.convertXmlStrToObject(LoanInfoInformVO.class, param);
			// Object 强转为实体类对象
			LoanInfoInformVO audit = (LoanInfoInformVO) auditinfo;
			audit.setStatus("0");
			int auditInfoId = 0;
			String applyInfoId = "";
			try {
				// 给予默认创建修改人
				audit.setCreateBy(MSConstant.CREATEBY);
				audit.setLastUpdateBy(MSConstant.LASTUPDATEBY);
				// 放款成功
				if (audit.getLoanResult().equals("01")) {
					ApplyVO applyVO = new ApplyVO();
					Auditinfo auditinfos = new Auditinfo();
					auditinfos.setProposalNo(audit.getProposalNo());
					// 查询申请id
					PolicyinfoReturnVO policyinfoReturn = transferPICCDao.findApplyInfoId(auditinfos);
					applyInfoId = policyinfoReturn.getApplyInfoId();
					// 跟新申请状态
					applyVO.setApplyInfoId(applyInfoId);
					applyVO.setApplyInfoStatus(BaseDataConstant.APPLY_STATUS_LOAN_SUCCEED);
					transferPICCDao.uadateApplyInfo(applyVO);
				}
				auditInfoId = transferPICCDao.addLoanInfoInform(audit);
				if (auditInfoId != 0) {
					int id = 2;
					// 查出要返回的数据
					LoanInfoReturnInformVO auditReturn = transferPICCDao.findLoanInfoReturnInform(id);
					auditReturn.setProposalno(audit.getProposalNo());
					// 将数据转为xml 返回出去
					StringWriter writer = null;
				
					Marshaller marshaller = JaxbWriteXml.getMarshaller(LoanInfoReturnInformVO.class);
					writer = new StringWriter();
					marshaller.marshal(auditReturn, writer);
					xml = writer.toString();
					xml = new String(xml.getBytes(), "gb2312");
				}
				// 放款通知流程步骤
				piicApplyService.loanNotice(applyInfoId, audit);
			}  catch (Exception e) {
				int id = 1;
				// 查出要返回的数据
				LoanInfoReturnInformVO auditReturn = transferPICCDao.findLoanInfoReturnInform(id);
				Marshaller marshaller = JaxbWriteXml.getMarshaller(LoanInfoReturnInformVO.class);
				// 将数据转为xml 返回出去
				StringWriter writer = new StringWriter();
				marshaller.marshal(auditReturn, writer);
				xml = writer.toString();
				throw new Exception(e);
			} 
		}
		logger.debug("放款通知返回数据：" + xml);
		return xml;
	}

	/**
	 * 推送保单号
	 */
	@Override
	public String applyInfo(String param) {
		String xml = null;
		if (!param.equals("")) {
			System.out.println(param);
			logger.debug("推送保单号请求数据：" + param);
			try {
				PolicyInfos info = JaxbReadXml.xml2Object(param, PolicyInfos.class);
				GeneralInfoVO generalInfo = info.getGeneralInfoVO();
				int num = transferPICCDao.findGeneralInfoNum(generalInfo);
				//存放申请id
				PolicyinfoReturnVO policyinfoReturn = new PolicyinfoReturnVO();
				List<PushPolicyInfoVO> infoList = info.getPushPolicyInfoList();
				//对投保单号进行判空
				if(infoList.get(0).getProposalNo()!= null && infoList.get(0).getProposalNo()!=""){
					if (num != 0) {
						
						PushPolicyInfoVO pushPolicyInfoVO = new PushPolicyInfoVO();
						int pushPolicyInfoId = 0;
						for (int i = 0; i < infoList.size(); i++) {
							pushPolicyInfoVO = infoList.get(i);
							pushPolicyInfoVO.setStatus("0");
							pushPolicyInfoVO.setCreateBy(MSConstant.CREATEBY);
							pushPolicyInfoVO.setLastUpdateBy(MSConstant.LASTUPDATEBY);
							pushPolicyInfoId = transferPICCDao.addPushPolicyInfo(pushPolicyInfoVO);
							//根据投保单号查询申请id
							Auditinfo audit = new Auditinfo();
							audit.setProposalNo(pushPolicyInfoVO.getProposalNo());
							policyinfoReturn = transferPICCDao.findApplyInfoId(audit);
						}
						if (pushPolicyInfoId != 0) {
							int id = 2;
							// 查出要返回的数据
							PolicyReturnInfoVO policyReturnInfo = transferPICCDao.findPolicyReturnInfo(id);
							policyReturnInfo.setProposalno(infoList.get(0).getProposalNo());
							// 将数据转为xml 返回出去
							StringWriter writer = null;
							try {
								Marshaller marshaller = JaxbWriteXml.getMarshaller(PolicyReturnInfoVO.class);
								writer = new StringWriter();
								marshaller.marshal(policyReturnInfo, writer);
							} catch (JAXBException e) {
								e.printStackTrace();
							}
							xml = writer.toString();
						}
					} else {
						try {
							int id = 1;
							// 查出要返回的数据
							PolicyReturnInfoVO policyReturnInfo = transferPICCDao.findPolicyReturnInfo(id);
							policyReturnInfo.setResultMessage("平台标识出错！！！");
							logger.debug("平台标识出错！！！"+info.getGeneralInfoVO().getPlateformCode());
							Marshaller marshaller = JaxbWriteXml.getMarshaller(PolicyReturnInfoVO.class);
							// 将数据转为xml 返回出去
							StringWriter writer = new StringWriter();
							marshaller.marshal(policyReturnInfo, writer);
							xml = writer.toString();
						} catch (JAXBException e1) {
							e1.printStackTrace();
						}
					}
				}else{
					try {
						int id = 1;
						// 查出要返回的数据
						PolicyReturnInfoVO policyReturnInfo = transferPICCDao.findPolicyReturnInfo(id);
						policyReturnInfo.setResultMessage("投保单号为空！！！");
						logger.debug("----------------------投保单号为空！！！");
						Marshaller marshaller = JaxbWriteXml.getMarshaller(PolicyReturnInfoVO.class);
						// 将数据转为xml 返回出去
						StringWriter writer = new StringWriter();
						marshaller.marshal(policyReturnInfo, writer);
						xml = writer.toString();
					} catch (JAXBException e1) {
						e1.printStackTrace();
					}
				}
				//接收保单号流程
				piicApplyService.receivePolicyNumber(policyinfoReturn.getApplyInfoId(), info);
			} catch (Exception e) {
				try {
					int id = 1;
					// 查出要返回的数据
					PolicyReturnInfoVO policyReturnInfo = transferPICCDao.findPolicyReturnInfo(id);
					Marshaller marshaller = JaxbWriteXml.getMarshaller(PolicyReturnInfoVO.class);
					// 将数据转为xml 返回出去
					StringWriter writer = new StringWriter();
					marshaller.marshal(policyReturnInfo, writer);
					xml = writer.toString();
				} catch (JAXBException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		logger.debug("推送保单号返回数据：" + xml);
		return xml;
	}

}
