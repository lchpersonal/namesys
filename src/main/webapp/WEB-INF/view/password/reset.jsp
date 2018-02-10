<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="Description" content=""/>
    <meta name="keywords" content=""/>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/jquery.md5.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <style type="text/css">

        body {
           /* background: url(/static/bk.jpg) no-repeat;*/
            height: 100%;
            width: 100%;
            overflow: hidden;
            background-size: cover;
        }

        .inputCss{
            outline:0;
            border: 1px solid #b6262f;
            box-shadow: 0px 0px 10px 0px #f95d5d;
            -moz-transition:border ease-in-out 0.15s,box-shadow ease-in-out 0.15s;
            -webkit-transition:border ease-in-out 0.15s,box-shadow ease-in-out 0.15s;
            height: 35px;
            width: 250px;
            background-color: #333333;
            color: #ffffff;
            padding-left: 10px;
        }

        .btn {
            width: 250px;
            padding:8px;
            background-color: #b6262f;
            border-color: #b6262f;
            color: #fff;
            -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
            border-radius: 20px; /* future proofing */
            -khtml-border-radius: 20px; /* for old Konqueror browsers */
            text-align: center;
            vertical-align: middle;
            border: 0px solid transparent;
            font-weight: 900;
            font-size:125%;
        }

        .btn:focus{outline:none!important;}

    </style>
</head>
<body class="m0">
<div style="background: #000; height: 100%; width: 100%">
    <div style="text-align: center;position: relative;top: 50%;transform: translateY(-50%);">
        <div style="margin-bottom: 68px">
            <span style="color: #fff;font-size: 25px">修改密码</span>
        </div>
        <div class="mb20">
            <input type="text" placeholder="用户名" id="username" class="inputCss" />
        </div>
        <div class="mb20">
            <input type="password" placeholder="原密码" id="password" class="inputCss"/>
        </div>
        <div class="mb20">
            <input type="password" placeholder="新密码" id="newPassword" class="inputCss"/>
        </div>
        <div class="mb20">
            <input type="password" placeholder="重复新密码" id="newPassword2" class="inputCss"/>
        </div>
        <div>
            <input type="button" id="reset" class="btn ripple" value="修&nbsp;&nbsp;改">
        </div>
        <br/>
        <br/>
        <br/>
    </div>
</div>
<div class="alertBkDiv" >
</div>
<div class="alertdiv bgred" style="opacity: 0.8; color:#fff">
</div>
<script type="text/javascript">

    $(function () {
       $("#reset").click(function () {
           var username = $.trim($("#username").val());
           var password = $.trim($("#password").val());
           var newPassword = $.trim($("#newPassword").val());
           var newPassword2 = $.trim($("#newPassword2").val());
           if(username == "" || password==""){
               showMsg("用户名或旧密码不能为空");
               return;
           }
           if(newPassword == "" || newPassword2==""){
               showMsg("新密码不能为空");
               return;
           }
           if(newPassword != newPassword2){
               showMsg("两次输入的密码不一致");
               return;
           }
           $.post("/user/password/reset.json", {
                   "username": username,
                   "password": $.md5(password),
                   "newPassword": $.md5(newPassword),
                   "confirm": $.md5(newPassword2)
               },
               function (data) {
                   if (data != null && data.result.code == 0) {
                       window.location.href = "/login";
                   } else {
                       showMsg(data.result.detail);
                   }
               });
       });

       function showMsg(msg) {
           $(".alertdiv").text(msg);
           $(".alertBkDiv").show();
           $(".alertdiv").fadeIn();
           setTimeout(function () {
               $(".alertdiv").fadeOut();
               $(".alertBkDiv").fadeOut();
           }, 1200);
       }
    });
</script>
</body>
</html>
