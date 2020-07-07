$(function(){
  $('button.btn-creation').on('click', function(e){  //新增员工弹框
    formal.showAddBox();
  });
});
var formal = {
  showAddBox : function(){
    window.open('/employee_info.jsp');

  }
};