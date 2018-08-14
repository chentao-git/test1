$(function() {
	//加载效果
	mask_fullscreen();
	// 初始化table并刷新数据
	$('#tbAttachment').bootstrapTable(init);

	// 表单验证初始化
	initFormValid();
	
//    //初始化时间控件
//    initDateControl();

	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		// 刷新列表
		$("#tbAttachment").bootstrapTable('refresh', init);

	});
	// 添加按钮点击事件
	$("#btnAdd").click(function() {
		// 显示弹出框
		showModal(1);
	});

	// 批量删除按钮点击事件
	$("#btnDelete").click(function() {
		var rows = $("#tbAttachment").bootstrapTable('getSelections');
		if (rows.length <= 0) {
			swal("很抱歉", "请您勾选您要删除的记录！", "info");
		} else {
			var attachmentIds = new Array();
			for (var i = 0; i < rows.length; i++) {
				attachmentIds.push(rows[i].attachmentID);
			}
			// 批量删除附件信息
			deleteAttachment(attachmentIds);
		}
	});

	// 保存按钮点击事件
	$("#btnSave").click(function() {
		saveAtt();// 保存数据
	});
});
////初始化时间控件
//var initDateControl = function(){
//
//    var start = {
//		elem : "#start",
//		format : "YYYY-MM-DD",
//		min: '1900-01-01', //最小日期
//		max: '2099-12-31', //最大日期
//		istime : false,//是否显示时间选择
//		istoday : false,//是否显示今天
//		choose : function(datas) {
//			end.min = datas;
//			end.start = datas
//		}
//	};
//    var end = {
//		elem : "#end",
//		format : "YYYY-MM-DD",
//		min : laydate.now(),
//		max: '2099-12-31', //最大日期
//		istime : false,
//		istoday : false,
//		choose : function(datas) {
//			start.max = datas
//		}
//	};
//    //有效期开始时间
//    laydate(start);
//    //有效期结束时间
//    laydate(end);
//}

// 查询的参数
var queryParams = function(params) {
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		attachmentName : $("#attachmentName").val(),// 附件名称
		attachmentItemID : $("#attachmentItemID").val(),// 附件ID
		createDate:$("#start").val(),//有效期开始时间
		lastUpdateDate:$("#end").val(),//有效期结束时间
		attachmentItem:$("#attachmentItem").val()
	};
	return temp;
};

// 操作
var actionFormatter = function(value, row, index) {
	var result = "";
//	result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"
//			+ value
//			+ "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteAttachment('"
			+ value
			+ "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	return result;
}
// 模块类型
var attachmentItemFormatter = function(value) {
	var attachmentItem = "";
	if (value == "1") {
		attachmentItem = "模板一";
	} else if (value == "2") {
		attachmentItem = "模板二";
	} else if (value == "3") {
		attachmentItem = "模板三";
	}
	return attachmentItem;
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
	url : '/xxm/rest/attr/attrService/findAttList',
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
	uniqueId : "attachmentId",
	showToggle : true,
	cardView : false,
	detailView : false,
	columns : [ {
		checkbox : true
	}, {
		field : 'attachmentID',
		title : '操作',
		width : 100,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter,
	}, {
		field : 'attachmentName',
		title : '附件名称',
		sortable : true
	}, {
		field : 'attachmentSysName',
		title : '系统存放名称',
		sortable : true
	}, {
		field : 'attachmentItem',
		title : '模块',
		sortable : true,
		formatter:attachmentItemFormatter
	},{
		field : 'attachmentPath',
		title : '存放路径',
		sortable : true
	}, {
		field : 'createByName',
		title : '创建人',
		sortable : true
	}, {
		field : 'createDate',
		title : '创始时间',
		formatter : dataFormatter
		
	},{
		field : 'lastUpdateByName',
		title : '修改人',
		sortable : true
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
	var validate = $("#attachmentForm").validate(
			{
				rules : {
					attachmentName : "required",
					attachmentSysName : "required",
					attachmentItem:{
						required:true,
						min:1
						},
					attachmentItem:{
						required:true,
						min:1
						},
					attachmentItemID : "required",
					attachmentPath : "required",
					createBy : "required",
					lastUpdeteBy : "required",

				},
				messages : {
					attachmentName : e + "请输入附件名",
					attachmentSysName : e + "请输入系统存放名称",
					attachmentItem : e + "请选择模块",
					attachmentItemID : e + "请输入模块id",
					attachmentPath : e + "请输入存放路径",
					createBy : e + "请填写创建人",
					lastUpdeteBy : e + "请填写修改人",

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
var showModal = function(type, attachmentID) {
	// 重置表单
	$("#attachmentForm").get(0).reset();
	// 弹出模式窗口
	$('#attachmentModal').modal('show');

	// 添加
	if (type == 1) {
		$('#attachmentModal').find("h4").html("添加附件");
		// 显示区域
		$("#edit").show();

		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#attachmentForm").find(".has-error").removeClass("has-error");
	}

	// 修改
	if (type == 2) {
		$('#attachmentModal').find("h4").html("修改附件");
		// 显示区域
		$("#edit").show();
		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#attachmentForm").find(".has-error").removeClass("has-error");
		// 查询附件详细信息
		var attachmentInfo = findAttachmentInfo(attachmentID);
		// 填充表单数据
		CommonUtils.fillFormData("#attachmentForm",JSON.stringify(attachmentInfo));
		// 填充隐藏AttachmentID
		$("#attachmentForm").find("#attachmentID").val(attachmentID);
	}

	// 详情
	if (type == 3) {
		$('#attachmentModal').find("h4").html("附件详情");
		// 查询附件详细信息
		var attachmentInfo = findAttachmentInfo(attachmentID);
		// 填充表单数据
		CommonUtils.fillFormData("#detail", JSON.stringify(attachmentInfo));
		// 证件类型
		$("#detail").find("#attachmentItem").html(
				attachmentItemFormatter(attachmentInfo.attachmentItem));
		// 显示区域
		$("#edit").hide();
		$("#detail").show();
		// 保存按钮隐藏
		$("#btnSave").hide();
	}
	// 隐藏值，附件判断提交数据的类型
	$('#attachmentModal').find("#modalType").val(type);
}

// 保存附件
var saveAtt = function() {
	// 验证表单数据
	if (!$("#attachmentForm").valid()) {
		return;
	}
	// 获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#attachmentForm');
	// 添加或编辑类型变量
	var type = $('#attachmentModal').find("#modalType").val();
	var url = null;
	if (type == "1") {// 添加
		url = "/xxm/rest/attr/attrService/addAttachment";
	} else {// 修改
		url = "/xxm/rest/attr/attrService/updateAttachment";
		formData.attachmentID = $("#attachmentForm").find("#attachmentID").val();
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
					$("#attachmentModal").modal("hide");
					// 刷新列表
					$("#tbAttachment").bootstrapTable('refresh', init);

					// 重置表单
					$("#attachmentForm").get(0).reset();
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

// 删除附件
var deleteAttachment = function(attachmentIds) {
	// 删除数据模式窗口
	swal({
		title : "您确定要删除这些信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "删除",
		closeOnConfirm : false
	}, function() {
		// 记录删除附件的所有attachmentID
		var param = null;
		// 判断attachmentIds是不是数组类型
		if (attachmentIds instanceof Array) {
			param = {
				attachmentIds : attachmentIds
			};
		} else {
			// 定义一个数组
			var arr = new Array();
			arr.push(attachmentIds);
			param = {
				attachmentIds : arr
			};
		}
		$.ajax({
			url : '/xxm/rest/attr/attrService/deleteAttachment',
			data : JSON.stringify(param),
			type : 'post',
			async : false,// 同步请求
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
						// 刷新列表
						$("#tbAttachment").bootstrapTable('refresh', init);
					});
				}
			},
			error : errorFunc
		});
	})
}

// 查询附件详细信息
var findAttachmentInfo = function(attachmentID) {
	var attachmentInfo = null;
	$.ajax({
		url : '/xxm/rest/attr/attrService/findAttachment',
		data : JSON.stringify({
			attachmentID : attachmentID
		}),
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success : function(data) {
			if (null != data) {
				attachmentInfo = data;
			}
		},
		error : errorFunc
	});
	return attachmentInfo;
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}