var moduleCode = '03001';
$(function(){
	$.findMenu();  //获取菜单
});

function initFun(){
	if(secure.finds){
		demp.search();
		demp.init();
	}
	if(!secure.adds){
		$('.btn-create').remove();
	}
}

var demp = {
	nowId : 0,
	search : function(){
		demp.findDepartmentList(1);  //初始化部门List
		demp.findDepartmentPage(1);  //初始化页码
	},
	init : function(){
		$('button.btn-create').on('click', function(e){  //新增员工弹框
			demp.showAddBox();
		});
		$('button.btn-save').on('click', function(e){ //保存按钮
			demp.save();
		});
		$('button.btn-search').on('click', function(e){ //搜索按钮
			demp.findDepartmentList(1);   //部门List
			demp.findDepartmentPage(1);   //页码
		});
	},
	showAddBox : function(){
		$('input.demp-name').removeClass('border-red').val('');
		$('textarea.demp-desc').removeClass('border-red').val('');
		demp.nowId = 0;
		Dialog.showModal('#createModal');
	},
	save : function(){
		$.verify = true;
		var name = $.verifyFrom('input.demp-name');
		var description = $.verifyFrom('textarea.demp-desc');
		if(!$.verify) return;
		var url = './hzl/department/addDepartment';
		if(demp.nowId) {
			url = './hzl/department/modifyDepartment';
		}
		$.post(url, {id:demp.nowId,name:name,description:description}, function(data){
			Dialog.hideModal('#createModal');
			if(!$.isSuccess(data)) return;
			Dialog.msg(data.body);
			demp.findDepartmentList(1);
			demp.findDepartmentPage(1);
		});
	},
	findDepartmentList : function(page) {
		Dialog.loading(); //显示加载窗口
		var search = $('input.text-search').val();
		var tbody = $('tbody.tbody-list').empty();
		$.post('./hzl/department/findDepartmentList',{page:page,search:search}, function(data){
			Dialog.hideLoading(); //隐藏加载窗口
			if(!$.isSuccess(data)) return;
			$.each(data.body, function(index, value){
				$("<tr></tr>")
				.append($("<td></td>").append(value.id))
				.append($("<td></td>").append(value.name))
				.append($("<td></td>").append(value.time))
				.append($("<td></td>").append(value.creator))
				.append($("<td></td>").append(value.description))
				.append($("<td></td>").append(demp.getBtns(index, value)))
				.appendTo(tbody);
			});
		});
	},
	
	findDepartmentPage : function(page) {
		
		$("#pagination ul").empty();
		var search = $('input.text-search').val();
		$.post('./hzl/department/findDepartmentPage',{
				page : page,
				search:search
			}, function(data){
				$.setPage(data.body, demp.findDepartmentList);
			});
	},
	deleteDepartment : function(id){
		if(!id) return;
		Dialog.confirm("确认删除?",function(){
			$.post("./hzl/department/deleteDepartment", {id : id}, function(data){
				if(!$.isSuccess) return;
				Dialog.msg(data.body);
				demp.findDepartmentList(1); //初始化部门List
				demp.findDepartmentPage(1); //初始化页码
			});
		});
	},
	modifyBox : function(id, index){
		if(!id) return;
		demp.nowId = id;
		var tdArray = $('tbody.tbody-list').find('tr').eq(index).find('td');
		$('input.demp-name').val(tdArray[1].textContent);
		$('textarea.demp-desc').val(tdArray[4].textContent);
		Dialog.showModal('#createModal');
	},
	getBtns : function(index, value) {
		var btn = "";
		btn += secure.modifys ? "<button type='button' class='btn btn-primary btn-xs' onclick=demp.modifyBox("+value.id+","+index+")><span class='glyphicon glyphicon-pencil'></span>&nbsp;编辑</button>" : "";
		btn += secure.deletes ? "<button type='button' class='btn btn-danger btn-xs' onclick=demp.deleteDepartment("+value.id+")><span class='glyphicon glyphicon-remove'></span>&nbsp;删除</button>" : "";
		return btn;
	}
};