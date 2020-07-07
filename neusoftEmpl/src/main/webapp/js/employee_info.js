var moduleCode = '';
var id = 0;
//新增员工经历
$(function(){
	moduleCode = $.getUrlParam('moduleCode');
	if($.getUrlParam('id') != null){
		id = $.getUrlParam('id');
	}
	$.findMenu();
	$('button.btn-add-job').on('click',function(){
		//通过生成tr标签，将tr的html append到tbody中
		//$('tbody.tbody-job-list').append($('<tr></tr>').append($('tbody.tbody-job-list tr:last').html()));
		$('tbody.tbody-job-list').append($('tbody.tbody-job-list tr:last').prop('outerHTML'));
	});
	
	$('button.btn-save-or-modify').on('click',function(){
		var data = getemployeeInfo(); //获取并验证页面中的所有表单数据
		//if(!$.isSubmit) return;
		$.post('./hzl/internship/saveEmployeeInfo', {json:JSON.stringify(data)}, function(data){
			//if(!$.isSuccess(data)) return; 
			var result = data.body;
			if(!result) return;
			window.location = './employee_info.html?moduleCode=' + moduleCode + "&id=" + result;
		});
	});
	$('.demp').on('change', function(){
		$.getPosition();
	});
//	$.findAllProvince();
	$('.register-province, .current-province').on('change', function(){
		$.getCityByPid(this);
	});
	$('.register-city, .current-city').on('change', function(){
		$.getCountyByCid(this);
	});
	$('.register-county, .current-county').on('change', function(){
		$.getTownshipByCid(this);
	});
	$('.register-township, .current-township').on('change', function(){
		$.getVillageByTid(this);
	});
});

function initFun() {
	if(secure.finds) {
		$.getInitData(id);
	}
	
}

//验证页面中的所有表单，并将最终的数据封装为一个JSON对象返回
function getemployeeInfo() {
	$('.color-red').removeClass('color-red');
	var empl = {};
	empl.photo = $('img.emi-photo-image').attr('src');//验证头像
	empl.fullname = $.input('.fullname', false); //验证姓名
	empl.identity = $.input('.identity', false); //验证身份证
	empl.birthday = $.input('.birthday', false); //验证出生年月
	empl.social = $.input('.social', false); //验证社保卡号
	empl.posts = $.input('.posts', false); //验证上岗日期
	empl.phone = $.input('.phone', false); //手机号码
	empl.otherphone = $.input('.otherphone', false); //验证其他联系方式
	empl.emcontact = $.input('.emcontact', false); //验证紧急联系人
	empl.emphone = $.input('.emphone', false); //验证紧急电话
	empl.graschool = $.input('.graschool', false); //验证毕业院校
	empl.major = $.input('.major', false); //验证所学专业
	empl.gradate = $.input('.gradate', false); //验证毕业时间
	empl.education = $.input('.education', false); //验证学历
	empl.degree = $.input('.degree', false); //验证获得学位
	empl.remark = $.input('.remark', false); //验证备注
	empl.demp = $.select('.demp', false); //验证部门
	empl.posi = $.select('.posi', false); //验证职位
	empl.sex = $.radio('sex', false); //验证性别
	empl.culture = $.radio('culture', false); //验证文化程度
	empl.marriage = $.radio('marriage', false); //验证婚姻状况
	empl.political = $.radio('political', false); //验证政治面貌
	empl.nation = $.radio('nation', false); //验证民族
	empl.ifsocial = $.radio('ifsocial', false); //验证是否缴纳社保
	
	empl.company = $.findCompany(); //获取工作单位信息	
	empl.register = $.findRegister(); //获取户籍地址信息
	empl.current = $.findCurrent(); //获取现居住地址信息
	
	console.log(empl);
	return empl;
}

(function($) {
	//如果为true时，说明页面中的所有表单全部验证通过
	$.isSubmit = true;

	$.findRegister = function() {
		var reg = {};
		reg.province = $('.register-province').val();
		//console.log(reg.province);
		reg.city = $('.register-city').val();
		reg.county = $('.register-county').val();
		reg.township = $('.register-township').val();
		reg.village = $('.register-village').val();
		reg.detail = $('.register-address').val();
		if(reg.province < 1 || reg.city < 1 || reg.county < 1 || reg.township < 1) {
			$('.register-label').addClass('color-red');
			return null;
		}
		return reg;
	};
	
	$.findCurrent = function() {
		var cur = {};
		cur.province = $('.current-province').val();
		//console.log(reg.province);
		cur.city = $('.current-city').val();
		cur.county = $('.current-county').val();
		cur.township = $('.current-township').val();
		cur.village = $('.current-village').val();
		cur.detail = $('.current-address').val();
		if(cur.province < 1 || cur.city < 1 || cur.county < 1) {
			$('.current-label').addClass('color-red');
			return null;
		}
		return cur;
	};
	
	$.findAllProvince = function(){
		$.post('./hzl/internship/findAllProvince', function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, $('.register-province'), 0);
			$.echoSelect(data.body, $('.current-province'), 0);
		});
	};
	$.getCityByPid = function(_this) {
		var select = $(_this).hasClass('register-province') ? $('.register-city') : $('.current-city');
		//console.log($('.register-province').val());
		$.echoSelect(null, select, 0);
		var id = $('.register-province').val();
		if(!$(_this).hasClass('register-province')){
			id = $('.current-province').val();
		}
		if(!id) return;
		if($(_this).hasClass('register-province') ? $('.register-province').val() != -1  && $('.register-province').val() != 33 && $('.register-province').val() != 34 : $('.current-province').val() != -1  && $('.current-province').val() != 33 && $('.current-province').val() != 34) {
		$.post('./hzl/internship/findCityByPid', {id:id}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, select, 0);
			});
		}
	};
	$.getCountyByCid = function(_this) {
		var select = $(_this).hasClass('register-city') ? $('.register-county') : $('.current-county');
		$.echoSelect(null, select, 0);
		var id = $('.register-city').val();
		if(!$(_this).hasClass('register-city')){
			id = $('.current-city').val();
		}
		if(!id) return;
		if($('.register-city').val() > -1) {
		$.post('./hzl/internship/findCountyByCid', {id:id}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, select, 0);
			});
		}
	};
	$.getTownshipByCid = function(_this) {
		var select = $(_this).hasClass('register-county') ? $('.register-township') : $('.current-township');
		$.echoSelect(null, select, 0);
		var id = $('.register-county').val();
		if(!$(_this).hasClass('register-county')){
			id = $('.current-county').val();
		}
		if(!id) return;
		if($('.register-county').val() > -1) {
		$.post('./hzl/internship/findTownshipByCid', {id:id}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, select, 0);
		});
		}
	};
	$.getVillageByTid = function(_this) {
		var select = $(_this).hasClass('register-township') ? $('.register-village') : $('.current-village');
		$.echoSelect(null, select, 0);
		var id = $('.register-township').val();
		if(!$(_this).hasClass('register-township')){
			id = $('.current-township').val();
		}
		if(!id) return;
		if($('.register-township').val() > -1) {
		$.post('./hzl/internship/findVillageByTid', {id:id}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, select, 0);
		});
		}
	};
	//获取并验证Input表单中的数据，最后将数据返回
	$.input = function(className,isNull){
		// 1.获取指定元素中的值
		var val = $(className).val();
		// 2.验证这个值是否合法
			// 合法 - 将值返回 	
			// 不合法 - 设置input对应的label标签为红色，并返回null
		if(isNull){
			 return val;
		}
		if(val != null && val != ''){
			 return val; //验证通过，返回数据
		}
		$(className + '-label').addClass('color-red');
		$.isSubmit = false;
		// 3.将值返回
		return ''; //验证不通过，返回空串 
	};
	
	//获取并验证Select表单中的数据，最后将数据返回
	$.select = function(className,isNull){
		// 1.获取指定元素中的值
		var val = $(className).val();
		// 2.验证这个值是否合法
			// 合法 - 将值返回 	
			// 不合法 - 设置input对应的label标签为红色，并返回null
		if(isNull){
			 return val;
		}
		if(val != null && val != -1){
			 return val; //验证通过，返回数据
		}
		$(className + '-label').addClass('color-red');
		$.isSubmit = false;
		// 3.将值返回
		return ''; //验证不通过，返回空串 
	};
	
	//获取并验证Radio表单中的数据，最后将数据返回
	$.radio = function(className, isNull) {
		var obj = $('input[name='+className+']:checked');
		if(isNull && obj.length <=0) {
			return "";
		}
		else if(isNull) {
			return obj.val();
		}
		if(obj.length <= 0) {
			$.isSubmit = false;
			//验证不通过
			$('.'+className + '-label').addClass('color-red');
			return '';
		}
		return obj.val();
	};
	
	$.getPosition = function() {
		$('.posi').empty().append($("<option value=-1>请选择...</option>"));
		var dempId = $('.demp').val();
		if(!dempId) return;
		$.post('./hzl/internship/findSelectPosition', {id:dempId}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body, $('.posi'), 0);
		});
	};
	
	$.findCompany = function() {
		var result = new Array();
		var length = $('tr.tr-job').length;
		for(var i = 0; i<length; i++){
			var com = {};
			com.name = $('input.job-name').eq(i).val();
			com.participateTime = $('input.job-start').eq(i).val();
			com.leaveTime = $('input.job-end').eq(i).val();
			com.position = $('input.job-position').eq(i).val();
			com.reason = $('input.job-reason').eq(i).val();
			if(com.name || com.participatetime || com.leavetime || com.position || com.reason) {
				result.push(com);
			}
		}
		return result;
	};
	
	$.echoRadio = function(datas, eml, name) {
		if(!datas) return;
		$.each(datas, function(i,v){
			if(v.selected){
				$("<label class = 'emi-label'></label>")
				.append("<input type='radio' checked=true name='"+name+"' class='"+name+"' value='"+v.name+"' />")
				.append(v.name)
				.appendTo(eml);
			} else {
				$("<label class = 'emi-label'></label>")
				.append("<input type='radio' name='"+name+"' class='"+name+"' value='"+v.name+"' />")
				.append(v.name)
				.appendTo(eml);
			}
		});
	};
	
	$.getInitData = function(id) {
		$.post('./hzl/internship/getInitData', {id : id}, function(data){
			if(!$.isSuccess(data)) return;
			$.echoSelect(data.body.department, 'select.demp', 0);
			$.echoSelect(data.body.position, 'select.posi', 0);
			$.echoSelect(data.body.register.province, 'select.register-province', 0);
			$.echoSelect(data.body.register.city, 'select.register-city', 0);
			$.echoSelect(data.body.register.county, 'select.register-county', 0);
			$.echoSelect(data.body.register.township, 'select.register-township', 0);
			$.echoSelect(data.body.register.village, 'select.register-village', 0);
			$.echoSelect(data.body.current.province, 'select.current-province', 0);
			$.echoSelect(data.body.current.city, 'select.current-city', 0);
			$.echoSelect(data.body.current.county, 'select.current-county', 0);
			$.echoSelect(data.body.current.township, 'select.current-township', 0);
			$.echoSelect(data.body.current.village, 'select.current-village', 0);
			$('.register-address').val(data.body.register.detail);
			$('.current-address').val(data.body.current.detail);
			$.echoRadio(data.body.culture, $('.td-culture'), 'culture');
			$.echoRadio(data.body.marriage, $('.td-marriage'), 'marriage');
			$.echoRadio(data.body.political, $('.td-political'), 'political');
			$.echoRadio(data.body.nation, $('.td-nation'), 'nation');		
			$('.fullname').val(data.body.info.fullname);
			$('input[name=sex]').eq(data.body.info.sex ? 1 : 0).attr('checked', true);
			$('.identity').val(data.body.info.identity);
			$('.birthday').val(data.body.info.birthday);
			$('.social').val(data.body.info.social);
			$('.posts').val(data.body.info.posts);
			$('.phone').val(data.body.info.phone);
			$('.otherphone').val(data.body.info.otherphone);
			$('.emcontact').val(data.body.info.emcontact);
			$('.emphone').val(data.body.info.emphone);
			$('.graschool').val(data.body.info.graschool);
			$('.major').val(data.body.info.major);
			$('.gradate').val(data.body.info.gradate);
			$('.education').val(data.body.info.education);
			$('.degree').val(data.body.info.degree);
			$('.remark').val(data.body.info.remark);
			$('input[name=ifsocial]').eq(data.body.info.ifsocial=='否'? 1 : 0).attr('checked', true);
			});
	};
	
})(jQuery);

//删除员工经历
function removeJob(_this){
	if($('tr.tr-job').length <=1) {
		return;
	}
	$(_this).parent().parent().remove();
}

KindEditor.ready(function(K) {
	//获取插件editor
	var editor = K.editor({});
	//监听头像的点击事件
	K('img.emi-photo-image').click(function(){
		//加载图片上传插件
		editor.loadPlugin('image',function(){
			//显示图片上传窗口
			editor.plugin.imageDialog({
				showRemote : false
			});
		});
	});
	
});

