//表单向导
$("#form").steps({
	bodyTag:"fieldset",
//         		titleTemplate :"<span class='number'>#index#.</span>",  
// 				labels:{cancel:"取消",current:"当前步骤：",pagination:"分页",finish:"完成",next:"下一步",previous:"上一22步",loading:"加载中 ..."},
	onStepChanging:function(event,currentIndex,newIndex){//下一步
		if(currentIndex>newIndex){
			return true
		}
		if(newIndex===3&&Number($("#age").val())<18){
			return false
		}
		var form=$(this);
		if(currentIndex<newIndex){
			$(".body:eq("+newIndex+") label.error",form).remove();
			$(".body:eq("+newIndex+") .error",form).removeClass("error")
		}
		form.validate().settings.ignore=":disabled,:hidden";
		return form.valid();
	},
	onStepChanged:function(event,currentIndex,priorIndex){//下一步
		if(currentIndex===2&&Number($("#age").val())>=18){
			$(this).steps("next")
		}
		if(currentIndex===2&&priorIndex===3){
			$(this).steps("previous")
		}
	},
	onFinishing:function(event,currentIndex){//完成
		var form=$(this);
		form.validate().settings.ignore=":disabled";return form.valid()
	},
	onFinished:function(event,currentIndex){//完成
		//判断是否添加成功
		var isPass=addApply();//提交
		if(isPass){
			var form=$(this);
			form.submit()
		}
	}
}).validate({
	errorPlacement:function(error,element){
		element.before(error)
	},
	rules:{
		confirm:{equalTo:"#password"}
	}
});

$(document).ready(function(){
	//保存按钮
//	var html='<li aria-hidden="false" aria-disabled="false"><a role="menuitem">保存</a></li>';
//	$(".actions,.clearfix").find("ul li").last().prev().after(html);
	//当表单向导内容过长时将出现滚动条
	$(".content").css("overflow-y","scroll");
	$(".steps").css("padding-left","3px");
	//计算内容高度
	var a1 = document.body.offsetHeight; 
	var b1 = $(".steps").height();
	var c1 = $(".actions").height();
	$(".content").css("height",(a1-b1-2*c1+10)+"px");
	//产品类型
	CommonUtils.findBaseDataOption("#produceType","PRODUCE_TYPE",1);
	//证件类型
	CommonUtils.findBaseDataOption("#baqsngPaperType","BAQSNG_PAPER_TYPE",1);
	//配偶证件类型
	CommonUtils.findBaseDataOption("#spouseIdType","BAQSNG_PAPER_TYPE",1);
	//存量车
	CommonUtils.findBaseDataOption("#stockCarVl","STOCK_CAR",1);
	//资方管理
	CommonUtils.findBaseDataOption("#managementOfCapitalVl","THE_INVESTOR",1);
	//保险公司
	CommonUtils.findBaseDataOption("select[name='insuranceCompanyVl']","INSURANCE_COMPANY",1);
	//贷款银行
	CommonUtils.findBaseDataOption("#loanBankVl","LOAN_BANK",1);
	//贷款业务
	CommonUtils.findBaseDataOption("#loanBusinessVl","LOAN_BUSINESS",1);
	//车辆类型
	CommonUtils.findBaseDataOption("#vehicleTypeVl","VEHICLE_TYPE",1);
	//汽车经销商
	CommonUtils.findBaseDataOption("#autoDealerVl","AUTO_DEALER",1);
	//婚姻状况
	CommonUtils.findBaseDataOption("#marriageStatus","MARITAL_STATUS",1);
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
	//合作商产品类型
	CommonUtils.findBaseDataOption("#cooprProdType","COOPRPROD_TYPE",1);
	//银行代码
	CommonUtils.findBaseDataOption("#bankCode","BANKCODE",1);
	//归属机构代码
	CommonUtils.findBaseDataOption("#maincomCode","MAINCOM_CODE",1);
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	//时间控件初始化
	laydate({elem : "#birthDate",event : "focus"});
	
	//婚姻状况
	$("#marriageStatus").change(function(){
		if($(this).val()=="B"||$(this).val()=="A"){//已婚
			$("#spouse-div").show().prev().show();
		}else{
			$("#spouse-div").hide().prev().hide();
		}
	});
	
	findUserInfo();//业务员
});

//新增申请信息
function addApply(){
	var isPass =false;
	//获取表单元素所有数据集合
	var data = CommonUtils.getTagObj($("#applyInfo-table :input[name]"));
	var customerVO = CommonUtils.getTagObj($("#baseCustomerInfo-table :input[name]"));
	customerVO.baseCustomerSex=$('#baseCustomerSex-div input[name="baseCustomerSex"]:checked').val();//性别
	var spouse = CommonUtils.getTagObj($("#spouse-tab :input[name]"));
	data.customerVO =$.extend({}, customerVO, spouse);//合并对象
	$.ajax({
		url:"/xxm/rest/apply/applyService/addApply",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				isPass=true;
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
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
//业务员
var findUserInfo = function(){
//	$.ajax({
//		url:'/xxm/rest/user/userService/findUsers',
//		date:{},
//		type : 'post',
//		dataType : 'json',
//		async : false,// 同步请求
//		contentType : "application/json",
//		success:function(data){
//			if (null != data) {
//				var html='';
//				for(var i=0;i<data.length;i++){             
//		            html=html+"<option value="+data[i].userId+">"+data[i].name+"</option>";
//		        }
//				$("#salesman").append(html);
//			}
//			
//		},
//		error : errorFunc
//	});
	$.ajax({
		url:'/xxm/rest/user/userService/findLoginUser',
		date:{},
		type : 'post',
		dataType : 'json',
		async : false,// 同步请求
		contentType : "application/json",
		success:function(data){
			if (null != data) {
				$("#salesman").val(data.name);
				$("#salesmanId").val(data.userId);
			}
			
		},
		error : errorFunc
	});
}
