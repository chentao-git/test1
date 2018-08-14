$(function () {
	//加载效果
	mask_fullscreen();
    //初始化table并刷新数据
    $('#tbRole').bootstrapTable(init);
    
    //初始化时间控件
    initDateControl();
    
    //表单验证初始化
    initFormValid();
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#tbRole").bootstrapTable('refresh', init);
    });
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#tbRole").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var roleIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		roleIds.push(rows[i].roleId);
        	}
        	//批量删除角色信息
        	deleteRole(roleIds);
        }
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	saveRole();//保存数据
    });
    
    //查询重置方法
    $("#btnRest").click(function(){
    	//重置查询表单
    	$("#formSearch").get(0).reset();
    	laydate.reset();
    });
    
    //角色种类
    CommonUtils.findBaseDataOption("#editType","ROLE_TYPE",1);
    //角色种类
    CommonUtils.findBaseDataOption("#selectType","ROLE_TYPE",1);
    
});

//初始化时间控件
var initDateControl = function(){
	//角色有效期
    laydate({elem:"#validDate",event:"focus"});
    var start = {
		elem : "#start",
		format : "YYYY-MM-DD",
		min: '1900-01-01', //最小日期
		max: '2099-12-31', //最大日期
		istime : false,//是否显示时间选择
		istoday : false,//是否显示今天
		choose : function(datas) {
			end.min = datas;
			end.start = datas
		}
	};
    var end = {
		elem : "#end",
		format : "YYYY-MM-DD",
		min : laydate.now(),
		max: '2099-12-31', //最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			start.max = datas
		}
	};
    //有效期开始时间
    laydate(start);
    //有效期结束时间
    laydate(end);
}


//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        roleName: $("#roleName").val(),//角色姓名
        type:$("#selectType").val(),//角色种类
        startValidDate:$("#start").val(),//有效期开始时间
        endValidDate:$("#end").val()//有效期结束时间
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteRole('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'" + value + "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(4,'" + value + "')\" title='授权'><span class='glyphicon glyphicon-lock'></span></a>";
    return result;
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
    url: '/xxm/rest/role/roleService/findRoleList', //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
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
    uniqueId: "roleId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'roleId',
             title: '操作',
             width: 120,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'roleName',
			title: '角色名称',
			sortable : true
		}, {
			field: 'typeName',
			title: '角色种类',
			sortable : true
		},{
			field: 'validDate',
			title: '角色有效期限',
			sortable : true
		},{
			field: 'remark',
			width: 300,
			title: '备注'
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
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

//表单验证初始化
var initFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#roleForm").validate({
		rules : {
			roleName : "required",
			validDate: "required"
		},
		messages : {
			roleName : e + "请输入角色名称!",
			validDate : e + "请填入日期!"
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
var showModal =function(type,roleId){
	//重置表单
	$("#roleForm").get(0).reset();
	//弹出模式窗口
	$('#roleModal').modal('show'); 
	//添加
	if(type == 1){
		$('#roleModal').find("h4").html("添加角色");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		$("#authorize").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#roleForm").find(".has-error").removeClass("has-error");
	}
	//修改
	if(type == 2){
		$('#roleModal').find("h4").html("修改角色");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		$("#authorize").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#roleForm").find(".has-error").removeClass("has-error");
		//查询角色详细信息
		var roleInfo = findRoleInfo(roleId);
		//填充表单数据
		CommonUtils.fillFormData("#roleForm",JSON.stringify(roleInfo));
		//填充隐藏roleId
		$("#roleForm").find("#roleId").val(roleId);
	}
	//详情
	if(type == 3){
		$('#roleModal').find("h4").html("角色详情");
		//查询角色详细信息
		var roleInfo = findRoleInfo(roleId);
		//填充表单数据
		CommonUtils.fillFormData("#detail",JSON.stringify(roleInfo));
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		$("#authorize").hide();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//授权
	if(type == 4){
		$('#roleModal').find("h4").html("角色授权");
		//显示区域
		$("#edit").hide();
		$("#detail").hide();
		$("#authorize").show();
		//保存按钮显示
		$("#btnSave").show();
		//初始化菜单树
		initMenu();
		//查询角色详细信息
		var roleInfo = findRoleInfo(roleId);
		if(null != roleInfo && null != roleInfo.rpVOList && roleInfo.rpVOList.length > 0){
			//获取菜单对象
			var treeObj = $.fn.zTree.getZTreeObj("treeUserMenu");
			//角色权限
			var rolePermissions = roleInfo.rpVOList;
			for(var i = 0; i < rolePermissions.length; i++){
				var node = treeObj.getNodeByParam("menuId", rolePermissions[i].menuId, null);
				if(null != node){
					//选中菜单项，2参数表示节点的是否选中状态，3参数表示节点是否进行父子节点的勾选联动操作
					treeObj.checkNode(node,true,false);
				}
			}
		}
		//填充隐藏roleId
		$("#roleForm").find("#roleId").val(roleId);
	}
	//隐藏值，角色判断提交数据的类型
	$('#roleModal').find("#modalType").val(type);
}

//保存角色
var saveRole=function(){
	//验证表单数据
	if (!$("#roleForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#roleForm');
	//添加或编辑类型变量
	var type = $('#roleModal').find("#modalType").val();
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/role/roleService/addRole";
	}else if(type == "2"){//修改
		url = "/xxm/rest/role/roleService/updateRole";
		formData.roleId = $("#roleForm").find("#roleId").val(); 
	}else if(type == "4"){//授权
		url = "/xxm/rest/role/roleService/updateRoleAuth"; 
		//获取所有选中的权限
		var treeObj = $.fn.zTree.getZTreeObj("treeUserMenu");
		var nodes = treeObj.getCheckedNodes(true);
		formData.menuNodes = JSON.stringify(nodes);
		formData.roleId = $("#roleForm").find("#roleId").val(); 
	}
	//请求后台
	if(null != url){
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
						$("#roleModal").modal("hide");
						//刷新列表
						$("#tbRole").bootstrapTable('refresh', init);
						//重置表单
						$("#roleForm").get(0).reset();
					});
				}else{
					//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
				}
			},
			error : errorFunc
		});
	}
};

//删除角色
var deleteRole =function(roleIds){
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
		//记录删除角色的所有roleId
		var param = null;
		//判断roleIds是不是数组类型
		if(roleIds instanceof Array){
			param = {
				roleIds:roleIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(roleIds);
			param = {
				roleIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/role/roleService/deleteRole',
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
				    	$("#tbRole").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc 
	    });
	})
}

//查询角色详细信息
var findRoleInfo=function(roleId) {
	var roleInfo = null;
	$.ajax({url:'/xxm/rest/role/roleService/findRole',
		data:JSON.stringify({roleId:roleId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				roleInfo = data;
			}
		},
		error : errorFunc 
	});
	return roleInfo;
}

//菜单设置
var setting = {
	view: {
		addHoverDom: false,
		removeHoverDom: false,
		selectedMulti: false
	},
	check: {
		enable: true
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		drag:{
			isCopy:false,//不允许复制
			isMove:false//不允许移动
		}
	},
	data: {
		key: {
			name: "menuName"
		},
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentMenuId",
		}
	},
	callback: {
		beforeClick: null,//点击菜单
		beforeRemove: null
	}
};

//查询菜单集合
var findMenuList = function(){
	//菜单节点
	var zNodes = new Array();
	//请求后台
	$.ajax({url:"/xxm/rest/menu/menuService/findMenuList",
		data:JSON.stringify({disabledFlag:"0"}),
    	type:'post', 
    	async:false,//同步请求
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(null != data && data.length > 0){
    			zNodes = data;
    		}
    	},
    	error : errorFunc 
    });
	return zNodes;
}

//初始化菜单树
var initMenu = function(){
	//查询所有菜单目录并转换
	var nodes = findMenuList();
	//增加顶层根目录
	nodes.push({ menuId:0,parentMenuId:-1,menuName:"栏目菜单",open:true});
	//初始化栏目菜单
	$.fn.zTree.init($("#treeUserMenu"), setting, nodes);
	//全部展开
	var treeObj = $.fn.zTree.getZTreeObj("treeUserMenu");
	treeObj.expandAll(true);
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}
