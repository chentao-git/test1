$(function(){
	
	//查询诉讼申请详情
	selectAudit();
	//数据加载控件
	mask_fullscreen();
})
//查询诉讼申请详情
function selectAudit (){
	var test = window.location.search; //取得整个地址栏
	var value = test.split("=")[1];
	var data={trailerId:value};
	$.ajax({
		url:"/xxm/rest/trailer/trailerService/detailsQueryInfo",
		data:JSON.stringify(data),
		type:'post',
		ajaxOptions: {
	        async: false // 设置为异步
	    },
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data){
				data.processingResults = statusfFormfinal(data.processingResults);//格式化状态
				data.trailerStatus = stuatsFormatter(data.trailerStatus);//格式化状态
				data.isRelationed = statusForm(data.isRelationed);//格式化状态
				data.isReport = statusForm(data.isReport);//格式化状态
				$("#loanNumber").html(data.applyVO.loanContractNo);
				data2 = data.leaseInfoVO;//车辆信息对象
				data3 = data.trailerFileVO;//上传人信息
				data3.uploadDate = statusDate(data3.uploadDate)
				CommonUtils.fillFormData("#dataForm",JSON.stringify(data));
				CommonUtils.fillFormData("#dataForm",JSON.stringify(data2));
				CommonUtils.fillFormData("#dataForm",JSON.stringify(data3));
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : "数据未查到!!",type : "error"});
	   		}
		},
		error : errorFunc
	});	
	
};
//时间格式化
var statusDate = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}
//是否报案状态格式化
var statusForm = function(value){
	if(value == 1){
		return "是";
	}
	return "否";
}
//处理结果状态格式化
var statusfFormfinal = function(value){
	if(value == 1){
		value ="客户结清";
	}else if(value == 2){
		value ="客户罚款";
	}else if(value == 3) {
		value ="保证金交付";
	}
	return value;
}
//状态格式化
var stuatsFormatter = function (value){
	if(value == 1){
		value ="申请中";
	}else if(value == 2){
		value ="已审核通过";
	}else if(value == 3) {
		value ="已入库";
	}else {
		value = "已完成"
	}
	return value;
}
//数据加载动画
function mask_fullscreen(){
	$.mask_fullscreen(500);
}