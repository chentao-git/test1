$(function () {
	
    //初始化table并刷新数据
    $('#tbSignInfo').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbSignInfo").bootstrapTable('refresh', init);
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
    $("#btnAdd").click(function(){
    	//显示弹出框
    	showModal(1);
    });
    
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	addSignInfo();//保存数据
    });
    
  //响应按钮点击事件
    $("#returnTableBu").click(function(){
    	var node=$("#returnTable");
    	if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
		　　	node.show();　
		　　	$(this).text("隐藏预审响应历史");
		}else{
		　　	node.hide();
		　　	$(this).text("显示预审响应历史");
		}
    });
    
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
    var customerVO=row.customerVO;
//    result += '<a class="btn btn-xs red J_menuItem" href="../main/apply/data_supplement.html?applyInfoId='+value+'&customerId='+customerVO.customerId+'"><span class="glyphicon glyphicon-pencil"></span><label style="display:none;">修改</label></a>';
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='修改'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(3,'" + value + "')\" title='详情'><span class='glyphicon glyphicon-search'></span></a>";
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
    url: '/xxm/rest/entering/enteringService/findSignInfoList', //请求后台的URL（*）
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
			checkbox: true
		}, {
			 field:'signInfoId',
             title: '操作',
             width: 100,
             align: 'center',
             valign: 'middle',
             formatter: actionFormatter
		}, {
			field: 'proposalNo',
			title: '投保单号',
			sortable : true
		},{
			field: 'applyNo',
			title: '申请书编号',
			sortable : true
		},{
			field: 'createByName',
			title: '创建人',
			sortable: true
		},{
			field: 'createDate',
			title: '创建时间',
			formatter: dataFormatter
		},{
			field: 'lastUpdateByName',
			title: '修改人',
			sortable : true
		}, {
			field: 'lastUpdateDate',
			title: '修改时间',
			formatter: dataFormatter
		}, {
			field: 'signReturnInfoVO.applState',
			title: '当前申请状态',
			sortable: true,
			visible: false
		},{
			field: 'signReturnInfoVO.loanAmt',
			title: '银行审批金额',
			sortable: true,
			visible: false
		},{
			field: 'signReturnInfoVO.intAdjPct',
			title: '正常利率上浮比例',
			sortable: true,
			visible: false
		},{
			field: 'signReturnInfoVO.resultMessage',
			title: '响应信息描述',
			sortable: true,
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

//删除申请信息
var deleteApplyInfo =function(applyInfoIds){
	//删除数据模式窗口
	swal({
		title : "您确定要删除这些申请信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "删除",
		closeOnConfirm : false
	}, function() {
		//记录删除申请信息的所有applyInfoId
		var param = null;
		//判断applyInfoIds是不是数组类型
		if(applyInfoIds instanceof Array){
			param = {
				applyInfoIds:applyInfoIds
			};
		}else{
			//定义一个数组
			var arr = new Array();
			arr.push(applyInfoIds);
			param = {
				applyInfoIds:arr
			};
		}
	    $.ajax({url:'/xxm/rest/apply/applyService/deleteApplyInfo',
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
				    	$("#tbDataSupplement").bootstrapTable('refresh', init);
					});
	    		}
	    	},
	    	error : errorFunc 
	    });
	})
}

//判断弹出框类型（修改、新增、详情）并显示
var showModal =function(type,signInfoId){
	//重置表单
	$("#signInfoForm").get(0).reset();
	//弹出模式窗口
	$('#signInfoModal').modal('show'); 
	//添加
	if(type == 1){
		$('#signInfoModal').find("h4").html("新增签约状态查询");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
	}
	//修改
	if(type == 2){
		$('#signInfoModal').find("h4").html("修改签约状态查询");
		//查询用户详细信息
		var SignInfoVO=findSignInfo(signInfoId);
		CommonUtils.fillFormData("#signInfoForm",JSON.stringify(SignInfoVO));
		$("#signInfoId").val(signInfoId);
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
	}
	//详情
	if(type == 3){
		$('#signInfoModal').find("h4").html("申请详情");
		//查询用户详细信息
		var SignInfoVO=findSignInfo(signInfoId);
		$("#detail #proposalNo").text(SignInfoVO.proposalNo);
		$("#detail #applyNo").text(SignInfoVO.applyNo);
		//响应信息
		$("#returnTable").hide();
		var html='';
		var returnList=SignInfoVO.signReturnInfoList;
		if(returnList!=null&&returnList.length>0){
			for(var i=0;i<returnList.length;i++){
				html=html+'<tr>';
				html=html+'<td>'+(returnList[i].applState==null?'':returnList[i].applState)+'</td>';
				html=html+'<td>'+(returnList[i].loanAmt==null?'':returnList[i].loanAmt)+'</td>';
				html=html+'<td>'+(returnList[i].intAdjPct==null?'':returnList[i].intAdjPct)+'</td>';
				html=html+'<td>'+(returnList[i].resultMessage==null?'':returnList[i].resultMessage)+'</td>';
				html=html+'<td>'+(returnList[i].createByName==null?'':returnList[i].createByName)+'</td>';
				html=html+'<td>'+(returnList[i].createDate==null?'':returnList[i].createDate)+'</td>';
				html=html+'</tr>';
			}
		}else{
			html=html+'<tr>';
			html=html+'<td colspan="6">无历史信息！</td>';
			html=html+'</tr>';
		}
		$("#returnTable").append(html);
		
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#signInfoModal').find("#modalType").val(type);
}

//新增签约信息
function addSignInfo(){
	var isPass =false;
	//获取表单元素所有数据集合
	var data = CommonUtils.getTagObj($("#signInfoForm :input[name]"));
	data.signInfoId=$("#signInfoId").val();
	$.ajax({
		url:"/xxm/rest/entering/enteringService/addSignInfo",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				isPass=true;
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					//关闭弹出框
					$("#signInfoModal").modal("hide");
					//刷新列表
					$("#tbSignInfo").bootstrapTable('refresh', init);
				});
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
	return isPass;
}

//查询签约信息
function findSignInfo(signInfoId){
	var vo;
	var data={signInfoId:signInfoId};
	$.ajax({
		url:"/xxm/rest/entering/enteringService/findSignInfo",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {
			if(data){
				vo=data;
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc
	});
	return vo;
}




