//初始化菜单目录树
$(document).ready(function(){
	
    //初始化菜单树
    initMenu();
    //初始化表单验证
    initMenuFormValid();
	
	//保存整个菜单目录
	$("#btnSaveMenu").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		var node = treeObj.getNodes();
		console.log(JSON.stringify(node[0].children));
		//批量保存菜单
		batchSaveMenu(JSON.stringify(node[0].children));
	});
	
	//刷新整个菜单目录
	$("#btnRefreshMenu").click(function(){
		//初始化菜单树
	    initMenu();
	    $(".right").hide();
	});
	
	//保存单个菜单目录
	$("#btnSaveMenuNode").click(function(){
		//添加菜单
		addMenu();
	});
	
	//关闭单个菜单目录
	$("#btnCloseMenuNode").click(function(){
		$(".right").hide();
	});
	
	//展开整个目录
	$("#btnDownMenu").click(function(){
		//全部展开
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		treeObj.expandAll(true);
	});
	
	//关闭整个目录
	$("#btnUpMenu").click(function(){
		//全部关闭
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		treeObj.expandAll(false);
	});
	
	//栏目类型选择
	$("#menuType").change(function(){
		menuTypeChange($(this).val());
	});
});

var setting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		showRemoveBtn: showRemoveBtn,
		showRenameBtn: false
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
		beforeClick: beforeClick,//点击菜单
		beforeDrag: beforeDrag,//菜单拖拽
		//beforeEditName: beforeEditName,//编辑菜单
		beforeRemove: beforeRemove
	}
};

//栏目测试数据
var zNodes =[
    { menuId:0,parentMenuId:-1,menuName:"栏目菜单",open:true},
    { menuId:2, parentMenuId:0, menuName:"父节点 2", open:true},
    { menuId:21, parentMenuId:2, menuName:"叶子节点 2-1"},
    { menuId:22, parentMenuId:2, menuName:"叶子节点 2-2"},
    { menuId:23, parentMenuId:2, menuName:"叶子节点 2-3"},
	{ menuId:1, parentMenuId:0, menuName:"父节点 1", open:true},
	{ menuId:11, parentMenuId:1, menuName:"叶子节点 1-1",remark:"xxxxx",status:0},
	{ menuId:12, parentMenuId:1, menuName:"叶子节点 1-2"},
	{ menuId:13, parentMenuId:1, menuName:"叶子节点 1-3"},
	{ menuId:3, parentMenuId:0, menuName:"父节点 3", open:true},
	{ menuId:31, parentMenuId:3, menuName:"叶子节点 3-1"},
	{ menuId:32, parentMenuId:3, menuName:"叶子节点 3-2"},
	{ menuId:33, parentMenuId:3, menuName:"叶子节点 3-3"}
];


//显示移除按钮
function showRemoveBtn(treeId, treeNode) {
	//顶层根目录不允许删除
	if(treeNode.menuId == 0){
		return false;
	}
	//含有子节点的不允许删除
	if(treeNode.children){
		return false;
	}
	return true;
}

//节点点击事件
function beforeClick(treeId, treeNode){
	if(treeNode.menuId == 0){
		return;
	}
	//重置验证
	initMenuFormValid().resetForm();
	$("#menuForm").find(".has-error").removeClass("has-error");
	//重置表单数据
	$("#menuForm").get(0).reset();
	$("#menuForm").find("#menuName").val(treeNode.menuName);
	$("#menuForm").find("#parentMenuId").val(treeNode.parentMenuId);
	//判断菜单属性是否存在
	if(treeNode.hasOwnProperty("menuPath")){//路径
		$("#menuForm").find("#menuPath").val(treeNode.menuPath);
		$("#menuForm").find("#menuId").val(treeNode.menuId);
		//操作标识：1，表示添加、2表示修改
		$("#menuForm").find("#type").val("2");
	}else{
		//操作标识：1，表示添加、2表示修改
		$("#menuForm").find("#type").val("1");
	}
	if(treeNode.hasOwnProperty("menuType")){//类型
		$("#menuForm").find("#menuType").val(treeNode.menuType);
	}
	if(treeNode.hasOwnProperty("disabledFlag")){//状态
		$("#menuForm").find("#disabledFlag").val(treeNode.disabledFlag);
	}
	if(treeNode.hasOwnProperty("remark")){//备注
		$("#menuForm").find("#remark").val(treeNode.remark);
	}
	if(treeNode.hasOwnProperty("extendField1")){// 扩展字段1
		$("#menuForm").find("#extendField1").val(treeNode.extendField1);
	}
	if(treeNode.hasOwnProperty("extendField2")){// 扩展字段2
		$("#menuForm").find("#extendField2").val(treeNode.extendField2);
	}
	if(treeNode.hasOwnProperty("extendField3")){// 扩展字段3
		$("#menuForm").find("#extendField3").val(treeNode.extendField3);
	}
	if(treeNode.hasOwnProperty("extendField4")){// 扩展字段4
		$("#menuForm").find("#extendField4").val(treeNode.extendField4);
	}
	//栏目类型判断
	menuTypeChange($("#menuForm").find("#menuType").val());
	$(".right").show();//显示菜单详细信息
}

//判断节点是否可以拖拽
function beforeDrag(treeId, treeNodes) {
	if(treeNodes[0].menuId == 0){
		return false;
	}
	return true;
}

//删除节点
function beforeRemove(treeId, treeNode) {
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
	    $.ajax({url:'/xxm/rest/menu/menuService/deleteMenu',
	    	data:JSON.stringify({menuId:treeNode.menuId}),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data.result){
	    			//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						//初始化菜单树
					    initMenu();
					});
	    		}
	    	},
	    	error : errorFunc
	    });
	});
	return false;
}

//鼠标移上节点显示添加、删除节点按钮
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		zTree.addNodes(treeNode, {menuId:(100 + newCount), parentMenuId:treeNode.menuId, menuName:"new node" + (newCount++)});
		return false;
	});
};

//鼠标移除节点隐藏添加、删除节点按钮
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

//查询菜单集合
var findMenuList = function(){
	//菜单节点
	var zNodes = new Array();
	//请求后台
	$.ajax({url:"/xxm/rest/menu/menuService/findMenuList",
		data:JSON.stringify({}),
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


//添加菜单
var addMenu = function(){
	//验证表单数据
	if (!$("#menuForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#menuForm');
	//操作标识：1，表示添加、2表示修改
	var type = $("#menuForm").find("#type").val(); 
	var url = null;
	if(type == "2"){//修改
		url = "/xxm/rest/menu/menuService/updateMenu";
		//设置栏目Id
		formData.menuId = $("#menuForm").find("#menuId").val();
	}else{//添加
		url = "/xxm/rest/menu/menuService/addMenu";
	}
	//设置父节点Id
	var parentMenuId = $("#menuForm").find("#parentMenuId").val();
	formData.parentMenuId = parentMenuId;
	//请求后台
	$.ajax({url:url,
		data:JSON.stringify(formData),
    	type:'post', 
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(data.result){
    			//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//初始化菜单树
				    initMenu();
				    $(".right").hide();
				});
    		}else{
    			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
    		}
    	},
    	error : errorFunc  
    });
}

//批量菜单集合
var batchSaveMenu = function(nodes){
	//请求后台
	$.ajax({url:"/xxm/rest/menu/menuService/updateBatchMenu",
		data:nodes,
    	type:'post', 
    	dataType:'json',  
    	contentType: "application/json",
    	success:function(data) {  
    		if(data.result){
    			//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//初始化菜单树
				    initMenu();
				});
    		}else{
    			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
    		}
    	},
    	error : errorFunc 
    });
}


//初始化菜单树
var initMenu = function(){
	//查询所有菜单目录并转换
	var nodes = findMenuList();
	//增加顶层根目录
	nodes.push({ menuId:0,parentMenuId:-1,menuName:"栏目菜单",open:true});
	//初始化栏目菜单
	$.fn.zTree.init($("#treeMenu"), setting, nodes);
	//全部展开
	var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
	treeObj.expandAll(true);
}

//表单验证初始化
var initMenuFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#menuForm").validate({
		rules : {
			menuName : {
				required : !0,
				maxlength : 25
			},
			menuPath : {
				required : !0,
				maxlength : 100,
			}
		},
		messages : {
			menuName : {
				required : e + "请填点名称",
				maxlength : e + "名称不能超过25字符"
			},
			menuPath : {
				required : e + "请填写路径",
				maxlength : e + "路径不能超过100字符",
			}
		},
		highlight : function(e) {
			if(e.type == "radio" || e.type == "checkbox"){
				$(e).parent().parent().removeClass("has-success").addClass("has-error");
			}else{
				$(e).parent().removeClass("has-success").addClass("has-error");
			}
		},
		success : function(e) {
			if(e.type == "radio" || e.type == "checkbox"){
				$(e).parent().parent().removeClass("has-error");//.addClass("has-success")
			}else{
				$(e).parent().removeClass("has-error");//.addClass("has-success")
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


//栏目类型判断
var menuTypeChange = function(val){
	if(val == "1"){//如果是按钮类型，则隐藏路径
		$("#divMenuPath").hide();
	}else{
		$("#divMenuPath").show();
	}
}

	
