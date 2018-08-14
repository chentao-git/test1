$(function () {
	
	//获取页面类型
	pageType = CommonUtils.getUrlParam("pageType");
    //初始化table并刷新数据
	$('#tbActivity').bootstrapTable(init());
	
    //查询Button的点击事件
    $("#btnActivityQuery").click(function(){
    	//刷新列表
    	$("#tbActivity").bootstrapTable('refresh', init());
    });
    
    //添加按钮点击事件
    $("#btnActivityAdd").click(function(){
    	openTab(null);
    });
    
    //关闭选项卡
    $('.tab-close').on('click', function(ev) {
       var ev=window.event||ev;
       ev.stopPropagation();
       closeTab();//关闭选项卡
    });
    
    $("#tabList").click(function(){
    	$("#tab-2").hide();
    });
    
    $("#tabEdit").click(function(){
    	$("#tab-2").show();
    });
    
    $(".btnClose").click(function(){
    	closeTab();//关闭选项卡
    });
    
    //初始化富文本框
    $(".click2edit").summernote({
    	height: 200,
    	maxHeight:500,
		lang : "zh-CN",
		focus : true
	})
	
	//提交申请
    $("#btnAddSave").click(function(){
    	saveApplyLeave();//保存数据
    });
	
	//审批同意
    $("#btnAgree").click(function(){
    	leaveAgree();
    });
    
    //审批不同意
    $("#btnDisagree").click(function(){
    	leaveDisagree();
    });
    
    //重新提交申请
    $("#btnModifySave").click(function(){
    	leaveModifyApply(true);
    });
    
    //取消申请申请
    $("#btnModifyCancel").click(function(){
    	leaveModifyApply(false);
    });
    
    //销假申请
    $("#btnBackSubmit").click(function(){
    	leaveBackSubmit();
    });
    
    //设置时间控件
    initDateControl("#addStart","#addEnd");
    initDateControl("#modifyStart","#modifyEnd");
    initDateControl("#backStart","#backEnd");
    
});


//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset  //页码
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    if(pageType == "taskList"){
	    if(null != row.taskAssignee && "" != row.taskAssignee){
	    	result += "<a href='javascript:;' class='btn btn-xs red handle' taskDefinitionKey='"+row.taskDefinitionKey+"' taskName='"+row.taskName+"' leaveId="+row.id+" taskId="+row.taskId+ " title='办理'>办理<span class='fa fa-close'></span></a>";
	    }else{
	    	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"disableActivity('" + row.taskId + "',1)\" title='签收'>签收<span class='fa fa-check'></span></a>";
	    }
    }
    return result;
}

//当前节点渲染
var taskNameFormatter = function(value,row, index) {
	var name = '<a class="trace" href="javascript:;" pid="'+row.processInstanceId+'" pdid="'+row.processDefinitionId+'" title="点击查看流程图">'+row.taskName+'</a>';
    return name;
}

//流程状态
var taskStatusFormatter = function(value){
	return value ? "已挂起" : "正常";
}

//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}

var pageType;//页面类型
//表单初始化参数设置
var init = function(){
	var	pageURL = '/xxm/rest/leave/leaveService/'+ pageType;
	//bootstrapTable初始化参数
	var initParam = {
	    url: pageURL, //请求后台的URL（*）
	    method: 'post',                      //请求方式（*）
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
	    uniqueId: "id",                 //每一行的唯一标识，一般为主键列
	    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false,                   //是否显示父子表
		columns: [{
				checkbox: true
			}, {
				 field:'id',
	             title: '操作',
	             width: 120,
	             align: 'center',
	             valign: 'middle',
	             formatter: actionFormatter
			}, {
				field: 'leaveType',
				title: '假种'
			},{
				field: 'userId',
				title: '申请人'
			},{
				field: 'applyTime',
				title: '申请时间',
				formatter: dataFormatter
			},{
				field: 'startTime',
				title: '开始时间',
				formatter: dataFormatter
			},{
				field: 'endTime',
				title: '结束时间',
				formatter: dataFormatter
			},{
				field: 'taskName',
				title: '当前节点',
				formatter: taskNameFormatter
			}, {
				field: 'taskCreateTime',
				title: '创建任务时间'
			}, {
				field: 'suspended',
				title: '流程状态',
				formatter:taskStatusFormatter
			}, {
				field: 'version',
				title: '版本号'
			}, {
				field: 'taskAssignee',
				title: '当前处理人'
			}
		],
		onLoadSuccess:function(data){
			 // 跟踪
		    //$('.trace').click(graphTrace);
		    $(".handle").click(function(){
		    	//openLeaveDialog(this);
		    	openTab(this);
		    });
		}
	}
	return initParam;
}


//打开选项卡
var openTab = function(obj){
	var list = $("#tabList");
    var edit = $("#tabEdit");
    if(edit.is(':hidden')){//如果edit是隐藏的则显示edit元素
	　　	edit.show();　
		$(edit.find("a").attr('href')).show();
	}
    edit.addClass('active');
    $(edit.find("a").attr('href')).addClass('active');
    list.removeClass('active');
	$(list.find("a").attr('href')).removeClass('active');
	//任务参数
	var leaveId = $(obj).attr("leaveId");
	var taskId = $(obj).attr("taskId");
	var taskDefinitionKey = $(obj).attr("taskDefinitionKey");
	var taskName = $(obj).attr("taskName");
	//添加申请
	if(taskDefinitionKey == undefined){
		edit.find("span").html("新增申请");
		//重置表单
		$("#addApplyLeaveForm").get(0).reset();
		$("#addApplyLeaveForm").find(".click2edit").code("");
		//显示区域
		$("#addApplyLeave").show();
		$("#approvalApplyLeave").hide();
		$("#modifyApplyLeave").hide();
		$("#reportBackLeave").hide();
		$("#detail").hide();
	}
	//审批
	if(taskDefinitionKey == "deptLeaderAudit" || taskDefinitionKey == "hrAudit"){
		edit.find("span").html("审批["+taskName+"]");
		//重置表单
		$("#approvalApplyLeaveForm").get(0).reset();
		//显示区域
		$("#addApplyLeave").hide();
		$("#approvalApplyLeave").show();
		$("#modifyApplyLeave").hide();
		$("#reportBackLeave").hide();
		$("#detail").hide();
		//查询请假流程详情
		var data = findLeaveInfo(leaveId,taskId);
		//填充数据
		CommonUtils.fillFormData("#approvalApplyLeaveForm",JSON.stringify(data.leave));
	}
	//修改申请
	if(taskDefinitionKey == "modifyApply"){
		edit.find("span").html("重新申请["+taskName+"]");
		//重置表单
		$("#modifyApplyLeaveForm").get(0).reset();
		//显示区域
		$("#addApplyLeave").hide();
		$("#approvalApplyLeave").hide();
		$("#modifyApplyLeave").show();
		$("#reportBackLeave").hide();
		$("#detail").hide();
		var data = findLeaveInfo(leaveId,taskId);
		//填充数据
		CommonUtils.fillFormData("#modifyApplyLeaveForm",JSON.stringify(data.leave));
		$("#modifyApplyLeaveForm").find(".click2edit").code(data.leave.reason);
		//填充操作记录
		var variables = data.variables;
		if(null != variables){
			//消息
			var msg = "";
			//部门主管驳回原因
			if(!variables.deptLeaderPass){
				msg += "部门主管驳回申请，原因："+ variables.leaderBackReason;
			}
			//人事驳回原因
			if(!variables.hrPass){
				msg += "人事驳回申请，原因："+ variables.hrBackReason;
			}
			$("#modifyApplyLeaveForm").find("#recordDiv").html(msg);
		}
	}
	//销假
	if(taskDefinitionKey == "reportBack"){
		edit.find("span").html("销假["+taskName+"]");
		//重置表单
		$("#reportBackLeaveForm").get(0).reset();
		//显示区域
		$("#addApplyLeave").hide();
		$("#approvalApplyLeave").hide();
		$("#modifyApplyLeave").hide();
		$("#reportBackLeave").show();
		$("#detail").hide();
		var data = findLeaveInfo(leaveId,taskId);
		//填充数据
		CommonUtils.fillFormData("#reportBackLeaveForm",JSON.stringify(data.leave));
	}
	//详情
	if(taskDefinitionKey == 3){
		//edit.find("span").html("详情");
		//重置表单
		//$("#activityDetailsForm").get(0).reset();
		//$("#activityDetailsForm").find("img").html("");
		$("#processImageBorder").html("");
		//显示区域
		$("#modifyApplyLeave").hide();
		$("#detail").show();
	}
	//隐藏值，判断提交数据的类型
	$("#applyLeave #leaveId").val(leaveId);//流程Id
	$("#applyLeave #taskId").val(taskId);//任务id
	$("#applyLeave #taskDefinitionKey").val(taskDefinitionKey);//设置步骤key
}


//关闭选项卡
var closeTab = function(){
	var edit = $("#tabEdit");
    if(edit.hasClass('active')){
	    if(edit.index()==edit.length){
	        edit.prev().addClass('active');
	        $(edit.find("a").attr('href')).prev().addClass('active');
	    }else{
	        edit.next().addClass('active');
	        $(edit.find("a").attr('href')).next().addClass('active');
	    } 
	}
    edit.hide();
    $(edit.find("a").attr('href')).hide();
}

//保存
var saveApplyLeave=function(){
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#addApplyLeaveForm');
	//获取富文本域 详情值
	var activityDetailsHTML = $("#addApplyLeaveForm").find(".click2edit").code();
	formData.reason = activityDetailsHTML;
	//请求后台
	$.ajax({
		url:"/xxm/rest/leave/leaveService/start",
		data:JSON.stringify(formData),
		type:'post',  
		async:false,//同步请求
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//关闭选项卡
					closeTab();
					//刷新列表
			    	$("#tbActivity").bootstrapTable('refresh', init());
			    	//重置表单
					$("#addApplyLeaveForm").get(0).reset();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
};


//启用
var disableActivity =function(taskIds,type){
	//启用数据模式窗口
	swal({
		title : "您确定要签收这些信息吗",
		//text : "签收后其它用户将不能在进行签收！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "签收",
		closeOnConfirm : false
	}, function() {
		//操作处理
		operationActivity(taskIds,type);
	})
}

//操作（启用、禁用、删除）
var operationActivity = function(taskIds,type){
	//记录禁用的所有activityId
	var param = null;
	//判断activityIds是不是数组类型
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
	var uri = "";//请求路径
	if(type == 1){//签收
		url = '/xxm/rest/leave/leaveService/claim';
	}else if(type == 2){//启用
		url = '/xxm/rest/leave/leaveService/';
	}else if(type == 3){//删除
		url = '/baqsng/rest/activity/activityService/deleteActivity';
	}
    $.ajax({url:url,
    	data:JSON.stringify(param),
    	type:'post', 
    	async:false,//同步请求
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(data.result){
    			//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//关闭选项卡
					closeTab();
					//刷新列表
			    	$("#tbActivity").bootstrapTable('refresh', init());
				});
    		}
    	},
    	error : errorFunc  
    });
}

//查询详细信息
var findLeaveInfo=function(leaveId,taskId) {
	var leaveInfo = null;
	$.ajax({url:'/xxm/rest/leave/leaveService/getLeaveWithVars',
		data:JSON.stringify({id:leaveId,taskId:taskId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				leaveInfo = data;
			}
		},
		error : errorFunc
	});
	return leaveInfo;
}

//同意
var leaveAgree = function(){
	//任务Id
	var taskId = $("#applyLeave #taskId").val();
	//设置步骤key
	var taskDefinitionKey = $("#applyLeave #taskDefinitionKey").val();
	//审批原因
	var backReason = $("#approvalApplyLeaveForm #backReason").val();
	//部门主管审批同意
	if(taskDefinitionKey == "deptLeaderAudit"){
		// 设置流程变量
		complete(taskId, [{
			key: 'deptLeaderPass',
			value: true,
			type: 'B'
		}, {
			key: 'leaderBackReason',
			value: backReason,
			type: 'S'
		}]);
	}
	//人事审批同意
	else if(taskDefinitionKey == "hrAudit"){
		// 设置流程变量
		complete(taskId, [{
			key: 'hrPass',
			value: true,
			type: 'B'
		}, {
			key: 'hrBackReason',
			value: backReason,
			type: 'S'
		}]);
	}
}

//不同意 
var leaveDisagree = function(){
	//任务Id
	var taskId = $("#applyLeave #taskId").val();
	//设置步骤key
	var taskDefinitionKey = $("#applyLeave #taskDefinitionKey").val();
	//审批原因
	var backReason = $("#approvalApplyLeaveForm #backReason").val();
	if (backReason == '') {
		//消息提示款
		swal({title : "很抱歉",text : "请输入驳回理由！",type : "info"});
		return;
	}
	//部门主管审批不同意
	if(taskDefinitionKey == "deptLeaderAudit"){
		// 设置流程变量
		complete(taskId, [{
			key: 'deptLeaderPass',
			value: false,
			type: 'B'
		}, {
			key: 'leaderBackReason',
			value: backReason,
			type: 'S'
		}]);
	}
	//人事审批不同意
	else if(taskDefinitionKey == "hrAudit"){
		// 设置流程变量
		complete(taskId, [{
			key: 'hrPass',
			value: false,
			type: 'B'
		}, {
			key: 'hrBackReason',
			value: backReason,
			type: 'S'
		}]);
	}
	
}

//修改申请
var leaveModifyApply = function(reApply){
	var taskId = $("#applyLeave #taskId").val();
	// 提交的时候把变量
	complete(taskId, [{
		key: 'reApply',
		value: reApply,
		type: 'B'
	}, {
		key: 'leaveType',
		value: $('#modifyApplyLeaveForm #leaveType').val(),
		type: 'S'
	}, {
		key: 'startTime',
		value: $('#modifyApplyLeaveForm #modifyStart').val(),
		type: 'S'
	}, {
		key: 'endTime',
		value: $('#modifyApplyLeaveForm #modifyEnd').val(),
		type: 'S'
	}, {
		key: 'reason',
		value: $('#modifyApplyLeaveForm #reason').code(),
		type: 'S'
	}]);
}

//销假提交
var leaveBackSubmit = function(){
	var taskId = $("#applyLeave #taskId").val();
	complete(taskId, [{
		key: 'realityStartTime',
		value: $('#reportBackLeaveForm #backStart').val(),
		type: 'S'
	}, {
		key: 'realityEndTime',
		value: $('#reportBackLeaveForm #backEnd').val(),
		type: 'S'
	}]);
}

/**
 * 完成任务
 * @param {Object} taskId
 */
var complete = function(taskId, variables) {
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
    $.ajax({url:'/xxm/rest/leave/leaveService/complete',
		data:JSON.stringify({
			taskId:taskId,
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
					//关闭选项卡
					closeTab();
					//刷新列表
			    	$("#tbActivity").bootstrapTable('refresh', init());
				});
			}else{
				//消息提示款
				swal({title : "很抱歉",text :data.msg ,type : "error"});
			}
		},
		error : errorFunc 
	});
}

//初始化时间控件
var initDateControl = function(startId,endId){
    var start = {
		elem : startId,
		format : "YYYY-MM-DD",
		min: '1900-01-01', //最小日期
		max: '2099-12-31', //最大日期
		istime : false,//是否显示时间选择
		istoday : false,//是否显示今天
		choose : function(datas) {
			end.min = datas;
			end.start = datas
		}
	};
    var end = {
		elem : endId,
		format : "YYYY-MM-DD",
		min : laydate.now(),
		max: '2099-12-31', //最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			start.max = datas
		}
	};
    //有效期开始时间
    laydate(start);
    //有效期结束时间
    laydate(end);
}
