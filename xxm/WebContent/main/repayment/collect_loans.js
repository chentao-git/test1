$(function () {
	//获取url传的值
	var repaymentPlanId = getUrlParam('repaymentPlanId');
	var type = getUrlParam('type');
	//初始化表单
	$('#businessOpportunity').bootstrapTable(init);
	if(repaymentPlanId !=null){
		//根据传过来的id值查到详情信息
		var info = findRepaymentPlan(repaymentPlanId);
		//console.log(info)
		//填充表单数据
		CommonUtils.fillFormData("#form",JSON.stringify(info));
		//填充隐藏Id
		$("#tableform").find("#applyInfoId").val(info.applyInfoId);		
		//填充表单数据
		$('#businessOpportunity').bootstrapTable('refresh', init);
	}else{
		
		//聚焦事件
		$('#search1').focus(function(){
			$('#search2').show();
		})
		//失焦事件
	    $('#search1').blur(function(){
			setTimeout(()=>{
				$('#search2').hide();
			},200)
		})
		//点击事件
		$('#search2').click(function(){
			var searchs = $('#search1').val();
			if(searchs === ""){
               $('#search1').focus();
			}else{
			var info = findRepaymentPlanss(searchs);
			//填充表单数据
			CommonUtils.fillFormData("#form",JSON.stringify(info));
			if(info !=null){
				//填充隐藏Id
				$("#tableform").find("#applyInfoId").val(info.applyInfoId);
			}
			//刷新列表
			$('#businessOpportunity').bootstrapTable('refresh', init);
			}
	    })
	}
	//时间控件初始化
	laydate({elem : "#collectingTime",event : "focus"});
	//类型（使用回调函数确认异步执行后再执行）
	CommonUtils.findBaseDataOption("#collectionMethodVL","COLLECTION_METHOD",1,"",fun);
	
	
	//保存按钮点击事件
    $("#btnSave").click(function(){
    	//验证表单数据
    	if (!$("#tableform").valid()) {
    		return;
        }
    	//获取表单元素所有数据集合
    	var formData = CommonUtils.getFormJson('#tableform');
    	var applyInfoId = $("#tableform").find("#applyInfoId").val();
    	formData.applyInfoId = applyInfoId;
    	console.log(111)
    	var url = "/xxm/rest/repayment/repaymentService/addCollection";
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
    					if(repaymentPlanId !=null){
    						window.location.href = "collect_loans.html?repaymentPlanId="+repaymentPlanId+"";
    					}else{
    						window.location.href = "collect_loans.html";
    					}
    			    	    
    				});
    	   		}else{
    	   			//消息提示款
    				swal({title : "很抱歉",text : data.msg,type : "error"});
    	   		}
    		},
    		error : errorFunc  
    	});
    });
    
});
function fun(){
	var type = getUrlParam('type');
		if(type === "1"){
			$("#collectionMethodVL option[value='1']").attr("selected","selected");
		}else if(type === "2"){
			$("#collectionMethodVL option[value='2']").attr("selected","selected");
		}
}
//查询的参数
var queryParams = function (params) {
	var repaymentPlanId = getUrlParam('repaymentPlanId');
	if(repaymentPlanId !=null){
	//根据传过来的id值查到详情信息
	var info = findRepaymentPlan(repaymentPlanId);
	
	var loanContractNo = info.loanContractNo;
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        loanContractNo : loanContractNo,
    };
	}else{
		var searchs = $('#search1').val();
		var loanContractNo = searchs;
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        limit: params.limit,   //页面大小
	        offset: params.offset,  //页码
	        loanContractNo : loanContractNo,
	    };
	}
    return temp;
};



//获取url中的参数
function getUrlParam(name) {
 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
 var r = window.location.search.substr(1).match(reg); //匹配目标参数
 if (r != null) return unescape(r[2]); return null; //返回参数值
}

var findRepaymentPlan = function(repaymentPlanId){
	var info = null;
	$.ajax({url:'/xxm/rest/repayment/repaymentService/findRepaymentPlan',
		data:JSON.stringify({repaymentPlanId:repaymentPlanId}),
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

var findRepaymentPlanss = function(repaymentPlanId){
	var info = null;
	$.ajax({url:'/xxm/rest/repayment/repaymentService/findRepaymentPlanss',
		data:JSON.stringify({loanContractNo:repaymentPlanId}),
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
    url: '/xxm/rest/repayment/repaymentService/findRepaymentPlans', //请求后台的URL（*）
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
    showColumns: false,                  //是否显示所有的列
    showRefresh: false,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: false,                //是否启用点击选中行
    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "repaymentPlanId",                 //每一行的唯一标识，一般为主键列
    showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			field: 'periods',
			title: '逾期期数',
			sortable : true
		},{
			field: 'returnPrincipal',
			title: '应还本金',
			sortable: true
		},{
			field: 'returnInterest',
			title: '应还利息',
			sortable : true
		},{
			field: '',
			title: '逾期天数',
			sortable : true
		},{
			field: 'returnDefaultInterest',
			title: '应付罚息',
			sortable : true
		},{
			field: 'returnDate',
			title: '应还时间',
			sortable : true,
			formatter: formatDate
		},{
			field: '',
			title: '以还金额',
			sortable : true
		},{
			field: '',
			title: '总计',
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

