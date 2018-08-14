/**
 *提前还款
 */
$(function(){
	//时间控件初始化
	laydate({elem : "#clearingDate",event : "focus"});
	$("#btnSearch").click(function(){
		if($("#loanContractNo").val() == "" || $("#loanContractNo").val() == null){
			//swal({title : "很抱歉",text : "贷款编号不能为空，请输入贷款编号。",type : "info"});
			$("#clientForm").find("label").text("");
			$("#loanContractNo").val("");
			$("#normalPayInfo-table").find("input").val("");
			return;
		}
		var data = {loanContractNo : $("#loanContractNo").val()};
		//加载效果
		mask_fullscreen();
		//查询客户贷款信息
		$.ajax({ 
			url : '/xxm/rest/apply/applyService/findApplyInfo',
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',
			async:false,//同步请求
			contentType: "application/json",
			success:function(result) {
				if(null != result && "" != result ){
					//填充表单
					$("#baseCustomerName").text(result.customerVO.baseCustomerName);
					$("#mobileNo").text(result.customerVO.mobileNo);
					$("#certType").text(result.customerVO.certType);
					$("#certNo").text(result.customerVO.certNo);
					$("#applyInfoId").val(result.applyInfoId);
				}else{
					$("#clientForm").find("label").text("");
					$("#loanContractNo").val("");
					$("#normalPayInfo-table").find("input").val("");
				}
			},
			error : errorFunc
		});
	})
	//添加结清数据
	$("#btnClearing").click(function(){
		debugger;
		//获取表单元素所有数据集合
		var data=CommonUtils.getFormJson('#normalPayForm');
		alert(data);
		$.ajax({
			url : '/xxm/rest/clearing/clearingService/addClearing',
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data.result){
					//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						$("#clientForm").find("label").text("");
						$("#loanContractNo").val("");
						$("#normalPayInfo-table").find("input").val("");
						$("#clearingType").val("");
						window.location.href='clearing_list.html';
					});
				}else{
					//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
				}
			},
			error : errorFunc  
		});
	})
});
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}
