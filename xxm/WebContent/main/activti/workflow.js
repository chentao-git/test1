function graphTrace(options) {
	
    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid'),
	    pdid: $(this).attr('pdid')
    };
    var opts = $.extend(true, _defaults, options);
    
    alert($(this).attr('pid') + "  " + $(this).attr('pdid'));

    // 处理使用js跟踪当前节点坐标错乱问题
//    $('#changeImg').live('click', function() {
//        $('#workflowTraceDialog').dialog('close');
//        if ($('#imgDialog').length > 0) {
//            $('#imgDialog').remove();
//        }
//        $('<div/>', {
//            'id': 'imgDialog',
//            title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点<button id="diagram-viewer">Diagram-Viewer</button>',
//            html: "<img src='" + ctx + '/workflow/process/trace/auto/' + opts.pid + "' />"
//        }).appendTo('body').dialog({
//            modal: true,
//            resizable: false,
//            dragable: false,
//            width: document.documentElement.clientWidth * 0.95,
//            height: document.documentElement.clientHeight * 0.95
//        });
//    });

	/*
	用官方开发的Diagram-Viewer跟踪
	 */
//	$('#diagram-viewer').live('click', function() {
//		$('#workflowTraceDialog').dialog('close');
//
//		if ($('#imgDialog').length > 0) {
//			$('#imgDialog').remove();
//		}
//
//		var url = ctx + '/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid;
//
//		$('<div/>', {
//			'id': 'imgDialog',
//			title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点',
//			html: '<iframe src="' + url + '" width="100%" height="' + document.documentElement.clientHeight * 0.90 + '" />'
//		}).appendTo('body').dialog({
//			modal: true,
//			resizable: false,
//			dragable: false,
//			width: document.documentElement.clientWidth * 0.95,
//			height: document.documentElement.clientHeight * 0.95
//		});
//	});

   
    //获取图片数据
    var getImage = function(){
    	var imageUrl = "";
    	 //请求后台
        $.ajax({
    		url:'/xxm/rest/activiti/activitiService/processInstance',
    		data:JSON.stringify({pid:opts.pid,type:"image"}),
    		type:'post',  
    		async:false,//同步请求
    		dataType:'json',  
    		contentType: "application/json",
    		success:function(data) {
    			if(data.result){
    				imageUrl = data.imgData;
    			}
    		},
    		error : errorFunc
    	});
        return imageUrl;
    }
    
    //请求后台
    $.ajax({
		url:'/xxm/rest/activiti/activitiService/trace',
		data:opts.pid,
		type:'post',  
		async:false,//同步请求
		dataType:'json',  
		contentType: "application/json",
		success:function(infos) {
			debugger;
			var positionHtml = "";
			//获取图片信息
			var imageUrl = "data:image/png;base64,"+getImage();
	        // 生成图片
	        var varsArray = new Array();
	        $.each(infos, function(i, v) {
	            var $positionDiv = $('<div/>', {
	                'class': 'activity-attr'
	            }).css({
	                position: 'absolute',
	                left: (v.x - 1),
	                top: (v.y - 1),
	                width: (v.width - 2),
	                height: (v.height - 2),
	                backgroundColor: 'black',
	                opacity: 0,
	                zIndex: $.fn.qtip.zindex - 1
	            });

	            // 节点边框
	            var $border = $('<div/>', {
	                'class': 'activity-attr-border'
	            }).css({
	                position: 'absolute',
	                left: (v.x - 1),
	                top: (v.y - 1),
	                width: (v.width - 4),
	                height: (v.height - 3),
	                zIndex: $.fn.qtip.zindex - 2
	            });

	            if (v.currentActiviti) {
	                $border.addClass('ui-corner-all-12').css({
	                    border: '3px solid red'
	                });
	            }
	            positionHtml += $positionDiv.prop("outerHTML") + $border.prop("outerHTML");
	            varsArray[varsArray.length] = v.vars;
	        });

	        if ($('#workflowTraceDialog').length == 0) {
	            $('<div/>', {
	                id: 'workflowTraceDialog',
	                title: '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>',
	                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;width:100%' />" +
	                "<div id='processImageBorder'>" +
	                positionHtml +
	                "</div>" +
	                "</div>"
	            }).appendTo('body');
	        } else {
	            $('#workflowTraceDialog img').attr('src', imageUrl);
	            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
	        }

	        // 设置每个节点的data
	        $('#workflowTraceDialog .activity-attr').each(function(i, v) {
	            $(this).data('vars', varsArray[i]);
	        });
	        
	    	//弹出模式窗口
//	    	$('#workflowTraceDialog').modal('show'); 
	    	
	    	// 此处用于显示每个节点的信息，如果不需要可以删除
            $('.activity-attr').qtip({
                content: function() {
                    var vars = $(this).data('vars');
                    var tipContent = "<table class='need-border'>";
                    $.each(vars, function(varKey, varValue) {
                        if (varValue) {
                            tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
                        }
                    });
                    tipContent += "</table>";
                    return tipContent;
                },
                position: {
                    at: 'bottom left',
                    adjust: {
                        x: 3
                    }
                }
            });
            // end qtip

	        // 打开对话框
	       /* $('#workflowTraceDialog').dialog({
	            modal: true,
	            resizable: false,
	            dragable: false,
	            open: function() {
	                $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>');
	                $('#workflowTraceDialog').css('padding', '0.2em');
	                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);

	                // 此处用于显示每个节点的信息，如果不需要可以删除
	                $('.activity-attr').qtip({
	                    content: function() {
	                        var vars = $(this).data('vars');
	                        var tipContent = "<table class='need-border'>";
	                        $.each(vars, function(varKey, varValue) {
	                            if (varValue) {
	                                tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
	                            }
	                        });
	                        tipContent += "</table>";
	                        return tipContent;
	                    },
	                    position: {
	                        at: 'bottom left',
	                        adjust: {
	                            x: 3
	                        }
	                    }
	                });
	                // end qtip
	            },
	            close: function() {
	                $('#workflowTraceDialog').remove();
	            },
	            width: document.documentElement.clientWidth * 0.95,
	            height: document.documentElement.clientHeight * 0.95
	        });*/
		},
		error : errorFunc 
	});
    
    /*$.getJSON('/xxm/rest/activiti/activitiService/trace?pid=' + opts.pid, function(infos) {
        var positionHtml = "";
        // 生成图片
        var varsArray = new Array();
        $.each(infos, function(i, v) {
            var $positionDiv = $('<div/>', {
                'class': 'activity-attr'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 2),
                height: (v.height - 2),
                backgroundColor: 'black',
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });

            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });

            if (v.currentActiviti) {
                $border.addClass('ui-corner-all-12').css({
                    border: '3px solid red'
                });
            }
            positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
            varsArray[varsArray.length] = v.vars;
        });

        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }

        // 设置每个节点的data
        $('#workflowTraceDialog .activity-attr').each(function(i, v) {
            $(this).data('vars', varsArray[i]);
        });

        // 打开对话框
        $('#workflowTraceDialog').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            open: function() {
                $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>');
                $('#workflowTraceDialog').css('padding', '0.2em');
                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);

                // 此处用于显示每个节点的信息，如果不需要可以删除
                $('.activity-attr').qtip({
                    content: function() {
                        var vars = $(this).data('vars');
                        var tipContent = "<table class='need-border'>";
                        $.each(vars, function(varKey, varValue) {
                            if (varValue) {
                                tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
                            }
                        });
                        tipContent += "</table>";
                        return tipContent;
                    },
                    position: {
                        at: 'bottom left',
                        adjust: {
                            x: 3
                        }
                    }
                });
                // end qtip
            },
            close: function() {
                $('#workflowTraceDialog').remove();
            },
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });

    });*/
}
