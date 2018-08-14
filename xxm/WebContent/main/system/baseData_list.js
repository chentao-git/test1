$(function () {
	//加载效果
	mask_fullscreen();
    //初始化table并刷新数据
    $('#tbBaseData').bootstrapTable(init);
    
    //表单验证初始化
    initBaseDataFormValid();

    //查询Button的点击事件
    $("#btnBaseDataQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#tbBaseData").bootstrapTable('refresh', init);
    });
    
    //添加按钮点击事件
    $("#btnBaseDataAdd").click(function(){
    	//显示弹出框
    	showModal(1,0,"");
    });
    
    //批量删除按钮点击事件
    $("#btnBaseDataDelete").click(function(){
    	var rows= $("#tbBaseData").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var baseIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		baseIds.push(rows[i].baseId);
        	}
        	//批量删除基础数据信息
        	deleteBaseData(baseIds,"","");
        }
    });
    
    //保存按钮点击事件
    $("#btnBaseDataSave").click(function(){
    	saveBaseData();//保存数据
    });
    
    //刷新缓存
    $("#btnBaseDataRefresh").click(function(){
    	refreshBaseData();
    });
    
    //刷新缓存
    $("#btnTaskBaseDataRefresh").click(function(){
    	initTaskBaseData();
    });
    
});


//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        level: "0",//查询第一层目录
        matchBaseName: $("#matchBaseName").val(),//基础数据名称
        matchBaseCode: $("#matchBaseCode").val()//基础数据编码
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'"+row.level+"','" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteBaseData('" + value + "','"+row.level+"','"+row.baseParentId+"')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'"+row.level+"','" + value + "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    return result;
}

//状态字段渲染
var validFlagFormatter = function(value) {
	var name = "";
    if (value == "1") { 
    	name = "失效";
    }else { 
    	name = "启用";
    }
    return name;
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
    url: '/xxm/rest/baseData/baseDataService/findBaseDataList', //请求后台的URL（*）
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
    uniqueId: "baseId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: true,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'baseId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'baseName',
			title: '数据名称'
		},{
			field: 'baseCode',
			title: '数据编码',
			sortable : true
		},{
			field: 'validFlag',
			title: '状态',
			sortable: true,
			//align: 'center',
			formatter: validFlagFormatter
		},{
			field: 'remark',
			title: '备注',
			sortable : true,
			visible: false
		}, {
			field: 'extendField1',
			title: '扩展字段1',
			visible: false
		}, {
			field: 'extendField2',
			title: '扩展字段2',
			visible: false
		}, {
			field: 'extendField3',
			title: '扩展字段3',
			visible: false
		}, {
			field: 'extendField4',
			title: '扩展字段4',
			visible: false
		}, {
			field: 'createByName',
			title: '创建人'
		}, {
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		}, {
			field: 'lastUpdateByName',
			title: '最后更新人'
		}, {
			field: 'lastUpdateDate',
			title: '最后更新时间',
			formatter: dataFormatter
		}
	],
	onExpandRow:function (index,row,$detail) {  
	    initSubTable(index,row,$detail);  
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

//bootstrapTable子项初始化参数
var initChild = function(level,baseId){
	var detailView = true;
	//控制3层子项
	if(level >= 3){
		detailView = false;
	}
	var param = {  
		url:'/xxm/rest/baseData/baseDataService/findBaseDataList',  
		method:'post',  
		queryParams:function (params) {
			var temp = {
					limit: params.limit,   //页面大小
					offset: params.offset,  //页码
					level: level,
					baseParentId: baseId
			};
			return temp;
		},  
		//ajaxOptions:{baseParentId:baseId,level:1},  
		clickToSelect:false,
		pagination: true,
		sidePagination: 'server', 
		cache: false, // 不缓存
		detailView:detailView,  
		uniqueId:"baseId",  
		pageSize:10,  
		pageList:[10,25],  
		columns:[{
			field:'baseId',
			title: '操作&nbsp;<button type="button" id="btnUpMenu" title="增加子项基础数据" onclick=showModal(1,'+level+','+baseId+') class="btn btn-outline btn-info btn-sm"><span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span></button>',
			width: 100,
			align: 'center',
			valign: 'middle',
			formatter: actionFormatter
		}, {
			field: 'baseName',
			title: '数据名称'
		},{
			field: 'baseCode',
			title: '数据编码'
		},{
			field: 'baseCodeValue',
			title: '数据编码值'
		},{
			field: 'validFlag',
			title: '状态',
			formatter: validFlagFormatter
		}, {
			field: 'createByName',
			title: '创建人'
		}, {
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		}, {
			field: 'lastUpdateByName',
			title: '最后更新人'
		}, {
			field: 'lastUpdateDate',
			title: '最后更新时间',
			formatter: dataFormatter
		}],
		onExpandRow:function (index,row,$detail) {  
		    initSubTable(index,row,$detail);  
		},
		onLoadError:function(status, res){
			errorFunc(res,status);
		}
	}
	return param;
}

//显示子列表
function initSubTable(index,row,$detail) {  
    var baseId = row.baseId;
    //新创建一个table元素
    var cur_table = $detail.html('<table id="tbChildBaseData_'+baseId+'"></table>').find('table');
    var level = parseInt(row.level);
    //加载子项数据
    $(cur_table).bootstrapTable(initChild(level+1,baseId));  
}  

//增加自定义验证
jQuery.validator.addMethod("baseCode", function(value, element) {
    var length = value.length;
    var baseCode = /^[a-zA-Z_0-9_-]+$/;//数字、字母、下划线、减号
    return this.optional(element) ||  baseCode.test(value);
}, "请输入正确的础数据编码");


//表单验证初始化
var initBaseDataFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#baseDataForm").validate({
		rules : {
			baseName :{
				required:true,
				maxlength:100
			},
			baseCode: {
				required:true,
				baseCode:true,
				maxlength:50
			},
			baseCodeValue:{
				required:true,
				baseCode:true,
				maxlength:50
			}
		},
		messages : {
			baseName : {
				required:e + "请输入基础数据名称",
				maxlength:e + "输入的基础数据名称长度不能超过100个字符"
			},
			baseCode :{
				required:e + "请输入基础数据编码",
				baseCode:e + "请输入正确的础数据编码（字母、数字或下划线）",
				maxlength:e + "输入的基础数据编码长度不能超过50个字符"
			} ,
			baseCodeValue : {
				required:e + "请输入基础数据编码值",
				baseCode:e + "请输入正确的础数据编码（字母、数字或下划线）",
				maxlength:e + "输入的基础数据编码长度不能超过50个字符"
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
var showModal =function(type,level,baseId){
	//重置表单
	$("#baseDataForm").get(0).reset();
	//弹出模式窗口
	$('#baseDataModal').modal('show'); 
	//添加
	if(type == 1){
		$('#baseDataModal').find("h4").html("添加基础数据");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnBaseDataSave").show();
		//基础数据编码值显示隐藏
		if(level == "0"){
			$("#baseDataForm").find("#divBaseCodeValue").hide();
			$("#baseDataForm").find("#divBaseCode").show();
		}else{
			$("#baseDataForm").find("#divBaseCodeValue").show();
			$("#baseDataForm").find("#divBaseCode").hide();
		}
		//重置验证
		initBaseDataFormValid().resetForm();
		$("#baseDataForm").find(".has-error").removeClass("has-error");
		//填充隐藏baseParentId
		$("#baseDataForm").find("#baseParentId").val(baseId);
	}
	//修改
	if(type == 2){
		$('#baseDataModal').find("h4").html("修改基础数据");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnBaseDataSave").show();
		//重置验证
		initBaseDataFormValid().resetForm();
		$("#baseDataForm").find(".has-error").removeClass("has-error");
		//查询基础数据详细信息
		var baseDataInfo = findbaseDataInfo(baseId);
		//基础数据编码值显示隐藏
		if(baseDataInfo.level == "0"){
			$("#baseDataForm").find("#divBaseCodeValue").hide();
			$("#baseDataForm").find("#divBaseCode").show();
		}else{
			$("#baseDataForm").find("#divBaseCodeValue").show();
			$("#baseDataForm").find("#divBaseCode").hide();
		}
		//填充表单数据
		CommonUtils.fillFormData("#baseDataForm",JSON.stringify(baseDataInfo));
		//填充隐藏baseId
		$("#baseDataForm").find("#baseId").val(baseId);
		//填充隐藏baseParentId
		$("#baseDataForm").find("#baseParentId").val(baseDataInfo.baseParentId);
	}
	//详情
	if(type == 3){
		$('#baseDataModal').find("h4").html("基础数据详情");
		//查询基础数据详细信息
		var baseDataInfo = findbaseDataInfo(baseId);
		//基础数据编码值显示隐藏
		if(baseDataInfo.level == "0"){
			$("#baseDataDetailForm").find("#divBaseCodeValue").hide();
		}else{
			$("#baseDataDetailForm").find("#divBaseCodeValue").show();
		}
		//填充表单数据
		CommonUtils.fillFormData("#detail",JSON.stringify(baseDataInfo));
		//性别
		$("#detail").find("#validFlag").html(validFlagFormatter(baseDataInfo.validFlag));
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnBaseDataSave").hide();
	}
	//隐藏值，基础数据判断提交数据的类型
	$('#baseDataModal').find("#modalType").val(type);
	//数据层级
	$("#baseDataForm").find("#level").val(level);
}

//保存基础数据
var saveBaseData=function(){
	//验证表单数据
	if (!$("#baseDataForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#baseDataForm');
	//添加或编辑类型变量
	var type = $('#baseDataModal').find("#modalType").val();
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/baseData/baseDataService/addBaseData";
		//设置基础数据级别
		formData.level = $('#baseDataForm').find("#level").val();
		formData.baseParentId = $("#baseDataForm").find("#baseParentId").val();
	}else{//修改
		url = "/xxm/rest/baseData/baseDataService/updateBaseData";
		formData.baseId = $("#baseDataForm").find("#baseId").val();
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
					$("#baseDataModal").modal("hide");
					//获取父节点Id
					var baseParentId = $("#baseDataForm").find("#baseParentId").val();
					if(null != baseParentId && "" != baseParentId && baseParentId != "0"){
						var level = $('#baseDataForm').find("#level").val();
						//刷新子项列表
						$("#tbChildBaseData_"+baseParentId).bootstrapTable('refresh',initChild(level,baseParentId));  
					}else{
						//刷新主列表
						$("#tbBaseData").bootstrapTable('refresh', init);
					}
			    	//重置表单
					$("#baseDataForm").get(0).reset();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
};

//删除基础数据
var deleteBaseData =function(baseIds,level,baseParentId){
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
		//记录删除基础数据的所有baseId
		var param = null;
		//判断baseIds是不是数组类型
		if(baseIds instanceof Array){
			param = {
				baseIds:baseIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(baseIds);
			param = {
				baseIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/baseData/baseDataService/deleteBaseData',
	    	data:JSON.stringify(param),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data.result){
	    			//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						if(null != baseParentId && "" != baseParentId && baseParentId != "0"){
							//刷新子项列表
							$("#tbChildBaseData_"+baseParentId).bootstrapTable('refresh',initChild(level,baseParentId));  
						}else{
							//刷新主列表
							$("#tbBaseData").bootstrapTable('refresh', init);
						}
					});
	    		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
	    	},
	    	error : errorFunc 
	    });
	})
}

//查询基础数据详细信息
var findbaseDataInfo=function(baseId) {
	var baseDataInfo = null;
	$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataInfo',
		data:JSON.stringify({baseId:baseId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				baseDataInfo = data;
			}
		},
		error : errorFunc 
	});
	return baseDataInfo;
}

//刷新缓存基础数据
var refreshBaseData = function(){
	$.ajax({url:'/xxm/rest/baseData/baseDataService/initBaseData',
		data:JSON.stringify({baseId:baseId}),
		type:'post',  
		dataType:'json',
		async:true,//同步请求
		contentType: "application/json",
		success:function(data) {  
			//消息提示款
			swal({title : "恭喜您",text :"缓存刷新成功！",type : "success"});
		},
		error : errorFunc  
	});
}

//初始化定时任务配置基础数据
var initTaskBaseData = function(){
	$.ajax({url:'/xxm/rest/baseData/baseDataService/initTaskBaseData',
		data:JSON.stringify({baseCode:"SYS_TASK_CRON_CONFIG"}),
		type:'post',  
		dataType:'json',
		async:true,//同步请求
		contentType: "application/json",
		success:function(data) {  
			//消息提示款
			swal({title : "恭喜您",text :"定时任务配置基础数据刷新成功！",type : "success"});
		},
		error : errorFunc 
	});
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}