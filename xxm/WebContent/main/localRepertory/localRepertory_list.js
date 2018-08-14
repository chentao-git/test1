$(function() {
	//加载效果
	mask_fullscreen();
	// 初始化table并刷新数据
	$('#tbLocalRepertory').bootstrapTable(init);
	// 初始化时间控件
	initDateControl();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		$("#tbLocalRepertory").bootstrapTable('refresh', init);
	});
});

//初始化时间控件
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
//时间格式化
var dataFormatter = function(value) {
	if (null != value && "" != value) {
		return value.replace(".0", "");
	}
	return "";
}
//操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += '<a class="J_menuItem" href="install/install_list.html?gpsId='+value+'">安装</a>';
	return result;
}
//查询的参数
var queryParams = function(params) {
	var gpsPlaceVL = CommonUtils.getUrlParam("gpsPlaceVL");
//	var installVO = {productSn : $("#productSn").val(),installDate : $("#installDate").val()};
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		gpsPlaceVL : gpsPlaceVL,
		gpsId : $("#gpsId").val(),
		model : $("#model").val(),
//		installVO:installVO,
		priductSN:$("#productSn").val(),
		startCreateDate : $("#start").val(),// 有效期开始时间
		endCreateDate : $("#end").val()
		// 有效期结束时间
	};
	return temp;
};
//bootstrapTable初始化参数
var init = {
	url :  '/xxm/rest/gpsInfo/gpsInfoService/findGpsInfoList',
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
	uniqueId : "gpsId",
	showToggle : true,
	cardView : false,
	detailView : false,
	columns : [{
		field : 'priductSN',
		title : 'SN',
		sortable : true
	}, {
		field : 'model',
		title : '型号',
		sortable : true
	}, {
		field : 'brand',
		title : '品牌',
		sortable : true
	},  {
		field : 'gpsPlaceName',
		title : '位置',
		sortable : true
	}, {
		field : 'costing',
		title : '采购价格',
		sortable : true
	}, {
		field : 'sellingPrice',
		title : '标准售价',
		sortable : true
	}, {
		field : 'createDate',
		title : '创建时间',
		formatter : dataFormatter
	}, {
		field : 'lastUpdateDate',
		title : '修改时间',
		formatter : dataFormatter
	},{
		field : 'gpsId',
		title : '操作',
		width : 100,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	}],
onLoadSuccess:function(data){
	$.getScript("../js/contabs.children.min.js");  //加载js文件
},
onLoadError:function(status, res){
	errorFunc(res,status);
}
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}