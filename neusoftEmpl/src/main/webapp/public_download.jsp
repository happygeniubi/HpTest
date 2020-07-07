<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2019/8/19
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<title>Title</title>
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
</head>
<body>
<div class="container breadcrumb-container">
  <ol class="breadcrumb">
    <li>管理系统</li>
    <li>公告与下载</li>
    <li class="active">下载中心</li>
  </ol>
</div>
<!-- 显示分页信息 -->
<div class="container main-container">
<div class="row">
  <!--分页文字信息  -->
  <div class="col-md-10">
    <div class="input-group w600">
      <input id="content" type="text" value="" class="form-control text-search" placeholder="请输文件名称..."/>
      <div class="input-group-btn">
        <button type="button" onclick="" class="btn btn-primary btn-search">
          <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
        </button>
      </div>
    </div>
  </div>

</div>

<table id="fileDisplay" class="table table-bordered table-hover margin-top-20">
  <thead>
  <tr>
    <th>文件标题</th>
    <th>创建人</th>
    <th>创建日期</th>
    <th>文件描述</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody class="tbody-list">
  </tbody>
</table>
<div class="row">
  <div class="col-md-4" id="page_nav_area"></div>
  <!-- 分页条信息 -->
  <div class="col-md-8" id="page_info_area"></div>
</div>
</div>


</body>
</html>
<script type="text/javascript">


  var currentPage;

  var choose;

  $(function () {
    //页面加载完毕，去首页获得对应的数据
    to_page(1);

  });

  function to_page(pn) {
    $.ajax({
      url: "/hzl/listdownload",
      data: "curPage=" + pn,
      type: "GET",
      success: function (result) {
       console.log(result);
        //1、解析并显示员工数据

        build_emps_table(result);
        //2、解析并显示分页信息
        build_page_info(result);
        //3、解析显示分页条数据
        build_page_nav(result);
      }
    });
  };

  function build_emps_table(result) {
    //在构建之前先清空所有的数据
    $("#fileDisplay tbody").empty();
    //第一步：获得所有的员工数据
    var emps = result.pageInfo.list;
    //第二步：对员工数据进行遍历显示出来
    $.each(emps, function (index, item) {
      var noTitlie = $("<td></td>").append(item.flTitle);
      var noMessage = $("<td></td>").append(item.flPeople);
      var noPeople = $("<td></td>").append(item.flDate);
      var noDate = $("<td></td>").append(item.flDescribe);

      //添加编辑按钮
      var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs").attr("value", item.flID)
        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
      var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs").attr("value", item.flID)
        .append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
      var downBtn = $("<a></a>").addClass("btn btn-info btn-xs").attr("href","/hzl/download?filename="+item.flFile)
        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("下載");


      var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ").append(downBtn);

      //这里我们点击编辑按钮的时候，在弹出编辑对话框之前我们要使用ajax到后台去查询对应的员工的信息，这里需要点击编辑按钮的时候，需要得到被修改员工的id，这里设计的比较巧秒，我们在创建出编辑按钮的时候，给编辑按钮添加一个自定义的属性。该属性值就是员工的id

      //               在点击编辑按钮的时候我们就可以通过该属性获得的该员工的id


      //将上面的table表td数据添加到tr中，这里是一个链式操作
      $("<tr></tr>")
        .append(noTitlie)
        .append(noMessage)
        .append(noPeople)
        .append(noDate)
        .append(btnTd)
        .appendTo("#fileDisplay tbody");

      //"#emps_table tbody"表示选取id为emps_table下的所有的<tbody>的元素，不清楚的看锋利的jquery书籍相当的经典


    });

  };

  //解析显示分页信息
  function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第" + result.pageInfo.pageNum + "页," + "总共" + result.pageInfo.pages + "页," + "总共" +
      result.pageInfo.total + "条记录");
    total = result.pageInfo.total;
    currentPage = result.pageInfo.pageNum;

  }

  //解析右下角的导航栏
  function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#").attr("aria-label", "Previous"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("aria-label", "Previous"));
    //判断是否有前一页，如果没有前一页就不能点击
    if (result.pageInfo.hasPreviousPage == false) {
      firstPageLi.addClass("disabled");
      prePageLi.addClass("disabled");
    } else {
      prePageLi.click(function () {
        //跳转到当前页的前一页

        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(result.pageInfo.pageNum - 1);


      });
    }
    ;
    //给前一页和首页添加按钮点击事件
    firstPageLi.click(function () {
      //跳转到当前页的前一页

      //说明删除联系人成功
      //重新ajax请求到删除联系人的页面
      to_page(1);


    });


    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));

    //如果没有下一页不能被点击
    if (result.pageInfo.hasNextPage == false) {
      nextPageLi.addClass("disabled");
      lastPageLi.addClass("disabled");
    }
    else {
      //给下一页和尾页添加点击事
      nextPageLi.click(function () {

        to_page(result.pageInfo.pageNum + 1);


      });
    }
    ;
    lastPageLi.click(function () {

      //说明删除联系人成功
      //重新ajax请求到删除联系人的页面
      to_page(result.pageInfo.pages);


      //说明删除联系人成功
      //重新ajax请求到删除联系人的页面


    });

    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.pageInfo.navigatepageNums, function (index, item) {
      var numLi = $("<li></li>").append($("<a></a>").append(item));
      //判断当前遍历的如果是当前正在显示的页面，应该高亮显示
      if (result.pageInfo.pageNum == item) {
        numLi.addClass("active");
      }
      //添加点击事件
      numLi.click(function () {
        //点
        // 击的时候重新使用ajax到服务器查询数据

        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(item);
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面


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
  $(document).on("click", ".btn.btn-danger.btn-xs", function () {
    //首先需要获得要删除的联系人的姓名如何获得了,主要这里不能写成parent，暂时还没有弄明白
    //alert($(this).parents("tr").find("td:eq(1)").text());
    //删除联系人的id
    var noId = $(this).val();
    //弹出确认对话框
    if (confirm("你确认删除" + $(this).parents("tr").find("td:eq(1)").text() + "吗?")) {
      //发生ajax删除联系人
      $.ajax({
        url: "/hzl/deleteNotice",
        type: "post",
        data: "noId=" + noId,
        dataType: "json",
        success: function (result) {
            //说明删除联系人成功
            //重新ajax请求到删除联系人的页面
            to_page(currentPage);


        }


      });

    }
  });




</script>