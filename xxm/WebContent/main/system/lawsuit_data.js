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
	var data={filedId:value};
	$.ajax({
		url:"/xxm/rest/lawsuit/lawsuitService/auditParticularsQuery",
		data:JSON.stringify(data),
		type:'post',
		ajaxOptions: {
	        async: false // 设置为异步
	    },
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data){
				data = stuatsFormatter(data);//格式化处理状态
				$("#loanNumber").html(data.loanNumber);
				CommonUtils.fillFormData("#dataForm",JSON.stringify(data));
				var datas = data.remark;
				
				var temp = ""
				for (var i = 0; i < datas.length; i++) {
					temp += "<tr>"
	                        +"<td>跟踪备注：</td>"
	                        +"<td colspan='6' ><label>"+datas[i]+"</label></td>"
	                        +"</tr>"
				}
				$("#add-Tracking").append(temp);
				
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : "数据未查到!!",type : "error"});
	   		}
		},
		error : errorFunc
	});	
	
};
//状态格式化
var stuatsFormatter = function (value){
	if(value.registerStatus == 1){
		value.registerStatus ="客户结清";
	}else if(value.registerStatus == 2){
		value.registerStatus ="客户罚款";
	}else if(value.registerStatus == 3) {
		value.registerStatus ="保证金交付";
	}
	return value;
}
//数据加载动画
function mask_fullscreen(){
	$.mask_fullscreen(500);
}