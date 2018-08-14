package com.xxm.it.business.vo;

import com.xxm.it.system.vo.CommonVO;

public class BusinessOpportunityVO extends CommonVO{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String businessOpportunityId; //商机id
		private String focusOnProductVL; //关注产品id
		private String focusOnProductName; //关注产品名称
		private String loanAmount; //贷款额
		private String interestRate; //利率
		private String expectedTransactionTime; //预计成交时间
		private String actualTransactionTime;  //实际成交时间
		private String remark; //备注
		private String baseCustomerId; //客户id
		private String baseCustomerName; //客户名称
		private String status; //状态
		private String createBy;
		private String createDate;
		private String lastUpdateBy;
		private String lastUpdateByName;
		private String lastUpdateDate;
		private String[] ids = new String[] {};
		
		
		public String getLastUpdateByName() {
			return lastUpdateByName;
		}
		public void setLastUpdateByName(String lastUpdateByName) {
			this.lastUpdateByName = lastUpdateByName;
		}
		public String getBusinessOpportunityId() {
			return businessOpportunityId;
		}
		public void setBusinessOpportunityId(String businessOpportunityId) {
			this.businessOpportunityId = businessOpportunityId;
		}
		public String getFocusOnProductVL() {
			return focusOnProductVL;
		}
		public void setFocusOnProductVL(String focusOnProductVL) {
			this.focusOnProductVL = focusOnProductVL;
		}
		public String getFocusOnProductName() {
			return focusOnProductName;
		}
		public void setFocusOnProductName(String focusOnProductName) {
			this.focusOnProductName = focusOnProductName;
		}
		public String getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}
		public String getInterestRate() {
			return interestRate;
		}
		public void setInterestRate(String interestRate) {
			this.interestRate = interestRate;
		}
		public String getExpectedTransactionTime() {
			return expectedTransactionTime;
		}
		public void setExpectedTransactionTime(String expectedTransactionTime) {
			this.expectedTransactionTime = expectedTransactionTime;
		}
		public String getActualTransactionTime() {
			return actualTransactionTime;
		}
		public void setActualTransactionTime(String actualTransactionTime) {
			this.actualTransactionTime = actualTransactionTime;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getBaseCustomerId() {
			return baseCustomerId;
		}
		public void setBaseCustomerId(String baseCustomerId) {
			this.baseCustomerId = baseCustomerId;
		}
		public String getBaseCustomerName() {
			return baseCustomerName;
		}
		public void setBaseCustomerName(String baseCustomerName) {
			this.baseCustomerName = baseCustomerName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreateBy() {
			return createBy;
		}
		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		public String getLastUpdateBy() {
			return lastUpdateBy;
		}
		public void setLastUpdateBy(String lastUpdateBy) {
			this.lastUpdateBy = lastUpdateBy;
		}
		public String getLastUpdateDate() {
			return lastUpdateDate;
		}
		public void setLastUpdateDate(String lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
		public String[] getIds() {
			return ids;
		}
		public void setIds(String[] ids) {
			this.ids = ids;
		}
	
		
		
}
