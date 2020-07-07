<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/8/8
  Time: 21:32
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
    <script src="./js/company_departure.js"></script>
</head>

<body>

<div class="container breadcrumb-container">
    <ol class="breadcrumb">
        <li>管理系统</li>
        <li>员工信息管理</li>
        <li class="active">离职员工</li>
    </ol>
</div>

<div class="container main-container">
    <!-- 代码区 -->
    <!-- div class="row">
      < div class="col-md-10">
        <select class="form-control w120 float-left margin-right-10 select-department" ><option value="-1">请选择部门</option></select><br>
        <select class="form-control w120 select-department" style="margin:-20px 20px;"><option value="-1">请选择职位</option></select>
        <div class="input-group w600 ">

          <input type="text" class="form-control text-search" placeholder="请输入搜索内容..." />
          <div class="input-group-btn">
            <button type="button" class="btn btn-primary btn-search">
              <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
            </button>
          </div>
        </div>
        </div>
      </div-->

    <tr valign="top">
        <td height="30">

            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">

                        <table width="100%" border="0" cellpadding="0" cellspacing="0">

                            <button style="float:right" type="button"class="btn btn-primary btn-creation" >
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;新增员工信息
                            </button>
                            <tr>
                                <td class="font3">
                                    职位：
                                    <select name="job_id" style="width:143px;">
                                        <option value="0">--请选择职位--</option>
                                        <c:forEach items="${requestScope.jobs }" var="job">
                                            <option value="${job.id }">${job.name }</option>
                                        </c:forEach>
                                    </select>
                                    姓名：<input type="text" name="name">
                                    身份证号码：<input type="text" name="cardId" maxlength="18">

                                </td>

                            </tr>

                            <tr>
                                <td class="font3">
                                    性别：
                                    <select name="sex" style="width:143px;">
                                        <option value="0">--请选择性别--</option>
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                    手机：<input type="text" name="phone">
                                    所属部门：<select  name="dept_id" style="width:100px;">
                                    <option value="0">--部门选择--</option>
                                    <c:forEach items="${requestScope.depts }" var="dept">
                                        <option value="${dept.id }">${dept.name }</option>
                                    </c:forEach>
                                </select>&nbsp;
                                    <input type="submit" value="搜索"/>
                                    <input id="delete" type="button" value="删除"/>

                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

    <!-- 数据列表 -->
    <table id="employee_departure_List" class="table table-bordered table-hover margin-top-20">
        <thead>
        <tr id="emList">
            <th align = center><input type="checkBox" id="selectAll" name="case" /></th>
            <th style="width:60px;">姓名</th>
            <th style="width:60px;">性别</th>
            <th style="width:150px;">身份证</th>
            <th style="width:100px;">上岗日期</th>
            <th style="width:150px;">所属部门</th>
            <th style="width:150px;">职位</th>
            <th style="width:60px;">状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="tbody-list">
        <!--里面的数据结合后台显示-->


        </tbody>
    </table>
    <div class="row">
        <!--分页文字信息  -->

        <div class="col-md-4" id="page_nav_area"></div>
        <!-- 分页条信息 -->
        <div class="col-md-8" id="page_info_area"></div>


    </div>

    <div class="container footer-container">
        开发者-happygod-<a href>http://www.happygeniubibibibi.com</a>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
  $(document).ready(function () {

    if(location.href.indexOf("#reloaded")==-1){
      location.href=location.href+"#reloaded";
      location.reload();
    }
  });
  //定义一个全局变量，获得当前中的记录数
  var total;

  //定义一个全局变量，记录当前修改的页面
  var currentPage;


  $(function(){
    //页面加载完毕，去首页获得对应的数据
    to_page(1);

  });

  $.selectDelete;
  function to_page(pn){
    $.ajax({
      url:"/hzl/quitEmployee",
      data:"curPage="+pn,
      type:"GET",
      success:function(result){
        //console.log(result);
        //1、解析并显示员工数据
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
    $("#employee_departure_List tbody").empty();
    //第一步：获得所有的员工数据
    var emps = result.pageInfo.list;
    //第二步：对员工数据进行遍历显示出来
    $.each(emps,function(index,item){
      var all = $("<input type='checkbox'  id='case' name='case' class='check_item'/>").attr("value",item.enId);
      var allDeleteBtn= $("<td></td>").append(all);
      var enName = $("<td></td>").append(item.enName);
      var enSex = $("<td></td>").append(item.enSex);
      var enIdentity = $("<td></td>").append(item.enIdentity);
      var enDate = $("<td></td>").append(item.enDate);
      var enDepartment = $("<td></td>").append(item.enDepartment);
      var enPost = $("<td></td>").append(item.enPost);
      var enStatus = $("<td></td>").append(item.enStatus);

      //添加编辑按钮
      var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs").attr("value",item.enId)
        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("重新录取");
      var delBtn =  $("<button></button>").addClass("btn btn-danger btn-xs").attr("value",item.enId)
        .append($("<span></span>").addClass("glyphicon glyphicon-minus")).append("销毁数据");

      var regordBtn =  $("<button></button>").addClass("btn btn-warning btn-xs").attr("value",item.enId)
        .append($("<span></span>").addClass("glyphicon glyphicon-align-left")).append("记录");

      var displayBtn = $("<button></button>").addClass("btn btn-info btn-xs").attr("value",item.enId)
        .append($("<span></span>").addClass("glyphicon glyphicon-link")).append("离职原因");


      var btnTd = $("<td></td>").append(editBtn).append(" ").append(regordBtn).append(" ").append(displayBtn).append(" ").append(delBtn);

      //这里我们点击编辑按钮的时候，在弹出编辑对话框之前我们要使用ajax到后台去查询对应的员工的信息，这里需要点击编辑按钮的时候，需要得到被修改员工的id，这里设计的比较巧秒，我们在创建出编辑按钮的时候，给编辑按钮添加一个自定义的属性。该属性值就是员工的id

      //               在点击编辑按钮的时候我们就可以通过该属性获得的该员工的id


      //将上面的table表td数据添加到tr中，这里是一个链式操作
      $("<tr></tr>").append(allDeleteBtn)
        .append(enName)
        .append(enSex)
        .append(enIdentity)
        .append(enDate)
        .append(enDepartment)
        .append(enPost)
        .append(enStatus)
        .append(btnTd)
        .appendTo("#employee_departure_List tbody");

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
        if(result.code == 200){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageNum - 1);

        }
        if(result.code == 300){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectNotice(result.pageInfo.pageNum - 1);

        }


      });
    };
    //给前一页和首页添加按钮点击事件
    firstPageLi.click(function(){
      //跳转到当前页的前一页
      if(result.code == 200){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(1);


      }
      if(result.code == 300){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        selectNotice(1);

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
        if(result.code == 200){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(result.pageInfo.pageNum + 1);



        }
        if(result.code == 300){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectNotice(result.pageInfo.pageNum + 1);


        }
      });
    };
    lastPageLi.click(function(){
      if(result.code == 200){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        to_page(result.pageInfo.pages);



      }
      if(result.code == 300){
        //说明删除联系人成功
        //重新ajax请求到删除联系人的页面
        history.back(0)

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
        if(result.code == 200){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          to_page(item);
        }
        if(result.code == 300){
          //说明删除联系人成功
          //重新ajax请求到删除联系人的页面
          selectNotice(item);

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
    var enId = $(this).val();
    //弹出确认对话框
    if(confirm("你确认删除 \" "+$(this).parents("tr").find("td:eq(1)").text()+"\" 吗?")){
      //发生ajax删除联系人
      $.ajax({
        url:"/hzl/deleteEmployee",
        type:"post",
        data:"enId="+enId,
        dataType:"json",
        success:function(result){
          if(result.code == 200){
            //说明删除联系人成功
            //重新ajax请求到删除联系人的页面
            to_page(currentPage);
          }

          if(result.code == 300){
            selectNotice(currentPage);
          }

        }



      });

    }
  });



  //给录取按钮添加点击事件
  $(document).on("click",".btn.btn-primary.btn-xs",function(){
    //首先需要获得要删除的联系人的姓名如何获得了,主要这里不能写成parent，暂时还没有弄明白
    //alert($(this).parents("tr").find("td:eq(1)").text());
    //删除联系人的id
    var enId = $(this).val();
    //弹出确认对话框
    if(confirm("你确认重新录取 \" "+$(this).parents("tr").find("td:eq(1)").text()+"\" 吗?")){
      //发生ajax删除联系人
      $.ajax({
        url:"/hzl/ReAdmissionEmployee",
        type:"post",
        data:"enId="+enId,
        dataType:"json",
        success:function(result){
          if(result.code == 200){
            //说明删除联系人成功
            //重新ajax请求到删除联系人的页面
            to_page(currentPage);
          }

          if(result.code == 300){
            selectNotice(currentPage);
          }

        }



      });

    }
  });




  /*
  *
  *
  *
  *
  *
  *
  * */



</script>
