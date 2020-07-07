<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/7/12
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>实习员工</title>

    <link href="./css/bootstrap-dialog.min.css" rel="stylesheet">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">

    <script src="./js/jquery-1.11.1.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/bootstrap-dialog.min.js"></script>
    <script src="./js/bootstrap-pagy.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/company_department1.js"></script>

</head>

<body>

<div class="container breadcrumb-container">
    <ol class="breadcrumb">
        <li>管理系统</li>
        <li>部门与职位</li>
        <li class="active">部门管理</li>
    </ol>
</div>

<div class="container main-container">
    <!-- 代码区 -->
    <div class="row">
        <div class="col-md-10">
            <div class="input-group w600">
                <input type="text" id="departmentContent" class="form-control text-search" placeholder="请输入部门名称..." />
                <div class="input-group-btn">
                    <button type="button" onclick="selectDepartment(1)" class="btn btn-primary btn-search">
                        <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
                    </button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-10">
                <button class="btn btn-primary btn-creation" id="emp_add_modal_btn"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;新增</button>
                <button class="btn btn-danger btn-sm" onclick="allDelete()" id="emp_delete_all_btn">批量删除</button>
            </div>
        </div>
    </div>
    <!-- 数据列表 -->
    <table id="department_list" class="table table-bordered table-hover margin-top-20">
        <thead>
        <tr>
            <th><input type="checkBox" id="selectAll" name="case" onclick="selectAllCheckBox('case')"/></th>
            <th>名称</th>
            <th>创建时间</th>
            <th>创建者</th>
            <!-- <th style="width:120px;">部门经理</th> -->
            <th>简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="tbody-list" id="post">

        </tbody>
    </table>

    <!-- 显示分页信息 -->
    <div class="row">
        <!--分页文字信息  -->

        <div class="col-md-4" id="page_nav_area"></div>
        <!-- 分页条信息 -->
        <div class="col-md-8" id="page_info_area"></div>


    </div>

</div>
<div class="container footer-container">
    开发者-happygod-<a href>http://www.happygeniubibibibi.com</a>
</div>

<!-- Modal -->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="mycreateModal">
    <div class="modal-dialog" role="document" style="width:430px;">
        <div class="modal-content login-modal-content">

            <div class="modal-header modal-header-primary">
                <h4 class="modal-title" id="mycreateModal">新增部门</h4>
            </div>

            <div class="modal-body">
                <form id="department" method="post" role="form" >

                    <div class="form-group">
                        <input type="tel" id="deName" name="deName"  minlength="2" class="form-control demp-name" placeholder="部门名称" required>
                    </div>

                    <div class="form-group">
                        <textarea id="deMessage" name="deMessage" class="form-control margin-top-10 demp-desc" rows="2" placeholder="部门描述信息"></textarea>
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" id="addDepartment"  class="btn btn-primary btn-save" >保存</button>
                    </div>
                </form>

            </div>


        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="departmentMessage" tabindex="-1" role="dialog" aria-labelledby="mycreateModal">
    <div class="modal-dialog" role="document" style="width:430px;">
        <div class="modal-content login-modal-content">
            <div class="modal-header modal-header-primary">
                <h4 class="modal-title" id="mycreateModal1">部门编辑</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="deId" value="">
                <input type="tel" id="departmentName1"   class="form-control demp-name" placeholder="部门名称" value="">
                <textarea id="departmentMesaage1"  class="form-control margin-top-10 demp-desc" value="" rows="2" placeholder="部门描述信息"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="saveDepartment"  class="btn btn-primary btn-save" >保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">




  //定义一个全局变量，获得当前中的记录数
  var total;

  //定义一个全局变量，记录当前修改的页面
  var currentPage;

  var choose;

  $(function(){
    //页面加载完毕，去首页获得对应的数据
    to_page(1);

  });


  function to_page(pn){
    $.ajax({
      url:"/hzl/listPosition",
      data:"curPage="+pn,
      type:"GET",
      success:function(result){
        //console.log(result);
        //1、解析并显示员工数据
        choose=result.page;

        build_emps_table(result);
        //2、解析并显示分页信息
        build_page_info(result);
        //3、解析显示分页条数据
        build_page_nav(result);
      }
    });
  };


  function build_emps_table(result){
    //在构建之前先清空所有的数据
    $("#department_list tbody").empty();
    //第一步：获得所有的员工数据
    var emps = result.pageInfo.list;
    //第二步：对员工数据进行遍历显示出来
    $.each(emps,function(index,item){
      var all = $("<input type='checkbox'  id='case' name='case' class='check_item'/>").attr("value",item.deId);
      var allDeleteBtn= $("<td></td>").append(all);
      var deName = $("<td></td>").append(item.deName);
      var deDate = $("<td></td>").append(item.deDate);
      var dePeople = $("<td></td>").append(item.dePeople);
      var deMessage = $("<td></td>").append(item.deMessage);

      //添加编辑按钮
      var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs").attr("value",item.deId)
        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
      var delBtn =  $("<button></button>").addClass("btn btn-danger btn-xs").attr("value",item.deId)
        .append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");



      var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);

      //这里我们点击编辑按钮的时候，在弹出编辑对话框之前我们要使用ajax到后台去查询对应的员工的信息，这里需要点击编辑按钮的时候，需要得到被修改员工的id，这里设计的比较巧秒，我们在创建出编辑按钮的时候，给编辑按钮添加一个自定义的属性。该属性值就是员工的id

      //               在点击编辑按钮的时候我们就可以通过该属性获得的该员工的id


      //将上面的table表td数据添加到tr中，这里是一个链式操作
      $("<tr></tr>").append(allDeleteBtn)
        .append(deName)
        .append(deDate)
        .append(dePeople)
        .append(deMessage)
        .append(btnTd)
        .appendTo("#department_list tbody");

      //"#emps_table tbody"表示选取id为emps_table下的所有的<tbody>的元素，不清楚的看锋利的jquery书籍相当的经典


    });

  };


  //解析显示分页信息
  function build_page_info(result){
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第"+result.pageInfo.pageNum+"页,"+"总共"+result.pageInfo.pages+"页,"+"总共"+
      result.pageInfo.total+"条记录");
    total =  result.pageInfo.total;
    currentPage = result.pageInfo.pageNum;

  }

  //解析右下角的导航栏
  function build_page_nav(result){
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#").attr("aria-label","Previous"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("aria-label","Previous"));
    //判断是否有前一页，如果没有前一页就不能点击
    if(result.pageInfo.hasPreviousPage == false){
      firstPageLi.addClass("disabled");
      prePageLi.addClass("disabled");
    }else {
      prePageLi.click(function () {
        //跳转到当前页的前一页
        if(choose==0){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageNum - 1);

        }
        if(choose==1){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectDepartment(result.pageInfo.pageNum - 1);

        }


      });
    };
    //给前一页和首页添加按钮点击事件
    firstPageLi.click(function(){
      //跳转到当前页的前一页
      if(choose==0){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(1);


      }
      if(choose==1){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        selectDepartment(1);

      }


    });



    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));

    //如果没有下一页不能被点击
    if(result.pageInfo.hasNextPage == false){
      nextPageLi.addClass("disabled");
      lastPageLi.addClass("disabled");
    }
    else{
      //给下一页和尾页添加点击事
      nextPageLi.click(function () {
        if(choose==0){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageNum + 1);


        }
        if(choose==1){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectDepartment(result.pageInfo.pageNum + 1);


        }
      });
    };
    lastPageLi.click(function(){
      if(choose==0){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(result.pageInfo.pages);



      }
      if(choose==1){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        selectDepartment(result.pageInfo.pages)

      }
    });

    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.pageInfo.navigatepageNums,function(index,item){
      var numLi = $("<li></li>").append($("<a></a>").append(item));
      //判断当前遍历的如果是当前正在显示的页面，应该高亮显示
      if(result.pageInfo.pageNum == item){
        numLi.addClass("active");
      }
      //添加点击事件
      numLi.click(function(){
        //点
        // 击的时候重新使用ajax到服务器查询数据
        if(choose==0){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(item);
        }
        if(choose==1){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectDepartment(item);

        }

      });
      ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);
    //把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
  };

  //接下来实现删除操作，给删除按钮添加点击事件
  //给编辑按钮添加点击事件
  $(document).on("click",".btn.btn-danger.btn-xs",function(){
    //首先需要获得要删除的联系人的姓名如何获得了,主要这里不能写成parent，暂时还没有弄明白
    //alert($(this).parents("tr").find("td:eq(1)").text());
    //删除联系人的id
    var deId = $(this).val();
    //弹出确认对话框
    if(confirm("你确认删除"+$(this).parents("tr").find("td:eq(1)").text()+"吗?")){
      //发生ajax删除联系人
      $.ajax({
        url:"/hzl/deletePosition",
        type:"post",
        data:"deId="+deId,
        dataType:"json",
        success:function(result){
          if(choose == 0){
            //说明删除联系人成功
            //重新ajax请求到删除联系人的页面
            if(result.pageInfo){
              to_page(currentPage);

            }else {
              alert("部门下有职位，不能删除");
              to_page(currentPage);
            }
          }

          if(choose == 1){
            if(result.pageInfo){
              selectDepartment(currentPage);

            }else {
              alert("部门下有职位，不能删除");
              selectDepartment(currentPage);
            }
          }

        }



      });

    }
  });





  //给编辑按钮添加点击事件
  $(document).on("click",".btn.btn-primary.btn-xs",function(){

    $("#deId").val("");
    $("#departmentName1").val(''); ;
    $("#departmentMesaage1").val("");

    var deId = $(this).val();
    //使用ajax请求部门的数据
    $.ajax(
      {
        url:"/hzl/updateDeapartment",
        type:"post",
        data:"deId="+deId,
        dataType:"json",
        success:function(result){
          $("#departmentName1").val(result[0].deName);
          $("#departmentMesaage1").val(result[0].deMessage);
          $("#deId").attr("value",result[0].deId);
        }


      }
    );

    //弹出对话框
    $('#departmentMessage').modal({
      backdrop:'static'
    })

  });

  //给编辑联系人的保存按钮增加点击事件
  $("#saveDepartment").click(function(){

    //使用ajax提交数据到后台更新数据这里需要
    //强调的是我们采用的是put方式的提交
    // data:$("#addEmpModal form").serialize()传递的请求参数中必须携带一个_method
    var deId = $("#deId").val();
    var deName = $("#departmentName1").val();
    var deMessage = $("#departmentMesaage1").val();
    $.ajax({
      url:"/hzl/updateDe",
      type:"post",
      data:{
        deId:deId,
        deName:deName,
        deMessage:deMessage,
      },
      dataType:"json",
      success:function(result){
        //修改联系人成功之后，关闭修改联系人的摸态对话框
        if(choose==0){
          $("#departmentMessage").modal('hide');
          //跳转到当前的修改页面
          to_page(currentPage);
        }
        if(choose==1){
          $("#departmentMessage").modal('hide');
          //跳转到当前的修改页面
          selectNotice(currentPage);
        }
      }
    });

  });

  $('#addDepartment').on('click',function (e) {  //增加员工

    var deName = $("#deName").val();
    var deMessage = $("#deMessage").val();
    $.ajax({
      type:"post",
      url:"/hzl/savePosition",
      data:{
        deName:deName,
        deMessage:deMessage,
      },
      dataType:"json",
      success:function () {

        $("#createModal").modal('hide');
        to_page(currentPage);


      }
    });



  });


  function selectDepartment(pn) {


    var content = $("#departmentContent").val();
    var curPage = pn;
    $.ajax({
      type:"post",
      url:"/hzl/selectPosition",
      data:{
        content:content,
        curPage:curPage,
      },
      dataType:"json",
      success:function (result) {
        choose=result.page;
        build_emps_table(result);
        build_page_info(result);
        build_page_nav(result);
      }
    });
  };

  //批量删除
  function allDelete() {
    var checked = $("#department_list").find(":input[id='case']:checked");
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
        $.post("/hzl/deleteAllDeapartment", {"ids": ids}, function (data) {
          if(data){
            alert("已勾选部门删除成功!");
            to_page(currentPage);
          }else {
            alert("勾选部门下有职位，不能删除");
            to_page(currentPage);
          }
        });
      }
    };
  };

  //批量删除全选中
  function selectAllCheckBox(Obj){
    var collid = document.getElementById("selectAll");
    var coll = document.getElementsByName(Obj);
    if (collid.checked){
      for(var i = 0; i < coll.length; i++)
        coll[i].checked = true;
    }else{
      for(var i = 0; i < coll.length; i++)
        coll[i].checked = false;
    }
  };


  $(document).ready(function () {

    if(location.href.indexOf("#reloaded")==-1){
      location.href=location.href+"#reloaded";
      location.reload();
    }
  });


</script>

