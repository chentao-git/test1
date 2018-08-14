$(function () {
	//数据加载效果控件
    mask_fullscreen();
    //初始化table并刷新数据
    $('#tbdistributor').bootstrapTable(init);
    //类型
    CommonUtils.findBaseDataOption("#sell-select","YINGXIAO_LX",1);
    //表单验证初始化
    initFormValid();
    
    //下拉控件初始化
    initSelect();
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	mask_fullscreen();
    	//刷新列表
    	$("#tbdistributor").bootstrapTable('refresh', init);
    });
 
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#tbdistributor").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var sellIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		sellIds.push(rows[i].sellId);
        	}
        	//批量删除用户信息
        	deleteAffiliated(sellIds);
        }
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	saveAffiliated();//保存数据
    });
    
})
//数据加载动画
function mask_fullscreen(){
	$.mask_fullscreen(500);
}
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        companyName: $("#name").val(),//挂靠商名称
        address: $("#account").val()//操作公司
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteAffiliated('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'" + value + "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    return result;
}


//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}
//经销类型分类
var categoryVLFormatter = function(value){
	var categoryVL = "";
	if(value == 1){
		categoryVL ="小型车"
	}else if(value == 2){
		categoryVL ="中型车"
	}else {
		categoryVL ="卡车"
	}
    return categoryVL;
}

//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/sell/sellService/findSellList', //请求后台的URL（*）
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
    minimumCountColumns: 2,             //最少允许
    clickToSelect: false,                //是否启用点击的列数选中行
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "sellId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'sellId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'sellName',
			title: '公司负责人',
			sortable : true
		},{
			field: 'companyName',
			title: '公司名称',
			sortable : true
		},{
			field: 'workingGroup',
			title: '分类',
			sortable : true,
			formatter: categoryVLFormatter
		},{
			field: 'phone',
			title: '联系电话',
			sortable: true
			
		},{
			field: 'address',
			title: '公司地址',
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
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
    
}

//表单验证初始化
var initFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#sellForm").validate({
		rules : {
			sellId : "required",
			sellName: "required",
			companyName : "required",
			workingGroup:"required",
			phone:"required",
			address:"required"
		},
		messages : {
			sellName : e + "请输入负责人名称",
			companyName : e + "请输入公司名称",
			workingGroup : e + "请输入类型",
			phone : e + "请输入公司的联系电话",
			address : e + "请输入公司地址",
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
var showModal =function(type,companySellId){
	//重置表单
	$("#sellForm").get(0).reset();
	//弹出模式窗口
	$('#affiliatedModal').modal('show'); 
	//设置下拉框宽度
	$("#sellForm").find("#useRoleIds_chosen").css("width","100%");
	$("#detail").find("#useRoleIdsDetails_chosen").css("width","100%");
	//添加
	if(type == 1){
		$('#affiliatedModal').find("h4").html("添加汽车经销");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#sellForm").find(".has-error").removeClass("has-error");
		
	}
	//修改
	if(type == 2){
		$('#affiliatedModal').find("h4").html("修改汽车经销信息");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#sellForm").find(".has-error").removeClass("has-error");
		//查询用户详细信息
		var affiliatedInfo = findAffiliatedInfo(companySellId);
		
		//填充表单数据
		CommonUtils.fillFormData("#sellForm",JSON.stringify(affiliatedInfo));
		$("#sell-select option[value='"+affiliatedInfo.working_group+"']").attr("selected","selected");
	
		
		//填充隐藏Id
		$("#sellForm").find("#affiliatedId").val(companySellId);
	}
	//详情
	if(type == 3){
		$('#affiliatedModal').find("h4").html("经销商详情信息");
		//查询用户详细信息
		var affiliatedInfo = findAffiliatedInfo(companySellId);
		//填充表单数据
		//debugger
		CommonUtils.fillFormData("#detail",JSON.stringify(affiliatedInfo));
		//查询操作公司名字
		var info = findName(affiliatedInfo);
		var type = categoryVLFormatter(info.workingGroup);
		$("#detail").find("#operationCompanyVl").html(type);
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#affiliatedModal').find("#modalType").val(type);
}

//保存用户
var saveAffiliated=function(){
	//验证表单数据
	if (!$("#sellForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#sellForm');
	//添加或编辑类型变量
	var type = $('#affiliatedModal').find("#modalType").val();
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/sell/sellService/saveSell";
	}else{//修改
		var companySellId = $("#sellForm").find("#affiliatedId").val(); 
		formData.sellId = companySellId;//设置用户id
		url = "/xxm/rest/sell/sellService/updateSellInfo";
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
					$("#affiliatedModal").modal("hide");
					//刷新列表
			    	$("#tbdistributor").bootstrapTable('refresh', init);
			    	//重置表单
					$("#sellForm").get(0).reset();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
};

//删除
var deleteAffiliated =function(sellIds){
	
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
		//debugger;
		//记录删除用户的所有affiliatedId
		var param = null;
		//判断affiliatedId是不是数组类型
		if(sellIds instanceof Array){
			param = {
					sellIds:sellIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(sellIds);
			param = {
					sellIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/sell/sellService/deleteInfo',
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
				    	$("#tbdistributor").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc  
	    });
	})
}

//查询用户详细信息
var findAffiliatedInfo=function(companySellId) {
	var affiliatedInfo = null;
	$.ajax({url:'/xxm/rest/sell/sellService/querySellIdInfo',
		data:JSON.stringify({sellId:companySellId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				affiliatedInfo = data;
			}
		},
		error : errorFunc 
	});
	return affiliatedInfo;
}

var findName = function(affiliatedInfo){
	var info = null;
	$.ajax({url:'/xxm/rest/sell/sellService/querySellIdInfo',
		data:JSON.stringify(affiliatedInfo),
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


