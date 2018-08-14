$(function () {
	
    //初始化table并刷新数据
    $('#baseCustomer').bootstrapTable(init);
    
    //表单验证初始化
//    initFormValid();
    
    //下拉控件初始化
    initSelect();

    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//加载效果
    	mask_fullscreen();
    	//刷新列表
    	$("#baseCustomer").bootstrapTable('refresh', init);
    });
 
    
    
    //添加按钮点击事件
    $("#btnAdd").click(function(){
//    	window.location.href = "addBase_customer.html?type=";
    	$('#J_menuItem').attr('href','customer/addBase_customer.html?type=');
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#baseCustomer").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var customerIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		customerIds.push(rows[i].baseCustomerId);
        	}
        	//批量删除用户信息
        	deleteUser(customerIds);
        }
    });
    
  
});
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        baseCustomerName: $("#baseCustomerName").val(),//客户名称
    };
    return temp;
};
//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    if(row.customerLevel === '1' || row.customerLevel === '2'){
        result += "<a href='customer/updateBase_customer.html?baseCustomerId="+ value + "' class='J_menuItem btn btn-xs' title='编辑'><span class='glyphicon glyphicon-pencil'></span><label style='display:none'>编辑客户</label></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteUser('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
        result += "<a href='customer/customer_data.html?baseCustomerId="+ value + "' class='J_menuItem btn btn-xs' title='查看'><span class='glyphicon glyphicon-search'></span><label style='display:none'>客户详情</label></a>";
       
        if(row.lockManName === null || row.lockManName === ''){
        	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"updateName(2,'" + value + "')\"  title='锁定'><span class='glyphicon glyphicon-eye-open'></span></a>";
        }else{
        	result += "<a class='J_menuItem btn btn-xs'  href='customer/customer_schedule.html?baseCustomerId="+ value + "' title='跟进'><span class='glyphicon glyphicon-phone-alt'></span><label style='display:none'>进度</label></a>";
            result += "<a class='J_menuItem btn btn-xs'  href='customer/business_opportunity.html?baseCustomerId="+ value +"'  title='商机'><span class='glyphicon glyphicon-calendar'></span><label style='display:none'>商机</label></a>";
   	     result += "<a href='javascript:;' class='btn btn-xs red'onclick=\"updateName(1,'" + value + "')\"  title='释放'><span class='glyphicon glyphicon-eye-close'></span></a>";
        	
        }
       
    }else if(row.customerLevel === '3'){
    	result += "<a href='customer/updateBase_customer.html?baseCustomerId="+ value + "' class='J_menuItem btn btn-xs' title='编辑'><span class='glyphicon glyphicon-pencil'></span><label style='display:none'>编辑客户</label></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteUser('" + value + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
        result += "<a href='customer/customer_data.html?baseCustomerId="+ value + "' class='J_menuItem btn btn-xs' title='查看'><span class='glyphicon glyphicon-search'></span><label style='display:none'>客户详情</label></a>";
        if(row.lockManName === null || row.lockManName === ''){
        	result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"updateName(2,'" + value + "')\" title='锁定'><span class='glyphicon glyphicon-eye-open'></span></a>";
        }else{
        	result += "<a class='J_menuItem btn btn-xs'  href='customer/customer_schedule.html?baseCustomerId="+ value + "' title='跟进'><span class='glyphicon glyphicon-phone-alt'></span><label style='display:none'>进度</label></a>";
            result += "<a class='J_menuItem btn btn-xs'  href='customer/business_opportunity.html?baseCustomerId="+ value +"'  title='商机'><span class='glyphicon glyphicon-calendar'></span><label style='display:none'>商机</label></a>";
            result += "<a class='J_menuItem btn btn-xs'  href='customer/service_record.html?baseCustomerId=" + value + "'  title='服务'><span class='glyphicon glyphicon-equalizer'></span><label style='display:none'>服务</label></a>";
       	 result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"updateName(1,'" + value + "')\" title='释放'><span class='glyphicon glyphicon-eye-close'></span></a>";
        	
        }
    }
    return result;
}
var statusFormatter = function(value){
	var paperType = "";
    if (value == "1") { 
    	paperType = "潜在客户";
    }else if(value == "2") { 
    	paperType = "意向客户";
    }else if(value == "3"){
    	paperType = "签约客户";
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
    url: '/xxm/rest/customerFile/customerFileService/findCustomerList', //请求后台的URL（*）
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
    uniqueId: "baseCustomerId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'baseCustomerId', 
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'baseCustomerName',
			title: '客户名称',
			sortable : true
		},{
			field: 'lockManName',
			title: '锁定人',
			sortable : true
		},{
			field: 'mobileNo',
			title: '联系电话',
			sortable : true
		},{
			field: 'createDate',
			title: '首次贷款日期',
			sortable: true,
			formatter:dataFormatter
		},{
			field: 'customerLevel',
			title: '客户类型',
			sortable : true,
			formatter: statusFormatter
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

var updateName =function(type,baseCustomerId){
	var extendField1 ='';
	if(type === 1){
		//释放 TODO(根据基础客户id清空锁定人 锁上 跟进, 服务, 商机,操作)
		//用扩展字段来接收操作类型
		var extendField1 = type; 
	}else if(type === 2){
		//锁定 TODO(根据基础客户id给出锁定人 解锁 跟进, 服务, 商机,操作)
		//用扩展字段来接收操作类型
		var extendField1 = type;
	}
	var formData = {};
	formData.extendField1 = extendField1;
	formData.baseCustomerId = baseCustomerId;
	//请求后台
	$.ajax({
		url:"/xxm/rest/customerFile/customerFileService/updateLastUpdateByName",
		data:JSON.stringify(formData),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//刷新列表
			    	$("#baseCustomer").bootstrapTable('refresh', init);
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
}
//删除
var deleteUser =function(customerIds){
	
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
		//判断customerIds是不是数组类型
		if(customerIds instanceof Array){
			param = {
					customerIds:customerIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(customerIds);
			param = {
					customerIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/baseCustomer/baseCustomerService/deleteBaseCustomer',
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
	    		}else{
	    			swal({title : "很抱歉",text : data.msg,type : "error"});
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







