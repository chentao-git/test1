$(function() {

	$("#btnSave1").click(function(){
		var actIdGroupVO = $("#actIdGroupID").val();
		var rowsRightGP= $("#tbPacket").bootstrapTable('getData'); 
		var gPUserIds = new Array();
    	for(var i = 0; i < rowsRightGP.length; i++){
    		gPUserIds.push(rowsRightGP[i].userId);
    	}
    	//deleteMembership(actIdGroupVO);
		addMembership(gPUserIds, actIdGroupVO);
	});
	
	//把左边选中的数据，不重复的移到右边
	$("#btnMove").click(function(){
		var rowsLeft= $("#tbPermission").bootstrapTable('getSelections'); 
		move(rowsLeft);
	});
	
	//移除右表选中数据
	$("#btnRemove").click(function(){
		var rowsRights= $("#tbPacket").bootstrapTable('getSelections'); 
		if(rowsRights.length <=0 ){  
	        swal("很抱歉", "请您勾选您要删除的记录！","info");
	        return;
	    }
		
		var rowsRightUserIds = new Array()
		for(var j = 0 ; j < rowsRights.length ; j++){
			rowsRightUserIds[j] = rowsRights[j].userId;
    	}
		$('#tbPacket').bootstrapTable('remove', {  
            field: 'userId',  
            values: rowsRightUserIds  
        });  
	});
	
	//加载效果
	mask_fullscreen();
	// 初始化并刷新
	$('#tbActIdGroup').bootstrapTable(init);
	$('#tbPermission').bootstrapTable(initUser);
	$('#tbPacket').bootstrapTable(initPpacket);
	
	// 表单验证初始化
	initFormValid();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		//加载效果
		mask_fullscreen();
		// 刷新列表
		$("#tbActIdGroup").bootstrapTable('refresh', init);
	});

	// 添加按钮点击事件
	$("#btnAdd").click(function() {
		// 显示弹出框
		showModal(1);
	});

	// 批量删除按钮点击事件
	$("#btnDelete").click(function() {
		var rows = $("#tbActIdGroup").bootstrapTable('getSelections');
		if (rows.length <= 0) {
			swal("很抱歉", "请您勾选您要删除的记录！", "info");
		} else {
			var actIdGroupIDs = new Array();
			for (var i = 0; i < rows.length; i++) {
				actIdGroupIDs.push(rows[i].actIdGroupID);
			}
			// 批量删除权限组信息
			deleteActIdGroup(actIdGroupIDs);
		}
	});

	// 保存按钮点击事件
	$("#btnSave").click(function() {
		saveActIdGroup();// 保存数据
	});
    //查询Button的点击事件
    $("#btnSelect").click(function(){
    	//刷新列表
    	$("#tbPermission").bootstrapTable('refresh', initUser);
    });

});


var move = function(rowsLeft){
	if(rowsLeft.length <=0 ){  
        swal("很抱歉", "请您勾选您要增加的记录！","info");
        return;
    }
	var rowsRight= $("#tbPacket").bootstrapTable('getData'); 
//	$('#tbPacket').bootstrapTable('append', rowsLeft); //prepend的方法必须定义所有字段，但是append不必  
	var rightUserIds = new Array(rowsRight.length);
	//获取到ID存入rightUserIds并用于判断
	for(var j = 0 ; j < rowsRight.length ; j++){
		rightUserIds[j] = rowsRight[j].userId;
	}
	//判断是否有重复数据
    for(var a =0;a<rowsLeft.length;a++){
    	if(rightUserIds.indexOf(rowsLeft[a].userId) == -1){
    		$('#tbPacket').bootstrapTable('append', rowsLeft[a]);
    		
    	}
    }
}

// 查询的参数
var queryParams = function(params) {
	var temp = {
		limit : params.limit,// 页码大小
		offset : params.offset,// 页码
		actIdGroupNAME : $("#actIdGroupNAME").val(),
		actIdGroupTYPE:$("#actIdGroupTYPE").val()
	};
	return temp;
};
//查询的参数
var queryUsers = function(queryUser) {
	var temp = {
		limit : queryUser.limit,// 页码大小
		offset : queryUser.offset,// 页码
		name : $("#name").val()
	};
	return temp;
};

// 操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"
			+ value
			+ "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteActIdGroup('"
			+ value
			+ "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(4,'"
			+ value + "')\" title='权限'><span class='glyphicon glyphicon-user'></span></a>";

	return result;
}

// 初始化参数
var init = {
	url : '/xxm/rest/actIdGroup/actIdGroupService/findActIdGroupList', // 请求后台的URL（*）
	method : 'post', // 请求方式（*）
	toolbar : '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	queryParams : queryParams, // 传递参数（*）
	sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
	search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	strictSearch : true,
	showColumns : true, // 是否显示所有的列
	showRefresh : false, // 是否显示刷新按钮
	minimumCountColumns : 2, // 最少允许的列数
	clickToSelect : false, // 是否启用点击选中行
	// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	uniqueId : "actIdGroupID", // 每一行的唯一标识，一般为主键列
	showToggle : false, // 是否显示详细视图和列表视图的切换按钮
	cardView : false, // 是否显示详细视图
	detailView : false, // 是否显示父子表
	columns : [ {
		checkbox : true
	}, {
		field : 'actIdGroupID',
		title : '操作',
		width : 150,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	}, {
		field : 'actIdGroupID',
		title : '权限组ID',
		sortable : true
	}, {
		field : 'actIdGroupNAME',
		title : '权限组名称',
		sortable : true
	}, {
		field : 'actIdGroupTYPE',
		title : '类型',
		sortable : true
	} ]
}
// bootstrapTable初始化参数
var initUser = {
	url : '/xxm/rest/user/userService/findUserList', // 请求后台的URL（*）
	method : 'post', // 请求方式（*）
	ajaxOptions : {
		async : false
	// 设置为异步
	},
	toolbar : '#toolbar2', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	 queryParams: queryUsers, //传递参数（*）
	sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
	search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	strictSearch : true,
	showColumns : false, // 是否显示所有的列
	showRefresh : false, // 是否显示刷新按钮
	minimumCountColumns : 2, // 最少允许的列数
	clickToSelect : true, // 是否启用点击选中行
	// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	uniqueId : "userId", // 每一行的唯一标识，一般为主键列
	showToggle : false, // 是否显示详细视图和列表视图的切换按钮
	cardView : false, // 是否显示详细视图
	detailView : false, // 是否显示父子表
	columns : [ {
		checkbox : true
	},{
		field : 'userId',
		visible : false
	}, {
		field : 'name',
		title : '用户姓名',
		sortable : true
	},{
		field : 'phone',
		title : '手机号码',
		sortable : true
	},{
		field : 'employeeNo',
		title : '工号',
		sortable : true
	} ],
	onDblClickRow: function (row) {
		var rowsLeft= new Array();
		rowsLeft.push(row);
		move(rowsLeft);
    }
}
// bootstrapTable初始化参数
var initPpacket = {
		url : '/xxm/rest/actIdGroup/actIdGroupService/findGroupUserList', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		ajaxOptions : {
			async : false// 设置为异步
		},
		toolbar : '#toolbar3', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式
		queryParams:  function(params) {
			var temp = {
				actIdGroupID:$("#actIdGroupID").val()
			};
			return temp;
		}, //传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : true,
		showColumns : false, // 是否显示所有的列
		showRefresh : false, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : false, // 是否启用点击选中行
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "userId", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		columns : [ {
			checkbox : true
		},{
			field : 'userId',
			visible : false
		},{
			field : 'name',
			title : '用户姓名',
			sortable : true
		}, {
			field : 'phone',
			title : '手机号码',
			sortable : true
		}, {
			field : 'employeeNo',
			title : '工号',
			sortable : true
		} ]
	}
// 表单验证初始化
var initFormValid = function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#actIdGroupForm").validate(
			{
				rules : {
					actIdGroupREV : "required",
					actIdGroupNAME : "required",
					actIdGroupTYPE : "required",
				},
				messages : {
					actIdGroupREV : e + "请输入姓名",
					actIdGroupNAME : e + "请输入部门",
					actIdGroupTYPE : e + "请输入类型",
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
var showModal = function(type, actIdGroupID) {
	// 重置表单
	$("#actIdGroupForm").get(0).reset();
	if(type<4){	
		// 弹出模式窗口
		$('#actIdGroupModal').modal('show');
		
	// 添加
	if (type == 1) {
		$('#actIdGroupModal').find("h4").html("添加权限组");
		// 显示区域
		$("#edit").show();
		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#actIdGroupForm").find(".has-error").removeClass("has-error");
		// 恢复ID为可编辑状态
		$("input[id='actIdGroupID']").attr("readonly", false);
	}

	// 修改
	if (type == 2) {
		$('#actIdGroupModal').find("h4").html("修改权限组");
		// 显示区域
		$("#edit").show();
		$("#detail").hide();
		$("#permission").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#actIdGroupForm").find(".has-error").removeClass("has-error");
		// 查询详细信息
		var actIdGroupInfo = findActIdGroupInfo(actIdGroupID);
		// 填充表单数据
		CommonUtils.fillFormData("#actIdGroupForm", JSON.stringify(actIdGroupInfo));
		// 填充隐藏Id
		$("#actIdGroupForm").find("#actIdGroupID").val(actIdGroupID);
		// 设置Id不可编辑
		$("input[id='actIdGroupID']").attr("readonly", true);

	}

	// 详情
	if (type == 3) {
		$('#actIdGroupModal').find("h4").html("查看权限组详情");
		// 查询权限组详细信息
		var actIdGroupInfo = findActIdGroupInfo(actIdGroupID);
		// 填充表单数据
		CommonUtils.fillFormData("#detail", JSON.stringify(actIdGroupInfo));
		// 显示区域
		$("#edit").hide();
		$("#detail").show();
		$("#permission").hide();
		// 保存按钮隐藏
		$("#btnSave").hide();
	}
	}
	if (type == 4) {
		$('#permissionModal').find("h4").html("保存权限组");
		// 弹出模式窗口
		$("#permissionModal").modal('show');
		$("#actIdGroupID").val(actIdGroupID);
//		$("#permissionModal #actIdGroupID").val(actIdGroupID);
		$("#tbPermission").bootstrapTable('refresh', initUser);
		$('#tbPacket').bootstrapTable('refresh', initPpacket);

	}
	// 隐藏值，权限组判断提交数据的类型
	$('#actIdGroupModal').find("#modalType").val(type);
}

// 保存数据
var saveActIdGroup = function() {
	// 验证表单数据
	if (!$("#actIdGroupForm").valid()) {
		return;
	}
	// 获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#actIdGroupForm');
	// 添加或编辑类型变量
	var type = $('#actIdGroupModal').find("#modalType").val();
	var url = null;
	if (type == "1") {// 添加
		url = "/xxm/rest/actIdGroup/actIdGroupService/addActIdGroup";
	} else {// 修改
		url = "/xxm/rest/actIdGroup/actIdGroupService/updateActIdGroup";
		formData.actIdGroupID = $("#actIdGroupForm").find("#actIdGroupID").val();
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
					$("#actIdGroupModal").modal("hide");
					// 刷新列表
					$("#tbActIdGroup").bootstrapTable('refresh', init);
					// 重置表单
					$("#actIdGroupForm").get(0).reset();
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
// 删除权限组
var deleteActIdGroup = function(actIdGroupIDs) {
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
		// 记录删除的所有Id
		var param = null;
		// 判断Ids是不是数组类型
		if (actIdGroupIDs instanceof Array) {
			param = {
				actIdGroupIDs : actIdGroupIDs
			};
		} else {
			// 定义一个数组
			var arr = new Array();
			arr.push(actIdGroupIDs);
			param = {
				actIdGroupIDs : arr
			};
		}
		$.ajax({
			url : '/xxm/rest/actIdGroup/actIdGroupService/deleteActIdGroup',
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
						$("#tbActIdGroup").bootstrapTable('refresh', init);
					});
				}
			},
			error : errorFunc
		});
	})
}

// 查询权限组详细信息
var findActIdGroupInfo = function(actIdGroupID) {
	var actIdGroupInfo = null;
	$.ajax({
		url : '/xxm/rest/actIdGroup/actIdGroupService/findActIdGroup',
		data : JSON.stringify({
			actIdGroupID : actIdGroupID
		}),
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success : function(data) {
			if (null != data) {
				actIdGroupInfo = data;
			}
		},
		error : errorFunc
	});
	return actIdGroupInfo;
}
//保存数据
var addMembership = function(gPUserIds, groupId) {
	// 记录删除的所有Id
	var par = null;
	// 判断Ids是不是数组类型
	if (gPUserIds instanceof Array) {
		par = {
				gPUserIds : gPUserIds,
				actIdGroupID : groupId
		};
	} else {
		// 定义一个数组
		var arr = new Array();
		arr.push(gPUserIds);
		par = {
				gPUserIds : arr,
				actIdGroupID : groupId
		};
	}
	// 请求后台
	$.ajax({
		url : '/xxm/rest/actIdGroup/actIdGroupService/addMembership',
		data : JSON.stringify(par),
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
					$('#permissionModal').modal('hide');
					// 刷新列表
					$("#tbPacket").bootstrapTable('refresh', initPpacket);
					// 重置表单
//					$("#actIdGroupForm").get(0).reset();
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

//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}