<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/7/21
  Time: 15:43
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

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">


    <script src="./js/jquery-1.11.1.min.js"></script>
    <script src="./js/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script src="./js/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/bootstrap-pagy.min.js"></script>
    <script src="./js/bootstrap-dialog.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/company_position1.js"></script>

</head>

<body>

<div class="container breadcrumb-container">
    <ol class="breadcrumb">
        <li>管理系统</li>
        <li>部门与职位</li>
        <li class="active">职位管理</li>
    </ol>
</div>

<div class="container main-container">
    <!-- 代码区 -->
    <div class="row">
        <div class="col-md-10">
            <div class="input-group w600">

                <input id="positionContent" type="text" class="form-control text-search" placeholder="请输入职位名称..." />
                <div class="input-group-btn">
                    <button onclick="selectPosition(1)" type="button" class="btn btn-primary btn-search">
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
    <table id="position_list" class="table table-bordered table-hover margin-top-20">
        <thead>
        <tr>
            <th><input type="checkBox" id="selectAll" name="case" onclick="selectAllCheckBox('case')"/></th>
            <th style="width:130px;">职位名称</th>
            <th style="width:140px;">创建时间</th>
            <th style="width:100px;">创建人</th>
            <th style="width:150px;">所属部门</th>
            <th style="width:380px;">简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="position" class="tbody-list">
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
                <h4 class="modal-title" id="mycreateModal">新增职位</h4>
            </div>
            <form id="positionMessage">

                <div class="modal-body">


                    <div class="form-group">
                        <select id="department" class="form-control posi-department">

                            <option value ="-1">请选择</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="tel" id="poName" name="poName" class="form-control margin-top-10 posi-name" placeholder="职位名称" required>
                    </div>

                    <div class="form-group">
                        <textarea id="poMessage" name="poMessage" class="form-control margin-top-10 posi-desc" rows="2" placeholder="职位描述信息" required></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="addPosition"  class="btn btn-primary btn-save" >保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 修改职位弹窗 -->
<div class="modal fade" id="createModal1" tabindex="-1" role="dialog" aria-labelledby="mycreateModal">
    <div class="modal-dialog" role="document" style="width:430px;">
        <div class="modal-content login-modal-content">
            <div class="modal-header modal-header-primary">
                <h4 class="modal-title" id="mycreateModal2">职位编辑</h4>
            </div>
            <div class="modal-body">
                <select id="department1" class="form-control posi-department">

                    <option value ="-1">请选择</option>

                </select>
                <input type="tel" id="poName1" class="form-control margin-top-10 posi-name" placeholder="职位名称">
                <input type="hidden" id="poId" value="">
                <textarea id="poMessage1" class="form-control margin-top-10 posi-desc" rows="2" placeholder="职位描述信息"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="savePosition"  class="btn btn-primary btn-save" >保存</button>
            </div>
        </div>
    </div>
</div>

</body>
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
      url:"/hzl/classList",
      data:"curPage="+pn,
      type:"GET",
      success:function(result){
        //console.log(result);
        //1、解析并显示员工数据
        choose=result.page;
        var de = result.pageInfo.postList;

        var str = "";
        var str1 = "<option value =\"-1\">请选择</option>";
        for(var i =0;i<de.length;i++){
          str+="<option value ="+de[i].deId+">"+de[i].deName+"</option>";
        }
        $("#department").empty();
        $("#department").html(str);
        $("#department1").empty();
        $("#department1").html(str);
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
    $("#position_list tbody").empty();
    //第一步：获得所有的员工数据
    var emps = result.pageInfo.pageInfo.list;
    //第二步：对员工数据进行遍历显示出来
    $.each(emps,function(index,item){
      var all = $("<input type='checkbox'  id='case' name='case' class='check_item'/>").attr("value",item.poid);
      var allDeleteBtn= $("<td></td>").append(all);
      var poName = $("<td></td>").append(item.poname);
      var poDate = $("<td></td>").append(item.podate);
      var poPeople = $("<td></td>").append(item.popeople);
      var poDepartment = $("<td></td>").append(item.podepartment);
      var poMessage = $("<td></td>").append(item.pomessage);

      //添加编辑按钮
      var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs").attr("value",item.poid)
        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
      var delBtn =  $("<button></button>").addClass("btn btn-danger btn-xs").attr("value",item.poid)
        .append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");



      var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);

      //这里我们点击编辑按钮的时候，在弹出编辑对话框之前我们要使用ajax到后台去查询对应的员工的信息，这里需要点击编辑按钮的时候，需要得到被修改员工的id，这里设计的比较巧秒，我们在创建出编辑按钮的时候，给编辑按钮添加一个自定义的属性。该属性值就是员工的id

      //               在点击编辑按钮的时候我们就可以通过该属性获得的该员工的id


      //将上面的table表td数据添加到tr中，这里是一个链式操作
      $("<tr></tr>").append(allDeleteBtn)
        .append(poName)
        .append(poDate)
        .append(poPeople)
        .append(poDepartment)
        .append(poMessage)
        .append(btnTd)
        .appendTo("#position_list tbody");

      //"#emps_table tbody"表示选取id为emps_table下的所有的<tbody>的元素，不清楚的看锋利的jquery书籍相当的经典


    });

  };


  //解析显示分页信息
  function build_page_info(result){
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第"+result.pageInfo.pageInfo.pageNum+"页,"+"总共"+result.pageInfo.pageInfo.pages+"页,"+"总共"+
      result.pageInfo.pageInfo.total+"条记录");
    total =  result.pageInfo.pageInfo.total;
    currentPage = result.pageInfo.pageInfo.pageNum;

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
    if(result.pageInfo.pageInfo.hasPreviousPage == false){
      firstPageLi.addClass("disabled");
      prePageLi.addClass("disabled");
    }else {
      prePageLi.click(function () {
        //跳转到当前页的前一页
        if(choose==0){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageInfo.pageNum - 1);

        }
        if(choose==1){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectPosition(result.pageInfo.pageInfo.pageNum - 1);

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
        selectPosition(1);

      }


    });



    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));

    //如果没有下一页不能被点击
    if(result.pageInfo.pageInfo.hasNextPage == false){
      nextPageLi.addClass("disabled");
      lastPageLi.addClass("disabled");
    }
    else{
      //给下一页和尾页添加点击事
      nextPageLi.click(function () {
        if(choose==0){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageInfo.pageNum + 1);


        }
        if(choose==1){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectPosition(result.pageInfo.pageInfo.pageNum + 1);


        }
      });
    };

    lastPageLi.click(function(){
      if(choose==0){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(result.pageInfo.pageInfo.pages);



      }
      if(choose==1){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        selectPosition(result.pageInfo.pageInfo.pages)

      }
    });

    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.pageInfo.pageInfo.navigatepageNums,function(index,item){
      var numLi = $("<li></li>").append($("<a></a>").append(item));
      //判断当前遍历的如果是当前正在显示的页面，应该高亮显示
      if(result.pageInfo.pageInfo.pageNum == item){
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
          selectPosition(item);

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
    var poId = $(this).val();
    //弹出确认对话框
    if(confirm("你确认删除"+$(this).parents("tr").find("td:eq(1)").text()+"吗?")){
      //发生ajax删除联系人
      $.ajax({
        url:"/hzl/deleteClass",
        type:"post",
        data:"poId="+poId,
        dataType:"json",
        success:function(result){
          if(choose == 0){
            //说明删除联系人成功
            //重新ajax请求到删除联系人的页面
            if(result.pageInfo){
              to_page(currentPage);

            }else {
              alert("职位下有员工，不能删除");
              to_page(currentPage);
            }
          }

          if(choose == 1){
            if(result.pageInfo){
              selectPosition(currentPage);

            }else {
              alert("职位下有员工，不能删除");
              selectPosition(currentPage);
            }
          }

        }



      });

    }
  });





  //给编辑按钮添加点击事件
  $(document).on("click",".btn.btn-primary.btn-xs",function(){

    $("#poId").val("");
    $("#poName1").val(''); ;
    $("#poMessage1").val("");

    var poId = $(this).val();
    //使用ajax请求部门的数据
    $.ajax(
      {
        url:"/hzl/selectPositionById",
        type:"post",
        data:"poId="+poId,
        dataType:"json",
        success:function(result){
          $("#department1").val(result[0].deid);
          $("#poId").val(result[0].poid);
          $("#poName1").val(result[0].poname);
          $("#poMessage1").val(result[0].pomessage);
        }


      }
    );

    //弹出对话框
    $('#createModal1').modal({
      backdrop:'static'
    })

  });

  //给编辑联系人的保存按钮增加点击事件
  $("#savePosition").click(function(){

    //使用ajax提交数据到后台更新数据这里需要
    //强调的是我们采用的是put方式的提交
    // data:$("#addEmpModal form").serialize()传递的请求参数中必须携带一个_method
    var poid = $("#poId").val();
    var poname = $("#poName1").val();
    var pomessage = $("#poMessage1").val();
    var podepartment = $("#department1").find("option:selected").text();
    var deid = $("#department1").val();
    $.ajax({
      url:"/hzl/updatePosition",
      type:"post",
      data:{
        poid:poid,
        poname:poname,
        pomessage:pomessage,
        podepartment:podepartment,
        deid:deid,
      },
      dataType:"json",
      success:function(result){
        //修改联系人成功之后，关闭修改联系人的摸态对话框
        if(choose==0){
          $("#createModal1").modal('hide');
          //跳转到当前的修改页面
          to_page(currentPage);
        }
        if(choose==1){
          $("#createModal1").modal('hide');
          //跳转到当前的修改页面
          selectPosition(currentPage);
        }
      }
    });

  });

  $(".btn-creation").click(function(){

    //弹出对话框
    $('#mycreateModal1').modal({
      backdrop:'static'
    })
  });

  function selectPosition(pn) {


    var content = $("#positionContent").val();
    var curPage = pn;
    $.ajax({
      type:"post",
      url:"/hzl/findClassByName",
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
    var checked = $("#position_list").find(":input[id='case']:checked");
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
        $.post("/hzl/deleteAllPosition", {"ids": ids}, function (data) {
          if(data){
            alert("已勾选职位删除成功!");
            to_page(currentPage);
          }else {
            alert("勾选职位下有职位，不能删除");
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


  $("#addPosition").click(function() {

    var poname = $("#poName").val();
    var pomessage = $("#poMessage").val();
    var podepartment = $("#department").find("option:selected").text();
    var deid = $("#department").val();


    $.ajax({
      type:"post",
      url:"/hzl/saveClass",
      data:{
        poname:poname,
        pomessage:pomessage,
        podepartment:podepartment,
        deid:deid,
      },
      dataType:"json",
      success:function () {

        $("#createModal").modal('hide');
        to_page(currentPage);


      }
    });

  });

</script>
</html>