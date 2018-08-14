var loanInfoId = CommonUtils.getUrlParam("loanInfoId");
var isEdit = CommonUtils.getUrlParam("isEdit");
$(document).ready(function(){
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
	
	if(loanInfoId!=null){//填充数据
		var data={loanInfoId:loanInfoId};
		$.ajax({
			url:"/xxm/rest/entering/enteringService/findLoaninfo",
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
					CommonUtils.fillFormData("#loanInfo-tab",JSON.stringify(data));
					if(data.customerVO){//客户信息
						CommonUtils.fillFormData("#customer-tab",JSON.stringify(data.customerVO));
					}
					if(data.leaseInfoVO){//租赁物信息
						CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
					}
					
					if(isEdit=="0"){//详情
						$(":input").attr("disabled","disabled");
						$("#saveBu,#submitBu").hide();
						//响应信息
						var returnList=data.loanReturnInfoList;
						var html='';
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
	var data=CommonUtils.getTagObj($("#loanInfo-tab :input[name]"));//放款信息
	var customerVO=CommonUtils.getTagObj($("#customer-tab :input[name]"));//客户信息
	data.customerVO=customerVO;
	var leaseInfoVO=CommonUtils.getTagObj($("#leaseInfo-tab :input[name]"));//车辆信息
	data.leaseInfoVO=leaseInfoVO;
	data.loaninfoStatus=operationType;
	$.ajax({
		url:"/xxm/rest/entering/enteringService/loanInfoEdit",
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
