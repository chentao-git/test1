$(function () {
	
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
    
});


//查询的参数
var queryParams = function (params) {
	var customerVO={baseCustomerName:$("#baseCustomerName").val()};
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,//页码
        applyInfoStatus : 15,
        loanContractNo:$("#loanContractNo").val(),
        customerVO:customerVO
    };
    return temp;
};

//操作
var actionFormatter = function(value, row, index) {
    var result = "";
    var customerVO=row.customerVO;
    var src = "../main/project/project_data2.html?applyInfoId={0}&customerId={1}&taskDefinitionKey={2}&taskName={3}&taskId={4}&type={5}";
    src1 = src.format(value,customerVO.customerId,row.taskDefinitionKey,row.taskName,row.taskId,"view");
    result += '<a class="btn btn-xs red J_menuItem" href="'+src1+'">详情 <span class="glyphicon glyphicon-search"></span></a>';
    result += '<a class="btn btn-xs red J_menuItem" href="install/install_list.html?applyInfoId='+value+'">安装 </a>';
    result += '<a class="btn btn-xs red" href="javascript:;" onclick="showModal(' + value + ')">打印合同 </a>';
    result += '<a class="btn btn-xs red" href="javascript:;" onclick="showMD(' + value + ')">合同上传 </a>';
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
    url: '/xxm/rest/apply/applyService/findApplyList', //请求后台的URL（*）
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
			field: 'productionName',
			title: '产品名字',
			sortable : true
		},{
			field: 'applyInfoStatus',
			title: '结果',
			sortable : true
		},{
			field: 'createDate',
			title: '申请时间',
			formatter: dataFormatter
		},{
			field: 'taskCreateTime',
			title: '完成时间'
		}
	],
	onLoadSuccess:function(data){
		$.getScript("../js/contabs.children.min.js");  //加载js文件
	},
	onLoadError:function(status, res){
		errorFunc(res,status);
	}
}
var showMD =function(id){
	$("#tbData").css("display","none");
	$("#fileupload").css("display","block");
	var type = $("#modalType").val();
	//id不同清除input框
	if(type != id){
		var display = $('#affiliatedModal').css('display');
//		if(display == 'none'){
			$('#fileFrom').find(".dz").remove();
			$('#fileFroms').get(0).reset();
			$('#fileFroms').find("input[name='files']").remove();
//		}
	}
	$('#affiliatedModal').modal('show');
	//将申请id存放页面 方便判断取值
	$("#modalType").val(id);
}
//上传页面点击事件
$("#fileFrom").click(function(){
	//上传预览数量
	 var count = $("#fileFrom").find('.dz').length;
	 if(count>15||count===15){
		swal({title : "很抱歉",text : "上传单位文档最大不能超过15个！",type : "info"});
		return;
	 }
	 var uploadFile = '<input name="files" id="uploaderInput" class="hide" type="file" multiple="multiple"/>';
	 $("#fileFroms").append($(uploadFile));
	 //触发input点击事件
	 $("#uploaderInput").click();
})
//上传文件事件
$(document).on("change","#uploaderInput",function(){
	 var count = $("#fileFrom").find('.dz').length;
	 var ts=this;
	 var fp = $("#uploaderInput");
	 var lg = this.files.length
	 var counts = count+lg;
	 if(counts>15){
		 swal({title : "很抱歉",text : "上传单位文档最大不能超过15个！",type : "info"});
		 return;
	 }
	for(var i = 0; i < this.files.length; i++){
		var fileSize = this.files[i].size; 
		if(fileSize>10*1024*1024){
			$("#uploaderInput").remove();
			//消息提示款
			swal({title : "很抱歉",text : "上传单位文档最大不能超过10M！",type : "info"});
			$("#uploaderInput").val("");
			return;
		}
	var fileName=this.files[i].name; 
	var suffixIndex=fileName.lastIndexOf(".");  
	var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
	if(suffix!="TXT"&&suffix!="DOC"&&suffix!="WPS"&&suffix!="RTF"
		&&suffix!="HTML"&&suffix!="PDF"&&suffix!="DOCX"&&suffix!="XLSX"&&suffix!="XLS"){  
		$("#uploaderInput").remove();
		//消息提示款
		swal({title : "很抱歉",text : "请上传文档（格式TXT、DOC、WPS、RTF等）!",type : "info"});
		return;
	}
	//精确文件大小的小数点
	var num = fileSize/1024;
	var nums = parseFloat(num.toFixed(2));
	var html = ''; 
	 	html += '<div class="dz">'
		html +='<div class="dz-details">'
		html +='<div class="dz-filename"><span data-dz-name="">'+fileName+'</span></div> '   
		html +='<div class="dz-size" data-dz-size=""><strong>'+nums+'</strong> KB</div>'  
		html +='<div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress=""></span></div>'
		html +='</div></div>';
 	$("#fileFrom").append($(html));	
	}
//	 		$(this).attr('id','newid');
	$(this).removeAttr("id");
 })
 
// x 点击事件
$('#affiliatedModal').find('.to-colse').click(function(){
	$('#affiliatedModal').modal('hide'); 
	$('#fileFrom').find(".dz").remove();
	$('#fileFroms input').remove();
	$("#seeFile tbody tr").remove();
})

$('#collapse-link').click(function(){
		console.log(0)
		var display = $('#fileFrom').css('display');
		if(display == 'none'){
			$('#fileFrom').css('display','block')
		}else{
			$('#fileFrom').css('display','none')
		}
	})
	
//提交事件
$("#sub").click(function(){
	//创建FormData对象
	var data = new FormData($('#fileFroms')[0]);
	var extendField1=$("#modalType").val();
	 var FileController = '/xxm/rest/fileUpload/uploadFileService/uploadDocument?attachmentItem=contract&attachmentItemID='+extendField1;
		$.ajax({url:FileController,
			data:data,
			type:'post',  
			dataType:'json',
			processData: false,
	        contentType: false,
			success:function(rs) {  
				if(rs.flag){
					//消息提示款
					swal({title : "恭喜您",text : rs.msg,type : "success"},function(){
						$('#affiliatedModal').modal('hide'); 
						$('#affiliatedModal').find(".dz").remove();
						$('.iboxContent input').remove();
					});
				} else {
					//消息提示款
					swal({title : "很抱歉",text : rs.msg, type : "error"});
				}
			},
			error : function() { 
				//消息提示款
				swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
			}  
		});
	 
})

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
	    	error : function() { 
	    		//消息提示款
				swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
	    	}  
	    });
	})
}

var showModal =function(id){
	var num = $("#seeFile tbody tr").length;
	var type = $("#modalType").val();
	$("#modalType").val(id);
	//id不同清除input框
	if(type != id){
		if(num>0){
			$("#seeFile tbody tr").remove();
			refresh();
		}else{
			 refresh();
		}
	}else{
		if(num===0){
			  refresh();
		}
	}
	
	$('#affiliatedModal').modal('show');
	$("#tbData").css("display","block");
	$("#fileupload").css("display","none");
   
}
var refresh = function(){
	var applyInfoId = $("#modalType").val();
	var serverPath = CommonUtils.getServerURL() + "/file";
	 $.ajax({url:'/xxm/rest/attr/attrService/findAttachments',
	    	data:JSON.stringify({attachmentItemID:applyInfoId}),
	    	type:'post', 
	    	async:false,//同步请求
	    	dataType:'json',  
	    	contentType: "application/json",
	    	success:function(data) {  
	    		if(data){
	    			var list = data;
	    			for(var i=0;i<list.length;i++){
	    				var htmls = "";
	    				htmls += '<tr><td><span class="line">'+list[i].attachmentName+'</span></td>'
	    				htmls += '<td><span style="display:none">'+list[i].attachmentSysName+'</span><a class="btn btn-xs red" href="'+serverPath+list[i].attachmentPath+'" download="'+list[i].attachmentName+'">打印</a></td></tr>'
	    				$("#seeFile tbody").append(htmls);
	    			}
	    		}
	    	},
	    	error : errorFunc 
	    });
}
