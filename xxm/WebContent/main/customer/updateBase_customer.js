$(function () {
	//获取url中的参数
	function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}
	var type = getUrlParam('type');
	var baseCustomerId = getUrlParam('baseCustomerId');
	
	//查询出要渲染的信息数据
	var dateInfo = findInfo(baseCustomerId);
	//查出客户业务信息id 用作于更新业务信息判断
	var customerId = dateInfo.customerId;
	//时间格式化
	dateInfo.birthDate = formatDate(dateInfo.birthDate);
	
	
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
//	console.log($("#baseCustomerSex-div input").length+"///"+dateInfo.baseCustomerSex);
	$("#baseCustomerSex-div input[value='"+dateInfo.baseCustomerSex+"']").prop("checked","checked");
	//填充表单数据
	CommonUtils.fillFormData("#form",JSON.stringify(dateInfo));
	console.log(dateInfo.baseCustomerSex);
	if(dateInfo.marriageStatusName.indexOf("已婚")>-1){
		$('#spouse-div').show();
	}
	//是否农行
	CommonUtils.findBaseDataRadio("#isABC-div","IS_ABC",1,"isABC");
	console.log($("#isABC-div input").length+"///"+dateInfo.isABC);
	$("#isABC-div input[value='"+dateInfo.isABC+"']").prop("checked","checked");
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
//	$("#baseCustomerSex-div .iCheck-helper").click(function(){
//		alert("111")
//	})
	//婚姻状况
//	CommonUtils.findBaseDataRadio("#marriageStatus-div","MARITAL_STATUS",1,"marriageStatus");
	CommonUtils.findBaseDataOption("#marriageStatus","MARITAL_STATUS",1,"",function(){
		$("#marriageStatus option[value='"+dateInfo.marriageStatus+"']").attr("selected","selected");
	});

	//证件类型
	CommonUtils.findBaseDataOption("#certType","BAQSNG_PAPER_TYPE",1,"",function(){
		$("#certType option[value='"+dateInfo.certType+"']").attr("selected","selected");
	});
	//最高学历
	CommonUtils.findBaseDataOption("#educationType","EDUCATION_TYPE",1,"",function(){
		$("#educationType option[value='"+dateInfo.educationType+"']").attr("selected","selected");
	});
	//扣款银行
	CommonUtils.findBaseDataOption("#openBankName","LOAN_BANK",1,"",function(){
	$("#openBankName option[value='"+dateInfo.openBankName+"']").attr("selected","selected");
	});
	//扣款对象分类
	CommonUtils.findBaseDataOption("#deductionsObjectType","BANK_FLAG",1,"",function(){
		$("#deductionsObjectType option[value='"+dateInfo.deductionsObjectType+"']").attr("selected","selected");
	});
	//配偶证件类型
	CommonUtils.findBaseDataOption("#spouseIdType","BAQSNG_PAPER_TYPE",1,"",function(){
		$("#spouseIdType option[value='"+dateInfo.spouseIdType+"']").attr("selected","selected");
	});
	
	//时间控件初始化
	laydate({elem : "#birthDate",event : "focus"});
	
	//婚姻状况
	$("#marriageStatus").change(function(){
		var str = $(this).find("option:selected").text();
		if(str.indexOf("已婚")>-1){//已婚
			$("#spouse-div").show();
		}else{
			$("#spouse-div").hide();
			//将其下面input select 属性清空
			$('#spouse-div input').val("");
			$('#spouse-div select').prop('selectedIndex', 0);
		}
	});
	
	
	
	//保存按钮点击事件
    $("#btnSave").click(function(){
    	//验证表单数据
    	if (!$("#form").valid()) {
    		return;
        }
    	//获取表单元素所有数据集合
    	var formData = CommonUtils.getFormJson('#form');
    	
    	  formData.baseCustomerId = baseCustomerId;
    	
    	var url = "/xxm/rest/baseCustomer/baseCustomerService/updateBaseCustomerInfo";
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
    					location.reload();
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
//查询用户详细信息
var findInfo=function(baseCustomerId) {
	//加载效果
	mask_fullscreen();
	var dateInfo = null;
	$.ajax({url:'/xxm/rest/baseCustomer/baseCustomerService/findBaseCustomer',
		data:JSON.stringify({baseCustomerId:baseCustomerId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {
			if(null != data){
				dateInfo = data;
			}
		},
		error : errorFunc  
	});
	return dateInfo;
}

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
//加载效果
function mask_fullscreen(){
	$.mask_fullscreen(1000);
}