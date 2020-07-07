<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>Title</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>实习员工</title>

  <link href="./css/bootstrap-dialog.min.css" rel="stylesheet">
  <link href="./css/bootstrap.min.css" rel="stylesheet">
  <link href="./css/common.css" rel="stylesheet">
  <link rel="stylesheet" href="./css/upload.min.css">
  <script src="./js/jquery-1.11.1.min.js"></script>
  <script src="./js/bootstrap.min.js"></script>
  <script src="./js/bootstrap-dialog.min.js"></script>
  <script src="./js/bootstrap-pagy.min.js"></script>
  <%--<script src="./js/common.js"></script>--%>
  <%--<script src="./js/company_department.js"></script>--%>

</head>
<body>
<div class="container breadcrumb-container">
  <ol class="breadcrumb">
    <li>管理系统</li>
    <li>公告与下载</li>
    <li class="active">上传文档</li>
  </ol>
</div>
<h2>文件上传</h2>
<center>
  <div class="box">

    <h2>CSI</h2>
    <form method="post" enctype="multipart/form-data">

      <tr>
        <td>文件标题:</td>
        <td><input type="text" name="title" id="title"></td>
      </tr>
      </br>
      <tr>
        <td>创建人:</td>
        <td><input type="text" name="creater" id="creater"></td>
      </tr>
      <tr>
        </br>
        <td>文件描述:</td>
        <td><input type="text" name="desription" id="desription"></td>
      </tr>
      </br>
      <div class="inputBox">
        <input type="file" name="file" id="file">
      </div>
      <br/>
      <input type="button" value="上傳" name="fileUpload" id="fileUpload" />
    </form>
  </div>
</center>


</body>

</html>
<script type="text/javascript">

  $("#fileUpload").on('click', function () {

    var file_obj = document.getElementById('file').files[0];
    var fd = new FormData();
    var title = $("#title").val();
    var creater = $("#creater").val();
    var desription = $("#desription").val();

    fd.append('file', file_obj);
    fd.append('title', title);
    fd.append('creater', creater);
    fd.append('desription', desription);


    $.ajax({
      type: "POST",
      url: "/hzl/addFile",
      data: fd,
      cache: false,//文件不设置缓存
      processData: false,//数据不被转换为字符串
      contentType: false,//上传文件时使用，避免 JQuery 对其操作
      success: function (data) {
        alert("上传成功");
      },

    });
  });

</script>
