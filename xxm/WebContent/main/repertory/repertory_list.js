$(function() {
	
	// 初始化table并刷新数据
	$('#tbRepertory').bootstrapTable(init);
	// 初始化时间控件
	initDateControl();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		$("#tbRepertory").bootstrapTable('refresh', init);
	});
});

//初始化时间控件
var initDateControl = function() {
	laydate({
		elem : "#installDate",
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
//查询的参数
var queryParams = function(params) {
	var gpsPlaceVL = CommonUtils.getUrlParam("gpsPlaceVL");
	var installVO = {productSn : $("#productSn").val(),startInstallDate : $("#start").val(),endInstallDate : $("#end").val()};
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		gpsPlaceVL : gpsPlaceVL,
		gpsId : $("#gpsId").val(),
		model : $("#model").val(),
		installVO:installVO,
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
		field : 'applyVO.loanContractNo',
		title : '货款单号',
		sortable : true
	}, {
		field : 'baseLeaseInfoVO.plateNumber',
		title : '车牌号',
		sortable : true
	}, {
		field : 'baseLeaseInfoVO.frameNumber',
		title : '车架号',
		sortable : true
	}, {
		field : 'installVO.installBy',
		title : '安装人',
		sortable : true
	}, {
		field : 'installVO.installSite',
		title : '安装地点',
		sortable : true
	} ,{
		field : 'installVO.installCarSite',
		title : '安装位置',
		sortable : true
	},{
		field : 'installVO.installDate',
		title : '安装时间',
		sortable : true
	}],
	onLoadSuccess:function(data){
		//加载效果
		mask_fullscreen();
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