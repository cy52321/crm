<%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 2021/3/9
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="#" />
    <script>
        //启动执行函数
        $(function (){
            if (window.top!=window){
                window.top.location=window.location;
            }
            //页面加载完毕后，将用户文本框中的内容清空
            $("#loginAct").val("");
            //页面加载完毕后，让用户的文本框自动获得焦点
            $("#loginAct").focus();
            //为登录按钮绑定事件，执行登录操作-点击按钮
            $("#submitBtn").on("click",function (){
                login();
            })
            //为登录按钮绑定事件，执行登录操作-回车提交
            $(window).on("keydown",function (event){
                if (event.keyCode==13){
                    login();
                }
            })
        })
        //登录账号验证
        function login(){
            //获取账号和密码
            $("#msg").html("");
            var loginAct = $.trim($("#loginAct").val());
            var loginPwd = $.trim($("#loginPwd").val());
            if (loginAct==""||loginPwd==""){
                $("#msg").html("账号或密码不能为空");
                return false;
            }
            $.ajax({
                url:"/userlogin",
                data:{
                    "loginAct":loginAct,
                    "loginPwd":loginPwd
                },
                type:"post",/*
                async: false,*/
                dataType:"json",
                success:function (data){
                    if (data.success=="true"){
                        window.location.href="workbench/index.jsp";
                    }else{
                        $("#msg").html(data.msg);
                    }
                },
                error:function(){
                    window.location.href="workbench/index.jsp";
                }
            })
        }
    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="images/2.jpg" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">2021 &nbsp;<span style="font-size: 12px;">&copy;2021&nbsp;物联网平台</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input class="form-control" type="text" placeholder="用户名"id="loginAct">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input class="form-control" type="password" placeholder="密码"id="loginPwd">
                </div>
                <div class="checkbox"  style="position: relative;top: 30px; left: 10px;">

                    <span id="msg" style="color:red;"></span>

                </div>
                <button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
