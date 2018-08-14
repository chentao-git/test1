//js工具类

//基础数据递归
function findChildren(Str,data,level,baseParentId){
	var result=data.childrenList;
	var html="";
	var k=false;
	html=html+'<option value="">请选择</option>';
	for(var i=0;i<result.length;i++){
		if(result[i].level==level){
			if(result[i].baseParentId==baseParentId){
				html=html+'<option value='+result[i].baseCodeValue+'>'+result[i].baseName+'</option>';
				k=true;
			}
		}else{
			findChildren(Str,result[i],level,baseParentId);
		}
	}	
	if(k)$(Str).append(html);
}

var CommonUtils = {
	//获取表单元素值，并封装成集合对象
	getFormJson:function(form) {
		var o = {};
		var a = $(form).serializeArray();
		$.each(a, function () {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value == "" ? null : this.value);
			} else {
				o[this.name] = (this.value == "" ? null : this.value);
			}
		});
		return o;
	},
	//获取$Str的元素，并封装成集合对象,$Str：$.find($(":input[name]"))
	getTagObj:function($Str) {
		var o = {};
		var tagName;
		$Str.each(function(){
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value == "" ? null : this.value);
			} else {
				o[this.name] = (this.value == "" ? null : this.value);
			}
		});
		return o;
	},
	//填充Form元素数据
	fillFormData:function($form,jsonStr){
		var obj = eval("("+jsonStr+")");
		var key,value,tagName,type,arr;
		for(x in obj){
			key = x;
			value = obj[x];
			if (!value) {
				value=""
			} 
			$($form+" [name='"+key+"'],[name='"+key+"[]']").each(function(){
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if(tagName=='INPUT'){
					if(type=='radio'){
						$(this).attr('checked',$(this).val()==value);
					}else if(type=='checkbox'){
						if(value instanceof Array){
							arr = value;
						}else{
							arr = value.split(',');
						}
						for(var i =0;i<arr.length;i++){
							if($(this).val()==arr[i]){
								$(this).attr('checked',true);
								break;
							}
						}
					}else{
						$(this).val(value);
					}
				}else if(tagName=='SELECT' || tagName=='TEXTAREA'){
					$(this).val(value);
				} else{
					$(this).html(value);
				}
			});
		}
	},
	//获取当前请求路径参数
	getUrlParam:function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	},
	//设置临时存取放数据
	setTempData:function(key,data) {
		if (window.sessionStorage) {
			sessionStorage.setItem(key, data);
		}
	},
	//获取临时存储数据
	getTempData:function(key) {
		if (window.sessionStorage) {
			return sessionStorage.getItem(key);
		}
		return null;
	},
	//判断字符是否为空，返回当前值
	isEmptyReturnStr:function(str){
		if(null != str && "" != str){
			return str;
		}
		return "";
	},
	//判断字符为空，返回boolean类型值
	isEmptyReturnBoolean:function(str){
		if(null != str && "" != str){
			return true;
		}
		return false;
	},
	//判断两个数组的数据是否有公共数据
	existsSameValues:function(a1, a2) {
	    if(a1 instanceof Array && a2 instanceof Array){
	        for (var i=0,iLen=a1.length; i<iLen; i++){
	            for (var j=0,jLen=a2.length; j<jLen; j++){
	                if (a1[i]===a2[j]){
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	},
	getServerRoot:function(){ //例如：http://localhost:8080/baqsng
		var curWwwPath = window.document.location.href;  
		//获取主机地址之后的目录
		var pathName = window.document.location.pathname;  
		var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080  
		var localhostPath = curWwwPath.substring(0, pos); //获取带"/"的项目名，如：/web
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);  
		var rootPath = localhostPath + projectName;  
		return rootPath;  
	},
	getServerURL:function(){
		var curWwwPath = window.document.location.href;  
		//获取主机地址之后的目录
		var pathName = window.document.location.pathname;  
		var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080 
		var localhostPath = curWwwPath.substring(0, pos);
		return localhostPath;  
	},
	auditTpyeName:function(Str){
		var val="";
		if(Str=="01"){
			val="预审";
		}else if(Str=="02"){
			val="初审";
		}else if(Str=="03"){
			val="终审";
		}
		return val;
	},
	//查询基础数据（下拉框、通过选择器注入）callback 回调
	findBaseDataOption : function(Str,code,level,baseParentId,callback){
		$(Str).empty();
		var data = {};
		data.baseCode=code;
		data.level=level;
		$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCodeEhcache',
			   data:JSON.stringify(data),
			   type:'post',
			   cache:false,  
			   async: true,
			   dataType:'json',  
			   contentType: "application/json",
			   success:function(data) {  
					if(data!=null){
						var result=data.childrenList;
						var html="";
						var k=false;
						html=html+'<option value="">请选择</option>';
						for(var i=0;i<result.length;i++){
							if(typeof(baseParentId)=='undefined'||result[i].level==level){
								html=html+'<option value='+result[i].baseCodeValue+'>'+result[i].baseName+'</option>';
								k=true;
							}else{
								findChildren(Str,result[i],level,baseParentId);
							}
						}
						if(k)$(Str).append(html);
						
					}
					callback && callback();
			    },
			    error : function() { }  
		});
	},
	//查询基础数据（下拉框、返回字符串）
	findBaseDataOptionStr : function(code,level){
		var html="";
		var data = {};
		data.baseCode=code;
		data.level=level;
		$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCode',
			   data:JSON.stringify(data),
			   type:'post',
			   cache:false,  
			   async: false,
			   dataType:'json',  
			   contentType: "application/json",
			   success:function(result) {  
					if(result!=null&&result.length>0){
						for(var i=0;i<result.length;i++){
							html=html+'<option value='+result[i].baseCodeValue+'>'+result[i].baseName+'</option>'
						}								
					}
			    },
			    error : function() { }  
		});
		return html;
	},
	//查询基础数据（单选框、通过选择器注入）
	findBaseDataRadio : function(Str,code,level,tagName){
		$(Str).empty();
		var data = {};
		data.baseCode=code;
		data.level=level;
		$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCode',
			   data:JSON.stringify(data),
			   type:'post',
			   cache:false,  
			   async: false, //同步 默认为同步 同步得等方法结束才往下走
			   dataType:'json',  
			   contentType: "application/json",
			   success:function(result) {  
					if(result!=null&&result.length>0){
						var html="";
						for(var i=0;i<result.length;i++){
							html=html+'<label class="radio-inline" ><input type="radio" '+(i==0?'checked=""':'')+' name='+tagName+' value='+result[i].baseCodeValue+'>'+result[i].baseName+'</label>';
						}								
						$(Str).append(html);
						//单选复选初始化
						$(Str).iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
					}
			    },
			    error : function() { }  
		});
	},
	//查询基础数据（复选框、通过选择器注入）
	findBaseDataCheckbox : function(Str,code,level,tagName){
		$(Str).empty();
		var data = {};
		data.baseCode=code;
		data.level=level;
		$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCode',
			   data:JSON.stringify(data),
			   type:'post',
			   cache:false,  
			   async: true,
			   dataType:'json',  
			   contentType: "application/json",
			   success:function(result) {  
					if(result!=null&&result.length>0){
						var html="";
						for(var i=0;i<result.length;i++){
							html=html+'<label class="checkbox-inline" ><input type="checkbox" name='+tagName+' value='+result[i].baseCodeValue+'>'+result[i].baseName+'</label>';
						}								
						$(Str).append(html);
						//单选复选初始化
						$(Str).iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
					}
			    },
			    error : function() { }  
		});
	},
	//根据条件查询基础数据
	findBaseDataInfo : function(code,codeVal,level){
		var baseDataVO;
		var data = {};
		data.baseCode=code;
		data.baseCodeValue=codeVal;
		data.level=level;
		$.ajax({url:'/xxm/rest/baseData/baseDataService/findBaseDataInfo',
			   data:JSON.stringify(data),
			   type:'post',
			   cache:false,  
			   async: false,
			   dataType:'json',  
			   contentType: "application/json",
			   success:function(result) {  
					if(result!=null){
						baseDataVO=result;
					}
			    },
			    error : function() { }  
		});
		return baseDataVO;
	}
}

//初始化下拉控件
var initSelect = function(){
	var config = {
		".chosen-select" : {},
		".chosen-select-deselect" : {
			allow_single_deselect : !0
		},
		".chosen-select-no-single" : {
			disable_search_threshold : 10
		},
		".chosen-select-no-results" : {
			no_results_text : "Oops, nothing found!"
		},
		".chosen-select-width" : {
			width : "95%"
		}
	};
	for (var selector in config) {
		$(selector).chosen(config[selector]);
	}
}

//初始化下拉框数据
var initSelectOption = function(obj){
	var formId = (obj.formId) ? obj.formId : "";//当前元素父元素Id
	var docId = obj.docId;//当前元素Id
	var baseCode = obj.baseCode;//获取基础数据code
	var level = obj.level;//基础数据层级
	var order = (obj.order) ? obj.order : "last_update_date";
	var async = (obj.async) ? obj.async : true;
	var isClear = (obj.isClear) ? obj.isClear : false;
	var value = (obj.value) ? obj.value : "";
	var extendField1 = (obj.extendField1) ? obj.extendField1:"";
	var extendField2 = (obj.extendField2) ? obj.extendField2:"";
	var extendField3 = (obj.extendField3) ? obj.extendField3:"";
	var extendField4 = (obj.extendField4) ? obj.extendField4:"";
	if(isClear){
		$(formId + " " + docId).empty();
	}
	var param = {
		baseCode:baseCode,
		level:level,
		order:order,
		extendField1:extendField1,
		extendField2:extendField2,
		extendField3:extendField3,
		extendField4:extendField4
	}
	$.ajax({
		url:'/xxm/rest/baseData/baseDataService/findBaseDataListByCode',
		data:JSON.stringify(param),
		async: async,
		type:'post',  
		dataType:'json',  
		contentType: "application/json",
		success:function(result) {  
			if(null != result && result.length > 0){
				var option = "";
				for(var i = 0; i < result.length; i++){
					if(result[i].baseCodeValue == value){
						option +="<option value='"+result[i].baseCodeValue+"' selected='selected'>"+result[i].baseName+"</option>";
					}else{
						option +="<option value='"+result[i].baseCodeValue+"'>"+result[i].baseName+"</option>";
					}
				}
				$(formId + " " + docId).append(option);
			}
	    }
	});
}

//关闭当前选项卡
var closeMenuTab = function(){
	var data_id = $(".J_mainContent", parent.document).find("iframe.J_iframe").not(":hidden").attr("data-id");
	if(data_id == "home.html"){
		return;
	}
	$(".J_menuTabs", parent.document).find(".J_menuTab").filter("[data-id='"+data_id+"']").children("i").click();
}

/**
 * String 占位字符
 */
String.prototype.format = function() {
	if (arguments.length == 0){
		return this;
	}
	var param = arguments[0];
	var s = this;
	if (typeof (param) == 'object') {
		for (var key in param){
			s = s.replace(new RegExp("\\{" + key + "\\}", "g"), param[key]);
		}
		return s;
	} else {
		for (var i = 0; i < arguments.length; i++){
			s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
		}
		return s;
	}
}

//错误消息函数处理
var errorFunc =  function(req, status, err) {
	window.setTimeout("",1000);
	var auth = req.getResponseHeader("REQUIRES_AUTH");  
    if(auth == 1){
    	var auth_url = req.getResponseHeader("REQUIRES_AUTH_URL"); 
    	//消息提示款
    	swal({title : "很抱歉",text : "登录超时，请重新登录！",type : "error"},function(){
			//跳转页面
			window.parent.location.href= auth_url;
    	});
    }else{
    	//消息提示款
    	swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"},function(){
    		$.mask_close_all();//关闭加载进度条
    	});
    }
}  
