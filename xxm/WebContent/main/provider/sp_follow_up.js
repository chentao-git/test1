$(function () {
	//类型
	CommonUtils.findBaseDataOption("#follow","FOLLOW_UP_CATEGORY",1);
	//类型
	CommonUtils.findBaseDataOption("#followUpTypeVL","FOLLOW_UP_CATEGORY",1);
    //初始化table并刷新数据
    $('#baseCustomer').bootstrapTable(init);
    
    //表单验证初始化
//    initFormValid();
    


    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#baseCustomer").bootstrapTable('refresh', init);
    });
  //时间控件初始化
	laydate({elem : "#followUpDate",event : "focus"});
    
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#baseCustomer").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var followUpIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		followUpIds.push(rows[i].folowId);
        	}
        	//批量删除用户信息
        	deleteUser(followUpIds);
        }
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	save();//保存数据
    });
    
  
});

//获取url中的参数
function getUrlParam(name) {
 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
 var r = window.location.search.substr(1).match(reg); //匹配目标参数
 if (r != null) return unescape(r[2]); return null; //返回参数值
}
var spId = getUrlParam('spId');
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        spId:spId,
        followUpTypeVL: $("#follow").val(),//跟进类别
        followUpContent: $("#contentRegistration").val(),  //跟进内容
    };
    return temp;
};
//操作
var actionFormatter = function(value, row, index) {
    var result = "";
        result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteUser('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
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


//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/provider/providerService/findSpFollowUpList', //请求后台的URL（*）
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
    uniqueId: "folowId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'folowId', 
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'spName',
			title: 'sp商',
			sortable : true
		},{
			field: 'lastUpdateByName',
			title: '员工',
			sortable : true
		},{
			field: 'followUpTypeName',
			title: '跟进类别',
			sortable : true
		},{
			field: 'followUpContent',
			title: '跟进内容',
			sortable: true
		},{
			field: 'followUpDate',
			title: '跟进时间',
			sortable : true,
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
	var validate = $("#basicDataForm").validate({
		rules : {
			followUpDate : "required",
			followUpTypeVL : {
				required:true,
				min:1
			},
			followUpContent : "required"
		},
		messages : {
			followUpDate : e + "请输入跟进时间",
			followUpTypeVL : e + "请选择跟进类别",
			followUpContent : e + "请输入内容登记"
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
//日期格式化
var formatDate = function (date) { 
	var date = new Date(Date.parse(date)) //拿到的日期为字符串 将其转换为Date
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
}; 

var showModal =function(type,folowId){
	//重置表单
	$("#basicDataForm").get(0).reset();
	//弹出模式窗口
	$('#dataModal').modal('show'); 
	//设置角色下拉框宽度
	$("#basicDataForm").find("#useRoleIds_chosen").css("width","100%");
	$("#detail").find("#useRoleIdsDetails_chosen").css("width","100%");
	//添加
	if(type == 1){
		$('#dataModal').find("h4").html("添加跟进记录");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#basicDataForm").find(".has-error").removeClass("has-error");
		//类型
		CommonUtils.findBaseDataOption("#followUpTypeVL","FOLLOW_UP_CATEGORY",1);
	}
	//修改
	if(type == 2){
		$('#dataModal').find("h4").html("修改跟进记录");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#basicDataForm").find(".has-error").removeClass("has-error");
		//查询用户详细信息
		var dataInfo = findDataInfo(folowId);
		//时间格式转化下为yy-mm-dd格式
		dataInfo.followUpDate = formatDate(dataInfo.followUpDate);
		//填充表单数据
		CommonUtils.fillFormData("#basicDataForm",JSON.stringify(dataInfo));
		
		$("#followUpTypeVL option[value='"+dataInfo.followUpTypeVL+"']").attr("selected","selected");
		
		//填充隐藏Id
		$("#basicDataForm").find("#modelId").val(folowId);
	}
	//详情
	if(type == 3){
		$('#dataModal').find("h4").html("跟进记录详情");
		//查询用户详细信息
		var dataInfo = findDataInfo(folowId);
		dataInfo.followUpDate = formatDate(dataInfo.followUpDate);
		//填充表单数据
		CommonUtils.fillFormData("#detail",JSON.stringify(dataInfo));
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#dataModal').find("#modalType").val(type);
}


//保存用户
var save=function(){
	//验证表单数据
	if (!$("#basicDataForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#basicDataForm');
	//添加或编辑类型变量
	var type = $('#dataModal').find("#modalType").val();
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/provider/providerService/addSpFollowUp";
		formData.spId = spId;
	}else{//修改
		var folowId = $("#basicDataForm").find("#modelId").val(); 
		formData.folowId = folowId;//设置用户id
		url = "/xxm/rest/provider/providerService/updateSpFollowUp";
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
					$("#dataModal").modal("hide");
					//刷新列表
			    	$("#baseCustomer").bootstrapTable('refresh', init);
			    	//重置表单
					$("#basicDataForm").get(0).reset();
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc
	});
};


//查询用户详细信息
var findDataInfo=function(folowId) {
	var dataInfo = null;
	$.ajax({url:'/xxm/rest/provider/providerService/findSpFollowUp',
		data:JSON.stringify({folowId:folowId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				dataInfo = data;
			}
		},
		error : errorFunc 
	});
	return dataInfo;
}

//删除
var deleteUser =function(followUpIds){
	
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
//		debugger;
		//记录删除用户的所有Id
		var param = null;
		//判断followUpIds是不是数组类型
		if(followUpIds instanceof Array){
			param = {
					followUpIds:followUpIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(followUpIds);
			param = {
					followUpIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/provider/providerService/deleteSpFollowUp',
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
				    	$("#baseCustomer").bootstrapTable('refresh', init);
					});
	    		}
	    	},
    		error : errorFunc
	    });
	})
}






