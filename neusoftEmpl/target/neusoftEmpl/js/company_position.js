var moduleCode = '03002';
$(function(){
	$.findMenu(); //获取导航菜单
});

function initFun(){
	if(secure.finds){
		position.search();
		position.showSelectDepartment();
		position.init();
	}
	if(!secure.adds){
		$('.btn-create').remove();
	}
}

var position = {

	dataList : null,
	departmentList : null,
	
	search : function(){
		position.findPositionList(1);
		position.findPositionPage(1);
	},
	init : function(){
		$('button.btn-creation').on('click', function(e){  //新增员工弹框
			position.showAddBox();
		});
		$('button.btn-save').on('click', function(e){ //保存按钮
			position.save();
		});
		$('button.btn-search').on('click', function(e){ //搜索按钮
			position.findPositionList(1);   //部门List
			position.findPositionPage(1);   //页码
		});
	},
	findPositionList : function(page){
		Dialog.loading(); //显示加载窗口
		var department = $('select.select-department').val();
		var search = $('input.text-search').val();
		var tbody = $('tbody.tbody-list').empty();
		$.post('./hzl/position/findPositionList', {
			departmentId : department,
			search : search,
			page : page
		}, function(data){
			Dialog.hideLoading(); //隐藏加载窗口
			if(!$.isSuccess(data)) return;
			position.dataList = data.body;
			$.each(data.body, function(index,value){
				$("<tr></tr>")
				.append($("<td></td>").append(value.id))
				.append($("<td></td>").append(value.name))
				.append($("<td></td>").append(value.time))
				.append($("<td></td>").append(value.creator))
				.append($("<td></td>").append(value.departmentName))
				.append($("<td></td>").append(value.description))
				.append($("<td></td>").append(position.getBtns(index)))
				.appendTo(tbody);
			});
		});
	},
	
	findPositionPage : function(page) {
		$("#pagination ul").empty();
		var department = $('select.select-department').val();
		var search = $('input.text-search').val();
		$.post('./hzl/position/findPositionPage',{
			departmentId : department,	
			search : search,
			page : page
		}, function(data) {
			$.setPage(data.body, position.findPositionList);
		});
	},
	
	
	showSelectDepartment : function(){
		$.post('./hzl/position/findSelectDepartment', function(data){
			position.departmentList = data.body;
			$.echoSelect(data.body, 'select.select-department', 0);
		});
	},
	
	showAddBox : function(){
		$('.border-red').removeClass('border-red');
		$('input.posi-name, textarea.posi-desc').val('');
		position.nowId = 0;
		//var select = $('select.posi-department').empty().append("<option value=-1>请选择部门</option");
		$.post('./hzl/position/findSelectDepartment', function(data){
			$.echoSelect(data.body, 'select.posi-department', 0);
			/*$.each(data.body, function(index, value){
				$("<option value="+value.id+"></option>").append(value.name).appendTo(select);
			});*/
			Dialog.showModal('#createModal');
		});
	},
	
	save : function(){
		$.verify = true;
		var department = $.verifySelect('select.posi-department');
		console.log(department);
		var name = $.verifyFrom('input.posi-name');
		console.log(name);
		var description = $.verifyFrom('textarea.posi-desc');
		console.log(description);
		if(!$.verify) return;
		var url = './hzl/position/addPosition';
		if(position.nowId > 0){
			url = './hzl/position/modifyPosition';
		}
		$.post(url, {
			id : position.nowId, //13
			departmentId : department, //6
			name : name, //修改测试
			description : description //修改测试
		}, function(data){
			if(!$.isSuccess(data)) return;
			Dialog.msg(data.body);
			Dialog.hideModal('#createModal');
			position.search();
		});
	},
	
	deletePosition : function(index) {
		if(position.dataList == null || position.dataList.length <= 0){
			return;
		}
		Dialog.confirm("确认删除?", function(data){
		/*console.log(position.dataList[index]);*/
		$.post('./hzl/position/deletePosition', {id:position.dataList[index].id}, function(data){
			if(!$.isSuccess(data)) return;
			Dialog.msg(data.body);
			position.search();
			});
		});
	},
	
	modifyBox : function(index) {
		if(position.dataList == null || position.dataList.length <= 0){
			return;
		}
		var obj = position.dataList[index]
		$('input.posi-name').val(obj.name);
		$('textarea.posi-desc').val(obj.description);
		position.nowId = obj.id;
		$.echoSelect(position.departmentList, 'select.posi-department', obj.department);
		Dialog.showModal('#createModal');
	},
	
	getBtns : function(index) {
		var btn = "";
		btn += secure.modifys ? "<button type='button' class='btn btn-primary btn-xs' onclick=position.modifyBox("+index+")><span class='glyphicon glyphicon-pencil'></span>&nbsp;编辑</button>" : "";
		btn += secure.deletes ? "<button type='button' class='btn btn-danger btn-xs' onclick=position.deletePosition("+index+")><span class='glyphicon glyphicon-remove'></span>&nbsp;删除</button>" : "";
		return btn;
	}
};