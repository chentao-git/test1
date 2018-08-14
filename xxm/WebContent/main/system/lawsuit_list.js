$(function () {
	//时间控件初始化
	laydate({elem : "#applyDates",event : "focus",format: 'YYYY-MM-DD hh:mm:ss',istime:true});
	laydate({elem : "#storageDate",event : "focus",format: 'YYYY-MM-DD hh:mm:ss',istime:true});
	//初始化时间控件
	$("#toolbar .input-daterange.input-group").datepicker({todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,calendarWeeks:!0,autoclose:!0})
	//数据加载动画
	mask_fullscreen();
	//初始化下拉类型
	CommonUtils.findBaseDataOption("#select_filed","FILED_SELECT",1);
	
	//下拉控件初始化
    initSelect();
	//初始化table并刷新数据
    $('#tbTrailer').bootstrapTable(init);
	//表单验证初始化
    initFormValid();
  
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbTrailer").bootstrapTable('refresh', init);
    	$.mask_fullscreen(1000);
    });
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
    	$("#trailerForm").find("label[name]").html('');
    	showModal("1");//新增
    });
    //字段校验
	$.extend($.validator.messages, {
	    required: "这是必填字段！"
    })
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	addTrailer();//保存数据
    });
    
    //搜索申请信息
    $("#selectApply").click(function(){
    	//加载动画效果
    	mask_element();
    	var loanNumber=$("#loanContractNoinp").val();
    	if(loanNumber!=null){
    		var data={loanNumber:loanNumber};
    		$.ajax({
    			url:"/xxm/rest/lawsuit/lawsuitService/loanNumberQuery",
    			data:JSON.stringify(data),
    			type:'post',  
    			dataType:'json',  
    			contentType: "application/json",
    			success:function(data) {
    				if(data){
    					$("#applyInfoId").val(data.applyInfoId);
    					CommonUtils.fillFormData("#trailerForm",JSON.stringify(data));
    		   		}else{
    		   			//重置数据
    		   			$("#trailerForm").find("label[name]").html('');
    		   			//消息提示款
    		   			swal({title : "很抱歉",text : "数据未查到!!",type : "error"});
    		   		}
    			},
    			error : errorFunc
    		});
    	}
    });
    
});
//数据加载动画
function mask_fullscreen(){
	$.mask_fullscreen(500);
}
//指定搜索加载
function mask_element(){
	$.mask_element("#trailerForm", 1000);
}
//查询的参数
var queryParams = function (params) {
	var infoStatu = $("#select_filed").val();
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        loanNumber:$("#loanNumber").val(),
        customerName:$("#customerName").val(),
        endDate:$("#endDate").val(),
        startDate:$("#startDate").val(),
        infoStatus:infoStatu
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
	var result = "";
    var infoStatus = row.infoStatus;
    result += '<a class="btn btn-xs red J_menuItem" href="../main/system/lawsuit_Data.html?applyInfoId='+value+'">详情</a>';
    if(infoStatus == 1){
    	result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(2,'+value+')">诉讼审核</a>';
    }else if(infoStatus == 2){
    	result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(4,'+value+')">最终处理登记</a>';
    }
    result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(3,'+value+')">跟踪</a>';
    return result;
}

//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}

//时间格式化
var dataForm = function(value){
	var values = value;
	if(null != values.appliedDate && "" != values.appliedDate){
		var date = values.appliedDate;
		values.appliedDate = date.replace(".0","");
	}
	if(null != values.applyDate && "" != values.applyDate){
		var date = values.applyDate;
		values.applyDate = date.replace(".0","");
	}
	return values;
}

//格式化审核状态
var stuatsFormatter = function(value){
	if(value == 1){
		return "已申请";
	}
	if(value == 2){
		return "已审核";
	}
	if(value == "3"){
		return "处理完成";
	}
	return "";
}
//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/lawsuit/lawsuitService/applyInfoList', //请求后台的URL（*）
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
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数决定表格高度
    uniqueId: "filedId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [ {
		 field:'filedId',
         title: '操作',
         width: 200,
         align: 'center',
         valign: 'middle',
         formatter: actionFormatter
		},{
			field: 'loanNumber',
			title: '贷款编号',
			sortable : true
		},{
			field: 'customerName',
			title: '客户名称',
			sortable : true
		},{
			field: 'lawsuitVehicle',
			title: '车牌号码',
			sortable: true
		},{
			field: 'cardNumber',
			title: '身份证号码',
			sortable : true
		},{
			field: 'appliedAmount',
			title: '申请金额',
			sortable : true
		},{
			field: 'applyDate',
			title: '申请时间',
			formatter: dataFormatter
		},{
			field: 'infoStatus',
			title: '状态',
			width: 140,
			formatter: stuatsFormatter
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status,res){
		errorFunc(res,status);
	}
}
//保存
function addTrailer(){
	var type=$('#TrailerModal').find("#modalType").val();
	//验证表单数据
	if ((type==1&&!$("#trailerForm").valid())) {
		return;
    }
	var urls ="";
	if(type==2){
			//验证表单数据
			if (!$("#editTrailerForm").valid()) {
				return;
			}
			var filedId = $("#filedId").val();
			var data = CommonUtils.getFormJson($("#editTrailerForm"));
			data.filedId=filedId;
			urls="/xxm/rest/lawsuit/lawsuitService/saveAuditInfo"
		}else if(type == 3){
			var data = CommonUtils.getFormJson($("#editTrackingForm"));
			var fileds = $("#filedIds").attr("class");
			data.filed = fileds;
			urls="/xxm/rest/lawsuit/lawsuitService/addTrackingInfo";//添加跟踪信息
		}else if(type == 4){
			//验证表单数据
			if (!$("#finalTrackingForm").valid()) {return;}
			var data = CommonUtils.getFormJson($("#finalTrackingForm"));
			urls="/xxm/rest/lawsuit/lawsuitService/addRegisterInfo";
		}else {
			var data = CommonUtils.getFormJson($("#trailerForm"));
			var temp = $("#loanContractNoinp").val();
			if (temp == null || temp == ''){
				swal({title : "很抱歉",text : "请搜索数据侯提交！！",type : "error"});
				return;
			}
			data.loanNumber=temp;
			urls="/xxm/rest/lawsuit/lawsuitService/addApplyInfo";//添加诉讼申请信息
		}
		$.ajax({
			url:urls,
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
					//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						//关闭弹出框
						$("#TrailerModal").modal("hide");
						//刷新列表
				    	$("#tbTrailer").bootstrapTable('refresh', init);
				    	//重置表单
						$("#trailerForm").get(0).reset();
						$("#editTrailerForm").get(0).reset();
					});
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
			},
			error : errorFunc
		});
}
//所有弹出框隐藏
var shoModalhide = function(){
	$("#addTrailer-div").hide();
	$("#editTrailer-div").hide();
	$("#editTracking-div").hide();
	$("#finalTracking-div").hide();
}
//判断弹出框类型（新增、审核）并显示
var showModal =function(type,filedId,test){
	//隐藏区域
	shoModalhide();
	//弹出模式窗口
	$('#TrailerModal').modal('show'); 
	//添加
	if(type == 1){
		//重置表单
		$("#trailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("新建诉讼申请");
		
		
		//显示区域
		$("#addTrailer-div").show();
		initFormValid().resetForm();
	}
	//审核
	if(type == 2){
		//重置表单
		$("#editTrailerForm").get(0).reset();
		//清除页面信息
		$("#editTrailerForm").find("label[name]").html('');
		//查询审核数据
		selectAudit(filedId,type);
		$('#TrailerModal').find("h4").html("诉讼审核 ");
		//显示区域
		$("#editTrailer-div").show();
		//重置验证
		initFormValid().resetForm();
		//id
		$("#filedId").val(filedId);
	}
	//跟踪
	if(type == 3){
		//重置表单
		$("#editTrackingForm").get(0).reset();
		//清除页面信息
		$("#editTrackingForm").find("label[name]").html('');
		$('#TrailerModal').find("h4").html("诉讼单跟踪 ");
		//数据查询
		//查询审核数据
		selectAudit(filedId,type);
		//显示区域
		$("#editTracking-div").show();
		//重置验证
		initFormValid().resetForm();
	}
	//最终登记
	if(type == 4){
		//重置表单
		$("#finalTrackingForm").get(0).reset();
		//清除页面信息
		$("#finalTrackingForm").find("label[name]").html('');
		$('#TrailerModal').find("h4").html("最终处理");
		//数据查询
		selectAudit(filedId,type);
		//显示区域
		$("#finalTracking-div").show();
		//重置验证
		initFormValid().resetForm();
	}
	//隐藏值，判断提交数据的类型
	$('#TrailerModal').find("#modalType").val(type);
}

//搜索审核信息
function selectAudit (value,type){
	var data={filedId:value};
	$.ajax({
		url:"/xxm/rest/lawsuit/lawsuitService/auditInfoQuery",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data){
				var data = dataForm(data);
				$("#applyInfoId").val(data.applyInfoId);
				CommonUtils.fillFormData("#editTrailerForm",JSON.stringify(data));
				if(type == 3){
					$("#loanNumber-s").html(data.loanNumber);
					$("#filedIds").removeClass();
					$("#filedIds").addClass(data.filedId);
				}
				if(type == 4){
					CommonUtils.fillFormData("#finalTrackingForm",JSON.stringify(data));
				}
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : "数据未查到!!",type : "error"});
	   		}
		},
		error : errorFunc 
	});	
	
};

//表单验证初始化
var initFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#trailerForm").validate({
		rules : {
			applyName : "required",
			applyDate: "required"
		},
		messages : {
			applyName: "请输入申请人！",
			applyDate: "请输入时间！"
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

