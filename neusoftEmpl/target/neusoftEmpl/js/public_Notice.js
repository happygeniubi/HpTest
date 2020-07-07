$(function() {

  $('button.btn.btn-primary.btn-creation').on('click', function (e) {  //新增员工弹框
    creation.showAddBox();
  });


});



var creation = {           //增加公告弹窗
  showAddBox : function(){
    Dialog.showModal("#createAddModal")
  }
};