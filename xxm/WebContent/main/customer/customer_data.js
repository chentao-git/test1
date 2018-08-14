var baseCustomerId = CommonUtils.getUrlParam("baseCustomerId");
$(document).ready(function(){
	
	//附件阶段
	CommonUtils.findBaseDataOption("#category-big","ATTACHMENT_CLASSIFY",1);
	//附件阶段条件
	CommonUtils.findBaseDataOption("#stage-condition","ATTACHMENT_CLASSIFY",1);
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	
	//克隆担保人
	$("[name='addSponsor']").click(function(){
		var count=$("span[name='order-span']").length;
		$("#sponsor-area").append($("#sponsor-area div[name='sponsor-div']:last").clone(true));
		//获取最后一个担保人div
		var $div=$("#sponsor-area div[name='sponsor-div']:last");
		//单选框的name不能相同
		$div.find("span[name='order-span']").html(count+1);//显示第几个担保人
		//单选复选初始化
	});
	
	//查询阶段下的类别
	$("#category-big").change(function(){
		var codeVal=$(this).val();
		var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
		CommonUtils.findBaseDataOption("#category-little","ATTACHMENT_CLASSIFY",2,vo.baseId);
	});
	//查询阶段下的类别“条件”
	$("#stage-condition").change(function(){
		var codeVal=$(this).val();
		if(codeVal!=null&&codeVal!=''){
			var vo=CommonUtils.findBaseDataInfo("ATTACHMENT_CLASSIFY",codeVal,1);
			CommonUtils.findBaseDataOption("#category-condition","ATTACHMENT_CLASSIFY",2,vo.baseId);
		}else{
			$("#category-condition").empty();
		}
	});
	
	//附件条件查询按钮
	$("#conditionBu").click(function(){
		var stageCondition=$("#stage-condition").val();//阶段
		if(stageCondition!=null&&stageCondition!=''){
			$("input[name='extendField1']").each(function(){
//				alert($(this).parents(".file-box").length);
				if($(this).val()!=stageCondition){
//					alert(22);
					$(this).parent().hide();
				}else{
//					alert(11);
					$(this).parent().show();
				}
			});
		}else{
			$(".file").show();
		}
		var categoryCondition=$("#category-condition").val();//类别
		var prev;
		if(categoryCondition!=null&&categoryCondition!=''){
			$("input[name='extendField2']").each(function(){
				prev=$(this).prev();
				if($(this).val()!=categoryCondition){
					$(this).parent().hide();
				}else if(prev==stageCondition){
					$(this).parent().show();
				}
			});
		}
		var attachmentType=$("#attachmentType-div input:checked").val();//附件类型
		if(attachmentType==2){//图片
			$("div[name='img-div']").show();
			$("div[name='document-div']").hide();
		}else if(attachmentType==3){//文档
			$("div[name='document-div']").show();
			$("div[name='img-div']").hide();
		}else{
			$("div[name='document-div']").show();
			$("div[name='img-div']").show();
		}
	});
	//附件条件重置按钮
	$("#resetBu").click(function(){
		$("#stage-condition option:first").prop("selected", 'selected');//阶段
		$("#category-condition option:first").prop("selected", 'selected');//类别
		$("#attachmentType-div input[value='']").prop("checked","checked");//附件类型
		//单选复选初始化
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	});
	
	if(baseCustomerId!=null){//填充数据
		var customerVO = {baseCustomerId:baseCustomerId};
		var data={customerVO:customerVO};
		$.ajax({
			url:"/xxm/rest/apply/applyService/findCustomerDate",
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
//					CommonUtils.fillFormData("#applyInfo-tab",JSON.stringify(data));
//					$("#productionVl").html(data.productionVl);
//					$("#loanContractNo").html(data.loanContractNo);
					if(data.customerVO){//客户信息
						CommonUtils.fillFormData("#customer-base-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-spouse-tab",JSON.stringify(data.customerVO));
						CommonUtils.fillFormData("#customer-account-tab",JSON.stringify(data.customerVO));
						var str =data.customerVO.marriageStatusName;
						if(null != str && str.indexOf("已婚") != -1){//婚姻状况
							$("#spouse-div").show();
						}else{
							$("#spouse-div").hide();
						}
						
					}
					//附件
					var attachmentTextList=data.attachmentTextList;
					if(null != attachmentTextList && attachmentTextList.length>0){
						var serverPath = CommonUtils.getServerURL() + "/file";
						var html='';
						for(var i = 0; i < attachmentTextList.length; i++){
							if(attachmentTextList[i].attachmentType=="3"){//文档
								html=html+'<div class="file-box" name="document-div" style="width:120px;">'; 
							}else{
								html=html+'<div class="file-box" name="img-div" style="width:120px;">'; 
							}
							html=html+'<div class="file" style="width:100px;">'; 
							html=html+'<div class="image" style="height:70px;">'; 
							if(attachmentTextList[i].attachmentType=="3"){//文档
								html=html+'<img class="img-responsive" src="../img/document.png" alt="">';
							}else{
								html=html+'<img modal="zoomImg" class="img-responsive" src="'+serverPath + attachmentTextList[i].attachmentPath+'" alt="">';
							}
							html=html+'</div>'; 
							html=html+'<div class="file-name">';
							html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attachmentTextList[i].attachmentName+'">'+attachmentTextList[i].attachmentName+'</div>'; 
//							html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
							html=html+'</div>'; 
							html=html+'<input type="hidden" name="attId" value='+attachmentTextList[i].attachmentID+'>';
							html=html+'<input type="hidden" name="extendField1" value='+attachmentTextList[i].extendField1+'>';
							html=html+'<input type="hidden" name="extendField2" value='+attachmentTextList[i].extendField2+'>';
							html=html+'</div></div>'; 
						}
						$("#upload-div").append(html);
						$("[name='tooltip-div']").tooltip();//提示初始化
						$.getScript("../js/boxImg.js");  //加载图片轮播js文件
					}
					//担保人
					var sponsorList=data.sponsorList;
					if(sponsorList!=null&&sponsorList.length>0){
						for(var i=0;i<sponsorList.length;i++){
							if(i==0){
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
							}else{
								$("[name='addSponsor']").click();
								CommonUtils.fillFormData("#sponsor-area div[name='sponsor-div']:last",JSON.stringify(sponsorList[i]));
							}
						}
					}
					var leaseInfoVO = data.leaseInfoVO;
					if(leaseInfoVO){//租赁物信息
						CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
					}
					//贷款
					$('#loan').bootstrapTable(ainit);
					//跟进
					$('#baseCustomer').bootstrapTable(init);
					
					//服务
				    $('#service').bootstrapTable(sinit);
				    
				    //商机
				    $('#businessOpportunity').bootstrapTable(pinit);
				     
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : "未查询到数据！！",type : "error"});
		   		}
			},
    		error : errorFunc
		});
	}
//	 $("#tab-2").trigger("click");
});
//查询的参数
var queryParams = function (params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        baseCustomerId:baseCustomerId,
    };
    return temp;
};
//查询的参数
var queryParam = function (params) {
	var customerVO = {baseCustomerId:baseCustomerId}
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        customerVO:customerVO,
    };
    return temp;
};


//日期格式化
var formatDate = function (date) { 
	var date = new Date(Date.parse(date)) //拿到的日期为字符串 将其转换为Date
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
}; 


//跟进  bootstrapTable初始化参数
var init = {
    url: '/xxm/rest/customerFile/customerFileService/findCustomerScheduleList', //请求后台的URL（*）
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
    uniqueId: "scheduleId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			field: 'baseCustomerName',
			title: '客户名称',
			sortable : true
		},{
			field: 'lastUpdateByName',
			title: '员工',
			sortable : true
		},{
			field: 'followUpCategoryName',
			title: '跟进类别',
			sortable : true
		},{
			field: 'contentRegistration',
			title: '内容登记',
			sortable: true
		},{
			field: 'followUpTime',
			title: '跟进时间',
			sortable : true,
			formatter: formatDate
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
//服务  bootstrapTable初始化参数
var sinit = {
    url: '/xxm/rest/customerFile/customerFileService/findServiceRecordList', //请求后台的URL（*）
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
    uniqueId: "serviceRecordId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			field: 'baseCustomerName',
			title: '客户名称',
			sortable : true
		},{
			field: 'lastUpdateByName',
			title: '员工',
			sortable : true
		},{
			field: 'serviceTime',
			title: '服务时间',
			sortable : true,
			formatter: formatDate
		},{
			field: 'processingProgressName',
			title: '处理进度',
			sortable: true
		},{
			field: 'contentRegistration',
			title: '内容登记',
			sortable : true
		},{
			field: 'processingResults',
			title: '处理结果',
			sortable : true
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

//商机  bootstrapTable初始化参数
var pinit = {
    url: '/xxm/rest/customerFile/customerFileService/findBusinessOpportunityList', //请求后台的URL（*）
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
    uniqueId: "businessOpportunityId",                 //每一行的唯一标识，一般为主键列
    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
	columns: [{
			field: 'baseCustomerName',
			title: '客户名称',
			sortable : true
		},{
			field: 'lastUpdateByName',
			title: '员工',
			sortable : true
		},{
			field: 'focusOnProductName',
			title: '关注产品',
			sortable : true
		},{
			field: 'loanAmount',
			title: '预计贷款额',
			sortable: true
		},{
			field: 'interestRate',
			title: '预计利率',
			sortable : true
		},{
			field: 'expectedTransactionTime',
			title: '预计成交时间',
			sortable : true,
			formatter: formatDate
		},{
			field: 'actualTransactionTime',
			title: '实际成交时间',
			sortable : true,
			formatter: formatDate
		},{
			field: 'remark',
			title: '状态',
			sortable : true
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

//贷款信息  bootstrapTable初始化参数
var ainit = {
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
    queryParams: queryParam,           //传递参数（*）
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
			field: 'loanContractNo',
			title: '贷款编号',
			sortable : true
		},{
			field: 'productionName',
			title: '产品名字',
			sortable : true
		},{
			field: '',
			title: '贷款金额',
			sortable : true
		},{
			field: 'salesman',
			title: '业务员',
			sortable: true
		},{
			field: '',
			title: '当前阶段',
			sortable : true
		},{
			field: 'createDate',
			title: '申请时间',
			sortable : true,
			formatter: formatDate
		},{
			field: '',
			title: '结果',
			sortable : true
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
//加载效果
function mask_fullscreen(){

	$.mask_fullscreen(1000);

}
