/**
 *贷款结清 
 */
$(function(){
	//加载效果
	mask_fullscreen();
	// 初始化table并刷新数据
	$('#tbClearing').bootstrapTable(init);
	// 查询Button的点击事件
	$("#btnClearing").click(function() {
		//加载效果
		mask_fullscreen();
		$("#tbClearing").bootstrapTable('refresh', init);
	});
});

// 查询的参数
var queryParams = function(params) {
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		clearingType : $("#clearingType").val(), //还款状态
		loanContractNo : $("#loanContractNo").val(), //贷款编号
		baseCustomerName : $("#baseCustomerName").val() //客户姓名
	};
	return temp;
};
//状态类型
var clearingTypeFormatter =function(value){
	var clearingType="";
	if(value=="0"){
		clearingType="请选择";
	}else if(value=="1"){
		clearingType="正常结清";
	}else if(value=="2"){
		clearingType="提前还款";
	}else if(value=="3"){
		clearingType="不良贷款追回";
	}else if(value=="4"){
		clearingType="抵押物拍卖";
	}else if(value=="5"){
		clearingType="贷款保险理赔";
	}else if(value=="6"){
		clearingType="不良资产出售";
	}else if(value=="7"){
		clearingType="不良贷款结清";
	}
	return clearingType;
}
//操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='详情'>详情</a>";
	return result;
}
// bootstrapTable初始化参数
var init = {
	url : '/xxm/rest/clearing/clearingService/findClearingList',
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
	uniqueId : "clearingId",
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
		field : 'productionName',
		title : '产品名称',
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
		field : 'salesman',//业务员
		title : '申请人',
		sortable : true
	}, {
		field : 'clearingType',
		title : '结清原因',
		sortable : true,
		formatter : clearingTypeFormatter
	},{
		field : 'clearingPerson',//经手人
		title : '操作人',
		sortable : true
	},{
		field : 'clearingId',
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
//详情
var showModal = function(value){
	swal({title : "很抱歉",text : "暂未开放详情功能，敬请期待！",type : "info"});
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}