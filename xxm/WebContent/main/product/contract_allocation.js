$(function () {
	//获取url中的参数
	function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}
	var productId = getUrlParam('productId');
	
	var info = findRepaymentPlan(productId);
	//日期格式化
	info.publishDate = formatDate(info.publishDate);
	//填充表单数据
	CommonUtils.fillFormData("#proForm",JSON.stringify(info));
    //保存按钮点击事件
    $("#btnSave").click(function(){
    	//验证表单数据
    	if (!$("#fileForm").valid()) {
    		return;
        }
    	//获取表单元素所有数据集合
    	var formData = CommonUtils.getFormJson('#fileForm');
    	var url = "/xxm/rest/productList/productService/";
    	//请求后台
//    	$.ajax({
//    		url:url,
//    		data:JSON.stringify(formData),
//    		type:'post',  
//    		dataType:'json',  
//    		contentType: "application/json",
//    		success:function(data) {
//    			if(data.result){
//    				//消息提示款
//    				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
//    			    	    window.location.href = "product_list.html";
//    				});
//    	   		}else{
//    	   			//消息提示款
//    				swal({title : "很抱歉",text : data.msg,type : "error"});
//    	   		}
//    		},
//    		error : function() {  
//    			//消息提示款
//    			swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
//    		}  
//    	});
    });
    UploadFile();
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
//根据id查询详情信息
var findRepaymentPlan = function(productId){
	var info = null;
	$.ajax({url:'/xxm/rest/productList/productService/findProduce',
		data:JSON.stringify({productId:productId}),
		type:'post',  
		dataType:'json',
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {  
			if(null != data){
				info = data;
			}
		},
		error : errorFunc
	});
	return info;
}


var html = '';
html += ' <tr><td><input type="text" name="Name" class="form-control"></td>'
html += '<td><div class="btn-group">'
html += '<label title="上传文件" class="btn btn-primary"><input type="file" name="file2" class="hide" multiple="multiple" accept=".xml"> 上传文件</label>'
html += '<label title="预览文件" class="btn btn-primary">'
html += '<input type="button" name="file" class="hide"> 预览文件</label></div></td> <td>'
html += '<select name="collectionMethodVL" id="collectionMethodVL" class="form-control"><option value="">请选择</option><option value="1">1</option></select>'
html += '</td><td><i onclick="add(this)" id="add" class="fa fa-plus-square"></i><i onclick="del(this)" style="margin-left:3px;" id="del" class="fa fa-minus-square"></i></td></tr>'
	 
function add(k){
	 $(k).parent().parent().parent().append(html);
}
function del(k){
	$(k).parent().parent().remove();
}

//上传
var UploadFile = function(){
	//图片上传
	$(document).on('change', "input[name='file']", function(){
		var ts=this;
		if(this.files.length > 5){
			//消息提示款
			swal({title : "很抱歉",text : "每次上传总数不能超过5个！",type : "info"});
			$("input[name='file']").val("");
			return;
		}
		for(var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size; 
			if(fileSize>10*1024*1024){
				//消息提示款
				swal({title : "很抱歉",text : "上传文件最大不能超过10M！",type : "info"});
				$("input[name='file']").val("");
				return;
			}
			var fileName=this.files[i].name; 
			var suffixIndex=fileName.lastIndexOf(".");  
			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
			if(suffix!="XML"){  
				//消息提示款
				swal({title : "很抱歉",text : "请上传文件（格式xml）!",type : "info"});
				$("input[name='file']").val("");
				return;
			}
			//TODO 要上传数据填充至预览中 
		}
		//创建FormData对象
//		var data = new FormData();
//		//为FormData对象添加数据
//		$.each($(this)[0].files, function(i, file) {
//			data.append('upload_file', file);
//		});
		
	});
}


