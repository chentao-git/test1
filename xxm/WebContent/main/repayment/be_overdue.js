$(function () {
	//加载效果
	mask_fullscreen();
    //初始化table并刷新数据
    $('#businessOpportunity').bootstrapTable(init);
    
    //表单验证初始化
//    initFormValid();
    
    //下拉控件初始化
    initSelect();

    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#businessOpportunity").bootstrapTable('refresh', init);
    });
  //时间控件初始化
//	laydate({elem : "#publishdate",event : "focus"});
//	laydate({elem : "#haltSalesdate",event : "focus"});
    
	CommonUtils.findBaseDataOption("#repaymentMethod","PAYMENT_METHOD",1);
    
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
//var baseCustomerId = getUrlParam('baseCustomerId');
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        loanContractNo : $("#loanContract").val(),
//        productName: $("#productname").val(),//产品名称
//        publishDate: $("#publishdate").val(),  //产品发布时间
//        haltSalesDate: $("#haltSalesdate").val(), //产品停售时间
//        repaymentMethodVl: $("#repaymentMethod").val() //还款方式
    };
    return temp;
};
//操作
var actionFormatter = function(value, row, index) {
    var result = "";
        result += '<a class="J_menuItem" href="repayment/collect_loans.html?repaymentPlanId='+value+'">催收</a>';
        result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(" + value + ")\" >还款</a>";
    return result;
}

//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}
//日期格式化
var formatDate = function (date) { 
	if(date.toString()!=null && date.toString()!=''){
		var date = new Date(Date.parse(date)) //拿到的日期为字符串 将其转换为Date
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? '0' + m : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    return y + '-' + m + '-' + d; 
	}else{
		return '';
	}
}; 


//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/repayment/repaymentService/findRepaymentPlanList', //请求后台的URL（*）
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
    uniqueId: "repaymentPlanId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			 field:'repaymentPlanId', 
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'loanContractNo',
			title: '贷款编号',
			sortable : true
		},{
			field: 'periods',
			title: '逾期期数',
			sortable : true
		},{
			field: 'baseCustomerName',
			title: '姓名',
			sortable : true
			
		},{
			field: 'returnPrincipal',
			title: '应还本金(元)',
			sortable: true
		},{
			field: 'returnInterest',
			title: '应还利息(元)',
			sortable : true
		},{
			field: 'returnDefaultInterest',
			title: '应付罚息(元)',
			sortable : true
		},{
			field: 'returnDate',
			title: '应还时间',
			sortable : true,
			formatter: formatDate
		},{
			field: '',
			title: '还款状态',
			sortable : true
		},{
			field: '',
			title: '以还金额',
			sortable : true
		},{
			field: '',
			title: '剩余应还',
			sortable : true
		},{
			field: '',
			title: '逾期天数',
			sortable : true
		},{
			field: 'collectionCount',
			title: '催收次数',
			sortable : true
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}

var showModal = function(repaymentPlanId){
	 var repaymentPlanId = repaymentPlanId;
	swal({
		title : "确信还款？",
		text : "还款后你就没有逾期记录了！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "确信",
		closeOnConfirm : false
	}, function() {
	 
	   $.ajax({url:'/xxm/rest/repayment/repaymentService/updateRepaymentPlan',
	    	data:JSON.stringify({repaymentPlanId:repaymentPlanId}),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data.result){
	    			//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						//刷新列表
				    	$("#businessOpportunity").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc  
	    });
   })
}
	
//表单验证初始化
var initFormValid = function(){
	var e = "<i class='fa fa-times-circle'></i> ";
	var validate = $("#basicDataForm").validate({
		rules : {
			loanAmount : "required",
			interestRate: "required",
			focusOnProductVL : "required",
			expectedTransactionTime:"required",
			actualTransactionTime:"required",
			remark:"required"
		},
		messages : {
			loanAmount : e + "请输入预计贷款额",
			interestRate : e + "请输入预计利率",
			focusOnProductVL : e + "请输入关注产品",
			expectedTransactionTime : e + "请输入预计成交时间",
			actualTransactionTime : e + "请输入实际成交时间",
			remark : e + "内容备注"
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







//删除
var deleteUser =function(ids){
	
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
		//判断ids是不是数组类型
		if(ids instanceof Array){
			param = {
					ids:ids
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(ids);
			param = {
					ids:arr
			};
		}
	    $.ajax({url:'/xxm/rest/customerFile/customerFileService/deleteServiceRecord',
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
				    	$("#businessOpportunity").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc  
	    });
	})
}
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}




