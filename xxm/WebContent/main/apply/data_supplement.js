var applyInfoId = CommonUtils.getUrlParam("applyInfoId");
var customerId = CommonUtils.getUrlParam("customerId");
var taskId = CommonUtils.getUrlParam("taskId");
var taskDefinitionKey = CommonUtils.getUrlParam("taskDefinitionKey");

//表单向导
$("#form").steps({
	bodyTag:"fieldset",
//	enableAllSteps:true ,
//	enablePagination :false ,
	onStepChanging:function(event,currentIndex,newIndex){//下一步
		if(currentIndex>newIndex){
			return true
		}
		if(newIndex===3&&Number($("#age").val())<18){
			return false
		}
		var form=$(this);
		if(currentIndex<newIndex){
			$(".body:eq("+newIndex+") label.error",form).remove();
			$(".body:eq("+newIndex+") .error",form).removeClass("error")
		}
		form.validate().settings.ignore=":disabled,:hidden";
//		return form.valid();
		return true;
	},
	onStepChanged:function(event,currentIndex,priorIndex){//下一步
		if(currentIndex===2&&Number($("#age").val())>=18){
			$(this).steps("next")
		}
		if(currentIndex===2&&priorIndex===3){
			$(this).steps("previous")
		}
	},
	onFinishing:function(event,currentIndex){//完成
		var form=$(this);
		form.validate().settings.ignore=":disabled";return form.valid()
	},
	onFinished:function(event,currentIndex){//完成
		//判断是否添加成功
			var isPass=supplementFu("2");//提交
			if(isPass){
				var form=$(this);
				form.submit()
			}
	}
}).validate({
	errorPlacement:function(error,element){
		element.before(error)
	},
	rules:{
		confirm:{equalTo:"#password"}
	}
});


$(document).ready(function(){
	
	//保存按钮
	var html='<li id="saveBu" aria-hidden="false" aria-disabled="false"><a role="menuitem">保存</a></li>';
	$(".actions,.clearfix").find("ul li").last().prev().after(html);
	//当表单向导内容过长时将出现滚动条
	$(".content").css("overflow-y","scroll");
	$(".steps").css("padding-left","3px");
	//计算内容高度
	var a1 = document.body.offsetHeight; 
	var b1 = $(".steps").height();
	var c1 = $(".actions").height();
	$(".content").css("height",(a1-b1-2*c1+10)+"px");
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
	//婚姻状况
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
	//争议解决方式
	CommonUtils.findBaseDataOption("#arguSolution","ARGU_SOLUTION",1);
	//车辆种类
	CommonUtils.findBaseDataOption("#carKindCode","CAR_KIND_CODE",1);
	//购车用途(租赁使用用途)
	CommonUtils.findBaseDataOption("#leaseholdUse","CAR_USAGE_PRODUCT_CONFIG",1);
	//客户来源
	CommonUtils.findBaseDataOption("#loanWay","LOAN_WAY",1);
	//所购车辆种类
//	CommonUtils.findBaseDataOption("#loanWay","LOAN_WAY",1);
	//所购/所租车辆类型
	CommonUtils.findBaseDataOption("#carType","CAR_TYPE",1);
	//是否他行
	CommonUtils.findBaseDataOption("#isotherbank","ISOTHERBANK",1);
	//收款方账号类型
	CommonUtils.findBaseDataOption("#payeetype","PAYEETYPE",1);
	//GPS状态
	CommonUtils.findBaseDataOption("#gpsstt","GPSSTT",1);
	//是否为‘白户’
	CommonUtils.findBaseDataOption("#ifwhiteflag","IFWHITEFLAG",1);
	//原车辆使用性质
	CommonUtils.findBaseDataOption("#useNature","USE_NATURE",1);
	//是否是联保业务出单
	CommonUtils.findBaseDataOption("#iscoinTypeFlag","ISCOIN_TYPE_FLAG",1);
	//健康状况
	CommonUtils.findBaseDataOption("#appliHealth","APPLI_HEALTH",1);
	//户籍状况
	CommonUtils.findBaseDataOption("#appliResidence","APPLI_RESIDENCE",1);
	//主要收入来源
	CommonUtils.findBaseDataOption("#incomeSource","INCOME_SOURCE",1);
	//住房产权
	CommonUtils.findBaseDataOption("#houseStat","HOUSE_STAT",1);
	//住房来源
	CommonUtils.findBaseDataOption("#houseSource","HOUSE_SOURCE",1);
	//住房类型
	CommonUtils.findBaseDataOption("#houseType","HOUSE_TYPE",1);
	//居住状况
	CommonUtils.findBaseDataOption("#familySts","FAMILY_STS",1);
	//信用还款记录
	CommonUtils.findBaseDataOption("#repayRec","REPAY_REC",1);
	//公用事业付费记录
	CommonUtils.findBaseDataOption("#pubRec","PUB_REC",1);
	//其他记录
	CommonUtils.findBaseDataOption("#othRec","OTH_REC",1);
	//单位性质
	CommonUtils.findBaseDataOption("#unitType","UNIT_TYPE",1);
	//投保人是否非首次购车
	CommonUtils.findBaseDataOption("#noFirstFlag","NO_FIRST_FLAG",1);
	//抵(质)押物性质
	CommonUtils.findBaseDataOption("#guaranteeType","GUARANTEE_TYPE",1);
	//入账是否借款人账号
	CommonUtils.findBaseDataOption("#isLoanAccount","IS_LOAN_ACCOUNT",1);
	//是否固定利率
	CommonUtils.findBaseDataOption("#fixRateInd","FIX_RATE_IND",1);
	//联系方式
	CommonUtils.findBaseDataOption("#contct","CONTCT",1);
	//联系人类别
	CommonUtils.findBaseDataOption("#linkType","LINK_TYPE",1);
	//流程标志
	CommonUtils.findBaseDataOption("#mktProdType","MKTPROD_TYPE",1);
//	抵(质)押物性质GUARANTEE_TYPE
//	CommonUtils.findBaseDataOption("#guaranteeType","GUARANTEE_TYPE",1);
//	是否需要办理保险REPAID_TYPE
	CommonUtils.findBaseDataOption("#repaidType","REPAID_TYPE",1);
//	抵质押品法律有效性LOAN_BANK_CODE
	CommonUtils.findBaseDataOption("#loanBankCode","LOAN_BANK_CODE",1);
//	押品是否已出租LOAN_NATURE
	CommonUtils.findBaseDataOption("#loanNature","LOAN_NATURE",1);
//	是否运营车辆USE_NATURE_CODE
	CommonUtils.findBaseDataOption("#useNatureCode","USE_NATURE_CODE",1);
//	权证完备状态PRE_CHAR1
	CommonUtils.findBaseDataOption("#preChar1","PRE_CHAR1",1);
//	抵押物状态—阶段LOAN_YEAR
	CommonUtils.findBaseDataOption("#loanYear","LOAN_YEAR",1);
//	抵押物状态—是否扣押NONLOCAL_FLAG
	CommonUtils.findBaseDataOption("#nonlocalFlag","NONLOCAL_FLAG",1);
//	所购车辆种类STEP_HULL
	CommonUtils.findBaseDataOption("#lease_stepHull","STEP_HULL",1);
//	押品所有人证件类型LOAN_USAGE
	CommonUtils.findBaseDataOption("#loanUsage","LOAN_USAGE",1);
//	是否共有财产COMMUNITY_PROPERTY
	CommonUtils.findBaseDataOption("#communityProperty","COMMUNITY_PROPERTY",1);
//	现任职务
	CommonUtils.findBaseDataOption("#positions","APPLIDUTY",1);
//	职业代码
	CommonUtils.findBaseDataOption("#appliWork","APPLIWORK",1);
//	车辆用途
	CommonUtils.findBaseDataOption("#usesTp","USES_TP",1);
//	利率模式
	CommonUtils.findBaseDataOption("#tateMode","TATEMODE",1);
//	险种代码
	CommonUtils.findBaseDataOption("#riskCode","RISK_CODE",1);
//	资金用途
	CommonUtils.findBaseDataOption("#loanPurpost","LOANPURPOST",1);
//	申请币种
	CommonUtils.findBaseDataOption("#applyCurrency","APPLYCURRENCY",1);
//	申请期限
	CommonUtils.findBaseDataOption("#applNosInst","APPLNOSINST",1);
//	利率调整方式
	CommonUtils.findBaseDataOption("#nextRepcOption","NEXT_REPC_OPTION",1);
//	号牌种类
	CommonUtils.findBaseDataOption("#licenseTypeProductConfig","LICENSE_TYPE_PRODUCT_CONFIG",1);
//	所购车辆种类(抵押登记)
	CommonUtils.findBaseDataOption("#stepHull","STEP_HULL_M",1);
//	银行代码
	CommonUtils.findBaseDataOption("#bankcode","BANKCODE",1);
//	主联机构代码
	CommonUtils.findBaseDataOption("#maincomcode","MAINCOMCODE",1);
//	方案代码
	CommonUtils.findBaseDataOption("#rationType","RATION_TYPE",1);
//	编号
	CommonUtils.findBaseDataOption("#schemeCode","SCHEME_CODE",1);
//	还款频率
	CommonUtils.findBaseDataOption("#repyFq","REPY_FQ",1);
//	被保险人组织机构代码
	CommonUtils.findBaseDataOption("#insuredIdType","INSUREDID_TYPE",1);
	
	//二手车必传项
	$.stepHullFu=function(){
		//新车必填又二手车必填的字段：车架号,发动机号,车辆颜色,车型代码,车型名称,初登日期,人保评估价,行驶里程数（公里）,号牌号码
		//第三方评估价/车龄/原车辆使用性质
		var val1=$("#tripartiteEv,#carAge,#useNature");
		var val2=$("#sealAm,#invoiceValue");//销售指导价/发票金额
		if($("#lease_stepHull").val()=="1"){//二手车
			val1.addClass("required");
			val2.removeClass("required").removeClass("error");
		}else if($("#lease_stepHull").val()=="0"){//新车
			val2.addClass("required");
			val1.removeClass("required").removeClass("error");
		}else{
			val1.removeClass("required").removeClass("error");
			val2.removeClass("required").removeClass("error");
		}
		$("label[class='error']").remove();
	}
	$("#lease_stepHull").change(function(){
		$.stepHullFu();
	});
	//流程标识
	$("#mktProdType").change(function(){
		if($(this).val()=="A202009-FIR"){//人保租赁贷
			//租赁公司名称/租赁公司地址/租赁公司购车付款流水号/租赁公司购车付款日期/租赁公司购车付款金额/租赁公司购车收款方/租赁公司购车收款账号/租赁合同编号/租赁合同生效日/租赁合同到期日/租赁合同金额
			$("#leasecmpname,#leasecmpadr,#payJnlNo,#payDate,#payAmount,#payeeName,#payeeAcct,#downPayment,#leaseCtctNo,#leaseStarTime,#leaseEndTime,#leaseCtctAm").addClass("required");
		}else{
			$("#leasecmpname,#leasecmpadr,#payJnlNo,#payDate,#payAmount,#payeeName,#payeeAcct,#downPayment,#leaseCtctNo,#leaseStarTime,#leaseEndTime,#leaseCtctAm").removeClass("required").removeClass("error");
		}
		$("label[class='error']").remove();
	});
	
	//租赁合同金额
	$("#leaseCtctAm").change(function(){
		if($(this).val()!=""&&$("#applyAmt").val()!=""&&Number($(this).val())<Number($("#applyAmt").val())){
			$(this).val("");
			swal({title : "很抱歉",text : "租赁合同金额必须≥基本信息里的申请金额",type : "info"});
		}
	});
	
	//首付款金额≥汽车实际成交价的10%
	$("#tranAm,#downPayment").change(function(){
		var tranAm=Number(($("#tranAm").val()==""?'0':$("#tranAm").val()));//实际成交价
		var downPayment=Number(($("#downPayment").val()==""?'0':$("#downPayment").val()));//首付款金额
		if(tranAm!=0&&downPayment!=0){
			var val=downPayment/tranAm;
			if(val<0.1){
				$("#downPayment").val("");
				swal({title : "很抱歉",text : "首付款金额≥汽车实际成交价的10%",type : "info"});
			}
		}
	});
	
	//租赁公司购车收款方
//	$("#payeeName").change(function(){
//		if($(this).val()!=""&&$("#leasecmpname").val()!=""&&$(this).val()!=$("#leasecmpname").val()){
//			$(this).val("");
//			swal({title : "很抱歉",text : "租赁公司购车收款方需与租赁公司名称保持一致",type : "info"});
//		}
//	});
	
	//收款方名称
	$("#payeename").change(function(){
		if($("#mktProdType").val()=="A202009-FIR"&&$(this).val()!=""&&$("#leasecmpname").val()!=""&&$(this).val()!=$("#leasecmpname").val()){
			$(this).val("");
			swal({title : "很抱歉",text : "租赁模式下租赁公司名称和收款方名称要一致",type : "info"});
		}
	});
	//租赁公司名称
	$("#leasecmpname").change(function(){
		if($("#mktProdType").val()=="A202009-FIR"&&$(this).val()!=""&&$("#payeename").val()!=""&&$(this).val()!=$("#payeename").val()){
			$(this).val("");
			swal({title : "很抱歉",text : "租赁模式下租赁公司名称和收款方名称要一致",type : "info"});
		}
	});
	
	//实际成交价≥4万元
	$("#tranAm").change(function(){
		if($(this).val()!=""&&Number($(this).val())<40000){
			$(this).val("");
			swal({title : "很抱歉",text : "实际成交价≥4万元",type : "info"});
		}
	});
	//车龄≤8年
	$("#carAge").change(function(){
		if($(this).val()!=""&&Number($(this).val())>8){
			$(this).val("");
			swal({title : "很抱歉",text : "车龄≤8年",type : "info"});
		}
	});
	//还款日
	$("#atrsDueDay_base").blur(function(){
		if($(this).val()=="10"||$(this).val()=="15"){
			$(this).val("");
			//消息提示款
			swal({title : "请避免10日、15日",text : data.msg,type : "info"});
		}
	});
	//利率计算模式为指定利率时，利率调整方式必须为FXX"
	$("#nextRepcOption").change(function(){
		if($("#tateMode").val()=="FX"){
			$("#nextRepcOption option[value='FXX']").prop("selected","selected");
			//消息提示款
			swal({title : "当利率计算模式为 固定 利率时，利率调整方式必须为    固定不变!",text : data.msg,type : "info"});
		}else if($("#tateMode").val()==""){
			$("#nextRepcOption option[value='FXX']").prop("selected","selected");
			//消息提示款
			swal({title : "请先选择利率计算模式!",text : data.msg,type : "info"});
		}
	});
	
	//资金用途。车辆按揭传输用途为：07 购置车产  租赁贷和车抵贷为：13 支付其他消费款
	$("#mktProdType").change(function(){
		if($(this).val()=="A201002-PRE"){
			$("#loanPurpost option[value='07']").prop("selected","selected");
		}else if($(this).val()==""){
			$("#loanPurpost option[value='']").prop("selected","selected");
		}else{
			$("#loanPurpost option[value='13']").prop("selected","selected");
		}
	});
	
	//首付比例
	$("#preRentRateVL").change(function(){
		//成交价
		var actualPrice=$("#actualPrice").val();
		var k=-1;
		if(actualPrice!=""&&actualPrice!=null){
			actualPrice=Number(actualPrice);
			var str=$(this).find("option:selected").text().replace("%","");
			if(str!="请选择"){
			    str= str/100;
			    //首付款
				var downPayment=actualPrice*Number(str);
				$("#quotedPrice_downPayment").val(downPayment);
				//融资金额
				var financingAmount=actualPrice-downPayment;
				$("#financingAmount").val(financingAmount);
				k=1;
			}
		}
		if(k==-1){
			$("#quotedPrice_downPayment").val("");
			$("#financingAmount").val("");
		}
	});
	
	//成交价
	$("#actualPrice").change(function(){
		//成交价
		var actualPrice=$("#actualPrice").val();
		$("#guidePrice").val(actualPrice);
		if(actualPrice!=""&&actualPrice!=null){
			actualPrice=Number(actualPrice);
			//首付款
			var str=$("#preRentRateVL").find("option:selected").text().replace("%","");
			if(str!="请选择"){
				str= str/100;
				var downPayment=actualPrice*Number(str);
				$("#quotedPrice_downPayment").val(downPayment);
				//融资金额
				var financingAmount=actualPrice-downPayment;
				$("#financingAmount").val(financingAmount);
				k=1;
			}
		}
		if(k==-1){
			$("#quotedPrice_downPayment").val("");
			$("#financingAmount").val("");
		}
	});
	
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	//附件阶段
	CommonUtils.findBaseDataOption("#category-big","ATTACHMENT_CLASSIFY",1);
	//附件阶段条件
	CommonUtils.findBaseDataOption("#stage-condition","ATTACHMENT_CLASSIFY",1);
	
	//时间控件初始化
//	laydate({elem : "#birthDate",event : "focus"});
//	laydate({elem : "#signDate",event : "focus"});
//	laydate({elem : "#startDate",event : "focus"});
//	laydate({elem : "#endDate",event : "focus"});
//	laydate({elem : "#deliverDate",event : "focus"});
//	laydate({elem : "#applyDate",event : "focus"});
//	laydate({elem : "#idvalidStart",event : "focus"});
//	laydate({elem : "#idvalidEnd",event : "focus"});
//	laydate({elem : "#gtreDt",event : "focus"});
//	laydate({elem : "#loanEndDate",event : "focus"});
//	laydate({elem : "#loanStartDate",event : "focus"});
//	laydate({elem : "#enrollDate",event : "focus"});
	//获取明天的日期
	var day3 = new Date();
	day3.setTime(day3.getTime()+24*60*60*1000);
	var s3 = day3.getFullYear()+"-" + (day3.getMonth()+1) + "-" + day3.getDate();
	//时间控件初始化
	var a="#";
	$(".laydate-icon").each(function(){
		//起保日期必须大于或等于明天
		if($(this).attr("id")=="startDate"){
			laydate({
				   elem: '#startDate', //对应id
				   event : "focus",
				   min: s3 //设定最小日期为明天日期
				});
		}else{
			laydate({elem : a+$(this).attr("id"),event : "focus"});
		}
	});
	
	//贷款申请金额≤30万元
	$("#applyAmt").change(function(){
		if($(this).val()!=""&&Number($(this).val())>300000){
			$(this).val("");
			swal({title : "很抱歉",text : "贷款申请金额≤30万元",type : "info"});
		}
	});	
	
	//查询挂靠商基本信息
	findAffiliateds("#companyAffiliatedId");
	//婚姻状况
	$("#marriageStatus").change(function(){
		if($(this).val()=="B"||$(this).val()=="A"){//已婚
			$("#spouse-div").show();
		}else{
			$("#spouse-div").hide();
		}
	});
	
	//克隆担保人
	$("[name='addSponsor']").click(function(){
		var count=$("span[name='order-span']").length;
		$("#sponsor-area").append($("#sponsor-area div[name='sponsor-div']:last").clone(true));
		//获取最后一个担保人div
		var $div=$("#sponsor-area div[name='sponsor-div']:last");
		$div.find("[name='delSponsor']").show();
		//单选框的name不能相同
		$div.find("input[name='sponsorSex']").attr("name","sponsorSex"+count+1);
		$div.find("span[name='order-span']").html(count+1);//显示第几个担保人
//		$div.find("input").val("");
		//单选复选初始化
		$div.find(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	});
	//删除担保人
	$("[name='delSponsor']").click(function(){
		$(this).parent().remove();
		//担保人排序
		var count=$("span[name='order-span']").length+1;
		var i=1;
		$("span[name='order-span']").each(function(){
			if(i<=count)$(this).html(i++);
		});
	});
	
	//保存
	$("#saveBu").click(function(){
		supplementFu("1");
	});
	
	//查询阶段下的类别
//	$("#category-big").change(function(){
//		var codeVal=$(this).val();
//		if(codeVal!=null&&codeVal!=''){
//			var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
//			CommonUtils.findBaseDataOption("#category-little","ATTACHMENT_CLASSIFY",2,vo.baseId);
//		}else{
//			$("#category-little").empty();
//		}
//	});
	//查询阶段下的类别“条件”
//	$("#stage-condition").change(function(){
//		var codeVal=$(this).val();
//		if(codeVal!=null&&codeVal!=''){
//			var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
//			CommonUtils.findBaseDataOption("#category-condition","ATTACHMENT_CLASSIFY",2,vo.baseId);
//		}else{
//			$("#category-condition").empty();
//		}
//	});
	
	var fileAttmentFile = function(){
		var stageCondition=$("#stage-condition").val();//阶段
		if(stageCondition!=null&&stageCondition!=''){
			$("input[name='extendField1']").each(function(){
				if($(this).val()!=stageCondition){
					$(this).parents(".file-box").hide();
				}else{
					$(this).parents(".file-box").show();
				}
			});
		}else{
			$(".file-box").show();
		}
		var categoryCondition=$("#category-condition").val();//类别
		var prev;
		if(categoryCondition!=null&&categoryCondition!=''){
			$("input[name='extendField2']").each(function(){
				prev=$(this).prev();
				if($(this).val()!=categoryCondition){
					$(this).parents(".file-box").hide();
				}else if(prev.val()==stageCondition){
					$(this).parents(".file-box").show();
				}
			});
		}
		var attachmentType=$("#attachmentType-div input:checked").val();//附件类型
		if(attachmentType!="1"){
			$(".file-box:visible").each(function(){
				var name=$(this).attr("name");
				if(attachmentType==2&&name=="img-div"){//图片
					$(this).show();
				}else if(attachmentType==3&&name=="document-div"){//文档
					$(this).show();
				}else{
					$(this).hide();
				}
			});
		}
	}
	
	//附件显示筛选
	$("#attachmentType-div").find(":input").on('ifChecked', function(event){ 
		fileAttmentFile();
	}); 
	
	//阶段筛选
	$("#stage-condition").change(function(){
		fileAttmentFile();
		var codeVal=$(this).val();
		if(codeVal!=null&&codeVal!=''){
			var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
			CommonUtils.findBaseDataOption("#category-condition","ATTACHMENT_CLASSIFY",2,vo.baseId);
		}else{
			$("#category-condition").empty();
		}
	});
	
	//类别筛选
	$("#category-condition").change(function(){
		fileAttmentFile();
	});
	
	//附件条件查询按钮
//	$("#conditionBu").click(function(){
//		var stageCondition=$("#stage-condition").val();//阶段
//		if(stageCondition!=null&&stageCondition!=''){
//			$("input[name='extendField1']").each(function(){
//				if($(this).val()!=stageCondition){
//					$(this).parent().hide();
//				}else{
//					$(this).parent().show();
//				}
//			});
//		}else{
//			$(".file").show();
//		}
//		var categoryCondition=$("#category-condition").val();//类别
//		var prev;
//		if(categoryCondition!=null&&categoryCondition!=''){
//			$("input[name='extendField2']").each(function(){
//				prev=$(this).prev();
//				if($(this).val()!=categoryCondition){
//					$(this).parent().hide();
//				}else if(prev==stageCondition){
//					$(this).parent().show();
//				}
//			});
//		}
//		var attachmentType=$("#attachmentType-div input:checked").val();//附件类型
//		if(attachmentType==2){//图片
//			$("div[name='img-div']").show();
//			$("div[name='document-div']").hide();
//		}else if(attachmentType==3){//文档
//			$("div[name='document-div']").show();
//			$("div[name='img-div']").hide();
//		}else{
//			$("div[name='document-div']").show();
//			$("div[name='img-div']").show();
//		}
//	});
	//附件条件重置按钮
	$("#resetBu").click(function(){
		$("#stage-condition option:first").prop("selected", 'selected');//阶段
		$("#category-condition option:first").prop("selected", 'selected');//类别
		$("#attachmentType-div input[value='']").prop("checked","checked");//附件类型
		//单选复选初始化
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	});
	
	//删除附件
	$(document).on("click","button[name='delAttachment']",function(){
		$(this).parents(".file-box").remove();
	})
	
	if(applyInfoId!=null){//填充数据
		$.mask_fullscreen();//打开加载进度条
		var data={applyInfoId:applyInfoId};
		$.ajax({
			url:"/xxm/rest/apply/applyService/findProjectData",
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
					CommonUtils.fillFormData("#applyInfo-tab",JSON.stringify(data));
					var rejudicationReturnVO=data.rejudicationReturnVO;
					$("#prejudicNo").val(rejudicationReturnVO.prejudicNo);
					$("#productionVl").val(data.productionVlName);
					$("#loanContractNo").val(data.loanContractNo);
					$("#salesman").val(data.salesmanName);
					if(data.customerVO){//客户信息
						CommonUtils.fillFormData("#customer-base-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-spouse-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-account-tab",JSON.stringify(data.customerVO));
						if(data.customerVO.marriageStatus=="2"){//婚姻状况
							$("#spouse-div").show();
						}
					}
					if(data.supplementBaseVO){//补充基本信息
						CommonUtils.fillFormData("#supplement-base-tab",JSON.stringify(data.supplementBaseVO));
						CommonUtils.fillFormData("#supplement-default-tab",JSON.stringify(data.supplementBaseVO));
						var startDate=data.supplementBaseVO.startDate;  //起保日期
						if(startDate!=""&&startDate!=null){
							startDate = startDate.replace(/-/g,"/");//替换字符，变成标准格式  
							var d2=new Date();//取今天的日期  
							var d1 = new Date(Date.parse(startDate));  
							if(d1<d2){  
							  $("#startDate").val("");
							}
						}
						//申请书编号
						$("#applyNo").val((data.applyNo==null?'':data.applyNo));
					}
					var quotedPriceVO=data.quotedPriceVO;
					if(quotedPriceVO){//报价信息
						CommonUtils.fillFormData("#quotedPrice-tab",JSON.stringify(quotedPriceVO));
						//还款计划
//						CommonUtils.fillFormData("#repaymentPlan-tab",JSON.stringify(quotedPriceVO.));
					}
					if(data.leaseInfoVO){//租赁物信息
						CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
					}
					var carInsuranceVO=data.carInsuranceVO;
					if(carInsuranceVO){//车辆保险信息
						CommonUtils.fillFormData("#carInsurance-tab",JSON.stringify(carInsuranceVO));
						if(carInsuranceVO.majorInsuranceVLS!=null){//主要险种
							var arr=carInsuranceVO.majorInsuranceVLS.split(',');
							for(var i=0;i<arr.length;i++){
								$("#majorInsuranceVLS-div input[value='"+arr[i]+"']").prop("checked","checked").parents("div").addClass("checked");;
							}
						}
					}
					if(data.guaranteeInsuranceVO){//车辆保险信息
						CommonUtils.fillFormData("#guaranteeInsurance-tab",JSON.stringify(data.guaranteeInsuranceVO));
					}
					var sponsorList=data.sponsorList;//担保人
					if(sponsorList!=null&&sponsorList.length>0){
						for(var i=0;i<sponsorList.length;i++){
							if(i==0){
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
								$("#sponsor-area div[name='sponsor-div']:last").find("div[name='sponsorSex-div'] input[value='"+sponsorList[i].sponsorSex+"']").prop("checked","checked");
								//单选初始化
								$("#sponsor-area div[name='sponsor-div']:last").find("div[name='sponsorSex-div']").iCheck({radioClass:"iradio_square-green"});
							}else{
								$("[name='addSponsor']").click();
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
								$("#sponsor-area div[name='sponsor-div']:last").find("div[name='sponsorSex-div'] input[value='"+sponsorList[i].sponsorSex+"']").prop("checked","checked");
								//单选初始化
								$("#sponsor-area div[name='sponsor-div']:last").find("div[name='sponsorSex-div']").iCheck({radioClass:"iradio_square-green"});
							}
						}
					}
					//附件
					var attachmentTextList=data.attachmentTextList;
					if(null != attachmentTextList && attachmentTextList.length>0){
						var serverPath = CommonUtils.getServerURL() + "/file";
						var html='';
						for(var i = 0; i < attachmentTextList.length; i++){
							if(attachmentTextList[i].attachmentType=="3"){//文档
								html=html+'<div class="file-box" name="document-div" style="width:120px;">'; 
							}else{
								html=html+'<div class="file-box" name="img-div" style="width:120px;">'; 
							}
							html=html+'<div class="file" style="width:100px;">'; 
							html=html+'<div class="image" style="height:70px;">'; 
							if(attachmentTextList[i].attachmentType=="3"){//文档
								html=html+'<img class="img-responsive" src="../img/document.png" alt="">';
							}else{
								html=html+'<img modal="zoomImg" class="img-responsive" src="'+serverPath + attachmentTextList[i].attachmentPath+'" alt="">';
							}
							html=html+'</div>'; 
							html=html+'<div class="file-name">';
							html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attachmentTextList[i].attachmentName+'">'+attachmentTextList[i].attachmentName+'</div>'; 
							html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
							html=html+'</div>'; 
							html=html+'<input type="hidden" name="attId" value='+attachmentTextList[i].attachmentID+'>';
							html=html+'<input type="hidden" name="extendField1" value='+attachmentTextList[i].extendField1+'>';
							html=html+'<input type="hidden" name="extendField2" value='+attachmentTextList[i].extendField2+'>';
							html=html+'</div></div>'; 
						}
						$("#upload-div").append(html);
						$("[name='tooltip-div']").tooltip();//提示初始化
						$.getScript("../js/boxImg.js");  //加载图片轮播js文件
					}
					
					if(data.msCoinVO){//投保单归属机构信息
						CommonUtils.fillFormData("#coin-tab",JSON.stringify(data.msCoinVO));
					}
					var msInsuredPlanVO=data.msInsuredPlanVO;
					if(msInsuredPlanVO){//方案信息
						CommonUtils.fillFormData("#insuredPlan-tab",JSON.stringify(data.msInsuredPlanVO));
						//条款信息
						var msSchemeList=msInsuredPlanVO.msSchemeList;
						if(msSchemeList!=null&&msSchemeList.length>0){
							CommonUtils.fillFormData("#scheme-tab",JSON.stringify(msSchemeList[0]));
						}
						//特别约定信息
						var msEngageList=msInsuredPlanVO.msEngageList;
						if(msEngageList!=null&&msEngageList.length>0){
							CommonUtils.fillFormData("#engage-tab",JSON.stringify(msEngageList[0]));
						}
					}
					var msInsuredVO=data.msInsuredVO;
					if(msInsuredVO){//被保人信息
						CommonUtils.fillFormData("#insured-tab",JSON.stringify(msInsuredVO));
						//受益人信息
						if(msInsuredVO.msBeneficiaryVO){
							CommonUtils.fillFormData("#beneficiarys-tab",JSON.stringify(msInsuredVO.msBeneficiaryVO));
						}
					}
					
					var msLoanInfoVO=data.msLoanInfoVO;
					if(msLoanInfoVO){//放款信息
						CommonUtils.fillFormData("#loanInfo-tab",JSON.stringify(msLoanInfoVO));
					}
					
					var msMortgageInfoVO=data.msMortgageInfoVO;
					if(msMortgageInfoVO){//车辆抵押登记信息
						CommonUtils.fillFormData("#mortgageInfo-tab",JSON.stringify(msMortgageInfoVO));
					}
					//默认值
					$("#startHour").val("00");//起保小时
					$("#endHour").val("24");//终保小时
					$.stepHullFu();//二手车必传项
					$.mask_close_all();//关闭加载进度条
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
					$.mask_close_all();//关闭加载进度条
		   		}
			},
			error : errorFunc 
		});
	}
	$('#tbLeaseInfo').bootstrapTable(init);
	$("#leaseInfoBu").click(function(){
		//初始化table并刷新数据
		$("#tbLeaseInfo").bootstrapTable('refresh', init);
	});
	
	//基础租赁物选择
    $("#leaseInfoConfirmBu").click(function(){
    	var rows= $("#tbLeaseInfo").bootstrapTable('getSelections');
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要选择的公司！","info");
        }else{
        	var leaseInfoId;
        	for(var i = 0; i < rows.length; i++){
        		leaseInfoId=rows[i].leaseInfoId;
        	}
        	$('#myModal').modal('hide');//隐藏弹框对象
        	//查询公司详细信息
        	$("#leaseInfo-tab").find("input").val("");
    		var leaseInfoVO = findBaseLeaseInfo(leaseInfoId);
    		CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
    		
        }
    })
    
    //图片上传操作
//    initUploadImage();
    //文档上传操作
//    initUploadDocument();
    //上传操作
    initUploadFile();
    //业务员
//    findUserInfo();
});

//信息提交
function supplementFu(operationType){
	$.mask_fullscreen();//打开加载进度条
	var isPass =false;
	//获取表单元素所有数据集合
	var data = CommonUtils.getTagObj($("#applyInfo-tab :input[name]"));
	var supplementBaseVO1=CommonUtils.getTagObj($("#supplement-base-tab :input[name]"));//基本信息
	var supplementBaseVO2=CommonUtils.getTagObj($("#supplement-default-tab :input[name]"));//罚息信息
	data.supplementBaseVO=$.extend({}, supplementBaseVO1, supplementBaseVO2);//合并对象
	var customerVO1=CommonUtils.getTagObj($("#customer-base-tab :input[name]"));//客户基本信息
	customerVO1.baseCustomerSex=$('#baseCustomerSex-div input[name="baseCustomerSex"]:checked ').val();//性别
	var customerVO2=CommonUtils.getTagObj($("#customer-spouse-tab :input[name]"));//配偶信息
	var customerVO3=CommonUtils.getTagObj($("#customer-account-tab :input[name]"));//帐号信息
	customerVO3.isABC=$('#isABC-div input[name="isABC"]:checked').val();//是否农行
	data.customerVO=$.extend({}, customerVO1, customerVO2,customerVO3);//合并对象
	data.customerVO.customerId=customerId;
	data.quotedPriceVO=CommonUtils.getTagObj($("#quotedPrice-tab :input[name]"));//报价信息
	data.repaymentPlanVO=CommonUtils.getTagObj($("#repaymentPlan-tab :input[name]"));//还款计划
	data.leaseInfoVO=CommonUtils.getTagObj($("#leaseInfo-tab :input[name]"));//租凭物信息
	data.carInsuranceVO=CommonUtils.getTagObj($("#carInsurance-tab :input[name]"));//车产保险
	//主要险种(复选)
	var s='';
	$('#majorInsuranceVLS-div input[name="majorInsuranceVLS"]:checked').each(function(){ 
		s+=$(this).val()+',';
	}); 
	data.carInsuranceVO.majorInsuranceVLS=s;
	data.guaranteeInsuranceVO=CommonUtils.getTagObj($("#guaranteeInsurance-tab :input[name]"));//保证保险
	var sponsorList=new Array();
	var sponsorVO={};
	$("table[name='sponsor-tab']").each(function(){
		sponsorVO={};
		sponsorVO=CommonUtils.getTagObj($(this).find(":input[name]").not("input[type='radio']"));//担保人信息
		sponsorVO.sponsorSex=$(this).find($('input[type="radio"]:checked')).val();//担保人性别
		sponsorList.push(sponsorVO);
	});
	data.sponsorList=sponsorList;//担保人集合
	//附件
	var attachmentTextVO={};
	var attachmentTextList=new Array();
//	$("input[name='attId']").each(function(){
//		attachmentTextVO={};
//		attachmentTextVO.attachmentID=$(this).val();
//		attachmentTextList.push(attachmentTextVO);
//	});
	$(".file-box").each(function(){
		attachmentTextVO={};
		attachmentTextVO.attachmentID=$(this).find("input[name='attId']").val();
		attachmentTextVO.attachmentItem="applyInfo";
//		attachmentTextVO.extendField1=$(this).find("input[name='extendField1']").val();
//		attachmentTextVO.extendField2=$(this).find("input[name='extendField2']").val();
		attachmentTextList.push(attachmentTextVO);
	});
	data.attachmentTextList=attachmentTextList;//附件集合
	data.msCoinVO=CommonUtils.getTagObj($("#coin-tab :input[name]"));//投保单归属机构信息
	var msInsuredPlanVO=CommonUtils.getTagObj($("#insuredPlan-tab :input[name]"));//方案信息
	var msSchemeVO=CommonUtils.getTagObj($("#scheme-tab :input[name]"));//条款信息
	var msSchemeList=new Array();
	msSchemeList.push(msSchemeVO);
	var msEngageVO=CommonUtils.getTagObj($("#engage-tab :input[name]"));//特别约定信息
	var msEngageList=new Array();
	msEngageList.push(msEngageVO);
	msInsuredPlanVO.msSchemeList=msSchemeList;
	msInsuredPlanVO.msEngageList=msEngageList;
	data.msInsuredPlanVO=msInsuredPlanVO;
	
	var msInsuredVO=CommonUtils.getTagObj($("#insured-tab :input[name]"));//被保人信息
	var msBeneficiaryVO=CommonUtils.getTagObj($("#beneficiarys-tab :input[name]"));//受益人信息
	msInsuredVO.msBeneficiaryVO=msBeneficiaryVO;
	data.msInsuredVO=msInsuredVO;
	
	data.msLoanInfoVO=CommonUtils.getTagObj($("#loanInfo-tab :input[name]"));//放款信息
	data.msMortgageInfoVO=CommonUtils.getTagObj($("#mortgageInfo-tab :input[name]"));//车辆抵押登记信息
	
	data.applyInfoId=applyInfoId;//申请信息id
	data.operationType=operationType;//操作类型   保存还是提交
	data.taskId = taskId;//任务Id
	data.taskDefinitionKey = taskDefinitionKey;//任务节点
	$.ajax({
		url:"/xxm/rest/apply/applyService/dataSupplement",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			$.mask_close_all();//关闭加载进度条
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					isPass=true;
					//关闭当前选项卡
					closeMenuTab();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
				$.mask_close_all();//关闭加载进度条
	   		}
		},
		error : errorFunc  
	});
	return isPass;
}

//查询挂靠商基本信息
function findAffiliateds(Str){
	$.ajax({
		url:"/xxm/rest/affiliated/affiliatedService/findAffiliateds",
		data:JSON.stringify({}),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(result) {
			if(result!=null){
				var html='';
				for(var i=0;i<result.length;i++){
					html=html+'<option value='+result[i].companyAffiliatedId+'>'+result[i].companyAffiliatedName+'</option>';
				}
				$(Str).append(html);
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
}
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        leaseholdUse: $("#leaseholdUse").val(),//租赁使用用途
        leaseholdArea: $("#leaseholdArea").val()//租赁物使用区域
    };
    return temp;
};
//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}
//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/leaseInfo/leaseInfoService/findLeaseInfoList', //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
    ajaxOptions: {
        async: false // 设置为异步
    },
    toolbar: '#toolbar',                //工具按钮用哪个容器
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sortable: true,                     //是否启用排序
    sortOrder: "asc",                   //排序方式
    queryParams: queryParams,           //传递参数（*）
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber:1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    strictSearch: true,
    showColumns: true,                  //是否显示所有的列
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: false,                //是否启用点击选中行
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "baseLeaseInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			field: 'carName',
			title: '车名称',
			sortable : true
		},{
			field: 'carTypeName',
			title: '车产类型',
			sortable : true
		},{
			field: 'manufacturer',
			title: '制造商',
			sortable : true
//			formatter: categoryVLFormatter
		},{
			field: 'brand',
			title: '品牌',
			sortable: true
			
		},{
			field: 'model',
			title: '型号',
			sortable : true
		}, {
			field: 'leaseholdUse',
			title: '租赁使用用途',
			sortable : true
		},{
			field: 'leaseholdArea',
			title: '租赁物使用区域',
			sortable : true
		}, {
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		}, {
			field: 'lastUpdateDate',
			title: '最后更新时间',
			formatter: dataFormatter
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	}
}

//查询基础租赁物信息
var findBaseLeaseInfo=function(baseLeaseInfoId) {
	var baseLeaseInfo = null;
	$.ajax({url:'/xxm/rest/leaseInfo/leaseInfoService/findLeaseInfoVO',
		data:JSON.stringify({baseLeaseInfoId:baseLeaseInfoId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				baseLeaseInfo = data;
			}
		},
		error : errorFunc
	});
	return baseLeaseInfo;
}

//上传图片
//var initUploadImage = function(){
//	//图片上传
//	$(document).on('change', "input[name='file']", function(){
//		var extendField1=$("#category-big").val();
//		var extendField2=$("#category-little").val();
//		if(extendField1==null||extendField2==null
//				||extendField1==""||extendField2==""){
//			//消息提示款
//			swal({title : "很抱歉",text : "请选择阶段与类别后再进行上传！",type : "info"});
//			$("input[name='file']").val("");
//			return;
//		}
//		var ts=this;
//		if(this.files.length > 10){
//			//消息提示款
//			swal({title : "很抱歉",text : "上传图片总数不能超过10个！",type : "info"});
//			$("input[name='file']").val("");
//			return;
//		}
//		for(var i = 0; i < this.files.length; i++){
//			var fileSize = this.files[i].size; 
//			if(fileSize>1*1024*1024){
//				//消息提示款
//				swal({title : "很抱歉",text : "上传单张图片最大不能超过1M！",type : "info"});
//				$("input[name='file']").val("");
//				return;
//			}
//			var fileName=this.files[i].name; 
//			var suffixIndex=fileName.lastIndexOf(".");  
//			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
//			if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
//				//消息提示款
//				swal({title : "很抱歉",text : "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!",type : "info"});
//				$("input[name='file']").val("");
//				return;
//			}
//		}
//		//创建FormData对象
//		var data = new FormData();
//		//为FormData对象添加数据
//		$.each($(this)[0].files, function(i, file) {
//			data.append('upload_file', file);
//		});
//		var certNo=$("#certNo").val();//身份证
//		var prejudicNo=$("#prejudicNo").val();//预审单号
//		var applyInfoId=$("#applyInfoId").val();//申请id
//		var code="applyInfo";//
//		var stage=extendField1;//阶段
//		var productionVl=extendField2;//文件名称命名规则
//		var FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadImg?certNo='+certNo+'&prejudicNo='+prejudicNo+'&stage='+stage+'&productionVl='+productionVl;
//		FileController=FileController+'&attachmentItemID='+applyInfoId+'&attachmentItem='+code;
////		var FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadImg?attachmentItem=applyInfo&extendField1='+extendField1+'&extendField2='+extendField2;
//		$.ajax({url:FileController,
//			data:data,
//			type:'post',  
//			dataType:'json',
//			processData: false,
//	        contentType: false,
//			success:function(rs) {  
//				if(rs.flag){
//					//消息提示款
//					swal({title : "恭喜您",text : rs.msg,type : "success"},function(){
//						//显示上传成功图片
//						if(null != rs.url && "" != rs.url){
//							var serverPath = CommonUtils.getServerURL() + "/file";
//							var imgURL = rs.url.split(",");
//							var attIds = rs.attrId.split(",");
//							var attrName = rs.attrName.split(",");
//							var html='';
//							for(var i = 0; i < imgURL.length; i++){
//								html=html+'<div name="img-div" class="file-box" style="width:120px;">'; 
//								html=html+'<div class="file" style="width:100px;">'; 
//								html=html+'<div class="image" style="height:70px;">'; 
//								html=html+'<img modal="zoomImg" class="img-responsive" src="'+serverPath + imgURL[i]+'" alt="">';
//								html=html+'</div>'; 
//								html=html+'<div class="file-name">';
//								html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attrName[i]+'">'+attrName[i]+'</div>'; 
//								html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
//								html=html+'</div>'; 
//								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
//								html=html+'<input type="hidden" name="extendField1" value='+extendField1+'>';
//								html=html+'<input type="hidden" name="extendField2" value='+extendField2+'>';
//								html=html+'</div></div>'; 
//							}
//							$("#upload-div").append(html);
//							$("input[name='file']").val("");
//							$("[name='tooltip-div']").tooltip();//提示初始化
//							$.getScript("../js/boxImg.js");  //加载图片轮播js文件
//						}
//					});
//				} else {
//					//消息提示款
//					swal({title : "很抱歉",text : rs.msg, type : "error"});
//				}
//			},
//			error : errorFunc 
//		});
//	});
//}

//文档上传
//var initUploadDocument = function(){
//	$(document).on('change', "input[name='file2']", function(){
//		var extendField1=$("#category-big").val();
//		var extendField2=$("#category-little").val();
//		if(extendField1==null||extendField2==null
//				||extendField1==""||extendField2==""){
//			//消息提示款
//			swal({title : "很抱歉",text : "请选择阶段与类别后再进行上传！",type : "info"});
//			$("input[name='file2']").val("");
//			return;
//		}
//		var ts=this;
//		if(this.files.length > 10){
//			//消息提示款
//			swal({title : "很抱歉",text : "上传文件总数不能超过10个！",type : "info"});
//			$("input[name='file2']").val("");
//			return;
//		}
//		for(var i = 0; i < this.files.length; i++){
//			var fileSize = this.files[i].size; 
//			if(fileSize>10*1024*1024){
//				//消息提示款
//				swal({title : "很抱歉",text : "上传单位文档最大不能超过10M！",type : "info"});
//				$("input[name='file2']").val("");
//				return;
//			}
//			var fileName=this.files[i].name; 
//			var suffixIndex=fileName.lastIndexOf(".");  
//			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
//			if(suffix!="TXT"&&suffix!="DOC"&&suffix!="WPS"&&suffix!="RTF"
//				&&suffix!="HTML"&&suffix!="PDF"&&suffix!="DOCX"&&suffix!="XLSX"&&suffix!="XLS"){  
//				//消息提示款
//				swal({title : "很抱歉",text : "请上传文档（格式TXT、DOC、WPS、RTF等）!",type : "info"});
//				$("input[name='file2']").val("");
//				return;
//			}
//		}
//		//创建FormData对象
//		var data = new FormData();
//		//为FormData对象添加数据
//		$.each($(this)[0].files, function(i, file) {
//			data.append('upload_file', file);
//		});
//		var certNo=$("#certNo").val();//身份证
//		var prejudicNo=$("#prejudicNo").val();//预审单号
//		var applyInfoId=$("#applyInfoId").val();//申请id
//		var code="applyInfo";//
//		var stage=extendField1;//阶段
//		var productionVl=extendField2;//文件名称命名规则
////		var FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadDocument?attachmentItem=applyInfo&extendField1='+extendField1+'&extendField2='+extendField2;
//		var FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadDocument?certNo='+certNo+'&prejudicNo='+prejudicNo+'&stage='+stage+'&productionVl='+productionVl;
//		FileController=FileController+'&attachmentItemID='+applyInfoId+'&attachmentItem='+code;
//		$.ajax({url:FileController,
//			data:data,
//			type:'post',  
//			dataType:'json',
//			processData: false,
//	        contentType: false,
//			success:function(rs) {  
//				if(rs.flag){
//					//消息提示款
//					swal({title : "恭喜您",text : rs.msg,type : "success"},function(){
//						//显示上传成功图片
//						if(null != rs.url && "" != rs.url){
//							var serverPath = CommonUtils.getServerURL() + "/file";
//							var attIds = rs.attrId.split(",");
//							var attrName = rs.attrName.split(",");
//							var html='';
//							for(var i = 0; i < attIds.length; i++){
//								html=html+'<div class="file-box" name="document-div" style="width:120px;">'; 
//								html=html+'<div class="file" style="width:100px;">'; 
//								html=html+'<div class="image" style="height:70px;">'; 
//								html=html+'<img class="img-responsive" src="../img/document.png" alt="">';
//								html=html+'</div>'; 
//								html=html+'<div class="file-name">';
//								html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attrName[i]+'">'+attrName[i]+'</div>'; 
//								html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
//								html=html+'</div>'; 
//								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
//								html=html+'<input type="hidden" name="extendField1" value='+extendField1+'>';
//								html=html+'<input type="hidden" name="extendField2" value='+extendField2+'>';
//								html=html+'</div></div>'; 
//							}
//							$("#upload-div").append(html);
//							$("[name='tooltip-div']").tooltip();//提示初始化
//							$("input[name='file2']").val("");
//						}
//					});
//				} else {
//					//消息提示款
//					swal({title : "很抱歉",text : rs.msg, type : "error"});
//				}
//			},
//			error : errorFunc 
//		});
//	});
//}

//业务员
//var findUserInfo = function(){
//	$.ajax({
//		url:'/xxm/rest/user/userService/findUsers',
//		date:{},
//		type : 'post',
//		dataType : 'json',
//		async : false,// 同步请求
//		contentType : "application/json",
//		success:function(data){
//			if (null != data) {
//				var html='';
//				for(var i=0;i<data.length;i++){             
//		            html=html+"<option value="+data[i].userId+">"+data[i].name+"</option>";
//		        }
//				$("#salesman").append(html);
//			}
//			
//		},
//		error : errorFunc
//	});
//}
//上传
var initUploadFile = function(){
	$(document).on('change', "input[name='file3']", function(){
		var extendField1=$("#stage-condition").val();
		var extendField2=$("#category-condition").val();
		var uploadType;
		if(extendField1==null||extendField2==null
				||extendField1==""||extendField2==""){
			//消息提示款
			swal({title : "很抱歉",text : "请选择阶段与类别后再进行上传！",type : "info"});
			$("input[name='file3']").val("");
			return;
		}
		var ts=this;
		if(this.files.length > 10){
			//消息提示款
			swal({title : "很抱歉",text : "上传文件总数不能超过10个！",type : "info"});
			$("input[name='file3']").val("");
			return;
		}
		for(var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size; 
			if(fileSize>1*1024*1024){
				//消息提示款
				swal({title : "很抱歉",text : "上传单位文档最大不能超过1M！",type : "info"});
				$("input[name='file3']").val("");
				return;
			}
			var fileName=this.files[i].name; 
			var suffixIndex=fileName.lastIndexOf(".");  
			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
//			suffix=="TXT"||suffix=="DOC"||suffix=="WPS"||suffix=="RTF"
//				||suffix=="HTML"||suffix=="PDF"||suffix=="DOCX"||suffix=="XLSX"||suffix=="XLS"
			
//			suffix=="BMP"||suffix=="JPG"||suffix=="JPEG"||suffix=="PNG"||suffix=="GIF"
			if(suffix=="PDF"){  
				uploadType=3;
			}else if(suffix=="JPG"||suffix=="PNG"){  
				uploadType=2;
			}else{
				//消息提示款
				swal({title : "很抱歉",text : "请上传正确格式!(图片支持JPG、PNG格式。文档支持PDF格式)",type : "info"});
				$("input[name='file3']").val("");
				return;
			}
		}
		//创建FormData对象
		var data = new FormData();
		//为FormData对象添加数据
		$.each($(this)[0].files, function(i, file) {
			data.append('upload_file', file);
		});
		var certNo=$("#certNo").val();//身份证
		var prejudicNo=$("#prejudicNo").val();//预审单号
		var applyInfoId=$("#applyInfoId").val();//申请id
		var code="applyInfo";//
		var stage=extendField1;//阶段
		var productionVl=extendField2;//文件名称命名规则
		var FileController;
		if(uploadType==2){//图片
			FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadImg?certNo='+certNo+'&prejudicNo='+prejudicNo+'&stage='+stage+'&productionVl='+productionVl;
			FileController=FileController+'&attachmentItemID='+applyInfoId+'&attachmentItem='+code;
		}else if(uploadType==3){//文档
			FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadDocument?certNo='+certNo+'&prejudicNo='+prejudicNo+'&stage='+stage+'&productionVl='+productionVl;
			FileController=FileController+'&attachmentItemID='+applyInfoId+'&attachmentItem='+code;
		}
		$.mask_fullscreen();//打开加载进度条
		$.ajax({url:FileController,
			data:data,
			type:'post',  
			dataType:'json',
			processData: false,
	        contentType: false,
			success:function(rs) {  
				if(rs.flag){
					//消息提示款
					swal({title : "恭喜您",text : rs.msg,type : "success"},function(){
						//显示上传成功图片
						if(null != rs.url && "" != rs.url){
							var serverPath = CommonUtils.getServerURL() + "/file";
							var attIds = rs.attrId.split(",");
							var attrName = rs.attrName.split(",");
							var imgURL = rs.url.split(",");
							var html='';
							for(var i = 0; i < attIds.length; i++){
								if(uploadType==2){//图片
									html=html+'<div class="file-box" name="img-div" style="width:120px;">'; 
								}else if(uploadType==3){//文档
									html=html+'<div class="file-box" name="document-div" style="width:120px;">'; 
								}
								html=html+'<div class="file" style="width:100px;">'; 
								html=html+'<div class="image" style="height:70px;">'; 
								if(uploadType==2){//图片
									html=html+'<img modal="zoomImg" class="img-responsive" src="'+serverPath + imgURL[i]+'" alt="">';
								}else if(uploadType==3){//文档
									html=html+'<img class="img-responsive" src="../img/document.png" alt="">';
								}
								html=html+'</div>'; 
								html=html+'<div class="file-name">';
								html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attrName[i]+'">'+attrName[i]+'</div>'; 
								html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
								html=html+'</div>'; 
								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
								html=html+'<input type="hidden" name="extendField1" value='+extendField1+'>';
								html=html+'<input type="hidden" name="extendField2" value='+extendField2+'>';
								html=html+'</div></div>'; 
							}
							$("#upload-div").append(html);
							$("[name='tooltip-div']").tooltip();//提示初始化
							$("input[name='file3']").val("");
							$.getScript("../js/boxImg.js");  //加载图片轮播js文件
							$.mask_close_all();//关闭加载进度条
						}
					});
				} else {
					$.mask_close_all();//关闭加载进度条
					//消息提示款
					swal({title : "很抱歉",text : rs.msg, type : "error"});
				}
			},
			error : errorFunc 
		});
	});
}