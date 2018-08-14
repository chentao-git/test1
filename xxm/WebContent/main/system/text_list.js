$(function () {
	
	 //初始化图片文件选择框
    $('#file-pretty input[type="file"]').prettyFile({text:"请选择..."});
    initUploadImage();//初始化
    
});

//上传图片
var initUploadImage = function(){
	//图片上传
	$(document).on('change', "input[accept='image/*']", function(){
		var ts=this;
		var num = 0;
		if(!isNaN(ts.id.substr(ts.id.length-1, ts.id.length))){
			num = ts.id.substr(ts.id.length-1, ts.id.length);
		}
		if(this.files.length > 1){
			//消息提示款
			swal({title : "很抱歉",text : "上传图片总数不能超过1个！",type : "info"});
			return;
		}
		for(var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size; 
			if(fileSize>20*1024*1024){
				//消息提示款
				swal({title : "很抱歉",text : "上传单张图片最大不能超过20M！",type : "info"});
				return;
			}
		}
		//创建FormData对象
		var data = new FormData();
		//为FormData对象添加数据
		$.each($(this)[0].files, function(i, file) {
			data.append('upload_file', file);
		});
		var certNo = "11011319900713177X";
		var prejudicNo = "RB0163620180Q000E014732992807079";
		var productionVl = $("#productionVl").val();
		
		var stage = $("#stage").val();
		var FileController = '/xxm/rest/piccUpload/PICCUploadFileService/uploadImg?certNo='+certNo+'&prejudicNo='+prejudicNo+'&productionVl='+productionVl+'&stage='+stage;
		$.ajax({url:FileController,
			data:data,
			type:'post',  
			dataType:'json',
			processData: false,
	        contentType: false,
			success:function(rs) {
				if(rs.flag){
					//消息提示款
					swal({title : "恭喜您",text : rs.msg,type : "success"});
				} else {
					//消息提示款
					swal({title : "很抱歉",text : rs.msg, type : "error"});
				}
			},
			error : errorFunc
		});
	});
}


