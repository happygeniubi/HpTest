var moduleCode = '01001';

$(function(){
	$.findMenu();
});

function initFun(){
	if(secure.finds){
		empl.init();
	}
	if(!secure.adds){
		$('.btn-create').remove();
	}
}

var empl = {
	searchType : 0,
	init : function(){
		empl.findSelectDepartment();
		$('.btn-create').on('click', function(data){
			window.open('employee_info.html?moduleCode='+moduleCode);
		});
		$('ul.search-type-ul').find('li').on('click', function(){
			//alert($(this).index());
			empl.searchType = $(this).index();
			$('.search-type-name').text($(this).find('a').text());		
		});
		$('select.select-department').on('change', function(){
			empl.findSelectPosition($('select.select-department').val());
		});
	},
	findSelectDepartment : function(){
		$.post('./hzl/internship/findSelectDepartment', function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, 'select.select-department', 0);
		});
	},
	findSelectPosition : function(id) {
		if(!id) return;
		$.post('./hzl/internship/findSelectPosition', {id : id}, function(data) {
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, 'select.select-position', 0);
		});
	},
};