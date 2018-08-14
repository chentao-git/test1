package com.xxm.it.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xxm.it.piic.service.ITransferPICCService;
import com.xxm.it.piic.vo.MortgageinfoVO;

public class Test2 {


	public static void main(String[] args) {
		// 加载spring的配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-core.xml");
		ITransferPICCService transferPICCService = (ITransferPICCService) context.getBean("transferPICCServiceImpl");
		//银行预审
//		PrejudicationVO prejudicationVO = new PrejudicationVO();
//		prejudicationVO.setApplyInfoId("15");
//		transferPICCService.bankpretrial(prejudicationVO);
//		投保
//		ApplyVO applyVO = new ApplyVO();
//		applyVO.setApplyInfoId("15");
//		transferPICCService.insure(applyVO);
		
		
		//签约状态查询
//		SignInfoVO signInfoVO = new SignInfoVO();
//		signInfoVO.setSignInfoId("1");
//		transferPICCService.contractStatu(signInfoVO);
		//请求放款
//		LoanVO loanVO = new LoanVO();
//		loanVO.setLoanInfoId("4");
//		transferPICCService.transferLoan(loanVO);
		
		//车辆抵押信息
		MortgageinfoVO mortgageinfoVO = new MortgageinfoVO();
		mortgageinfoVO.setMortgageInfoId("1");
//		transferPICCService.vehicleMortgageInfo(mortgageinfoVO);
	}
}
