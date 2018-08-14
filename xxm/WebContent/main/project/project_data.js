var applyInfoId = CommonUtils.getUrlParam("applyInfoId");
var taskDefinitionKey = CommonUtils.getUrlParam("taskDefinitionKey");
var taskName = CommonUtils.getUrlParam("taskName");
var taskId = CommonUtils.getUrlParam("taskId");
var type = CommonUtils.getUrlParam("type");
//var customerId = CommonUtils.getUrlParam("customerId");
$(document).ready(function(){
	//附件阶段
	CommonUtils.findBaseDataOption("#category-big","ATTACHMENT_CLASSIFY",1);
	//附件阶段条件
	CommonUtils.findBaseDataOption("#stage-condition","ATTACHMENT_CLASSIFY",1);
	//项目审批拒绝原因
	CommonUtils.findBaseDataOption("#reject_reason","APPROVE_REJECT_REASON",1);
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	
	//克隆担保人
	$("[name='addSponsor']").click(function(){
		var count=$("span[name='order-span']").length;
		$("#sponsor-area").append($("#sponsor-area div[name='sponsor-div']:last").clone(true));
		//获取最后一个担保人div
		var $div=$("#sponsor-area div[name='sponsor-div']:last");
		//单选框的name不能相同
		$div.find("span[name='order-span']").html(count+1);//显示第几个担保人
		//单选复选初始化
	});
	
	
	//点击请求放款响应
	$("#loanInfoBu").click(function(){
		var loanInfoId=$("#loanInfoId").val();
		if(loanInfoId!=null){
			$.ajax({
				url:"/xxm/rest/apply/applyService/requestLoanInfo",
				data:JSON.stringify({loanInfoId:loanInfoId,applyInfoId:applyInfoId}),
				type:'post',  
				dataType:'json',  
				contentType: "application/json",
				success:function(data) {
					if(data.result){
						isPass=true;
						//消息提示款
						swal({title : "恭喜您",text : data.msg,type : "success"},function(){
							findLoanInfos(loanInfoId);
						});
			   		}else{
			   			//消息提示款
						swal({title : "很抱歉",text : data.msg,type : "error"});
			   		}
				},
				error : errorFunc  
			});
		}else{
			//消息提示款
			swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
		}
	});
	
	//点击请求车辆抵押响应信息
	$("#mortgageInfoBu").click(function(){
		var mortgageInfoId=$("#mortgageInfoId").val();
		if(mortgageInfoId!=null){
			$.ajax({
				url:"/xxm/rest/apply/applyService/requestMortgageInfo",
				data:JSON.stringify({mortgageInfoId:mortgageInfoId}),
				type:'post',  
				dataType:'json',  
				contentType: "application/json",
				success:function(data) {
					if(data.result){
						isPass=true;
						//消息提示款
						swal({title : "恭喜您",text : data.msg,type : "success"},function(){
							findMortgageReturnInfos(mortgageInfoId);
						});
			   		}else{
			   			//消息提示款
						swal({title : "很抱歉",text : data.msg,type : "error"});
			   		}
				},
				error : errorFunc 
			});
		}else{
			//消息提示款
			swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
		}
	});
	
	//点击获取签约响应
	$("#signInfoBu").click(function(){
		var applyInfoId=$("#applyInfoId").val();
		if(applyInfoId!=null){
			$.ajax({
				url:"/xxm/rest/apply/applyService/manualSignReturninfo",
				data:JSON.stringify({applyInfoId:applyInfoId}),
				type:'post',  
				dataType:'json',  
				contentType: "application/json",
				success:function(data) {
					if(data.result){
						isPass=true;
						//消息提示款
						swal({title : "恭喜您",text : data.msg,type : "success"},function(){
							findSignInfos(applyInfoId);
						});
			   		}else{
			   			//消息提示款
						swal({title : "很抱歉",text : data.msg,type : "error"});
			   		}
				},
				error : errorFunc
			});
		}else{
			//消息提示款
			swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
		}
	});
	
	//查询阶段下的类别
	$("#category-big").change(function(){
		var codeVal=$(this).val();
		var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
		CommonUtils.findBaseDataOption("#category-little","ATTACHMENT_CLASSIFY",2,vo.baseId);
	});
	//查询阶段下的类别“条件”
	$("#stage-condition").change(function(){
		var codeVal=$(this).val();
		if(codeVal!=null&&codeVal!=''){
			var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
			CommonUtils.findBaseDataOption("#category-condition","ATTACHMENT_CLASSIFY",2,vo.baseId);
		}else{
			$("#category-condition").empty();
		}
	});
	
	//附件条件查询按钮
	$("#conditionBu").click(function(){
		var stageCondition=$("#stage-condition").val();//阶段
		if(stageCondition!=null&&stageCondition!=''){
			$("input[name='extendField1']").each(function(){
				if($(this).val()!=stageCondition){
					$(this).parent().hide();
				}else{
					$(this).parent().show();
				}
			});
		}else{
			$(".file").show();
		}
		var categoryCondition=$("#category-condition").val();//类别
		var prev;
		if(categoryCondition!=null&&categoryCondition!=''){
			$("input[name='extendField2']").each(function(){
				prev=$(this).prev();
				if($(this).val()!=categoryCondition){
					$(this).parent().hide();
				}else if(prev==stageCondition){
					$(this).parent().show();
				}
			});
		}
		var attachmentType=$("#attachmentType-div input:checked").val();//附件类型
		if(attachmentType==2){//图片
			$("div[name='img-div']").show();
			$("div[name='document-div']").hide();
		}else if(attachmentType==3){//文档
			$("div[name='document-div']").show();
			$("div[name='img-div']").hide();
		}else{
			$("div[name='document-div']").show();
			$("div[name='img-div']").show();
		}
	});
	//附件条件重置按钮
	$("#resetBu").click(function(){
		$("#stage-condition option:first").prop("selected", 'selected');//阶段
		$("#category-condition option:first").prop("selected", 'selected');//类别
		$("#attachmentType-div input[value='']").prop("checked","checked");//附件类型
		//单选复选初始化
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	});
	
	if(applyInfoId!=null){//填充数据
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
					$("#productionVl").html(data.productionVl);
					$("#loanContractNo").html(data.loanContractNo);
					if(data.customerVO){//客户信息
						CommonUtils.fillFormData("#customer-base-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-spouse-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-account-tab",JSON.stringify(data.customerVO));
						var str =data.customerVO.marriageStatusName;
						if(null != str && str.indexOf("已婚") != -1){//婚姻状况
							$("#spouse-div").show();
						}else{
							$("#spouse-div").hide();
						}
					}
					if(data.supplementBaseVO){//补充基本信息
						CommonUtils.fillFormData("#supplement-base-tab",JSON.stringify(data.supplementBaseVO));
						CommonUtils.fillFormData("#supplement-default-tab",JSON.stringify(data.supplementBaseVO));
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
							//主要险种(多选)
							findBaseDataCheckbox("#majorInsuranceVLS-div","MAJOR_INSURANCE",1,"majorInsuranceVLS");
							var arr=carInsuranceVO.majorInsuranceVLS.split(',');
							var k;
							$("#majorInsuranceVLS-div input[name='majorInsuranceVLS']").each(function(){
								k=-1;
								for(var i=0;i<arr.length;i++){
									if(arr[i]==$(this).val()){
										k=2;
									}
								}
								if(k==-1){//代表没有
									$(this).parent().hide();
								}
							});
						}
					}
					if(data.guaranteeInsuranceVO){//保险信息
						CommonUtils.fillFormData("#guaranteeInsurance-tab",JSON.stringify(data.guaranteeInsuranceVO));
					}
					var sponsorList=data.sponsorList;//担保人
					if(sponsorList!=null&&sponsorList.length>0){
						for(var i=0;i<sponsorList.length;i++){
							if(i==0){
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
							}else{
								$("[name='addSponsor']").click();
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
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
//							html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
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
						var loanReturnInfoList=msLoanInfoVO.loanReturnInfoList;
						var html='';
						var loanInfoInformVO;
						if(loanReturnInfoList!=null&&loanReturnInfoList.length>0){
							for(var i=0;i<loanReturnInfoList.length;i++){
								html=html+'<tr>';
								html=html+'<td>'+(loanReturnInfoList[i].proposalno==null?'':loanReturnInfoList[i].proposalno)+'</td>';
								html=html+'<td>'+(loanReturnInfoList[i].resultMessage==null?'':loanReturnInfoList[i].resultMessage)+'</td>';
								html=html+'<td>'+(loanReturnInfoList[i].createByName==null?'':loanReturnInfoList[i].createByName)+'</td>';
								html=html+'<td>'+(loanReturnInfoList[i].createDate==null?'':loanReturnInfoList[i].createDate)+'</td>';
								loanInfoInformVO=loanReturnInfoList[i].loanInfoInformVO;
								if(loanInfoInformVO!=null&&loanInfoInformVO.proposalNo!=null&&loanInfoInformVO.proposalNo!=""){
//									html=html+'<td><button type="button" class="btn btn-primary btn-sm" onclick="\'$.findLoanInformInfo('+loanInfoInformVO.proposalNo+')\'">通知详情</button></td>';
									html=html+"<td><button type='button' class='btn btn-primary btn-sm' onclick=\"$.findLoanInformInfo('"+loanInfoInformVO.proposalNo+"')\">通知详情</button></td>";
								}else{
									html=html+'<td>等待通知</td>';
								}
								html=html+'</tr>';
							}
						}else{
							html=html+'<tr>';
							html=html+'<td colspan="4" style="text-align:center;">无历史信息！</td>';
							html=html+'</tr>';
						}
						$("#loanReturnTbody").append(html);
					}
					
					var msMortgageInfoVO=data.msMortgageInfoVO;
					if(msMortgageInfoVO){//车辆抵押登记信息
						CommonUtils.fillFormData("#mortgageInfo-tab",JSON.stringify(msMortgageInfoVO));
						//响应信息
						var html='';
						var returnList=msMortgageInfoVO.mortgageReturnInfoList;
						if(returnList!=null&&returnList.length>0){
							for(var i=0;i<returnList.length;i++){
								html=html+'<tr>';
								html=html+'<td>'+(returnList[i].proposalno==null?'':returnList[i].proposalno)+'</td>';
								html=html+'<td>'+(returnList[i].resultMessage==null?'':returnList[i].resultMessage)+'</td>';
								html=html+'<td>'+(returnList[i].createByName==null?'':returnList[i].createByName)+'</td>';
								html=html+'<td>'+(returnList[i].createDate==null?'':returnList[i].createDate)+'</td>';
								html=html+'</tr>';
							}
						}else{
							html=html+'<tr>';
							html=html+'<td colspan="4" style="text-align:center;">无历史信息！</td>';
							html=html+'</tr>';
						}
						$("#mortgageInfoReturnTbody").append(html);
					}
					
					if(type=='signInfo'){//签约
						$('#myTab li:eq(13) a').tab('show');//选项卡默认打开
						$("#mortgageInfoReturn-div,#loanReturn-div").hide();//车辆抵押响应、放款响应信息
						findSignInfos(applyInfoId);
					}else if(type=='loanInfo'){//放款
						$('#myTab li:eq(11) a').tab('show');//选项卡默认打开
						$("#mortgageInfoReturn-div").hide();//车辆抵押响应
						findSignInfos(applyInfoId);
					}else if(type=='mortgageinfo'){//车辆抵押
						$('#myTab li:eq(12) a').tab('show');//选项卡默认打开
						findSignInfos(applyInfoId);
					}else{
						$("#signInfo-li").hide();
					}
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
			},
			error : errorFunc 
		});
	}
	
	//审核意见按钮改变事件，ifCreated 事件应该在插件初始化之前绑定 
	$("#tab-9").find("[name=reject_result]:input").on('ifChecked', function(event){ 
		var val = $(this).val();
		if(val == "1"){
			$("#tab-9").find("#resonDiv").hide();
		}else{
			$("#tab-9").find("#resonDiv").show();
		}
	}); 
	
	//提交审核
	$("#tab-9").find("#btnSbumitAudit").click(function(){
		var checkedVal = "";
		$("#tab-9").find("[name=reject_result]:input").each(function(){
			if(true == $(this).is(':checked')){
				checkedVal = $(this).val();
			}
		});
		if(checkedVal == ""){
			//消息提示款
			swal({title : "很抱歉",text : "请选择审核意见！",type : "info"});
			return;
		}
		//同意
		if(checkedVal == "1"){
			auditAgree(applyInfoId,taskId,taskDefinitionKey,"#auditForm");
		}
		//驳回
		if(checkedVal == "3"){
			auditDisagree(applyInfoId,taskId,taskDefinitionKey,"#auditForm");
		}
		//拒绝
		if(checkedVal == "2"){
			
		}
	});
	
	//关闭审核
	$("#tab-9").find("#btnSubmitClose").click(function(){
		//关闭当前选项卡
		closeMenuTab();
	});
	
	//初始化审批信息内容
	initAuditInfo(applyInfoId,taskId);
	
	//详情隐藏审批内容
	if(null != type && type == "audit"){
		$("#tab-9").find("#auditDiv").show();
	}else{
		$("#tab-9").find("#auditDiv").hide();
	}
	
	$.findLoanInformInfo=function(no){
		findLoanNoticeInfo(no);
	}
	
});
//查询放款通知详细
function findLoanNoticeInfo(proposalNo){
	$("#detail").find("label[name]").html("");
	$('#loan-informModal').modal('show');
	$.ajax({url:'/xxm/rest/apply/applyService/findLoanNoticeInfo',
		   data:JSON.stringify({proposalNo:proposalNo}),
		   type:'post',
		   cache:false,  
		   async: false,
		   dataType:'json',  
		   contentType: "application/json",
		   success:function(result) {  
			   if(result){
				 //弹出模式窗口
				   CommonUtils.fillFormData("#detail",JSON.stringify(result));
			   }else{
				   errorFunc
			   }
		   },
		   error : errorFunc
	});
}

function findBaseDataCheckbox(Str,code,level,tagName){
	$(Str).empty();
	var data = {};
	data.baseCode=code;
	data.level=level;
	$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCode',
		   data:JSON.stringify(data),
		   type:'post',
		   cache:false,  
		   async: false,
		   dataType:'json',  
		   contentType: "application/json",
		   success:function(result) {  
				if(result!=null&&result.length>0){
					var html="";
					for(var i=0;i<result.length;i++){
						html=html+'<label class="checkbox-inline"><input type="hidden" name='+tagName+' value='+result[i].baseCodeValue+'>'+result[i].baseName+'、</label>';
					}								
					$(Str).append(html);
				}
		    },
		    error : errorFunc
	});
}
//签约状态查询
function findSignInfos(applyInfoId){
	$("#signInfoReturnTbody").empty();
	$.ajax({url:'/xxm/rest/apply/applyService/findSignInfos',
		   data:JSON.stringify({applyInfoId:applyInfoId}),
		   type:'post',
		   cache:false,  
		   async: false,
		   dataType:'json',  
		   contentType: "application/json",
		   success:function(result) {  
			    var html='';
				var returnList=result;
				var signReturnInfoVO;
				if(returnList!=null&&returnList.length>0){
					for(var i=0;i<returnList.length;i++){
						signReturnInfoVO=returnList[i].signReturnInfoVO;
						html=html+'<tr>';
						html=html+'<td>'+(signReturnInfoVO.applState==null?'':signReturnInfoVO.applState)+'</td>';
						html=html+'<td>'+(signReturnInfoVO.loanAmt==null?'':signReturnInfoVO.loanAmt)+'</td>';
						html=html+'<td>'+(signReturnInfoVO.intAdjPct==null?'':signReturnInfoVO.intAdjPct)+'</td>';
						html=html+'<td>'+(signReturnInfoVO.resultMessage==null?'':signReturnInfoVO.resultMessage)+'</td>';
						html=html+'<td>'+(signReturnInfoVO.createByName==null?'':signReturnInfoVO.createByName)+'</td>';
						html=html+'<td>'+(signReturnInfoVO.createDate==null?'':signReturnInfoVO.createDate)+'</td>';
						html=html+'</tr>';
					}
				}else{
					html=html+'<tr>';
					html=html+'<td colspan="6" style="text-align:center;">无历史信息！</td>';
					html=html+'</tr>';
				}
				$("#signInfoReturnTbody").append(html);
		    },
		    error : errorFunc
	});
}
//查询请求放款
function findLoanInfos(loanInfoId){
	$("#loanReturnTbody").empty();
	$.ajax({url:'/xxm/rest/apply/applyService/findLoanReturnInfos',
		   data:JSON.stringify({requestId:loanInfoId}),
		   type:'post',
		   cache:false,  
		   async: false,
		   dataType:'json',  
		   contentType: "application/json",
		   success:function(result) {  
			    var html='';
			    var loanInfoInformVO;
			    var loanReturnInfoList=result;
				if(loanReturnInfoList!=null&&loanReturnInfoList.length>0){
					for(var i=0;i<loanReturnInfoList.length;i++){
						html=html+'<tr>';
						html=html+'<td>'+(loanReturnInfoList[i].proposalno==null?'':loanReturnInfoList[i].proposalno)+'</td>';
						html=html+'<td>'+(loanReturnInfoList[i].resultMessage==null?'':loanReturnInfoList[i].resultMessage)+'</td>';
						html=html+'<td>'+(loanReturnInfoList[i].createByName==null?'':loanReturnInfoList[i].createByName)+'</td>';
						html=html+'<td>'+(loanReturnInfoList[i].createDate==null?'':loanReturnInfoList[i].createDate)+'</td>';
						loanInfoInformVO=loanReturnInfoList[i].loanInfoInformVO;
						if(loanInfoInformVO!=null&&loanInfoInformVO.proposalNo!=null&&loanInfoInformVO.proposalNo!=""){
							html=html+"<td><button type='button' class='btn btn-primary btn-sm' onclick=\"$.findLoanInformInfo('"+loanInfoInformVO.proposalNo+"')\">通知详情</button></td>";
						}else{
							html=html+'<td>等待通知</td>';
						}
						html=html+'</tr>';
					}
				}else{
					html=html+'<tr>';
					html=html+'<td colspan="4" style="text-align:center;">无历史信息！</td>';
					html=html+'</tr>';
				}
				$("#loanReturnTbody").append(html);
		    },
		    error : errorFunc
	});
}
//查询车辆抵押响应
function findMortgageReturnInfos(mortgageInfoId){
	$("#mortgageInfoReturnTbody").empty();
	$.ajax({url:'/xxm/rest/apply/applyService/findMortgageReturnInfos',
		   data:JSON.stringify({requestId:mortgageInfoId}),
		   type:'post',
		   cache:false,  
		   async: false,
		   dataType:'json',  
		   contentType: "application/json",
		   success:function(result) {  
			   //响应信息
				var html='';
				var returnList=result;
				if(returnList!=null&&returnList.length>0){
					for(var i=0;i<returnList.length;i++){
						html=html+'<tr>';
						html=html+'<td>'+(returnList[i].proposalno==null?'':returnList[i].proposalno)+'</td>';
						html=html+'<td>'+(returnList[i].resultMessage==null?'':returnList[i].resultMessage)+'</td>';
						html=html+'<td>'+(returnList[i].createByName==null?'':returnList[i].createByName)+'</td>';
						html=html+'<td>'+(returnList[i].createDate==null?'':returnList[i].createDate)+'</td>';
						html=html+'</tr>';
					}
				}else{
					html=html+'<tr>';
					html=html+'<td colspan="4" style="text-align:center;">无历史信息！</td>';
					html=html+'</tr>';
				}
				$("#mortgageInfoReturnTbody").append(html);
		    },
		    error : errorFunc
	});
}



