$(function () {
	
	
	//存量车
	CommonUtils.findBaseDataOption("#stockCarVl","STOCK_CAR",1);
	//还款日期类型
	CommonUtils.findBaseDataOption("#repaymentDateTypeVl","PAYMENT_DATE_TYP",1);
	//担保费计算方式
	CommonUtils.findBaseDataOption("#securityDepositCountVl","PAYMENT_METHOD",1);
	//提成计算基准
	CommonUtils.findBaseDataOption("#deductionCountVl","DATUM_CALCULATION_DATUM",1);
	
	//还款方式
	CommonUtils.findBaseDataRadio("#repaymentMethodVl-div","PAYMENT_METHOD",1,"repaymentMethodVl");
	//利率计算方式
	CommonUtils.findBaseDataRadio("#rateMethodVl-div","INTEREST_CALCULATED",1,"rateMethodVl");
	//担保人信息
	CommonUtils.findBaseDataRadio("#needGuarantorInfoVl-div","GUARANTORS_INFORMATION",1,"needGuarantorInfoVl");
	//提前还贷
	CommonUtils.findBaseDataRadio("#aheadPrepaymentVl-div","ADVANCE_RETURN_LOAN",1,"aheadPrepaymentVl");
	
	
	
	//时间控件初始化
	laydate({elem : "#publishDate",event : "focus"});
	laydate({elem : "#haltSalesDate",event : "focus"});
	
	//获取url中的参数
	function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}
//	var type = getUrlParam('type');
//	if(type === ''){
//		 $('.customerLevel').show();
//	}
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	//验证表单数据
    	if (!$("#form").valid()) {
    		return;
        }
    	//获取表单元素所有数据集合
    	var formData = CommonUtils.getFormJson('#form');
    	var url = "/xxm/rest/productList/productService/addProduce";
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
    			    	    window.location.href = "addProduct.html";
    				});
    	   		}else{
    	   			//消息提示款
    				swal({title : "很抱歉",text : data.msg,type : "error"});
    	   		}
    		},
    		error : errorFunc 
    	});
    });
    
//    $("#btnQuit").click(function(){
//    	    window.location.href = "product_list.html";
//    
//    });
});



