$(function() {
	//加载效果
	mask_fullscreen();
	// 初始化table并刷新数据
	$('#tbGpsInfo').bootstrapTable(init);
	// 表单验证初始化
	initFormValid();
	// 初始化时间控件
	initDateControl();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		$("#tbGpsInfo").bootstrapTable('refresh', init);
	});
	// 添加按钮点击事件
	$("#btnAdd").click(function() {
		// 显示弹出框
		showModal(1);
	});
	// 批量删除按钮点击事件
	$("#btnDelete").click(function() {
		var rows = $("#tbGpsInfo").bootstrapTable('getSelections');
		if (rows.length <= 0) {
			swal("很抱歉", "请您勾选您要删除的记录！", "info");
		} else {
			var gpsIds = new Array();
			for (var i = 0; i < rows.length; i++) {
				gpsIds.push(rows[i].gpsId);
			}
			// 批量删除信息
			deleteGpsInfo(gpsIds);
		}
	});

	// 保存按钮点击事件
	$("#btnSave").click(function() {
		saveAtt();// 保存数据
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
	var gpsPlaceVL = CommonUtils.getUrlParam("gpsPlaceVL");
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		gpsPlaceVL : gpsPlaceVL,
		model : $("#model").val(),
		startCreateDate : $("#start").val(),// 有效期开始时间
		endCreateDate : $("#end").val()
	// 有效期结束时间
	};
	return temp;
};
// 操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"
			+ value
			+ "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteGpsInfo('"
			+ value
			+ "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";	
	return result;
}
// 时间格式化
var dataFormatter = function(value) {
	if (null != value && "" != value) {
		return value.replace(".0", "");
	}
	return "";
}
// bootstrapTable初始化参数
var init = {
	url : '/xxm/rest/gpsInfo/gpsInfoService/findGpsInfoList',
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
	columns : [ {
		checkbox : true
	}, {
		field : 'gpsId',
		title : '操作',
		width : 100,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	}, {
		field : 'model',
		title : '型号',
		sortable : true
	}, {
		field : 'brand',
		title : '品牌',
		sortable : true,
	}, {
		field : 'manufacturers',
		title : '厂家',
		sortable : true
	}, {
		field : 'remark',
		title : '其他说明',
		sortable : true
	}, {
		field : 'sellingPrice',
		title : '标准售价',
		sortable : true,
	}, {
		field : 'costing',
		title : '标准成本',
		sortable : true,
	}, {
		field : 'createDate',
		title : '创建时间',
		formatter : dataFormatter
	}, {
		field : 'lastUpdateDate',
		title : '修改时间',
		formatter : dataFormatter
	} ],
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}
// 表单验证初始化
var initFormValid = function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#gpsInfoForm").validate(
			{
				rules : {
					model : "required",
					brand : "required",
					manufacturers : "required",
					remark : "required",
					sellingPrice : "required",
					costing : "required",

				},
				messages : {
					model : e + "请输入型号",
					brand : e + "请输入品牌",
					manufacturers : e + "请输入厂家",
					remark : e + "请输入说明",
					sellingPrice : e + "请输入标准售价",
					costing : e + "请输入标准成本",

				},
				highlight : function(e) {
					if (e.type == "radio" || e.type == "checkbox") {
						$(e).parent().parent().parent().removeClass(
								"has-success").addClass("has-error");
					} else {
						$(e).parent().parent().removeClass("has-success")
								.addClass("has-error");
					}
				},
				success : function(e) {
					if (e.type == "radio" || e.type == "checkbox") {
						$(e).parent().parent().parent()
								.removeClass("has-error");// .addClass("has-success")
					} else {
						$(e).parent().parent().removeClass("has-error");// .addClass("has-success")
					}
				},
				errorElement : "span",
				errorPlacement : function(e, r) {
					if (r.is(":radio") || r.is(":checkbox")) {
						e.appendTo(r.parent().parent());
					} else {
						e.appendTo(r.parent());
					}
				},
				errorClass : "help-block m-b-none",
				validClass : "help-block m-b-none"
			});
	return validate;
}

// 判断弹出框类型（修改、新增、详情）并显示
var showModal = function(type, gpsId) {
	// 重置表单
	$("#gpsInfoForm").get(0).reset();
	// 弹出模式窗口
	$('#gpsInfoModal').modal('show');

	// 添加
	if (type == 1) {
		$('#gpsInfoModal').find("h4").html("添加gps信息");
		// 显示区域
		$("#edit").show();

		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#gpsInfoForm").find(".has-error").removeClass("has-error");
	}

	// 修改
	if (type == 2) {
		$('#gpsInfoModal').find("h4").html("修改gps信息");
		// 显示区域
		$("#edit").show();
		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#gpsInfoForm").find(".has-error").removeClass("has-error");
		// 查询gps型号详细信息
		var gpsInfoInfo = findGpsInfoInfo(gpsId);
		// 填充表单数据
		CommonUtils.fillFormData("#gpsInfoForm", JSON.stringify(gpsInfoInfo));
		// 填充隐藏gpsId
		$("#gpsInfoForm").find("#gpsId").val(gpsId);
	}
	// 详情
	if (type == 3) {
		$('#gpsInfoModal').find("h4").html("gps详情");
		// 查询gps型号详细信息
		var gpsInfoInfo = findGpsInfoInfo(gpsId);
		// 填充表单数据
		CommonUtils.fillFormData("#detail", JSON.stringify(gpsInfoInfo));
		// 显示区域
		$("#edit").hide();
		$("#detail").show();
		// 保存按钮隐藏
		$("#btnSave").hide();
	}
	// 隐藏值，gps型号信息判断提交数据的类型
	$('#gpsInfoModal').find("#modalType").val(type);
}
// 保存gps型号信息
var saveAtt = function() {
	// 验证表单数据
	if (!$("#gpsInfoForm").valid()) {
		return;
	}
	// 获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#gpsInfoForm');
	// 添加或编辑类型变量
	var type = $('#gpsInfoModal').find("#modalType").val();
	var url = null;
	if (type == "1") {// 添加
		url = "/xxm/rest/gpsInfo/gpsInfoService/addGpsInfo";
	} else {// 修改
		url = "/xxm/rest/gpsInfo/gpsInfoService/updateGpsInfo";
		formData.gpsId = $("#gpsInfoForm").find("#gpsId").val();
	}
	// 请求后台
	$.ajax({
		url : url,
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
				}, function() {
					// 关闭弹出框
					$("#gpsInfoModal").modal("hide");
					// 刷新列表
					$("#tbGpsInfo").bootstrapTable('refresh', init);

					// 重置表单
					$("#gpsInfoForm").get(0).reset();
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
};

// 删除gps型号信息
var deleteGpsInfo = function(gpsIds) {
	swal({
		title : "您确定要删除这些信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "删除",
		closeOnConfirm : false
	}, function() {
		// 记录删除资料所有的gpsIds
		var param = null;
		// 判断gpsIds是否是数组
		if (gpsIds instanceof Array) {
			param = {
				gpsIds : gpsIds
			};
		} else {
			// 定义一个数组
			var arr = new Array();
			arr.push(gpsIds);
			param = {
				gpsIds : arr
			};
		}
		$.ajax({
			url : '/xxm/rest/gpsInfo/gpsInfoService/deleteGpsInfo',
			data : JSON.stringify(param),
			type : 'post',
			async : false,// 同步请求
			dataType : 'json',
			contentType : "application/json",
			success : function(data) {
				if (data.result) {
					// 消息提示款
					swal({
						title : "恭喜您删除成功",
						text : data.msg,
						type : "success"
					}, function() {
						// 刷新列表
						$("#tbGpsInfo").bootstrapTable('refresh', init);
					});
				}
			},
    		error : errorFunc
		});
	})
}
// 查询gps型号详细信息
var findGpsInfoInfo = function(gpsId) {
	var gpsInfoInfo = null;
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
				gpsInfoInfo = data;
			}
		},
		error : errorFunc
	});
	return gpsInfoInfo;
}
//加载效果
function mask_fullscreen(){

	$.mask_fullscreen(1000);

}
