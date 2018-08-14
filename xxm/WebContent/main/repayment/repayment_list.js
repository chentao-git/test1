$(function(){
	//加载效果
	mask_fullscreen();
	// 初始化table并刷新数据
	$('#tbRepayment').bootstrapTable(init);
	// 初始化时间控件
	initDateControl();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		$("#tbRepayment").bootstrapTable('refresh', init);
	});
	
});
// 初始化时间控件
var initDateControl = function() {
	// 角色有效期
	laydate({
		elem : "#createDate",
		event : "focus"
	});
	var start = {
		elem : "#start",
		format : "YYYY-MM-DD",
		min : '1900-01-01', // 最小日期	
		max : '2099-12-31', // 最大日期
		istime : false,// 是否显示时间选择
		istoday : false,// 是否显示今天
		choose : function(datas) {
			end.min = datas;
			end.start = datas
		}
	};
	var end = {
		elem : "#end",
		format : "YYYY-MM-DD",
		min : laydate.now(),
		max : '2099-12-31', // 最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			start.max = datas
		}
	};
	// 有效期开始时间
	laydate(start);
	// 有效期结束时间
	laydate(end);
}
// 查询的参数
var queryParams = function(params) {
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		applyInfoStatus : $("#applyInfoStatus").val(),//还款状态
		loanContractNo : $("#loanContractNo").val(),//贷款编号
		baseCustomerName : $("#baseCustomerName").val(),//客户姓名
		startCreateDate : $("#start").val(),// 有效期开始时间
		endCreateDate : $("#end").val()				// 有效期结束时间
	};
	return temp;
};
//状态类型
var statusFormatter =function(value){
	var status="";
	if(value=="0"){
		status="请选择";
	}else if(value=="1"){
		status="归档完成";
	}else if(value=="2"){
		status="合同已上传";
	}else if(value=="3"){
		status="还款中";
	}else if(value=="4"){
		status="已还清款项并结单";
	}else if(value=="5"){
		status="已放款待归单";
	}
	return status;
}
//操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"
			+ value
			+ "')\" title='还款计划'>还款计划</a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='详情'>详情</a>";
	return result;
}
// bootstrapTable初始化参数
var init = {
	url : '/xxm/rest/repayment/repaymentService/findRepaymentPlanList',
	method : 'post',
	toolbar : '#toolbar',
	striped : true,
	cache : false,
	pagination : true,
	sortable : true,
	sortOrder : "asc",
	queryParams : queryParams,
	sidePagination : "server",
	pageNumber : 1,
	pageSize : 10,
	pageList : [ 10, 25, 50, 100 ],
	search : false,
	strictSearch : true,
	showColumns : true,
	showRefresh : true,
	minimumCountColumns : 2,
	clickToSelect : false,
	// height: 500,
	uniqueId : "repaymentPlanId",
	showToggle : true,
	cardView : false,
	detailView : false,
	columns : [ {
		checkbox : true
	}, {
		field : 'loanContractNo',
		title : '贷款编号',
		sortable : true
	}, {
		field : 'baseCustomerName',
		title : '客户姓名',
		sortable : true
	}, {
		field : 'productionVl',
		title : '贷款数额',
		sortable : true
	}, {
		field : 'certType',
		title : '证件类型',
		sortable : true
	}, {
		field : 'certNo',
		title : '证件号码',
		sortable : true
	}, {
		field : 'createDate',
		title : '申请时间',
		sortable : true
	}, {
		field : 'applyInfoStatus',
		title : '当前状态',
		sortable : true,
		formatter : statusFormatter
	}, {
		field : 'repaymentPlanId',
		title : '操作',
		width : 100,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	} ],
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}