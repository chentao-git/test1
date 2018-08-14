$(function () {
	
    //初始化table并刷新数据
    $('#tbDataSupplement').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbDataSupplement").bootstrapTable('refresh', init);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#tbDataSupplement").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var applyInfoIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		applyInfoIds.push(rows[i].applyInfoId);
        	}
        	//批量删除申请信息
        	deleteApplyInfo(applyInfoIds);
        }
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
    var rejudicationReturnVO=row.rejudicationReturnVO;
    var src = "../main/apply/data_supplement.html?applyInfoId={0}&customerId={1}&taskId={2}&taskDefinitionKey={3}";
	src = src.format(value,customerVO.customerId,row.taskId,row.taskDefinitionKey);
    result += '<a class="btn btn-xs red J_menuItem" href="'+src+'"><span class="glyphicon glyphicon-edit">办理</span></a>';
//    result += "<a class='btn btn-xs red' href='javascript:;' onclick=\"deleteApplyInfo('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'>删除</span></a>";
    result += "<a class='btn btn-xs red' href='javascript:;' onclick=\"findAuditInfos('" + rejudicationReturnVO.prejudicNo + "')\" title='日志'>预审日志</a>";
    return result;
}


//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}

//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/apply/applyService/findMakeUpApplyInfoList', //请求后台的URL（*）
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
    uniqueId: "applyInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [ {
			 field:'applyInfoId',
             title: '操作',
             width: 140,
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
		}, {
			field: 'createDate',
			title: '申请时间',
			formatter: dataFormatter
		}, {
			field: 'applyInfoStatusName',
			title: '状态',
			sortable : true
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	}
}

//删除申请信息
var deleteApplyInfo =function(applyInfoIds){
	//删除数据模式窗口
	swal({
		title : "您确定要删除这些申请信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "删除",
		closeOnConfirm : false
	}, function() {
		//记录删除申请信息的所有applyInfoId
		var param = null;
		//判断applyInfoIds是不是数组类型
		if(applyInfoIds instanceof Array){
			param = {
				applyInfoIds:applyInfoIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(applyInfoIds);
			param = {
				applyInfoIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/apply/applyService/deleteApplyInfo',
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
				    	$("#tbDataSupplement").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc  
	    });
	})
}

//查询申请单的审核结果通知日志
var findAuditInfos =function(prejudicNo){
	$("#auditinfoTbody").empty();
	//弹出模式窗口
	$('#auditInfosModal').modal('show'); 
	var param = {};
	param.prejudicNo=prejudicNo;
    $.ajax({url:'/xxm/rest/apply/applyService/findAuditInfos',
    	data:JSON.stringify(param),
    	type:'post', 
    	async:false,//同步请求
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(data&&data.length>0){
    			var html="";
    			for(var i=0;i<data.length;i++){
    				html=html+"<tr>";
    				html=html+"<td>"+CommonUtils.auditTpyeName(data[i].auditTpye)+"</td>";
    				html=html+"<td>"+(data[i].prejudicNo==null?'':data[i].prejudicNo)+"</td>";
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
}

