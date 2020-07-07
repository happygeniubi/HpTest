<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/7/17
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <title>员工信息管理系统</title>

  <!--[if lt IE 8]>
  <meta http-equiv="refresh" content="0;ie.html"/>
  <![endif]-->

  <link rel="shortcut icon" href="favicon.ico">
  <link href="./css/bootstrap.min.css" rel="stylesheet">
  <link href="./css/font-awesome.min.css" rel="stylesheet">
  <link href="./css/animate.min.css" rel="stylesheet">
  <link href="./css/style.min.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
  <!--左侧导航开始-->
  <nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
      <ul class="nav" id="side-menu">
        <li class="nav-header">
          <div class="dropdown profile-element">
            <span><img alt="image" class="img-circle" src="images/head.jpg"/></span>
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                  <span class="block m-t-xs"><strong class="font-bold"><shiro:principal></shiro:principal></strong></span>
                                <span class="text-muted text-xs block"<shiro:hasRole name="super_admin">>超级管理员<b
                                    class="caret"></b></span</shiro:hasRole>>
                                                                   <span class="text-muted text-xs block"
                                                                   <shiro:hasRole name="user">>普通用户<b class="caret"></b></span </shiro:hasRole> >

                                </span>
            </a>
            <ul class="dropdown-menu animated fadeInRight m-t-xs">
              <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
              </li>
              <li><a class="J_menuItem" href="profile.html">个人资料</a>
              </li>
              <li><a class="J_menuItem" href="contacts.html">联系我们</a>
              </li>
              <li><a class="J_menuItem" href="mailbox.html">信箱</a>
              </li>
              <li class="divider"></li>
              <li><a href="/hzl/doindexLogout">安全退出</a>
              </li>
            </ul>
          </div>
          <div class="logo-element">@
          </div>
        </li>
        <li>
          <a href="#">
            <i class="fa fa-user"></i>
            <span class="nav-label">员工信息管理</span>
            <span class="fa arrow"></span>
          </a>
          <ul class="nav nav-second-level">
            <li>
              <a class="J_menuItem" href="employees_formal.jsp" data-index="0">正式员工</a>
            </li>
            <li>
              <a class="J_menuItem" href="employees_internship.jsp">实习员工</a>
            </li>
            <li>
              <a class="J_menuItem" href="employees_training.html">培训项目</a>
            </li>
            <li>
              <a class="J_menuItem" href="employees_departure.jsp">离职员工</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#">
            <i class="fa fa-bar-chart-o"></i>
            <span class="nav-label">考勤及薪酬</span>
            <span class="fa arrow"></span>
          </a>
          <ul class="nav nav-second-level">
            <li>
              <a class="J_menuItem" href="work_attendance.html">员工考勤</a>
            </li>
            <li>
              <a class="J_menuItem" href="work_pay.html">员工薪酬</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"><i class="fa fa-briefcase"></i> <span class="nav-label">部门与职位</span><span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li><a class="J_menuItem" href="company_department.jsp">部门管理</a>
            </li>
            <li><a class="J_menuItem" href="company_position.jsp">职位管理</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"><i class="fa fa-shield"></i> <span class="nav-label">&nbsp;权限管理</span><span
              class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li><a class="J_menuItem" href="system_account.jsp">用户管理</a>
            </li>
            <li><a class="J_menuItem" href="system_role.html">角色管理</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"><i class="fa fa-file"></i> <span class="nav-label">公告与下载</span><span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li><a class="J_menuItem" href="public_notice.jsp">公告管理</a>
            </li>
            <li <%--<shiro:hasPermission name="file:upload">--%>><a class="J_menuItem" href="public_upload.jsp">上传文件</a>
            </li <%--</shiro:hasPermission>--%>>
            <li><a class="J_menuItem" href="public_download.jsp">下载中心</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
  <!--左侧导航结束-->
  <!--右侧部分开始-->
  <div id="page-wrapper" class="gray-bg dashbard-1">
    <div class="row border-bottom">
      <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
            class="fa fa-bars"></i> </a>
          <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
            <div class="form-group">
              <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
            </div>
          </form>
        </div>
      </nav>
    </div>
    <div class="row content-tabs">
      <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
      </button>
      <nav class="page-tabs J_menuTabs">
        <div class="page-tabs-content">
          <a href="javascript:;" class="active J_menuTab" data-id="employees_formal.html">正式员工</a>
        </div>
      </nav>
      <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
      </button>
      <div class="btn-group roll-nav roll-right">
        <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

        </button>
        <ul role="menu" class="dropdown-menu dropdown-menu-right">
          <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
          </li>
          <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
          </li>
        </ul>
      </div>
      <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
    </div>
    <div class="row J_mainContent" id="content-main">
      <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="employees_formal.jsp" frameborder="0"
              data-id="employees_formal.html" seamless></iframe>
    </div>
    <div class="footer">
      <div class="pull-right">&copy; 2019 <a href="http://www.zi-han.net/" target="_blank">Happyge's web</a>
      </div>
    </div>
  </div>
  <!--右侧部分结束-->
</div>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="./js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="./js/plugins/layer/layer.min.js"></script>
<script src="./js/hplus.min.js"></script>
<script type="text/javascript" src="./js/contabs.min.js"></script>
<script src="./js/plugins/pace/pace.min.js"></script>
</body>
</html>
