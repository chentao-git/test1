$(function () {
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
	//婚姻状况
//	CommonUtils.findBaseDataRadio("#marriageStatus-div","MARITAL_STATUS",1,"marriageStatus");
	CommonUtils.findBaseDataOption("#marriageStatus","MARITAL_STATUS",1);
	//是否农行
	CommonUtils.findBaseDataRadio("#isABC-div","IS_ABC",1,"isABC");
	//证件类型
	CommonUtils.findBaseDataOption("#certType","BAQSNG_PAPER_TYPE",1);
	//还款途径
	CommonUtils.findBaseDataOption("#paymentApproachVL","PAYMENT_APPROACH",1);
	//还款方式
	CommonUtils.findBaseDataOption("#paymentMethodVL","PAYMENT_METHOD",1);
	//自动扣款还款
	CommonUtils.findBaseDataOption("#isAutoPay","AUTO_PAY",1);
	//利率计算方式
	CommonUtils.findBaseDataOption("#interestCalculatedVL","INTEREST_CALCULATED",1);
	//还款手续费承担方
	CommonUtils.findBaseDataOption("#payBearerVL","PAY_BEARER",1);
	//还款日期类型
	CommonUtils.findBaseDataOption("#paymentDateTypeVL","PAYMENT_DATE_TYP",1);
	//担保费计算方式
	CommonUtils.findBaseDataOption("#guaranteeCalculatedVL","GUARANTEE_CALCULATED",1);
	//允许提前还贷
	CommonUtils.findBaseDataOption("#prepaymentVL","PREPAYMENT",1);
	//罚息计算基础
	CommonUtils.findBaseDataOption("#defaultInterestVL","DEFAULT_INTEREST",1);
	//存量车
	CommonUtils.findBaseDataOption("#stockCarVl","STOCK_CAR",1);
	//最高学历
	CommonUtils.findBaseDataOption("#educationType","EDUCATION_TYPE",1);
	//最高学历
	CommonUtils.findBaseDataOption("#educationTypeVL","EDUCATION_TYPE",1);
	//扣款银行
	CommonUtils.findBaseDataOption("#openBankName","LOAN_BANK",1);
	//扣款对象分类
	CommonUtils.findBaseDataOption("#deductionsObjectType","BANK_FLAG",1);
	//预付租金比例
	CommonUtils.findBaseDataOption("#preRentRateVL","PRE_RENT_RATE",1);
	//方案
	CommonUtils.findBaseDataOption("#planVL","PLAN",1);
	//车产类型
	CommonUtils.findBaseDataOption("select[name='carTypeVL']","CAR_TYPE",1);
	//商业第三者责任险
	CommonUtils.findBaseDataOption("#thirdInsuranceVL","THIRD_INSURANCE",1);
	//主要险种(多选)
	CommonUtils.findBaseDataCheckbox("#majorInsuranceVLS-div","MAJOR_INSURANCE",1,"majorInsuranceVLS");
	//保险公司
	CommonUtils.findBaseDataOption("select[name='insuranceCompanyVl']","INSURANCE_COMPANY",1);
	//关系
	CommonUtils.findBaseDataOption("#relationVL","RELATION",1);
	//资方管理
	CommonUtils.findBaseDataOption("#managementOfCapitalVl","THE_INVESTOR",1);
	//汽车经销商
	CommonUtils.findBaseDataOption("#autoDealerVl","AUTO_DEALER",1);
	//配偶证件类型
	CommonUtils.findBaseDataOption("#spouseIdType","BAQSNG_PAPER_TYPE",1);
	//担保人证件类型
	CommonUtils.findBaseDataOption("#certTypeVL","BAQSNG_PAPER_TYPE",1);
	//担保人性别
	CommonUtils.findBaseDataRadio("#sponsorSex-div","BAQSNG_SEX",1,"sponsorSex");
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	//时间控件初始化
	laydate({elem : "#birthDate",event : "focus"});
	
	//婚姻状况
	$("#marriageStatus").change(function(){
		var str = $(this).find("option:selected").text();
		if(str.indexOf("已婚")>-1){//已婚
			$("#spouse-div").show();
		}else{
			$("#spouse-div").hide();
		}
	});
	//获取url中的参数
	function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}
	var type = getUrlParam('type');
	if(type === ''){
		 $('.customerLevel').show();
	}
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	//验证表单数据
    	if (!$("#form").valid()) {
    		return;
        }
    	//获取表单元素所有数据集合
    	var formData = CommonUtils.getFormJson('#form');
    	if(formData.customerLevel === null ){
    	  formData.customerLevel= type;
    	}
    	//加载效果
    	mask_fullscreen();
    	var url = "/xxm/rest/baseCustomer/baseCustomerService/addBaseCustomerType";
    	//请求后台
    	$.ajax({
    		url:url,
    		data:JSON.stringify(formData),
    		type:'post',  
    		dataType:'json',  
    		contentType: "application/json",
    		success:function(data) {
    			if(data.result){
    				//消息提示款
    				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
    					location.reload();
    					
    				});
    	   		}else{
    	   			//消息提示款
    				swal({title : "很抱歉",text : data.msg,type : "error"});
    	   		}
    		},
    		error : errorFunc 
    	});
    });
//    $("#btnQuit").click(function(){
//    	if(type === '1'){
//    	    window.location.href = "base_customer.html";
//    	}else if(type === '2'){
//    		window.location.href = "intention_base_customer.html";
//    	}else if(type === '3'){
//    		window.location.href = "contract_base_customer.html";
//    	}else if(type === ''){
//    		window.location.href = "archivalRecords.html";
//    	}
//    });
});
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}


