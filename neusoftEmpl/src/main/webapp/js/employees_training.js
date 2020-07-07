$(function(){
	$('button.btn-creation').on('click', function(e){  //新增培训项目弹框
		training.showAddBox();
	});
});
var training = {
	showAddBox : function(){		
		Dialog.showModal('#createModal');
	}
};