$(function() {
	// 初始化table并刷新数据
	$('#tbProvider').bootstrapTable(init);
	// 表单验证初始化
	initFormValid();
	findBusinessInfo();
	// 查询Button的点击事件
	$("#btnQuery").click(function() {
		$("#tbProvider").bootstrapTable('refresh', init);
	});
	// 添加按钮点击事件
	$("#btnAdd").click(function() {
		// 显示弹出框
		showModal(1);
	});
	// 批量删除按钮点击事件
	$("#btnDelete").click(function() {
		var rows = $("#tbProvider").bootstrapTable('getSelections');
		if (rows.length <= 0) {
			swal("很抱歉", "请您勾选您要删除的记录！", "info");
		} else {
			var spIds = new Array();
			for (var i = 0; i < rows.length; i++) {
				spIds.push(rows[i].spId);
			}
			// 批量删除信息
			deleteProvider(spIds);
		}
	});

	// 保存按钮点击事件
	$("#btnSave").click(function() {
		saveAtt();// 保存数据
	});
});
//查询的参数
var queryParams = function(params) {
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		spName : $("#spName").val(),//服务商名称
		spContacts : $("#spContacts").val() //服务商联系人
	};
	return temp;
};
// 操作
var actionFormatter = function(value, row, index) {
	var result = "";
	result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"
			+ value
			+ "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteProvider('"
			+ value
			+ "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"
			+ value
			+ "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	result += "<a class='J_menuItem btn btn-xs green' href='provider/sp_follow_up.html?spId="+ value+"' title='跟进'>跟进</a>";
	result +=  "<a class='J_menuItem btn btn-xs green' href='provider/sp_service.html?spId="+ value+"' title='服务'>服务</a>";
	result += "<a class='J_menuItem btn btn-xs green' href='provider/sp_partition_allocation.html?spId="+ value+"' title='分润配置'>分润配置</a>";
	result += "<a class='J_menuItem btn btn-xs green' href='provider/sp_account_flow.html?spId="+ value+"' title='账户流水'>账户流水</a>";
	return result;
}
////保险公司
//var spBusinessFormatter=function(){
//	var spBusinessType=""
//		if(value="1"){
//			spBusinessType="A";
//		}else if(value="2"){
//			spBusinessType="B";
//		}else if(value="3"){
//			spBusinessType="C";
//		}else if(value="4"){
//			spBusinessType="D";
//		}else if(value="5"){
//			spBusinessType="E";
//		}
//	return spBusinessType;
//}
//服务商类别
var spCategoryFormatter=function(){
	var spCategoryType=""
		if(value="1"){
			spCategoryType="个人";
		}else if(value="2"){
			spCategoryType="公司";
		}
	return spCategoryType;
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
	url : '/xxm/rest/provider/providerService/findProviderList',
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
	uniqueId : "spId",
	showToggle : true,
	cardView : false,
	detailView : false,
	columns : [ {
		checkbox : true
	}, {
		field : 'spId',
		title : '操作',
		width : 200,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	}, {
		field : 'spName',
		title : '服务商名称',
		sortable : true
	}, {
		field : 'spContacts',
		title : '服务商联系人',
		sortable : true,
	}, {
		field : 'spMobile',
		title : '手机号码',
		sortable : true
	}, {
		field : 'spBusiness',
		title : '业务归属人',
		sortable : true,
		formatter : findBusinessInfo
	},{
		field : 'Balance',
		title : '余额',
		sortable : true,
	} ],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
    },
    onLoadError:function(status, res){
		errorFunc(res,status);
    }
}
// 表单验证初始化
var initFormValid = function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#providerForm").validate({
		rules : {
			spName : "required",
			spContacts : "required",
			spFax : "required",
			spAddress : "required",
			spInsurance :{
				required:true,
				min:1
			},
			spPhone : "required",
			spSuperior : {
				required:true,
				min:1
			},
			spCategory : {
				required:true,
				min:1
			},
			spMail : {
				"required":true,
				"email":true
				
			},
			spUrl : "required",
			spMobile : "required",
			spBusiness : {
				required:true,
				min:1
			},
			spBankAccountName : "required",
			spBankAccount : "required",
			spBankCardNumber : "required"
		},
		messages : {
			spName : e + "请输入服务商名称",
			spContacts : e + "请输入服务商联系人",
			spFax : e + "请输入传真",
			spAddress : e + "请输入地址",
			spInsurance : e + "请选择保险公司",
			spPhone : e + "请输入电话",
			spSuperior : e + "请输入上级服务商",
			spCategory : e + "请输入服务商类别",
			spMail : e + "请输入正确的邮箱格式",
			spUrl : e + "请输入正确的网站格式",
			spMobile : e + "请输入手机号码",
			spBusiness : e + "请选择业务归属人",
			spBankAccountName : e + "请输入银行账户名称",
			spBankAccount : e + "请输入银行账号",
			spBankCardNumber : e + "请输入银行卡号"
		},highlight : function(e) {
					if(e.type == "radio" || e.type == "checkbox"){
						$(e).parent().parent().parent().removeClass("has-success").addClass("has-error");
					}else{
						$(e).parent().parent().removeClass("has-success").addClass("has-error");
					}
				},
				success : function(e) {
					if(e.type == "radio" || e.type == "checkbox"){
						$(e).parent().parent().parent().removeClass("has-error");//.addClass("has-success")
					}else{
						$(e).parent().parent().removeClass("has-error");//.addClass("has-success")
					}
				},
				errorElement : "span",
				errorPlacement : function(e, r) {
					if(r.is(":radio") || r.is(":checkbox")){
						e.appendTo(r.parent().parent());
					}else{
						e.appendTo(r.parent());
					}
				},
				errorClass : "help-block m-b-none",
				validClass : "help-block m-b-none"
			});
			return validate;
}

//判断弹出框类型（修改、新增、详情）并显示
var showModal = function(type, spId) {
	// 重置表单
	$("#providerForm").get(0).reset();
	// 弹出模式窗口
	$('#providerModal').modal('show');

	// 添加
	if (type == 1) {
		$('#providerModal').find("h4").html("添加服务商信息");
		// 显示区域
		$("#edit").show();

		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#providerForm").find(".has-error").removeClass("has-error");
	}

	// 修改
	if (type == 2) {
		$('#providerModal').find("h4").html("修改服务商信息");
		// 显示区域
		$("#edit").show();
		$("#detail").hide();
		// 保存按钮显示
		$("#btnSave").show();
		// 重置验证
		initFormValid().resetForm();
		$("#providerForm").find(".has-error").removeClass("has-error");
		// 查询服务商详细信息
		var providerInfo = findProviderInfo(spId);
		// 填充表单数据
		CommonUtils.fillFormData("#providerForm", JSON.stringify(providerInfo));
		// 填充隐藏spId
		$("#providerForm").find("#spId").val(spId);
	}
	// 详情
	if (type == 3) {
		$('#providerModal').find("h4").html("服务商详情");
		// 查询服务商详细信息
		var providerInfo = findProviderInfo(spId);
		// 填充表单数据
		CommonUtils.fillFormData("#detail", JSON.stringify(providerInfo));
		$("#detail").find("#spCategory").html(spCategoryFormatter(providerInfo.spCategory));
		// 显示区域
		$("#edit").hide();
		$("#detail").show();
		// 保存按钮隐藏
		$("#btnSave").hide();
	}
	// 隐藏值，服务商信息判断提交数据的类型
	$('#providerModal').find("#modalType").val(type);
}
// 保存服务商信息
var saveAtt = function() {
	// 验证表单数据
	if (!$("#providerForm").valid()) {
		return;
	}
	// 获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#providerForm');
	// 添加或编辑类型变量
	var type = $('#providerModal').find("#modalType").val();
	var url = null;
	if (type == "1") {// 添加
		url = "/xxm/rest/provider/providerService/addProvider";
	} else {// 修改
		url = "/xxm/rest/provider/providerService/updateProvider";
		formData.spId = $("#providerForm").find("#spId").val();
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
					$("#providerModal").modal("hide");
					// 刷新列表
					$("#tbProvider").bootstrapTable('refresh', init);

					// 重置表单
					$("#providerForm").get(0).reset();
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
//删除gps型号信息
var deleteProvider = function(spIds) {
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
		if (spIds instanceof Array) {
			param = {
					spIds : spIds
			};
		} else {
			// 定义一个数组
			var arr = new Array();
			arr.push(spIds);
			param = {
					spIds : arr
			};
		}
		$.ajax({
			url : '/xxm/rest/provider/providerService/deleteProvider',
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
						$("#tbProvider").bootstrapTable('refresh', init);
					});
				}
			},
    		error : errorFunc
		});
	})
}
// 查询详细信息
var findProviderInfo = function(spId) {
	var providerInfo = null;
	$.ajax({
		url : '/xxm/rest/provider/providerService/findProvider',
		data : JSON.stringify({
			spId : spId
		}),
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success : function(data) {
			if (null != data) {
				providerInfo = data;
			}
		},
		error : errorFunc
	});
	return providerInfo;
}
//业务归属人
var findBusinessInfo = function(){
	$.ajax({
		url:'/xxm/rest/user/userService/findUsers',
		date:{},
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success:function(data){
			if (null != data) {
				var html='';
				for(var i=0;i<data.length;i++){             
		            html=html+"<option value="+data[i].userId+">"+data[i].name+"</option>";
		        }
				$("#spBusiness").append(html);
			}
			
		},
		error : errorFunc
	});
}