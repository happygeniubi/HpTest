var moduleCode = '04001';
$(function(){
  account.findAccountList(1);
  account.findAccountPage(1);
  $.findModuleParameter();
	//$.findMenu();  //获取菜单
});

function initFun(){
	if(secure.finds){
		// account.search();
		// account.init();
	}
	if(!secure.adds){
		$('.btn-create').remove();
	}
}

var account = {
	nowid : 0,
  data_list: null,
	search : function(){
		account.findAccountList(1);
		account.findAccountPage(1);
	},
	init : function(){
		$('.btn-create').on('click', function(){
			account.showAddAccountModal();
		});
		$('.btn-save').on('click', function(){
			account.save();
		});
		$('.btn-search').on('click', function(){
			account.search();
		});
	},
	showAddAccountModal : function(){
		$('input.acc-username').removeClass('border-red').val('');
		$('input.acc-nickname').removeClass('border-red').val('');
		$('input.acc-password').removeClass('border-red').val('').show();
		account.nowid = 0;
		Dialog.showModal('#accountModal');
	},
	findAccountList : function(page){
		var body = $('.data-body').empty();
		var search = $('.search').val();
		account.data_list = new Array();
		$.post('./hzl/account/findAccountList', {search:search, page:page}, function(data){
			if(!$.isSuccess(data)) return;
			$.each(data.body.data, function(index,value){
				$("<tr></tr>")
				.append($("<td></td>").append(value.id))
				.append($("<td></td>").append(value.username))
				.append($("<td></td>").append(value.nickname))
				.append($("<td></td>").append(value.time))
				.append($("<td></td>").append(value.creator))
				.append($("<td></td>").append(account.getBtns(index,value)))
				.appendTo(body);
			});
		});
	},
	findAccountPage : function(page) {
		$('#pagination ul').empty();
		var search = $('.search').val();
    $.post('./hzl/account/findAccountList', {search:search, page:page}, function(data){
      $.setPage(data.body.page, account.findAccountList);
    });
	},
	getBtns : function(index, value){
		var btns = "";
		btns +=  "<button type='button' class='btn btn-primary btn-xs' onclick='account.modifyAccount("+index+","+value.id+")'><span class='glyphicon glyphicon-pencil'></span> 修改</button>";
		btns +=  "<button type='button' class='btn btn-success btn-xs' onclick='account.resetPassword("+value.id+")'><span class='glyphicon glyphicon-cog'></span> 初始密码</button>";
		btns +=  "<button type='button' class='btn btn-warning btn-xs' onclick='account.accountRoleList("+value.id+")'><span class='glyphicon glyphicon-user'></span> 角色</button>";
		btns +=  "<button type='button' class='btn btn-danger btn-xs' onclick='account.del("+value.id+")'><span class='glyphicon glyphicon-trash'></span> 删除</button>";
		return btns;
	},
	save : function(){
		$.verify = true;
		var url = './hzl/account/addAccount';
		var username = $.verifyFrom('input.acc-username');
		var nickname = $.verifyFrom('input.acc-nickname');
		var password = '';
		if(account.nowid > 0){
			url = './hzl/account/modifyAccount';
		}else {
			password = $.verifyFrom('input.acc-password');
		}
		if(!$.verify) return;
		$.post(url, {
			id : account.nowid,
			username : username,
			nickname : nickname,
			password : password
		}, function(data){
			if(!$.isSuccess(data)) return;
			Dialog.msg(data.body);
			Dialog.hideModal('#accountModal');
			account.search();
		});
	},
	del : function(id){
		if(!id) return;
		Dialog.confirm("请确认是否删除该帐号", function(){
			$.post('./hzl/account/deleteAccount', {id:id}, function(data){
				if(!$.isSuccess(data)) return;
				Dialog.msg(data.body);
				account.search();
			});
		});
	},
	resetPassword : function(id){
		if(!id) return;
		Dialog.confirm("请确认是否重置该帐号的密码为<b>happygeniubi<b/>", function(){
			$.post('./hzl/account/resetPassword', {id:id}, function(data){
				if(!$.isSuccess(data)) return;
				Dialog.msg(data.body);
				account.search();
			});
		});
	},
	modifyAccount : function(index, id){
		$('input.acc-password').hide();
		var tdArray = $('.data-body').find('tr').eq(index).find('td');
		$('input.acc-username').attr('disabled', 'true').val(tdArray[1].textContent);
		$('input.acc-nickname').val(tdArray[2].textContent);
		Dialog.showModal('#accountModal');
		account.nowid = id;
	},
	accountRoleList : function(id) {
		if(!id) return;
		account.nowid = id;
		var ul = $('ul.role-list').empty();
		Dialog.loading();
		$.post("./hzl/account/findAccountRole", {id:id}, function(data){
			Dialog.hideLoading();
			if(!$.isSuccess(data)) return;
			Dialog.showModal('#accountRoleModal');
			$.each(data.body, function(index, value){
				$("<li class='role-item'></li>")
				.append($("<label></label>")
						.append(value.selected ? "<input type='checkbox' checked=true onclick='account.saveRole("+value.id+")' />" : "<input type='checkbox' onclick='account.saveRole("+value.id+")'/>")
						.append(value.name)
				).appendTo(ul);
			});
		});
	},
	saveRole : function(roleId) {
		if(!roleId || !account.nowid) return;
		$.post('./hzl/account/saveRole', {accountId : account.nowid, roleId : roleId}, function(data){
			$.isSuccess(data);
		});
	}
};