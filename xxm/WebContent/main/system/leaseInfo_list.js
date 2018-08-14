$(function () {
	
    //初始化table并刷新数据
    $('#leaseInfo').bootstrapTable(init);
    //类型
	CommonUtils.findBaseDataOption("#carTypeVL","CAR_TYPE",1);
    //表单验证初始化
    initFormValid();
    
    //下拉控件初始化
    initSelect();

    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#leaseInfo").bootstrapTable('refresh', init);
    });
 
    
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#leaseInfo").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var leaseInfoIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		leaseInfoIds.push(rows[i].baseLeaseInfoId);
        	}
        	//批量删除用户信息
        	deleteLeaseInfo(leaseInfoIds);
        }
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	saveLeaseInfo();//保存数据
    });
    
});
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        leaseholdUse: $("#name").val(),//租赁使用用途
        leaseholdArea: $("#account").val()//租赁物使用区域
    };
    return temp;
};
//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteLeaseInfo('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"showModal(3,'" + value + "')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    return result;
}
//车产类型name
var operationCompanyVLFormatter = function(value){
	var leaseInfo = {carTypeVL:value};
	var info = findName(leaseInfo);
	var operationCompanyVL = "";
	carTypeVL = info.carTypeName;
    return carTypeVL;
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
    url: '/xxm/rest/leaseInfo/leaseInfoService/findLeaseInfoList', //请求后台的URL（*）
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
    uniqueId: "baseLeaseInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'baseLeaseInfoId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'carName',
			title: '车名称',
			sortable : true
		},{
			field: 'carTypeVL',
			title: '车产类型',
			sortable : true,
			formatter: operationCompanyVLFormatter
		},{
			field: 'manufacturer',
			title: '制造商',
			sortable : true
//			formatter: categoryVLFormatter
		},{
			field: 'brand',
			title: '品牌',
			sortable: true
			
		},{
			field: 'model',
			title: '型号',
			sortable : true
		}, {
			field: 'leaseholdUse',
			title: '租赁使用用途',
			sortable : true
		},{
			field: 'leaseholdArea',
			title: '租赁物使用区域',
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
	var validate = $("#leaseInfoForm").validate({
		rules : {
			carName : "required",
			carTypeVL: "required",
			manufacturer:"required",
			brand:"required",
			model:"required",
			engineNumber:"required",
			leaseholdUse:"required",
			leaseholdArea:"required",
			frameNumber:"required",
			drivingForm:"required",
			engineModel:"required",
			enginePower:"required",
			carColor:"required",
			plateNumber:"required",
			otherInstructions:"required"
		},
		messages : {
			carName : e + "请输入凭租车名称",
			carTypeVL : e + "请选择车产类型",
			manufacturer : e + "请输入制造商",
			brand : e + "请输入品牌",
			model : e + "请输入型号",
			engineNumber : e + "请输入发动机号",
			leaseholdUse: e + "请输入使用用途",
			leaseholdArea: e + "请输入使用区域",
			frameNumber: e + "请输入车架号",
			drivingForm: e + "请输入驱动形式",
			engineModel: e + "请输入发动机型号",
			enginePower: e + "请输入发动机功率",
			carColor: e + "请输入车颜色",
			plateNumber: e + "请输入车牌号",
			otherInstructions: e + "请输入其他配置说明",
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
var showModal =function(type,baseLeaseInfoId){
	//重置表单
	$("#leaseInfoForm").get(0).reset();
	//弹出模式窗口
	$('#leaseInfoModal').modal('show'); 
	//设置角色下拉框宽度
	$("#leaseInfoForm").find("#useRoleIds_chosen").css("width","100%");
	$("#detail").find("#useRoleIdsDetails_chosen").css("width","100%");
	//添加
	if(type == 1){
		$('#leaseInfoModal').find("h4").html("添加凭租物");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#leaseInfoForm").find(".has-error").removeClass("has-error");
		//类型
		CommonUtils.findBaseDataOption("#carTypeVL","CAR_TYPE",1);
	}
	//修改
	if(type == 2){
		$('#leaseInfoModal').find("h4").html("修改凭租物信息");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
		//重置验证
		initFormValid().resetForm();
		$("#leaseInfoForm").find(".has-error").removeClass("has-error");
		//根据id查询用户详细信息
		var leaseInfo = findLeaseInfo(baseLeaseInfoId);
		//填充表单数据
		CommonUtils.fillFormData("#leaseInfoForm",JSON.stringify(leaseInfo));
		$("#carTypeVL option[value='"+leaseInfo.carTypeVL+"']").attr("selected","selected");
		
		//填充隐藏Id
		$("#leaseInfoForm").find("#leaseInfoId").val(baseLeaseInfoId);
	}
	//详情
	if(type == 3){
		$('#leaseInfoModal').find("h4").html("凭租物详情");
		//根据id查询用户详细信息
		var leaseInfo = findLeaseInfo(baseLeaseInfoId);
		//填充表单数据
		CommonUtils.fillFormData("#detail",JSON.stringify(leaseInfo));
		//查询操作公司名字
		var info = findName(leaseInfo);
		$("#detail").find("#carTypeVL").html(info.carTypeName);
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#leaseInfoModal').find("#modalType").val(type);
}

//保存用户
var saveLeaseInfo=function(){
	//验证表单数据
	if (!$("#leaseInfoForm").valid()) {
		return;
    }
	//获取表单元素所有数据集合
	var formData = CommonUtils.getFormJson('#leaseInfoForm');
	//添加或编辑类型变量
	var type = $('#leaseInfoModal').find("#modalType").val();
	console.log(type);
	var url = null;
	if(type == "1"){//添加
		url = "/xxm/rest/leaseInfo/leaseInfoService/addLeaseInfo";
	}else{//修改
		var baseLeaseInfoId = $("#leaseInfoForm").find("#leaseInfoId").val(); 
		formData.baseLeaseInfoId = baseLeaseInfoId;//设置用户id
		url = "/xxm/rest/leaseInfo/leaseInfoService/updateLeaseInfoVO";
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
					$("#leaseInfoModal").modal("hide");
					//刷新列表
			    	$("#leaseInfo").bootstrapTable('refresh', init);
			    	//重置表单
					$("#leaseInfoForm").get(0).reset();
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
var deleteLeaseInfo =function(leaseInfoIds){
	
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
		//记录删除用户的所有leaseInfoId
		var param = null;
		//判断leaseInfoIds是不是数组类型
		if(leaseInfoIds instanceof Array){
			param = {
					leaseInfoIds:leaseInfoIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(leaseInfoIds);
			param = {
					leaseInfoIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/leaseInfo/leaseInfoService/deleteLeaseInfo',
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
				    	$("#leaseInfo").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc 
	    });
	})
}

//查询用户详细信息
var findLeaseInfo=function(baseLeaseInfoId) {
	var leaseInfo = null;
	$.ajax({url:'/xxm/rest/leaseInfo/leaseInfoService/findLeaseInfoVO',
		data:JSON.stringify({baseLeaseInfoId:baseLeaseInfoId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				leaseInfo = data;
			}
		},
		error : errorFunc 
	});
	return leaseInfo;
}

var findName = function(leaseInfo){
	var info = null;
	$.ajax({url:'/xxm/rest/leaseInfo/leaseInfoService/findcarTypeName',
		data:JSON.stringify(leaseInfo),
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
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}



