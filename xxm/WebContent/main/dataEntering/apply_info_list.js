$(function () {
	//时间控件初始化
	laydate({elem : "#birthDate",event : "focus"});
	//流程标志
	CommonUtils.findBaseDataOption("#mktProdType","MKT_PROD_TYPE",1);
	//合作商产品类型
	CommonUtils.findBaseDataOption("#cooprProdType","COOPRPROD_TYPE",1);
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
	//证件类型
	CommonUtils.findBaseDataOption("#certType","BAQSNG_PAPER_TYPE",1);
	
    //初始化table并刷新数据
    $('#tbDataSupplement').bootstrapTable(init);
    
    //查询Button的点击事件
    $("#btnQuery").click(function(){
    	//刷新列表
    	$("#tbDataSupplement").bootstrapTable('refresh', init);
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
    	addApply();//保存数据
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
	var customerVO={baseCustomerName:$("#baseCustomerName").val()};
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        isValid: 2,
        loanContractNo:$("#loanContractNo").val(),
        customerVO:customerVO
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    var customerVO=row.customerVO;
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(3,'" + value + "')\" title='详情'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"showModal(2,'" + value + "')\" title='修改'><span class='glyphicon glyphicon-pencil'></span></a>";
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
var statusName = function(value){
	if("-1" == value){
		return "请求失败";
	}else if("0" == value){
		return "请求成功";
	}else if("1" == value){
		return "已提交";
	}else{
		return "";
	}
}

//bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/entering/enteringService/findApplyInfoList', //请求后台的URL（*）
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
			field: 'customerVO.mobileNo',
			title: '手机号',
			sortable : true
		}, {
			field: 'createDate',
			title: '申请时间',
			formatter: dataFormatter
		}, {
			field: 'applyInfoStatus',
			title: '状态',
			sortable : true,
			formatter: statusName
		}, {
			field: 'rejudicationReturnVO.prejudicNo',
			title: '预审单号',
			sortable : true,
			visible: false
		}, {
			field: 'rejudicationReturnVO.resultMessage',
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
var showModal =function(type,applyId){
	//重置表单
	$("#applyForm").get(0).reset();
	//弹出模式窗口
	$('#applyModal').modal('show'); 
	//id
	$('#applyId').val(''); 
	//添加
	if(type == 1){
		$('#applyModal').find("h4").html("银行预审申请");
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
	}
	//修改
	if(type == 2){
		$('#applyModal').find("h4").html("修改银行预审申请");
		//查询用户详细信息
		findApplyInfo(applyId,type);
		$("#applyId").val(applyId);
		//显示区域
		$("#edit").show();
		$("#detail").hide();
		//保存按钮显示
		$("#btnSave").show();
	}
	//详情
	if(type == 3){
		$('#applyModal').find("h4").html("申请详情");
		//查询用户详细信息
		findApplyInfo(applyId,type);
		//显示区域
		$("#edit").hide();
		$("#detail").show();
		//保存按钮隐藏
		$("#btnSave").hide();
		//隐藏响应历史
		$("#returnTable").hide();
	}
	//隐藏值，用户判断提交数据的类型
	$('#applyModal').find("#modalType").val(type);
}

//新增申请信息
function addApply(){
	var isPass =false;
	//获取表单元素所有数据集合
	var data = {};
	var customerVO = CommonUtils.getTagObj($("#applyForm :input[name]"));
	customerVO.baseCustomerSex=$('#baseCustomerSex-div input[name="baseCustomerSex"]:checked').val();//性别
	data.mktProdType=$("#mktProdType").val();
	data.cooprProdType=$("#cooprProdType").val();
	data.customerVO=customerVO;
	data.applyInfoId=$("#applyId").val();
	$.ajax({
		url:"/xxm/rest/entering/enteringService/addApply",
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
					$("#applyModal").modal("hide");
					//刷新列表
			    	$("#tbDataSupplement").bootstrapTable('refresh', init);
			    	//重置表单
					$("#applyForm").get(0).reset();
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

//查询申请信息
function findApplyInfo(applyInfoId,type){
	var data={applyInfoId:applyInfoId};
	$.ajax({
		url:"/xxm/rest/entering/enteringService/findApplyInfo",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data){
				if(type=="3"){//详情
					$("#detail #mktProdTypeName").text(data.mktProdTypeName);
					$("#detail #cooprProdTypeName").text(data.cooprProdTypeName);
					//客户信息
					var customerVO=data.customerVO;
					if(customerVO){
						CommonUtils.fillFormData("#detail",JSON.stringify(customerVO));
					}
					//响应信息
					var returnList=data.rejudicationReturnList;
					if(returnList!=null&&returnList.length>0){
						var html='';
						for(var i=0;i<returnList.length;i++){
							html=html+'<tr>';
							html=html+'<td>'+(returnList[i].prejudicNo==null?'':returnList[i].prejudicNo)+'</td>';
							html=html+'<td>'+(returnList[i].resultMessage==null?'':returnList[i].resultMessage)+'</td>';
							html=html+'<td>'+(returnList[i].createByName==null?'':returnList[i].createByName)+'</td>';
							html=html+'<td>'+(returnList[i].createDate==null?'':returnList[i].createDate)+'</td>';
							html=html+'</tr>';
						}
						$("#returnTable").append(html);
					}
				}else if(type=="2"){//修改
//					$("#applyForm #mktProdType").val(data.mktProdType);
//					$("#applyForm #cooprProdType").val(data.cooprProdType);
					$("#mktProdType option[value='"+data.mktProdType+"']").prop("selected",true);
					$("#cooprProdType option[value='"+data.cooprProdType+"']").prop("selected",true);
					//客户信息
					var customerVO=data.customerVO;
					if(customerVO){
						CommonUtils.fillFormData("#applyForm",JSON.stringify(customerVO));
					}
				}
	   		}else{
	   			//消息提示款
				swal({title : "很抱歉",text : data.msg,type : "error"});
	   		}
		},
		error : errorFunc 
	});
}




