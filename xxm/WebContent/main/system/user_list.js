$(function () {
	
    //初始化table并刷新数据
    $('#tbUser').bootstrapTable(init);
    
    //表单验证初始化
    initFormValid();
    
    //下拉控件初始化
    initSelect();

    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#tbUser").bootstrapTable('refresh', init);
    });
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#tbUser").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var userIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		userIds.push(rows[i].userId);
        	}
        	//批量删除用户信息
        	deleteUser(userIds);
        }
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	saveUser();//保存数据
    });
    
    //角色种类
    CommonUtils.findBaseDataOption("#editType","USER_TYPE",1);
    //角色种类
    CommonUtils.findBaseDataOption("#selectType","USER_TYPE",1);
});


//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        type:$("#selectType").val(),//角色种类
        name: $("#name").val(),//用户姓名
        account: $("#account").val()//用户账号
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteUser('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'" + value + "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    //result += '<a class="J_menuItem" href="system/menu_list.html?userId='+value+'">xxxxxxx</a>';
    return result;
}

//性别字段格式化
var sexFormatter = function(value) {
	var sexName = "";
    if (value == "1") { 
    	sexName = "男";
    }else { 
    	sexName = "女";
    }
    return sexName;
}
//择证件类型
var paperTypeFormatter = function(value){
	var paperType = "";
    if (value == "1") { 
    	paperType = "身份证";
    }else if(value == "2") { 
    	paperType = "护照";
    }else if(value == "3"){
    	paperType = "港澳通行证";
    }
    return paperType;
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
    url: '/xxm/rest/user/userService/findUserList', //请求后台的URL（*）
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
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: false,                //是否启用点击选中行
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "userId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'userId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'name',
			title: '用户姓名',
			sortable : true
		},{
			field: 'age',
			title: '年龄',
			sortable : true,
			visible: false
		},{
			field: 'sex',
			title: '性别',
			sortable: true,
			formatter: sexFormatter
		},{
			field: 'employeeNo',
			title: '工号',
			sortable : true
		},{
			field: 'account',
			title: '用户账号',
			sortable : true
		}, {
			field: 'phone',
			title: '电话号码',
			sortable : true
		}, {
			field: 'typeName',
			title: '用户种类',
			sortable : true
		}, {
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		}, {
			field: 'lastUpdateDate',
			title: '最后更新时间',
			formatter: dataFormatter
		}
	],
	onLoadSuccess:function(data){
		//加载效果
    	mask_fullscreen();
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

//表单验证初始化
var initFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#userForm").validate({
		rules : {
			name : "required",
			phone: "required",
			age : "required",
			employeeNo:"required",
			account:"required",
			sex:{
				required:true,
				min:1
			},
			paperType:{
				required:true,
				min:1
			},
			paperNo:"required",
			password : {
				required : !0,
				minlength : 5
			},
			confirm_password : {
				required : !0,
				minlength : 5,
				equalTo : "#password"
			}
		},
		messages : {
			name : e + "请输入你的姓名",
			phone : e + "请输入您的联系电话",
			age : e + "请输入您的年龄",
			sex : e + "请选择性别",
			employeeNo : e + "请输入您的工号",
			account : e + "请填写账号",
			paperType: e + "请选择证件类型",
			paperNo: e + "请填写证件编号",
			password : {
				required : e + "请输入您的密码",
				minlength : e + "密码必须5个字符以上"
			},
			confirm_password : {
				required : e + "请再次输入密码",
				minlength : e + "密码必须5个字符以上",
				equalTo : e + "两次输入的密码不一致"
			}
		},
		highlight : function(e) {
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
var showModal =function(type,userId){
	//重置表单
	$("#userForm").get(0).reset();
	//弹出模式窗口
	$('#userModal').modal('show'); 
	//设置角色下拉框宽度
	$("#userForm").find("#useRoleIds_chosen").css("width","100%");
	$("#detail").find("#useRoleIdsDetails_chosen").css("width","100%");
	//添加
	if(type == 1){
		$('#userModal').find("h4").html("添加用户");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		//初始化角色选择数据
		initRoleSelect("#useRoleIds");
		$("#userForm").find(".has-error").removeClass("has-error");
	}
	//修改
	if(type == 2){
		$('#userModal').find("h4").html("修改用户");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#userForm").find(".has-error").removeClass("has-error");
		//查询用户详细信息
		var userInfo = findUserInfo(userId);
		//初始化角色选择数据
		initRoleSelect("#useRoleIds",userInfo.userRoles);
		//填充表单数据
		CommonUtils.fillFormData("#userForm",JSON.stringify(userInfo));
		//在次密码填充
		$("#userForm").find("#confirm_password").val(userInfo.password);
		//填充隐藏UserId
		$("#userForm").find("#userId").val(userId);
	}
	//详情
	if(type == 3){
		$('#userModal').find("h4").html("用户详情");
		//查询用户详细信息
		var userInfo = findUserInfo(userId);
		//初始化角色选择数据
		initRoleSelect("#useRoleIdsDetails",userInfo.userRoles);
		//填充表单数据
		CommonUtils.fillFormData("#detail",JSON.stringify(userInfo));
		//性别
		$("#detail").find("#sex").html(sexFormatter(userInfo.sex));
		//证件类型
		$("#detail").find("#paperType").html(paperTypeFormatter(userInfo.paperType));
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#userModal').find("#modalType").val(type);
}

//保存用户
var saveUser=function(){
	//验证表单数据
	if (!$("#userForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#userForm');
	//移除确认密码
	delete formData.confirm_password;
	//添加或编辑类型变量
	var type = $('#userModal').find("#modalType").val();
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/user/userService/addUser";
		var userRoleIds = $("#useRoleIds").val();
		if(null != userRoleIds && "" != userRoleIds){
			var userRoles = new Array();
			for(var i  = 0; i < userRoleIds.length; i++){
				userRoles.push({roleId:userRoleIds[i]});
			}
			formData.userRoles = userRoles;//设置用户角色
		}
	}else{//修改
		url = "/xxm/rest/user/userService/updateUser";
		var userId = $("#userForm").find("#userId").val(); 
		formData.userId = userId;//设置用户id
		var userRoleIds = $("#useRoleIds").val();
		if(null != userRoleIds && "" != userRoleIds){
			var userRoles = new Array();
			for(var i  = 0; i < userRoleIds.length; i++){
				userRoles.push({userId:userId,roleId:userRoleIds[i]});
			}
			formData.userRoles = userRoles;//设置用户角色
		}
	}
	//请求后台
	$.ajax({
		url:url,
		data:JSON.stringify(formData),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//关闭弹出框
					$("#userModal").modal("hide");
					//刷新列表
			    	$("#tbUser").bootstrapTable('refresh', init);
			    	//重置表单
					$("#userForm").get(0).reset();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
};

//删除用户
var deleteUser =function(userIds){
	//删除数据模式窗口
	swal({
		title : "您确定要删除这些信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "删除",
		closeOnConfirm : false
	}, function() {
		debugger;
		//记录删除用户的所有userId
		var param = null;
		//判断userIds是不是数组类型
		if(userIds instanceof Array){
			param = {
				userIds:userIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(userIds);
			param = {
				userIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/user/userService/deleteUser',
	    	data:JSON.stringify(param),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data.result){
	    			//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						//刷新列表
				    	$("#tbUser").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc 
	    });
	})
}

//查询用户详细信息
var findUserInfo=function(userId) {
	var userInfo = null;
	$.ajax({url:'/xxm/rest/user/userService/findUser',
		data:JSON.stringify({userId:userId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				userInfo = data;
			}
		},
		error : errorFunc
	});
	return userInfo;
}

//查询有效角色集合信息
var findRolesInfo = function() {
	var roles = null;
	$.ajax({url:'/xxm/rest/role/roleService/findRoles',
		data:JSON.stringify({}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				roles = data;
			}
		},
		error : errorFunc  
	});
	return roles;
}

//初始化角色选择数据
var initRoleSelect = function(selectId,checkedArray){
	//获取有效角色
	var roles = findRolesInfo();
	if(null != roles && roles.length > 0){
		var option = "";
		for(var i = 0; i < roles.length; i++){
			option += '<option value="'+roles[i].roleId+'" hassubinfo="true">'+roles[i].roleName+'</option>';
		}
		$(selectId).html(option).trigger("chosen:updated");
		//默认值
		if(checkedArray && null != checkedArray){
			for(var j = 0; j < checkedArray.length; j++){
				$(selectId + " option[value='"+checkedArray[j].roleId+"']").attr("selected","selected");
			}
			$(selectId).trigger("chosen:updated");
		}
	}
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}

