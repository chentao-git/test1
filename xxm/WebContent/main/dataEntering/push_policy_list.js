$(function () {
	
    //初始化table并刷新数据
    $('#tbPushPolicy').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbPushPolicy").bootstrapTable('refresh', init);
    });
    
});


//查询的参数
var queryParams = function (params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        serialNo:$("#serialNo").val(),//借据号
        policyNo:$("#policyNo").val(),//保单号
        proposalNo:$("#proposalNo").val()//投保单号
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    var customerVO=row.customerVO;
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(3,'" + value + "')\" title='详情'><span class='glyphicon glyphicon-search'></span></a>";
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
    url: '/xxm/rest/entering/enteringService/findPushPolicyList', //请求后台的URL（*）
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
    showRefresh: false,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: false,                //是否启用点击选中行
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "pushPolicyInfosId",                 //每一行的唯一标识，一般为主键列
    showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			 field:'pushPolicyInfosId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		},{
			field: 'serialNo',
			title: '序列号',
			sortable : true
		}, {
			field: 'policyNo',
			title: '保单号',
			sortable : true
		}, {
			field: 'proposalNo',
			title: '投保单号'
		},{
			field: 'policyUrl',
			title: '保单查询链接',
			sortable: true
		}
	],
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

//判断弹出框类型（修改、新增、详情）并显示
var showModal =function(type,pushPolicyInfosId){
	//重置表单
	$("#loanNoticeForm").get(0).reset();
	//弹出模式窗口
	$('#loanNoticeModal').modal('show'); 
	//详情
	if(type == 3){
		$('#loanNoticeModal').find("h4").html("审核通知结果详情");
		//查询用户详细信息
		findPushPolicyInfo(pushPolicyInfosId);
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#loanNoticeModal').find("#modalType").val(type);
}

//查询申请信息
function findPushPolicyInfo(pushPolicyInfosId){
	var data={pushPolicyInfosId:pushPolicyInfosId};
	$.ajax({
		url:"/xxm/rest/entering/enteringService/findPushPolicyInfo",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(null != data){
				CommonUtils.fillFormData("#detail",JSON.stringify(data));
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		onLoadError:function(status, res){
			errorFunc(res,status);
		}
	});
}