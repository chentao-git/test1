$(function () {
	
    //初始化table并刷新数据
    $('#tbDataSupplement').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    $("#tbDataSupplement").bootstrapTable('refresh', init);
    });
    //时间控件初始化
	laydate({elem : "#createDate",event : "focus"});
	laydate({elem : "#lastUpdateDate",event : "focus"});
	
	CommonUtils.findBaseDataOption("#productionVl","PRODUCE_TYPE",1);
	

    
});


//查询的参数
var queryParams = function (params) {
	var customerVO={baseCustomerName:$("#baseCustomerName").val(),certNo:$("#certNo").val()};
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        applyInfoStatus: 2,
        createDate : $("#createDate").val(),
        lastUpdateDate : $("#lastUpdateDate").val(),
        loanContractNo:$("#loanContractNo").val(),
        customerVO:customerVO,
        productionVl : $("#productionVl").val()
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    var customerVO=row.customerVO;
    result += '<a class="btn btn-xs red J_menuItem" href="../main/project/project_data2.html?applyInfoId='+value+'&customerId='+customerVO.customerId+'">详情</a>';
    result += '<a class="btn btn-xs red J_menuItem" href="../main/project/data.html?applyInfoId='+value+'&customerId='+customerVO.customerId+'">归档</a>';
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
    url: '/xxm/rest/apply/applyService/findApplyInfoList', //请求后台的URL（*）
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
    uniqueId: "applyInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			 field:'applyInfoId',
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
			field: 'customerVO.baseCustomerName',
			title: '姓名',
			sortable : true
		},{
			field: 'customerVO.certTypeName',
			title: '证件类型',
			sortable: true
		},{
			field: 'customerVO.certNo',
			title: '证件号码',
			sortable : true
		},{
			field: 'productionName',
			title: '产品名字',
			sortable : true
		},{
			field: 'applyInfoStatusName',
			title: '结果',
			sortable : true
		},{
			field: 'createDate',
			title: '申请时间',
			formatter: dataFormatter
		},{
			field: 'lastUpdateDate',
			title: '完成时间',
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





