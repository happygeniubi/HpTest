$(function(){
  $('button.btn-creation').on('click', function(e){  //新增职位弹框
    position.showAddBox();
  });


});
var position = {
  showAddBox : function(){
    Dialog.showModal('#createModal');
  }
};