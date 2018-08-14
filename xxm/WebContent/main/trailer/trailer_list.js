var imgData = new Array();// 保存图片信息
var imgCount = 0;// 图片个数
$(function() {
	// 时间控件初始化
	laydate({
		elem : "#auditDate",
		event : "focus",
		format : 'YYYY-MM-DD hh:mm:ss',
		istime : true
	});
	laydate({
		elem : "#uploadDate",
		event : "focus",
		format : 'YYYY-MM-DD hh:mm:ss',
		istime : true
	});
	laydate({
		elem : "#storageDate",
		event : "focus",
		format : 'YYYY-MM-DD hh:mm:ss',
		istime : true
	});
	laydate({
		elem : "#hello",
		event : "focus",
		format : 'YYYY-MM-DD hh:mm:ss',
		istime : true
	});
	// 是否
	CommonUtils.findBaseDataRadio("#isABC-div", "IS_ABC", 1, "isReport");
	CommonUtils.findBaseDataRadio("#isABCD-div", "IS_ABC", 1, "isRelationed");
	// 下拉框
	// 初始化下拉类型
	CommonUtils.findBaseDataOption("#trailerStatus", "TRAILER_STATUS", 1);
	// 初始化时间控件
	$("#toolbar .input-daterange.input-group").datepicker({
		todayBtn : "linked",
		keyboardNavigation : !1,
		forceParse : !1,
		calendarWeeks : !0,
		autoclose : !0
	})
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green"
	});
	// 初始化table并刷新数据
	$('#tbTrailer').bootstrapTable(init);
	// 表单验证初始化
	// initFormValid();
	// 表单验证默认提示
	$.extend($.validator.messages, {
		required : "这是必填字段！"
	})

	$("#btnQuery").click(function() {
		mask_fullscreen();
		// 刷新列表
		$("#tbTrailer").bootstrapTable('refresh', init);
	});

	// 添加按钮点击事件
	$("#btnAdd").click(function() {
		//重置数据
		$("#trailerForm").find("label[name]").html('');
		showModal("1");// 新增
	});
	// 图片上传
	initUploadImage();
	// 搜索申请信息
	$("#selectApply").click(
		function() {
			var loanContractNo = $("#loanContractNo-inp").val();
			var applyVO = {
				'loanContractNo' : loanContractNo
			}
			if (trailerId != null) {
				var data = {
					applyVO : applyVO
				};
				$.ajax({
					url : "/xxm/rest/trailer/trailerService/loanContractNoQuery",
					data : JSON.stringify(data),
					type : 'post',
					dataType : 'json',
					contentType : "application/json",
					success : function(data) {
						if (data) {
							$("#applyInfoId").val(
									data.applyInfoId);
							if (data.customerVO) {// 客户信息
								CommonUtils.fillFormData("#trailerForm",JSON.stringify(data.customerVO));
								CommonUtils.fillFormData("#trailerForm",JSON.stringify(data.leaseInfoVO));
							}
						} else {
							//重置数据
							$("#trailerForm").find("label[name]").html('');
							// 消息提示款
							swal({
								title : "很抱歉",
								text : "没有查询到数据！",
								type : "error"
							});
						}
					},
					error : errorFunc
				});
			}
		});

	// 保存按钮点击事件
	$("#btnSave").click(function() {
		addTrailer();// 保存数据
	});

});

// 查询的参数
var queryParams = function(params) {
	var customerVO = {
		baseCustomerName : $("#baseCustomerName").val()
	};
	var applyVO = {
		loanContractNo : $("#loanContractNo").val()
	};
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset,// 页码
		trailerStatus : $("#trailerStatus").val(),
		startDate : $("#startDate").val(),
		endDate : $("#endDate").val(),
		customerVO : customerVO,
		applyVO : applyVO
	};
	return temp;
};

// 操作
var actionFormatter = function(value, row, index) {
	var result = "";
	var customerVO = row.customerVO;
	result += '<a class="btn btn-xs red J_menuItem" href="../main/trailer/trailer_Details.html?applyInfoId='
			+ value + '">详情</a>';
	if (row.trailerStatus == 1) {
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(2,'
				+ value + ')">拖车审核</a>';
	} else if (row.trailerStatus == 3) {
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(7,'
				+ value + ')">最终处理</a>';
	} else if (row.trailerStatus == 2) {
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(6,'
				+ value + ')">下载材料</a>';
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(4,'
				+ value + ')">人保委托</a>';
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(5,'
				+ value + ')">转出委托</a>';
		result += '<a class="btn btn-xs red J_menuItem" onclick="showModal(3,'
				+ value + ')">车辆入库</a>';
	}

	return result;
}

// 时间格式化
var dataFormatter = function(value) {
	if (null != value && "" != value) {
		return value.replace(".0", "");
	}
	return "";
}

// 申请状态格式化
var statusFormatter = function(value) {
	if (value == 1) {
		return "申请中";
	}
	if (value == 2) {
		return "已审核通过";
	}
	if (value == 3) {
		return "已入库";
	}
	return "已完成";

}

// bootstrapTable初始化参数
var init = {
	url : '/xxm/rest/trailer/trailerService/findTrailerList', // 请求后台的URL（*）
	method : 'post', // 请求方式（*）
	ajaxOptions : {
		async : false
	// 设置为异步
	},
	toolbar : '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	queryParams : queryParams, // 传递参数（*）
	sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
	search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	strictSearch : true,
	showColumns : true, // 是否显示所有的列
	showRefresh : true, // 是否显示刷新按钮
	minimumCountColumns : 2, // 最少允许的列数
	clickToSelect : false, // 是否启用点击选中行
	// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	uniqueId : "trailerId", // 每一行的唯一标识，一般为主键列
	showToggle : true, // 是否显示详细视图和列表视图的切换按钮
	cardView : false, // 是否显示详细视图
	detailView : false, // 是否显示父子表
	columns : [ {
		field : 'trailerId',
		title : '操作',
		width : 200,
		align : 'center',
		valign : 'middle',
		formatter : actionFormatter
	}, {
		field : 'applyVO.loanContractNo',
		title : '贷款编号',
		sortable : true
	}, {
		field : 'customerVO.baseCustomerName',
		title : '客户姓名',
		sortable : true
	}, {
		field : 'leaseInfoVO.plateNumber',
		title : '车牌号',
		sortable : true
	}, {
		field : 'customerVO.certNo',
		title : '身份证号码',
		sortable : true
	}, {
		field : 'applyInfoIds',
		title : '申请序号',
		sortable : true
	}, {
		field : 'trailerStatus',
		title : '拖车申请进度',
		width : 140,
		formatter : statusFormatter
	}, {
		field : 'applyDate',
		title : '申请金额(元)',
		formatter : dataFormatter
	}, {
		field : 'applyDate',
		title : '申请时间',
		formatter : dataFormatter
	} ],
	onLoadSuccess : function(data) {
		mask_fullscreen();
		$.getScript("../js/contabs.children.min.js"); // 加载js文件
	},
	onLoadError : function(status, res) {
		errorFunc(res, status);
	}
}

function addTrailer() {
	var type = $('#TrailerModal').find("#modalType").val();
	// 验证表单数据
	if ((type == 1 && !$("#trailerForm").valid())) {
		return;
	} else if ((type == 2 && !$("#trailerAppoveForm").valid())) {
		return;
	} else if ((type == 3 && !$("#editTrailerForm").valid())) {
		return;
	} else if ((type == 4 && !$("#entrustTrailerForm").valid())) {
		return;
	} else if ((type == 7 && !$("#finalAuditAppoveForm").valid())) {
		return;
	}
	var trailerId = $("#trailerId").val();
	var applyInfoId = $("#applyInfoId").val();
	var data;
	var url = "";
	if (type == 1 && applyInfoId != null) {// 申请拖车
		data = CommonUtils.getFormJson($("#trailerForm"));
		data.applyInfoId = applyInfoId;
		url = "/xxm/rest/trailer/trailerService/addTrailer";
	} else if (type == 2 && trailerId != null) {// 审核
		data = CommonUtils.getFormJson($("#trailerAppoveForm"));
		data.trailerId = trailerId;
		url = "/xxm/rest/trailer/trailerService/trailerAppove";
	} else if (type == 3 && trailerId != null) {// 拖车入库
		data = CommonUtils.getFormJson($("#editTrailerForm"));
		data.trailerFileVO = {
			'trailerId' : trailerId,
			'fileIds' : new Array(),
			'fileNames' : new Array(),
			'fileURLs' : new Array()
		};
		data.trailerFileVO = imgDataInfo(data.trailerFileVO);
		data.trailerId = trailerId;
		url = "/xxm/rest/trailer/trailerService/carGarage";
	} else if (type == 4 && trailerId != null) {
		if (imgCount == 0) {
			// 消息提示款
			swal({
				title : "很抱歉",
				text : '至少要上传一份文件或图片！',
				type : "error"
			});
			return;
		}
		data = CommonUtils.getFormJson($("#entrustTrailerForm"));
		data = imgDataInfo(data);
		data.trailerId = trailerId;
		url = "/xxm/rest/trailer/trailerService/trailerFileInfo";
	} else if (type == 5 && trailerId != null) {

	} else if (type == 7 && trailerId != null) {// 最终处理
		data = CommonUtils.getFormJson($("#finalAuditAppoveForm"));
		data.trailerId = trailerId;
		url = "/xxm/rest/trailer/trailerService/finalProcessing";
	} else {
		return;
	}
	$.ajax({
		url : url,
		data : JSON.stringify(data),
		type : 'post',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			if (data) {
				// 消息提示款
				swal({
					title : "恭喜您",
					text : data.msg,
					type : "success"
				}, function() {
					// 关闭弹出框
					$("#TrailerModal").modal("hide");
					// 刷新列表
					$("#tbTrailer").bootstrapTable('refresh', init);
					// 重置表单
					$("#trailerForm").get(0).reset();
					$("#editTrailerForm").get(0).reset();
					$("#trailerAppoveForm").get(0).reset();
				});
			} else {
				// 消息提示款
				swal({
					title : "很抱歉",
					text : data.msg,
					type : "error"
				});
			}
		},
		error : errorFunc
	});
}
// 判断弹出框类型（新增、入库）并显示
var showModal = function(type, trailerId) {
	// 弹出模式窗口
	$('#TrailerModal').modal('show');
	// 申请拖车
	if (type == 1) {
		// 重置表单
		$("#trailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("新建拖车申请");
		// 隐藏区域
		hideModule();
		// 显示区域
		$("#addTrailer-div").show();
	}
	// 拖车审核
	if (type == 2) {
		// 重置表单
		$("#trailerAppoveForm").get(0).reset();
		$('#TrailerModal').find("h4").html("拖车审核");
		// 隐藏区域
		hideModule();
		// 显示区域
		$("#trailerAppove-div").show();
		selectInfo(trailerId)// 查询拖车审核信息
		$("#trailerId").val(trailerId);
	}
	// 车辆入库
	if (type == 3) {
		// 图片信息
		imgData = new Array();// 初始化
		imgCount = 0;// 初始化
		$(".img-con").empty();
		// 重置表单
		$("#editTrailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("车辆入库 ");
		// 隐藏区域
		hideModule();
		// 显示区域
		$("#editTrailer-div").show();
		selectInfo(trailerId, 3);
		// id
		$("#trailerId").val(trailerId);
	}
	// 人保委托
	if (type == 4) {
		// 图片信息
		imgData = new Array();// 初始化
		imgCount = 0;// 初始化
		$(".img-con").empty();
		// 重置表单
		$("#entrustTrailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("人保委托 ");
		// 隐藏区域
		hideModule();
		// 显示区域
		$("#entrustTrailer-div").show();
		$("#trailerId").val(trailerId);
	}
	// 转出委托
	if (type == 5) {
		// 重置表单
		$("#editTrailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("转出委托");
		// 隐藏区域
		hideModule();
		$("#btnSave").hide();
		// 显示区域
		$("#rollOutTrailer-div").show();
		qeuryTrailerImage(trailerId);
		$("#trailerId").val(trailerId);
	}
	// 下载材料
	if (type == 6) {
		$("#downTrailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("材料下载");
		// 隐藏区域
		hideModule();
		$("#btnSave").hide();
		// 显示区域
		$("#downTrailer-div").show();
		$("#trailerId").val(trailerId);
	}
	// 最终审核
	if (type == 7) {
		// 重置表单
		$("#entrustTrailerForm").get(0).reset();
		$('#TrailerModal').find("h4").html("最终审核 ");
		// 隐藏区域
		hideModule();
		// 显示区域
		$("#finalAudit").show();
		selectInfo(trailerId, type)// 查询拖车审核信息
		$("#trailerId").val(trailerId);
	}
	// 隐藏值，判断提交数据的类型
	$('#TrailerModal').find("#modalType").val(type);
}
// 隐藏所有的弹出框
var hideModule = function() {
	$("#editTrailer-div").hide();
	$("#addTrailer-div").hide();
	$("#trailerAppove-div").hide();
	$("#entrustTrailer-div").hide();
	$("#finalAudit").hide();
	$("#rollOutTrailer-div").hide();
	$("#downTrailer-div").hide();
	$("#btnSave").show();
}
// 查询委托文件信息
var qeuryTrailerImage = function(value) {
	var data = {
		trailerId : value
	}
	$.ajax({
		url : "/xxm/rest/trailer/trailerService/queryTrailerImageInfo",
		data : JSON.stringify(data),
		type : 'post',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			$("#donw-Images").empty();
			if (data.length != 0) {
				var html = "";
				for (var i = 0; i < data.length; i++) {
					html += '<tr>' + '<td>' + data[i].fileName + '</td>'
							+ '<td><a href="/upload/imgFile/' + data[i].fileURL
							+ '" download=' + data[i].fileName
							+ ' ><i class="fa fa-download"></i>下载</a></td>'
							+ '</tr>'
				}
				$("#donw-Images").append(html);

			} else {
				// 消息提示款
				swal({
					title : "很抱歉",
					text : "没有找到委托文件！",
					type : "error"
				});
			}
		},
		error : errorFunc
	});
}
// 查询拖车审核信息
function selectInfo(value, type) {
	var data = {
		trailerId : value
	};
	$.ajax({
		url : "/xxm/rest/trailer/trailerService/detailsQuery",
		data : JSON.stringify(data),
		type : 'post',
		ajaxOptions : {
			async : false
		// 设置为异步
		},
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			if (data) {
				if (type == 7) {
					var isRelationed = statusForm(data.isRelationed);// 格式化状态
					data.isRelationed = isRelationed;
					var isReport = statusForm(data.isReport);// 格式化状态
					data.isReport = isReport;
					CommonUtils.fillFormData("#finalAuditAppoveForm", JSON.stringify(data.applyVO));
					CommonUtils.fillFormData("#finalAuditAppoveForm", JSON.stringify(data));
					CommonUtils.fillFormData("#finalAuditAppoveForm", JSON.stringify(data.leaseInfoVO));
				} else if (type == 3) {
					$("#loanNo").text(data.loanContractNo);
					CommonUtils.fillFormData("#editTrailerForm", JSON.stringify(data.leaseInfoVO));
					CommonUtils.fillFormData("#editTrailerForm", JSON.stringify(data.applyVO));
				} else {
					CommonUtils.fillFormData("#trailerAppoveForm", JSON.stringify(data));
					CommonUtils.fillFormData("#trailerAppoveForm", JSON.stringify(data.applyVO));
					CommonUtils.fillFormData("#trailerAppoveForm", JSON.stringify(data.customerVO));
					CommonUtils.fillFormData("#trailerAppoveForm", JSON.stringify(data.leaseInfoVO));
				}
			} else {
				// 消息提示款
				swal({
					title : "很抱歉",
					text : "数据未查到!!",
					type : "error"
				});
			}
		},
		error : errorFunc
	});

};
// 是否报案状态格式化
var statusForm = function(value) {
	if (value == 1) {
		return "是";
	}
	return "否";
}
// 上传图片
var initUploadImage = function() {
	var fileSizes = new Array();
	$(document).on('change',"input[name='file']",function() {
		var ts = this;
		if (this.files.length > 10|| (imgCount + this.files.length) > 10) {
			// 消息提示款
			swal({title : "很抱歉",text : "上传图片总数不能超过10个！",type : "info"});
			$("input[name='file']").val("");
				return;
			}
			for (var i = 0; i < this.files.length; i++) {
				var fileSize = this.files[i].size;
				fileSizes[i] = fileSize;
				if (fileSize > 20 * 1024 * 1024) {
					// 消息提示款
					swal({
						title : "很抱歉",
						text : "上传单张图片最大不能超过20M！",
						type : "info"
					});
					$("input[name='file']").val("");
					return;
				}
				var fileName = this.files[i].name;
				var suffixIndex = fileName.lastIndexOf(".");
				var suffix = fileName.substring(suffixIndex + 1)
						.toUpperCase();
				if (suffix != "BMP" && suffix != "JPG"
						&& suffix != "JPEG" && suffix != "PNG"
						&& suffix != "GIF" && suffix != "TXT"
						&& suffix != "DOC" && suffix != "WPS"
						&& suffix != "RTF" && suffix != "HTML"
						&& suffix != "PDF" && suffix != "DOCX"
						&& suffix != "XLSX" && suffix != "XLS") {
					// 消息提示款
					swal({
						title : "很抱歉",
						text : "文档请上传（TXT、DOC、WPS、RTF等格式）!图片请上传（BMP、JPG、JPEG、PNG、GIF等格式）!",
						type : "info"
					});
	
					$("input[name='file']").val("");
					return;
				}
			}
			// 创建FormData对象
			var data = new FormData();
			// 为FormData对象添加数据
			$.each($(this)[0].files, function(i, file) {
				data.append('upload_file', file);
			});
			var FileController = '/xxm/rest/fileUpload/uploadFileService/uploadImg?attachmentItem=trailer';
			$.ajax({
				url : FileController,
				data : data,
				type : 'post',
				dataType : 'json',
				processData : false,
				contentType : false,
				success : function(rs) {
					if (rs.flag) {
						// 消息提示款
						swal({title : "恭喜您",text : rs.msg,type : "success"},
						function() {
							debugger
							// 显示上传成功图片
							if (null != rs.url
									&& "" != rs.url) {
								var serverPath = CommonUtils
										.getServerURL();// 获取网路路径
								var imgURL = rs.url
										.split(","); // 得到文件路径
								var attIds = rs.attrId
										.split(",");//
								var attrName = rs.attrName
										.split(",");// 文件名称
								var html = '';
								for (var i = 0; i < imgURL.length; i++) {
									var temp = imgURL[i];
									var reg = /\\/g;
									temp = temp
											.replace(
													reg,
													"/");
									var fileNames = imgURL[i];
									var suffixIndexs = fileNames
											.lastIndexOf(".");
									var suffixs = fileNames
											.substring(
													suffixIndexs + 1)
											.toUpperCase();
									if (suffixs != "BMP"
											&& suffixs != "JPG"
											&& suffixs != "JPEG"
											&& suffixs != "PNG"
											&& suffixs != "GIF") {
										imgURL[i] = '../img/document.png';
									}
									var size = Math
											.round((fileSizes[i] / 1024));// 获取文件大小
									var html = '<div>'
											+ '<img id="aing-a" title='
											+ attrName[i]
											+ ' src='
											+ imgURL[i]
											+ '>'
											+ '<p class="text" title='
											+ attrName[i]
											+ '>'
											+ attrName[i]
											+ '</p><p>'
											+ size
											+ 'KB</p>'
											+ '<i class="fa fa-times close-img" style="color:red" title="删除图片"></i>'
											+ '</div>'
									$(".img-con")
											.append(
													html);
									if (imgData[0] != null) {
										imgData[imgCount] = {
											'imgURL' : temp,
											'attrName' : attrName[i],
											'attId' : attIds[i]
										};
									} else {
										imgData[i] = {
											'imgURL' : temp,
											'attrName' : attrName[i],
											'attId' : attIds[i]
										};
									}
									imgCount++;
								}
								// 删除图片
								$(".close-img").click(function() {
									var temp = this;
									var div = $(temp).parent();
									var temp = $(div).children();
									var name = $($(temp)[1]).text();
									for (var i = 0; i < imgCount; i++) {
										var fileName = imgData[i].attrName;
										if (fileName == name) {
											imgData.splice(i,1);
											imgCount--;
										}
									}
									div.remove();
								})
							}

						});
					} else {
						// 消息提示款
						swal({title : "很抱歉",text : rs.msg,type : "error"});
					}
				},
				error : errorFunc
			});
		});
}
// 获取上传图片信息
var imgDataInfo = function(data) {
	var fileIds = new Array();
	var fileNames = new Array();
	var fileURLs = new Array();
	if (imgData.length != 0) {
		for (var i = 0; i < imgCount; i++) {
			fileIds.push(imgData[i].attId);
			fileNames.push(imgData[i].attrName);
			fileURLs.push(imgData[i].imgURL);
		}
	}

	data.fileIds = fileIds;
	data.fileNames = fileNames;
	data.fileURLs = fileURLs;
	return data;
}
// 显示图片预览框
$("#dj-img").click(function() {
	showImg();
})
// 入库上传浏览
$("#rk-imgs").click(function() {
	showImg();
})
var showImg = function() {
	$("#img-browse").show();
	$("#img-browse-s").show();
	$(".img-title").text("上传图片预览：");

}
// 隐藏图片预览框
$("#close-div,.close-a,#img-browse").click(function() {
	$("#img-browse-s").fadeToggle("slow");
	$("#img-browse").fadeToggle("slow");
})
// 加载效果
function mask_fullscreen() {
	$.mask_fullscreen(1000);
}
