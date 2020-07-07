$(function(){
  $('button.btn-creation').on('click', function(e){  //新增部门弹框


    dempatment.showAddBox();
  });


});
var dempatment = {
  showAddBox : function(){
    Dialog.showModal('#createModal');
  }
};