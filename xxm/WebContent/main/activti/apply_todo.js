/**
 * 流程任务办理
 */
$(function() {
    
});

//批量
var batchClaimTask = function(tableId){
	var rows= $(tableId).bootstrapTable('getSelections');  
    if(rows.length <=0 ){  
    	swal("很抱歉", "请您勾选您要签收的记录！","info");
    	return;
    }
	var taskIds = new Array();
	for(var i = 0; i < rows.length; i++){
		taskIds.push(rows[i].taskId);
	}
	//批量签收
	claimTask(taskIds,tableId);
}

//签收
var claimTask =function(taskIds,tableId){
	//启用数据模式窗口
	swal({
		title : "您确定要签收吗",
		//text : "签收后其它用户将不能在进行签收！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "签收",
		closeOnConfirm : false
	}, function() {
		//操作处理
		operationApply(taskIds,tableId);
	})
}
//操作
var operationApply = function(taskIds,tableId){
	var param = null;
	//判断taskIds是不是数组类型
	if(taskIds instanceof Array){
		param = {
			taskIds:taskIds
		};
	}else{
		//定义一个数组
		var arr = new Array();
		arr.push(taskIds);
		param = {
			taskIds:arr
		};
	}
    $.ajax({url:"/xxm/rest/piicflow/piicApplyService/claimTask",
    	data:JSON.stringify(param),
    	type:'post', 
    	async:false,//同步请求
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(data.result){
    			//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//刷新列表
			    	$(tableId).bootstrapTable('refresh', init);
				});
    		}
    	},
    	error : errorFunc 
    });
}

//通过
var auditAgree = function(applyInfoId,taskId,taskDefinitionKey,formId){
	//审批表单
	var form = CommonUtils.getFormJson(formId);
	var remark = $.trim(form.remark);//审批备注
	//文员审批同意
	if(taskDefinitionKey == "clerkAudit"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey, [{
			key: 'clerkAuditPass',
			value: true,
			type: 'B'
		}, {
			key: 'clerkAuditRemark',
			value: remark,
			type: 'S'
		}]);
	}
	//风控审批同意
	if(taskDefinitionKey == "windControlAudit" || taskDefinitionKey == "windControlAudit2" || taskDefinitionKey == "windControlAudit3"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey, [{
			key: taskDefinitionKey +'Pass',
			value: true,
			type: 'B'
		}, {
			key: taskDefinitionKey + 'Remark',
			value: remark,
			type: 'S'
		}]);
	}
	//风控主管审批同意
	if(taskDefinitionKey == "windControlSupervisorAudit"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey, [{
			key: 'windControlSupervisorAuditPass',
			value: true,
			type: 'B'
		}, {
			key: 'windControlSupervisorAuditRemark',
			value: remark,
			type: 'S'
		}]);
	}
}

//驳回
var auditDisagree = function(applyInfoId,taskId,taskDefinitionKey,formId){
	//审批表单
	var form = CommonUtils.getFormJson(formId);
	var reason = form.reason;//审批原因
	var remark = $.trim(form.remark);//审批备注
	if (null == reason || reason == '') {
		//消息提示款
		swal({title : "很抱歉",text : "请选择审批原因！",type : "info"});
		return;
	}
	if (null == remark || remark == '') {
		//消息提示款
		swal({title : "很抱歉",text : "请填写备注说明！",type : "info"});
		return;
	}
	//文员审批不同意
	if(taskDefinitionKey == "clerkAudit"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey,[{
			key: 'clerkAuditPass',
			value: false,
			type: 'B'
		}, {
			key: 'clerkAuditReason',
			value: reason,
			type: 'S'
		}, {
			key: 'clerkAuditRemark',
			value: remark,
			type: 'S'
		}]);
	}
	//风控员审批不同意
	if(taskDefinitionKey == "windControlAudit" || taskDefinitionKey == "windControlAudit2" || taskDefinitionKey == "windControlAudit3"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey, [{
			key: taskDefinitionKey + 'Pass',
			value: false,
			type: 'B'
		}, {
			key: taskDefinitionKey + 'Reason',
			value: reason,
			type: 'S'
		}, {
			key: taskDefinitionKey + 'Remark',
			value: remark,
			type: 'S'
		}]);
	}
	//风控主管审批不同意
	if(taskDefinitionKey == "windControlSupervisorAudit"){
		// 设置流程变量
		complete(applyInfoId,taskId,taskDefinitionKey, [{
			key: 'windControlSupervisorAuditPass',
			value: false,
			type: 'B'
		}, {
			key: 'windControlSupervisorAuditReason',
			value: reason,
			type: 'S'
		}, {
			key: 'windControlSupervisorAuditRemark',
			value: remark,
			type: 'S'
		}]);
	}
}

/**
 * 完成任务
 * @param {Object} taskId
 */
var complete = function(applyInfoId,taskId,taskDefinitionKey, variables) {
	// 转换JSON为字符串
    var keys = "", values = "", types = "";
	if (variables) {
		$.each(variables, function() {
			if (keys != "") {
				keys += ",";
				values += ",";
				types += ",";
			}
			keys += this.key;
			values += this.value;
			types += this.type;
		});
	}
	// 发送任务完成请求
    $.ajax({url:'/xxm/rest/piicflow/piicApplyService/completeTask',
		data:JSON.stringify({
			applyInfoId:applyInfoId,
			taskId:taskId,
			taskDefinitionKey:taskDefinitionKey,
	        keys: keys,
	        values: values,
	        types: types
	    }),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//关闭当前选项卡
					closeMenuTab();
				});
			}else{
				//消息提示款
				swal({title : "很抱歉",text :data.msg ,type : "error"});
			}
		},
		error : errorFunc 
	});
}

//查询流程详细信息
//var findApplyInfo=function(applyInfoId,taskId) {
//	var applyInfo = null;
//	$.ajax({url:'/xxm/rest/piicflow/piicApplyService/getApplyWithVars',
//		data:JSON.stringify({applyInfoId:applyInfoId,taskId:taskId}),
//		type:'post',  
//		dataType:'json',
//		async:false,//同步请求
//		contentType: "application/json",
//		success:function(data) {  
//			if(null != data){
//				applyInfo = data;
//			}
//		},
//		error : errorFunc
//	});
//	return applyInfo;
//}


////查询审批信息内容
//var initAuditInfo = function(applyInfoId,taskId){
//	//查询流程详细信息
//	var data = findApplyInfo(applyInfoId,taskId);
//	if(null == data || data.length <= 0){
//		return;
//	}
//	for(var i =0;i < data.length; i++){
//		var logDiv = $("#tab-9").find("#logDiv").clone();
//		logDiv.attr("id","logDiv"+i);//重新命名Id
//		var param = data[i];
//		var sign = true;
//		for(var key in param){
//			//处理人
//			if(key.indexOf("Handler")>=0){
//				logDiv.find("#handler").text((param[key] == null || param[key] =="null" )?"-":param[key]);
//			}
//			//处理时间
//			if(key.indexOf("ProcessingTime")>=0){
//				logDiv.find("#processingTime").text(param[key]);
//			}
//			//审核结果
//			if(key.indexOf("Pass")>=0){
//				logDiv.find("#pass").text(param[key]?"通过":"驳回");
//			}
//			//审核原因
//			if(key.indexOf("Reason")>=0){
//				logDiv.find("#reason").text("【备注】"+param[key]);
//			}
//			//审核描述
//			if(key.indexOf("Remark")>=0){
//				logDiv.find("#remark").text(param[key]);
//			}
//			//节点名称
//			if(sign){
//				var taskName = getTaskName(key);
//				logDiv.find("#taskName").text(taskName);
//				sign = false;
//			}
//		}
//		logDiv.show();//显示
//		$("#tab-9").find("#logDiv").before(logDiv);
//	}
//}


//获取任务名称
//var getTaskName = function(taskDefinitionKey){
//	if(taskDefinitionKey.indexOf("startApply") >= 0){
//		return "申请";
//	}
//	if(taskDefinitionKey.indexOf("bankAudit") >= 0){
//		return "银行预审";
//	}
//	if(taskDefinitionKey.indexOf("makeUpInfo")>= 0){
//		return "补充资料";
//	}
//	if(taskDefinitionKey.indexOf("clerkAudit")>= 0){
//		return "文员审核";
//	}
//	if(taskDefinitionKey.indexOf("windControlAudit")>= 0){
//		return "风控员审核";
//	}
//	if(taskDefinitionKey.indexOf("windControlSupervisorAudit")>= 0){
//		return "风控主管审核";
//	}
//	if(taskDefinitionKey.indexOf("insure")>= 0){
//		return "投保";
//	}
//	if(taskDefinitionKey.indexOf("startAuditResult")>= 0){
//		return "初审结果";
//	}
//	if(taskDefinitionKey.indexOf("endAuditResult")>= 0){
//		return "终审结果";
//	}
//	if(taskDefinitionKey.indexOf("signInfo")>= 0){
//		return "签约信息查询";
//	}
//	if(taskDefinitionKey.indexOf("loanApply")>= 0){
//		return "放款申请";
//	}
//	if(taskDefinitionKey.indexOf("loanNotice")>= 0){
//		return "放款通知";
//	}
//	if(taskDefinitionKey.indexOf("mortgage")>= 0){
//		return "补充抵押证明/GRS安装";
//	}
//	if(taskDefinitionKey.indexOf("receivePolicyNumber")>= 0){
//		return "接受保单号";
//	}
//}


//查询流程日志信息
var findFlowLogList=function(applyInfoId,taskId) {
	var applyInfo = null;
	$.ajax({url:'/xxm/rest/piicflow/piicApplyService/findFlowLogList',
		data:JSON.stringify({applyInfoId:applyInfoId,taskId:taskId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				applyInfo = data;
			}
		},
		error : errorFunc
	});
	return applyInfo;
}

//查询审批信息内容
var initAuditInfo = function(applyInfoId,taskId){
	//查询流程详细信息
	var data = findFlowLogList(applyInfoId,taskId);
	if(null == data || data.length <= 0){
		return;
	}
	for(var i =0;i < data.length; i++){
		var logDiv = $("#logDiv").clone();
		logDiv.attr("id","logDiv"+i);//重新命名Id
		var param = data[i];
		//处理人
		logDiv.find("#handler").text((param.userName == null || param.userName =="null" )?"-":param.userName);
		//处理时间
		logDiv.find("#processingTime").text(param.processingTime.replace(".0",""));
		//审核结果
		if(null != param.result && "" != param.result){
			logDiv.find("#pass").text(param.result=="true"?"通过":"驳回");
		}
		//审核原因
		if(null != param.reason && "" != param.reason){
			logDiv.find("#reasonRemark").append("<span id=reason>"+param.reason+"</span><br/>");
		}
		//审核描述
		if(null != param.remark && "" != param.remark){
			logDiv.find("#reasonRemark").append("<span><font style='font-weight:  bold;'>备注：</font>"+param.remark+"</span>");
		}
		//节点名称
		var taskName = getTaskName(param.taskDefinitionKey);
		logDiv.find("#taskName").text(taskName);
		logDiv.show();//显示
		$("#logDiv").before(logDiv);
	}
}

//获取任务名称
var getTaskName = function(taskDefinitionKey){
	if(taskDefinitionKey == "startApply"){
		return "申请";
	}
	if(taskDefinitionKey == "bankAudit"){
		return "银行预审";
	}
	if(taskDefinitionKey == "makeUpInfo"){
		return "补充资料";
	}
	if(taskDefinitionKey == "clerkAudit"){
		return "文员审核";
	}
	if(taskDefinitionKey == "windControlAudit" || taskDefinitionKey == "windControlAudit2" || taskDefinitionKey == "windControlAudit3"){
		return "风控员审核";
	}
	if(taskDefinitionKey == "windControlSupervisorAudit"){
		return "风控主管审核";
	}
	if(taskDefinitionKey == "insure"){
		return "投保";
	}
	if(taskDefinitionKey == "startAuditResult"){
		return "初审结果";
	}
	if(taskDefinitionKey == "endAuditResult"){
		return "终审结果";
	}
	if(taskDefinitionKey == "signInfo"){
		return "签约信息查询";
	}
	if(taskDefinitionKey == "loanApply"){
		return "放款申请";
	}
	if(taskDefinitionKey == "makeUpLoan"){
		return "补充放款影像";
	}
	if(taskDefinitionKey == "loanNotice"){
		return "放款通知";
	}
	if(taskDefinitionKey == "makeUpMortgage"){
		return "补充抵押影像";
	}
	if(taskDefinitionKey == "mortgage"){
		return "补充抵押证明/GPS安装";
	}
	if(taskDefinitionKey == "receivePolicyNumber"){
		return "接受保单号";
	}
}