$(function () {
	$("#menuNavbar .dropdown").mouseleave(function(){
		$(this).click();
	});
});


//获取菜单层级
var menuLevel =function(level){
	if(level == 2){
		return "second";
	}
	if(level == 3){
		return "third";
	}
}

//判断子节点中是否含有菜单节点
var existsMenu = function(clildMenus){
	if(null != clildMenus && clildMenus.length > 0){
		for(var i = 0; i < clildMenus.length ; i++){
			if(clildMenus[i].menuType == "0"){
				return true;
			}
		}
	}
	return false;
}

/*
//递归子菜单
var recursionMenus = function(level,userMenus){
	//子菜单定位到3级菜单
	if(level > 3){
		return "";
	}
	var menuDoc = '<ul class="nav nav-'+menuLevel(level)+'-level">';
	for(var i = 0; i < userMenus.length ; i++){
		var menu = userMenus[i];
		//过滤按钮节点
		if(menu.menuType != "0"){
			continue;
		}
		var clildMenus = menu.childMenus;
		menuDoc += '<li>';
		menuDoc += '<a '+((menu.menuPath != "#")?'class="J_menuItem"':'')+' href="'+menu.menuPath+'">' + menu.menuName;
		if(existsMenu(clildMenus)){
			menuDoc += '<span class="fa arrow"></span>';
			menuDoc += '</a>';
			menuDoc += recursionMenus(level+1,menu.childMenus);
		}else{
			menuDoc += '</a>';
		}
		menuDoc += '</li>';
	}
	menuDoc += '</ul>';
	return menuDoc;
}

//获取用户信息
$.ajax({url:'/xxm/rest/user/userService/findLoginUser',
	data:JSON.stringify({}),
	type:'post',  
	dataType:'json',  
	async:false,//同步请求
	contentType: "application/json",
	success:function(data) {
		if (data && null != data) {
			$("#userName").html(data.name);
			var userMenus = data.userMenus;
			//用户菜单
			if(null != userMenus && userMenus.length > 0){
				var menuDoc = "";
				for(var i = 0; i < userMenus.length ; i++){
					var menu = userMenus[i];
					//过滤按钮节点
					if(menu.menuType != "0"){
						continue;
					}
					var clildMenus = menu.childMenus;
					menuDoc += "<li>";
					menuDoc += '<a '+ ((menu.menuPath != "#")?'class="J_menuItem"':'') +' href="'+menu.menuPath+'">';
					menuDoc += '<i class="'+menu.extendField1+'"></i>';
					menuDoc += '<span class="nav-label">'+menu.menuName+'</span>';
					if(existsMenu(clildMenus)){
						menuDoc += '<span class="fa arrow"></span>';
						menuDoc += "</a>";
						menuDoc += recursionMenus(2,clildMenus);
					}else{
						menuDoc += "</a>";
					}
					menuDoc += "</li>";
				}
				$("#side-menu").append(menuDoc);
			}
		}
	},
	error : function() {  
		//消息提示款
		swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
	}  
});

*/

//递归子菜单
var recursionMenus2 = function(level,userMenus){
	//子菜单定位到3级菜单
	if(level > 3){
		return "";
	}
	var menuDoc = '';
	if(level == 2){
		menuDoc += '<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">';
	}else{
		menuDoc += '<ul class="dropdown-menu">';
	}
	for(var i = 0; i < userMenus.length ; i++){
		var menu = userMenus[i];
		//过滤按钮节点
		if(menu.menuType != "0"){
			continue;
		}
		var clildMenus = menu.childMenus;
		//判断是否含有子菜单
		var rs = existsMenu(clildMenus);
		menuDoc += '<li '+(rs?'class="dropdown-submenu"':'')+'>';
		menuDoc += '<a '+((menu.menuPath != "#")?'class="J_menuItem"':'')+' href="'+(menu.menuPath)+'">' + menu.menuName + '</a>';
		if(rs){
			menuDoc += recursionMenus2(level+1,menu.childMenus);
		}
		menuDoc += '</li>';
	}
	menuDoc += '</ul>';  
	return menuDoc;
}


//获取用户信息
$.ajax({url:'/xxm/rest/user/userService/findLoginUser',
	data:JSON.stringify({}),
	type:'post',  
	dataType:'json',  
	async:false,//同步请求
	contentType: "application/json",
	success:function(data) {
		if (data && null != data) {
			$("#userName").html(data.name);
			var userMenus = data.userMenus;
			//用户菜单
			if(null != userMenus && userMenus.length > 0){
				var menuDoc = "";
				for(var i = 0; i < userMenus.length ; i++){
					var menu = userMenus[i];
					//过滤按钮节点
					if(menu.menuType != "0"){
						continue;
					}
					var clildMenus = menu.childMenus;
					//判断是否含有子菜单
					var rs = existsMenu(clildMenus);
					menuDoc += '<li class="dropdown">';
					if(rs){
						//class="btn btn-primary"
						menuDoc += '<a role="button" data-toggle="dropdown" class="" data-target="#" href="javascript:;"><i class="'+menu.extendField1+'"></i>' + menu.menuName;
						menuDoc += '<span class="caret"></span>';
						menuDoc += '</a>';
						menuDoc += recursionMenus2(2,clildMenus);
					}else{
						menuDoc += '<a role="button" data-toggle="dropdown" data-target="#" '+((menu.menuPath != "#")?'class="J_menuItem"':'')+' href="'+(menu.menuPath)+'"><i class="'+menu.extendField1+'"></i>' + menu.menuName + '</a>';
					}
					menuDoc += "</li>";
				}
				$("#menuNavbar").append(menuDoc);
			}
		}
	},
	error : function() {  
		//消息提示款
		swal({title : "很抱歉",text : "系统错误，请联系管理员！",type : "error"});
	}  
});


//退出系统
var quit = function(){
	//获取用户信息
	$.ajax({url:'/xxm/rest/user/userService/quit',
		data:JSON.stringify({}),
		type:'post',  
		dataType:'json',  
		async:false,//同步请求
		contentType: "application/json",
		success:function(data) {
			//跳转页面
			window.location.href="../main/login.html";
		}
	});
}


