$(function() {
	
	//聚焦事件
	$('#search1').focus(function(){
		$('#search2').show();
	})
	//失焦事件
    $('#search1').blur(function(){
		setTimeout(()=>{
			$('#search2').hide();
		},200)
	})
	//接收url传的参数 查询数据填充表单
	var applyInfoId = getUrlParam('applyInfoId');
	if(applyInfoId != null){
		var applyInfo = findApplyInfo(applyInfoId);
		$('#search1').val(applyInfo.loanContractNo);
		serach();
	}
	//点击事件
	$('#search2').click(function(){
		//清空上次数据
		$("#baseLeaseInfo-table label").text("无"); 
		$("#leaseInfoId").val("");
		//获取查询
		var searchs = $('#search1').val();
		if(searchs === ""){
            $('#search1').focus();
          
		}else{
			serach();
		}
	})

	function serach(){
		var searchs = $('#search1').val();
		var info = findLeaseInfo(searchs);
		//填充表单数据
		if(info != null){
			$("#leaseInfoId").val(info.baseLeaseInfoVO.leaseInfoId);
			if(info.baseLeaseInfoVO.plateNumber != null){
				$("#plateNumber").text(info.baseLeaseInfoVO.plateNumber);
			} if(info.baseLeaseInfoVO.frameNumber != null){
				$("#frameNumber").text(info.baseLeaseInfoVO.frameNumber);
			} if(info.baseLeaseInfoVO.engineModel != null){
				$("#engineModel").text(info.baseLeaseInfoVO.engineModel);
			} if(info.baseLeaseInfoVO.enginePower != null){
				$("#enginePower").text(info.baseLeaseInfoVO.enginePower);
			} if(info.baseLeaseInfoVO.carColor != null){
				$("#carColor").text(info.baseLeaseInfoVO.carColor);
			} if(info.baseLeaseInfoVO.brand != null){
				$("#brand").text(info.baseLeaseInfoVO.brand);
			} if(info.baseLeaseInfoVO.manufacturer != null){
				$("#manufacturer").text(info.baseLeaseInfoVO.manufacturer);
			}
		}
	}
		//聚焦事件
	$('#searchInput').focus(function(){
		$('#searchSN').show();
	})
	//失焦事件
    $('#searchInput').blur(function(){
		setTimeout(()=>{
			$('#searchSN').hide();
		},200)
	})
	//点击事件
	$('#searchSN').click(function(){
		//清空上次数据
		$("#gps-table label").text("无"); 
		var searchs = $('#searchInput').val();
		if(searchs === ""){
           $('#searchInput').focus();
		}else{
			var gpsInfo = findGpsInfo(searchs);
			if(gpsInfo === null){
				swal({title : "很抱歉",text : "产品序列号不存在",type : "error"});
			}else if(gpsInfo.gpsPlaceVL === "3"){
				swal({title : "很抱歉",text : "产品序列号错误",type : "error"});
			}else{
				//填充表单数据
				CommonUtils.fillFormData("#gps-table", JSON.stringify(gpsInfo));
				if(gpsInfo.providerVO.spName != null){
					$("#spName").text(gpsInfo.providerVO.spName);
				}
			}
			
			
		}
	})
	// 初始化table并刷新数据
//	$('#tbInstall').bootstrapTable(init);
//	$("input[name='applyVO.loanContractNo']").click(function() {
//		$("#tbInstall").bootstrapTable('refresh', init);
//		// 弹出模式窗口
//		$("#gpsInfoModal").modal('show');
//	});
//
//	// 获取单选选中id并填充数据
//	$("#btnSave").click(
//			function() {
//				var rows = $("#tbInstall").bootstrapTable('getSelections');
//				if (rows.length > 0) {
//					var gpsId = rows[0].gpsId;
//				}
//				findGpsInfos(gpsId);
//				var gpsInfo = findGpsInfos(gpsId);
//				// 填充表单数据
//				CommonUtils.fillFormData("#baseLeaseInfo-table", JSON.stringify(gpsInfo));
//				$("#gpsInfoModal").modal('hide');
//			});
//	 $(document).on("dblclick","#tbInstall tr",function(){
////		 	$(this).find("input[name='btSelectItem']").prop("checked","checked");
//		 	 $(this).find(":radio").prop("checked",true);
//		 	var gpsId = $(this).attr("data-uniqueid");
//			var gpsInfo = findGpsInfos(gpsId);
//			// 填充表单数据
//			CommonUtils.fillFormData("#baseLeaseInfo-table", JSON.stringify(gpsInfo));
//			$("#gpsInfoModal").modal('hide');
//	   
//	 });
	//接收url传的参数 查询数据填充表单
	var type = getUrlParam('gpsId');
	if(type != null){
		var gpsInfoInfo = findGpsInfoInfo(type);
		// 填充表单数据
		$("#searchInput").val(gpsInfoInfo.priductSN);
		CommonUtils.fillFormData("#gps-table", JSON.stringify(gpsInfoInfo));
		if(gpsInfoInfo.providerVO.spName != null){
			$("#spName").text(gpsInfoInfo.providerVO.spName);
		}
	}
	
});
//获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
//确定入仓事件
$("#btnAffirm").click(function(){
	// 验证表单数据
	if (!$("#form").valid()) {
		return;
	}
	// 获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#form');
	var leaseInfoId = $("#leaseInfoId").val();
	var loanContractNo =  $("#search1").val();
	var baseLeaseInfoVO = {leaseInfoId:leaseInfoId};
	var applyVO = {loanContractNo:loanContractNo};
	formData.baseLeaseInfoVO = baseLeaseInfoVO;
	formData.applyVO = applyVO; 
	// 请求后台
	$.ajax({
		url : '/xxm/rest/gpsInfo/gpsInfoService/addInstall',
		data : JSON.stringify(formData),
		type : 'post',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			if (data.result) {
				// 消息提示款
				swal({
					title : "恭喜您",
					text : data.msg,
					type : "success"
				});
			} else {
				// 消息提示款
				swal({
					title : "很抱歉",
					text : data.msg,
					type : "error"
				});
			}
		},
		error : errorFunc
	});
	
})

//var init = {
//	url : '/xxm/rest/gpsInfo/gpsInfoService/findGpsInfoList',
//	method : 'post',
//	toolbar : '#toolbar',
//	striped : true,
//	cache : false,
//	pagination : true,
//	sortable : true,
//	sortOrder : "asc",
//	sidePagination : "server",
//	pageNumber : 1,
//	pageSize : 10,
//	pageList : [ 10, 25, 50, 100 ],
//	search : false,
//	strictSearch : true,
//	showColumns : true,
//	showRefresh : true,
//	minimumCountColumns : 2,
//	clickToSelect : false,
//	// height: 500,
//	uniqueId : "gpsId",
//	showToggle : false,
//	cardView : false,
//	detailView : false,
//	columns : [ {
//		radio : true
//	}, {
//		field : 'applyVO.loanContractNo',
//		title : '货款单号',
//		sortable : true
//	}, {
//		field : 'baseLeaseInfoVO.plateNumber',
//		title : '车牌号',
//		sortable : true
//	}, {
//		field : 'baseLeaseInfoVO.frameNumber',
//		title : '车架号',
//		sortable : true
//	}, {
//		field : 'baseLeaseInfoVO.engineModel',
//		title : '引擎号',
//		sortable : true
//	}, {
//		field : 'baseLeaseInfoVO.enginePower',
//		title : '引擎功率',
//		sortable : true,
//	}, {
//		field : 'baseLeaseInfoVO.carColor',
//		title : '颜色',
//		sortable : true,
//	},  {
//		field : 'providerVO.spName',
//		title : 'SP商',
//		sortable : true
//	} ]
//}
// 填充单选选中数据
var findGpsInfos = function(gpsId) {
	var gpsInfo = null;
	$.ajax({
		url : '/xxm/rest/gpsInfo/gpsInfoService/findGpsInfo',
		data : JSON.stringify({
			gpsId : gpsId
		}),
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success : function(data) {
			if (null != data) {
				gpsInfo = data;
			}
		},
		error : errorFunc
	});
	return gpsInfo;
}

//根据sn号查询到gps信息
var findGpsInfo = function(searchs){
	var info = null;
	$.ajax({url:'/xxm/rest/gpsInfo/gpsInfoService/findGpsInfo',
		data:JSON.stringify({priductSN : searchs}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				info = data;
			}
		},
		error : errorFunc
	});
	return info;
}

//根据申请编号查询车辆信息
var findLeaseInfo = function(searchs){
	var info = null;
	$.ajax({url:'/xxm/rest/gpsInfo/gpsInfoService/findLeaseInfo',
		data:JSON.stringify({applyVO : {loanContractNo : searchs}}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				info = data;
			}
		},
		error : errorFunc 
	});
	return info;
}

// 根据id填充
var findGpsInfoInfo = function(type) {
	var gpsInfoInfo = null;
	if (type != null) {
		$.ajax({
			url : '/xxm/rest/gpsInfo/gpsInfoService/findGpsInfo',
			data : JSON.stringify({gpsId : type}),
			type : 'post',
			dataType : 'json',
			async : false,// 同步请求
			contentType : "application/json",
			success : function(data) {
				if (null != data) {
					gpsInfoInfo = data;
				}
			},
    		error : errorFunc
		});
		return gpsInfoInfo;
	}
}
//根据id查询
var findApplyInfo = function(applyInfoId) {
	var applyInfo = null;
	if (applyInfoId != null) {
		$.ajax({
			url : '/xxm/rest/apply/applyService/findApplyInfo',
			data : JSON.stringify({applyInfoId : applyInfoId}),
			type : 'post',
			dataType : 'json',
			async : false,// 同步请求
			contentType : "application/json",
			success : function(data) {
				if (null != data) {
					applyInfo = data;
				}
			},
    		error : errorFunc
		});
		return applyInfo;
	}
}