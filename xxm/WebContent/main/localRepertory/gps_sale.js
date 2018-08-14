$(function () {
	//获取sp商列表 组装成下拉框
	var html = findModelList();
	$("#spType").append(html);
	
	//时间控件初始化
	laydate({elem : "#outWarehouseTime",event : "focus"});
//	initFormValid();
	//类型
	CommonUtils.findBaseDataOption("#outWarehouseType","OUT_WAREHOUSE",1);
	//日期格式化
//	info.publishDate = formatDate(info.publishDate);
    
});
//日期格式化
var formatDate = function (date) { 
	if(date.toString()!=null && date.toString()!=''){
		var date = new Date(Date.parse(date)) //拿到的日期为字符串 将其转换为Date
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? '0' + m : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    return y + '-' + m + '-' + d; 
	}else{
		return '';
	}
}; 
//获取sp商列表 组装成下拉框
var findModelList = function(){
	var html = '';
	$.ajax({url:'/xxm/rest/gpsInfo/gpsInfoService/findProviderList',
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				html=html+'<option value="">请选择</option>';
				var info = data.rows;
				for(var i=0;i<info.length;i++){
					html=html+'<option value='+info[i].spId+'>'+info[i].spName+'</option>';
				}
			}
		},
		error : errorFunc
	});
	
	return html;
}
 //添加事件
$("#btnAdd").click(function(){
	//产品序列号
	var SN = $("#snVal").val();
	if(SN === ""){
		swal({title : "抱歉",text : "请输入产品序列号！",type : "error"});
	}else{
		//判断产品序列号是否存在
		var priductSNInfo = findPriductSNInfo(SN);
		if(priductSNInfo === null){
			swal({title : "很抱歉",text : "产品序列号不存在！",type : "error"});
		}else{
			 var addLine = '';
			 addLine +=	 '<tr>'
		 	 addLine +=	 '<td><input style="background-color:white;" readonly="readonly" id="SN" name="priductSN" class="form-control"></td>'
	 		 addLine +=	 '<td><input style="background-color:white;" readonly="readonly" id="model" name="model" class="form-control"></td>'
 			 addLine +=	 ' <td><input style="background-color:white;" readonly="readonly" id="brand" name="brand" class="form-control"></td>'
			 addLine +=	 ' <td><input type="text" id="cost" name="costing" class="form-control"></td>'
			 addLine +=	 '<td><i onclick="del(this)" id="del" class="fa fa-minus-square"></i></td>'
			 addLine +=	 '</tr>'
			$("#addLine").append(addLine);
			var length = $("#addLine").find("tr").length;
			var judge = false;
			//判断table中是否已经输入过该sn
			for(var i=0;i<length;i++){
				var SNLine = $("#addLine tr").eq(i).find("#SN").val();
				if(SN === SNLine){
					 judge = true;
					 break;
				}else{
					judge = false;
				}
			}
			if(judge === true){
				$("#addLine tr:last").remove();
				swal({title : "抱歉",text : "该产品序列号已输入过了！",type : "error"});
			}else{
				//获取型号id 并查询详情信息
				var dataInfo = findDataInfo(priductSNInfo.gpsId);
				if(dataInfo === null){
					$("#addLine tr:last").remove();
					swal({title : "很抱歉",text : "本地库存不存在！",type : "error"});
				}else{
					//填充表单
					CommonUtils.fillFormData("#addLine tr:last",JSON.stringify(dataInfo));
					//将添加条件重置
					$("#snVal").val("");
				}
				
			}
		}
	}
});
//出仓事件
$("#btnSave").click(function(){
	var length = $("#addLine").find("tr").length;
	if(length === 0){
		swal({title : "抱歉",text : "请输入gps信息！",type : "error"});
	}
	var lineList = new Array();
	
	//获取入仓表单元素所有数据集合
	var entryInfo =  CommonUtils.getFormJson('#form');
	lineList[0] = entryInfo;
	var judge = false;
	for(var i=0;i<length;i++){
		var formData = {};
		var sn = $("#addLine tr").eq(i).find("#SN").val();
		var cost = $("#addLine tr").eq(i).find("#cost").val();
		if(cost === ""){
			judge = true;
			break;
		}
		formData.priductSN = sn;
		formData.costing = cost;
		
		lineList[i+1] = formData
	}
	if(judge === true){
		swal({title : "抱歉",text : "请输入成本！",type : "error"});
	}else if(entryInfo.spType === null) {
		swal({title : "抱歉",text : "请选择sp商！",type : "error"});
	}else if(entryInfo.outWarehouseType === null){
		swal({title : "抱歉",text : "请选择出仓类型！",type : "error"});
	}else{
		var url = "/xxm/rest/gpsInfo/gpsInfoService/addOutWarehouseInfo";
		//请求后台
		$.ajax({
			url:url,
			data:JSON.stringify(lineList),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data.result){
					//消息提示款
					swal({title : "恭喜您",text : data.msg,type : "success"},function(){
						window.location.reload();
					});
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
			},
    		error : errorFunc 
		});
	}

});


//sn查询详细信息
var findPriductSNInfo=function(SN) {
	var dataInfo = null;
	var gpsInfoVO = {};
	gpsInfoVO.priductSN = SN;
	$.ajax({url:'/xxm/rest/gpsInfo/gpsInfoService/findGpsInfo',
		data:JSON.stringify(gpsInfoVO),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				dataInfo = data;
			}
		},
		error : errorFunc
	});
	return dataInfo;
}
//id查询详细信息
var findDataInfo=function(gpsId) {
	var dataInfo = null;
	var gpsInfo = {};
	gpsInfo.gpsPlaceVL = "1";
	gpsInfo.gpsId = gpsId;
	$.ajax({url:'/xxm/rest/gpsInfo/gpsInfoService/findGpsInfo',
		data:JSON.stringify(gpsInfo),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				dataInfo = data;
			}
		},
		error : errorFunc
	});
	return dataInfo;
}
 //移除tr
function del(k){
	$(k).parent().parent().remove();
}



