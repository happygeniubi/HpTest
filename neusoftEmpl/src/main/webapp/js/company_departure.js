//批量删除
$(function () {

  $("#emp_delete_all_btn").click(function () {

    var checked = $(".table.table-bordered.table-hover.margin-top-20").find(":input[id='case']:checked");
    console.log(checked);
    if (checked.length <= 0) {
      alert("你还没有选择任何内容！");
      return;
    };
    if(confirm("确定删除所选中的?")==true){
      var ids= "";
      checked.each(function(index, item) {
        var id = $(item).val();
        ids += id + ",";
      });
      if (ids.length > 0) {
        $.post("/hzl/deleteAllEmployee", {"ids": ids}, function (data) {
          alert("已勾选员工离职成功!");
          to_page(currentPage);
        });
      }
    };
  });


//批量删除全选中
  $("#selectAll").click(function () {
    var collid = document.getElementById("selectAll");
    var coll = document.getElementsByName('case');
    if (collid.checked){
      for(var i = 0; i < coll.length; i++)
        coll[i].checked = true;
    }else{
      for(var i = 0; i < coll.length; i++)
        coll[i].checked = false;
    }
  });
});
