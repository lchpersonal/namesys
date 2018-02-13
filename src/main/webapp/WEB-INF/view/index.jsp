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
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <style type="text/css">
        .hrefcss2 {
            font-size: 14px;
            color: #fff;
            display: block;
            border: 1px solid #eee;
            text-decoration: none;
            cursor: auto;
            line-height: 40px;
            text-align: center;
            margin: 0 0 15px 0px;
        }
    </style>
</head>
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb20">
    <a href="#" onclick="javascript :history.back(-1);" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">首页</span>
    <a href="javascript:void(0);"  id="logout" class="fr f18 m0 p0 dib red tdn">登出</a>
</div>
<table width="100%" align="center" class="mt30">
    <tr>
        <td align="center">
            <a href="/sts/selfinfo" id="selfinfo" class='hrefcss2 w200 bgred'>个人信息</a>
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="/jst/name/addname" id="addname" class='hrefcss2 w200 bgred'>添加名单</a>
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="/name/select"  id="viewname" class=' hrefcss2 w200 bgred'>查看名单</a>
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="/jst/echelon/echelon"  id="echelon" class=' hrefcss2 w200 bgred'>分梯队</a>
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="/paving/namelist"  id="paving" class=' hrefcss2 w200 bgred'>铺垫记录</a>
        </td>
    </tr>
    <c:if test="${user.type > 0}">
        <tr>
            <td align="center">
                <a href="javascript:alert('此功能暂不开放');"  id="children" class=' hrefcss2 w200 bgred'>伞下主任</a>
            </td>
        </tr>
    </c:if>
    <c:if test="${user.username=='chengli'}">
        <tr>
            <td align="center">
                <a href="/jst/sys/addUser"  id="addUser" class='hrefcss2 w200 bgred'>添加用户</a>
            </td>
        </tr>
    </c:if>
</table>
<script type="text/javascript">
    $(function(){
        $("#logout").click(function () {
            $.post("/logout.json", {}, function (data) {
                if (data.result.code == 0) {
                    location.href = "/";
                }
            });
        });
    });
</script>
</body>
</html>
