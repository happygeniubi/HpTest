var moduleCode = '04002';
$(function(){
  role.findRoleList(1);
  role.findRolePage(1);
	// $.findMenu();  //获取菜单
});

function initFun(){
	if(secure.finds){
		role.search();
		role.init();
	}
	if(!secure.adds){
		$('.btn-create').remove();
	}
}

var role = {
	nowid : 0,
	search : function() {
		role.findRoleList(1);
		role.findRolePage(1);
	},
	init : function(){
		$('.btn-create').on('click', function(){
			role.showAddRoleModal();
		});
		$('.btn-save').on('click', function(){
			role.save();
		});
		$('.btn-search').on('click', role.search);
	},
	showAddRoleModal : function(){
		$('input.role-name').removeClass('border-red').val('');
		$('textarea.role-description').removeClass('border-red').val('');
		Dialog.showModal('#roleModal');
	},
	save : function(){
		var url = './hzl/role/addRole';
		if(role.nowid > 0){
			url = './hzl/role/modifyRole';
		}
		$.verify = true;
		var name = $.verifyFrom('input.role-name');
		var description = $.verifyFrom('textarea.role-description');
		if(!$.verify) return;
		$.post(url, {id:role.nowid, name:name, description:description}, function(data){
			if(!$.isSuccess(data)) return;
			Dialog.hideModal('#roleModal');
			Dialog.msg(data.body);
			role.search();
		});
	},
	findRoleList : function(page) {
		var body = $('.data-body').empty();
		var search = $('.search').val();
		$.post('./hzl/role/findRoleList', {search:search, page:page}, function(data){
			if(!$.isSuccess(data)) return;
			$.each(data.body.data, function(index, value){
				$('<tr></tr>')
				.append($('<td></td>').append(value.id))
				.append($('<td></td>').append(value.name))
				.append($('<td></td>').append(value.time))
				.append($('<td></td>').append(value.creator))
				.append($('<td></td>').append(value.description))
				.append($('<td></td>').append(role.getBtns(index, value)))
				.appendTo(body);				
			});
		});
	},
	findRolePage : function(page) {
    $('#pagination ul').empty();
    var search = $('.search').val();
    $.post('./hzl/role/findRoleList', {search:search, page:page}, function(data){
    	console.log(data);
      $.setPage(data.body.page, role.findRoleList);
    });
	},
	getBtns : function(index, value){
		var btns = "";
		btns += "<button type='button' class='btn btn-primary btn-xs' onclick='role.modifyRole("+index+","+value.id+")'><span class='glyphicon glyphicon-pencil'></span> 修改</button>";
		btns += "<button type='button' class='btn btn-warning btn-xs' onclick='role.showModuleModal("+value.id+")'><span class='glyphicon glyphicon-star'></span> 模块</button>";
		btns += "<button type='button' class='btn btn-danger btn-xs' onclick='role.del("+value.id+")'><span class='glyphicon glyphicon-trash'></span> 删除</button>";
		return btns;
	},
	del : function(id) {
		if(!id) return;
		Dialog.confirm("请确认是否删除该角色", function(){
			$.post('./hzl/role/deleteRole', {id:id}, function(data){
				if(!$.isSuccess(data)) return;
				role.search();
				Dialog.msg(data.body);
			});
		});
	},
	modifyRole : function(index, id) {
		if(!id) return;
		role.nowid = id;
		var tdArray = $('.data-body').find('tr').eq(index).find('td');
		$('input.role-name').val(tdArray[1].textContent);
		$('textarea.role-description').val(tdArray[4].textContent);
		Dialog.showModal("#roleModal");

	},
	showModuleModal : function(id){
		if(!id) return;
		var body = $('.module-list-table').empty();		
		role.nowid = id;
		Dialog.loading();
		$.post('./hzl/role/findModuleList', {id:id}, function(data){
			Dialog.hideLoading();
			if(!$.isSuccess(data)) return;
			$.each(data.body, function(index, value){
				if(value.superCode == '0'){
					$("<tr class='mod-tr mod-tr-2'></tr>")
					.append($("<td colspan='2'></td>").append(value.name))
					.appendTo(body);
				} else {
					$("<tr class='mod-tr'></tr>")
					.append($("<td class='mod-td-name'></td>").append(value.name))
					.append(role.getModuleLabel(value))
					.appendTo(body);
				}
			});
			Dialog.showModal('#moduleModal');
		});
	},
	getModuleLabel : function(value) {
		return $("<td></td>")
		.append($("<label></label>").append(role.getCheckbox(value.finds, value.code, 0)).append("查询"))
		.append($("<label></label>").append(role.getCheckbox(value.adds, value.code, 1)).append("添加"))
		.append($("<label></label>").append(role.getCheckbox(value.modifys, value.code, 2)).append("修改"))
		.append($("<label></label>").append(role.getCheckbox(value.deletes, value.code, 3)).append("删除"));
	},
	getCheckbox : function(checked, moduleCode, type){
		if(checked) {
			return "<input type='checkbox' onclick='role.modifyRoleModule(\""+moduleCode+"\", "+type+")' checked=true />";
		}
		return "<input type='checkbox' onclick='role.modifyRoleModule(\""+moduleCode+"\", "+type+")' />";
	},
	modifyRoleModule : function(moduleCode, type) {
		$.post('./hzl/role/modifyRoleModule',{roleId:role.nowid, moduleCode:moduleCode, type:type}
		, function(data){
			if(!$.isSuccess(data)) return;
		});
	}
};