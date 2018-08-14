var applyInfoId = CommonUtils.getUrlParam("applyInfoId");
var isEdit = CommonUtils.getUrlParam("isEdit");
$(document).ready(function(){
	//争议解决方式
	CommonUtils.findBaseDataOption("#arguSolution","ARGU_SOLUTION",1);
	//车辆种类
	CommonUtils.findBaseDataOption("#carKindCode","CAR_KIND_CODE",1);
	//购车用途
	CommonUtils.findBaseDataOption("#leaseholdUse","CAR_USAGE_PRODUCT_CONFIG",1);
	//客户来源
	CommonUtils.findBaseDataOption("#loanWay","LOAN_WAY",1);
	//所购车辆种类
	CommonUtils.findBaseDataOption("#stepHull","STEP_HULL",1);
	//所购/所租车辆类型
	CommonUtils.findBaseDataOption("#carType","CAR_TYPE",1);
	//是否他行
	CommonUtils.findBaseDataOption("#isotherbank","ISOTHERBANK",1);
	//收款方账号类型
	CommonUtils.findBaseDataOption("#payeetype","PAYEETYPE",1);
	//GPS状态
	CommonUtils.findBaseDataOption("#gpsstt","GPSSTT",1);
	//是否为‘白户’
	CommonUtils.findBaseDataOption("#ifwhiteflag","IFWHITEFLAG",1);
	//健康状况
	CommonUtils.findBaseDataOption("#appliHealth","APPLI_HEALTH",1);
	//婚姻状况
	CommonUtils.findBaseDataOption("#marriageStatus","MARITAL_STATUS",1);
	//受教育程度
	CommonUtils.findBaseDataOption("#educationType","EDUCATION_TYPE",1);
	//户籍状况
	CommonUtils.findBaseDataOption("#appliResidence","APPLI_RESIDENCE",1);
	//主要收入来源
	CommonUtils.findBaseDataOption("#incomeSource","INCOME_SOURCE",1);
	//住房产权
	CommonUtils.findBaseDataOption("#houseStat","HOUSE_STAT",1);
	//住房来源
	CommonUtils.findBaseDataOption("#houseSource","HOUSE_SOURCE",1);
	//住房类型
	CommonUtils.findBaseDataOption("#houseType","HOUSE_TYPE",1);
	//居住状况
	CommonUtils.findBaseDataOption("#familySts","FAMILY_STS",1);
	//信用还款记录
	CommonUtils.findBaseDataOption("#repayRec","REPAY_REC",1);
	//公用事业付费记录
	CommonUtils.findBaseDataOption("#pubRec","PUB_REC",1);
	//其他记录
	CommonUtils.findBaseDataOption("#othRec","OTH_REC",1);
	//单位性质
	CommonUtils.findBaseDataOption("#unitType","UNIT_TYPE",1);
	//投保人是否非首次购车
	CommonUtils.findBaseDataOption("#noFirstFlag","NO_FIRST_FLAG",1);
	//抵(质)押物性质
	CommonUtils.findBaseDataOption("#guaranteeType","GUARANTEE_TYPE",1);
	//还款方式
	CommonUtils.findBaseDataOption("#paymentMethodVL","PAYMENT_METHOD",1);
	//入账是否借款人账号
	CommonUtils.findBaseDataOption("#isLoanAccount","IS_LOAN_ACCOUNT",1);
	//车辆用途
	CommonUtils.findBaseDataOption("#usesTp","USES_TP",1);
	//原车辆使用性质
	CommonUtils.findBaseDataOption("#useNature","USE_NATURE",1);
	//是否固定利率
	CommonUtils.findBaseDataOption("#fixRateInd","FIX_RATE_IND",1);
	//流程标志
	CommonUtils.findBaseDataOption("#mktProdType","MKT_PROD_TYPE",1);
	//联系方式
	CommonUtils.findBaseDataOption("#contct","CONTCT",1);
	//联系人类别
	CommonUtils.findBaseDataOption("#linkType","LINK_TYPE",1);
	//证件类型
	CommonUtils.findBaseDataOption("#certType","BAQSNG_PAPER_TYPE",1);
	//发证国家
	CommonUtils.findBaseDataOption("#issCtry","ISS_CTRY",1);
	//是否是联保业务出单
	CommonUtils.findBaseDataOption("#iscoinTypeFlag","ISCOIN_TYPE_FLAG",1);
	//性别
	CommonUtils.findBaseDataRadio("#baseCustomerSex-div","BAQSNG_SEX",1,"baseCustomerSex");
	//时间控件初始化
	var a="#";
	$(".laydate-icon").each(function(){
		laydate({elem : a+$(this).attr("id"),event : "focus"});
	});
	
	//提交
	$("#sumbitData").click(function(){
		supplementFu(0);//0：提交
	});
	//保存
	$("#saveData").click(function(){
		supplementFu(1);//1：保存
	});
	
	//图片上传操作
    initUploadImage();
    //图片文档操作
    initUploadDocument();
	
	//附件阶段
	CommonUtils.findBaseDataOption("#category-big","ATTACHMENT_CLASSIFY",1);
	//附件阶段条件
	CommonUtils.findBaseDataOption("#stage-condition","ATTACHMENT_CLASSIFY",1);
	//单选复选初始化
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	
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
	
	if(applyInfoId!=null){//填充数据
		var data={applyInfoId:applyInfoId};
		$.ajax({
			url:"/xxm/rest/entering/enteringService/findProjectData",
			data:JSON.stringify(data),
			type:'post',  
			dataType:'json',  
			contentType: "application/json",
			success:function(data) {
				if(data){
					if(data.msCoinVO){//投保单归属机构信息
						CommonUtils.fillFormData("#coin-tab",JSON.stringify(data.msCoinVO));
					}
					var msInsuredPlanVO=data.msInsuredPlanVO;
					if(msInsuredPlanVO){//方案信息
						CommonUtils.fillFormData("#insuredPlan-tab",JSON.stringify(data.msInsuredPlanVO));
						//条款信息
						var msSchemeList=msInsuredPlanVO.msSchemeList;
						if(msSchemeList!=null&&msSchemeList.length>0){
							CommonUtils.fillFormData("#scheme-tab",JSON.stringify(msSchemeList[0]));
						}
						//特别约定信息
						var msEngageList=msInsuredPlanVO.msEngageList;
						if(msEngageList!=null&&msEngageList.length>0){
							CommonUtils.fillFormData("#engage-tab",JSON.stringify(msEngageList[0]));
						}
					}
					var msInsuredVO=data.msInsuredVO;
					if(msInsuredVO){//被保人信息
						CommonUtils.fillFormData("#insured-tab",JSON.stringify(msInsuredVO));
						//受益人信息
						if(msInsuredVO.msBeneficiaryVO){
							CommonUtils.fillFormData("#beneficiarys-tab",JSON.stringify(msInsuredVO.msBeneficiaryVO));
						}
					}
					if(data.customerVO){//客户信息
						CommonUtils.fillFormData("#customer-base-tab",JSON.stringify(data.customerVO));
					}
					if(data.supplementBaseVO){//补充基本信息
						CommonUtils.fillFormData("#supplement-base-tab",JSON.stringify(data.supplementBaseVO));
					}
					if(data.leaseInfoVO){//租赁物信息
						CommonUtils.fillFormData("#leaseInfo-tab",JSON.stringify(data.leaseInfoVO));
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
					
					if(isEdit=="0"){//详情
						$("#sumbitData,#saveData").hide();//隐藏提交按钮
						$(":input").attr("disabled","disabled");
						$("#upload-edit").hide();
						$("#tab-8").find(":input").removeAttr("disabled");
						//响应信息
						var returnList=data.policyinfoReturnList;
						var html='';
						if(returnList!=null&&returnList.length>0){
							for(var i=0;i<returnList.length;i++){
								html=html+'<tr>';
								html=html+'<td>'+(returnList[i].serialNo==null?'':returnList[i].serialNo)+'</td>';
								html=html+'<td>'+(returnList[i].proposalNo==null?'':returnList[i].proposalNo)+'</td>';
								html=html+'<td>'+(returnList[i].saveMessage==null?'':returnList[i].saveMessage)+'</td>';
								html=html+'<td>'+(returnList[i].saveTimes==null?'':returnList[i].saveTimes)+'</td>';
								html=html+'<td>'+(returnList[i].createDate==null?'':returnList[i].createDate)+'</td>';
								html=html+'</tr>';
							}
						}else{
							html=html+'<tr>';
							html=html+'<td colspan="5">无历史信息！</td>';
							html=html+'</tr>';
						}
						$("#returnTable").append(html);
					}else{
						$("#return-li").hide();//隐藏响应信息
					}
		   		}else{
		   			//消息提示款
					swal({title : "很抱歉",text : data.msg,type : "error"});
		   		}
			},
			error : errorFunc 
		});
	}
});
//信息提交
function supplementFu(operationType){
	var isPass =false;
	//获取表单元素所有数据集合
	var data = {};
	var supplementBaseVO=CommonUtils.getTagObj($("#supplement-base-tab :input[name]"));//基本信息
	data.supplementBaseVO=supplementBaseVO;
	var msCoinVO=CommonUtils.getTagObj($("#coin-tab :input[name]"));//投保单归属机构信息
	data.msCoinVO=msCoinVO;
	var msInsuredPlanVO=CommonUtils.getTagObj($("#insuredPlan-tab :input[name]"));//方案信息
	var msSchemeVO=CommonUtils.getTagObj($("#scheme-tab :input[name]"));//条款信息
	var msEngageVO=CommonUtils.getTagObj($("#engage-tab :input[name]"));//特别约定信息
	var msSchemeVO=CommonUtils.getTagObj($("#scheme-tab :input[name]"));//条款信息
	var msSchemeList=new Array();
	msSchemeList.push(msSchemeVO);
	var msEngageList=new Array();
	msEngageList.push(msEngageVO);
	msInsuredPlanVO.msSchemeList=msSchemeList;
	msInsuredPlanVO.msEngageList=msEngageList;
	data.msInsuredPlanVO=msInsuredPlanVO;
	
	var leaseInfoVO=CommonUtils.getTagObj($("#leaseInfo-tab :input[name]"));//车辆基础信息
	data.leaseInfoVO=leaseInfoVO;
	var customerVO=CommonUtils.getTagObj($("#customer-base-tab :input[name]"));//资信等级信息
	customerVO.baseCustomerSex=$('#baseCustomerSex-div input[name="baseCustomerSex"]:checked ').val();//性别
	data.customerVO=customerVO;
	var msInsuredVO=CommonUtils.getTagObj($("#insured-tab :input[name]"));//被保人信息
	var msBeneficiaryVO=CommonUtils.getTagObj($("#beneficiarys-tab :input[name]"));//受益人信息
	msInsuredVO.msBeneficiaryVO=msBeneficiaryVO;
	data.msInsuredVO=msInsuredVO;
	
	//附件
	var attachmentTextVO={};
	var attachmentTextList=new Array();
	$("input[name='attId']").each(function(){
		attachmentTextVO={};
		attachmentTextVO.attachmentID=$(this).val();
		attachmentTextList.push(attachmentTextVO);
	});
	data.attachmentTextList=attachmentTextList;//附件集合
	
	data.applyInfoId=applyInfoId;//申请信息id
	data.operationType=operationType;//操作类型   保存还是提交
	$.ajax({
		url:"/xxm/rest/entering/enteringService/dataSupplement",
		data:JSON.stringify(data),
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(data) {
			if(data.result){
				//消息提示款
				swal({title : "恭喜您",text : data.msg,type : "success"},function(){
					isPass=true;
					closeMenuTab();
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
//上传图片
var initUploadImage = function(){
	//图片上传
	$(document).on('change', "input[name='file']", function(){
		var extendField1=$("#category-big").val();
		var extendField2=$("#category-little").val();
		if(extendField1==null||extendField2==null
				||extendField1==""||extendField2==""){
			//消息提示款
			swal({title : "很抱歉",text : "请选择阶段与类别后再进行上传！",type : "info"});
			$("input[name='file']").val("");
			return;
		}
		var ts=this;
		if(this.files.length > 10){
			//消息提示款
			swal({title : "很抱歉",text : "上传图片总数不能超过10个！",type : "info"});
			$("input[name='file']").val("");
			return;
		}
		for(var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size; 
			if(fileSize>20*1024*1024){
				//消息提示款
				swal({title : "很抱歉",text : "上传单张图片最大不能超过20M！",type : "info"});
				$("input[name='file']").val("");
				return;
			}
			var fileName=this.files[i].name; 
			var suffixIndex=fileName.lastIndexOf(".");  
			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
			if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
				//消息提示款
				swal({title : "很抱歉",text : "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!",type : "info"});
				$("input[name='file']").val("");
				return;
			}
		}
		//创建FormData对象
		var data = new FormData();
		//为FormData对象添加数据
		$.each($(this)[0].files, function(i, file) {
			data.append('upload_file', file);
		});
		var FileController = '/xxm/rest/fileUpload/uploadFileService/uploadImg?attachmentItem=applyInfo&extendField1='+extendField1+'&extendField2='+extendField2;
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
						//显示上传成功图片
						if(null != rs.url && "" != rs.url){
							var serverPath = CommonUtils.getServerURL() + "/file";
							var imgURL = rs.url.split(",");
							var attIds = rs.attrId.split(",");
							var attrName = rs.attrName.split(",");
							var html='';
							for(var i = 0; i < imgURL.length; i++){
//								html=html+'<div class="wrap" name="img-div">'; 
//								html=html+'<img modal="zoomImg" src="'+serverPath + imgURL[i]+'" alt="">';
//								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
//								html=html+'<div class="text">图片一</div>';  
//								html=html+'</div>';  
								
								html=html+'<div name="img-div" class="file-box" style="width:120px;">'; 
								html=html+'<div class="file" style="width:100px;">'; 
								html=html+'<div class="image" style="height:70px;">'; 
								html=html+'<img modal="zoomImg" class="img-responsive" src="'+serverPath + imgURL[i]+'" alt="">';
								html=html+'</div>'; 
								html=html+'<div class="file-name">';
								html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attrName[i]+'">'+attrName[i]+'</div>'; 
								html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
								html=html+'</div>'; 
								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
								html=html+'<input type="hidden" name="extendField1" value='+extendField1+'>';
								html=html+'<input type="hidden" name="extendField2" value='+extendField2+'>';
								html=html+'</div></div>'; 
							}
							$("#upload-div").append(html);
							$("input[name='file']").val("");
							$("[name='tooltip-div']").tooltip();//提示初始化
							$.getScript("../js/boxImg.js");  //加载图片轮播js文件
						}
					});
				} else {
					//消息提示款
					swal({title : "很抱歉",text : rs.msg, type : "error"});
				}
			},
			error : errorFunc
		});
	});
}

//文档上传
var initUploadDocument = function(){
	$(document).on('change', "input[name='file2']", function(){
		var extendField1=$("#category-big").val();
		var extendField2=$("#category-little").val();
		if(extendField1==null||extendField2==null
				||extendField1==""||extendField2==""){
			//消息提示款
			swal({title : "很抱歉",text : "请选择阶段与类别后再进行上传！",type : "info"});
			$("input[name='file2']").val("");
			return;
		}
		var ts=this;
		if(this.files.length > 10){
			//消息提示款
			swal({title : "很抱歉",text : "上传文件总数不能超过10个！",type : "info"});
			$("input[name='file2']").val("");
			return;
		}
		for(var i = 0; i < this.files.length; i++){
			var fileSize = this.files[i].size; 
			if(fileSize>10*1024*1024){
				//消息提示款
				swal({title : "很抱歉",text : "上传单位文档最大不能超过10M！",type : "info"});
				$("input[name='file2']").val("");
				return;
			}
			var fileName=this.files[i].name; 
			var suffixIndex=fileName.lastIndexOf(".");  
			var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
			if(suffix!="TXT"&&suffix!="DOC"&&suffix!="WPS"&&suffix!="RTF"
				&&suffix!="HTML"&&suffix!="PDF"&&suffix!="DOCX"&&suffix!="XLSX"&&suffix!="XLS"){  
				//消息提示款
				swal({title : "很抱歉",text : "请上传文档（格式TXT、DOC、WPS、RTF等）!",type : "info"});
				$("input[name='file2']").val("");
				return;
			}
		}
		//创建FormData对象
		var data = new FormData();
		//为FormData对象添加数据
		$.each($(this)[0].files, function(i, file) {
			data.append('upload_file', file);
		});1
		var FileController = '/xxm/rest/fileUpload/uploadFileService/uploadDocument?attachmentItem=applyInfo&extendField1='+extendField1+'&extendField2='+extendField2;
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
						//显示上传成功图片
						if(null != rs.url && "" != rs.url){
							var serverPath = CommonUtils.getServerURL() + "/file";
							var attIds = rs.attrId.split(",");
							var attrName = rs.attrName.split(",");
							var html='';
							for(var i = 0; i < attIds.length; i++){
								html=html+'<div class="file-box" name="document-div" style="width:120px;">'; 
								html=html+'<div class="file" style="width:100px;">'; 
								html=html+'<div class="image" style="height:70px;">'; 
								html=html+'<img class="img-responsive" src="../img/document.png" alt="">';
								html=html+'</div>'; 
								html=html+'<div class="file-name">';
								html=html+'<div style="height:35px;" class="line-limit-length" name="tooltip-div" data-toggle="tooltip" data-placement="top" title="" data-original-title="'+attrName[i]+'">'+attrName[i]+'</div>'; 
								html=html+'<button name="delAttachment" class="btn btn-primary btn-block btn-xs">删除</button>'; 
								html=html+'</div>'; 
								html=html+'<input type="hidden" name="attId" value='+attIds[i]+'>';
								html=html+'<input type="hidden" name="extendField1" value='+extendField1+'>';
								html=html+'<input type="hidden" name="extendField2" value='+extendField2+'>';
								html=html+'</div></div>'; 
							}
							$("#upload-div").append(html);
							$("[name='tooltip-div']").tooltip();//提示初始化
							$("input[name='file2']").val("");
						}
					});
				} else {
					//消息提示款
					swal({title : "很抱歉",text : rs.msg, type : "error"});
				}
			},
			error : errorFunc
		});
	});
}
