var mortgageInfoId = CommonUtils.getUrlParam("mortgageInfoId");
var isEdit = CommonUtils.getUrlParam("isEdit");
$(document).ready(function(){
	
//	抵(质)押物性质GUARANTEE_TYPE
	CommonUtils.findBaseDataOption("#guaranteeType","GUARANTEE_TYPE",1);
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
	CommonUtils.findBaseDataOption("#stepHull","STEP_HULL",1);
//	押品所有人证件类型LOAN_USAGE
	CommonUtils.findBaseDataOption("#loanUsage","LOAN_USAGE",1);
//	是否共有财产COMMUNITY_PROPERTY
	CommonUtils.findBaseDataOption("#communityProperty","COMMUNITY_PROPERTY",1);
	
	//时间控件初始化
	var a="#";
	$(".laydate-icon").each(function(){
		laydate({elem : a+$(this).attr("id"),event : "focus"});
	});
	
	//提交
	$("#submitBu").click(function(){
		supplementFu(1);//1：提交
	});
	//保存
	$("#saveBu").click(function(){
		supplementFu(0);//0：保存
	});
	
	if(mortgageInfoId!=null){//填充数据
		var data={mortgageInfoId:mortgageInfoId};
		$.ajax({
			url:"/xxm/rest/entering/enteringService/findMortgageInfo",
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
					CommonUtils.fillFormData("#mortgageInfo-tab",JSON.stringify(data));
					if(data.leaseInfoVO){//租赁物信息
						CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
					}
					if(isEdit=="0"){//详情
						$(":input").attr("disabled","disabled");
						$("#saveBu,#submitBu").hide();
						
						//响应信息
						var html='';
						var returnList=data.mortgageReturnInfoList;
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
							html=html+'<td colspan="4">无历史信息！</td>';
							html=html+'</tr>';
						}
						$("#returnTable").append(html);
					}else{
						$("#return-div").hide();
					}
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
			},
			error : errorFunc
		});
	}else{
		$("#return-div").hide();
	}
});
//信息提交
function supplementFu(operationType){
	var isPass =false;
	//获取表单元素所有数据集合
	var data=CommonUtils.getTagObj($("#mortgageInfo-tab :input[name]"));//放款信息
	var leaseInfoVO=CommonUtils.getTagObj($("#leaseInfo-tab :input[name]"));//车辆信息
	data.leaseInfoVO=leaseInfoVO;
	data.mortgageInfoStatus=operationType;
	$.ajax({
		url:"/xxm/rest/entering/enteringService/mortgageInfoEdit",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					isPass=true;
					closeMenuTab();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc
	});
	return isPass;
}
