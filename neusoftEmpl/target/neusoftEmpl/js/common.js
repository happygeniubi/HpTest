var Dialog = {
			loadingDialog : null,
			loading : function(){
				Dialog.loadingDialog = new BootstrapDialog({
					title : '加载中',
					message : '正在加载,请稍后...',
					closable : false,
					type : BootstrapDialog.TYPE_SUCCESS
				}).open();
			},
			hideLoading : function(){
				Dialog.loadingDialog.close();
			},
			msg : function(text) {
				new BootstrapDialog({
					title : '标题',
					message : text,
					type : BootstrapDialog.TYPE_SUCCESS
				}).open();
			},
			confirm : function(text, cellback) {
				new BootstrapDialog({
					title : '提示信息',
					message : text,
					type : BootstrapDialog.TYPE_SUCCESS,
					buttons : [
						{
							label : '取消',
							action : function(dialog) {
								dialog.close();
							}
						},{
							cssClass: 'btn-primary',
							label : '确定',
							action : function(dialog) {	
								cellback();
								dialog.close();
							}
						 },
					]
				}).open();
			  },
			  showModal : function(id){
				  $(id).modal({
					  backdrop: 'static',
					  keyboard: false, 
				  }).modal('show');
			  },
			  hideModal : function(id){
				  $(id).modal('hide'); 
			  },
			};

var secure = {};

(function($) {
	// 获取传递的参数
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	};
	//检测后台返回的数据是否成功
	$.isSuccess = function(data) {
		if(typeof data != 'object') {
			data = $.parseJSON(data);
		}
		if(data.head == true) {
			return true;
		}
		if(data.body == 'UNLOGIN') {
			window.location.href = './login.html';
			return false;
		}
		if('PERMISSION_DENIED' == data.body){
			Dialog.msg('没有权限!');
			return false;
		}
		Dialog.msg(data.body);
		return false;
	};
	
	$.page = 0;
	$.setPage = function(data, fun) {
		$("#pagination").pagy({
			currentPage: data.current,
			totalPages: data.totalPage,
			innerWindow: 5,
			first:'《',
			prev:'<',
			next:'>',
			last:'》',
			gap:'..',
			truncate: false,
			page: function(clickPage){
				/*if($.page == clickPage) return false;
				$.page = clickPage; 不重复刷新*/
				fun(clickPage);
				return true; 
			}
		});
	};
	
	$.echoSelect = function(data, id, selected){
		var select = $(id).empty().append("<option value=-1>请选择...</option>");
		if(!data) return;
		$.each(data, function(index, value){
			//console.log(value);
			if(value.id == selected || value.selected){
			$("<option value="+value.id+" selected=true></option>").append(value.name).appendTo(select);
			} else {
				$("<option value="+value.id+"></option>").append(value.name).appendTo(select);
			}
		});
	};
	
	$.findMenu = function(){
		var ul = $('ul.nav-list');
		$.post('./hzl/findMenu', function(data){
				 if(!$.isSuccess(data)) return;
			// $.each(data.body, function(index, value){
			// 	if(value.level !=0) return;
      //   $("<li></li>")
      //  		.append($("<a href='#'></a>").append("<i class='fa fa-user'></i>").append($("<span class='nav-label'></span>").append(value.name)).append("<span class='fa arrow'></span>"))
      //     .append($.subMenu(data.body, value.code))
      //     .appendTo(ul);
			// });
			// $.findModuleParameter();
		});
	};
	
	$.findModuleParameter = function(){
//		alert(moduleCode);
		$.post('./hzl/findModuleParameter', {moduleCode : moduleCode}, function(data){
			if(!$.isSuccess(data)) return;
//			console.log(data);
			$('.acctInfo').empty().text(data.body.account);
			$('.module-name-1').text(data.body.superName);
			$('.module-name-2').text(data.body.name);
			secure.finds = data.body.finds == 'true';
			secure.adds = data.body.adds == 'true';
			secure.modifys = data.body.modifys == 'true';
			secure.deletes = data.body.deletes == 'true';
			if(!secure.finds){
				$('.main-container').remove();
				return;
			}
			initFun();
		});
	};
	
	$.subMenu = function(data, code) {
		if(!data || !code) return;
		var ul = $("<ul class='nav nav-second-level collapse'></ul>");
		$.each(data, function(i,v){
			if(v.level != 1 || v.superCode != code) return;
			$("<li></li>").append($("<a class=\"J_menuItem\" href='./" + v.page + "'></a>").append(v.name)).appendTo(ul);
		});
		return ul;
	};
	
	$.verify = true,
	$.verifyFrom = function(className, isNull) {
		var val = $(className).removeClass('border-red').val();
		if(isNull) return val;
		if(val == null || val == '' || val.length <=0){
			$.verify = false;
			$(className).addClass('border-red');
		}
		return val;
	};
	$.verifySelect = function(className, isNull) {
		var val = $(className).removeClass('border-red').val();
		if(isNull) return val;
		if(val == null || val == -1){
			$.verify = false;
			$(className).addClass('border-red');
		}
		return val;
	};
}(jQuery));

$(function(){
	$('.exit').on('click',function(){
		Dialog.confirm('请确定是否需要注销登录!', function(){	
			//清除所有session值
			sessionStorage.clear();  
			//发送请求到后台注销登录，成功后跳转到登录界面;
			window.location = './login.html';
		});
	});
	$('.user-info').on('click',function(){
		BootstrapDialog.show({
			message: $('<div></div>').load('modify_password.html'),
			cssClass: 'modify-password-dialog-box'
		});
	});
	
	$('input.date').on('click, focus', function(e) {
		WdatePicker();
	});
	$('input.date-before').on('click, focus', function(e) {
		WdatePicker({maxDate:'%y-%M-%d'});
	});
	$('input.date-after').on('click, focus', function(e) {
		WdatePicker({minDate:'%y-%M-%d'});
	});
});

function initMy97datepicker() {
	WdatePicker();
}