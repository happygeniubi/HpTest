$(function(){
	$('button.btn-creation').on('click', function(e){  //新增员工弹框
		internship.showAddBox();
	});
});
var internship = {
	showAddBox : function(){		
		window.open('./employee_info.html');
	}
};