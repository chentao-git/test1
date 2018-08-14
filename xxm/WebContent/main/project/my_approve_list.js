$(function () {
	
    //初始化table并刷新数据
    $('#tbDataSupplement').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	$.mask_fullscreen();
    	//刷新列表
    	$("#tbDataSupplement").bootstrapTable('refresh', init);
    });
    
});


//查询的参数
var queryParams = function (params) {
	var customerVO={baseCustomerName:$("#baseCustomerName").val()};
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        loanContractNo:$("#loanContractNo").val(),
        customerVO:customerVO
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    var customerVO=row.customerVO;
    var policyinfoReturnVO=row.policyinfoReturnVO;
    var src = "../main/project/project_data2.html?applyInfoId={0}&customerId={1}&taskDefinitionKey={2}&taskName={3}&taskId={4}&type={5}";
    var src1 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"view");
    var src2 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"signInfo");
    var src3 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"loanInfo");
    var src4 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"mortgageinfo");
    var src5 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"loanUpload");
    var src6 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"mortgageUpload");
    var src7 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"audit");//办理
    //签约
    if(row.applyInfoStatus == "9"){
    	result += '<a class="btn btn-xs red J_menuItem" href="'+src2+'" title="签约">签约 <span class="glyphicon glyphicon-edit"></span></a>';
    }
    //放款
    else if(row.applyInfoStatus == "16"){
    	result += '<a class="btn btn-xs red J_menuItem" href="'+src3+'" title="放款">放款 <span class="glyphicon glyphicon-edit"></span></a>';
    }
    //抵押登记
    else if(row.applyInfoStatus == "18"){
    	result += '<a class="btn btn-xs red J_menuItem" href="'+src4+'" title="抵押登记">抵押 <span class="glyphicon glyphicon-edit"></span></a>';
    }
    //补充放款影像
    else if(row.applyInfoStatus == "12"){
    	result += '<a class="btn btn-xs red J_menuItem" href="'+src5+'" title="补充放款影像">补充 <span class="glyphicon glyphicon-edit"></span></a>';
    }
    //补充抵押影像
    else if(row.applyInfoStatus == "13"){
    	result += '<a class="btn btn-xs red J_menuItem" href="'+src6+'" title="补充抵押影像">补充 <span class="glyphicon glyphicon-edit"></span></a>';
    }else{
    	//签收办理
    	if(null != row.taskAssignee && "" != row.taskAssignee){
	    	result += '<a class="btn btn-xs red J_menuItem" href="'+src7+'" title="办理">办理 <span class="glyphicon glyphicon-edit"></span></a>';
	    }else{
	    	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"claimTask('" + row.taskId + "','#tbDataSupplement')\" title='签收'>签收 <span class='glyphicon glyphicon-check'></span></a>";
	    }
    }
    result += '<a class="btn btn-xs red J_menuItem" href="'+src1+'">详情 <span class="glyphicon glyphicon-search"></span></a>';
    result += "<a class='btn btn-xs red' href='javascript:;' onclick=\"findAuditInfos('" + policyinfoReturnVO.proposalNo + "')\" title='日志'>日志 <span class='glyphicon glyphicon-list-alt'></span></a>";
    return result;
}

//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}

//当前节点渲染
var taskNameFormatter = function(value,row, index) {
	var name = '<a class="trace" href="javascript:;" taskId="'+row.taskId+'" pid="'+row.processInstanceId+'" pdid="'+row.processDefinitionId+'" title="点击查看流程图">'+row.taskName+'</a>';
    return name;
}

//流程状态
var taskStatusFormatter = function(value){
	return value ? "已挂起" : "正常";
}

//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/apply/applyService/findApplyInfoList', //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
    ajaxOptions: {
        async: true // 设置为异步
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
    uniqueId: "applyInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [ {
			 field:'applyInfoId',
             title: '操作',
             width: 180,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'loanContractNo',
			title: '贷款编号',
			sortable : true
		},{
			field: 'customerVO.baseCustomerName',
			title: '姓名',
			sortable : true
		},{
			field: 'customerVO.certTypeName',
			title: '证件类型',
			sortable: true
		},{
			field: 'customerVO.certNo',
			title: '证件号码',
			sortable : true
		},{
			field: 'productionName',
			title: '产品名字',
			sortable : true
		},{
			field: 'applyInfoStatusName',
			title: '当前阶段',
			sortable : true
		},{
			field: 'createDate',
			title: '申请时间',
			formatter: dataFormatter
		},{
			field: 'taskName',
			title: '当前步骤',
			formatter: taskNameFormatter
		}, {
			field: 'taskCreateTime',
			title: '创建任务时间'
		}, {
			field: 'suspended',
			title: '流程状态',
			formatter:taskStatusFormatter,
			visible: false,
		}, {
			field: 'version',
			title: '版本号',
			visible: false,
		}, {
			field: 'taskAssigneeName',
			title: '当前处理人'
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
		$.mask_close_all();
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

//查询申请单的审核结果通知日志
var findAuditInfos =function(proposalNo){
	var html="";
	$("#auditinfoTbody").empty();
	//弹出模式窗口
	$('#auditInfosModal').modal('show'); 
	if(proposalNo!=null&&proposalNo!=""){
		var param = {};
		param.proposalNo=proposalNo;
	    $.ajax({url:'/xxm/rest/apply/applyService/findAuditInfos',
	    	data:JSON.stringify(param),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data&&data.length>0){
	    			for(var i=0;i<data.length;i++){
	    				html=html+"<tr>";
	    				html=html+"<td>"+CommonUtils.auditTpyeName(data[i].auditTpye)+"</td>";
	    				html=html+"<td>"+(data[i].proposalNo==null?'':data[i].proposalNo)+"</td>";
	    				html=html+"<td>"+(data[i].underwriteResult=="00"?'审核通过':'审核不通过')+"</td>";
	    				html=html+"<td>"+(data[i].underwriteTimes==null?'':data[i].underwriteTimes)+"</td>";
	    				html=html+"<td>"+(data[i].errorMessage==null?'':data[i].errorMessage)+"</td>";
	    				html=html+"<td>"+(data[i].loanNo==null?'':data[i].loanNo)+"</td>";
	    				html=html+"<td>"+(data[i].applyNo==null?'':data[i].applyNo)+"</td>";
	//    				html=html+"<td>"+(data[i].createDate==null?'':data[i].createDate)+"</td>";
	    				html=html+"</tr>";
	    			}
	    			$("#auditinfoTbody").append(html);
	    		}
	    	},
	    	error : function() { 
	    		//消息提示款
				swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
	    	}  
	    });
	}else{
		html=html+"<tr>";
		html=html+"<td colspan='7' style='text-align:center;'>无历史信息！</td>";
		html=html+"</tr>";
		$("#auditinfoTbody").append(html);
	}
}


