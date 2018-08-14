$(function () {
	
    //初始化table并刷新数据
    $('#tbLoaninfo').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbLoaninfo").bootstrapTable('refresh', init);
    });
    
    //批量删除按钮点击事件
    $("#btnDelete").click(function(){
    	var rows= $("#tbDataSupplement").bootstrapTable('getSelections');  
        if(rows.length <=0 ){  
        	swal("很抱歉", "请您勾选您要删除的记录！","info");
        }else{
        	var applyInfoIds = new Array();
        	for(var i = 0; i < rows.length; i++){
        		applyInfoIds.push(rows[i].applyInfoId);
        	}
        	//批量删除申请信息
        	deleteApplyInfo(applyInfoIds);
        }
    });
    
    //添加按钮点击事件
//    $("#btnAdd").click(function(){
    	//显示弹出框
//    	showModal(1);
//    });
    
    //保存按钮点击事件
//    $("#btnSave").click(function(){
//    	addSignInfo();//保存数据
//    });
    
});


//查询的参数
var queryParams = function (params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        proposalNo:$("#proposalNo").val(),
        applyNo:$("#applyNo").val()
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    result += '<a class="btn btn-xs red J_menuItem" href="../main/dataEntering/loanInfo_edit.html?loanInfoId='+value+'&isEdit=1"><span class="glyphicon glyphicon-pencil"></span><label style="display:none;">修改</label></a>';
    result += '<a class="btn btn-xs red J_menuItem" href="../main/dataEntering/loanInfo_edit.html?loanInfoId='+value+'&isEdit=0"><span class="glyphicon glyphicon-search"></span><label style="display:none;">详情</label></a>';
    return result;
}


//时间格式化
var dataFormatter = function(value){
	if(null != value && "" != value){
		return value.replace(".0","");
	}
	return "";
}

//状态
var StatusName = function(value){
	if(value=="1"){
		return "已提交";
	}else if(value=="0"){
		return "草稿";
	}else{
		return "";
	}
}

//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/entering/enteringService/findLoanInfoList', //请求后台的URL（*）
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
    uniqueId: "loanInfoId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			checkbox: true
		}, {
			 field:'loanInfoId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'applicant',
			title: '申请人',
			sortable : true
		},{
			field: 'applyLoanTimes',
			title: '申请放款时间',
			sortable : true
		},{
			field: 'bankCode',
			title: '银行代码',
			sortable: true
		},{
			field: 'loanNo',
			title: '借据号',
			sortable: true
		},{
			field: 'applyNo',
			title: '申请书号',
			sortable: true
		},{
			field: 'loaninfoStatus',
			title: '状态',
			sortable: true,
			formatter: StatusName
		},{
			field: 'createBy',
			title: '创建人',
			sortable : true
		},{
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		},{
			field: 'loanReturnInfoVO.proposalno',
			title: '投保单号',
			sortable : true,
			visible: false
		},{
			field: 'loanReturnInfoVO.resultMessage',
			title: '响应信息描述',
			sortable : true,
			visible: false
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}





