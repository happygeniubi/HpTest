/**
 * 登录弹框js
 */
$(function() {
	$('#myModal').modal({
		backdrop : false,
		show : true,
		keyboard : false
	});
	
	//刷新图形验证码的事件监听
	$('img.login-verify-img').on('click', login.resetVerifyCode);
	//重置用户名、密码、验证码的事件监听
	$('button.reset').on('click', login.reset);
	//登录点击事件
	$('button.btn-login').on('click', login.login);
	//刷脸登录点击事件
	$('button.btn-facelogin').on('click', login.facelogin);
	//回车登陆
	$('input.username, input.password, input.login-verify').on('keyup', function(event){
		if(event.keyCode == 13) login.login();
	});
});

var login = {
		'login' : function(){
			$.verify = true;
			var uname = $.verifyFrom('input.username');
			var pword = $.verifyFrom('input.password');
			var verify = $.verifyFrom('input.login-verify');
			if($.verify == false) return;
			Dialog.loading(); //显示加载窗口
			$.post('./hzl/0/login' , {
				username : uname,
				password : pword,
				verify : verify
			}, function(data) {
				if($.isSuccess(data)){
					window.location.href = './index.jsp';
					return;
				}
				Dialog.hideLoading(); //隐藏加载窗口
				login.resetVerifyCode(); // 登录失败时刷新图形验证码
			});
	},
	'facelogin' : function() {
				window.location.href = './facelogin.html';
	},
	//刷新图形验证码
	'resetVerifyCode' : function() {
		$('img.login-verify-img').attr('src', './hzl/0/findVerifycode?'+new Date().getTime());
	},
	//重置表单数据
	'reset' : function() {
		$('.username,.password,.login-verify-input').val('');
		login.resetVerifyCode();//刷新验证码
	}
};