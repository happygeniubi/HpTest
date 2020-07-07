<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/8/21
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
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
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/bootstrap-pagy.min.js"></script>
    <script src="./js/bootstrap-dialog.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/company_position.js"></script>
</head>

<body>

<div class="container breadcrumb-container">
    <ol class="breadcrumb">
        <li>理系统</li>
        <li>公告与下载</li>
        <li class="active">公告管理</li>
    </ol>
</div>

<div class="container main-container">
    <!-- 代码区 -->
    <div class="row">
        <div class="col-md-10">
            <div class="input-group w600">

                <input id="content" type="text" class="form-control text-search" placeholder="请输公告名称..." />
                <div class="input-group-btn">
                    <button onclick="selectClass(1)" type="button" class="btn btn-primary btn-search">
                        <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-2 text-align-right">
            <button type="button" class="btn btn-primary btn-creation">
                <span class="glyphicon glyphicon-plus"></span>&nbsp;新增公告
            </button>
        </div>
    </div>
    <!-- 数据列表 -->
    <table class="table table-bordered table-hover margin-top-20">
        <thead>
        <tr>
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
    <ul id="class_page" class="pagination">


    </ul>

</div>

<div class="container footer-container">
    开发者-happygod-<a href>http://www.happygeniubibibibi.com</a>
</div>

<!-- Modal -->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="mycreateModal">
    <div class="modal-dialog" role="document" style="width:430px;">
        <div class="modal-content login-modal-content">
            <div class="modal-header modal-header-primary">
                <h4 class="modal-title" id="mycreateModal">新增公告</h4>
            </div>
            <div class="modal-body">
                <select id="department" class="form-control posi-department">

                    <option value ="-1">请选择</option>

                </select>
                <input type="tel" id="poName" class="form-control margin-top-10 posi-name" placeholder="职位名称">
                <textarea id="poMessage" class="form-control margin-top-10 posi-desc" rows="2" placeholder="职位描述信息"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" onclick="saveClass()"  class="btn btn-primary btn-save" >保存</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">


</script>
</html>