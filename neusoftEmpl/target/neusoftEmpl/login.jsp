<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2019/8/26
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>

<a style="display:block;width:200px;height:42px;line-height:42px;font-size:18px;text-align:center;margin:40px auto;font-weight:800;" href="#" class="tbox">点击弹出</a>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title" align="center">登录框</h4>
                <br/>
                    <div class="form-group">
                        <label for="username" class="col-sm-offset-2 col-sm-2 control-label">账号</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="username" name="username" placeholder="请输您的入账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control"  id="password" name="password" placeholder="请输入您的密码">
                        </div>
                    </div>
                    <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-5">
                        <button id="submitBtn" type="submit" class="btn btn-default btn-block btn-primary">登录</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
        <!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(function(){
        $(".tbox").click(function(){
            $('#myModal').modal('show') //显示模态框
        })
      $("#submitBtn").click(function () {
        var username= $("#username").val();
        var password= $("#password").val();
        $.ajax({
          type:"post",
          url:"/hzl/dologin",
          data:{
            username:username,
            password:password,
          },
          dataType:"text",
          success:function (result) {
            if(result){
              window.open("/index.jsp");
            }else {
              window.open("/error.jsp");
            }
          }
        });

      })


    });
</script>
更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a>
</body>
</html>
