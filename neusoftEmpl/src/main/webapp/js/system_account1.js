$(function(){
  $('button.btn-creation').on('click', function(e){  //新增部门弹框


    account.showAddBox();
  });


});
var account = {
  showAddBox : function(){
    Dialog.showModal('#createModal');
  }
};